
package com.poo.gamestock.controlador;

import com.poo.gamestock.modelo.Cliente;
import com.poo.gamestock.modelo.Consola;
import com.poo.gamestock.modelo.Factura;
import com.poo.gamestock.modelo.Inventario;
import com.poo.gamestock.modelo.Producto;
import com.poo.gamestock.modelo.Vendedor;
import com.poo.gamestock.modelo.Videojuego;
import com.poo.gamestock.vista.Vista;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jesús Gudiño
 */
public class Controlador {
     private Vista vista;
    private Inventario inventario;
    private List<Cliente> clientes;
    private List<Vendedor> vendedores;
    private List<Factura> facturas;

    public Controlador(Vista vista) {
        this.vista = vista;
        this.inventario = new Inventario();
        this.clientes = new ArrayList<>();
        this.vendedores = new ArrayList<>();
        this.facturas = new ArrayList<>();
        cargarDatos();
    }

    public void iniciar() {
        int opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.leerEntero("Opcion");
            try {
                switch (opcion) {
                    case 1 -> listarProductos();
                    case 2 -> agregarVideojuego();
                    case 3 -> agregarConsola();
                    case 4 -> buscarProducto();
                    case 5 -> eliminarProducto();
                    case 6 -> actualizarStock();
                    case 7 -> registrarCliente();
                    case 8 -> registrarVenta();
                    case 9 -> verFacturas();
                    case 10 -> historialCliente();
                    case 0 -> vista.mostrarMensaje("Hasta pronto");
                    default -> vista.mostrarMensaje("Opcion no valida");
                }
            } catch (IllegalArgumentException e) {
                vista.mostrarMensaje("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    
        //---1. Listar productos---
    private void listarProductos() {
        if (inventario.listarProductos().isEmpty()) {
            vista.mostrarMensaje("El inventario esta vacio");
        }
        for (Producto p : inventario.listarProductos()) {
            p.mostrarInfo();
            vista.mostrarMensaje("Precio final: $" + (long) p.calcularPrecioFinal());
            vista.mostrarMensaje("");
        }
    }
    
    // ---2. Agregar Videojuego---
     private void agregarVideojuego() {
        String codigo = vista.leerTexto("Codigo");
        String nombre = vista.leerTexto("Nombre");
        double precio = vista.leerDecimal("Precio");
        int stock = vista.leerEntero("Stock");
        String plataforma = vista.leerTexto("Plataforma");
        String genero = vista.leerTexto("Genero");
        String formato = vista.leerTexto("Formato (Fisico o Digital)");
        String clasificacion = vista.leerTexto("Clasificacion de edad");

        Videojuego juego = new Videojuego(codigo, nombre, precio, stock, plataforma,
                genero, formato, clasificacion);
        inventario.agregarProducto(juego);
    }
     
     //3.---Agregar consola---
     private void agregarConsola() {
        String codigo = vista.leerTexto("Codigo");
        String nombre = vista.leerTexto("Nombre");
        double precio = vista.leerDecimal("Precio");
        int stock = vista.leerEntero("Stock");
        String plataforma = vista.leerTexto("Plataforma");
        String modelo = vista.leerTexto("Modelo");
        String almacenamiento = vista.leerTexto("Almacenamiento");

        Consola consola = new Consola(codigo, nombre, precio, stock, plataforma,
                modelo, almacenamiento);
        inventario.agregarProducto(consola);
    }
     
     //4.---Buscar Producto---
     private void buscarProducto() {
        String codigo = vista.leerTexto("Codigo del producto");
        Producto producto = inventario.buscarProducto(codigo);
        if (producto == null) {
            vista.mostrarMensaje("Producto no encontrado");
            return;
        }
        producto.mostrarInfo();
        vista.mostrarMensaje("Precio final: $" + (long) producto.calcularPrecioFinal());
    }
     
     //---5.Eliminar Producto---
     private void eliminarProducto() {
        String codigo = vista.leerTexto("Codigo del producto a eliminar");
        inventario.eliminarProducto(codigo);
    }
    
     //---6.Actualizar Stock
     private void actualizarStock() {
        String codigo = vista.leerTexto("Codigo del producto");
        int nuevoStock = vista.leerEntero("Nuevo stock");
        inventario.actualizarStock(codigo, nuevoStock);
    }
     
     
     
     
    //---7.Registrar Cliente---
    private void registrarCliente() {
        String nombre = vista.leerTexto("Nombre");
        String documento = vista.leerTexto("Documento");
        String correo = vista.leerTexto("Correo");
        String telefono = vista.leerTexto("Telefono");
        if (buscarCliente(documento) != null) {
            vista.mostrarMensaje("Ya existe un cliente con ese documento");
            return;
        }
        Cliente cliente = new Cliente(clientes.size() + 1, nombre, documento, correo, telefono);
        clientes.add(cliente);
        cliente.registrarCliente();
    }
    
    //---8.Registrar Venta---
    private void registrarVenta() {
        Vendedor vendedor = buscarVendedor(vista.leerEntero("Codigo del vendedor"));
        if (vendedor == null) {
            vista.mostrarMensaje("Vendedor no encontrado");
            return;
        }
        Cliente cliente = buscarCliente(vista.leerTexto("Documento del cliente"));
        if (cliente == null) {
            vista.mostrarMensaje("Cliente no encontrado, registrelo primero");
            return;
        }
        Factura factura = vendedor.registrarVenta("F-" + (facturas.size() + 1), cliente);
        vista.mostrarMensaje("Agregue productos (codigo vacio para terminar)");
        while (true) {
            String codigo = vista.leerTexto("Codigo del producto");
            if (codigo.isEmpty()) {
                break;
            }
            Producto producto = inventario.buscarProducto(codigo);
            if (producto == null) {
                vista.mostrarMensaje("Producto no encontrado");
                continue;
            }
            int cantidad = vista.leerEntero("Cantidad (stock: " + producto.getStock() + ")");
            try {
                agregarLinea(factura, producto, cantidad);
            } catch (IllegalArgumentException e) {
                vista.mostrarMensaje("Error: " + e.getMessage());
            }
        }
        if (factura.getDetalles().isEmpty()) {
            vista.mostrarMensaje("Venta cancelada, no se agregaron productos");
            return;
        }
        guardarFactura(factura);
        vista.mostrarMensaje(factura.generarComprobante());
    }
    
    //---9.Ver Facturas
    private void verFacturas() {
        if (facturas.isEmpty()) {
            vista.mostrarMensaje("Todavia no hay facturas");
            return;
        }
        for (Factura f : facturas) {
            vista.mostrarMensaje(f.generarComprobante());
            vista.mostrarMensaje("");
        }
    }
    
    //---10.Historial del Cliente---
    private void historialCliente() {
        Cliente cliente = buscarCliente(vista.leerTexto("Documento del cliente"));
        if (cliente == null) {
            vista.mostrarMensaje("Cliente no encontrado");
            return;
        }
        List<Factura> historial = cliente.consultarHistorialCompras();
        if (historial.isEmpty()) {
            vista.mostrarMensaje("El cliente no tiene compras");
        }
        for (Factura f : historial) {
            vista.mostrarMensaje(f.generarComprobante());
            vista.mostrarMensaje("");
        }
    }

    private void agregarLinea(Factura factura, Producto producto, int cantidad) {
        factura.agregarDetalle(producto, cantidad);
        producto.actualizarStock(-cantidad);
    }

    private void guardarFactura(Factura factura) {
        facturas.add(factura);
        factura.getCliente().agregarFacturaAlHistorial(factura);
    }

    private Cliente buscarCliente(String documento) {
        for (Cliente c : clientes) {
            if (c.getDocumento().equals(documento)) {
                return c;
            }
        }
        return null;
    }

    private Vendedor buscarVendedor(int id) {
        for (Vendedor v : vendedores) {
            if (v.getIdEmpleado() == id) {
                return v;
            }
        }
        return null;
    }

    private void cargarDatos() {
        inventario.agregarProducto(new Videojuego("VJ001", "God of War Ragnarok", 250000, 10, "PlayStation 5", "Accion", "Fisico", "M"));
        inventario.agregarProducto(new Videojuego("VJ002", "Forza Horizon 5", 200000, 8, "Xbox Series X", "Carreras", "Digital", "E"));
        inventario.agregarProducto(new Consola("CS001", "PlayStation 5 Slim", 2300000, 5, "PlayStation 5", "Slim", "1TB"));
        inventario.agregarProducto(new Consola("CS002", "Xbox Series S", 1500000, 4, "Xbox Series X", "Series S", "512GB"));

        Vendedor v1 = new Vendedor(1, "Carlos Perez");
        Vendedor v2 = new Vendedor(2, "Maria Lopez");
        vendedores.add(v1);
        vendedores.add(v2);

        Cliente c1 = new Cliente(1, "Ana Torres", "1001", "ana@correo.com", "3001234567");
        Cliente c2 = new Cliente(2, "Luis Gomez", "1002", "luis@correo.com", "3109876543");
        clientes.add(c1);
        clientes.add(c2);

        Factura f1 = v1.registrarVenta("F-1", c1);
        agregarLinea(f1, inventario.buscarProducto("VJ001"), 1);
        agregarLinea(f1, inventario.buscarProducto("CS001"), 1);
        guardarFactura(f1);

        Factura f2 = v2.registrarVenta("F-2", c2);
        agregarLinea(f2, inventario.buscarProducto("VJ002"), 2);
        guardarFactura(f2);

        Factura f3 = v1.registrarVenta("F-3", c2);
        agregarLinea(f3, inventario.buscarProducto("CS002"), 1);
        agregarLinea(f3, inventario.buscarProducto("VJ001"), 2);
        guardarFactura(f3);
    }
    
}
