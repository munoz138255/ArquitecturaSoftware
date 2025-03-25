package PanelControlJava;

import DAO.RegistroJugadores;
import DAO.RegistroPartidas;
import java.util.ArrayList;
import java.util.List;
import modelo.Jugador;
import modelo.Partida;
import modelo.Reto;
import modelo.Ronda;

public class PanelControlController {

    private final RegistroJugadores jugadorDAO;
    private final RegistroPartidas partidaDAO;
    private final PanelControlView view;
    
    // Tres retos "hard-coded"
    private final Reto[] retosDisponibles = {
        new Reto("Reto 1", "Descripción Reto 1", "Fácil", "ImagenFutura1"),
        new Reto("Reto 2", "Descripción Reto 2", "Medio", "ImagenFutura2"),
        new Reto("Reto 3", "Descripción Reto 3", "Difícil", "ImagenFutura3")
    };

    public PanelControlController(
            RegistroJugadores jugadorDAO,
            RegistroPartidas partidaDAO,
            PanelControlView view
    ) {
        this.jugadorDAO = jugadorDAO;
        this.partidaDAO = partidaDAO;
        this.view = view;
    }

    public void iniciar() {
        int opcion;
        do {
            view.mostrarMenu();
            opcion = view.leerEntero("Selecciona una opción:");
            ejecutarOpcion(opcion);
        } while (opcion != 0);
    }

