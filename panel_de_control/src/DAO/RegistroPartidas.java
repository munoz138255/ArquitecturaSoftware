package DAO;

import java.util.ArrayList;
import java.util.List;
import modelo.Partida;

public class RegistroPartidas {
    
    private final List<Partida> listaPartidas;

    public RegistroPartidas() {
        this.listaPartidas = new ArrayList<>();
    }

    // CREATE
    public void crearPartida(Partida partida) {
        listaPartidas.add(partida);
    }

    // READ: obtener todas
    public List<Partida> listarPartidas() {
        return listaPartidas;
    }

    // READ: obtener por ID
    public Partida obtenerPartidaPorID(int id) {
        for (Partida p : listaPartidas) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    // UPDATE
    public void actualizarPartida(Partida partidaActualizada) {
        // Similar lógica a la de jugadores:
        for (int i = 0; i < listaPartidas.size(); i++) {
            Partida p = listaPartidas.get(i);
            if (p == partidaActualizada) {
                listaPartidas.set(i, partidaActualizada);
                break;
            }
        }
    }

    // DELETE
    public void eliminarPartida(Partida partida) {
        listaPartidas.remove(partida);
    }
}
