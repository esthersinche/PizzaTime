package com.utp.pizzatime.model.entity;

/**
 *
 * @author Laura
 */

/*Hay dos clases de producto debido a q es mejor para lo q es generar pedido y modificar ingrediente, debido a q se va a necesitar guardarlo
si o si en una estructura y no necesitamos andar ingresando en los procedures datos q no tienen default debido a q sera posible modificarlos, y pq 
me daba cosa que estuviesen juntos*/

/*Importante: los ingredientes en pedido ingrediente se guardaran despues de q el usuario presione el boton, para asegurarnos que se guarden en
cierto orden y tambien por q me da paja cambiar mi metodo todochiquito.*/
public class Producto_modificar extends Producto_pedido {
    private Integer MEDIDA;
    private Integer STOCK_ACTUAL;
    private Integer STOCK_CAJAS;
    private Integer STOCK_MIN;
    private Integer STOCK_MAX;
    private String ID_PROV;
    private String DESCRIPCION;

    public Producto_modificar(Integer MEDIDA, Integer STOCK_ACTUAL, Integer STOCK_CAJAS, Integer STOCK_MIN, Integer STOCK_MAX, String ID_PROV, String DESCRIPCION, String ID_PRO, String NOMBRE_PRO, Double PRECIO) {
        super(ID_PRO, NOMBRE_PRO, PRECIO);//llama a la clase padre Producto_pedido para inicializar campos :]
        this.MEDIDA = MEDIDA;
        this.STOCK_ACTUAL = STOCK_ACTUAL;
        this.STOCK_CAJAS = STOCK_CAJAS;
        this.STOCK_MIN = STOCK_MIN;
        this.STOCK_MAX = STOCK_MAX;
        this.ID_PROV = ID_PROV;
        this.DESCRIPCION = DESCRIPCION;
    }

    public Integer getMEDIDA() {
        return MEDIDA;
    }

    public void setMEDIDA(Integer MEDIDA) {
        this.MEDIDA = MEDIDA;
    }

    public Integer getSTOCK_ACTUAL() {
        return STOCK_ACTUAL;
    }

    public void setSTOCK_ACTUAL(Integer STOCK_ACTUAL) {
        this.STOCK_ACTUAL = STOCK_ACTUAL;
    }

    public Integer getSTOCK_CAJAS() {
        return STOCK_CAJAS;
    }

    public void setSTOCK_CAJAS(Integer STOCK_CAJAS) {
        this.STOCK_CAJAS = STOCK_CAJAS;
    }

    public Integer getSTOCK_MIN() {
        return STOCK_MIN;
    }

    public void setSTOCK_MIN(Integer STOCK_MIN) {
        this.STOCK_MIN = STOCK_MIN;
    }

    public Integer getSTOCK_MAX() {
        return STOCK_MAX;
    }

    public void setSTOCK_MAX(Integer STOCK_MAX) {
        this.STOCK_MAX = STOCK_MAX;
    }

    public String getID_PROV() {
        return ID_PROV;
    }

    public void setID_PROV(String ID_PROV) {
        this.ID_PROV = ID_PROV;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

}
