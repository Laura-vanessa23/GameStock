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

    // Agregar producto validando códigos repetidos (Regla del TAD)
    public void agregarProducto(Producto p) {
        if (p == null) return;
        
        for (Producto prod : productos) {
            if (prod.getCodigo().equalsIgnoreCase(p.getCodigo())) {
                throw new IllegalArgumentException("Error: Ya existe un producto registrado con el código " + p.getCodigo());
            }
        }
        
        productos.add(p);
        this.fechaUltimaActualizacion = new Date();
        System.out.println("Producto agregado al inventario con exito.");
    }

    // Eliminar producto con validación de existencia
    public void eliminarProducto(String codigo) {
        Producto p = buscarProducto(codigo);
        if (p != null) {
            productos.remove(p);
            this.fechaUltimaActualizacion = new Date();
            System.out.println("Producto con codigo " + codigo + " eliminado del inventario.");
        } else {
            System.out.println("Aviso: No se puede eliminar. El producto con código " + codigo + " no se encuentra registrado.");
        }
    }

    //Buscar producto
    public Producto buscarProducto(String codigo) {
        for (Producto p : productos) {
            //equalsIgnoreCase sirve para validar sin distincion de Mayusculas
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null; 
    }

    // Actualizar stock con validación de existencia
    public void actualizarStock(String codigo, int cantidad) {
        Producto p = buscarProducto(codigo);
        if (p != null) {
            p.setStock(cantidad);
            this.fechaUltimaActualizacion = new Date();
            System.out.println("Stock actualizado para el producto: " + codigo);
        } else {
            System.out.println("Aviso: No se pudo actualizar. El producto con código " + codigo + " no existe en el inventario.");
        }
    }

    // Devuelve una copia de la lista para proteger la encapsulación
    public List<Producto> listarProductos() {
        return new ArrayList<>(productos);
    }

    // Getters y Setters
    public Date getFechaUltimaActualizacion() { return fechaUltimaActualizacion; }
    public void setFechaUltimaActualizacion(Date fechaUltimaActualizacion) { this.fechaUltimaActualizacion = fechaUltimaActualizacion; }
}