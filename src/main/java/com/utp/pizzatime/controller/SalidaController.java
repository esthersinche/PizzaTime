
package com.utp.pizzatime.controller;

/**
 *
 * @author Charisse
 */

import com.utp.pizzatime.model.dao.impl.I_DisponibleDAO;
import com.utp.pizzatime.model.dao.impl.I_ProductoDAO;
import com.utp.pizzatime.model.dto.DisponibleProductoDTO;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class SalidaController {
    
    private final I_ProductoDAO productoDAO;
    private final I_DisponibleDAO disponibleDAO;

    public SalidaController() {
        this.productoDAO = new I_ProductoDAO();
        this.disponibleDAO = new I_DisponibleDAO();
    }

    //ComboBox Ingredientes
    public List<String> obtenerNombresProductos() {
        return productoDAO.listarNombresProducto();
    }

    //Tabla DISPONIBLE
    public List<DisponibleProductoDTO> obtenerDisponiblesParaSalida() {
        return disponibleDAO.listarDisponiblesConProducto();

    }

    //ComboBox Tipo
    public List<String> obtenerTiposMovimiento() {
        return Arrays.asList("Cocina", "Merma");
    }

    //ComboBox Motivo
    public List<String> obtenerMotivosPorTipo(String tipo) {
        if ("Cocina".equalsIgnoreCase(tipo)) {
            return Collections.singletonList("Preparación de platos");
        } else if ("Merma".equalsIgnoreCase(tipo)) {
            return Arrays.asList("Producto vencido", "Mercancía dañada", "Mala rotación del producto");
        }
        return Collections.emptyList();
    }

    public String obtenerIdDisponiblePorProductoYLote(String nombreProducto, String lote) {
    return disponibleDAO.obtenerIdDisponiblePorIngredienteYLote(nombreProducto, lote);
    }


    
}
