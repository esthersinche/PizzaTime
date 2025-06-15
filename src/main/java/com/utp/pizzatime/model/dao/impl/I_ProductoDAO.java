package com.utp.pizzatime.model.dao.impl;

import com.utp.pizzatime.model.dao.ProductoDAO;
import com.utp.pizzatime.model.entity.Producto_modificar;
import com.utp.pizzatime.util.SQLConexion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class I_ProductoDAO implements ProductoDAO {

    private static final Logger log = LoggerFactory.getLogger(I_ProductoDAO.class);
    private final SQLConexion sqlCon = new SQLConexion();

    private static final String SQL_FIND_ALL = "SELECT ID_PRO, NOMBRE_PRO, PRECIO, MEDIDA, STOCK_ACTUAL, STOCK_CAJAS, "
            + "STOCK_MIN, STOCK_MAX, ID_PROV, DESCRIPCION "
            + "FROM PRODUCTO";
    private static final String SQL_FIND_BY_ID = SQL_FIND_ALL + " WHERE ID_PRO = ?";
    ;

    // Al insertar, stock_actual y stock_cajas se inicializan en 0 o con el mismo valor
    private static final String SQL_INSERT
            = "INSERT INTO PRODUCTO "
            + "(ID_PRO, NOMBRE_PRO, PRECIO, MEDIDA, STOCK_ACTUAL, STOCK_CAJAS, STOCK_MIN, STOCK_MAX, ID_PROV, DESCRIPCION) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    // En la actualización, omitimos STOCK_ACTUAL y STOCK_CAJAS para no cambiarlos
    private static final String SQL_UPDATE
            = "UPDATE PRODUCTO SET "
            + "NOMBRE_PRO = ?, PRECIO = ?, MEDIDA = ?, STOCK_MIN = ?, STOCK_MAX = ?, ID_PROV = ?, DESCRIPCION = ? "
            + "WHERE ID_PRO = ?";

    private static final String SQL_DELETE = "DELETE FROM PRODUCTO WHERE ID_PRO = ?";

    @Override
    public List<Producto_modificar> listarTodos() throws SQLException {
        log.debug("listarTodos()");
        List<Producto_modificar> lista = new ArrayList<>();
        try (Connection conn = sqlCon.establecerConexion(); PreparedStatement st = conn.prepareStatement(SQL_FIND_ALL); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                lista.add(mapRow(rs));
            }
        }
        log.info("Encontrados {} productos", lista.size());
        return lista;
    }

    @Override
    public Producto_modificar listarPorId(String id) throws SQLException {
        log.debug("listarPorId({})", id);
        try (Connection conn = sqlCon.establecerConexion(); PreparedStatement st = conn.prepareStatement(SQL_FIND_BY_ID)) {

            st.setString(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    log.info("Producto {} encontrado", id);
                    return mapRow(rs);
                } else {
                    log.warn("Producto {} no encontrado", id);
                    return null;
                }
            }
        }
    }

    @Override
    public void insertar(Producto_modificar p) throws SQLException {
        log.debug("insertar({})", p.getID_PRO());
        try (Connection conn = sqlCon.establecerConexion(); PreparedStatement st = conn.prepareStatement(SQL_INSERT)) {

            st.setString(1, p.getID_PRO());
            st.setString(2, p.getNOMBRE_PRO());
            st.setDouble(3, p.getPRECIO());
            st.setInt(4, p.getMEDIDA());
            st.setInt(5, p.getSTOCK_ACTUAL());  // normalmente 0
            st.setInt(6, p.getSTOCK_CAJAS());   // normalmente 0
            st.setInt(7, p.getSTOCK_MIN());
            st.setInt(8, p.getSTOCK_MAX());
            st.setString(9, p.getID_PROV());
            st.setString(10, p.getDESCRIPCION());

            int filas = st.executeUpdate();
            log.info("insertar: filas afectadas = {}", filas);
        } catch (SQLException ex) {
            log.error("Error al insertar producto {}", p.getID_PRO(), ex);
            throw ex;
        }
    }

    @Override
    public void actualizar(Producto_modificar p) throws SQLException {
        log.debug("actualizar({})", p.getID_PRO());
        try (Connection conn = sqlCon.establecerConexion(); PreparedStatement st = conn.prepareStatement(SQL_UPDATE)) {

            st.setString(1, p.getNOMBRE_PRO());
            st.setDouble(2, p.getPRECIO());
            st.setInt(3, p.getMEDIDA());
            st.setInt(4, p.getSTOCK_MIN());
            st.setInt(5, p.getSTOCK_MAX());
            st.setString(6, p.getID_PROV());
            st.setString(7, p.getDESCRIPCION());
            st.setString(8, p.getID_PRO());

            int filas = st.executeUpdate();
            log.info("actualizar: filas afectadas = {}", filas);
        } catch (SQLException ex) {
            log.error("Error al actualizar producto {}", p.getID_PRO(), ex);
            throw ex;
        }
    }

    @Override
    public void eliminar(String id) throws SQLException {
        log.debug("eliminar({})", id);
        try (Connection conn = sqlCon.establecerConexion(); PreparedStatement st = conn.prepareStatement(SQL_DELETE)) {

            st.setString(1, id);
            int filas = st.executeUpdate();
            log.info("eliminar: filas afectadas = {}", filas);
        } catch (SQLException ex) {
            log.error("Error al eliminar producto {}", id, ex);
            throw ex;
        }
    }

    private Producto_modificar mapRow(ResultSet rs) throws SQLException {
        return new Producto_modificar(
                rs.getInt("MEDIDA"),
                rs.getInt("STOCK_ACTUAL"),
                rs.getInt("STOCK_CAJAS"),
                rs.getInt("STOCK_MIN"),
                rs.getInt("STOCK_MAX"),
                rs.getString("ID_PROV"),
                rs.getString("DESCRIPCION"),
                rs.getString("ID_PRO"),
                rs.getString("NOMBRE_PRO"),
                rs.getDouble("PRECIO")
        );
    }

    @Override
    public String obtenerIdProductoPorNombre(String nombreProducto) throws SQLException {
        String idPro = null;
        String sql = "SELECT ID_PRO FROM PRODUCTO WHERE NOMBRE_PRO = ?";

        try (Connection conn = sqlCon.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombreProducto);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                idPro = rs.getString("ID_PRO");
            }
        }

        return idPro;
    }
}
