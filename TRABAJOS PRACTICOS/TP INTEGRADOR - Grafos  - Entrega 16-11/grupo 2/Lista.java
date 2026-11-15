public class Lista {
    private NodoLista cabeza;
    private NodoLista ultimo;
    private int cantidad;

    public Lista() {
        this.cabeza = null;
        this.ultimo = null;
        this.cantidad = 0;
    }

    public void agregar(Object x) {
        NodoLista nuevo = new NodoLista(x);
        if (cabeza == null) {
            cabeza = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.siguiente = nuevo;
            ultimo = nuevo;
        }
        cantidad++;
    }

    public int tamanio() {
        return cantidad;
    }

    public Object obtener(int i) {
        if (i < 0 || i >= cantidad) {
            throw new RuntimeException("obtener(): indice fuera de rango -> " + i);
        }
        NodoLista actual = cabeza;
        for (int k = 0; k < i; k++) {
            actual = actual.siguiente;
        }
        return actual.dato;
    }

    public boolean esVacia() {
        return cabeza == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        NodoLista actual = cabeza;
        while (actual != null) {
            sb.append(actual.dato).append(" ");
            actual = actual.siguiente;
        }
        sb.append("]");
        return sb.toString();
    }
}
