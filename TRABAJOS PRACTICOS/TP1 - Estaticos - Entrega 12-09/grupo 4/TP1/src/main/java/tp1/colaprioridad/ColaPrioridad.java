package tp1.colaprioridad;

public interface ColaPrioridad {
    void crear();
    void insertar(int x, int prioridad);
    void extraerMax();
    int verMax();
    int prioridadMax();
    boolean esVacia();
}
