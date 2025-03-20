
package tienda.dao;

import java.util.ArrayList;
import java.util.List;
import tienda.modelos.ItemCarrito;
import tienda.modelos.Libro;

public class Carrito {
    private List<ItemCarrito> items;

    public Carrito() {
        items = new ArrayList<>();
    }

    public void agregarLibro(Libro libro, int cantidad) {
        items.add(new ItemCarrito(libro, cantidad));
    }

    public List<ItemCarrito> getItems() {
        return items;
    }
}