    private void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1: // Crear Jugador
                crearJugador();
                break;
            case 2: // Listar Jugadores
                listarJugadores();
                break;
            case 3: // Editar Jugador
                editarJugador();
                break;
            case 4: // Borrar Jugador
                borrarJugador();
                break;
            case 5: // Crear Partida
                crearPartida();
                break;
            case 6: // Listar Partidas
                listarPartidas();
                break;
            case 7: // Editar Partida
                editarPartida();
                break;
            case 8: // Borrar Partida
                borrarPartida();
                break;
            case 0:
                view.mostrarMensaje("Saliendo...");
                break;
            default:
                view.mostrarMensaje("Opción inválida.");
        }
    }

    // -------------------
    // CRUD Jugador
    // -------------------
    
    // 1) CREAR JUGADOR
    private void crearJugador() {
        String nombre = view.leerString("Nombre del jugador:");
        Jugador jugador = new Jugador(nombre);
        jugadorDAO.crearJugador(jugador);
        view.mostrarMensaje("Jugador creado correctamente.");
    }
    
    // 2) LISTAR JUGADORES
    private void listarJugadores() {
        List<Jugador> jugadores = jugadorDAO.listarJugadores();
        view.listarJugadores(jugadores);
    }
    
    // 3) EDITAR JUGADOR
    private void editarJugador() {
        listarJugadores();
        
        if(jugadorDAO.listarJugadores().isEmpty()){
            return;
        }
        String nombreActual = view.leerString("Ingresa el nombre del jugador a editar:");
        
        // Localizamos al jugador
        Jugador jugadorEncontrado = jugadorDAO.obtenerJugador(nombreActual);
        if (jugadorEncontrado == null) {
            view.mostrarMensaje("No se encontró un jugador con ese nombre.");
            return;
        }

        String nuevoNombre = view.leerString("Ingresa el nuevo nombre del jugador:");
        
        // Editamos (ejemplo: solo modificamos el nombre)
        jugadorEncontrado.setNombre(nuevoNombre);

        // Se actualiza en "in memory". En un DAO real (BD) se haría un UPDATE en DB.
        jugadorDAO.actualizarJugador(jugadorEncontrado);
        view.mostrarMensaje("Jugador actualizado correctamente.");
    }

    // 4) BORRAR JUGADOR
    private void borrarJugador() {
        listarJugadores();
        if(jugadorDAO.listarJugadores().isEmpty()){
            return;
        }
        String nombreAEliminar = view.leerString("Ingresa el nombre del jugador a borrar:");
        
        // Localizamos al jugador
        Jugador jugadorEncontrado = jugadorDAO.obtenerJugador(nombreAEliminar);
        if (jugadorEncontrado == null) {
            view.mostrarMensaje("No se encontró un jugador con ese nombre.");
            return;
        }

        // Borramos
        jugadorDAO.eliminarJugador(jugadorEncontrado);
        view.mostrarMensaje("Jugador eliminado correctamente.");
    }

    // -------------------
    // CRUD Partida
    // -------------------
    
    // 5) CREAR PARTIDA
    private void crearPartida() {
        view.mostrarMensaje("Creación de Partida - Selecciona Jugadores");
        listarJugadores();

        String nombresEntrada = view.leerString("Introduce nombres de jugadores separados por coma (mínimo 2):");
        String[] nombres = nombresEntrada.split(",");
        
        List<Jugador> jugadores = new ArrayList<>();
        for (String nombre : nombres) {
            Jugador j = jugadorDAO.obtenerJugador(nombre.trim());
            if (j != null) {
                jugadores.add(j);
            }
        }
        if (jugadores.size() < 2) {
            view.mostrarMensaje("Deben participar al menos 2 jugadores.");
            return;
        }

        int nuevoId = partidaDAO.listarPartidas().size() + 1;
        Partida partida = new Partida(nuevoId, jugadores);

        view.mostrarMensaje("Creando rondas...");
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador dibujante = jugadores.get(i);
            Jugador recreador = jugadores.get((i + 1) % jugadores.size());
            
            // Seleccionamos uno de los 3 retos "hard-coded" (por índice)
            Reto retoAsignado = retosDisponibles[i % retosDisponibles.length];
            
            Ronda ronda = new Ronda(i + 1, dibujante, recreador, retoAsignado);
            partida.agregarRonda(ronda);
        }

        partidaDAO.crearPartida(partida);
        view.mostrarMensaje("Partida creada correctamente.");
    }
    
    // 6) LISTAR PARTIDAS
    private void listarPartidas() {
        List<Partida> partidas = partidaDAO.listarPartidas();
        view.listarPartidas(partidas);
    }
    
    // 7) EDITAR PARTIDA
    private void editarPartida() {
        listarPartidas();
        if(partidaDAO.listarPartidas().isEmpty()){
            return;
        }
        int idPartida = view.leerEntero("Ingresa el ID de la partida a editar:");
        
        // Localizamos la partida
        Partida partida = partidaDAO.obtenerPartidaPorID(idPartida);
        if (partida == null) {
            view.mostrarMensaje("No se encontró la partida con ese ID.");
            return;
        }
        
        // Ejemplo simple: cambiamos la lista de jugadores
        view.mostrarMensaje("Jugadores actuales de la partida:");
        for (Jugador j : partida.getJugadores()) {
            view.mostrarMensaje("- " + j.getNombre());
        }

        String nombresEntrada = view.leerString("Introduce nuevos nombres de jugadores (mínimo 2) separados por coma:");
        String[] nuevosNombres = nombresEntrada.split(",");
        
        List<Jugador> nuevosJugadores = new ArrayList<>();
        for (String nombre : nuevosNombres) {
            Jugador j = jugadorDAO.obtenerJugador(nombre.trim());
            if (j != null) {
                nuevosJugadores.add(j);
            }
        }
        if (nuevosJugadores.size() < 2) {
            view.mostrarMensaje("Deben participar al menos 2 jugadores.");
            return;
        }
        
        // Actualizamos la lista de jugadores
        partida.setJugadores(nuevosJugadores);

        // (Opcional) Podríamos recrear las rondas o editarlas de otra manera:
        // Ej: partida.setRondas(new ArrayList<>()); y volver a crearlas.
        // Dependiendo de la lógica, regeneramos las rondas:
        // ...
        
        partidaDAO.actualizarPartida(partida);
        view.mostrarMensaje("Partida actualizada correctamente.");
    }
    
    // 8) BORRAR PARTIDA
    private void borrarPartida() {
        listarPartidas();
        if(partidaDAO.listarPartidas().isEmpty()){
            return;
        }
        int idPartida = view.leerEntero("Ingresa el ID de la partida a borrar:");

        Partida partida = partidaDAO.obtenerPartidaPorID(idPartida);
        if (partida == null) {
            view.mostrarMensaje("No se encontró la partida con ese ID.");
            return;
        }

        partidaDAO.eliminarPartida(partida);
        view.mostrarMensaje("Partida eliminada correctamente.");
    }
}
