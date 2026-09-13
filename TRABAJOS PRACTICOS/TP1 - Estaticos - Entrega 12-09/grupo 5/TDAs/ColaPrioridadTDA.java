package TDAs;

public abstract class ColaPrioridadTDA {
    // Dominio:
    // Cola con Prioridad = Secuencia de elementos ordenados por un valor prioridad, donde el primer lugar lo ocupa el elemento con mayor prioridad, con FIFO como criterio de desempate.

    // Operaciones:
    // crear(); --> Lo hace el constructor dentro de la implementacion
    // post: devuelve una CCP vacía.

    public abstract void insertar(int elemento, int p);
    // pre: c no está llena.
    // post: x queda insertado.

    public abstract int extraerMax();
    // pre: c no está vacía.
    // post: se elimina el de mayor prioridad.

    public abstract int verMax();
    // pre: c no está vacía.
    // post: devuelve el elemento de mayor prioridad.

    public abstract int verPrioridadMax();
    // pre: c no está vacía.
    // post: devuelve la prioridad del elemento de mayor prioridad, sin modificar la cola.

    public abstract boolean esVacia();
    // post: devuelve true si c no tiene elementos, false en caso contrario.
}
