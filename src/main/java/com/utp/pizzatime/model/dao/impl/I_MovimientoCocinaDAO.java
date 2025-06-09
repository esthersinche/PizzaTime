package com.utp.pizzatime.model.dao.impl;

import com.utp.pizzatime.model.dao.MovimientoCocinaDAO;
import com.utp.pizzatime.model.entity.MovimientoCocina;
import com.utp.pizzatime.util.SQLConexion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Charisse
 */
public class I_MovimientoCocinaDAO implements MovimientoCocinaDAO {

    private static final Logger log = LoggerFactory.getLogger(I_MovimientoCocinaDAO.class);
    private final SQLConexion sqlCon = new SQLConexion();

    private static final String SQL_INSERT
            = "INSERT INTO MOVIMIENTO_COCINA "
            + "(ID_MOV, ID_DIS, TIPO, DNI_EMP, CANTIDAD_UNIT, CANTIDAD_CAJAS, LOTE, FECHA_MOV, MOTIVO) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_SELECT_RANGE
            = "SELECT * FROM MOVIMIENTO_COCINA WHERE FECHA_MOV BETWEEN ? AND ?";

    /**
     * Genera un nuevo ID con prefijo MOV y cinco dígitos secuenciales.
     */
    private String generarNuevoID(Connection conn) throws SQLException {
        String sql = "SELECT MAX(ID_MOV) AS max_id FROM MOVIMIENTO_COCINA WHERE ID_MOV LIKE 'MOV%'";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            if (rs.next() && rs.getString("max_id") != null) {
                String ultimo = rs.getString("max_id");      // e.g. "MOV00042"
                int num = Integer.parseInt(ultimo.substring(3));
                return String.format("MOV%05d", num + 1);
            }
        }
        // Si no hay ninguno, arrancamos en MOV00001
        return "MOV00001";
    }
    
    /**
     * Genera un nuevo ID con prefijo MER y cinco dígitos secuenciales.
     */
    private String generarNuevoIDMerma(Connection conn) throws SQLException {
        String sql = "SELECT MAX(ID_MOV) AS max_id FROM MOVIMIENTO_COCINA WHERE ID_MOV LIKE 'MER%'";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            if (rs.next() && rs.getString("max_id") != null) {
                String ultimo = rs.getString("max_id");      // e.g. "MER00042"
                int num = Integer.parseInt(ultimo.substring(3));
                return String.format("MER%05d", num + 1);
            }
        }
        // Si no hay ninguno, arrancamos en MER00001
        return "MER00001";
    }


    @Override
    public void registrarMovimientoCocina(MovimientoCocina m) throws SQLException {
        log.debug("registrarMovimientoCocina: idDis={}, cajas={}", m.getId_dis(), m.getCantidad_cajas());
        try (Connection conn = sqlCon.establecerConexion(); PreparedStatement ps = conn.prepareStatement(SQL_INSERT)) {

            String id = generarNuevoID(conn);
            ps.setString(1, id);
            ps.setString(2, m.getId_dis());
            ps.setString(3, "Cocina");
            ps.setInt(4, m.getDni_emp());
            ps.setInt(5, 0);
            ps.setInt(6, m.getCantidad_cajas());
            ps.setString(7, m.getLote());
            ps.setDate(8, new java.sql.Date(System.currentTimeMillis()));
            ps.setString(9, "Preparación de platos");

            int filas = ps.executeUpdate();
            if (filas > 0) {
                log.info("Movimiento en CAJAS registrado: idMov={}, idDis={}, cajas={}",
                        id, m.getId_dis(), m.getCantidad_cajas());
            }
        } catch (SQLException ex) {
            log.error("Error al registrar movimiento en CAJAS para idDis={}", m.getId_dis(), ex);
            throw ex;
        }
    }

    //registrarMovimientoMerma() movimiento x unidades
    @Override
    public void registrarMovimientoMerma(MovimientoCocina m) throws SQLException {
        log.debug("registrarMovimientoMerma: idDis={}, unidades={}, motivo={}", m.getId_dis(), m.getCantidad_unit(), m.getMotivo());

        try (Connection conn = sqlCon.establecerConexion(); PreparedStatement ps = conn.prepareStatement(SQL_INSERT)) {

            String nuevoID = generarNuevoIDMerma(conn);
            ps.setString(1, nuevoID);
            ps.setString(2, m.getId_dis());
            ps.setString(3, "Merma");
            ps.setInt(4, m.getDni_emp());
            ps.setInt(5, m.getCantidad_unit());
            ps.setInt(6, 0);
            ps.setString(7, m.getLote());
            ps.setDate(8, new java.sql.Date(System.currentTimeMillis()));
            ps.setString(9, m.getMotivo()); // 

            int filas = ps.executeUpdate();
            if (filas > 0) {
                log.info("Movimiento de MERMA registrado: idMov={}, idDis={}, unidades={}, motivo={}", nuevoID, m.getId_dis(), m.getCantidad_unit(), m.getMotivo());
            }
        } catch (SQLException ex) {
            log.error("Error al registrar movimiento MERMA para idDis={}", m.getId_dis(), ex);
            throw ex;
        }
    }


    //TODO filtro para reportes
    @Override
    public List<MovimientoCocina> encontrarPorRangoFecha(java.util.Date from, java.util.Date to) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String findLastIdMovByLote(String lote) throws SQLException {
        String sql
                = "SELECT TOP 1 ID_MOV "
                + "FROM MOVIMIENTO_COCINA "
                + "WHERE LOTE = ? "
                + "ORDER BY FECHA_MOV DESC";
        try (Connection conn = sqlCon.establecerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, lote);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("ID_MOV");
                }
            }
        }
        return null;
    }
}
