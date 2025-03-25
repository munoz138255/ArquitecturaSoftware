package modelo;

public class Ronda {
    private final int id;
    private Jugador dibujante;
    private Jugador recreador;
    private Reto reto;
    private String fotoRecreacion;
    private int puntuacion;

    public Ronda(int id, Jugador dibujante, Jugador recreador, Reto reto) {
        this.id = id;
        this.dibujante = dibujante;
        this.recreador = recreador;
        this.reto = reto;
        this.puntuacion = 0;
    }

    public int getId() {
        return id;
    }

    public Jugador getDibujante() {
        return dibujante;
    }

    public void setDibujante(Jugador dibujante) {
        this.dibujante = dibujante;
    }

    public Jugador getRecreador() {
        return recreador;
    }

    public void setRecreador(Jugador recreador) {
        this.recreador = recreador;
    }

    public Reto getReto() {
        return reto;
    }

    public void setReto(Reto reto) {
        this.reto = reto;
    }

    public String getFotoRecreacion() {
        return fotoRecreacion;
    }

    public void setFotoRecreacion(String fotoRecreacion) {
        this.fotoRecreacion = fotoRecreacion;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
}
