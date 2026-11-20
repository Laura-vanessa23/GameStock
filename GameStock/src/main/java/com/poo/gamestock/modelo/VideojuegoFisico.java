/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.gamestock.modelo;

/**
 *
 * @author PC
 */


public class VideojuegoFisico extends Videojuego {

    // Constructor por defecto
    public VideojuegoFisico() {
        super();
    }

    // Constructor parametrizado
    public VideojuegoFisico(String codigo, String nombre,
            double precio, int stock, String plataforma,
            String genero, String clasificacionEdad) {

        super(codigo, nombre, precio, stock, plataforma,
                genero, "Fisico", clasificacionEdad);
    }

    @Override
    public double calcularPrecioFinal() {
        return super.calcularPrecioFinal();
    }
}
