package com.utp.pizzatime.model.dao;

import com.utp.pizzatime.model.entity.Empleado;
import java.sql.SQLException;

/**
 *
 * @author EstherSinche
 */
public interface LoginDAO {
    Empleado findByDniAndPass(int dni, String pass) throws SQLException;
}
