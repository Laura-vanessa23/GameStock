/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.gamestock.modelo;

import java.time.LocalDate;

public class Vendedor extends Persona {

    private int idEmpleado;

    // Constructor con todos los datos
    public Vendedor(int idEmpleado, String nombre) {
        super(nombre);
        setIdEmpleado(idEmpleado);
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        if (idEmpleado <= 0) {
            throw new IllegalArgumentException("Codigo invalido.");
        }
        this.idEmpleado = idEmpleado;
    }

    public Factura registrarVenta(String numeroFactura,
                                  Cliente cliente) {

        if (cliente == null) {
            throw new IllegalArgumentException("Cliente invalido.");
        }

        System.out.println("Venta procesada por: " + getNombre());

        // Se crea la factura con el constructor completo
        // el vendedor es this porque es quien esta haciendo la venta
        return new Factura(numeroFactura, LocalDate.now(), cliente, this);
    }
}