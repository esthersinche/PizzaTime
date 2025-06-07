package com.utp.pizzatime.model.dao;

import com.utp.pizzatime.model.entity.Empleado;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 *
 * @author BeeIsMega
 */
public interface EmpleadoDAO {
    Empleado findByDniAndPass(int dni, String pass) throws SQLException;

}
