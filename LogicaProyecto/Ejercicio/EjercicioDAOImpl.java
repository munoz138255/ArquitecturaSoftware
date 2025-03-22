/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.panelcontroljava.Ejercicio;

/**
 *
 * @author byval
 */
import java.util.ArrayList;
import java.util.List;

public class EjercicioDAOImpl implements EjercicioDAO {
    private List<Ejercicio> ejercicios = new ArrayList<>();

    @Override
    public void crearEjercicio(Ejercicio ejercicio) {
        ejercicios.add(ejercicio);
    }

    @Override
    public Ejercicio obtenerEjercicio(String nombre) {
        return ejercicios.stream()
                .filter(e -> e.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Ejercicio> listarEjercicios() {
        return new ArrayList<>(ejercicios);
    }

    @Override
    public void actualizarEjercicio(Ejercicio ejercicio) {
        borrarEjercicio(ejercicio.getNombre());
        ejercicios.add(ejercicio);
    }

    @Override
    public void borrarEjercicio(String nombre) {
        ejercicios.removeIf(e -> e.getNombre().equalsIgnoreCase(nombre));
    }
}

