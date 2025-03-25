package PanelControlJava;

import java.util.List;
import java.util.Scanner;
import modelo.Jugador;
import modelo.Partida;
import modelo.Ronda;

public class PanelControlView {

    private final Scanner scanner = new Scanner(System.in);

    // Muestra el menú principal
    public void mostrarMenu() {
        System.out.println("\n=== Menú Principal ===");
        System.out.println("1. Crear Jugador");
        System.out.println("2. Listar Jugadores");
        System.out.println("3. Editar Jugador");
        System.out.println("4. Borrar Jugador");
        System.out.println("5. Crear Partida");
        System.out.println("6. Listar Partidas");
        System.out.println("7. Editar Partida");
        System.out.println("8. Borrar Partida");
        System.out.println("0. Salir");
    }

    // Lee un número entero
    public int leerEntero(String mensaje) {
        System.out.print(mensaje + " ");
        while (!scanner.hasNextInt()) {
            System.out.println("Debes ingresar un número válido.");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        return valor;
    }

    // Lee un string
    public String leerString(String mensaje) {
        System.out.print(mensaje + " ");
        return scanner.nextLine();
    }

    // Mensaje genérico
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    // Listar jugadores
    public void listarJugadores(List<Jugador> jugadores) {
        System.out.println("\n=== Lista de Jugadores ===");
        if (jugadores.isEmpty()) {
            System.out.println("(No hay jugadores registrados)");
        } else {
            for (Jugador j : jugadores) {
                System.out.println("- " + j);
            }
        }
    }

    // Listar partidas
    public void listarPartidas(List<Partida> partidas) {
        System.out.println("\n=== Lista de Partidas ===");
        if (partidas.isEmpty()) {
            System.out.println("(No hay partidas registradas)");
            return;
        }
        for (Partida p : partidas) {
            System.out.println("Partida #" + p.getId() + " - " + p.getFecha());
            if (p.getRondas().isEmpty()) {
                System.out.println("  (Sin rondas registradas)");
            } else {
                for (Ronda r : p.getRondas()) {
                    System.out.println("  Ronda #" + r.getId() + " | Dibujante: " 
                            + r.getDibujante().getNombre()
                            + " -> Recreador: " + r.getRecreador().getNombre()
                            + " | Reto: " + r.getReto().getNombre() + " ("
                            + r.getReto().getNivel() + ")");
                }
            }
        }
    }
}
