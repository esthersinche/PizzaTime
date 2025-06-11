/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.pizzatime.model.entity;

/**
 *
 * @author Dell
 */
public class Proveedor {

    private String ID_PROV;
    private String NOMBRE_PROV;
    private int TELEFONO;
    private String DIRECCION;

    public Proveedor(String ID_PROV, String NOMBRE_PROV, int TELEFONO, String DIRECCION) {
        this.ID_PROV = ID_PROV;
        this.NOMBRE_PROV = NOMBRE_PROV;
        this.TELEFONO = TELEFONO;
        this.DIRECCION = DIRECCION;
    }

    public String getID_PROV() {
        return ID_PROV;
    }

    public void setID_PROV(String ID_PROV) {
        this.ID_PROV = ID_PROV;
    }

    public String getNOMBRE_PROV() {
        return NOMBRE_PROV;
    }

    public void setNOMBRE_PROV(String NOMBRE_PROV) {
        this.NOMBRE_PROV = NOMBRE_PROV;
    }

    public int getTELEFONO() {
        return TELEFONO;
    }

    public void setTELEFONO(int TELEFONO) {
        this.TELEFONO = TELEFONO;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }

}
