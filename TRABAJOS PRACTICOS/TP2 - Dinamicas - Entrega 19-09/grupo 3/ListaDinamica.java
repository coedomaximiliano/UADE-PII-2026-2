// TP2 - Algoritmos y Estructuras de Datos II
// Pedro Stella - Bautista Chavarri

/**
 * Lista dinamica de elementos, implementada con una cadena de NodoLista. Es lo que
 * devuelve claves() y lo que devuelve clavesOrdenadas() de la Parte 4.
 * Guarda la cabeza y tambien el ultimo nodo, para que agregar al final sea O(1) y
 * claves() no pague un recorrido completo por cada clave.
 */
public class ListaDinamica {

    private NodoLista cabeza;
    private NodoLista ultimo;
    private int cantidad;

    // crear() -> Lista
    // post: devuelve una lista vacia
    public ListaDinamica() {
        this.cabeza = null;
        this.ultimo = null;
        this.cantidad = 0;
    }

    // agregar(l: Lista, x: elemento) -> Lista
    // post: x queda al final de l
    // costo: O(1), se engancha directo al ultimo nodo
    public void agregar(Object x) {
        NodoLista nuevo = new NodoLista(x);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            ultimo.siguiente = nuevo;
        }
        ultimo = nuevo;
        cantidad++;
    }

    // obtener(l: Lista, i: entero) -> elemento
    // pre:  0 <= i < cantidad(l)
    // post: devuelve el elemento que esta en la posicion i; l no se modifica
    // costo: O(i), hay que recorrer la cadena desde la cabeza
    public Object obtener(int i) {
        if (i < 0 || i >= cantidad) {
            throw new RuntimeException("obtener(): indice fuera de rango -> " + i);
        }
        NodoLista actual = cabeza;
        for (int pos = 0; pos < i; pos++) {
            actual = actual.siguiente;
        }
        return actual.dato;
    }

    // cantidad(l: Lista) -> entero
    // post: devuelve cuantos elementos tiene l
    public int cantidad() {
        return cantidad;
    }

    // esVacia(l: Lista) -> boolean
    // post: devuelve true si l no tiene elementos
    public boolean esVacia() {
        return cabeza == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        NodoLista actual = cabeza;
        while (actual != null) {
            sb.append(actual.dato);
            if (actual.siguiente != null) {
                sb.append(", ");
            }
            actual = actual.siguiente;
        }
        sb.append("]");
        return sb.toString();
    }
}
