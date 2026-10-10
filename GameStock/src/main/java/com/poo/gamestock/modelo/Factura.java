

package com.poo.gamestock.modelo;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author Jesús Gudiño
 */
public class Factura {
    
    private String numeroFactura;
    private Date fecha;
    private double total;
    private Cliente cliente;
    private Vendedor vendedor;
    private List<DetalleFactura> detalles;

    public Factura() {
        this.detalles = new ArrayList<>();
    }

    public Factura(String numeroFactura, Date fecha, Cliente cliente, Vendedor vendedor) {
        this.numeroFactura = numeroFactura;
        this.fecha = fecha;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.detalles = new ArrayList<>();
    }

    public void agregarDetalle(DetalleFactura detalle) {
        detalles.add(detalle);
        calcularTotal();
    }

    public void agregarDetalle(Producto producto, int cantidad) {
        agregarDetalle(new DetalleFactura(producto, cantidad));
    }

    public double calcularTotal() {
        total = 0;
        for (DetalleFactura d : detalles) {
            total += d.calcularSubtotal();
        }
        return total;
    }
    public String generarComprobante() {
    if (detalles.isEmpty()) {
        throw new IllegalArgumentException("La factura debe tener al menos un detalle");
    }
    String texto = "======== FACTURA " + numeroFactura + " ========\n";
    texto += "Fecha: " + fecha + "\n";
    texto += "Cliente: " + cliente.getNombre() + "\n";
    texto += "Vendedor: " + vendedor.getNombre() + "\n";
    texto += "-------------------------------\n";
    for (DetalleFactura d : detalles) {
        texto += d.getCantidad() + " x " + d.getProducto().getNombre() + " = $" + (long) d.getSubtotal() + "\n";
    }
    texto += "-------------------------------\n";
    texto += "TOTAL: $" + (long) total + "\n";
    texto += "===============================";
    return texto;
}
    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public List<DetalleFactura> getDetalles() {
        return detalles;
    }
    
}
