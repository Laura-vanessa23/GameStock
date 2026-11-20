/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.poo.gamestock.modelo;

public class Consola extends Producto {

    private String modelo;
    private String almacenamiento;

    public Consola() {
        super();
    }

    public Consola(String codigo, String nombre, double precio,
                   int stock, String plataforma,
                   String modelo, String almacenamiento) {

        super(codigo, nombre, precio, stock, plataforma);
        setModelo(modelo);
        setAlmacenamiento(almacenamiento);
    }

    @Override
    public double calcularPrecioFinal() {
        return precio;
    }

    public double calcularPrecioFinal(double descuentoPorcentaje) {

        if (!Double.isFinite(descuentoPorcentaje)
                || descuentoPorcentaje < 0
                || descuentoPorcentaje > 100) {
            throw new IllegalArgumentException(
                    "Descuento no valido.");
        }

        return precio * (1 - descuentoPorcentaje / 100);
    }

    public double calcularPrecioFinal(
            double descuentoPorcentaje, double costoEnvio) {

        if (!Double.isFinite(costoEnvio) || costoEnvio < 0) {
            throw new IllegalArgumentException(
                    "El envio no puede ser negativo.");
        }

        return calcularPrecioFinal(descuentoPorcentaje) + costoEnvio;
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Modelo: " + modelo
                + " | Almacenamiento: " + almacenamiento);
    }

    public boolean verificarCompatibilidad() {
        return almacenamiento != null
                && !almacenamiento.isEmpty();
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        if (modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El modelo no puede estar vacio.");
        }
        this.modelo = modelo.trim();
    }

    public String getAlmacenamiento() {
        return almacenamiento;
    }

    public void setAlmacenamiento(String almacenamiento) {
        if (almacenamiento == null
                || almacenamiento.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El almacenamiento no puede estar vacio.");
        }
        this.almacenamiento = almacenamiento.trim();
    }
}
