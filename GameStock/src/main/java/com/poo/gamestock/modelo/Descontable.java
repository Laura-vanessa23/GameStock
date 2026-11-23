/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poo.gamestock.modelo;

/**
 *
 * @author PC
 */
public interface Descontable {
    double MAX_DESCUENTO = 50;

    double calcularPrecioFinal(double descuentoPorcentaje);

    default double calcularAhorro(double descuentoPorcentaje) {
        return calcularPrecioFinal(0) - calcularPrecioFinal(descuentoPorcentaje);
    }

    default boolean descuentoPermitido(double descuentoPorcentaje) {
        return descuentoPorcentaje >= 0 && descuentoPorcentaje <= MAX_DESCUENTO;
    }
}
