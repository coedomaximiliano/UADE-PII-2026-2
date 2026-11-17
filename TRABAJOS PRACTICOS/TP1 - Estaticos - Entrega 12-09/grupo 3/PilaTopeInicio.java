// TP1 - Algoritmos y Estructuras de Datos II
// Pedro Stella - Bautista Chavarri

/**
 * TDA Pila de enteros con arreglo, tope siempre en la POSICION 0 (Variante B).
 * apilar y desapilar son O(n): corren todos los elementos un lugar para abrir
 * o cerrar el hueco en datos[0]. tope es O(1): solo lee datos[0].
 */
public class PilaTopeInicio implements Pila {

    private int[] datos;
    private int cantidad;   // cantidad de elementos; el tope esta siempre en datos[0]
    private int capacidad;

    // crear() -> Pila
    // post: devuelve una pila vacia
    public PilaTopeInicio(int capacidad) {
        this.capacidad = capacidad;
        this.datos = new int[capacidad];
        this.cantidad = 0;
    }

    // apilar(p: Pila, x: elemento) -> Pila
    // pre:  p no esta llena
    // post: x queda en el tope de p
    @Override
    public void apilar(int x) {
        if (esLlena()) {
            throw new RuntimeException("Pila llena");
        }
        for (int i = cantidad; i > 0; i--) {
            datos[i] = datos[i - 1];   // corre todo un lugar a la derecha para liberar datos[0]
        }
        datos[0] = x;
        cantidad++;
    }

    // desapilar(p: Pila) -> Pila
    // pre:  p no esta vacia
    // post: se elimina el elemento del tope de p
    @Override
    public int desapilar() {
        if (esVacia()) {
            throw new RuntimeException("Pila vacia");
        }
        int elemento = datos[0];
        for (int i = 0; i < cantidad - 1; i++) {
            datos[i] = datos[i + 1];   // corre todo un lugar a la izquierda para tapar datos[0]
        }
        cantidad--;
        return elemento;
    }

    // tope(p: Pila) -> elemento
    // pre:  p no esta vacia
    // post: devuelve el elemento del tope sin eliminarlo
    @Override
    public int tope() {
        if (esVacia()) {
            throw new RuntimeException("Pila vacia");
        }
        return datos[0];
    }

    // esVacia(p: Pila) -> boolean
    // post: devuelve true si p no tiene elementos
    @Override
    public boolean esVacia() {
        return cantidad == 0;
    }

    // esLlena(p: Pila) -> boolean
    // post: devuelve true si p alcanzo su capacidad maxima
    @Override
    public boolean esLlena() {
        return cantidad == capacidad;
    }
}
