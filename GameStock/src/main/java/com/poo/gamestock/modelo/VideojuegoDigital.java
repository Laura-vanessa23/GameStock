/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.gamestock.modelo;

/**
 *
 * @author PC
 */


public class VideojuegoDigital extends Videojuego {
    private double tamanioGB;

    public VideojuegoDigital() {
        super();
    }

    public VideojuegoDigital(String codigo, String nombre, double precio, int stock, String plataforma,
                             String genero, String clasificacionEdad, double tamanioGB) {
        super(codigo, nombre, precio, stock, plataforma, genero, clasificacionEdad);

        if (tamanioGB <= 0) {
            throw new IllegalArgumentException("El tamanio de la descarga debe ser mayor a 0.");
        }

        this.tamanioGB = tamanioGB;
    }

    @Override
    public double calcularPrecioFinal() {
        return this.precio * 0.90;
    }

    @Override
    public String getFormato() {
        return "Digital";
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Tamanio de descarga: " + tamanioGB + " GB");
    }

    public boolean verificarDisponibilidadDigital() {
        return this.stock > 0;
    }

    public double getTamanioGB() {
        return tamanioGB;
    }

    public void setTamanioGB(double tamanioGB) {
        this.tamanioGB = tamanioGB;
    }
}