package com.utp.pizzatime.model.entity;

/**
 *
 * @author BeeIsMega
 */
public class DetallePedido extends Producto_pedido {

    private String idDet;        // PK en DETALLE_PEDIDO
    private String idPed;        // FK a PEDIDO.ID_PED
    private int cantidadCajas;   // cantidad pedida

    public DetallePedido(String idDet,
            String idPed,
            String ID_PRO,
            String NOMBRE_PRO,
            Double PRECIO,
            int cantidadCajas) {
        super(ID_PRO, NOMBRE_PRO, PRECIO);
        this.idDet = idDet;
        this.idPed = idPed;
        this.cantidadCajas = cantidadCajas;
    }

    public String getIdDet() {
        return idDet;
    }

    public void setIdDet(String idDet) {
        this.idDet = idDet;
    }

    public String getIdPed() {
        return idPed;
    }

    public void setIdPed(String idPed) {
        this.idPed = idPed;
    }

    public int getCantidadCajas() {
        return cantidadCajas;
    }

    public void setCantidadCajas(int cantidadCajas) {
        this.cantidadCajas = cantidadCajas;
    }
    
}
