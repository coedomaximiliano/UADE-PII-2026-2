package TDAs;

public abstract class ListaTDA {
    // Dominio:
    // Lista = Colección finita de elementos sin posiciones ni orden garantizado, donde puede haber repetidos.

    // Operaciones:
    // crear() -> Lo hace el constructor.
    // post: devuelve una lista vacia.

    public abstract void agregar(Object x);
    // post: x pertenece a la lista y tamanio aumenta en 1.

    public abstract void eliminar(Object x);
    // pre: x pertenece a la lista.
    // post: se elimina una ocurrencia de x y tamanio disminuye en 1.

    public abstract boolean pertenece(Object x);
    // post: devuelve true si x aparece al menos una vez en l.

    public abstract Object elegir();
    // pre: la lista no esta vacia.
    // post: devuelve un elemento cualquiera de l sin eliminarlo.

    public abstract int tamanio();
    // post: devuelve la cantidad de elementos de la lista.

    public abstract boolean esVacia();
    // post: devuelve true si la lista no tiene elementos, false en caso contrario.
}
