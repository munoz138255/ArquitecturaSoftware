/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.List;
import modelo.Partida;

/**
 *
 * @author alumno
 */
public interface RegistroPartidasInterface {
    void crearPartida(Partida partida);
    Partida obtenerPartida(int id);
    List<Partida> listarPartidas();
    void borrarPartida(int id);
}