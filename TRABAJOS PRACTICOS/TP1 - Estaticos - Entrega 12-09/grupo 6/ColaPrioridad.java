public interface ColaPrioridad {
    void insertar(int valor, int prioridad);
    ItemPrioridad extraerMax();
    ItemPrioridad verMax();
    boolean esVacia();
}
