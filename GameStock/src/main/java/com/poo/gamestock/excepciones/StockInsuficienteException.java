/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.gamestock.excepciones;

/**
 * Se lanza cuando se pide mas cantidad de la que hay en stock
 */
public class StockInsuficienteException extends GameStockException {

    private int stockDisponible;
    private int cantidadPedida;

    public StockInsuficienteException(int stockDisponible, int cantidadPedida) {
        super("Stock insuficiente. Disponible: " + stockDisponible
                + ". Pedido: " + cantidadPedida + ".");
        this.stockDisponible = stockDisponible;
        this.cantidadPedida = cantidadPedida;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }

    public int getCantidadPedida() {
        return cantidadPedida;
    }
}