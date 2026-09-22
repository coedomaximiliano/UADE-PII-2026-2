public class ColaPrioridadVarianteA implements ColaPrioridad {
    private int[] valores;
    private int[] prioridades;
    private int[] ordenes;
    private int cantidad;
    private int ordenActual;
    private int capacidad;

    private static final int CAPACIDAD_DEFAULT = 1000;

    public ColaPrioridadVarianteA() {
        this(CAPACIDAD_DEFAULT);
    }

    public ColaPrioridadVarianteA(int capacidad) {
        this.capacidad = capacidad;
        this.valores = new int[capacidad];
        this.prioridades = new int[capacidad];
        this.ordenes = new int[capacidad];
        this.cantidad = 0;
        this.ordenActual = 0;
    }

    @Override
    public void insertar(int valor, int prioridad) {
        if (cantidad == capacidad) {
            throw new RuntimeException("Cola con prioridad llena");
        }
        valores[cantidad] = valor;
        prioridades[cantidad] = prioridad;
        ordenes[cantidad] = ordenActual;
        cantidad++;
        ordenActual++;
    }

    @Override
    public ItemPrioridad extraerMax() {
        if (esVacia()) {
            throw new RuntimeException("Cola con prioridad vacia");
        }
        int pos = posicionMax();
        ItemPrioridad max = new ItemPrioridad(valores[pos], prioridades[pos]);

        valores[pos] = valores[cantidad - 1];
        prioridades[pos] = prioridades[cantidad - 1];
        ordenes[pos] = ordenes[cantidad - 1];
        cantidad--;

        return max;
    }

    @Override
    public ItemPrioridad verMax() {
        if (esVacia()) {
            throw new RuntimeException("Cola con prioridad vacia");
        }
        int pos = posicionMax();
        return new ItemPrioridad(valores[pos], prioridades[pos]);
    }

    @Override
    public boolean esVacia() {
        return cantidad == 0;
    }

    private int posicionMax() {
        int pos = 0;
        for (int i = 1; i < cantidad; i++) {
            if (prioridades[i] > prioridades[pos] ||
                    (prioridades[i] == prioridades[pos] && ordenes[i] < ordenes[pos])) {
                pos = i;
            }
        }
        return pos;
    }
}
