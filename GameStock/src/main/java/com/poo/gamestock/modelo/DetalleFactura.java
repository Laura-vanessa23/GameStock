
package com.poo.gamestock.modelo;

/**
 *
 * @author Jesús Gudiño
 */
public class DetalleFactura {
    
    
    

    private int cantidad;
    private double precioUnitario;
    private double subtotal;
    private Producto producto;

    public DetalleFactura() {
    }

    public DetalleFactura(Producto producto, int cantidad) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
        if (cantidad > producto.getStock()) {
            throw new IllegalArgumentException("La cantidad supera el stock disponible");
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

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
}
