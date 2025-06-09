
package com.utp.pizzatime.model.entity;

/**
 *
 * @author Charisse
 */
public class MovimientoCocina {

    private String id_dis;
    private int dni_emp;
    private int cantidad_unit;
    private int cantidad_cajas;
    private String lote;

    // Getters y Setters

    public String getId_dis() {
        return id_dis;
    }

    public void setId_dis(String id_dis) {
        this.id_dis = id_dis;
    }

    public int getDni_emp() {
        return dni_emp;
    }

    public void setDni_emp(int dni_emp) {
        this.dni_emp = dni_emp;
    }

    public int getCantidad_unit() {
        return cantidad_unit;
    }

    public void setCantidad_unit(int cantidad_unit) {
        this.cantidad_unit = cantidad_unit;
    }

    public int getCantidad_cajas() {
        return cantidad_cajas;
    }

    public void setCantidad_cajas(int cantidad_cajas) {
        this.cantidad_cajas = cantidad_cajas;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }
}
