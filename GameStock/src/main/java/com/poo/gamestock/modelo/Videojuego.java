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
    private String formato;
    private String clasificacionEdad;

    // Constructor por defecto
    public Videojuego() {
        super();
    }

    // Constructor parametrizado
    public Videojuego(String codigo, String nombre, double precio,
            int stock, String plataforma, String genero,
            String formato, String clasificacionEdad) {

        super(codigo, nombre, precio, stock, plataforma);

        setGenero(genero);
        setFormato(formato);
        setClasificacionEdad(clasificacionEdad);
    }

    @Override
    public double calcularPrecioFinal() {

        if (formato != null && formato.equalsIgnoreCase("Digital")) {
            return precio * 0.90;
        }

        return precio;
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();

        System.out.println("Genero: " + genero
                + " | Formato: " + formato
                + " | Clasificacion: " + clasificacionEdad);
    }

    public boolean verificarDisponibilidadDigital() {
        return formato != null
                && formato.equalsIgnoreCase("Digital")
                && stock > 0;
    }

    // Getters y setters

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        if (genero == null || genero.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El genero no puede estar vacio.");
        }
        this.genero = genero;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {

        if (formato == null
                || (!formato.equalsIgnoreCase("Fisico")
                && !formato.equalsIgnoreCase("Físico")
                && !formato.equalsIgnoreCase("Digital"))) {

            throw new IllegalArgumentException(
                    "El formato debe ser Fisico o Digital.");
        }

        this.formato = formato.equalsIgnoreCase("Digital")
                ? "Digital" : "Fisico";
    }

    public String getClasificacionEdad() {
        return clasificacionEdad;
    }

    public void setClasificacionEdad(String clasificacionEdad) {
        if (clasificacionEdad == null
                || clasificacionEdad.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "La clasificacion no puede estar vacia.");
        }
        this.clasificacionEdad = clasificacionEdad;
    }
}
