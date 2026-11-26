/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poo.gamestock.modelo;

import com.poo.gamestock.excepciones.ArchivoException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Se encarga de leer y escribir los archivos del sistema
 * Todo se guarda en una carpeta como archivos de texto
 */
public class GestorArchivos {

    private static final String ARCHIVO_PRODUCTOS = "inventario.csv";
    private static final String ARCHIVO_CLIENTES = "clientes.csv";
    private static final String ARCHIVO_FACTURAS = "facturas.csv";
    private static final String ARCHIVO_COMPROBANTES = "comprobantes.txt";

    private String carpeta;

    public GestorArchivos(String carpeta) {
        if (carpeta == null || carpeta.trim().isEmpty()) {
            throw new IllegalArgumentException("La carpeta no puede estar vacia.");
        }
        this.carpeta = carpeta.trim();
    }

    // Dice si ya existen los tres archivos guardados
    public boolean existenDatos() {
        return existe(ARCHIVO_PRODUCTOS)
                && existe(ARCHIVO_CLIENTES)
                && existe(ARCHIVO_FACTURAS);
    }

    private boolean existe(String nombreArchivo) {
        return new File(carpeta, nombreArchivo).exists();
    }

    //  GUARDAR

    public void guardarProductos(List<Producto> productos) throws ArchivoException {
        guardarLista(ARCHIVO_PRODUCTOS, productos);
    }

    public void guardarClientes(List<Cliente> clientes) throws ArchivoException {
        guardarLista(ARCHIVO_CLIENTES, clientes);
    }

    public void guardarFacturas(List<Factura> facturas) throws ArchivoException {
        guardarLista(ARCHIVO_FACTURAS, facturas);

        // Ademas se guarda un archivo con los comprobantes para leerlos facil
        List<String> comprobantes = new ArrayList<>();
        for (Factura factura : facturas) {
            comprobantes.add(factura.generarComprobante());
            comprobantes.add("");
        }
        escribirLineas(ARCHIVO_COMPROBANTES, comprobantes);
    }

    // Sirve para cualquier lista de objetos Persistible
    // cada objeto se convierte en una linea con aLinea()
    private void guardarLista(String nombreArchivo, List<? extends Persistible> datos)
            throws ArchivoException {
        List<String> lineas = new ArrayList<>();
        for (Persistible dato : datos) {
            lineas.add(dato.aLinea());
        }
        escribirLineas(nombreArchivo, lineas);
    }

    // CARGAR 

    public List<Producto> cargarProductos() throws ArchivoException {
        List<String> lineas = leerLineas(ARCHIVO_PRODUCTOS);
        List<Producto> productos = new ArrayList<>();
        int numeroLinea = 0;

        for (String linea : lineas) {
            numeroLinea++;
            if (linea.trim().isEmpty()) {
                continue;
            }
            try {
                productos.add(convertirProducto(linea));
            } catch (NumberFormatException e) {
                throw lineaInvalida(ARCHIVO_PRODUCTOS, numeroLinea, "hay un numero mal escrito");
            } catch (IllegalArgumentException e) {
                throw lineaInvalida(ARCHIVO_PRODUCTOS, numeroLinea, e.getMessage());
            }
        }
        return productos;
    }

    public List<Cliente> cargarClientes() throws ArchivoException {
        List<String> lineas = leerLineas(ARCHIVO_CLIENTES);
        List<Cliente> clientes = new ArrayList<>();
        int numeroLinea = 0;

        for (String linea : lineas) {
            numeroLinea++;
            if (linea.trim().isEmpty()) {
                continue;
            }
            try {
                String[] campos = linea.split(Persistible.SEPARADOR, -1);
                exigirCampos(campos, 5);
                clientes.add(new Cliente(Integer.parseInt(campos[0]), campos[1],
                        campos[2], campos[3], campos[4]));
            } catch (NumberFormatException e) {
                throw lineaInvalida(ARCHIVO_CLIENTES, numeroLinea, "hay un numero mal escrito");
            } catch (IllegalArgumentException e) {
                throw lineaInvalida(ARCHIVO_CLIENTES, numeroLinea, e.getMessage());
            }
        }
        return clientes;
    }

    // Para armar las facturas se necesitan los clientes los vendedores y los productos
    public List<Factura> cargarFacturas(List<Cliente> clientes,
            List<Vendedor> vendedores, Inventario inventario) throws ArchivoException {
        List<String> lineas = leerLineas(ARCHIVO_FACTURAS);
        List<Factura> facturas = new ArrayList<>();
        int numeroLinea = 0;

        for (String linea : lineas) {
            numeroLinea++;
            if (linea.trim().isEmpty()) {
                continue;
            }
            try {
                facturas.add(convertirFactura(linea, clientes, vendedores, inventario));
            } catch (NumberFormatException e) {
                throw lineaInvalida(ARCHIVO_FACTURAS, numeroLinea, "hay un numero mal escrito");
            } catch (DateTimeParseException e) {
                throw lineaInvalida(ARCHIVO_FACTURAS, numeroLinea, "la fecha esta mal escrita");
            } catch (IllegalArgumentException e) {
                throw lineaInvalida(ARCHIVO_FACTURAS, numeroLinea, e.getMessage());
            }
        }
        return facturas;
    }


