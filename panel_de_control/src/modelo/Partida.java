package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Partida {
    private int id;
    private Date fecha;
    private List<Ronda> rondas;
    private List<Jugador> jugadores;

    public Partida(int id, List<Jugador> jugadores) {
        this.id = id;
        this.fecha = new Date();
        this.jugadores = jugadores;
        this.rondas = new ArrayList<>();
    }

    public void agregarRonda(Ronda ronda) {
        rondas.add(ronda);
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

    public List<Ronda> getRondas() {
        return rondas;
    }

    public void setRondas(List<Ronda> rondas) {
        this.rondas = rondas;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
}
