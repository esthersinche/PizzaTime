package com.utp.pizzatime.service;

import com.utp.pizzatime.model.entity.Empleado;

/**
 *
 * @author EstherSinche
 */
public class SessionService {

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
