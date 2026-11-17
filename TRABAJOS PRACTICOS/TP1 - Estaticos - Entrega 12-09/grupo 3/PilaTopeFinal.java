// TP1 - Algoritmos y Estructuras de Datos II
// Pedro Stella - Bautista Chavarri

/**
 * TDA Pila de enteros con arreglo, tope en la ULTIMA POSICION OCUPADA (Variante A).
 * apilar, desapilar y tope son O(1): solo se mueve el indice tope.
 */
public class PilaTopeFinal implements Pila {

    private int[] datos;
    private int tope;       // indice del proximo lugar libre (= cantidad de elementos)
    private int capacidad;

    // crear() -> Pila
    // post: devuelve una pila vacia
    public PilaTopeFinal(int capacidad) {
        this.capacidad = capacidad;
        this.datos = new int[capacidad];
        this.tope = 0;
    }

    // apilar(p: Pila, x: elemento) -> Pila
    // pre:  p no esta llena
    // post: x queda en el tope de p
    @Override
    public void apilar(int x) {
        if (esLlena()) {
            throw new RuntimeException("Pila llena");
        }
        datos[tope] = x;
        tope++;
    }

    // desapilar(p: Pila) -> Pila
    // pre:  p no esta vacia
    // post: se elimina el elemento del tope de p
    @Override
    public int desapilar() {
        if (esVacia()) {
            throw new RuntimeException("Pila vacia");
        }
        tope--;
        return datos[tope];
    }

    // tope(p: Pila) -> elemento
    // pre:  p no esta vacia
    // post: devuelve el elemento del tope sin eliminarlo
    @Override
    public int tope() {
        if (esVacia()) {
            throw new RuntimeException("Pila vacia");
        }
        return datos[tope - 1];   // tope apunta al proximo lugar libre
    }

    // esVacia(p: Pila) -> boolean
    // post: devuelve true si p no tiene elementos
    @Override
    public boolean esVacia() {
        return tope == 0;
    }

    // esLlena(p: Pila) -> boolean
    // post: devuelve true si p alcanzo su capacidad maxima
    @Override
    public boolean esLlena() {
        return tope == capacidad;
    }
}
