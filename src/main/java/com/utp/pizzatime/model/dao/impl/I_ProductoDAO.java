package com.utp.pizzatime.model.dao.impl;

import com.utp.pizzatime.model.dao.ProductoDAO;
import com.utp.pizzatime.model.entity.Producto_modificar;
import com.utp.pizzatime.util.SQLConexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class I_ProductoDAO implements ProductoDAO{
    
    private static final String SQL_FIND_ALL =
        "SELECT ID_PRO, NOMBRE_PRO, PRECIO, MEDIDA, STOCK_ACTUAL, STOCK_CAJAS, "
      + "STOCK_MIN, STOCK_MAX, ID_PROV, DESCRIPCION "
      + "FROM PRODUCTO";

    private static final String SQL_FIND_BY_ID =
        SQL_FIND_ALL + " WHERE ID_PRO = ?";

    private static final String SQL_INSERT =
        "INSERT INTO PRODUCTO "
      + "(ID_PRO, NOMBRE_PRO, PRECIO, MEDIDA, STOCK_ACTUAL, STOCK_CAJAS, "
      + " STOCK_MIN, STOCK_MAX, ID_PROV, DESCRIPCION) "
      + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE =
        "UPDATE PRODUCTO SET "
      + "NOMBRE_PRO = ?, PRECIO = ?, MEDIDA = ?, STOCK_ACTUAL = ?, STOCK_CAJAS = ?, "
      + "STOCK_MIN = ?, STOCK_MAX = ?, ID_PROV = ?, DESCRIPCION = ? "
      + "WHERE ID_PRO = ?";

    private static final String SQL_DELETE =
        "DELETE FROM PRODUCTO WHERE ID_PRO = ?";

    private final SQLConexion sqlCon = new SQLConexion();

    @Override
    public List<Producto_modificar> listarTodos() throws SQLException {
        List<Producto_modificar> lista = new ArrayList<>();
        try (Connection conn = sqlCon.establecerConexion(); PreparedStatement st = conn.prepareStatement(SQL_FIND_ALL); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                lista.add(mapRow(rs));
            }
        }
        return lista;
    }

    @Override
    public Producto_modificar listarPorId(String id) throws SQLException {
        try (Connection conn = sqlCon.establecerConexion(); PreparedStatement st = conn.prepareStatement(SQL_FIND_BY_ID)) {

            st.setString(1, id);
            try (ResultSet rs = st.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        }
    }

    @Override
    public void insertar(Producto_modificar p) throws SQLException {
        try (Connection conn = sqlCon.establecerConexion();
             PreparedStatement st = conn.prepareStatement(SQL_INSERT)) {

            st.setString(1, p.getID_PRO());
            st.setString(2, p.getNOMBRE_PRO());
            st.setDouble(3, p.getPRECIO());
            st.setInt(4, p.getMEDIDA());
            st.setInt(5, p.getSTOCK_ACTUAL());
            st.setInt(6, p.getSTOCK_CAJAS());
            st.setInt(7, p.getSTOCK_MIN());
            st.setInt(8, p.getSTOCK_MAX());
            st.setString(9, p.getID_PROV());
            st.setString(10, p.getDESCRIPCION());

            st.executeUpdate();
        }
    }

    @Override
    public void actualizar(Producto_modificar p) throws SQLException {
        try (Connection conn = sqlCon.establecerConexion(); PreparedStatement st = conn.prepareStatement(SQL_UPDATE)) {

            st.setString(1, p.getNOMBRE_PRO());
            st.setDouble(2, p.getPRECIO());
            st.setInt(3, p.getMEDIDA());
            st.setInt(4, p.getSTOCK_ACTUAL());
            st.setInt(5, p.getSTOCK_CAJAS());
            st.setInt(6, p.getSTOCK_MIN());
            st.setInt(7, p.getSTOCK_MAX());
            st.setString(8, p.getID_PROV());
            st.setString(9, p.getDESCRIPCION());
            st.setString(10, p.getID_PRO());

            st.executeUpdate();
        }
    }

    @Override
    public void eliminar(String id) throws SQLException {
        try (Connection conn = sqlCon.establecerConexion(); PreparedStatement st = conn.prepareStatement(SQL_DELETE)) {

            st.setString(1, id);
            st.executeUpdate();
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
   
}
