/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.gamestock.excepciones;

/**
 * Se lanza cuando algo sale mal al leer o escribir un archivo
 * Por ejemplo el archivo no existe o una linea esta mal escrita
 */
public class ArchivoException extends GameStockException {

    public ArchivoException(String mensaje) {
        super(mensaje);
    }
}