    // La primera palabra de la linea dice que tipo de producto es
    private Producto convertirProducto(String linea) {
        String[] c = linea.split(Persistible.SEPARADOR, -1);
        String tipo = c[0];

        if (tipo.equals("FISICO")) {
            exigirCampos(c, 9);
            return new VideojuegoFisico(c[1], c[2], Double.parseDouble(c[3]),
                    Integer.parseInt(c[4]), c[5], c[6], c[7], c[8]);
        }
        if (tipo.equals("DIGITAL")) {
            exigirCampos(c, 9);
            return new VideojuegoDigital(c[1], c[2], Double.parseDouble(c[3]),
                    Integer.parseInt(c[4]), c[5], c[6], c[7], Double.parseDouble(c[8]));
        }
        if (tipo.equals("CONSOLA")) {
            exigirCampos(c, 8);
            return new Consola(c[1], c[2], Double.parseDouble(c[3]),
                    Integer.parseInt(c[4]), c[5], c[6], c[7]);
        }
        throw new IllegalArgumentException("Tipo de producto desconocido " + tipo);
    }

    private Factura convertirFactura(String linea, List<Cliente> clientes,
            List<Vendedor> vendedores, Inventario inventario) {
        String[] c = linea.split(Persistible.SEPARADOR, -1);
        exigirCampos(c, 5);

        Cliente cliente = null;
        for (Cliente posible : clientes) {
            if (posible.getDocumento().equals(c[2])) {
                cliente = posible;
            }
        }
        if (cliente == null) {
            throw new IllegalArgumentException("No existe el cliente con documento " + c[2]);
        }

        int idVendedor = Integer.parseInt(c[3]);
        Vendedor vendedor = null;
        for (Vendedor posible : vendedores) {
            if (posible.getIdEmpleado() == idVendedor) {
                vendedor = posible;
            }
        }
        if (vendedor == null) {
            throw new IllegalArgumentException("No existe el vendedor " + idVendedor);
        }

        Factura factura = new Factura(c[0], LocalDate.parse(c[1]), cliente, vendedor);

        // Cada detalle es codigo cantidad descuento precio
        for (String textoDetalle : c[4].split("\\|")) {
            String[] d = textoDetalle.split(":", -1);
            exigirCampos(d, 4);

            Producto producto = inventario.buscarProducto(d[0]);
            if (producto == null) {
                throw new IllegalArgumentException("No existe el producto " + d[0]);
            }
            factura.agregarDetalle(new DetalleFactura(producto, Integer.parseInt(d[1]),
                    Double.parseDouble(d[2]), Double.parseDouble(d[3])));
        }
        return factura;
    }

    // Arma el error que dice en que archivo y en que linea esta el problema
    private ArchivoException lineaInvalida(String nombreArchivo, int numeroLinea, String motivo) {
        return new ArchivoException("Linea " + numeroLinea + " invalida en "
                + nombreArchivo + ": " + motivo);
    }

    private void exigirCampos(String[] campos, int cantidadEsperada) {
        if (campos.length != cantidadEsperada) {
            throw new IllegalArgumentException("Se esperaban " + cantidadEsperada
                    + " campos y hay " + campos.length);
        }
    }

    //LEER Y ESCRIBIR 

    private void escribirLineas(String nombreArchivo, List<String> lineas)
            throws ArchivoException {
        File carpetaDatos = new File(carpeta);
        if (!carpetaDatos.exists()) {
            carpetaDatos.mkdirs();
        }

        BufferedWriter escritor = null;
        try {
            escritor = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(new File(carpetaDatos, nombreArchivo)),
                    StandardCharsets.UTF_8));
            for (String linea : lineas) {
                escritor.write(linea);
                escritor.newLine();
            }
        } catch (IOException e) {
            throw new ArchivoException("No se pudo escribir el archivo " + nombreArchivo);
        } finally {
            // finally se ejecuta siempre y asi el archivo queda cerrado
            cerrar(escritor);
        }
    }

    private List<String> leerLineas(String nombreArchivo) throws ArchivoException {
        File archivo = new File(carpeta, nombreArchivo);
        if (!archivo.exists()) {
            throw new ArchivoException("No existe el archivo " + nombreArchivo
                    + ". Use la opcion 12 para guardar primero.");
        }

        List<String> lineas = new ArrayList<>();
        BufferedReader lector = null;
        try {
            lector = new BufferedReader(new InputStreamReader(
                    new FileInputStream(archivo), StandardCharsets.UTF_8));
            String linea;
            while ((linea = lector.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            throw new ArchivoException("No se pudo leer el archivo " + nombreArchivo);
        } finally {
            cerrar(lector);
        }
        return lineas;
    }

    private void cerrar(Closeable flujo) {
        if (flujo != null) {
            try {
                flujo.close();
            } catch (IOException e) {
                System.out.println("No se pudo cerrar el archivo");
            }
        }
    }
}