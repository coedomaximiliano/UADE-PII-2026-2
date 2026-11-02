public interface Cola {
    void encolar(NodoArbol x);

    NodoArbol desencolar();

    NodoArbol primero();

    boolean esVacia();

    int tamanio();
}
