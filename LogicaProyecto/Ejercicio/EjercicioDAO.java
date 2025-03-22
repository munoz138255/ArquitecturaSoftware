package com.mycompany.panelcontroljava.Ejercicio;


import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author byval
 */

public interface EjercicioDAO {
    void crearEjercicio(Ejercicio ejercicio);
    Ejercicio obtenerEjercicio(String nombre);
    List<Ejercicio> listarEjercicios();
    void actualizarEjercicio(Ejercicio ejercicio);
    void borrarEjercicio(String nombre);
}

