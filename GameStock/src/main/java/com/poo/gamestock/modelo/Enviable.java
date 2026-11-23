/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poo.gamestock.modelo;

/**
 *
 * @author PC
 */
public interface Enviable {
    double COSTO_ENVIO_BASE = 12000;
    double COSTO_POR_KG = 3000;
    double MINIMO_ENVIO_GRATIS = 300000;

    double getPesoKg();

    default double calcularCostoEnvio() {
        return COSTO_ENVIO_BASE + getPesoKg() * COSTO_POR_KG;
    }

    default boolean aplicaEnvioGratis(double valorCompra) {
        return valorCompra >= MINIMO_ENVIO_GRATIS;
    }
}
