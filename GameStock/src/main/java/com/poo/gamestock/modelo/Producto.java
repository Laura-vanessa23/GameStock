/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.gamestock.modelo;

/**
 *
 * LAURA ARIZA
 */
public abstract class Producto {
    protected String codigo; // acceso dif aluml por herencia para las subclases
    protected String nombre;
    protected double precio;
    protected int stock;
    protected String plataforma;

    // Constructor por defecto
    public Producto() {
    }

    // Constructor  con validaciones directas
    public Producto(String codigo, String nombre, double precio, int stock, String plataforma) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código del producto no puede estar vacío.");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0.");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo."); // Detiene la ejecución si se rompe la regla de negocio
        }
        if (plataforma == null || plataforma.trim().isEmpty()) {
            //es una alerta 
            throw new IllegalArgumentException("La plataforma no puede estar vacía.");   
        }

        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.plataforma = plataforma;
    }

    // Método abstracto 
    public abstract double calcularPrecioFinal();

   
    public int consultarStock() {
        return this.stock;
    }

    public void actualizarStock(int cantidad) {
        if (this.stock + cantidad < 0) {
            throw new IllegalArgumentException("El stock no puede quedar negativo.");
        }
        this.stock += cantidad;
    }

    // Mostrar información
    public void mostrarInfo() {
        System.out.println("Codigo: " + codigo + " | Nombre: " + nombre + " | Precio: $" + precio + " | Stock: " + stock + " | Plataforma: " + plataforma);
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