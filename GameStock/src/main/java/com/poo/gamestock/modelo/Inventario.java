/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.gamestock.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Inventario {
    private List<Producto> productos;
    private Date fechaUltimaActualizacion;

    // Constructor
    public Inventario() {
        this.productos = new ArrayList<>();
        this.fechaUltimaActualizacion = new Date();
    }

    // Métodos de gestión de inventario (Agregación con Producto)
    public void agregarProducto(Producto p) {
        productos.add(p);
        this.fechaUltimaActualizacion = new Date();
    }

    public void eliminarProducto(String codigo) {
        productos.removeIf(p -> p.getCodigo().equals(codigo));
        this.fechaUltimaActualizacion = new Date();
    }

    public Producto buscarProducto(String codigo) {
        for (Producto p : productos) {
            if (p.getCodigo().equals(codigo)) {
                return p;
            }
        }
        return null; 
    }

    public void actualizarStock(String codigo, int cantidad) {
        Producto p = buscarProducto(codigo);
        if (p != null) {
            p.setStock(cantidad);
        }
    }

    public List<Producto> listarProductos() {
        return productos;
    }

    // Getters y Setters
    public Date getFechaUltimaActualizacion() { return fechaUltimaActualizacion; }
    public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion) { this.fechaUltimaActualizacion = fechaUltimaActualizacion; }
}
