package TP1.Parte1_Pila;

// Variante A: el tope se mantiene en el ultimo lugar ocupado del arreglo.
public class PilaVarianteA implements Pila {

    private int[] datos;
    private int tope;       // indice del proximo lugar libre
    private int capacidad;

    public PilaVarianteA(int capacidad) {
        this.capacidad = capacidad;
        this.datos = new int[capacidad];
        this.tope = 0;
    }

    @Override
    public boolean esVacia() {
        return tope == 0;
    }

    @Override
    public boolean esLlena() {
        return tope == capacidad;
    }

    @Override
    public int capacidad() {
        return capacidad;
    }

    @Override
    public void apilar(int x) {
        if (esLlena()) {
            throw new RuntimeException("Pila llena");
        }
        datos[tope] = x;
        tope++;
    }

    @Override
    public int desapilar() {
        if (esVacia()) {
            throw new RuntimeException("Pila vacia");
        }
        tope--;
        return datos[tope];
    }

    @Override
    public int tope() {
        if (esVacia()) {
            throw new RuntimeException("Pila vacia");
        }
        return datos[tope - 1];
    }
}
