package com.utp.pizzatime.model.dao;

import com.utp.pizzatime.model.dto.DisponibleProductoDTO;
import com.utp.pizzatime.model.entity.Disponible;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author BeeIsMega
 */
public interface DisponibleDAO {

    List<DisponibleProductoDTO> listarDisponiblesConProducto();
    void insertarDisp(Disponible d) throws SQLException;
    List<Disponible> listarTodosDisp() throws SQLException;
    
    Disponible obtenerDisponibleFIFO(String nombreProducto, int cantidadMinima) throws SQLException;
    String obtenerIdDisponiblePorIngredienteYLote(String nombreProducto, String lote) throws SQLException;

}
