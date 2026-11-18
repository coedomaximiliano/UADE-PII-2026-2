public class ColaCircular implements Cola {
    private final int[] datos;
    private int frente;
    private int fin;
    private int cantidad;
    private int capacidad;

    public ColaCircular(int capacidad) {
        this.capacidad = capacidad;
        this.datos = new int[capacidad];
        this.fin = 0;
        this.frente = 0;
        this.cantidad = 0;
    }

    @Override
    public void encolar(int x) {
        if (esLlena()) {
            throw new IllegalStateException("Cola llena");
        }
        datos[fin] = x;
        fin = (fin +1) % datos.length;
        cantidad++;
    }

    @Override
    public int desencolar() {
        if (esVacia()) {
            throw new IllegalStateException("Cola vacia");
        }
        int x = datos[frente];
        frente = (frente +1) % datos.length;
        cantidad--;
        return x;
    }

    @Override
    public int primero() {
        if (esVacia()) {
            throw new IllegalStateException("Cola vacia");
        }
        return datos[frente];
    }

    @Override
    public int tamanio() {
        return cantidad;
    }

    @Override
    public boolean esVacia() {
        return cantidad == 0;
    }

    @Override
    public boolean esLlena() {
        return cantidad == capacidad;
    }
}
