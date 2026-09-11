package Implementaciones;

import TDAs.PilaTDA;

public class PilaEstaticaB extends PilaTDA {
    private int[] datos;
    private int cantidad; // Reemplaza la lógica anterior de tope dinámico
    private int capacidad;

    public PilaEstaticaB(int capacidad) {
        this.capacidad = capacidad;
        this.datos = new int[capacidad];
        this.cantidad = 0;
    }

    @Override
    public void apilar(int x) {
        if (cantidad == capacidad) {
            throw new RuntimeException("Pila llena");
        }
        // desplaza todos los elementos una posición a la derecha
        for (int i = cantidad - 1; i >= 0; i--) {
            datos[i + 1] = datos[i];
        }
        datos[0] = x; // tope en la posicion 0
        cantidad++;
    }

    @Override
    public void desapilar() {
        if (esVacia()) {
            throw new RuntimeException("Pila vacia");
        }
        // desplaza todos los elementos una posición a la izquierda
        for (int i = 0; i < cantidad - 1; i++) {
            datos[i] = datos[i + 1];
        }
        cantidad--;
    }

    @Override
    public int tope() {
        if (esVacia()) {
            throw new RuntimeException("Pila vacia");
        }
        return datos[0]; // tope siempre en la posicion 0
    }

    @Override
    public boolean esVacia() {
        return cantidad == 0;
    }
}