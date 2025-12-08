
package com.utp.pizzatime.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SQLConexion {
    private static final Logger log = LoggerFactory.getLogger(SQLConexion.class);

    private Connection conn = null;

    private final String usuario = "adminbdpizza";              // Para todos
    private final String contrasenia = "Prueba-bdpizza123";  // Para todos
    private final String bd = "BDPIZZA";              

        // Servidor de Azure 
    private final String ip = "bdpizza.database.windows.net"; 
    private final String puerto = "1433";   

    // Cadena JDBC actualizada para Azure SQL
    private final String cadena = "jdbc:sqlserver://" 
        + ip + ":" + puerto 
        + ";database=" + bd 
        + ";encrypt=true;trustServerCertificate=false;loginTimeout=30";

    public Connection establecerConexion() {
        try {
            conn = DriverManager.getConnection(cadena, usuario, contrasenia);
            log.info("Conexión exitosa a Azure SQL en {}:{} (BD={})", ip, puerto, bd);
            
        } catch (SQLException e) {
            log.error("Conexión fallida a Azure SQL en {}:{} (BD={}) -> {}",
                      ip, puerto, bd, e.getMessage());
        }
        return conn;
    }

}
