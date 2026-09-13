package Implementaciones;

import TDAs.ColaPrioridadTDA;

public class ColaPrioridadEstaticaA extends ColaPrioridadTDA {

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

    public ColaPrioridadEstaticaA(int capacidad) {
        this.capacidad = capacidad;
        this.datos = new Nodo[capacidad];
        this.cantidad = 0;
    }

    @Override
    public void insertar(int elemento, int p) {
        if (cantidad == capacidad) {
            throw new RuntimeException("Cola llena");
        }
        // Inserción en la primera posición libre
        datos[cantidad] = new Nodo(elemento, p);
        cantidad++;
    }

    @Override
    public int extraerMax() {
        if (esVacia()) {
            throw new RuntimeException("Cola vacia");
        }

        int idxMax = obtenerIndiceMayorPrioridad();
        int valorMax = datos[idxMax].valor;

        // Se desplazan los elementos a la izquierda para cubrir el hueco, preservando FIFO entre los elementos restantes
        for (int i = idxMax; i < cantidad - 1; i++) {
            datos[i] = datos[i + 1];
        }
        cantidad--;

        return valorMax;
    }

    @Override
    public int verMax() {
        if (esVacia()) {
            throw new RuntimeException("Cola vacia");
        }
        int idxMax = obtenerIndiceMayorPrioridad();
        return datos[idxMax].valor;
    }

    @Override
    public int verPrioridadMax() {
        if (esVacia()) {
            throw new RuntimeException("Cola vacia");
        }
        int idxMax = obtenerIndiceMayorPrioridad();
        return datos[idxMax].prioridad;
    }

    @Override
    public boolean esVacia() {
        return cantidad == 0;
    }

    // Busca el índice del elemento con mayor prioridad. Ante prioridades iguales, la comparación
    // > retorna el que ingresó primero (FIFO)
    private int obtenerIndiceMayorPrioridad() {
        int idxMax = 0;
        for (int i = 1; i < cantidad; i++) {
            if (datos[i].prioridad > datos[idxMax].prioridad) {
                idxMax = i;
            }
        }
        return idxMax;
    }
}
