/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.pizzatime.util;

import java.lang.management.ManagementFactory;
import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 *
 * @author Laura
 */
public class JMXSetup {
    public static void registrarMBeansensist(){//para llamar a este metodo cuando se quiera enseñar las notifs
        //como es estatico puede ser llamado desde cualquier parte del codigo
        try{
            MBeanServer mbeanser= ManagementFactory.getPlatformMBeanServer();//agarra el server MBean q ya esta en la JVM
            ObjectName objnm= new ObjectName("miapp.vencimientos:type=FecVenMonitor");//le da nombre dividido en
            //dominio y tipo, usado para invocar o escuchar notifs de otros cosos 
            FecVenMonitor fecvenmon= new FecVenMonitor();//instancia el MBean
            
            mbeanser.registerMBean(fecvenmon, objnm);//registra el MBean con el nombre dado, poniendolo en el radar
            //del sist para usarlo dsps desde un notificationlistener
            
            
        }catch(Exception e){
            e.printStackTrace();
            
        }
    }
    
}
