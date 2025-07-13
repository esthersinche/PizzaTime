/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.pizzatime.util;

import java.awt.Component;
import java.lang.management.ManagementFactory;
import javax.management.MBeanServer;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Laura Para no andar pegandolo en el coso de admin y employee
 */
public class ConfigJMXUtil {

    private static NotificationListener listener;

    public static void configListenerJMX(Component parentComponent) {
        try {
            MBeanServer mbser = ManagementFactory.getPlatformMBeanServer();
            ObjectName objname = new ObjectName("miapp.vencimientos:type=FecVenMonitor");
            System.out.println("[configListenerJMX] isRegistered?" + mbser.isRegistered(objname));

            /*
            // ver q el MBean esté registrado
        if (!mbser.isRegistered(objnam)) {
            System.out.println("[configListenerJMX] Registrando MBean");
            
            mbser.registerMBean(new FecVenMonitor(), objnam);
        }*/
            //Si ya existía, se quita
            if (mbser.isRegistered(objname)) {
                System.out.println("[configListenerJMX] Desregistrando MBean existente");
                try {
                    mbser.unregisterMBean(objname);
                } catch (Exception ignored) {
                    //aunq falle
                }
            }

            System.out.println("[configListenerJMX] Registrando MBean FecVenMonitor");
            mbser.registerMBean(new FecVenMonitor(), objname);

            // Limpiar listener
            if (listener != null) {
                mbser.removeNotificationListener(objname, listener);
            }

            listener = (notification, handback) -> {
                String message = notification.getMessage();
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(parentComponent, message,
                            "Vencimientos Próximos", JOptionPane.WARNING_MESSAGE);
                });
            };
            mbser.addNotificationListener(objname, listener, null, null);
            System.out.println("adding orrectly");

        } catch (Exception e) {
            System.err.println("[configListenerJMX] ERROR configurando listener");
            e.printStackTrace();

        }
    }

    public static void unconfigListener() throws Exception {
        System.out.println("aaaaaaaaa");

        MBeanServer mbser2 = ManagementFactory.getPlatformMBeanServer();
        ObjectName objname2 = new ObjectName("miapp.vencimientos:type=FecVenMonitor");
        System.out.println("[unconfigListener] isRegistered antes de remove? " + mbser2.isRegistered(objname2));

        if (listener != null && mbser2.isRegistered(objname2)) {//remover el listener
            mbser2.removeNotificationListener(objname2, listener);
            System.out.println("listener fuera out omnnfqf");
            listener = null;

            if (mbser2.isRegistered(objname2)) {
                mbser2.unregisterMBean(objname2);
                System.out.println("mbean killed");
            }

        }

        System.out.println("[unconfigListener] isRegistered después? " + mbser2.isRegistered(objname2));
    }

}
