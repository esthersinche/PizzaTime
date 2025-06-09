package com.utp.pizzatime.model.dao.impl;

import com.utp.pizzatime.model.dao.PrioridadDAO;
import com.utp.pizzatime.model.entity.Prioridad;
import com.utp.pizzatime.util.SQLConexion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BeeIsMega
 */
public class I_PrioridadDAO implements PrioridadDAO {

    private static final Logger log = LoggerFactory.getLogger(I_PrioridadDAO.class);
    private final SQLConexion sqlCon = new SQLConexion();

    private static final String SQL_INSERT
            = "INSERT INTO PRIORIDAD (ID_PRIO, ID_MOV, CANTIDAD_UNIT, FECHA_PRIO, VENCIMIENTO, LOTE) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL_SELECT_ALL
            = "SELECT * FROM PRIORIDAD";

    /**
     * Genera un nuevo ID con prefijo PRI y cinco dígitos secuenciales.
     */
    private String generarNuevoID(Connection conn) throws SQLException {
        String sql = "SELECT MAX(ID_PRIO) AS max_id FROM PRIORIDAD WHERE ID_PRIO LIKE 'PRI%'";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            if (rs.next() && rs.getString("max_id") != null) {
                String ultimo = rs.getString("max_id");
                int num = Integer.parseInt(ultimo.substring(3));
                return String.format("PRI%05d", num + 1);
            }
        }
        return "PRI00001";
    }

    @Override
    public void insertPrioridad(Prioridad p) throws SQLException {
        log.debug("insertPrioridad: idMov={}, unidades={}, lote={}", p.getIdMov(), p.getCantidadUnit(), p.getLote());
        try (Connection conn = sqlCon.establecerConexion(); PreparedStatement ps = conn.prepareStatement(SQL_INSERT)) {

            String id = generarNuevoID(conn);
            ps.setString(1, id);
            ps.setString(2, p.getIdMov());
            ps.setInt(3, p.getCantidadUnit());
            ps.setDate(4, new java.sql.Date(p.getFechaPrio().getTime()));
            ps.setDate(5, new java.sql.Date(p.getVencimiento().getTime()));
            ps.setString(6, p.getLote());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                log.info("Prioridad registrada: idPrio={}, idMov={}, unidades={}",
                        id, p.getIdMov(), p.getCantidadUnit());
            }
        } catch (SQLException ex) {
            log.error("Error al insertar prioridad para idMov={}", p.getIdMov(), ex);
            throw ex;
        }
    }

    @Override
    public List<Prioridad> findAllPrioridades() throws SQLException {
        log.debug("findAllPrioridades invoked");
        List<Prioridad> lista = new ArrayList<>();
        try (Connection conn = sqlCon.establecerConexion(); PreparedStatement ps = conn.prepareStatement(SQL_SELECT_ALL); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Prioridad pr = new Prioridad();
                pr.setIdPrio(rs.getString("ID_PRIO"));
                pr.setIdMov(rs.getString("ID_MOV"));
                pr.setCantidadUnit(rs.getInt("CANTIDAD_UNIT"));
                pr.setFechaPrio(rs.getDate("FECHA_PRIO"));
                pr.setVencimiento(rs.getDate("VENCIMIENTO"));
                pr.setLote(rs.getString("LOTE"));
                lista.add(pr);
            }
            log.info("findAllPrioridades found {} records", lista.size());
        } catch (SQLException ex) {
            log.error("Error al consultar prioridades", ex);
            throw ex;
        }
        return lista;
    }
}

