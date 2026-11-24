/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.gamestock.excepciones;

/**
 * Excepcion padre 
 */
public class GameStockException extends Exception {

    public GameStockException(String mensaje) {
        super(mensaje);
    }
}