/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.pizzatime.service;

import com.utp.pizzatime.util.FecVenMonitorMBean;
import com.utp.pizzatime.util.SQLConexion;
import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.management.MBeanServer;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;

/**
 *
 * @author Laura
 */
public class FecVenService {

    private final SQLConexion sqlc = new SQLConexion();//instancia de conexion 
    private static final String SQL_FECVENPROX
            = """
              WITH FecPrioridad AS (
                  SELECT 
                      d.ID_PRO,
                      p.LOTE,
                      p.VENCIMIENTO,
                      ROW_NUMBER() OVER (PARTITION BY d.ID_PRO ORDER BY p.FECHA_PRIO ASC) AS rn
                  FROM PRIORIDAD p
                  JOIN MOVIMIENTO_COCINA m ON p.ID_MOV = m.ID_MOV
                  JOIN DISPONIBLE d ON m.ID_DIS = d.ID_DIS
                  WHERE p.VENCIMIENTO <= DATEADD(DAY, 2, GETDATE())
              ),
              FecDisponible AS (
                  SELECT 
                      d.ID_PRO,
                      d.LOTE,
                      d.VENCIMIENTO,
                      ROW_NUMBER() OVER (PARTITION BY d.ID_PRO ORDER BY d.VENCIMIENTO ASC) AS rn
                  FROM DISPONIBLE d
                  WHERE d.VENCIMIENTO <= DATEADD(DAY, 2, GETDATE())
              )
              SELECT
                  COALESCE(fp.ID_PRO, fd.ID_PRO) AS ID_PRO,
                  COALESCE(fp.LOTE, fd.LOTE) AS LOTE,
                  COALESCE(fp.VENCIMIENTO, fd.VENCIMIENTO) AS VENCIMIENTO,
                  CASE 
                      WHEN fp.ID_PRO IS NOT NULL THEN 'PRIORIDAD'
                      ELSE 'DISPONIBLE'
                  END AS ORIGEN
              FROM FecPrioridad fp
              FULL OUTER JOIN FecDisponible fd 
                  ON fp.ID_PRO = fd.ID_PRO AND fp.LOTE = fd.LOTE
              WHERE (fp.rn = 1 OR fp.rn IS NULL) AND (fd.rn = 1 OR fd.rn IS NULL);""";
    //agarra las fechas de vencimiento de la tabla prioridad y disponible y las pone en dos subconsultas
    //en donde el row saca los q van a vencer en dos dias
    //luego se unen para verificar si estan hay o no en prioridad sino se saca de disponible 

    public void VeryNotifIng() {
        try (Connection conn = sqlc.establecerConexion(); PreparedStatement ps = conn.prepareStatement(SQL_FECVENPROX)) {
            ResultSet rs = ps.executeQuery();//ejecuta

            StringBuilder msg = new StringBuilder();//clase q permite hacer varios append para acumular los mensajes

            while (rs.next()) {
                msg.append("El Ingrediente: ").append(rs.getString("ID_PRO")).append(" con lote ").append(rs.getString("LOTE")).
                        append(" vence el ").append(rs.getDate("VENCIMIENTO")).append("\n");
            }
            
            if (!msg.isEmpty()) {
                MBeanServer mbeanserv= ManagementFactory.getPlatformMBeanServer();//agarra server MBeans
                ObjectName objname= new ObjectName("miapp.vencimientos:type=FecVenMonitor");//da nombre
                FecVenMonitorMBean fecvenmonitorr= MBeanServerInvocationHandler.newProxyInstance(mbeanserv, objname,
                        FecVenMonitorMBean.class, false);//hace el proxy
                fecvenmonitorr.notifFecvenci(msg.toString());  //pasa el mensaje para q pueda ser llamado  
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

}
