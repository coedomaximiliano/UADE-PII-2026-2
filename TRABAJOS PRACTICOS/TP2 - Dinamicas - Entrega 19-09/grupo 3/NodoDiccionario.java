// TP2 - Algoritmos y Estructuras de Datos II
// Pedro Stella - Bautista Chavarri

/**
 * Nodo de la cadena enlazada clave-valor: la pieza minima de la implementacion
 * dinamica del TDA Diccionario. Guarda un par (clave, valor) y una referencia al
 * proximo nodo de la cadena, null si es el ultimo.
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
