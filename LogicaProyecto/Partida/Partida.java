/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.panelcontroljava.Partida;

import com.mycompany.panelcontroljava.Ronda.Ronda;
import com.mycompany.panelcontroljava.Jugador.Jugador;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author byval
 */
public class Partida {
    private int id;
    private Date fecha;
    private String nivelDificultad;
    private List<Ronda> rondas;
    private List<Jugador> jugadores;

    public Partida(int id, String nivelDificultad, List<Jugador> jugadores) {
        this.id = id;
        this.fecha = new Date();
        this.nivelDificultad = nivelDificultad;
        this.jugadores = jugadores;
        this.rondas = new ArrayList<>();
    }

    public void agregarRonda(Ronda ronda) {
        rondas.add(ronda);
    }

    public List<Ronda> getRondas() {
        return rondas;
    }

    public void setRondas(List<Ronda> rondas) {
        this.rondas = rondas;
    }

    public String getNivelDificultad() {
        return nivelDificultad;
    }

    public void setNivelDificultad(String nivelDificultad) {
        this.nivelDificultad = nivelDificultad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
    
    
    
}
