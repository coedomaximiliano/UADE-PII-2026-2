// TP1 - Algoritmos y Estructuras de Datos II
// Pedro Stella - Bautista Chavarri

/**
 * TDA Cola con Prioridad de enteros: secuencia finita de elementos con una prioridad entera
 * asociada, donde sale primero el de mayor prioridad. Criterio de desempate: con igual
 * prioridad sale primero el que se inserto antes (FIFO). Esta interfaz es el CONTRATO del
 * TDA: cualquier variante que la implemente (desordenada u ordenada) es una cola con
 * prioridad valida si respeta el contrato, desempate incluido.
 * crear() -> ColaPrioridad no esta aca: en Java es el constructor de cada variante.
 */
public interface ColaPrioridad {

    // insertar(cp: ColaPrioridad, valor: elemento, prioridad: entero) -> ColaPrioridad
    // pre:  cp no esta llena
    // post: valor queda agregado a cp con esa prioridad
    void insertar(int valor, int prioridad);

    // extraerMax(cp: ColaPrioridad) -> ColaPrioridad
    // pre:  cp no esta vacia
    // post: se elimina de cp el elemento de mayor prioridad;
    //       con igual prioridad, el que se inserto antes
    int extraerMax();

    // verMax(cp: ColaPrioridad) -> elemento
    // pre:  cp no esta vacia
    // post: devuelve el elemento de mayor prioridad (el que sacaria extraerMax) sin eliminarlo
    int verMax();

    // prioridadMax(cp: ColaPrioridad) -> entero
    // pre:  cp no esta vacia
    // post: devuelve la prioridad del elemento que devuelve verMax, sin eliminarlo
    int prioridadMax();

    // esVacia(cp: ColaPrioridad) -> boolean
    // post: devuelve true si cp no tiene elementos
    boolean esVacia();

    // esLlena(cp: ColaPrioridad) -> boolean
    // post: devuelve true si cp alcanzo su capacidad maxima
    boolean esLlena();
}
