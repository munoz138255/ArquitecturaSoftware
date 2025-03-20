package tienda.modelos;

import java.util.ArrayList;
import java.util.List;
import tienda.modelos.Libro;

public class Proveedor {
    public String nombre;
    public String contacto;
    //public List<Libro> librosDisponibles;
    public String objectId;
    public String apellido;

    public Proveedor(String nombre, String apellido, String contacto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.contacto = contacto;
       // this.librosDisponibles = new ArrayList<>();
    }

}