
public class PilaEstatica {
    private int[] datos;
    private int tope;      
    private int capacidad;

    public PilaEstatica(int capacidad) {
        this.capacidad = capacidad;
        this.datos = new int[capacidad];
        this.tope = 0;
    }

    public boolean esVacia() {
        return tope == 0;
    }

    public boolean esLlena() {
        return tope == capacidad;
    }

    public void apilar(int x) {
        if (esLlena()) {
            throw new RuntimeException("Pila llena");
        }
        datos[tope] = x;
        tope++;
    }

    public int desapilar() {
        if (esVacia()) {
            throw new RuntimeException("Pila vacia");
        }
        tope--;
        return datos[tope];
    }

    public int tope() {
        if (esVacia()) {
            throw new RuntimeException("Pila vacia");
        }
        return datos[tope - 1];
    }
}
