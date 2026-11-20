/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.gamestock.modelo;

public class VideojuegoDigital extends Videojuego {

    private double tamanioGB;

    public VideojuegoDigital(String codigo, String nombre, double precio,
                             int stock, String plataforma, String genero,
                             String clasificacionEdad, double tamanioGB) {

        super(codigo, nombre, precio, stock, plataforma,
                genero, clasificacionEdad);

        setTamanioGB(tamanioGB);
    }

    @Override
    public double calcularPrecioFinal() {
        return precio * 0.90;
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
        return stock > 0;
    }

    public double getTamanioGB() {
        return tamanioGB;
    }

    public void setTamanioGB(double tamanioGB) {
        if (tamanioGB <= 0) {
            throw new IllegalArgumentException("Tamanio invalido.");
        }
        this.tamanioGB = tamanioGB;
    }
}