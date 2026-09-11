package Implementaciones;

import TDAs.PilaTDA;

public class PilaEstaticaA extends PilaTDA {
    private int[] datos;
    private int tope;       // cantidad de elementos / proximo lugar libre
    private int capacidad;

    public PilaEstaticaA(int capacidad) {
        this.capacidad = capacidad;
        this.datos = new int[capacidad];
        this.tope = 0;
    }

    @Override
    public void apilar(int x) {
        if (tope == capacidad) {
            throw new RuntimeException("Pila llena");
        }
        datos[tope] = x;
        tope++;
    }

    @Override
    public void desapilar() {
        if (esVacia()) {
            throw new RuntimeException("Pila vacia");
        }
        tope--;
    }

    @Override
    public int tope() {
        if (esVacia()) {
            throw new RuntimeException("Pila vacia");
        }
        return datos[tope - 1];
    }

    @Override
    public boolean esVacia() {
        return tope == 0;
    }
}
