
package com.poo.gamestock.modelo;

public class DetalleFactura {

    private int cantidad;
    private double precioUnitario;
    private double subtotal;
    private Producto producto;

    public DetalleFactura(Producto producto, int cantidad) {

        if (producto == null || cantidad <= 0
                || cantidad > producto.getStock()) {
            throw new IllegalArgumentException("Detalle invalido.");
        }

        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = producto.calcularPrecioFinal();
        this.subtotal = precioUnitario * cantidad;
    }

    public double calcularSubtotal() {
        subtotal = precioUnitario * cantidad;
        return subtotal;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public Producto getProducto() {
        return producto;
    }
}
