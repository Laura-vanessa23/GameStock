


package com.poo.gamestock.modelo;

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

    public Factura() {
        detalles = new ArrayList<>();
    }

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
    public void agregarDetalle(Producto producto, int cantidad) {
        agregarDetalle(new DetalleFactura(producto, cantidad));
    }

    public double calcularTotal() {

        total = 0;

        for (DetalleFactura detalle : detalles) {
            total += detalle.calcularSubtotal();
        }

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
                            detalle.calcularSubtotal()) + "\n";
        }

        texto += "-------------------------------\n";
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
