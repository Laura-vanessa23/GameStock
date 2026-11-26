/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.gamestock.modelo;

/**
 * Define los objetos que se pueden guardar en un archivo de texto
 * Cada objeto se convierte en una sola linea con los datos separados por punto y coma
 */
public interface Persistible {

    String SEPARADOR = ";";

    // abstracto: cada clase dice como se convierte en una linea
    String aLinea();

    // default: une los campos con el separador
    // si algun campo trae el separador se cambia por un espacio para no romper la linea
    default String unir(String... campos) {
        StringBuilder linea = new StringBuilder();
        for (int i = 0; i < campos.length; i++) {
            if (i > 0) {
                linea.append(SEPARADOR);
            }
            linea.append(campos[i].replace(SEPARADOR, " "));
        }
        return linea.toString();
    }
}