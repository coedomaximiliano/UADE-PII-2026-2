package Implementaciones;

public class Pila {
    private int[] datos;
    private int tope;       // cantidad de elementos / proximo lugar libre
    private int capacidad;

    public Pila(int capacidad) {
        this.capacidad = capacidad;
        this.datos = new int[capacidad];
        this.tope = 0;
    }

    public void apilar(int x) {
        if (tope == capacidad) {
            throw new RuntimeException("Pila llena");
        }
        datos[tope] = x;
        tope++;
    }

    public void desapilar() {
        if (esVacia()) {
            throw new RuntimeException("Pila vacia");
        }
        tope--;
    }

    public int tope() {
        if (esVacia()) {
            throw new RuntimeException("Pila vacia");
        }
        return datos[tope - 1];
    }

    public boolean esVacia() {
        return tope == 0;
    }
}
