/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.gamestock.modelo;

public class VideojuegoFisico extends Videojuego implements Enviable {

    private String edicion;

    // Constructor con todos los datos
    public VideojuegoFisico(String codigo, String nombre, double precio, int stock, String plataforma,
                            String genero, String clasificacionEdad, String edicion) {
        super(codigo, nombre, precio, stock, plataforma, genero, clasificacionEdad);
        setEdicion(edicion);
    }

    // La edicion coleccionista cuesta 15% mas
    @Override
    public double calcularPrecioFinal() {
        if (edicion.equalsIgnoreCase("Coleccionista")) {
            return this.precio * 1.15;
        }
        return this.precio;
    }

    @Override
    public String getFormato() {
        return "Fisico";
    }

    // Metodo de Enviable: lo que pesa un juego fisico
    @Override
    public double getPesoKg() {
        return 0.2;
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Edicion: " + edicion
                + " | Costo de envio: $" + String.format("%,.2f", calcularCostoEnvio()));
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        if (edicion == null || (!edicion.equalsIgnoreCase("Estandar") && !edicion.equalsIgnoreCase("Coleccionista"))) {
            throw new IllegalArgumentException("La edicion solo admite los valores 'Estandar' o 'Coleccionista'.");
        }
        this.edicion = edicion;
    }
}