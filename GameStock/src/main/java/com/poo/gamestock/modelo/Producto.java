/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.gamestock.modelo;

import com.poo.gamestock.excepciones.StockInsuficienteException;

public abstract class Producto implements Descontable, Persistible {

    protected String codigo;
    protected String nombre;
    protected double precio;
    protected int stock;
    protected String plataforma;

    public Producto(String codigo, String nombre, double precio,
                    int stock, String plataforma) {

        if (codigo == null || codigo.trim().isEmpty()
                || nombre == null || nombre.trim().isEmpty()
                || precio <= 0 || stock < 0
                || plataforma == null || plataforma.trim().isEmpty()) {
            throw new IllegalArgumentException("Datos del producto invalidos.");
        }

        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.plataforma = plataforma;
    }

    // Metodo abstracto cada hija lo calcula a su manera
    public abstract double calcularPrecioFinal();

    // Sobrecarga 1 viene de la interfaz Descontable
    // usa el metodo de arriba y le aplica el descuento extra
    @Override
    public double calcularPrecioFinal(double descuentoPorcentaje) {
        if (!descuentoPermitido(descuentoPorcentaje)) {
            throw new IllegalArgumentException("Descuento no valido.");
        }
        return calcularPrecioFinal() * (1 - descuentoPorcentaje / 100.0);
    }

    // Sobrecarga 2 descuento extra mas el costo de envio
    public double calcularPrecioFinal(double descuentoPorcentaje,
                                      double costoEnvio) {
        if (costoEnvio < 0) {
            throw new IllegalArgumentException("El envio no puede ser negativo.");
        }
        return calcularPrecioFinal(descuentoPorcentaje) + costoEnvio;
    }

    // Los metodos del stock son synchronized
    // asi solo un hilo a la vez puede usarlos

    public synchronized int consultarStock() {
        return stock;
    }

    // Revisa el stock y lo descuenta como una sola operacion
    // lanza StockInsuficienteException si el stock quedaria negativo
    public synchronized void actualizarStock(int cantidad)
            throws StockInsuficienteException {
        if (stock + cantidad < 0) {
            throw new StockInsuficienteException(stock, -cantidad);
        }
        stock += cantidad;
    }

    public synchronized int getStock() {
        return stock;
    }

    public synchronized void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("Stock invalido.");
        }
        this.stock = stock;
    }

    public void mostrarInfo() {
        System.out.println("Codigo: " + codigo
                + " | Nombre: " + nombre
                + " | Precio: $" + String.format("%,.2f", precio)
                + " | Stock: " + getStock()
                + " | Plataforma: " + plataforma);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getPlataforma() {
        return plataforma;
    }
}