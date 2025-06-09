
package com.utp.pizzatime.model.entity;

/**
 *
 * @author Charisse
 */
public class MovimientoCocina {

    private String idDis;
    private int dniEmp;
    private int cantidadUnit;
    private int cantidadCajas;
    private String lote;

    // Getters y Setters

    public String getId_dis() {
        return idDis;
    }

    public void setId_dis(String id_dis) {
        this.idDis = id_dis;
    }

    public int getDni_emp() {
        return dniEmp;
    }

    public void setDni_emp(int dni_emp) {
        this.dniEmp = dni_emp;
    }

    public int getCantidad_unit() {
        return cantidadUnit;
    }

    public void setCantidad_unit(int cantidad_unit) {
        this.cantidadUnit = cantidad_unit;
    }

    public int getCantidad_cajas() {
        return cantidadCajas;
    }

    public void setCantidad_cajas(int cantidad_cajas) {
        this.cantidadCajas = cantidad_cajas;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }
}
