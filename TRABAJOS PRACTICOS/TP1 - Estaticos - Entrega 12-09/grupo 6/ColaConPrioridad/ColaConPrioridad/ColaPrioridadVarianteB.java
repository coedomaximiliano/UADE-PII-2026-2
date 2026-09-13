public class ColaPrioridadVarianteB implements ColaPrioridad {
    private int[] valores;
    private int[] prioridades;
    private int[] ordenes;
    private int cantidad;
    private int ordenActual;
    private int capacidad;

    private static final int CAPACIDAD_DEFAULT = 1000;

    public ColaPrioridadVarianteB() {
        this(CAPACIDAD_DEFAULT);
    }

    public ColaPrioridadVarianteB(int capacidad) {
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

        int ordenNuevo = ordenActual;
        ordenActual++;

        int i = cantidad - 1;
        while (i >= 0 && esMayor(i, prioridad, ordenNuevo)) {
            valores[i + 1] = valores[i];
            prioridades[i + 1] = prioridades[i];
            ordenes[i + 1] = ordenes[i];
            i--;
        }

        valores[i + 1] = valor;
        prioridades[i + 1] = prioridad;
        ordenes[i + 1] = ordenNuevo;
        cantidad++;
    }

    @Override
    public ItemPrioridad extraerMax() {
        if (esVacia()) {
            throw new RuntimeException("Cola con prioridad vacia");
        }
        cantidad--;
        return new ItemPrioridad(valores[cantidad], prioridades[cantidad]);
    }

    @Override
    public ItemPrioridad verMax() {
        if (esVacia()) {
            throw new RuntimeException("Cola con prioridad vacia");
        }
        return new ItemPrioridad(valores[cantidad - 1], prioridades[cantidad - 1]);
    }

    @Override
    public boolean esVacia() {
        return cantidad == 0;
    }

    private boolean esMayor(int pos, int prioridad, int orden) {
        return prioridades[pos] > prioridad ||
                (prioridades[pos] == prioridad && ordenes[pos] < orden);
    }
}
