package com.utp.pizzatime.model.dao.impl;

import com.utp.pizzatime.model.dao.EmpleadoDAO;
import com.utp.pizzatime.model.entity.Empleado;
import com.utp.pizzatime.util.SQLConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author BeeIsMega
 */
public class EmpleadoDAOI implements EmpleadoDAO {

    private static final String SQL_LOGIN
            = "SELECT DNI_EMP, NOMBRE_EMP, ROL, PASS "
            + "FROM EMPLEADO "
            + "WHERE DNI_EMP = ? AND PASS = ?";

    private final SQLConexion sqlCon = new SQLConexion();

    @Override
    public Empleado findByDniAndPass(int dni, String pass) throws SQLException {
        // El try-with-resources cierra conn, stmt y rs automáticamente
        try (Connection conn = sqlCon.establecerConexion(); PreparedStatement st = conn.prepareStatement(SQL_LOGIN)) {

            st.setInt(1, dni);
            st.setString(2, pass);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Empleado(
                            rs.getInt("DNI_EMP"),
                            rs.getString("NOMBRE_EMP"),
                            rs.getString("ROL"),
                            null // NO devolvemos la pass en texto claro
                    );
                }
                return null;
            }
        }
    }
}
