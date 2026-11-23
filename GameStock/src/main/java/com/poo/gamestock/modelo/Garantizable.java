/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poo.gamestock.modelo;

import java.time.LocalDate;

/**
 *
 * @author PC
 */
public interface Garantizable {
    int getMesesGarantia();

    default String describirGarantia() {
        return "Garantia de " + getMesesGarantia() + " meses";
    }

    default boolean tieneGarantiaVigente(LocalDate fechaCompra) {
        if (fechaCompra == null) {
            throw new IllegalArgumentException("La fecha de compra no puede ser nula.");
        }
        return LocalDate.now().isBefore(fechaCompra.plusMonths(getMesesGarantia()));
    }
}
