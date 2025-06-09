package com.utp.pizzatime.model.entity;

import java.util.Date;

/**
 *
 * @author BeeIsMega
 */
public class Prioridad {
    private String idPrio;
    private String idMov;
    private int cantidadUnit;
    private Date fechaPrio;
    private Date vencimiento;
    private String lote;

    public String getIdPrio() {
        return idPrio;
    }

    public void setIdPrio(String idPrio) {
        this.idPrio = idPrio;
    }

    public String getIdMov() {
        return idMov;
    }

    public void setIdMov(String idMov) {
        this.idMov = idMov;
    }

    public int getCantidadUnit() {
        return cantidadUnit;
    }

    public void setCantidadUnit(int cantidadUnit) {
        this.cantidadUnit = cantidadUnit;
    }

    public Date getFechaPrio() {
        return fechaPrio;
    }

    public void setFechaPrio(Date fechaPrio) {
        this.fechaPrio = fechaPrio;
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
