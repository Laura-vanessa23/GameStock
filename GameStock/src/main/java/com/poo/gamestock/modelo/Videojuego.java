/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.gamestock.modelo;

/**
 *
 * LAURA ARIZA
 */
public class Videojuego extends Producto {
    private String genero;
    private String formato; // "Físico" o "Digital"
    private String clasificacionEdad;

    // Constructor por defecto
    public Videojuego() {
        super();
    }

    // Constructor con validaciones directas
    public Videojuego(String codigo, String nombre, double precio, int stock, String plataforma, 
                      String genero, String formato, String clasificacionEdad) {
        super(codigo, nombre, precio, stock, plataforma); // Valida los atributos heredados de Producto

        // Validaciones directas en el constructor
        if (genero == null || genero.trim().isEmpty()) {
            throw new IllegalArgumentException("El género del videojuego no puede estar vacío.");
        }
        if (formato == null || (!formato.equalsIgnoreCase("Físico") && !formato.equalsIgnoreCase("Digital"))) {
            throw new IllegalArgumentException("El formato sólo admite los valores 'Físico' o 'Digital'.");
        }
        if (clasificacionEdad == null || clasificacionEdad.trim().isEmpty()) {
            throw new IllegalArgumentException("La clasificación de edad no puede estar vacía.");
        }

        this.genero = genero;
        this.formato = formato;
        this.clasificacionEdad = clasificacionEdad;
    }

    @Override
    public double calcularPrecioFinal() {
        if (formato != null && formato.equalsIgnoreCase("Digital")) {
            return this.precio * 0.90; // 10% de descuento por ser digital
        }
        return this.precio;
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Género: " + genero + " | Formato: " + formato + " | Clasificación: " + clasificacionEdad);
    }

    public boolean verificarDisponibilidadDigital() {
        return this.formato != null && this.formato.equalsIgnoreCase("Digital") && this.stock > 0;
    }

    // Getters y Setters 
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getClasificacionEdad() {
        return clasificacionEdad;
    }

    public void setClasificacionEdad(String clasificacionEdad) {
        this.clasificacionEdad = clasificacionEdad;
    }
}