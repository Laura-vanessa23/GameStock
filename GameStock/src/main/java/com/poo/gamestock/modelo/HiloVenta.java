/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.gamestock.modelo;

import com.poo.gamestock.excepciones.StockInsuficienteException;

/**
Adelanto taller en clase concurrencia
 */
public class HiloVenta extends Thread {

    private Producto producto;
    private int cantidad;
    private boolean exito;

    // El nombre del vendedor se usa como nombre del hilo
    public HiloVenta(String nombreVendedor, Producto producto, int cantidad) {
        super(nombreVendedor);
        this.producto = producto;
        this.cantidad = cantidad;
        this.exito = false;
    }

    // Este metodo es lo que hace el hilo cuando arranca con start()
    @Override
    public void run() {
        System.out.println(getName() + " empieza a procesar la venta");
        try {
            // Pausa al azar para que los hilos se mezclen entre ellos
            Thread.sleep((long) (Math.random() * 500));

            producto.actualizarStock(-cantidad);
            exito = true;
            System.out.println(getName() + " compro " + cantidad
                    + " unidad(es) de " + producto.getNombre());
        } catch (StockInsuficienteException e) {
            System.out.println(getName() + " no pudo comprar: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println(getName() + " fue interrumpido");
        }
    }

    // Dice si la compra de este hilo salio bien
    public boolean isExito() {
        return exito;
    }
}