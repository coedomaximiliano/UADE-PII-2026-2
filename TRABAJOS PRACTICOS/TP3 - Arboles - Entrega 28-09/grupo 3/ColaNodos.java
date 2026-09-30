// TP3 - Algoritmos y Estructuras de Datos II
// Pedro Stella - Bautista Chavarri

/**
 * TDA Cola de nodos del arbol, con arreglo circular: es la cola de los TP anteriores,
 * adaptada para encolar NodoArbol en vez de enteros. La usa el recorrido por niveles,
 * que necesita procesar los nodos en el orden en que se encolaron (FIFO).
 * encolar, desencolar y frente son O(1): frente y fin avanzan con % capacidad y vuelven
 * a 0 al pasar el final, reutilizando los lugares que libera desencolar.
 */
public class ColaNodos {

    private NodoArbol[] datos;
    private int frente;
    private int fin;
    private int cantidad;
    private int capacidad;

    // crear() -> Cola
    // post: devuelve una cola vacia de capacidad fija
    public ColaNodos(int capacidad) {
        this.capacidad = capacidad;
        this.datos = new NodoArbol[capacidad];
        this.frente = 0;
        this.fin = 0;
        this.cantidad = 0;
    }

    // encolar(c: Cola, x: elemento) -> Cola
    // pre:  c no esta llena
    // post: x queda al final de c
    public void encolar(NodoArbol x) {
        if (esLlena()) {
            throw new RuntimeException("Cola llena");
        }
        datos[fin] = x;
        fin = (fin + 1) % capacidad;
        cantidad++;
    }

    // desencolar(c: Cola) -> Cola
    // pre:  c no esta vacia
    // post: se elimina el elemento del frente de c y se devuelve
    public NodoArbol desencolar() {
        if (esVacia()) {
            throw new RuntimeException("Cola vacia");
        }
        NodoArbol x = datos[frente];
        datos[frente] = null;
        frente = (frente + 1) % capacidad;
        cantidad--;
        return x;
    }

    // frente(c: Cola) -> elemento
    // pre:  c no esta vacia
    // post: devuelve el elemento del frente sin eliminarlo
    public NodoArbol frente() {
        if (esVacia()) {
            throw new RuntimeException("Cola vacia");
        }
        return datos[frente];
    }

    // esVacia(c: Cola) -> boolean
    // post: devuelve true si c no tiene elementos
    public boolean esVacia() {
        return cantidad == 0;
    }

    // esLlena(c: Cola) -> boolean
    // post: devuelve true si c alcanzo su capacidad maxima
    public boolean esLlena() {
        return cantidad == capacidad;
    }
}
