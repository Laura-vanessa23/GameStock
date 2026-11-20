/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.gamestock.modelo;



public class VideojuegoFisico extends Videojuego {
    private String edicion;

    public VideojuegoFisico() {
        super();
    }

    public VideojuegoFisico(String codigo, String nombre, double precio, int stock, String plataforma,
                            String genero, String clasificacionEdad, String edicion) {
        super(codigo, nombre, precio, stock, plataforma, genero, clasificacionEdad);

        if (edicion == null || (!edicion.equalsIgnoreCase("Estandar") && !edicion.equalsIgnoreCase("Coleccionista"))) {
            throw new IllegalArgumentException("La edicion solo admite los valores 'Estandar' o 'Coleccionista'.");
        }

        this.edicion = edicion;
    }

    @Override
    public double calcularPrecioFinal() {
        if (edicion != null && edicion.equalsIgnoreCase("Coleccionista")) {
            return this.precio * 1.15;
        }
        return this.precio;
    }

    @Override
    public String getFormato() {
        return "Fisico";
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Edicion: " + edicion);
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }
}