package com.utp.pizzatime.model.dao.impl;

import com.utp.pizzatime.model.dao.ProveedorDAO;
import com.utp.pizzatime.model.entity.Proveedor;
import com.utp.pizzatime.util.SQLConexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BeeIsMega
 */
public class I_ProveedorDAO implements ProveedorDAO {

    private static final String SQL_FIND_ALL
            = "SELECT ID_PROV, NOMBRE_PROV, TELEFONO, DIRECCION FROM PROVEEDOR";
    private static final String SQL_FIND_BY_ID = SQL_FIND_ALL + " WHERE ID_PROV = ?";
    private static final String SQL_INSERT
            = "INSERT INTO PROVEEDOR (ID_PROV, NOMBRE_PROV, TELEFONO, DIRECCION) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE
            = "UPDATE PROVEEDOR SET NOMBRE_PROV = ?, TELEFONO = ?, DIRECCION = ? WHERE ID_PROV = ?";
    private static final String SQL_DELETE
            = "DELETE FROM PROVEEDOR WHERE ID_PROV = ?";

    private final SQLConexion sqlCon = new SQLConexion();

    @Override
    public List<Proveedor> listarTodos() throws SQLException {
        List<Proveedor> lista = new ArrayList<>();
        try (Connection conn = sqlCon.establecerConexion(); PreparedStatement st = conn.prepareStatement(SQL_FIND_ALL); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                lista.add(mapRow(rs));
            }
        }
        return lista;
    }

    @Override
    public Proveedor listarPorId(String id) throws SQLException {
        try (Connection conn = sqlCon.establecerConexion(); PreparedStatement st = conn.prepareStatement(SQL_FIND_BY_ID)) {

            st.setString(1, id);
            try (ResultSet rs = st.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        }
    }

    @Override
    public void insertar(Proveedor proveedor) throws SQLException {
        try (Connection conn = sqlCon.establecerConexion(); PreparedStatement st = conn.prepareStatement(SQL_INSERT)) {

            st.setString(1, proveedor.getID_PROV());
            st.setString(2, proveedor.getNOMBRE_PROV());
            st.setInt(3, proveedor.getTELEFONO());
            st.setString(4, proveedor.getDIRECCION());

            st.executeUpdate();
        }
    }

    @Override
    public void actualizar(Proveedor proveedor) throws SQLException {
        try (Connection conn = sqlCon.establecerConexion(); PreparedStatement st = conn.prepareStatement(SQL_UPDATE)) {

            st.setString(1, proveedor.getNOMBRE_PROV());
            st.setInt(2, proveedor.getTELEFONO());
            st.setString(3, proveedor.getDIRECCION());
            st.setString(4, proveedor.getID_PROV());

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

    @Override
    public String obtenerIdProveedorPorNombre(String nombreProveedor) throws SQLException {
        String idProv = null;
        String sql = "SELECT ID_PROV FROM PROVEEDOR WHERE NOMBRE_PROV = ?";

        try (Connection conn = sqlCon.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombreProveedor);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    idProv = rs.getString("ID_PROV");
                }
            }
        }
        return idProv;
    }

    private Proveedor mapRow(ResultSet rs) throws SQLException {
        return new Proveedor(
                rs.getString("ID_PROV"),
                rs.getString("NOMBRE_PROV"),
                rs.getInt("TELEFONO"),
                rs.getString("DIRECCION")
        );
    }

}
