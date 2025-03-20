/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apirestcontroltienda;

import tienda.modelos.Proveedor;
import tienda.modelos.Libro;
import tienda.dao.DirectorioProveedores;
import tienda.dao.InventarioLibros;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author alumno
 */
public class APIRESTConsoleTienda {
        /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        DirectorioProveedores repo = new DirectorioProveedores();
        String objectID = repo.insert(new Proveedor("Juan", "Montes", "999119911"));
        ArrayList<Proveedor> proveedores = repo.getAll();
        for(Proveedor proveedor : proveedores){
            System.out.println(proveedor.nombre + "-" + proveedor.apellido);
        }
        if (!proveedores.isEmpty()){
            Proveedor firstProveedor = proveedores.get(0);
            firstProveedor.nombre = "Uxue";
           }
        
        
        InventarioLibros repo2 = new InventarioLibros();
        String objectID2 = repo2.insert(new Libro("Quijote", "Cervantes", "99919911", 0));
        ArrayList<Libro> Libros = repo2.getAll();
        for(Libro libro : Libros){
            System.out.println(libro.titulo + "-" + libro.autor + "-" + libro.isbn + "-" + libro.precio);
        }
        if (!proveedores.isEmpty()){
            Libro firstLibro = Libros.get(0);
            firstLibro.titulo = "Pinocho";
           }
        
        
    }
   
}
