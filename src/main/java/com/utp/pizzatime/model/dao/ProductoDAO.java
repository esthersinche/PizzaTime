package com.utp.pizzatime.model.dao;
import com.utp.pizzatime.model.entity.Producto_modificar;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author EstherSinche
 */
public interface ProductoDAO {
    List<Producto_modificar> listarTodos() throws SQLException;
    Producto_modificar listarPorId(String id) throws SQLException;
    void insertar(Producto_modificar p) throws SQLException;
    void actualizar(Producto_modificar p) throws SQLException;
    void eliminar(String id) throws SQLException; 
    String obtenerIdProductoPorNombre(String nombreProducto) throws SQLException;

}
