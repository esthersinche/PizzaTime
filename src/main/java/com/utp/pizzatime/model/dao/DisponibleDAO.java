package com.utp.pizzatime.model.dao;

import com.utp.pizzatime.model.entity.Disponible;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author BeeIsMega
 */
public interface DisponibleDAO {
    void insertarDisp(Disponible d) throws SQLException;
    List<Disponible> listarTodosDisp() throws SQLException;
}
