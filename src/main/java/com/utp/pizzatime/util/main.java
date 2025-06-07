package com.utp.pizzatime.util;

import com.sun.tools.javac.Main;

public class main {
   private static final org.slf4j.Logger log =
        org.slf4j.LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Arrancando prueba de conexion...");
        try {
            SQLConexion c = new SQLConexion();
            c.establecerConexion();
            log.info("Conexion OK");
        } catch (Exception e) {
            log.error("Fallo al conectar", e);
        }
    }
}
