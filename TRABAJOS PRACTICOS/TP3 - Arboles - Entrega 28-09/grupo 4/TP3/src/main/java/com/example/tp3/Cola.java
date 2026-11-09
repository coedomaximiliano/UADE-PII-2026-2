package com.example.tp3;

/** Cola FIFO dinámica de nodos del árbol, sin colecciones de java.util. */
public class Cola {
    private static class NodoCola {
        private final NodoArbol dato;
        private NodoCola siguiente;

        private NodoCola(NodoArbol dato) { this.dato = dato; }
    }

    private NodoCola primero;
    private NodoCola ultimo;

    public boolean esVacia() { return primero == null; }

    public void encolar(NodoArbol dato) {
        if (dato == null) throw new IllegalArgumentException("No se puede encolar null");
        NodoCola nuevo = new NodoCola(dato);
        if (esVacia()) primero = nuevo;
        else ultimo.siguiente = nuevo;
        ultimo = nuevo;
    }

    public NodoArbol desencolar() {
        if (esVacia()) throw new IllegalStateException("La cola está vacía");
        NodoArbol dato = primero.dato;
        primero = primero.siguiente;
        if (primero == null) ultimo = null;
        return dato;
    }
}
