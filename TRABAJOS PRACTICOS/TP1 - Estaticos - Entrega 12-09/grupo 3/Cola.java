// TP1 - Algoritmos y Estructuras de Datos II
// Pedro Stella - Bautista Chavarri

/**
 * TDA Cola de enteros: secuencia finita y ordenada de elementos donde el primero
 * en entrar es el primero en salir (FIFO).
 * Esta interfaz es el CONTRATO del TDA: que operaciones existen y que garantizan
 * (pre/post), sin decir como se implementan. Cualquier variante que la implemente
 * (cola lineal simple o cola circular) es una cola valida mientras respete este contrato.
 * crear() -> Cola es el constructor de cada variante (post: devuelve una cola vacia).
 */
public interface Cola {

    // encolar(c: Cola, x: elemento) -> Cola
    // pre:  c no esta llena
    // post: x queda al final de c
    void encolar(int x);

    // desencolar(c: Cola) -> Cola
    // pre:  c no esta vacia
    // post: se elimina el elemento del frente de c
    int desencolar();

    // frente(c: Cola) -> elemento
    // pre:  c no esta vacia
    // post: devuelve el elemento del frente sin eliminarlo
    int frente();

    // esVacia(c: Cola) -> boolean
    // post: devuelve true si c no tiene elementos
    boolean esVacia();

    // esLlena(c: Cola) -> boolean
    // post: devuelve true si c alcanzo su capacidad maxima
    boolean esLlena();
}
