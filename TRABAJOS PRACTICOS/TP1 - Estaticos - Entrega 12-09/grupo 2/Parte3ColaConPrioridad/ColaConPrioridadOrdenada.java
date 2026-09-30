public class ColaConPrioridadOrdenada<T> implements ColaConPrioridad<T> {

    private Object[] elementos;
    private int[] prioridades;
    private long[] sellos;
    private int cantidad;
    private final int capacidad;
    private long proximoSello;

    public ColaConPrioridadOrdenada(int capacidad) {
        this.capacidad = capacidad;
        this.elementos = new Object[capacidad];
        this.prioridades = new int[capacidad];
        this.sellos = new long[capacidad];
        this.cantidad = 0;
        this.proximoSello = 0;
    }

    @Override
    public void insertar(T elemento, int prioridad) {
        if (cantidad == capacidad) {
            throw new RuntimeException("Cola con prioridad llena");
        }
        long selloNuevo = proximoSello++;
        int i = cantidad - 1;
        while (i >= 0 && (prioridades[i] > prioridad
                || (prioridades[i] == prioridad && sellos[i] < selloNuevo))) {
            elementos[i + 1] = elementos[i];
            prioridades[i + 1] = prioridades[i];
            sellos[i + 1] = sellos[i];
            i--;
        }
        elementos[i + 1] = elemento;
        prioridades[i + 1] = prioridad;
        sellos[i + 1] = selloNuevo;
        cantidad++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ElementoConPrioridad<T> extraerMax() {
        if (esVacia()) {
            throw new RuntimeException("Cola con prioridad vacía");
        }
        cantidad--;
        ElementoConPrioridad<T> resultado =
                new ElementoConPrioridad<>((T) elementos[cantidad], prioridades[cantidad]);
        elementos[cantidad] = null;
        return resultado;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ElementoConPrioridad<T> verMax() {
        if (esVacia()) {
            throw new RuntimeException("Cola con prioridad vacía");
        }
        return new ElementoConPrioridad<>((T) elementos[cantidad - 1], prioridades[cantidad - 1]);
    }

    @Override
    public boolean esVacia() {
        return cantidad == 0;
    }
}
