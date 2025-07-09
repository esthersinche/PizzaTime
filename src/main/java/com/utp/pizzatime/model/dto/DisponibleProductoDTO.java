/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.pizzatime.model.dto;

import java.util.Date;

/**
 *
 * @author Charisse
 */
public class DisponibleProductoDTO {
    
        private String nombreProducto;
    private int cantidad;
    private Date fechaDis;
    private Date vencimiento;

    // Getters y setters
    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public Date getFechaDis() { return fechaDis; }
    public void setFechaDis(Date fechaDis) { this.fechaDis = fechaDis; }

    public Date getVencimiento() { return vencimiento; }
    public void setVencimiento(Date vencimiento) { this.vencimiento = vencimiento; }
    
}
