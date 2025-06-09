package com.utp.pizzatime.model.dao;

import com.utp.pizzatime.model.entity.Prioridad;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author BeeIsMega
 */
public interface PrioridadDAO {
    void insertPrioridad(Prioridad p) throws SQLException;
    List<Prioridad> findAllPrioridades() throws SQLException;
    
}
