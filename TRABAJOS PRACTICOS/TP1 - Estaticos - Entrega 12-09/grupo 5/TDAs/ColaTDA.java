package TDAs;

public abstract class ColaTDA {
    // Dominio:
    // Cola = Secuencia de elementos ordenados por llegada, donde el primer lugar se encuentra en el primer elemento que entró (FIFO).

    // Operaciones:
    // crear(); --> Lo hace el constructor dentro de la implementacion
    // post: devuelve una cola vacía.

    public abstract void encolar(int elemento);
    // pre: c no debe estar llena.
    // post: debe agregar el elemento a c.

    public abstract void desencolar();
    // pre: c no debe estar vacía.
    // post: debe eliminar el primer elemento de la cola.

    public abstract int primero();
    // pre: c no debe estar vacía.
    // post: devuelve el elemento que esta en el frente de c.

    public abstract boolean esVacia();
    // post: devuelve true si c esta vacia, devuelve false si c tiene elementos.
}
