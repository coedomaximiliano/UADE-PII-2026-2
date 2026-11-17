public class Lista {
    private NodoLista primero;
    private NodoLista ultimo;
    private int cantidad;

    public Lista() {
        this.primero = null;
        this.ultimo = null;
        this.cantidad = 0;
    }

    public void agregar(Object x) {
        NodoLista nuevo = new NodoLista(x);
        if (primero == null) {
            primero = nuevo;
        } else {
            ultimo.siguiente = nuevo;
        }
        ultimo = nuevo;
        cantidad++;
    }

    public Object obtener(int i) {
        if (i < 0 || i >= cantidad) {
            throw new RuntimeException("obtener(): posicion invalida -> " + i);
        }
        NodoLista actual = primero;
        for (int j = 0; j < i; j++) {
            actual = actual.siguiente;
        }
        return actual.dato;
    }

    public int tamanio() {
        return cantidad;
    }

    public boolean esVacia() {
        return primero == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        NodoLista actual = primero;
        while (actual != null) {
            sb.append(actual.dato).append(" ");
            actual = actual.siguiente;
        }
        sb.append("]");
        return sb.toString();
    }
}
