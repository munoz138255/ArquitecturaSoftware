/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.panelcontroljava.Ronda;

import com.mycompany.panelcontroljava.Ejercicio.Ejercicio;
import com.mycompany.panelcontroljava.Jugador.Jugador;

/**
 *
 * @author byval
 */
public class Ronda {
    private final int id;
    private Jugador dibujante;
    private Jugador recreador;
    private Ejercicio ejercicio;
    private String fotoRecreacion;
    private int puntuacion;

    public Ronda(int id, Jugador dibujante, Jugador recreador, Ejercicio ejercicio) {
        this.id = id;
        this.dibujante = dibujante;
        this.recreador = recreador;
        this.ejercicio = ejercicio;
        this.puntuacion = 0; // Se asigna tras evaluación
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

    public Ejercicio getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Ejercicio ejercicio) {
        this.ejercicio = ejercicio;
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

