package tienda.modelos;

public class Libro {
    public String titulo;
    public String autor;
    public String isbn;
    public int precio;
    public String objectId;

    public Libro(String titulo, String autor, String isbn, int precio) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.precio = precio;
    }
}
