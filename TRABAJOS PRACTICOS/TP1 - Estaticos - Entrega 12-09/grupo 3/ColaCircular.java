// TP1 - Algoritmos y Estructuras de Datos II
// Pedro Stella - Bautista Chavarri

/**
 * TDA Cola de enteros con arreglo circular (Variante B).
 * encolar, desencolar y frente son O(1): frente y fin avanzan con % capacidad y
 * vuelven a 0 al pasar el final, reutilizando los lugares liberados al desencolar.
 */
public class ColaCircular implements Cola {

    private int[] datos;
    private int frente;     // indice del primer elemento
    private int fin;        // indice del proximo lugar libre
    private int cantidad;   // cuantos elementos hay
    private int capacidad;

    // crear() -> Cola
    // post: devuelve una cola vacia
    public ColaCircular(int capacidad) {
        this.capacidad = capacidad;
        this.datos = new int[capacidad];
        this.frente = 0;
        this.fin = 0;
        this.cantidad = 0;
    }

    // encolar(c: Cola, x: elemento) -> Cola
    // pre:  c no esta llena
    // post: x queda al final de c
    @Override
    public void encolar(int x) {
        if (esLlena()) {
            throw new RuntimeException("Cola llena");
        }
        datos[fin] = x;
        fin = (fin + 1) % capacidad;   // clave: circular, "da la vuelta" al 0
        cantidad++;
    }

    // desencolar(c: Cola) -> Cola
    // pre:  c no esta vacia
    // post: se elimina el elemento del frente de c
    @Override
    public int desencolar() {
        if (esVacia()) {
            throw new RuntimeException("Cola vacia");
        }
        int x = datos[frente];
        frente = (frente + 1) % capacidad;   // clave: circular
        cantidad--;
        return x;
    }

    // frente(c: Cola) -> elemento
    // pre:  c no esta vacia
    // post: devuelve el elemento del frente sin eliminarlo
    @Override
    public int frente() {
        if (esVacia()) {
            throw new RuntimeException("Cola vacia");
        }
        return datos[frente];
    }

    // esVacia(c: Cola) -> boolean
    // post: devuelve true si c no tiene elementos
    @Override
    public boolean esVacia() {
        return cantidad == 0;
    }

    // esLlena(c: Cola) -> boolean
    // post: devuelve true si c alcanzo su capacidad maxima
    @Override
    public boolean esLlena() {
        return cantidad == capacidad;
    }
}
