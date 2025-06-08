/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.pizzatime.model.entity;

/**
 *
 * @author Dell
 */

public class Producto {
    private int idPro;
    private String nombrePro;
    private String medida;
    private int stockActual;
    private int stockCajas;
    private int stockMin;
    private int stockMax;
    private int idProv;
    private double precio;
    private String descripcion;

    // Constructor vacío
    public Producto() {}

    // Constructor con parámetros
    public Producto(int idPro, String nombrePro, String medida, int stockActual,
                    int stockCajas, int stockMin, int stockMax, int idProv,
                    double precio, String descripcion) {
        this.idPro = idPro;
        this.nombrePro = nombrePro;
        this.medida = medida;
        this.stockActual = stockActual;
        this.stockCajas = stockCajas;
        this.stockMin = stockMin;
        this.stockMax = stockMax;
        this.idProv = idProv;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public int getIdPro() { return idPro; }
    public void setIdPro(int idPro) { this.idPro = idPro; }

    public String getNombrePro() { return nombrePro; }
    public void setNombrePro(String nombrePro) { this.nombrePro = nombrePro; }

    public String getMedida() { return medida; }
    public void setMedida(String medida) { this.medida = medida; }

    public int getStockActual() { return stockActual; }
    public void setStockActual(int stockActual) { this.stockActual = stockActual; }

    public int getStockCajas() { return stockCajas; }
    public void setStockCajas(int stockCajas) { this.stockCajas = stockCajas; }

    public int getStockMin() { return stockMin; }
    public void setStockMin(int stockMin) { this.stockMin = stockMin; }

    public int getStockMax() { return stockMax; }
    public void setStockMax(int stockMax) { this.stockMax = stockMax; }

    public int getIdProv() { return idProv; }
    public void setIdProv(int idProv) { this.idProv = idProv; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
