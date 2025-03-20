package tienda.vistas;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import tienda.modelos.Libro;

public class InventarioLibrosVista extends JFrame {
    private JList<String> listaLibros;
   

    public InventarioLibrosVista() {
        setTitle("Inventario de Libros");
        setSize(300, 300);

       // librosInventarioComboBox
        listaLibros = new JList<>();
        add(new JScrollPane(listaLibros), BorderLayout.CENTER);
        
    }

    public void actualizarInventario(List<Libro> libros) {
        listaLibros.setListData(libros.stream().map(Libro::toString).toArray(String[]::new));
    }
    

            
}