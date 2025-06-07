
package com.utp.pizzatime.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class SQLConexion {
    
    private Connection conectar = null;

    private final String usuario = "sa";              // Tu usuario SQL Server
    private final String contrasenia = "1234";  // Tu contraseña
    private final String bd = "BDPIZZA";              // Nombre de tu base de datos
    private final String ip = "localhost";            // IP o nombre del host
    private final String puerto = "1433";             // Puerto por defecto de SQL Server

    private final String cadena = "jdbc:sqlserver://" + ip + ":" + puerto + 
        ";databaseName=" + bd + 
        ";encrypt=true;trustServerCertificate=true";

    public Connection establecerConexion() {
        try {
            conectar = DriverManager.getConnection(cadena, usuario, contrasenia);
            JOptionPane.showMessageDialog(null, "Conexión exitosa a la base de datos");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar: " + e.getMessage());
        }
        return conectar;
    }

}
