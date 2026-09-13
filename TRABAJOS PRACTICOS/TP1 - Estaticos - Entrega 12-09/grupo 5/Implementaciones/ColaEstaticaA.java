package Implementaciones;

import TDAs.ColaTDA;

public class ColaEstaticaA extends ColaTDA {

    private int[] datos;
    private int frente;   // indice del primer elemento
    private int cantidad; // cuantos elementos hay
    private int capacidad;

    public ColaEstaticaA(int capacidad) {
        this.capacidad = capacidad;
        this.datos = new int[capacidad];
        this.frente = 0;
        this.cantidad = 0;
    }

    @Override
    public void encolar(int x) {
        if (frente + cantidad == capacidad) {
            throw new RuntimeException("Cola llena");
        }
        datos[frente + cantidad] = x;
        cantidad++;
    }

    @Override
    public void desencolar() {
        if (esVacia()) {
            throw new RuntimeException("Cola vacia");
        }
        // No se corre nada. Se pierde espacio
        frente++;
        cantidad--;
    }

    @Override
    public int primero() {
        if (esVacia()) {
            throw new RuntimeException("Cola vacia");
        }
        return datos[frente];
    }

    @Override
    public boolean esVacia() {
        return cantidad == 0;
    }
}
