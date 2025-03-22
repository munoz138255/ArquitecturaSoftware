/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.panelcontroljava.Partida;

/**
 *
 * @author byval
 */
import java.util.ArrayList;
import java.util.List;

public class PartidaDAOImpl implements PartidaDAO {
    private List<Partida> partidas = new ArrayList<>();

    @Override
    public void crearPartida(Partida partida) {
        partidas.add(partida);
    }

    @Override
    public Partida obtenerPartida(int id) {
        return partidas.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Partida> listarPartidas() {
        return new ArrayList<>(partidas);
    }

    @Override
    public void borrarPartida(int id) {
        partidas.removeIf(p -> p.getId() == id);
    }
}

