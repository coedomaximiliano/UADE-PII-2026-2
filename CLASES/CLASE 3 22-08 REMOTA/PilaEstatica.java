public class PilaEstatica {
    
    private int[] datos;
    private int tope;       // cantidad de elementos / proximo lugar libre
    private int capacidad;

    // crear() -> Pila
    // post: devuelve una pila vacia
    public PilaEstatica(int capacidad) {
        this.capacidad = capacidad;
        this.datos = new int[capacidad];
        this.tope = 0;
    }

    // esVacia(p) -> boolean
    // post: devuelve true si p no tiene elementos
    public boolean esVacia() {
        return tope == 0;
    }

    // (auxiliar, no está en la especificación original,
    // pero hace falta para controlar el tamaño del array)
    public boolean esLlena() {
        return tope == capacidad;
    }

    // apilar(p, x) -> Pila
    // post: x queda en el tope de p
    public void apilar(int x) {
        if (esLlena()) {
            throw new RuntimeException("Pila llena");
        }
        datos[tope] = x;
        tope++;
    }

    // desapilar(p) -> Pila
    // pre:  p no esta vacia
    // post: se elimina el elemento del tope de p
    public void desapilar() {
        if (esVacia()) {
            throw new RuntimeException("Pila vacia");
        }
        tope--;
    }

    // tope(p) -> elemento
    // pre:  p no esta vacia
    // post: devuelve el elemento del tope sin eliminarlo
    public int tope() {
        if (esVacia()) {
            throw new RuntimeException("Pila vacia");
        }
        return datos[tope - 1];
    }
}