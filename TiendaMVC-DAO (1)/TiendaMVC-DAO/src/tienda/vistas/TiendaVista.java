package tienda.vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

// Vista: Interfaz gráfica con Swing
public class TiendaVista extends JFrame {
    private JComboBox<String> librosComboBox;
    private JTextField cantidadField;
    private JButton agregarButton;
    private JTextArea carritoArea;
    private JButton proveedoresButton;
    private JButton inventarioButton;
    
    public TiendaVista() {
        setTitle("Tienda de Libros");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior para selección de libros
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new FlowLayout());
        librosComboBox = new JComboBox<>();
        cantidadField = new JTextField(5);
        agregarButton = new JButton("Agregar al Carrito");
        panelSuperior.add(new JLabel("Libro:"));
        panelSuperior.add(librosComboBox);
        panelSuperior.add(new JLabel("Cantidad:"));
        panelSuperior.add(cantidadField);
        panelSuperior.add(agregarButton);
        add(panelSuperior, BorderLayout.NORTH);

        // Área de texto para mostrar el carrito
        carritoArea = new JTextArea();
        carritoArea.setEditable(false);
        add(new JScrollPane(carritoArea), BorderLayout.CENTER);

        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout());
        // Botón para mostrar proveedores
        proveedoresButton = new JButton("Mostrar Proveedores");
        panelInferior.add(proveedoresButton, BorderLayout.CENTER);
        inventarioButton = new JButton("Mostrar Inventario");
        panelInferior.add(inventarioButton, BorderLayout.SOUTH); 
        add(panelInferior, BorderLayout.SOUTH);
       
    }
                     
    public JComboBox<String> getLibrosComboBox() {
        return librosComboBox;
    }

    public JTextField getCantidadField() {
        return cantidadField;
    }

    public JTextArea getCarritoArea() {
        return carritoArea;
    }
    
    public JButton getAgregarButton() {
        return agregarButton;
    }

    public JButton getProveedoresButton() {
        return proveedoresButton;
    }
 
    public JButton getInventarioButton() {
        return inventarioButton;
    }
}
