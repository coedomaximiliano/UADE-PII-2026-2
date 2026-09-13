package Implementaciones;

import TDAs.ColaTDA;

public class ColaEstaticaB extends ColaTDA {

    private int[] datos;
    private int frente;   // indice del primer elemento
    private int cantidad; // cuantos elementos hay
    private int capacidad;

    public ColaEstaticaB(int capacidad) {
        this.capacidad = capacidad;
        this.datos = new int[capacidad];
        this.frente = 0;
        this.cantidad = 0;
    }

    @Override
    public void encolar(int x) {
        if (cantidad == capacidad) {
            throw new RuntimeException("Cola llena");
        }
        // Uso de % para "dar la vuelta" al principio del arreglo
        int pos = (frente + cantidad) % capacidad;
        datos[pos] = x;
        cantidad++;
    }

    @Override
    public void desencolar() {
        if (esVacia()) {
            throw new RuntimeException("Cola vacia");
        }
        // Avanza el frente circularmente
        frente = (frente + 1) % capacidad;
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
