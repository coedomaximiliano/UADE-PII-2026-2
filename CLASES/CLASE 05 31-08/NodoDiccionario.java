/**
 * ALGORITMOS Y ESTRUCTURAS DE DATOS II - Clase 4
 * Nodo de una cadena enlazada clave-valor: la pieza minima de la
 * implementacion dinamica del TDA Diccionario.
 *
 * Guarda un par (clave, valor) y una referencia al proximo nodo de la
 * cadena (null si es el ultimo). Es la misma idea de Nodo vista en la
 * Clase 5 para Pila/Lista (dato + siguiente), con dos datos en vez de
 * uno solo: acá el "dato" es en realidad un par.
 */
public class NodoDiccionario {
    Object clave;
    Object valor;
    NodoDiccionario siguiente;

    public NodoDiccionario(Object clave, Object valor) {
        this.clave = clave;
        this.valor = valor;
        this.siguiente = null;
    }
}
