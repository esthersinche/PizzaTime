package com.utp.pizzatime.service;

import com.utp.pizzatime.model.dao.DisponibleDAO;
import com.utp.pizzatime.model.dao.MovimientoCocinaDAO;
import com.utp.pizzatime.model.dao.PrioridadDAO;
import com.utp.pizzatime.model.dao.impl.I_DisponibleDAO;
import com.utp.pizzatime.model.dao.impl.I_MovimientoCocinaDAO;
import com.utp.pizzatime.model.dao.impl.I_PrioridadDAO;
import com.utp.pizzatime.model.entity.Disponible;
import com.utp.pizzatime.model.entity.MovimientoCocina;
import com.utp.pizzatime.model.entity.Prioridad;
import java.sql.SQLException;

/**
 *
 * @author BeeIsMega
 */
public class InventarioService {
    private final DisponibleDAO dispoDao = new I_DisponibleDAO();
    private final MovimientoCocinaDAO movDao = new I_MovimientoCocinaDAO();
    private final PrioridadDAO prioDao = new I_PrioridadDAO();

    /**
     * 1. Registrar llegada de stock
     */
    public void registrarDisponible(Disponible d) throws SQLException {
        dispoDao.insertarDisp(d);
    }

    /**
     * 2. Registrar movimiento por cocina
     */
    public void registrarMovimiento(MovimientoCocina m) throws SQLException {
        movDao.registrarMovimientoCocina(m);
    }

    /**
     * 3. Registrar merma
     */
    public void registrarMerma(MovimientoCocina m) throws SQLException {
        movDao.registrarMovimientoMerma(m);
    }

    /**
     * 4. Registrar devoluciones en prioridad
     */
    public void registrarDevolucion(Prioridad p) throws SQLException {
        prioDao.insertPrioridad(p);
    }
}
