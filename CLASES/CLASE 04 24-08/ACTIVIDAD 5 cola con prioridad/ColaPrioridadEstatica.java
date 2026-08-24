/**
 * ALGORITMOS Y ESTRUCTURAS DE DATOS II - Clase 3
 * TDA Cola con Prioridad - Implementacion estatica (array desordenado)
 *
 * insertar() es O(1): agrega siempre al final, sin ordenar nada.
 * extraerMax() / verMax() son O(n): hay que recorrer todo el array
 * para encontrar el elemento de mayor prioridad.
 */
public class ColaPrioridadEstatica {
    private String[] valores;
    private int[] prioridades;
    private int cantidad;
    private int capacidad;

    // crear() -> CCP
    public ColaPrioridadEstatica(int capacidad) {
        this.capacidad = capacidad;
        this.valores = new String[capacidad];
        this.prioridades = new int[capacidad];
        this.cantidad = 0;
    }

    // esVacia(c) -> boolean
    public boolean esVacia() {
        return cantidad == 0;
    }

    // esLlena(c) -> boolean
    public boolean esLlena() {
        return cantidad == capacidad;
    }

    // insertar(c, x, prioridad) -> CCP
    public void insertar(String x, int prioridad) {
        if (esLlena()) {
            throw new RuntimeException("Cola de prioridad llena");
        }
        valores[cantidad] = x;
        prioridades[cantidad] = prioridad;
        cantidad++;
    }

    // indice del elemento de mayor prioridad; ante empate, se queda con
    // el primero encontrado (el insertado antes) -> implementacion estable
    private int indiceMax() {
        int idxMax = 0;
        for (int i = 1; i < cantidad; i++) {
            if (prioridades[i] > prioridades[idxMax]) {
                idxMax = i;
            }
        }
        return idxMax;
    }

    // verMax(c) -> elemento
    public String verMax() {
        if (esVacia()) {
            throw new RuntimeException("Vacia");
        }
        return valores[indiceMax()];
    }

    // extraerMax(c) -> CCP
    public String extraerMax() {
        if (esVacia()) {
            throw new RuntimeException("Vacia");
        }
        int idx = indiceMax();
        String valor = valores[idx];
        for (int i = idx; i < cantidad - 1; i++) {
            valores[i] = valores[i + 1];
            prioridades[i] = prioridades[i + 1];
        }
        cantidad--;
        return valor;
    }

    // tamanio(c) -> entero
    public int tamanio() {
        return cantidad;
    }
}
