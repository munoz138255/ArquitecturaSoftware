package com.example.deportes.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Partida {
    private String id;
    private Date fecha;
    private List<Ronda> rondas;
    private Jugador jugador1;
    private Jugador jugador2;

    public Partida(String id, Jugador jugador1, Jugador jugador2) {
        this.id = id;
        this.fecha = new Date();
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.rondas = new ArrayList<>();
    }
    public static Partida nuevaPartida(String id, Jugador jugador1, Jugador jugador2) {
        return new Partida(id, jugador1, jugador2);
    }

    public void agregarRonda(Ronda ronda) {
        rondas.add(ronda);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Ronda> getRondas() {
        return rondas;
    }

    public void setRondas(List<Ronda> rondas) {
        this.rondas = rondas;
    }

    public Jugador getJugador1(){
        return jugador1;
    }
    public Jugador getJugador2(){
        return jugador2;
    }
    @Override
    public String toString(){
        return (this.jugador1.getNombre() + " vs " + this.jugador2.getNombre());
    }
}
