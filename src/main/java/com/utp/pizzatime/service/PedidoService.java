package com.utp.pizzatime.service;

import com.utp.pizzatime.model.dao.PedidoDAO;
import com.utp.pizzatime.model.dao.impl.I_PedidoDAO;
import com.utp.pizzatime.model.entity.Pedido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

/**
 *
 * @author BeeIsMega
 */
public class PedidoService {

    private static final Logger log = LoggerFactory.getLogger(PedidoService.class);
    private final PedidoDAO pedidoDao = new I_PedidoDAO();

    /**
     * Inserta un pedido con todos sus detalles en una sola operación.
     */
    public void crearPedido(Pedido pedido) throws SQLException {
        log.debug("PedidoService.crearPedido: preparando inserción de pedido con {} detalles",
                pedido.getDetalles() != null ? pedido.getDetalles().size() : 0);
        try {
            pedidoDao.insertPedidoConDetalles(pedido);
            log.info("PedidoService: pedido {} creado correctamente", pedido.getIdPed());
        } catch (SQLException ex) {
            log.error("PedidoService: error al crear pedido {}", pedido.getIdPed(), ex);
            throw ex;
        }
    }
    
    
}
