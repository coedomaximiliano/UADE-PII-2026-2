package com.example.tp4;

/**
 * TDA Cola (reutilizado de TP1).
 * Implementación dinámica mediante nodos enlazados con punteros al frente y al fondo
 * (FIFO: First-In, First-Out).
 * No utiliza ninguna estructura de java.util.
 *
 * @param <T> Tipo de los elementos almacenados en la cola.
 */
public class Cola<T> {

    private static class Nodo<T> {
        private final T dato;
        private Nodo<T> siguiente;

        private Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo<T> frente;
    private Nodo<T> fondo;
    private int cantidad;

    /**
     * Crea una cola vacía.
     * Complejidad temporal: O(1).
     */
    public Cola() {
        this.frente = null;
        this.fondo = null;
        this.cantidad = 0;
    }

    /**
     * Operación constructora según especificación algebraica: crear(): Cola.
     * Complejidad temporal: O(1).
     */
    public static <T> Cola<T> crear() {
        return new Cola<>();
    }

    /**
     * Agrega un elemento al final (fondo) de la cola.
     * Precondición: ninguna.
     * Postcondición: el elemento queda ubicado al final de la cola.
     * Complejidad temporal: O(1).
     */
    public void encolar(T elemento) {
        Nodo<T> nuevo = new Nodo<>(elemento);
        if (estaVacia()) {
            this.frente = nuevo;
        } else {
            this.fondo.siguiente = nuevo;
        }
        this.fondo = nuevo;
        this.cantidad++;
    }

    /**
     * Elimina y devuelve el elemento del frente de la cola.
     * Precondición: la cola no debe estar vacía (!estaVacia()).
     * Postcondición: se remueve y retorna el elemento más antiguo de la cola.
     * Complejidad temporal: O(1).
     */
    public T desencolar() {
        if (estaVacia()) {
            throw new IllegalStateException("No se puede desencolar de una cola vacía.");
        }
        T valor = this.frente.dato;
        this.frente = this.frente.siguiente;
        if (this.frente == null) {
            this.fondo = null;
        }
        this.cantidad--;
        return valor;
    }

    /**
     * Devuelve el elemento del frente de la cola sin eliminarlo.
     * Precondición: la cola no debe estar vacía (!estaVacia()).
     * Postcondición: retorna el primer elemento sin modificar la cola.
     * Complejidad temporal: O(1).
     */
    public T primero() {
        if (estaVacia()) {
            throw new IllegalStateException("No se puede consultar el frente de una cola vacía.");
        }
        return this.frente.dato;
    }

    /**
     * Indica si la cola está vacía.
     * Complejidad temporal: O(1).
     */
    public boolean estaVacia() {
        return this.frente == null;
    }

    /**
     * Devuelve la cantidad de elementos actuales en la cola.
     * Complejidad temporal: O(1).
     */
    public int cantidadElementos() {
        return this.cantidad;
    }
}
