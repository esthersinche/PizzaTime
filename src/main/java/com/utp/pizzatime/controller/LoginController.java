package com.utp.pizzatime.controller;

import com.utp.pizzatime.model.dao.EmpleadoDAO;
import com.utp.pizzatime.model.entity.Empleado;
import com.utp.pizzatime.model.dao.impl.EmpleadoDAOI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author EstherSinche
 */
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    private final EmpleadoDAO empleadoDao = new EmpleadoDAOI();

    /**
     * Devuelve un Empleado si las credenciales son correctas, o null si
     * DNI/pass no coinciden.
     */
    public Empleado intentarLogin(int dni, String pass) throws SQLException {
        log.debug("Intentando login para DNI={}", dni);
        Empleado emp = empleadoDao.findByDniAndPass(dni, pass);
        if (emp != null) {
            log.info("Login OK para DNI={}, rol={}", dni, emp.getRol());
        } else {
            log.warn("Login FALLIDO para DNI={}", dni);
        }
        return emp;
    }
}
