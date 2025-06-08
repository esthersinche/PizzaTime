package com.utp.pizzatime.model.dao;

import com.utp.pizzatime.model.entity.Pedido;
import java.sql.SQLException;

/**
 *
 * @author BeeIsMega
 */
public interface PedidoDAO {
    void insertPedidoConDetalles(Pedido pedido) throws SQLException;
    
}
