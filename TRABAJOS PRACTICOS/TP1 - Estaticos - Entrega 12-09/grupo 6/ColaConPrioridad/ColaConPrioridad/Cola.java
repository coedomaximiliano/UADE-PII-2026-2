public class Cola {
    private final int[] elementos;
    private int inicio;
    private int fin;
    private int cantidad;

    private static final int CAPACIDAD_DEFAULT = 1000;

    public Cola() {
        this(CAPACIDAD_DEFAULT);
    }

    public Cola(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser positiva");
        }
        elementos = new int[capacidad];
        inicio = 0;
        fin = 0;
        cantidad = 0;
    }

    public void acolar(int valor) {
        if (cantidad == elementos.length) {
            throw new IllegalStateException("La cola esta llena");
        }
        elementos[fin] = valor;
        fin = (fin + 1) % elementos.length;
        cantidad++;
    }

    public int desacolar() {
        if (esVacia()) {
            throw new IllegalStateException("La cola esta vacia");
        }
        int valor = elementos[inicio];
        inicio = (inicio + 1) % elementos.length;
        cantidad--;
        return valor;
    }

    public int primero() {
        if (esVacia()) {
            throw new IllegalStateException("La cola esta vacia");
        }
        return elementos[inicio];
    }

    public boolean esVacia() {
        return cantidad == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < cantidad; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(elementos[(inicio + i) % elementos.length]);
        }
        sb.append("]");
        return sb.toString();
    }
}
