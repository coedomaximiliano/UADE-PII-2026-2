// TP2 - Algoritmos y Estructuras de Datos II
// Pedro Stella - Bautista Chavarri

/**
 * Nodo de la Lista dinamica: un dato y la referencia al proximo nodo de la cadena
 * (null si es el ultimo). Es la misma idea de nodo que NodoDiccionario, pero con un
 * solo dato en vez de un par.
 */
public class NodoLista {

    Object dato;
    NodoLista siguiente;

    public NodoLista(Object dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}
