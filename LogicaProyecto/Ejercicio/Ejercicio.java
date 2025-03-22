/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.panelcontroljava.Ejercicio;

/**
 *
 * @author byval
 */
public class Ejercicio {
    private String nombre;
    private String descripcion;
    private String nivel;  // Fácil, Medio, Difícil
    private String imagenEjemplo; // URL o Path local

    public Ejercicio(String nombre, String descripcion, String nivel, String imagenEjemplo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nivel = nivel;
        this.imagenEjemplo = imagenEjemplo;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }

    public String getImagenEjemplo() { return imagenEjemplo; }
    public void setImagenEjemplo(String imagenEjemplo) { this.imagenEjemplo = imagenEjemplo; }
}
