/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.gamestock.excepciones;

/**
 * Se lanza cuando se busca un codigo que no existe en el inventario
 */
public class ProductoNoEncontradoException extends GameStockException {

    private String codigo;

    public ProductoNoEncontradoException(String codigo) {
        super("No existe un producto con el codigo " + codigo + ".");
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}