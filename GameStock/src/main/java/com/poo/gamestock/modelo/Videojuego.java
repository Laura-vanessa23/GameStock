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

    // Constructor parametrizado
    public Videojuego(String codigo, String nombre, double precio, int stock, String plataforma, 
                      String genero, String formato, String clasificacionEdad) {
        super(codigo, nombre, precio, stock, plataforma);
        this.genero = genero;
        this.formato = formato;
        this.clasificacionEdad = clasificacionEdad;
    }

    // Sobrescritura del método abstracto de Producto
    @Override
    public double calcularPrecioFinal() {
        // Ejemplo de lógica: si es digital, aplicamos un pequeño descuento, o retornamos el precio base
        if (formato != null && formato.equalsIgnoreCase("Digital")) {
            return this.precio * 0.90; // 10% de descuento por ser digital
        }
        return this.precio;
    }

    // Sobrescritura del método mostrarInfo
    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Género: " + genero + " | Formato: " + formato + " | Clasificación: " + clasificacionEdad);
    }

    // Método propio de Videojuego
    public boolean verificarDisponibilidadDigital() {
        return this.formato != null && this.formato.equalsIgnoreCase("Digital") && this.stock > 0;
    }

    // Getters y Setters específicos
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