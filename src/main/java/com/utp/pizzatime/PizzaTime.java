/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.utp.pizzatime;

import com.utp.pizzatime.view.Login;

/**
 *
 * @author BeeIsMega
 */
public class PizzaTime {

    public static void main(String[] args) {

        // Iniciar interfaz gráfica en el hilo correcto
        javax.swing.SwingUtilities.invokeLater(() -> {
            Login login = new Login();
            login.setVisible(true);
        });

    }
}
