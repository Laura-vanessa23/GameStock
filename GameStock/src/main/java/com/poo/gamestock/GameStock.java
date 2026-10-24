    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.poo.gamestock;

import com.poo.gamestock.vista.Vista;
import com.poo.gamestock.controlador.Controlador;

/**MAIN
 *
 * @author Jesus Gudiño
 */
public class GameStock {

    public static void main(String[] args) {
     Vista vista = new Vista();
    Controlador controlador = new Controlador(vista);
    controlador.iniciar();
    }
}
