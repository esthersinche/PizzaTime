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
 * @author Laura
 * Para no andar pegandolo en el coso de admin y employee
 */
public class ConfigJMXUtil {
    public static void configListenerJMX(Component parentComponent){
        try{
            MBeanServer mbser= ManagementFactory.getPlatformMBeanServer();
            ObjectName objnam= new ObjectName("miapp.vencimientos:type=FecVenMonitor");
            
            NotificationListener listens= (notification, handback) -> {
                String message= notification.getMessage();
                SwingUtilities.invokeLater(() -> {JOptionPane.showMessageDialog(parentComponent, message, 
                        "Vencimientos Próximos",JOptionPane.WARNING_MESSAGE);
                        });               
            };
            mbser.addNotificationListener(objnam, listens, null, null);
            
        }catch(Exception e){
            e.printStackTrace();
            
        }
    }
    
}
