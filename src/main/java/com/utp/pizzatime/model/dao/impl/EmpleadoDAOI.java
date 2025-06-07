package com.utp.pizzatime.model.dao.impl;

import com.utp.pizzatime.model.dao.EmpleadoDAO;
import com.utp.pizzatime.model.entity.Empleado;
import com.utp.pizzatime.util.SQLConexion;
import java.sql.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author EstherSinche
 */
public class EmpleadoDAOI implements EmpleadoDAO {

    private static final Logger log = LoggerFactory.getLogger(EmpleadoDAOI.class);

    private static final String SQL_LOGIN
            = "SELECT DNI_EMP, NOMBRE_EMP, ROL, PASS "
            + "FROM EMPLEADO "
            + "WHERE DNI_EMP = ? AND PASS = ?";

    private final SQLConexion sqlCon = new SQLConexion();

    @Override
    public Empleado findByDniAndPass(int dni, String pass) throws SQLException {
        log.debug("Invocando findByDniAndPass(dni={}, pass=***)", dni);
        try (Connection conn = sqlCon.establecerConexion(); PreparedStatement st = conn.prepareStatement(SQL_LOGIN)) {

            st.setInt(1, dni);
            st.setString(2, pass);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    log.info("Login exitoso para DNI {}", dni);
                    return new Empleado(
                            rs.getInt("DNI_EMP"),
                            rs.getString("NOMBRE_EMP"),
                            rs.getString("ROL"),
                            null
                    );
                } else {
                    log.warn("Login fallido para DNI {}", dni);
                    return null;
                }
            }

        } catch (SQLException e) {
            log.error("Error al ejecutar login para DNI {}", dni, e);
            throw e;
        }
    }
}
