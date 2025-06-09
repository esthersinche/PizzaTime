
package com.utp.pizzatime.model.dao.impl;
import com.utp.pizzatime.model.entity.MovimientoCocina;
import com.utp.pizzatime.util.SQLConexion;
import java.sql.*;
import java.util.List;
import java.util.UUID;
/**
 *
 * @author Charisse
 */
public class I_MovimientoCocinaDAO {
    
    private String generarNuevoID(Connection conn) throws SQLException {
        String sql = "SELECT MAX(ID_MOV) AS max_id FROM MOVIMIENTO_COCINA WHERE ID_MOV LIKE 'MOV%'";
        try (PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            if (rs.next()) {
                String ultimoId = rs.getString("max_id");
                if (ultimoId != null) {
                    int num = Integer.parseInt(ultimoId.substring(3)); // Quita el "MOV"
                    return String.format("MOV%05d", num + 1);
                }
            }
        }
        return "MOV00001"; // Primer registro
    }

    private static final String SQL_INSERT =
        "INSERT INTO MOVIMIENTO_COCINA (ID_MOV, ID_DIS, TIPO, DNI_EMP, CANTIDAD_UNIT, CANTIDAD_CAJAS, LOTE, FECHA_MOV, MOTIVO) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public void registrarMovimientos(List<MovimientoCocina> movimientos) {
        try (Connection conn = new SQLConexion().establecerConexion();
             PreparedStatement st = conn.prepareStatement(SQL_INSERT)) {

            for (MovimientoCocina m : movimientos) {
                
                // DEBUG: Mostrar valores que se van a insertar
                System.out.println("Insertando movimiento:");
                System.out.println("  ID_DIS: " + m.getId_dis());
                System.out.println("  DNI_EMP: " + m.getDni_emp());
                System.out.println("  CANTIDAD_UNIT: " + m.getCantidad_unit());
                System.out.println("  CANTIDAD_CAJAS: " + m.getCantidad_cajas());
                System.out.println("  LOTE: " + m.getLote());
                
                String nuevoID = generarNuevoID(conn);
                st.setString(1, nuevoID);
                st.setString(2, m.getId_dis());
                st.setString(3, "Cocina");
                st.setInt(4, m.getDni_emp());
                st.setInt(5, 0);
                st.setInt(6, m.getCantidad_cajas());
                st.setString(7, m.getLote());
                st.setDate(8, new java.sql.Date(System.currentTimeMillis()));
                st.setString(9, "Preparación de platos");

                st.executeUpdate();
                System.out.println("Registrando salida: " + m.getId_dis() + ", " + m.getCantidad_cajas());

            }

        } catch (SQLException e) {
            System.err.println("Error al registrar movimientos en cocina:");
            e.printStackTrace();
        }
    }
}
