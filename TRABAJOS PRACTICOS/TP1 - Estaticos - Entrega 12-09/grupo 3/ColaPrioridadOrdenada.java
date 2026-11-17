// TP1 - Algoritmos y Estructuras de Datos II
// Pedro Stella - Bautista Chavarri

/**
 * TDA Cola con Prioridad de enteros con dos arreglos paralelos ORDENADOS de menor a
 * mayor prioridad (Variante B): el maximo vive siempre en la posicion cantidad - 1.
 * insertar busca su lugar corriendo elementos: O(n).
 * extraerMax, verMax y prioridadMax leen la ultima posicion ocupada: O(1).
 */
public class ColaPrioridadOrdenada implements ColaPrioridad {

    private int[] valores;
    private int[] prioridades;  // ordenado ascendente; prioridades[i] es la de valores[i]
    private int cantidad;
    private int capacidad;

    // crear() -> ColaPrioridad
    // post: devuelve una cola con prioridad vacia
    public ColaPrioridadOrdenada(int capacidad) {
        this.capacidad = capacidad;
        this.valores = new int[capacidad];
        this.prioridades = new int[capacidad];
        this.cantidad = 0;
    }

    // insertar(cp: ColaPrioridad, valor: elemento, prioridad: entero) -> ColaPrioridad
    // pre:  cp no esta llena
    // post: valor queda agregado a cp con esa prioridad
    @Override
    public void insertar(int valor, int prioridad) {
        if (esLlena()) {
            throw new RuntimeException("Cola llena");
        }
        int i = cantidad - 1;
        // ">=" deja al nuevo debajo de los de igual prioridad: sale despues que ellos (FIFO)
        while (i >= 0 && prioridades[i] >= prioridad) {
            valores[i + 1] = valores[i];
            prioridades[i + 1] = prioridades[i];
            i--;
        }
        valores[i + 1] = valor;
        prioridades[i + 1] = prioridad;
        cantidad++;
    }

    // extraerMax(cp: ColaPrioridad) -> ColaPrioridad
    // pre:  cp no esta vacia
    // post: se elimina de cp el elemento de mayor prioridad;
    //       con igual prioridad, el que se inserto antes
    @Override
    public int extraerMax() {
        if (esVacia()) {
            throw new RuntimeException("Cola vacia");
        }
        cantidad--;
        return valores[cantidad];
    }

    // verMax(cp: ColaPrioridad) -> elemento
    // pre:  cp no esta vacia
    // post: devuelve el elemento de mayor prioridad (el que sacaria extraerMax) sin eliminarlo
    @Override
    public int verMax() {
        if (esVacia()) {
            throw new RuntimeException("Cola vacia");
        }
        return valores[cantidad - 1];
    }

    // prioridadMax(cp: ColaPrioridad) -> entero
    // pre:  cp no esta vacia
    // post: devuelve la prioridad del elemento que devuelve verMax, sin eliminarlo
    @Override
    public int prioridadMax() {
        if (esVacia()) {
            throw new RuntimeException("Cola vacia");
        }
        return prioridades[cantidad - 1];
    }

    // esVacia(cp: ColaPrioridad) -> boolean
    // post: devuelve true si cp no tiene elementos
    @Override
    public boolean esVacia() {
        return cantidad == 0;
    }

    // esLlena(cp: ColaPrioridad) -> boolean
    // post: devuelve true si cp alcanzo su capacidad maxima
    @Override
    public boolean esLlena() {
        return cantidad == capacidad;
    }
}
