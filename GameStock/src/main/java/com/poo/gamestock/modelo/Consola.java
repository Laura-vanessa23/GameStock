/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.gamestock.modelo;

/**
 *
 * LAURA ARIZA
 */
public class Consola extends Producto {
    private String modelo;
    private String almacenamiento;

    // Constructor por defecto
    public Consola() {
        super();
    }

    // Constructor  con validaciones directas
    public Consola(String codigo, String nombre, double precio, int stock, String plataforma, 
                   String modelo, String almacenamiento) {
        super(codigo, nombre, precio, stock, plataforma); 

        // Validaciones directas en el constructor
        if (modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("El modelo de la consola no puede estar vacío.");
        }
        if (almacenamiento == null || almacenamiento.trim().isEmpty()) {
            throw new IllegalArgumentException("El almacenamiento de la consola no puede estar vacío.");
        }

        this.modelo = modelo;
        this.almacenamiento = almacenamiento;
    }

    @Override
    public double calcularPrecioFinal() {
        return this.precio;
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Modelo de Consola: " + modelo + " | Almacenamiento: " + almacenamiento);
    }

    // Método sobrecargado 1
    public double calcularPrecioFinal(double descuentoPorcentaje) {
        if (descuentoPorcentaje < 0 || descuentoPorcentaje > 100) {
            throw new IllegalArgumentException("El porcentaje de descuento debe estar entre 0 y 100.");
        }
        return this.precio * (1 - (descuentoPorcentaje / 100.0));
    }

    // Método sobrecargado 2 Aplica descuento y suma costo de envío fijo
    public double calcularPrecioFinal(double descuentoPorcentaje, double costoEnvio) {
        double precioConDescuento = calcularPrecioFinal(descuentoPorcentaje);
        return precioConDescuento + costoEnvio;
    }

    public boolean verificarCompatibilidad() {
        return this.almacenamiento != null && !this.almacenamiento.isEmpty();
    }

    // Getters y Setters 
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