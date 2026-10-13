/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.gamestock.modelo;



import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private int id;
    private String nombre;
    private String documento;
    private String correo;
    private String telefono;
    private List<Factura> historialCompras; // Dependencia con Factura

    // Constructor por defecto
    public Cliente() {
        this.historialCompras = new ArrayList<>();
    }

    // Constructor parametrizado
    public Cliente(int id, String nombre, String documento, String correo, String telefono) {
        this.id = id;
        setNombre(nombre);
        setDocumento(documento);
        setCorreo(correo);
        this.telefono = telefono;
        this.historialCompras = new ArrayList<>();
    }

    // Getters y Setters con validaciones
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) {
        if (documento == null || documento.trim().isEmpty()) {
            throw new IllegalArgumentException("El documento de identidad no puede estar vacío.");
        }
        this.documento = documento;
    }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) {
        if (correo == null || !correo.contains("@") || !correo.contains(".")) {
            throw new IllegalArgumentException("El formato del correo electrónico no es válido.");
        }
        this.correo = correo;
    }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    
    public void registrarCliente() {
        System.out.println("Cliente registrado correctamente: " + this.nombre);
    }

    public List<Factura> consultarHistorialCompras() {
        // Devuelve una copia del historial de facturas del cliente
        return new ArrayList<>(historialCompras);
    }
    
    public void agregarFacturaAlHistorial(Factura factura) {
        if (factura != null) {
            this.historialCompras.add(factura);
        }
    }
}