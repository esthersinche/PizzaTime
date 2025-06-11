package com.utp.pizzatime.service;

import com.utp.pizzatime.model.dao.ProveedorDAO;
import com.utp.pizzatime.model.dao.impl.I_ProveedorDAO;
import com.utp.pizzatime.model.entity.Proveedor;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author BeeIsMega
 */
public class ProveedorService {
    private final ProveedorDAO provDao = new I_ProveedorDAO();

    /**
     * Obtener lista de todos los proveedores
     */
    public List<Proveedor> obtenerTodos() throws SQLException {
        return provDao.listarTodos();
    }

    /**
     * Obtener un proveedor por su ID
     */
    public Proveedor obtenerPorId(String id) throws SQLException {
        return provDao.listarPorId(id);
    }

    /**
     * Crear un nuevo proveedor
     */
    public void crearProveedor(Proveedor proveedor) throws SQLException {
        provDao.insertar(proveedor);
    }

    /**
     * Actualizar un proveedor existente
     */
    public void actualizarProveedor(Proveedor proveedor) throws SQLException {
        provDao.actualizar(proveedor);
    }

    /**
     * Eliminar un proveedor por ID
     */
    public void eliminarProveedor(String id) throws SQLException {
        provDao.eliminar(id);
    }

    /**
     * Buscar ID de proveedor por nombre
     */
    public String buscarIdPorNombre(String nombre) throws SQLException {
        return provDao.obtenerIdProveedorPorNombre(nombre);
    }
}