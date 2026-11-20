/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.gamestock.modelo;

/**
 *
 * @author PC
 */


public class VideojuegoDigital extends Videojuego {

    // Constructor por defecto
    public VideojuegoDigital() {
        super();
    }

    // Constructor parametrizado
    public VideojuegoDigital(String codigo, String nombre,
            double precio, int stock, String plataforma,
            String genero, String clasificacionEdad) {

        super(codigo, nombre, precio, stock, plataforma,
                genero, "Digital", clasificacionEdad);
    }

    @Override
    public double calcularPrecioFinal() {
        return super.calcularPrecioFinal();
    }
}
