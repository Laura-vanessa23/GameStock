/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.gamestock.modelo;

/**
 *
 * @author Emprendedor
 */
public class Vendedor {
    private int idEmpleado;
    private String nombre;

    // Constructor por defecto
    public Vendedor() {
    }

    // Constructor parametrizado
    public Vendedor(int idEmpleado, String nombre) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
    }

    // Getters y Setters
    public int getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(int idEmpleado) { this.idEmpleado = idEmpleado; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public void registrarVenta() {
        // Lógica para registrar venta
    }
}