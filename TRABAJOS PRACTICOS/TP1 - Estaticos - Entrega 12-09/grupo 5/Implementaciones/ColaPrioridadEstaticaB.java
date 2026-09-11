package Implementaciones;

import TDAs.ColaPrioridadTDA;

public class ColaPrioridadEstaticaB extends ColaPrioridadTDA {

    private static class Nodo {
        int valor;
        int prioridad;

        // Constructor de Nodo
        Nodo(int valor, int prioridad) {
            this.valor = valor;
            this.prioridad = prioridad;
        }
    }

    private Nodo[] datos;
    private int cantidad;
    private int capacidad;

    public ColaPrioridadEstaticaB(int capacidad) {
        this.capacidad = capacidad;
        this.datos = new Nodo[capacidad];
        this.cantidad = 0;
    }

    @Override
    public void insertar(int elemento, int p) {
        if (cantidad == capacidad) {
            throw new RuntimeException("Cola llena");
        }
        // Mantiene el arreglo ordenado por prioridad creciente de izquierda a derecha.
        // Los elementos con prioridad >= p se desplazan atrás de la nueva entrada, lo que garantiza FIFO.
        int i = cantidad - 1;
        while (i >= 0 && datos[i].prioridad >= p) {
            datos[i + 1] = datos[i]; // Desplaza a la derecha
            i--;
        }
        // Insertar en la posición correspondiente
        datos[i + 1] = new Nodo(elemento, p);
        cantidad++;
    }

    @Override
    public int extraerMax() {
        if (esVacia()) {
            throw new RuntimeException("Cola vacia");
        }
        // El máximo siempre está al final del arreglo
        int valorMax = datos[cantidad - 1].valor;
        cantidad--;
        return valorMax;
    }

    @Override
    public int verMax() {
        if (esVacia()) {
            throw new RuntimeException("Cola vacia");
        }
        // Acceso directo al último elemento
        return datos[cantidad - 1].valor;
    }

    @Override
    public int verPrioridadMax() {
        if (esVacia()) {
            throw new RuntimeException("Cola vacia");
        }
        return datos[cantidad - 1].prioridad;
    }

    @Override
    public boolean esVacia() {
        return cantidad == 0;
    }
}
