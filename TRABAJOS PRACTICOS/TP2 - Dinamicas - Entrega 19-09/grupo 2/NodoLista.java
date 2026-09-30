/**
 * Nodo de la Lista dinamica: guarda un dato y la referencia al
 * siguiente nodo de la cadena (null si es el ultimo).
 */
public class NodoLista {
    Object dato;
    NodoLista siguiente;

    public NodoLista(Object dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}
