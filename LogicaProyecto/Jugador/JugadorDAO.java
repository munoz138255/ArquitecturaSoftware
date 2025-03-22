/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.panelcontroljava.Jugador;

import java.util.List;

/**
 *
 * @author byval
 */
public interface JugadorDAO {
    void crearJugador(Jugador jugador);
    Jugador obtenerJugador(String nombre);
    List<Jugador> listarJugadores();
    void actualizarJugador(Jugador jugador);
    void borrarJugador(String nombre);
}