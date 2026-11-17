package com.example.tp4;

/**
 * TDA Pila (reutilizado de TP1).
 * Implementación dinámica mediante nodos enlazados (LIFO: Last-In, First-Out).
 * No utiliza ninguna estructura de java.util.
 *
 * @param <T> Tipo de los elementos almacenados en la pila.
 */
public class Pila<T> {

    private static class Nodo<T> {
        private final T dato;
        private final Nodo<T> siguiente;

        private Nodo(T dato, Nodo<T> siguiente) {
            this.dato = dato;
            this.siguiente = siguiente;
        }
    }

    private Nodo<T> tope;
    private int cantidad;

    /**
     * Crea una pila vacía.
     * Complejidad temporal: O(1).
     */
    public Pila() {
        this.tope = null;
        this.cantidad = 0;
    }

    /**
     * Operación constructora según especificación algebraica: crear(): Pila.
     * Complejidad temporal: O(1).
     */
    public static <T> Pila<T> crear() {
        return new Pila<>();
    }

    /**
     * Agrega un elemento en el tope de la pila.
     * Precondición: ninguna.
     * Postcondición: el elemento queda en el tope de la pila.
     * Complejidad temporal: O(1).
     */
    public void apilar(T elemento) {
        this.tope = new Nodo<>(elemento, this.tope);
        this.cantidad++;
    }

    /**
     * Elimina y devuelve el elemento en el tope de la pila.
     * Precondición: la pila no debe estar vacía (!estaVacia()).
     * Postcondición: se remueve y retorna el elemento que estaba en el tope.
     * Complejidad temporal: O(1).
     */
    public T desapilar() {
        if (estaVacia()) {
            throw new IllegalStateException("No se puede desapilar de una pila vacía.");
        }
        T valor = this.tope.dato;
        this.tope = this.tope.siguiente;
        this.cantidad--;
        return valor;
    }

    /**
     * Devuelve el elemento en el tope de la pila sin eliminarlo.
     * Precondición: la pila no debe estar vacía (!estaVacia()).
     * Postcondición: retorna el elemento del tope sin modificar la pila.
     * Complejidad temporal: O(1).
     */
    public T tope() {
        if (estaVacia()) {
            throw new IllegalStateException("No se puede consultar el tope de una pila vacía.");
        }
        return this.tope.dato;
    }

    /**
     * Indica si la pila no contiene elementos.
     * Complejidad temporal: O(1).
     */
    public boolean estaVacia() {
        return this.tope == null;
    }

    /**
     * Devuelve la cantidad de elementos actuales en la pila.
     * Complejidad temporal: O(1).
     */
    public int cantidadElementos() {
        return this.cantidad;
    }
}
