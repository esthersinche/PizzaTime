package com.utp.pizzatime.model.entity;

import java.util.Date;
import java.util.List;

/**
 *
 * @author BeeIsMega
 */

/*Estaremos dejando lo que es estado de pedido de lado hasta nuevo aviso.*/
public class Pedido {

    private String idPed;            
    private int dniEmp;              
    private Date fechaPedido;
    private List<DetallePedido> detalles;

    public Pedido(String idPed, int dniEmp, Date fechaPedido) {
        this.idPed = idPed;
        this.dniEmp = dniEmp;
        this.fechaPedido = fechaPedido;
    }

    public String getIdPed() {
        return idPed;
    }

    public void setIdPed(String idPed) {
        this.idPed = idPed;
    }

    public int getDniEmp() {
        return dniEmp;
    }

    public void setDniEmp(int dniEmp) {
        this.dniEmp = dniEmp;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }
    
    
}
