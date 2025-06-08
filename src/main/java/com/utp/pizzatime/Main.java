/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.pizzatime;

/**
 *
 * @author Dell
 */
public class Main {
    public static void main(String[] args) {
        ProductoDAO dao = new ProductoDAO();

        // Crear un producto
        Producto p = new Producto(1, "Leche Gloria", "Litros", 100, 20, 50, 200, 1, 3.50, "Leche evaporada entera");
        dao.agregarProducto(p);

        // Listar productos
        for (Producto prod : dao.listarProductos()) {
            System.out.println(prod.getNombrePro() + " - " + prod.getPrecio());
        }

        // Actualizar
        p.setPrecio(3.80);
        dao.actualizarProducto(p);

        // Eliminar
        dao.eliminarProducto(1);
    }
}
