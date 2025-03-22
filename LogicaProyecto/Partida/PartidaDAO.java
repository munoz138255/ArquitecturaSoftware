/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.panelcontroljava.Partida;

import java.util.List;

/**
 *
 * @author byval
 */

public interface PartidaDAO {
    void crearPartida(Partida partida);
    Partida obtenerPartida(int id);
    List<Partida> listarPartidas();
    void borrarPartida(int id);
}

