// TP1 - Algoritmos y Estructuras de Datos II
// Pedro Stella - Bautista Chavarri

/**
 * TDA Pila de enteros: secuencia finita y ordenada donde el ultimo agregado es
 * el primero accesible (LIFO). Esta interfaz es el CONTRATO del TDA: que operaciones
 * hay y que garantizan, sin decir como se implementan. Cualquier variante que la
 * implemente (tope al final o tope al inicio) es una pila valida si respeta el contrato.
 * crear() -> Pila no esta aca: en Java es el constructor de cada variante.
 */
public interface Pila {

    // apilar(p: Pila, x: elemento) -> Pila
    // pre:  p no esta llena
    // post: x queda en el tope de p
    void apilar(int x);

    // desapilar(p: Pila) -> Pila
    // pre:  p no esta vacia
    // post: se elimina el elemento del tope de p
    int desapilar();

    // tope(p: Pila) -> elemento
    // pre:  p no esta vacia
    // post: devuelve el elemento del tope sin eliminarlo
    int tope();

    // esVacia(p: Pila) -> boolean
    // post: devuelve true si p no tiene elementos
    boolean esVacia();

    // esLlena(p: Pila) -> boolean
    // post: devuelve true si p alcanzo su capacidad maxima
    boolean esLlena();
}
