/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.gamestock.modelo;

/**
 *
 * LAURA ARIZA
 */


public abstract class Videojuego extends Producto {

    private String genero;
    private String clasificacionEdad;

    public Videojuego() {
        super();
    }

    public Videojuego(String codigo, String nombre, double precio,
            int stock, String plataforma, String genero,
            String clasificacionEdad) {

        super(codigo, nombre, precio, stock, plataforma);

        setGenero(genero);
        setClasificacionEdad(clasificacionEdad);
    }

    public abstract String getFormato();

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();

        System.out.println("Genero: " + genero
                + " | Formato: " + getFormato()
                + " | Clasificacion: " + clasificacionEdad);
    }

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