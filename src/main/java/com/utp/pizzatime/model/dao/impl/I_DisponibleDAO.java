package com.utp.pizzatime.model.dao.impl;

import com.utp.pizzatime.model.dao.DisponibleDAO;
import com.utp.pizzatime.model.dto.DisponibleProductoDTO;
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
    
        public Disponible obtenerDisponibleFIFO(String nombreProducto, int cantidadMinima) {
        Disponible disp = null;
        String sql = "SELECT TOP 1 d.ID_DIS, d.LOTE, d.CANTIDAD_CAJAS " +
                     "FROM DISPONIBLE d JOIN PRODUCTO p ON d.ID_PRO = p.ID_PRO " +
                     "WHERE p.NOMBRE_PRO = ? AND d.CANTIDAD_CAJAS >= ? " +
                     "ORDER BY d.VENCIMIENTO ASC";

        try (Connection c = sqlCon.establecerConexion();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, nombreProducto);
            ps.setInt(2, cantidadMinima);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                disp = new Disponible();
                disp.setIdDis(rs.getString("ID_DIS"));
                disp.setLote(rs.getString("LOTE"));
                disp.setCantidadCajas(rs.getInt("CANTIDAD_CAJAS"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return disp;
    }
      // para agarrar id_dis por nombre yt lote 
        public String obtenerIdDisponiblePorIngredienteYLote(String nombreProducto, String lote) {
        String idDis = null;
        String sql = "SELECT d.ID_DIS FROM DISPONIBLE d JOIN PRODUCTO p ON d.ID_PRO = p.ID_PRO " +
                     "WHERE p.NOMBRE_PRO = ? AND d.LOTE = ?";

        try (Connection c = sqlCon.establecerConexion();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, nombreProducto);
            ps.setString(2, lote);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                idDis = rs.getString("ID_DIS");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idDis;
    }
          
      public String generarNuevoIdDisponible(Connection conn) throws SQLException {
    String sql = "SELECT MAX(ID_DIS) AS max_id FROM DISPONIBLE WHERE ID_DIS LIKE 'DIS%'";
    try (PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        if (rs.next() && rs.getString("max_id") != null) {
            String ultimo = rs.getString("max_id"); // e.g. DIS00045
            int num = Integer.parseInt(ultimo.substring(3));
            return String.format("DIS%05d", num + 1);
        }
    }
    return "DIS00001"; // primera vez
}  

    public List<DisponibleProductoDTO> listarDisponiblesConProducto() {
    List<DisponibleProductoDTO> lista = new ArrayList<>();
    String sql = "SELECT p.NOMBRE_PRO, d.CANTIDAD_CAJAS, d.FECHA_DIS, d.VENCIMIENTO "
               + "FROM DISPONIBLE d "
               + "JOIN PRODUCTO p ON d.ID_PRO = p.ID_PRO "
               + "WHERE d.CANTIDAD_CAJAS > 0";

    try (Connection con = new SQLConexion().establecerConexion();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            DisponibleProductoDTO dto = new DisponibleProductoDTO();
            dto.setNombreProducto(rs.getString("NOMBRE_PRO"));
            dto.setCantidad(rs.getInt("CANTIDAD_CAJAS"));
            dto.setFechaDis(rs.getDate("FECHA_DIS"));
            dto.setVencimiento(rs.getDate("VENCIMIENTO"));
            lista.add(dto);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return lista;
    }
        
        
}
