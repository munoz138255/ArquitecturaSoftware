package com.example.deportes.modelo;

public class Jugador {
    public String objectId;
    private String nombre;
    private float puntuacion;
    public String partidaId;

    public Jugador(String nombre, String partidaId) {
        this.nombre = nombre;
        this.puntuacion = 0;
        this.partidaId = partidaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPuntuacion() {
        return puntuacion;
    }
    public String getPartidaId(){
        return partidaId;
    }

    public void setPuntuacion(float puntuacion) {
        this.puntuacion = puntuacion;
    }

    @Override
    public String toString() {
        return nombre + " (Puntuación: " + puntuacion + ")";
    }

}
