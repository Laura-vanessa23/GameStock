/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.poo.gamestock.modelo;

/**
 *
 * @author PC
 */
public interface Descargable {
    double VELOCIDAD_PROMEDIO_MBPS = 100;

    double getTamanioGB();

    String generarCodigoDescarga();

    default double calcularTiempoDescargaMinutos(double velocidadMbps) {
        if (velocidadMbps <= 0) {
            throw new IllegalArgumentException("La velocidad debe ser mayor a 0.");
        }
        return (getTamanioGB() * 1024 * 8) / velocidadMbps / 60;
    }

    default double calcularTiempoDescargaMinutos() {
        return calcularTiempoDescargaMinutos(VELOCIDAD_PROMEDIO_MBPS);
    }
}
