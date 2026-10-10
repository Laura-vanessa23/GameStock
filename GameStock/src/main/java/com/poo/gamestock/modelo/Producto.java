/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.gamestock.modelo;

/**
 *
 * @author Emprendedor
 */
public abstract class Producto {
    protected String codigo;
    protected String nombre;
    protected double precio;
    protected int stock;
    protected String plataforma;

    // Constructor por defecto
    public Producto() {
    }

    // Constructor parametrizado
    public Producto(String codigo, String nombre, double precio, int stock, String plataforma) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.plataforma = plataforma;
    }

    // Método abstracto que las subclases deben implementar obligatoriamente
    public abstract double calcularPrecioFinal();

    // Método general para mostrar información
    public void mostrarInfo() {
        System.out.println("Código: " + codigo + " | Nombre: " + nombre + " | Precio: $" + precio + " | Stock: " + stock + " | Plataforma: " + plataforma);
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }
}