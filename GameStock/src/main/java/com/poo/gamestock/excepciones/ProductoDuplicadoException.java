/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.gamestock.excepciones;

/**
 * Se lanza cuando se intenta agregar un codigo que ya existe
 */
public class ProductoDuplicadoException extends GameStockException {

    private String codigo;

    public ProductoDuplicadoException(String codigo) {
        super("Ya existe un producto con el codigo " + codigo + ".");
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}