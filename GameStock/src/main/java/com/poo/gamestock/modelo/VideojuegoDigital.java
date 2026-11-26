/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.gamestock.modelo;

/**
 *
 * @author PC
 */
public class VideojuegoDigital extends Videojuego implements Descargable {

    private double tamanioGB;

    // Constructor con todos los datos
    public VideojuegoDigital(String codigo, String nombre, double precio,
                             int stock, String plataforma, String genero,
                             String clasificacionEdad, double tamanioGB) {

        super(codigo, nombre, precio, stock, plataforma,
                genero, clasificacionEdad);

        setTamanioGB(tamanioGB);
    }

    // El digital tiene 10% de descuento
    @Override
    public double calcularPrecioFinal() {
        return precio * 0.90;
    }

    @Override
    public String getFormato() {
        return "Digital";
    }

    // Metodo de Descargable arma el codigo con el codigo del juego y la hora
    @Override
    public String generarCodigoDescarga() {
        return getCodigo() + "-" + System.currentTimeMillis();
    }

    // Metodo de Persistible: el juego digital como una linea de texto
    @Override
    public String aLinea() {
        return unir("DIGITAL", codigo, nombre, String.valueOf(precio),
                String.valueOf(getStock()), plataforma, getGenero(),
                getClasificacionEdad(), String.valueOf(tamanioGB));
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Tamanio de descarga: " + tamanioGB + " GB"
                + " | Tiempo estimado: " + String.format("%.1f", calcularTiempoDescargaMinutos()) + " min");
    }

    public boolean verificarDisponibilidadDigital() {
        return stock > 0;
    }

    // Metodo de Descargable
    @Override
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