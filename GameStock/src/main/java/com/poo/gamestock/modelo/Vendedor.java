/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.gamestock.modelo;


import java.util.Date;

public class Vendedor {
    private int idEmpleado;
    private String nombre;

    // Constructor por defecto
    public Vendedor() {
    }

    // Constructor parametrizado
    public Vendedor(int idEmpleado, String nombre) {
        setIdEmpleado(idEmpleado);
        setNombre(nombre);
    }

    // Getters y Setters con validaciones
    public int getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(int idEmpleado) {
        if (idEmpleado <= 0) {
            throw new IllegalArgumentException("El código de empleado debe ser mayor a 0.");
        }
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del vendedor no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public Factura registrarVenta(String numeroFactura, Cliente cliente) {
        System.out.println("Venta procesada por el vendedor: " + this.nombre);
        // Crea y retorna la factura asociada
        return new Factura(numeroFactura, new Date(), cliente, this);
    }
}