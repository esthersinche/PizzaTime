/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.pizzatime.model.dao;

import java.sql.SQLException;
import java.util.List;
import com.utp.pizzatime.model.entity.Proveedor;

/**
 *
 * @author Dell
 */
public interface ProveedorDAO {

    List<Proveedor> listarTodos() throws SQLException;

    Proveedor listarPorId(String id) throws SQLException;

    void insertar(Proveedor proveedor) throws SQLException;

    void actualizar(Proveedor proveedor) throws SQLException;

    void eliminar(String id) throws SQLException;

    String obtenerIdProveedorPorNombre(String nombreProveedor) throws SQLException;
}
