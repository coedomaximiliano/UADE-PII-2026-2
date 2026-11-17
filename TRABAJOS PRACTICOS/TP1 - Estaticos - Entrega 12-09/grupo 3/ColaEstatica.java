// TP1 - Algoritmos y Estructuras de Datos II
// Pedro Stella - Bautista Chavarri

/**
 * TDA Cola de enteros con arreglo, cola lineal simple (Variante A).
 * encolar, desencolar y frente son O(1): no se corre ningun elemento, solo cambian frente y cantidad.
 * frente nunca vuelve a 0: los lugares que se liberan al desencolar no se reutilizan,
 * asi que la cola puede quedar llena aunque haya lugares libres al principio del arreglo.
 */
public class ColaEstatica implements Cola {

    private int[] datos;
    private int frente;     // indice del primer elemento
    private int cantidad;   // cuantos elementos hay (el fin es frente + cantidad)
    private int capacidad;

    // crear() -> Cola
    // post: devuelve una cola vacia
    public ColaEstatica(int capacidad) {
        this.capacidad = capacidad;
        this.datos = new int[capacidad];
        this.frente = 0;
        this.cantidad = 0;
    }

    // encolar(c: Cola, x: elemento) -> Cola
    // pre:  c no esta llena
    // post: x queda al final de c
    @Override
    public void encolar(int x) {
        if (esLlena()) {
            throw new RuntimeException("Cola llena");
        }
        datos[frente + cantidad] = x;
        cantidad++;
    }

    // desencolar(c: Cola) -> Cola
    // pre:  c no esta vacia
    // post: se elimina el elemento del frente de c
    @Override
    public int desencolar() {
        if (esVacia()) {
            throw new RuntimeException("Cola vacia");
        }
        int x = datos[frente];
        frente++;   // el lugar que queda libre no se vuelve a usar
        cantidad--;
        return x;
    }

    // frente(c: Cola) -> elemento
    // pre:  c no esta vacia
    // post: devuelve el elemento del frente sin eliminarlo
    @Override
    public int frente() {
        if (esVacia()) {
            throw new RuntimeException("Cola vacia");
        }
        return datos[frente];
    }

    // esVacia(c: Cola) -> boolean
    // post: devuelve true si c no tiene elementos
    @Override
    public boolean esVacia() {
        return cantidad == 0;
    }

    // esLlena(c: Cola) -> boolean
    // post: devuelve true si c alcanzo su capacidad maxima
    @Override
    public boolean esLlena() {
        // no es cantidad == capacidad: los lugares liberados antes de frente no se reutilizan
        return frente + cantidad == capacidad;
    }
}
