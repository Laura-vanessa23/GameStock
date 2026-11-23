/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.poo.gamestock.modelo;

public class Consola extends Producto implements Enviable, Garantizable {

    private String modelo;
    private String almacenamiento;

    // Constructor con todos los datos
    public Consola(String codigo, String nombre, double precio,
                   int stock, String plataforma,
                   String modelo, String almacenamiento) {

        super(codigo, nombre, precio, stock, plataforma);
        setModelo(modelo);
        setAlmacenamiento(almacenamiento);
    }

    // La consola se vende al precio normal
    @Override
    public double calcularPrecioFinal() {
        return precio;
    }

    // Metodo de Enviable: lo que pesa una consola
    @Override
    public double getPesoKg() {
        return 4.5;
    }

    // Cambio el calculo por defecto porque la consola es fragil
    // le sumo un valor extra al envio normal
    @Override
    public double calcularCostoEnvio() {
        return Enviable.super.calcularCostoEnvio() + 15000;
    }

    // Metodo de Garantizable: meses de garantia de la consola
    @Override
    public int getMesesGarantia() {
        return 12;
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Modelo: " + modelo
                + " | Almacenamiento: " + almacenamiento
                );
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