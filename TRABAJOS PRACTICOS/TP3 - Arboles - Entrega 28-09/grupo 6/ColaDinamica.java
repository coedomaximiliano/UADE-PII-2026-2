public class ColaDinamica implements Cola {
    private NodoCola primero;
    private NodoCola ultimo;
    private int cantidad;

    public ColaDinamica() {
        this.primero = null;
        this.ultimo = null;
        this.cantidad = 0;
    }

    @Override
    public void encolar(NodoArbol x) {
        NodoCola nuevo = new NodoCola(x);
        if (primero == null) {
            primero = nuevo;
        } else {
            ultimo.siguiente = nuevo;
        }
        ultimo = nuevo;
        cantidad++;
    }

    @Override
    public NodoArbol desencolar() {
        if (esVacia()) {
            throw new RuntimeException("desencolar(): cola vacia");
        }
        NodoArbol dato = primero.dato;
        primero = primero.siguiente;
        cantidad--;
        if (primero == null) {
            ultimo = null;
        }
        return dato;
    }

    @Override
    public NodoArbol primero() {
        if (esVacia()) {
            throw new RuntimeException("primero(): cola vacia");
        }
        return primero.dato;
    }

    @Override
    public boolean esVacia() {
        return primero == null;
    }

    @Override
    public int tamanio() {
        return cantidad;
    }
}
