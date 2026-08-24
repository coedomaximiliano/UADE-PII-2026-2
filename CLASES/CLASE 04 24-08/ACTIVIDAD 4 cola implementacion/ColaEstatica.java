/**
 * ALGORITMOS Y ESTRUCTURAS DE DATOS II - Clase 2
 * TDA Cola - Implementacion estatica circular (basada en arreglo)
 */
public class ColaEstatica {
    private int[] datos;
    private int inicio;     // indice del primer elemento
    private int fin;        // indice del proximo lugar libre
    private int cantidad;   // cuantos elementos hay actualmente
    private int capacidad;

    // crear() -> Cola
    public ColaEstatica(int capacidad) {
        this.capacidad = capacidad;
        this.datos = new int[capacidad];
        this.inicio = 0;
        this.fin = 0;
        this.cantidad = 0;
    }

    // esVacia(c) -> boolean
    public boolean esVacia() {
        return cantidad == 0;
    }

    // esLlena(c) -> boolean
    public boolean esLlena() {
        return cantidad == capacidad;
    }

    // encolar(c, x) -> Cola
    public void encolar(int x) {
        if (esLlena()) {
            throw new RuntimeException("Cola llena");
        }
        datos[fin] = x;
        fin = (fin + 1) % capacidad;   // clave: circular, "da la vuelta" al 0
        cantidad++;
    }

    // desencolar(c) -> Cola
    public int desencolar() {
        if (esVacia()) {
            throw new RuntimeException("Cola vacia");
        }
        int valor = datos[inicio];
        inicio = (inicio + 1) % capacidad;  // clave: circular
        cantidad--;
        return valor;
    }

    // primero(c) -> elemento
    public int primero() {
        if (esVacia()) {
            throw new RuntimeException("Cola vacia");
        }
        return datos[inicio];
    }

    // tamanio(c) -> entero
    public int tamanio() {
        return cantidad;
    }
}
