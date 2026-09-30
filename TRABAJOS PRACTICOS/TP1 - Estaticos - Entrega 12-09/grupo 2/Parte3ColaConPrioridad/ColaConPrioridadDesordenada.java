public class ColaConPrioridadDesordenada<T> implements ColaConPrioridad<T> {

    private Object[] elementos;
    private int[] prioridades;
    private int cantidad;
    private final int capacidad;

    public ColaConPrioridadDesordenada(int capacidad) {
        this.capacidad = capacidad;
        this.elementos = new Object[capacidad];
        this.prioridades = new int[capacidad];
        this.cantidad = 0;
    }

    @Override
    public void insertar(T elemento, int prioridad) {
        if (cantidad == capacidad) {
            throw new RuntimeException("Cola con prioridad llena");
        }
        elementos[cantidad] = elemento;
        prioridades[cantidad] = prioridad;
        cantidad++;
    }

    private int indiceMax() {
        int idxMax = 0;
        for (int i = 1; i < cantidad; i++) {
            if (prioridades[i] > prioridades[idxMax]) {
                idxMax = i;
            }
        }
        return idxMax;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ElementoConPrioridad<T> extraerMax() {
        if (esVacia()) {
            throw new RuntimeException("Cola con prioridad vacía");
        }
        int idx = indiceMax();
        ElementoConPrioridad<T> resultado =
                new ElementoConPrioridad<>((T) elementos[idx], prioridades[idx]);
        for (int i = idx; i < cantidad - 1; i++) {
            elementos[i] = elementos[i + 1];
            prioridades[i] = prioridades[i + 1];
        }
        cantidad--;
        elementos[cantidad] = null;
        return resultado;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ElementoConPrioridad<T> verMax() {
        if (esVacia()) {
            throw new RuntimeException("Cola con prioridad vacía");
        }
        int idx = indiceMax();
        return new ElementoConPrioridad<>((T) elementos[idx], prioridades[idx]);
    }

    @Override
    public boolean esVacia() {
        return cantidad == 0;
    }
}
