package TP1.Parte1_Pila;

// Variante B: el tope se mantiene siempre en la posicion 0 del arreglo.
public class PilaVarianteB implements Pila {

    private int[] datos;
    private int cantidad;
    private int capacidad;

    public PilaVarianteB(int capacidad) {
        this.capacidad = capacidad;
        this.datos = new int[capacidad];
        this.cantidad = 0;
    }

    @Override
    public boolean esVacia() {
        return cantidad == 0;
    }

    @Override
    public boolean esLlena() {
        return cantidad == capacidad;
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
        for (int i = cantidad; i > 0; i--) {
            datos[i] = datos[i - 1];
        }
        datos[0] = x;
        cantidad++;
    }

    @Override
    public int desapilar() {
        if (esVacia()) {
            throw new RuntimeException("Pila vacia");
        }
        int valor = datos[0];
        for (int i = 0; i < cantidad - 1; i++) {
            datos[i] = datos[i + 1];
        }
        cantidad--;
        return valor;
    }

    @Override
    public int tope() {
        if (esVacia()) {
            throw new RuntimeException("Pila vacia");
        }
        return datos[0];
    }
}
