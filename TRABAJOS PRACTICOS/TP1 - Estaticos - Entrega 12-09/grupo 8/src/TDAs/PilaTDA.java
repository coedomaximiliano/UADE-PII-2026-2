package TDAs;

public abstract class PilaTDA {
    // Dominio:
    // Pila = Secuencia finita de elementos ordenada por llegada, donde el último agregado es accesible en primer lugar (LIFO).

    // Operaciones:
    // crear(); --> Lo hace el constructor dentro de la implementacion
    // post: devuelve una pila vacía.

    public abstract void apilar(int elemento);
    // pre: p no está llena.
    // post: x queda en el tope de p.

    public abstract void desapilar();
    // pre: p no está vacía.
    // post: se elimina el elemento del tope de p.

    public abstract int tope();
    // pre: p no está vacía.
    // post: devuelve el elemento del tope sin eliminarlo.

    public abstract boolean esVacia();
    // post: devuelve true si p no tiene elementos, false en caso contrario.
}
