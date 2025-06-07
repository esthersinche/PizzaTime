package com.utp.pizzatime.controller;

import com.utp.pizzatime.model.dao.EmpleadoDAO;
import com.utp.pizzatime.model.entity.Empleado;
import com.utp.pizzatime.model.dao.impl.EmpleadoDAOI;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author BeeIsMega
 */
public class LoginController {

    private final EmpleadoDAO empleadoDao = new EmpleadoDAOI();

    public void intentarLogin(int dniIngresado, String passIngresada) {
        try {
            Empleado emp = empleadoDao.findByDniAndPass(dniIngresado, passIngresada);
            if (emp != null) {
                JOptionPane.showMessageDialog(null,
                        "¡Bienvenido, " + emp.getNombre_emp() + "!");
                // abrir ventana principal…
            } else {
                JOptionPane.showMessageDialog(null,
                        "DNI o contraseña incorrectos",
                        "Error de autenticación",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "No se pudo conectar a la base de datos:\n" + e.getMessage(),
                    "Error de conexión",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
