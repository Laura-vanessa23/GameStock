/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.gamestock.modelo;






import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona {

    private int id;
    private String documento;
    private String correo;
    private String telefono;
    private List<Factura> historialCompras;

    public Cliente() {
        super();
        historialCompras = new ArrayList<>();
    }

    public Cliente(int id, String nombre, String documento,
                   String correo, String telefono) {

        super(nombre);
        setId(id);
        setDocumento(documento);
        setCorreo(correo);
        setTelefono(telefono);
        historialCompras = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID debe ser mayor a cero.");
        }
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        if (documento == null || documento.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El documento no puede estar vacio.");
        }
        this.documento = documento.trim();
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if (correo == null ||
                !correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new IllegalArgumentException(
                    "El correo no es valido.");
        }
        this.correo = correo.trim();
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El telefono no puede estar vacio.");
        }
        this.telefono = telefono.trim();
    }

    public void registrarCliente() {
        System.out.println(
                "Cliente registrado correctamente: " + getNombre());
    }

    public List<Factura> consultarHistorialCompras() {
        return new ArrayList<>(historialCompras);
    }

    // Asociacion con Factura
    public void agregarFacturaAlHistorial(Factura factura) {
        if (factura == null) {
            throw new IllegalArgumentException(
                    "La factura no puede ser nula.");
        }

        if (!historialCompras.contains(factura)) {
            historialCompras.add(factura);
        }
    }
}
