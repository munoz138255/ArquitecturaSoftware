package tienda.vistas;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DirectorioProveedoresVista extends JFrame  {
   // private JList<String> listaProveedore;
    private JButton verProveedorButton;
    private JComboBox<String> listaProveedoresComboBox;

    public DirectorioProveedoresVista() {
        setTitle("Lista Proveedores");
        setSize(400, 300);
        setLayout(new BorderLayout());

       // librosInventarioComboBox
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new FlowLayout());
        listaProveedoresComboBox = new JComboBox<>();
        panelSuperior.add(listaProveedoresComboBox);
        
        add(panelSuperior, BorderLayout.NORTH);
        
        verProveedorButton = new JButton("Mostrar Datos Proveedor");
        add(verProveedorButton, BorderLayout.SOUTH);
    }

 public JComboBox<String> getProveedoresComboBox() {
        return listaProveedoresComboBox;
    }
       
 public JButton getProveedorButton() {
        return verProveedorButton;
    }
 
public void addProveedor(String proveedor) {
       listaProveedoresComboBox.addItem(proveedor);
    }
            
}