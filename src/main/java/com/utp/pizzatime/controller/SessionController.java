package com.utp.pizzatime.controller;

import com.utp.pizzatime.model.entity.Empleado;

/**
 *
 * @author BeeIsMega
 */
public class SessionController {

    private static Empleado currentUser;

    /**
     * Llama esto tras login exitoso
     */
    public static void setCurrentUser(Empleado user) {
        currentUser = user;
    }

    /**
     * Devuelve el empleado logueado
     */
    public static Empleado getCurrentUser() {
        return currentUser;
    }

    /**
     * Devuelve el DNI o lanza IllegalState si no hay sesión
     */
    public static int getCurrentDni() {
        if (currentUser == null) {
            throw new IllegalStateException("No hay usuario logueado");
        }
        return currentUser.getDni_emp();
    }

}
