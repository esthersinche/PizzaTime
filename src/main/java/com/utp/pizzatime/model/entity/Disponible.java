package com.utp.pizzatime.model.entity;

import java.util.Date;

/**
 *
 * @author BeeIsMega
 */
public class Disponible {
    private String idDis;
    private String idPro;
    private int cantidadCajas;
    private Date fechaDis;
    private Date vencimiento;
    private String lote;

    public String getIdDis() {
        return idDis;
    }

    public void setIdDis(String idDis) {
        this.idDis = idDis;
    }

    public String getIdPro() {
        return idPro;
    }

    public void setIdPro(String idPro) {
        this.idPro = idPro;
    }

    public int getCantidadCajas() {
        return cantidadCajas;
    }

    public void setCantidadCajas(int cantidadCajas) {
        this.cantidadCajas = cantidadCajas;
    }

    public Date getFechaDis() {
        return fechaDis;
    }

    public void setFechaDis(Date fechaDis) {
        this.fechaDis = fechaDis;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }
    
    
}
