public interface ColaConPrioridad<T> {

    void insertar(T elemento, int prioridad);

    ElementoConPrioridad<T> extraerMax();

    ElementoConPrioridad<T> verMax();

    boolean esVacia();
}
