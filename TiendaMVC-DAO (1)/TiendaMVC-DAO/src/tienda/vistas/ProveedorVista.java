package tienda.vistas;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class ProveedorVista extends JFrame {
    private String nombre;
    private String contacto;
    private JTextArea proveedorArea;

    public ProveedorVista() {
        setTitle("Proveedor");
        setSize(300, 300);
        setLayout(new BorderLayout());
        // Panel superior para selección de libros
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new FlowLayout());
 
        proveedorArea = new JTextArea();
        proveedorArea.setEditable(false);
        add(new JScrollPane(proveedorArea), BorderLayout.CENTER);
    
        add(panelSuperior, BorderLayout.NORTH);
      }
    
     public JTextArea getProveedorArea() {
        return proveedorArea;
    }

}