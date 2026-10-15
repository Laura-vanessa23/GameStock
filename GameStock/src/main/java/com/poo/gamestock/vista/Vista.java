
package com.poo.gamestock.vista;
import java.util.Scanner;
/**
 *
 * @author Jesús Gudiño
 */
public class Vista {
    
    private Scanner scanner = new Scanner(System.in);
 
    public void mostrarMenu() {
        System.out.println();
        System.out.println("===== GAMESTOCK POS =====");
        System.out.println("1. Listar productos");
        System.out.println("2. Agregar videojuego");
        System.out.println("3. Agregar consola");
        System.out.println("4. Buscar producto");
        System.out.println("5. Eliminar producto");
        System.out.println("6. Actualizar stock");
        System.out.println("7. Registrar cliente");
        System.out.println("8. Registrar venta");
        System.out.println("9. Ver todas las facturas");
        System.out.println("10. Historial de un cliente");
        System.out.println("0. Salir");
    }
 
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
 
    public String leerTexto(String pregunta) {
        System.out.print(pregunta + ": ");
        return scanner.nextLine();
    }
 
    public int leerEntero(String pregunta) {
        System.out.print(pregunta + ": ");
        int numero = scanner.nextInt();
        scanner.nextLine();
        return numero;
    }
 
    public double leerDecimal(String pregunta) {
        System.out.print(pregunta + ": ");
        double numero = scanner.nextDouble();
        scanner.nextLine();
        return numero;
    }
    
}
