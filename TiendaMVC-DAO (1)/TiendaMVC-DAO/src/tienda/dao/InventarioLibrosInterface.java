package tienda.dao;

import java.util.ArrayList;
import java.util.List;
import tienda.modelos.Libro;
import tienda.modelos.Proveedor;

interface InventarioLibrosInterface {

    ArrayList<Libro> getAll();

    String insert(Libro libro);
}