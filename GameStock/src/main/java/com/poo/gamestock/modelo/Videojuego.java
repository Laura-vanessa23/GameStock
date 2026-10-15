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

    // Constructor parametrizado con validaciones
    public Videojuego(String codigo, String nombre, double precio, int stock, String plataforma, 
                      String genero, String formato, String clasificacionEdad) {
        super(codigo, nombre, precio, stock, plataforma);
        setGenero(genero);
        setFormato(formato);
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

    // Getters y Setters con validaciones específicas
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        if (genero == null || genero.trim().isEmpty()) {
            throw new IllegalArgumentException("El género del videojuego no puede estar vacío.");
        }
        this.genero = genero;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        if (formato == null || (!formato.equalsIgnoreCase("Fisico") && !formato.equalsIgnoreCase("Digital"))) {
            throw new IllegalArgumentException("El formato sólo admite los valores 'Físico' o 'Digital'.");
        }
        this.formato = formato;
    }

    public String getClasificacionEdad() {
        return clasificacionEdad;
    }

    public void setClasificacionEdad(String clasificacionEdad) {
        this.clasificacionEdad = clasificacionEdad;
    }
}