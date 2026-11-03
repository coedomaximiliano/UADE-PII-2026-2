public class ColaAuxiliar {
    private NodoCola frente;
    private NodoCola fin;

    public ColaAuxiliar() {
        this.frente = null;
        this.fin = null;
    }

    public void encolar(NodoArbol x) {
        NodoCola nuevo = new NodoCola(x);
        if (frente == null) {
            frente = nuevo;
        } else {
            fin.siguiente = nuevo;
        }
        fin = nuevo;
    }

    public NodoArbol desencolar() {
        if (esVacia()) {
            throw new RuntimeException("Cola vacia");
        }
        NodoArbol valor = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null;
        }
        return valor;
    }

    public boolean esVacia() {
        return frente == null;
    }
}
