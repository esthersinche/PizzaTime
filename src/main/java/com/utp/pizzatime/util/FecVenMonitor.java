/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.pizzatime.util;

import java.util.concurrent.atomic.AtomicLong;
import javax.management.*;

/**
 *
 * @author Laura
 */
public class FecVenMonitor extends NotificationBroadcasterSupport implements FecVenMonitorMBean{
    private AtomicLong seq= new AtomicLong(1);//mas para hilos, para tratar de que sea escalable porsia queremos implementar
    //notifs paralelas, es una secuencia q genera ID UNICO
    
    @Override
    public void notifFecvenci(String mssg){//para emitir una notif de ing q esta a punto de vencer
        //crea notif
        //(tipo, fuente, secuencia q incrementa y genera ID, agarra fecha y hr, texto q se mostrara)
        Notification notifa= new Notification("Productos próximos a vencer", 
        this, seq.getAndIncrement(), System.currentTimeMillis(), mssg);
        sendNotification(notifa);       
    }  
}
