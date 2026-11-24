/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.gamestock.modelo;

import com.poo.gamestock.excepciones.StockInsuficienteException;

/**
 *
 * @author Jesús Gudiño
 */
public class DetalleFactura {

    private int cantidad;
    private double precioUnitario;
    private double subtotal;
    private double descuentoPorcentaje;
    private Producto producto;

    // Constructor sin descuento
    public DetalleFactura(Producto producto, int cantidad)
            throws StockInsuficienteException {
        this(producto, cantidad, 0);
    }

    // Constructor con descuento adicional
    public DetalleFactura(Producto producto, int cantidad, double descuentoPorcentaje)
            throws StockInsuficienteException {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
        if (cantidad > producto.getStock()) {
            throw new StockInsuficienteException(producto.getStock(), cantidad);
        }
        this.producto = producto;
        this.cantidad = cantidad;
        this.descuentoPorcentaje = descuentoPorcentaje;
        // Aqui se usa la sobrecarga de Descontable y ella valida el descuento
        this.precioUnitario = producto.calcularPrecioFinal(descuentoPorcentaje);
        this.subtotal = precioUnitario * cantidad;
    }

    public double calcularSubtotal() {
        subtotal = precioUnitario * cantidad;
        return subtotal;
    }

    // Si el producto es Enviable se calcula el envio si no es 0
    public double calcularEnvio() {
        if (producto instanceof Enviable) {
            Enviable enviable = (Enviable) producto;
            return enviable.calcularCostoEnvio() * cantidad;
        }
        return 0;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) throws StockInsuficienteException {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
        if (producto != null && cantidad > producto.getStock()) {
            throw new StockInsuficienteException(producto.getStock(), cantidad);
        }
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        if (precioUnitario <= 0) {
            throw new IllegalArgumentException("El precio unitario debe ser mayor a 0");
        }
        this.precioUnitario = precioUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getDescuentoPorcentaje() {
        return descuentoPorcentaje;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}