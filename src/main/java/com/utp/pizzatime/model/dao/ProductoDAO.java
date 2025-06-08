/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.pizzatime.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public void agregarProducto(Producto p) {
        String sql = "INSERT INTO producto (ID_PRO, NOMBRE_PRO, MEDIDA, STOCK_ACTUAL, STOCK_CAJAS, STOCK_MIN, STOCK_MAX, ID_PROV, PRECIO, DESCRIPCION) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, p.getIdPro());
            stmt.setString(2, p.getNombrePro());
            stmt.setString(3, p.getMedida());
            stmt.setInt(4, p.getStockActual());
            stmt.setInt(5, p.getStockCajas());
            stmt.setInt(6, p.getStockMin());
            stmt.setInt(7, p.getStockMax());
            stmt.setInt(8, p.getIdProv());
            stmt.setDouble(9, p.getPrecio());
            stmt.setString(10, p.getDescripcion());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Producto> listarProductos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM producto";
        try (Connection conn = Conexion.getConexion(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Producto p = new Producto(
                    rs.getInt("ID_PRO"),
                    rs.getString("NOMBRE_PRO"),
                    rs.getString("MEDIDA"),
                    rs.getInt("STOCK_ACTUAL"),
                    rs.getInt("STOCK_CAJAS"),
                    rs.getInt("STOCK_MIN"),
                    rs.getInt("STOCK_MAX"),
                    rs.getInt("ID_PROV"),
                    rs.getDouble("PRECIO"),
                    rs.getString("DESCRIPCION")
                );
                lista.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public void actualizarProducto(Producto p) {
        String sql = "UPDATE producto SET NOMBRE_PRO=?, MEDIDA=?, STOCK_ACTUAL=?, STOCK_CAJAS=?, STOCK_MIN=?, STOCK_MAX=?, ID_PROV=?, PRECIO=?, DESCRIPCION=? WHERE ID_PRO=?";
        try (Connection conn = Conexion.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNombrePro());
            stmt.setString(2, p.getMedida());
            stmt.setInt(3, p.getStockActual());
            stmt.setInt(4, p.getStockCajas());
            stmt.setInt(5, p.getStockMin());
            stmt.setInt(6, p.getStockMax());
            stmt.setInt(7, p.getIdProv());
            stmt.setDouble(8, p.getPrecio());
            stmt.setString(9, p.getDescripcion());
            stmt.setInt(10, p.getIdPro());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void eliminarProducto(int idPro) {
        String sql = "DELETE FROM producto WHERE ID_PRO=?";
        try (Connection conn = Conexion.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPro);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
