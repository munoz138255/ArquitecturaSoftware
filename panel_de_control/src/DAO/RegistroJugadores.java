package DAO;

import java.util.ArrayList;
import java.util.List;
import modelo.Jugador;

public class RegistroJugadores {

    private final List<Jugador> listaJugadores;

    public RegistroJugadores() {
        this.listaJugadores = new ArrayList<>();
    }

    // CREATE
    public void crearJugador(Jugador jugador) {
        listaJugadores.add(jugador);
    }

    // READ: obtener todos
    public List<Jugador> listarJugadores() {
        return listaJugadores;
    }

    // READ: obtener por nombre
    public Jugador obtenerJugador(String nombre) {
        for (Jugador j : listaJugadores) {
            if (j.getNombre().equalsIgnoreCase(nombre)) {
                return j;
            }
        }
        return null;
    }

    // UPDATE
    public void actualizarJugador(Jugador jugadorActualizado) {
        // Como se maneja en memoria, no es tan complicado:
        // Asegúrate de que "jugadorActualizado" sea el mismo objeto que está en la lista
        // En la vida real, buscaríamos un ID en DB. Aquí se asume que ya es referencia
        // y con setNombre(...) se va actualizando. No se requiere mucho en in-memory.
        // Sin embargo, si quieres forzar, podrías hacer algo como:
        for (int i = 0; i < listaJugadores.size(); i++) {
            Jugador j = listaJugadores.get(i);
            if (j == jugadorActualizado) {
                listaJugadores.set(i, jugadorActualizado);
                break;
            }
        }
    }

    // DELETE
    public void eliminarJugador(Jugador jugador) {
        listaJugadores.remove(jugador);
    }
}
