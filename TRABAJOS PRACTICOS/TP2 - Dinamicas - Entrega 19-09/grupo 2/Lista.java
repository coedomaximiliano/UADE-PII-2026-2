/**
 * Lista dinamica (cadena de NodoLista), la vista en la Clase 5.
 *
 * Es el tipo que devuelve claves(d) y clavesOrdenadas(d). Se mantiene
 * una referencia a la cabeza y otra al ultimo nodo, para que agregar al
 * final sea O(1) (no hay que recorrer la cadena cada vez).
 *
 * Operaciones:
 *   agregar(x)   -> agrega x al final de la lista.               O(1)
 *   tamanio()    -> cantidad de elementos.                       O(1)
 *   obtener(i)   -> el elemento en la posicion i (0..tamanio-1). O(i)
 *   esVacia()    -> true si no tiene elementos.                  O(1)
 */
public class Lista {

    private NodoLista cabeza;
    private NodoLista ultimo;
    private int cantidad;

    public Lista() {
        this.cabeza = null;
        this.ultimo = null;
        this.cantidad = 0;
    }

    public void agregar(Object x) {
        NodoLista nuevo = new NodoLista(x);
        if (cabeza == null) {
            cabeza = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.siguiente = nuevo;
            ultimo = nuevo;
        }
        cantidad++;
    }

    public int tamanio() {
        return cantidad;
    }

    public Object obtener(int i) {
        if (i < 0 || i >= cantidad) {
            throw new RuntimeException("obtener(): indice fuera de rango -> " + i);
        }
        NodoLista actual = cabeza;
        for (int k = 0; k < i; k++) {
            actual = actual.siguiente;
        }
        return actual.dato;
    }

    public boolean esVacia() {
        return cabeza == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        NodoLista actual = cabeza;
        while (actual != null) {
            sb.append(actual.dato).append(" ");
            actual = actual.siguiente;
        }
        sb.append("]");
        return sb.toString();
    }
}
