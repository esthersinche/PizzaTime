
package com.utp.pizzatime.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SQLConexion {
    private static final Logger log = LoggerFactory.getLogger(SQLConexion.class);

    private Connection conn = null;

    private final String usuario = "sa";              // Tu usuario SQL Server
    private final String contrasenia = "1234";  // Tu contraseña
    private final String bd = "BDPIZZA";              // Nombre de tu base de datos
    private final String ip = "localhost";            // IP o nombre del host
    private final String puerto = "1433";             // Puerto por defecto de SQL Server

    private final String cadena = "jdbc:sqlserver://" 
        + ip + ":" + puerto + 
        ";databaseName=" + bd + 
        ";encrypt=true;trustServerCertificate=true";

    public Connection establecerConexion() {
        try {
            conn = DriverManager.getConnection(cadena, usuario, contrasenia);
            log.info("Conexion exitosa a la base de datos {}:{} (BD={})", ip, puerto, bd);
            
        } catch (SQLException e) {
            log.info("Conexion fallida a la base de datos {}:{} (BD={})", ip, puerto, bd);
        }
        return conn;
    }

}
