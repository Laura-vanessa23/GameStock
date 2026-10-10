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

    // Constructor parametrizado con validaciones
    public Consola(String codigo, String nombre, double precio, int stock, String plataforma, 
                   String modelo, String almacenamiento) {
        super(codigo, nombre, precio, stock, plataforma);
        setModelo(modelo);
        setAlmacenamiento(almacenamiento);
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

    // --- MÉTODOS SOBRECARGADOS (OVERLOAD) exigidos por la rúbrica ---
    
    // Método sobrecargado 1: Aplicar descuento estándar
    public double calcularPrecioFinal(double descuentoPorcentaje) {
        if (descuentoPorcentaje < 0 || descuentoPorcentaje > 100) {
            throw new IllegalArgumentException("El porcentaje de descuento debe estar entre 0 y 100.");
        }
        return this.precio * (1 - (descuentoPorcentaje / 100.0));
    }

    // Método sobrecargado 2: Aplicar descuento y sumar costo de envío fijo
    public double calcularPrecioFinal(double descuentoPorcentaje, double costoEnvio) {
        double precioConDescuento = calcularPrecioFinal(descuentoPorcentaje);
        return precioConDescuento + costoEnvio;
    }

    public boolean verificarCompatibilidad() {
        return this.almacenamiento != null && !this.almacenamiento.isEmpty();
    }

    // Getters y Setters con validaciones
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        if (modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("El modelo de la consola no puede estar vacío.");
        }
        this.modelo = modelo;
    }

    public String getAlmacenamiento() {
        return almacenamiento;
    }

    public void setAlmacenamiento(String almacenamiento) {
        if (almacenamiento == null || almacenamiento.trim().isEmpty()) {
            throw new IllegalArgumentException("El almacenamiento de la consola no puede estar vacío.");
        }
        this.almacenamiento = almacenamiento;
    }
}