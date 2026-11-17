package Implementaciones;

public class Cola {
    private int[] datos;
    private int frente;    // indice del primer elemento
    private int cantidad;  // cuantos elementos hay
    private int capacidad; // cuantos elementos entran

    public Cola(int capacidad) {
        this.capacidad = capacidad;
        this.datos = new int[capacidad];
        this.frente = 0;
        this.cantidad = 0;
    }

    public void encolar(int x) {
        if (frente + cantidad == capacidad) {
            throw new RuntimeException("Cola llena");
        }
        datos[frente + cantidad] = x;
        cantidad++;
    }

    public void desencolar() {
        if (esVacia()) {
            throw new RuntimeException("Cola vacia");
        }
        // No se corre nada. Se pierde espacio
        frente++;
        cantidad--;
    }

    public int primero() {
        if (esVacia()) {
            throw new RuntimeException("Cola vacia");
        }
        return datos[frente];
    }

    public boolean esVacia() {
        return cantidad == 0;
    }
}
