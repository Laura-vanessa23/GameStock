/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.poo.gamestock.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Inventario {

    private List<Producto> productos;
    private LocalDate fechaUltimaActualizacion;

    public Inventario() {
        productos = new ArrayList<>();
        fechaUltimaActualizacion = LocalDate.now();
    }

    public synchronized void agregarProducto(Producto producto) {

        if (producto == null) {
            throw new IllegalArgumentException(
                    "El producto no puede ser nulo.");
        }

        for (Producto p : productos) {
            if (p.getCodigo().equalsIgnoreCase(
                    producto.getCodigo())) {
                throw new IllegalArgumentException(
                        "Ya existe un producto con ese codigo.");
            }
        }

        productos.add(producto);
        fechaUltimaActualizacion = LocalDate.now();

        System.out.println("Producto agregado correctamente.");
    }

    public synchronized void eliminarProducto(String codigo) {

        Producto producto = buscarProducto(codigo);

        if (producto == null) {
            throw new IllegalArgumentException(
                    "El producto no existe.");
        }

        productos.remove(producto);
        fechaUltimaActualizacion = LocalDate.now();
    }

    public synchronized Producto buscarProducto(String codigo) {

        if (codigo == null) {
            return null;
        }

        for (Producto p : productos) {
            if (p.getCodigo().equalsIgnoreCase(codigo.trim())) {
                return p;
            }
        }

        return null;
    }

    public synchronized void actualizarStock(
            String codigo, int cantidad) {

        Producto producto = buscarProducto(codigo);

        if (producto == null) {
            throw new IllegalArgumentException(
                    "El producto no existe.");
        }

        producto.setStock(cantidad);
        fechaUltimaActualizacion = LocalDate.now();
    }

    public synchronized List<Producto> listarProductos() {
        return new ArrayList<>(productos);
    }

    public LocalDate getFechaUltimaActualizacion() {
        return fechaUltimaActualizacion;
    }

    public void setFechaUltimaActualizacion(LocalDate fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException(
                    "La fecha no puede ser nula.");
        }
        fechaUltimaActualizacion = fecha;
    }
}
