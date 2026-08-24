/**
 * ALGORITMOS Y ESTRUCTURAS DE DATOS II - Clase 2
 * TDA Pila - Implementacion estatica (basada en arreglo)
 */
public class PilaEstatica {
    private int[] datos;
    private int tope;       // cantidad de elementos / proximo lugar libre
    private int capacidad;

    // crear() -> Pila
    public PilaEstatica(int capacidad) {
        this.capacidad = capacidad;
        this.datos = new int[capacidad];
        this.tope = 0;
    }

    // esVacia(p) -> boolean
    public boolean esVacia() {
        return tope == 0;
    }

    // esLlena(p) -> boolean
    public boolean esLlena() {
        return tope == capacidad;
    }

    // apilar(p, x) -> Pila
    public void apilar(int x) {
        if (esLlena()) {
            throw new RuntimeException("Pila llena");
        }
        datos[tope] = x;
        tope++;
    }

    // desapilar(p) -> Pila
    public int desapilar() {
        if (esVacia()) {
            throw new RuntimeException("Pila vacia");
        }
        tope--;
        return datos[tope];
    }

    // tope(p) -> elemento
    public int tope() {
        if (esVacia()) {
            throw new RuntimeException("Pila vacia");
        }
        return datos[tope - 1];
    }

    // tamanio(): cantidad de elementos actuales
    public int tamanio() {
        return tope;
    }

    // vaciar(): deja la pila en el mismo estado que devuelve crear()
    public void vaciar() {
        tope = 0;
    }
}
