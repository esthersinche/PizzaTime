package com.utp.pizzatime.model.entity;
/**
 *
 * @author EstherSinche
 */
public class Empleado {
    int dni_emp;
    String nombre_emp, rol, pass;

    public Empleado(int dni_emp, String nombre_emp, String rol, String pass) {
        this.dni_emp = dni_emp;
        this.nombre_emp = nombre_emp;
        this.rol = rol;
        this.pass = pass;
    }

    public int getDni_emp() {
        return dni_emp;
    }

    public void setDni_emp(int dni_emp) {
        this.dni_emp = dni_emp;
    }

    public String getNombre_emp() {
        return nombre_emp;
    }

    public void setNombre_emp(String nombre_emp) {
        this.nombre_emp = nombre_emp;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
