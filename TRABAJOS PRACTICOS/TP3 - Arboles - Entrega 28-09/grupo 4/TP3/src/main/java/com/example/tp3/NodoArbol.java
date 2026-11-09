package com.example.tp3;

/** Nodo enlazado con un dato y dos referencias a hijos. */
public class NodoArbol {
    private final int dato;
    private NodoArbol izquierdo;
    private NodoArbol derecho;

    public NodoArbol(int dato) { this(dato, null, null); }

    public NodoArbol(int dato, NodoArbol izquierdo, NodoArbol derecho) {
        this.dato = dato;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }

    public int getDato() { return dato; }
    public NodoArbol getIzquierdo() { return izquierdo; }
    public NodoArbol getDerecho() { return derecho; }
    void setIzquierdo(NodoArbol izquierdo) { this.izquierdo = izquierdo; }
    void setDerecho(NodoArbol derecho) { this.derecho = derecho; }
}
