public class ColaB implements Cola {
    private final int[] datos;
    private int frente;   // indice del primer elemento
    private int fin;   // indice del ultimo elemento
    private int cantidad; // cuantos elementos hay
    private int capacidad;

    public ColaB(int capacidad) {
        this.capacidad = capacidad;
        this.datos = new int[capacidad];
        this.fin = 0;
        this.frente = 0;
        this.cantidad = 0;
    }

    /**
     * post: x queda al final de la cola
     */
    @Override
    public void encolar(int x) {
        if (esLlena()) {
            throw new IllegalStateException("Cola llena");
        }
        datos[fin] = x;
        fin = (fin +1) % datos.length;
        cantidad++;
    }

    /**
     * pre:  la cola no esta vacia
     * post: se elimina el elemento del frente y se devuelve
     */
    @Override
    public int desencolar() {
        if (esVacia()) {
            throw new IllegalStateException("Cola vacia");
        }
        int x = datos[frente];
        frente = (frente +1) % datos.length;   // O(1) — no se corre nada
        cantidad--;
        return x;
    }

    /**
     * pre:  la cola no esta vacia
     * post: devuelve el elemento del frente sin eliminarlo
     */
    @Override
    public int primero() {
        if (esVacia()) {
            throw new IllegalStateException("Cola vacia");
        }
        return datos[frente]; // O(1)
    }

    @Override
    public int tamanio() {
        return cantidad;
    }

    /**
     * post: devuelve true si la cola no tiene elementos
     */
    @Override
    public boolean esVacia() {
        return cantidad == 0;
    }

    /**
     * post: devuelve true si la cola alcanzo su capacidad maxima
     */
    @Override
    public boolean esLlena() {
        return cantidad == capacidad;
    }
}
