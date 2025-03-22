/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.panelcontroljava.Jugador;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author byval
 */
public class JugadorDAOImpl implements JugadorDAO {
    private List<Jugador> jugadores = new ArrayList<>();

    @Override
    public void crearJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    @Override
    public Jugador obtenerJugador(String nombre) {
        return jugadores.stream()
                .filter(j -> j.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Jugador> listarJugadores() {
        return new ArrayList<>(jugadores);
    }

    @Override
    public void actualizarJugador(Jugador jugador) {
        borrarJugador(jugador.getNombre());
        jugadores.add(jugador);
    }

    @Override
    public void borrarJugador(String nombre) {
        jugadores.removeIf(j -> j.getNombre().equalsIgnoreCase(nombre));
    }
}