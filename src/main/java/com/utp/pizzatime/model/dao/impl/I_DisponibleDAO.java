package com.utp.pizzatime.model.dao.impl;

import com.utp.pizzatime.model.dao.DisponibleDAO;
import com.utp.pizzatime.model.entity.Disponible;
import com.utp.pizzatime.util.SQLConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BeeIsMega
 */
public class I_DisponibleDAO implements DisponibleDAO {
    private final SQLConexion sqlCon = new SQLConexion();

    private static final String SQL_INSERT
            = "INSERT INTO DISPONIBLE (ID_DIS, ID_PRO, CANTIDAD_CAJAS, FECHA_DIS, VENCIMIENTO, LOTE) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL_FIND_ALL
            = "SELECT * FROM DISPONIBLE";

    @Override
    public void insertarDisp(Disponible d) throws SQLException {
        try (Connection c = sqlCon.establecerConexion();
             PreparedStatement ps = c.prepareStatement(SQL_INSERT)) {
            ps.setString(1, d.getIdDis());
            ps.setString(2, d.getIdPro());
            ps.setInt   (3, d.getCantidadCajas());
            ps.setDate  (4, new java.sql.Date(d.getFechaDis().getTime()));
            ps.setDate  (5, new java.sql.Date(d.getVencimiento().getTime()));
            ps.setString(6, d.getLote());
            ps.executeUpdate();
        }
    }

    @Override
    public List<Disponible> listarTodosDisp() throws SQLException {
        List<Disponible> lista = new ArrayList<>();
        try (Connection c = sqlCon.establecerConexion();
             PreparedStatement ps = c.prepareStatement(SQL_FIND_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Disponible d = new Disponible();
                d.setIdDis(rs.getString("ID_DIS"));
                d.setIdPro(rs.getString("ID_PRO"));
                d.setCantidadCajas(rs.getInt("CANTIDAD_CAJAS"));
                d.setFechaDis(rs.getDate("FECHA_DIS"));
                d.setVencimiento(rs.getDate("VENCIMIENTO"));
                d.setLote(rs.getString("LOTE"));
                lista.add(d);
            }
        }
        return lista;
    }
    
}
