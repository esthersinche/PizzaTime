/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
public class Producto_pedido {
    private String ID_PRO;
    private String NOMBRE_PRO;
    private Double PRECIO;

    public Producto_pedido(String ID_PRO, String NOMBRE_PRO, Double PRECIO) {
        this.ID_PRO = ID_PRO;
        this.NOMBRE_PRO = NOMBRE_PRO;
        this.PRECIO = PRECIO;
    }

    public String getID_PRO() {
        return ID_PRO;
    }

    public void setID_PRO(String ID_PRO) {
        this.ID_PRO = ID_PRO;
    }

    public String getNOMBRE_PRO() {
        return NOMBRE_PRO;
    }

    public void setNOMBRE_PRO(String NOMBRE_PRO) {
        this.NOMBRE_PRO = NOMBRE_PRO;
    }

    public Double getPRECIO() {
        return PRECIO;
    }

    public void setPRECIO(Double PRECIO) {
        this.PRECIO = PRECIO;
    }
    
    
    
    
}
