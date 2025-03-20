package tienda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import tienda.modelos.ItemCarrito;
import tienda.modelos.Libro;
import tienda.modelos.Proveedor;
import tienda.dao.Carrito;
import tienda.dao.InventarioLibros;
import tienda.dao.DirectorioProveedores;
import tienda.vistas.TiendaVista;
import tienda.vistas.InventarioLibrosVista;
import tienda.vistas.DirectorioProveedoresVista;
import tienda.vistas.ProveedorVista;

public class TiendaControlador {

    private InventarioLibros inventario;
    private DirectorioProveedores directorio;
    private Carrito carrito;

    private TiendaVista vista;
    private InventarioLibrosVista inventarioVista;
    private DirectorioProveedoresVista listaProveedoresVista;
    private ProveedorVista proveedorVista;

    public TiendaControlador() {

        // Crear el modelo
        inventario = new InventarioLibros();
        directorio = new DirectorioProveedores();
        carrito = new Carrito();

        // Inicializar con algunos libros
        Libro libro1 = new Libro("Cien Años de Soledad", "Gabriel García Márquez", "123456789", 20.50);
        Libro libro2 = new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", "987654321", 15.75);
        Libro libro3 = new Libro("El Principito", "Antoine de Saint-Exupéry", "456789123", 10.00);

        inventario.agregarLibro(libro1);
        inventario.agregarLibro(libro2);
        inventario.agregarLibro(libro3);

        // Inicializar con algunos proveedores
        Proveedor proveedor1 = new Proveedor("Distribuidora Literaria", "contacto@literaria.com");
        Proveedor proveedor2 = new Proveedor("Editorial Universal", "ventas@universal.com");

        proveedor1.agregarLibro(libro1);
        proveedor1.agregarLibro(libro3);
        proveedor2.agregarLibro(libro2);

        directorio.agregarProveedor(proveedor1);
        directorio.agregarProveedor(proveedor2);

        // Crear la vista
        vista = new TiendaVista();

        // Inicializar combo box con los títulos de los libros
        for (Libro libro : inventario.getLibros()) {
            vista.getLibrosComboBox().addItem(libro.getTitulo());
        }

        // Agregar listener al botón de agregar al carrito
        vista.getAgregarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarLibroAlCarrito();
            }
        });

        vista.getProveedoresButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarProveedores();
            }
        });

        vista.getInventarioButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarInventario();
            }
        });

        vista.setVisible(true);

    }

    private void agregarLibroAlCarrito() {
        int selectedIndex = vista.getLibrosComboBox().getSelectedIndex();
        if (selectedIndex >= 0) {
            Libro libroSeleccionado = inventario.getLibros().get(selectedIndex);
            try {
                int cantidad = Integer.parseInt(vista.getCantidadField().getText());
                if (cantidad > 0) {
                    carrito.agregarLibro(libroSeleccionado, cantidad);
                    actualizarCarrito();
                } else {
                    JOptionPane.showMessageDialog(vista, "Ingrese una cantidad válida.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void actualizarCarrito() {
        StringBuilder contenidoCarrito = new StringBuilder();
        for (ItemCarrito item : carrito.getItems()) {
            contenidoCarrito.append(item).append("\n");
        }
        vista.getCarritoArea().setText(contenidoCarrito.toString());
    }

    private void mostrarInventario() {
        inventarioVista = new InventarioLibrosVista();
        inventarioVista.actualizarInventario(inventario.getLibros());
        inventarioVista.setVisible(true);
    }

    private void mostrarProveedores() {

        listaProveedoresVista = new DirectorioProveedoresVista();

        for (Proveedor proveedor : directorio.getProveedores() ) {
            listaProveedoresVista.getProveedoresComboBox().addItem(proveedor.getNombre());
        }
        // Agregar listener al botón de agregar al carrito
        listaProveedoresVista.getProveedorButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarProveedor();
            }
        });

        listaProveedoresVista.setVisible(true);
    }

    private void mostrarProveedor() {

        int selectedIndex = listaProveedoresVista.getProveedoresComboBox().getSelectedIndex();
        if (selectedIndex >= 0) {
            Proveedor proveedorSeleccionado = directorio.getProveedor(selectedIndex);
            try {
                proveedorVista = new ProveedorVista();
                proveedorVista.getProveedorArea().setText(proveedorSeleccionado.toString());

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "Elija un proveedor válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        proveedorVista.setVisible(true);
    }
}
