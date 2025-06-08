package com.utp.pizzatime.model.dao.impl;

import com.utp.pizzatime.model.dao.PedidoDAO;
import com.utp.pizzatime.model.entity.Pedido;
import com.utp.pizzatime.model.entity.DetallePedido;
import com.utp.pizzatime.util.SQLConexion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.UUID;

/**
 *
 * @author BeeIsMega
 */
public class I_PedidoDAO implements PedidoDAO {

    private static final Logger log = LoggerFactory.getLogger(I_PedidoDAO.class);
    private final SQLConexion sqlCon = new SQLConexion();

    private static final String SQL_INSERT_PEDIDO
            = "INSERT INTO PEDIDO (ID_PED, DNI_EMP, FECHA_PEDIDO, ESTADO_PEDIDO) "
            + "VALUES (?, ?, ?, ?)";

    private static final String SQL_INSERT_DETALLE
            = "INSERT INTO DETALLE_PEDIDO (ID_PED_DET, ID_PRO, ID_PED, CANTIDAD_CAJAS) "
            + "VALUES (?, ?, ?, ?)";

    @Override
    public void insertPedidoConDetalles(Pedido pedido) throws SQLException {
        Connection conn = sqlCon.establecerConexion();
        try {
            conn.setAutoCommit(false);

            // Genera el ID del pedido si no lo tiene
            String idPed = nextPedidoId(conn);
            pedido.setIdPed(idPed);

            // Insertar la cabecera del pedido
            try (PreparedStatement psPed = conn.prepareStatement(SQL_INSERT_PEDIDO)) {
                psPed.setString(1, pedido.getIdPed());
                psPed.setInt(2, pedido.getDniEmp());
                psPed.setDate(3, new java.sql.Date(pedido.getFechaPedido().getTime()));
                psPed.setString(4, "NULL"); 
                psPed.executeUpdate();
            }

            // Insertar todos los detalles en batch
            try (PreparedStatement psDet = conn.prepareStatement(SQL_INSERT_DETALLE)) {
                int idx = 1;
                for (DetallePedido det : pedido.getDetalles()) {
                    if (det.getIdDet() == null) {
                        String sufijo = String.format("%02d", idx++);
                        String idDet = idPed + sufijo;
                        det.setIdDet(idDet);
                        det.setIdPed(idPed);
                    }
                    psDet.setString(1, det.getIdDet());
                    psDet.setString(2, det.getID_PRO());
                    psDet.setString(3, det.getIdPed());
                    psDet.setInt(4, det.getCantidadCajas());
                    psDet.addBatch();
                }
                psDet.executeBatch();
            }

            conn.commit();
            log.info("Pedido {} insertado con {} detalles",
                    pedido.getIdPed(), pedido.getDetalles().size());

        } catch (SQLException ex) {
            conn.rollback();
            log.error("Error al insertar pedido {}, se hizo rollback",
                    pedido.getIdPed(), ex);
            throw ex;
        } finally {
            conn.setAutoCommit(true);
            conn.close();
        }
    }

    /**
     * Lee de la tabla PEDIDO el valor máximo actual de ID_PED (como entero), le
     * suma 1 y devuelve un String de 4 dígitos con ceros a la izquierda.
     */
    private String nextPedidoId(Connection conn) throws SQLException {
        String sql = "SELECT MAX(CAST(ID_PED AS INT)) FROM PEDIDO";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            int max = 0;
            if (rs.next() && rs.getString(1) != null) {
                max = rs.getInt(1);
            }
            int siguiente = max + 1;
            // Formatea a 4 dígitos (hasta 9999). Si quieres 6, usa "%06d"
            return String.format("%04d", siguiente);
        }
    }

    /**
     * Mismo enfoque para DETALLE_PEDIDO.
     */
    private String nextDetalleId(Connection conn) throws SQLException {
        String sql = "SELECT MAX(CAST(ID_PED_DET AS INT)) FROM DETALLE_PEDIDO";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            int max = 0;
            if (rs.next() && rs.getString(1) != null) {
                max = rs.getInt(1);
            }
            int siguiente = max + 1;
            return String.format("%04d", siguiente);
        }
    }

}
