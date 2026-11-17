// TP3 - Algoritmos y Estructuras de Datos II
// Pedro Stella - Bautista Chavarri

/**
 * Nodo de un arbol binario: la pieza minima de la implementacion dinamica del ABB.
 * Guarda el dato y dos referencias, una al subarbol izquierdo y otra al derecho, que
 * valen null cuando ese hijo no existe.
 * Es la misma idea de nodo de las estructuras dinamicas anteriores, pero con dos
 * referencias en vez de una: por eso el arbol no es una estructura lineal.
 */
public class NodoArbol {

    int dato;
    NodoArbol izquierdo;
    NodoArbol derecho;

    public NodoArbol(int dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
    }
}
