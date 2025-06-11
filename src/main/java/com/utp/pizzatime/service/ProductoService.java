package com.utp.pizzatime.service;

import com.utp.pizzatime.model.dao.ProductoDAO;
import com.utp.pizzatime.model.dao.impl.I_ProductoDAO;
import com.utp.pizzatime.model.entity.Producto_modificar;
import com.utp.pizzatime.util.SQLConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductoService {

    private static final Logger log = LoggerFactory.getLogger(ProductoService.class);
    private final ProductoDAO dao = new I_ProductoDAO();
    private final SQLConexion sqlCon = new SQLConexion();

    public List<Producto_modificar> listarTodos() throws SQLException {
        return dao.listarTodos();
    }

    public Producto_modificar buscarPorId(String id) throws SQLException {
        return dao.listarPorId(id);
    }

    public void crearProducto(Producto_modificar p) throws SQLException {
        dao.insertar(p);
    }

    public void actualizarProducto(Producto_modificar p) throws SQLException {
        dao.actualizar(p);
    }

    public void eliminarProducto(String id) throws SQLException {
        dao.eliminar(id);
    }

    /**
     * Incrementa STOCK_CAJAS en 'cajas' y recalcula STOCK_ACTUAL = STOCK_CAJAS
     * * MEDIDA
     */
    public void incrementarStock(String idPro, int cajas) throws SQLException {
        String sql
                = "UPDATE PRODUCTO SET "
                + "STOCK_CAJAS = STOCK_CAJAS + ?, "
                + "STOCK_ACTUAL = (STOCK_CAJAS + ?) * MEDIDA "
                + "WHERE ID_PRO = ?";

        try (Connection conn = sqlCon.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cajas);
            ps.setInt(2, cajas);
            ps.setString(3, idPro);
            int filas = ps.executeUpdate();
            log.info("incrementarStock: idPro={}, cajas={}, filasAfectadas={}", idPro, cajas, filas);

        } catch (SQLException ex) {
            log.error("Error en incrementarStock para producto {}", idPro, ex);
            throw ex;
        }
    }

    /**
     * Decrementa STOCK_CAJAS en 'cajas' y recalcula STOCK_ACTUAL = STOCK_CAJAS
     * * MEDIDA
     */
    public void decrementarStock(String idPro, int cajas) throws SQLException {
        String sql
                = "UPDATE PRODUCTO SET "
                + "STOCK_CAJAS = STOCK_CAJAS - ?, "
                + "STOCK_ACTUAL = (STOCK_CAJAS - ?) * MEDIDA "
                + "WHERE ID_PRO = ?";

        try (Connection conn = sqlCon.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cajas);
            ps.setInt(2, cajas);
            ps.setString(3, idPro);
            int filas = ps.executeUpdate();
            log.info("decrementarStock: idPro={}, cajas={}, filasAfectadas={}", idPro, cajas, filas);

        } catch (SQLException ex) {
            log.error("Error en decrementarStock para producto {}", idPro, ex);
            throw ex;
        }
    }

    //TODO Mejorar decrementación por merma!! tengo sueño xd NO USAR AUN, PROBAR CON EL DE ARRIBA
    public void decrementarStockMerma(String idPro, int unit) throws SQLException {
        String sql
                = "UPDATE PRODUCTO SET "
                + "STOCK_CAJAS = STOCK_CAJAS - (STOCK_ACTUAL - ?) , "
                + "STOCK_ACTUAL = (STOCK_ACTUAL - ?)"
                + "WHERE ID_PRO = ?";

        try (Connection conn = sqlCon.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, unit);
            ps.setInt(2, unit);
            ps.setString(3, idPro);
            int filas = ps.executeUpdate();
            log.info("decrementarStock: idPro={}, cajas={}, filasAfectadas={}", idPro, unit, filas);

        } catch (SQLException ex) {
            log.error("Error en decrementarStock para producto {}", idPro, ex);
            throw ex;
        }
    }
}
