/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.gamestock.modelo;

/**
 *
 * @author Emprendedor
 */
public class Consola extends Producto {
    private String modelo;
    private String almacenamiento;

    // Constructor por defecto
    public Consola() {
        super();
    }

    // Constructor parametrizado
    public Consola(String codigo, String nombre, double precio, int stock, String plataforma, 
                   String modelo, String almacenamiento) {
        super(codigo, nombre, precio, stock, plataforma);
        this.modelo = modelo;
        this.almacenamiento = almacenamiento;
    }

    // Sobrescritura del método abstracto de Producto
    @Override
    public double calcularPrecioFinal() {
        // Las consolas mantienen su precio base o se les puede sumar algún impuesto de importación si se desea
        return this.precio;
    }

    // Sobrescritura del método mostrarInfo
    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Modelo de Consola: " + modelo + " | Almacenamiento: " + almacenamiento);
    }

    // Método propio de Consola
    public boolean verificarCompatibilidad() {
        return this.almacenamiento != null && !this.almacenamiento.isEmpty();
    }

    // Getters y Setters específicos
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAlmacenamiento() {
        return almacenamiento;
    }

    public void setAlmacenamiento(String almacenamiento) {
        this.almacenamiento = almacenamiento;
    }
}