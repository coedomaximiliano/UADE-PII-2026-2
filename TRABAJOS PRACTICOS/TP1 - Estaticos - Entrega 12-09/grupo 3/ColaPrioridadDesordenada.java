// TP1 - Algoritmos y Estructuras de Datos II
// Pedro Stella - Bautista Chavarri

/**
 * TDA Cola con Prioridad de enteros con dos arreglos paralelos SIN ORDEN (Variante A).
 * insertar agrega en el primer lugar libre: O(1).
 * extraerMax, verMax y prioridadMax recorren todo el arreglo para hallar el maximo: O(n).
 */
public class ColaPrioridadDesordenada implements ColaPrioridad {

    private int[] valores;
    private int[] prioridades;  // prioridades[i] es la prioridad de valores[i]
    private int cantidad;
    private int capacidad;

    // crear() -> ColaPrioridad
    // post: devuelve una cola con prioridad vacia
    public ColaPrioridadDesordenada(int capacidad) {
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
        valores[cantidad] = valor;
        prioridades[cantidad] = prioridad;
        cantidad++;
    }

    // indice del elemento de mayor prioridad; ante empate se queda con el primero
    // encontrado (el insertado antes) -> implementacion estable
    private int indiceMax() {
        int idxMax = 0;
        for (int i = 1; i < cantidad; i++) {
            if (prioridades[i] > prioridades[idxMax]) {
                idxMax = i;
            }
        }
        return idxMax;
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
        int idx = indiceMax();
        int valor = valores[idx];
        // tapa el hueco corriendo los siguientes: pisarlo con el ultimo romperia el desempate FIFO
        for (int i = idx; i < cantidad - 1; i++) {
            valores[i] = valores[i + 1];
            prioridades[i] = prioridades[i + 1];
        }
        cantidad--;
        return valor;
    }

    // verMax(cp: ColaPrioridad) -> elemento
    // pre:  cp no esta vacia
    // post: devuelve el elemento de mayor prioridad (el que sacaria extraerMax) sin eliminarlo
    @Override
    public int verMax() {
        if (esVacia()) {
            throw new RuntimeException("Cola vacia");
        }
        return valores[indiceMax()];
    }

    // prioridadMax(cp: ColaPrioridad) -> entero
    // pre:  cp no esta vacia
    // post: devuelve la prioridad del elemento que devuelve verMax, sin eliminarlo
    @Override
    public int prioridadMax() {
        if (esVacia()) {
            throw new RuntimeException("Cola vacia");
        }
        return prioridades[indiceMax()];
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
