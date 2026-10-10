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
    protected String codigo;
    protected String nombre;
    protected double precio;
    protected int stock;
    protected String plataforma;

    // Constructor por defecto
    public Producto() {
    }

    // Constructor parametrizado con validaciones
    public Producto(String codigo, String nombre, double precio, int stock, String plataforma) {
        setCodigo(codigo);
        setNombre(nombre);
        setPrecio(precio);
        setStock(stock);
        setPlataforma(plataforma);
    }

    
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

    // Método general para mostrar información
    public void mostrarInfo() {
        System.out.println("Código: " + codigo + " | Nombre: " + nombre + " | Precio: $" + precio + " | Stock: " + stock + " | Plataforma: " + plataforma);
    }

    // Getters y Setters con validaciones
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código del producto no puede estar vacío.");
        }
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0.");
        }
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
        this.stock = stock;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        if (plataforma == null || plataforma.trim().isEmpty()) {
            throw new IllegalArgumentException("La plataforma no puede estar vacía.");
        }
        this.plataforma = plataforma;
    }
}