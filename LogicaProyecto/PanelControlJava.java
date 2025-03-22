/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.panelcontroljava;

/**
 *
 * @author byval
 */
import com.mycompany.panelcontroljava.Ejercicio.Ejercicio;
import com.mycompany.panelcontroljava.Ejercicio.EjercicioDAO;
import com.mycompany.panelcontroljava.Ejercicio.EjercicioDAOImpl;
import com.mycompany.panelcontroljava.Jugador.Jugador;
import com.mycompany.panelcontroljava.Jugador.JugadorDAO;
import com.mycompany.panelcontroljava.Jugador.JugadorDAOImpl;
import com.mycompany.panelcontroljava.Partida.Partida;
import com.mycompany.panelcontroljava.Partida.PartidaDAO;
import com.mycompany.panelcontroljava.Partida.PartidaDAOImpl;
import com.mycompany.panelcontroljava.Ronda.Ronda;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PanelControlJava {
    private static final JugadorDAO jugadorDAO = new JugadorDAOImpl();
    private static final EjercicioDAO ejercicioDAO = new EjercicioDAOImpl();
    private static final PartidaDAO partidaDAO = new PartidaDAOImpl();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        cargarEjerciciosIniciales();

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Selecciona una opción:");
            ejecutarOpcion(opcion);
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("\n=== Menú Principal ===");
        System.out.println("1. Crear Jugador");
        System.out.println("2. Listar Jugadores");
        System.out.println("3. Crear Partida");
        System.out.println("4. Listar Partidas");
        System.out.println("0. Salir");
    }

    private static void ejecutarOpcion(int opcion) {
    switch (opcion) {
        case 1:
            crearJugador();
            break;
        case 2:
            listarJugadores();
            break;
        case 3:
            crearPartida();
            break;
        case 4:
            listarPartidas();
            break;
        case 0:
            System.out.println("Saliendo...");
            break;
        default:
            System.out.println("Opción inválida.");
    }
}


    private static void crearJugador() {
        System.out.print("Nombre del jugador: ");
        String nombre = scanner.nextLine();

        Jugador jugador = new Jugador(nombre);

        jugadorDAO.crearJugador(jugador);
        System.out.println("Jugador creado correctamente.");
    }

    private static void listarJugadores() {
        System.out.println("=== Lista de Jugadores ===");
        for (Jugador j : jugadorDAO.listarJugadores()) {
            System.out.println(j);
        }
    }

    private static void crearPartida() {
        List<Jugador> jugadores = new ArrayList<>();

        System.out.println("Creación de Partida - Selecciona Jugadores");
        listarJugadores();
        System.out.println("Introduce nombres de jugadores separados por coma (mínimo 2):");
        String[] nombres = scanner.nextLine().split(",");

        for (String nombre : nombres) {
            Jugador jugador = jugadorDAO.obtenerJugador(nombre.trim());
            if (jugador != null) {
                jugadores.add(jugador);
            }
        }

        if (jugadores.size() < 2) {
            System.out.println("Deben participar al menos 2 jugadores.");
            return;
        }

        System.out.print("Nivel de dificultad (Fácil/Medio/Difícil): ");
        String nivel = scanner.nextLine();

        Partida partida = new Partida(partidaDAO.listarPartidas().size() + 1, nivel, jugadores);

        System.out.println("Creando rondas...");
        for (int i = 0; i < jugadores.size(); i++) {
            Jugador dibujante = jugadores.get(i);
            Jugador recreador = jugadores.get((i + 1) % jugadores.size());

            Ejercicio ejercicio = obtenerEjercicioAleatorio(nivel);
            System.out.println(dibujante.getNombre() + " crea un ejercicio para " + recreador.getNombre() +
                    ": " + ejercicio.getNombre());

            Ronda ronda = new Ronda(i + 1, dibujante, recreador, ejercicio);
            partida.agregarRonda(ronda);
        }

        partidaDAO.crearPartida(partida);
        System.out.println("Partida creada correctamente.");
    }

    private static void listarPartidas() {
        System.out.println("=== Listado de Partidas ===");
        for (Partida p : partidaDAO.listarPartidas()) {
            System.out.println("Partida #" + p.getId() + " - " + p.getFecha());
            for (Ronda r : p.getRondas()) {
                System.out.println("  Ronda: " + r.getDibujante().getNombre() +
                        " dibuja para " + r.getRecreador().getNombre() +
                        " - Ejercicio: " + r.getEjercicio().getNombre());
            }
        }
    }

    private static void cargarEjerciciosIniciales() {
        ejercicioDAO.crearEjercicio(new Ejercicio("Salto de rana", "Saltar como una rana 3 veces", "Fácil", ""));
        ejercicioDAO.crearEjercicio(new Ejercicio("Estrella", "Hacer una estrella en el suelo", "Fácil", ""));
        ejercicioDAO.crearEjercicio(new Ejercicio("Rueda", "Hacer una rueda lateral", "Medio", ""));
        ejercicioDAO.crearEjercicio(new Ejercicio("Pino", "Hacer el pino contra la pared", "Difícil", ""));
        ejercicioDAO.crearEjercicio(new Ejercicio("Voltereta", "Hacer una voltereta hacia adelante", "Fácil", ""));
    }

    private static Ejercicio obtenerEjercicioAleatorio(String nivel) {
        List<Ejercicio> ejercicios = ejercicioDAO.listarEjercicios();
        List<Ejercicio> filtrados = new ArrayList<>();
        for (Ejercicio e : ejercicios) {
            if (e.getNivel().equalsIgnoreCase(nivel)) {
                filtrados.add(e);
            }
        }
        if (filtrados.isEmpty()) {
            System.out.println("No hay ejercicios disponibles para el nivel " + nivel + ". Usando cualquier nivel.");
            filtrados = ejercicios;
        }
        return filtrados.get((int) (Math.random() * filtrados.size()));
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje + " ");
        while (!scanner.hasNextInt()) {
            System.out.println("Debes ingresar un número.");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer
        return valor;
    }
}
