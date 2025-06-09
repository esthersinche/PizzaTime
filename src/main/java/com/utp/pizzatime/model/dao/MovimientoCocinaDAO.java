package com.utp.pizzatime.model.dao;

import com.utp.pizzatime.model.entity.MovimientoCocina;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author BeeIsMega
 */
public interface MovimientoCocinaDAO {
    void registrarMovimientoCocina(MovimientoCocina m) throws SQLException;
    void registrarMovimientoMerma(MovimientoCocina m) throws SQLException;
    
    //PARA REPORTES:
    List<MovimientoCocina> encontrarPorRangoFecha(Date from, Date to) throws SQLException;
    
    String findLastIdMovByLote(String lote) throws SQLException;
}
