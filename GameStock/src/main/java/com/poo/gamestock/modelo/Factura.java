/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.gamestock.modelo;

import com.poo.gamestock.excepciones.StockInsuficienteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Factura {

    private String numeroFactura;
    private LocalDate fecha;
    private double total;
    private Cliente cliente;
    private Vendedor vendedor;
    private List<DetalleFactura> detalles;

    // Constructor con todos los datos
    public Factura(String numeroFactura, LocalDate fecha,
               Cliente cliente, Vendedor vendedor) {

        setNumeroFactura(numeroFactura);
        setFecha(fecha);
        setCliente(cliente);
        setVendedor(vendedor);
        detalles = new ArrayList<>();
    }

    // Sobrecarga 1
    public void agregarDetalle(DetalleFactura detalle) {

        if (detalle == null) {
            throw new IllegalArgumentException(
                    "El detalle no puede ser nulo.");
        }

        detalles.add(detalle);
        calcularTotal();
    }

       // Sobrecarga 2
    public void agregarDetalle(Producto producto, int cantidad)
            throws StockInsuficienteException {
        agregarDetalle(new DetalleFactura(producto, cantidad));
    }

    // Sobrecarga 3 con descuento adicional
    public void agregarDetalle(Producto producto, int cantidad, double descuentoPorcentaje)
            throws StockInsuficienteException {
        agregarDetalle(new DetalleFactura(producto, cantidad, descuentoPorcentaje));
    }

    // Suma solo los productos sin el envio
    public double calcularSubtotal() {
        double suma = 0;

        for (DetalleFactura detalle : detalles) {
            suma += detalle.calcularSubtotal();
        }

        return suma;
    }

    // Suma el envio de los productos Enviable
    // si la compra llega al minimo el envio es gratis
    public double calcularEnvio() {
        double envio = 0;

        for (DetalleFactura detalle : detalles) {
            envio += detalle.calcularEnvio();
        }

        if (calcularSubtotal() >= Enviable.MINIMO_ENVIO_GRATIS) {
            return 0;
        }

        return envio;
    }

    public double calcularTotal() {
        total = calcularSubtotal() + calcularEnvio();
        return total;
    }

    public String generarComprobante() {

        if (detalles.isEmpty()) {
            throw new IllegalArgumentException(
                    "La factura debe tener al menos un detalle.");
        }

        String texto = "======== FACTURA "
                + numeroFactura + " ========\n";

        texto += "Fecha: " + fecha + "\n";
        texto += "Cliente: " + cliente.getNombre() + "\n";
        texto += "Vendedor: " + vendedor.getNombre() + "\n";
        texto += "-------------------------------\n";

        for (DetalleFactura detalle : detalles) {
            texto += detalle.getCantidad() + " x "
                    + detalle.getProducto().getNombre()
                    + " = $" + String.format("%,.2f",
                            detalle.calcularSubtotal());

            // Si tiene descuento adicional se muestra
            if (detalle.getDescuentoPorcentaje() > 0) {
                texto += " (desc " + detalle.getDescuentoPorcentaje() + "%)";
            }
            texto += "\n";

            // Si el producto tiene garantia se muestra
            if (detalle.getProducto() instanceof Garantizable) {
                Garantizable garantizable = (Garantizable) detalle.getProducto();
                texto += "   " + garantizable.describirGarantia() + "\n";
            }
        }

        texto += "-------------------------------\n";
        texto += "Subtotal: $" + String.format("%,.2f",
                calcularSubtotal()) + "\n";
        texto += "Envio: $" + String.format("%,.2f",
                calcularEnvio()) + "\n";
        texto += "TOTAL: $" + String.format("%,.2f",
                calcularTotal()) + "\n";
        texto += "===============================";

        return texto;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        if (numeroFactura == null || numeroFactura.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El numero de factura no puede estar vacio.");
        }
        this.numeroFactura = numeroFactura.trim();
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException(
                    "La fecha no puede ser nula.");
        }
        this.fecha = fecha;
    }

    public double getTotal() {
        return calcularTotal();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException(
                    "La factura debe tener un cliente.");
        }
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        if (vendedor == null) {
            throw new IllegalArgumentException(
                    "La factura debe tener un vendedor.");
        }
        this.vendedor = vendedor;
    }

    public List<DetalleFactura> getDetalles() {
        return new ArrayList<>(detalles);
    }
}