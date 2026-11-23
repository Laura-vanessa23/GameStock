/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.gamestock.modelo;

public abstract class Producto implements Descontable {

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

    public int consultarStock() {
        return stock;
    }

    public void actualizarStock(int cantidad) {
        if (stock + cantidad < 0) {
            throw new IllegalArgumentException("Stock insuficiente.");
        }
        stock += cantidad;
    }

    public void mostrarInfo() {
    System.out.println("Codigo: " + codigo
            + " | Nombre: " + nombre
            + " | Precio: $" + String.format("%,.2f", precio)
            + " | Stock: " + stock
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("Stock invalido.");
        }
        this.stock = stock;
    }

    public String getPlataforma() {
        return plataforma;
    }
}