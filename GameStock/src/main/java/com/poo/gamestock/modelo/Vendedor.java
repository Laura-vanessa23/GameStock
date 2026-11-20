/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.gamestock.modelo;





import java.time.LocalDate;

public class Vendedor extends Persona {

    private int idEmpleado;

    public Vendedor() {
        super();
    }

    public Vendedor(int idEmpleado, String nombre) {
        super(nombre);
        setIdEmpleado(idEmpleado);
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        if (idEmpleado <= 0) {
            throw new IllegalArgumentException(
                    "El codigo de empleado debe ser mayor a cero.");
        }
        this.idEmpleado = idEmpleado;
    }

    public Factura registrarVenta(String numeroFactura,
                                  Cliente cliente) {

        if (cliente == null) {
            throw new IllegalArgumentException(
                    "El cliente no puede ser nulo.");
        }

        System.out.println(
                "Venta procesada por el vendedor: " + getNombre());

        // Uso del constructor por defecto
        Factura factura = new Factura();

        factura.setNumeroFactura(numeroFactura);
        factura.setFecha(LocalDate.now());
        factura.setCliente(cliente);
        factura.setVendedor(this);

        return factura;
    }
}
