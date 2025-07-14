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
    private int MEDIDA;
    private int STOCK_ACTUAL;
    private int STOCK_CAJAS;
    private int STOCK_MIN;
    private int STOCK_MAX;
    private String ID_PROV;
    private String DESCRIPCION;

    public Producto_modificar(int MEDIDA, int STOCK_ACTUAL, int STOCK_CAJAS, int STOCK_MIN, int STOCK_MAX, String ID_PROV, String DESCRIPCION, String ID_PRO, String NOMBRE_PRO, Double PRECIO) {
        super(ID_PRO, NOMBRE_PRO, PRECIO);//llama a la clase padre Producto_pedido para inicializar campos :]
        this.MEDIDA = MEDIDA;
        this.STOCK_ACTUAL = STOCK_ACTUAL;
        this.STOCK_CAJAS = STOCK_CAJAS;
        this.STOCK_MIN = STOCK_MIN;
        this.STOCK_MAX = STOCK_MAX;
        this.ID_PROV = ID_PROV;
        this.DESCRIPCION = DESCRIPCION;
    }

    public int getMEDIDA() {
        return MEDIDA;
    }

    public void setMEDIDA(int MEDIDA) {
        this.MEDIDA = MEDIDA;
    }

    public int getSTOCK_ACTUAL() {
        return STOCK_ACTUAL;
    }

    public void setSTOCK_ACTUAL(int STOCK_ACTUAL) {
        this.STOCK_ACTUAL = STOCK_ACTUAL;
    }

    public int getSTOCK_CAJAS() {
        return STOCK_CAJAS;
    }

    public void setSTOCK_CAJAS(int STOCK_CAJAS) {
        this.STOCK_CAJAS = STOCK_CAJAS;
    }

    public int getSTOCK_MIN() {
        return STOCK_MIN;
    }

    public void setSTOCK_MIN(int STOCK_MIN) {
        this.STOCK_MIN = STOCK_MIN;
    }

    public int getSTOCK_MAX() {
        return STOCK_MAX;
    }

    public void setSTOCK_MAX(int STOCK_MAX) {
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
