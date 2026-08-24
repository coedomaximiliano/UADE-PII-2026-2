import java.util.ArrayList;

/**
 * TDA ColaConPrioridad
 *
 * crear() -> CCP                          post: devuelve una CCP vacia
 * insertar(c, x, prioridad) -> CCP        post: x queda insertado
 * extraerMax(c) -> CCP                    pre: c no esta vacia / post: se elimina el de mayor prioridad
 * verMax(c) -> elemento                   pre: c no esta vacia
 * esVacia(c) -> boolean
 *
 * Implementacion: heap (monticulo) binario de maximos sobre un ArrayList.
 * El elemento de mayor prioridad siempre queda en la raiz (posicion 0),
 * por lo que verMax() es O(1) e insertar/extraerMax son O(log n).
 */
public class ColaConPrioridad<T> {

    // Clase interna para asociar cada elemento con su prioridad
    private class Nodo {
        T elemento;
        int prioridad;

        Nodo(T elemento, int prioridad) {
            this.elemento = elemento;
            this.prioridad = prioridad;
        }
    }

    private ArrayList<Nodo> heap;

    // crear() -> CCP
    // post: devuelve una CCP vacia
    public ColaConPrioridad() {
        heap = new ArrayList<>();
    }

    // esVacia(c) -> boolean
    public boolean esVacia() {
        return heap.isEmpty();
    }

    // insertar(c, x, prioridad) -> CCP
    // post: x queda insertado
    public void insertar(T x, int prioridad) {
        Nodo nuevo = new Nodo(x, prioridad);
        heap.add(nuevo);
        flotar(heap.size() - 1);
    }

    // verMax(c) -> elemento
    // pre: c no esta vacia
    public T verMax() {
        if (esVacia()) {
            throw new IllegalStateException("Error: la cola de prioridad esta vacia");
        }
        return heap.get(0).elemento;
    }

    // extraerMax(c) -> CCP
    // pre: c no esta vacia
    // post: se elimina el de mayor prioridad
    public void extraerMax() {
        if (esVacia()) {
            throw new IllegalStateException("Error: la cola de prioridad esta vacia");
        }
        int ultimo = heap.size() - 1;
        heap.set(0, heap.get(ultimo));
        heap.remove(ultimo);
        if (!esVacia()) {
            hundir(0);
        }
    }

    // ================= Operaciones auxiliares del heap =================

    private int padre(int i) {
        return (i - 1) / 2;
    }

    private int hijoIzq(int i) {
        return 2 * i + 1;
    }

    private int hijoDer(int i) {
        return 2 * i + 2;
    }

    private void intercambiar(int i, int j) {
        Nodo aux = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, aux);
    }

    // Sube el nodo en la posicion i mientras tenga mayor prioridad que su padre
    private void flotar(int i) {
        while (i > 0 && heap.get(i).prioridad > heap.get(padre(i)).prioridad) {
            intercambiar(i, padre(i));
            i = padre(i);
        }
    }

    // Baja el nodo en la posicion i mientras algun hijo tenga mayor prioridad
    private void hundir(int i) {
        int n = heap.size();
        while (true) {
            int mayor = i;
            int izq = hijoIzq(i);
            int der = hijoDer(i);

            if (izq < n && heap.get(izq).prioridad > heap.get(mayor).prioridad) {
                mayor = izq;
            }
            if (der < n && heap.get(der).prioridad > heap.get(mayor).prioridad) {
                mayor = der;
            }
            if (mayor == i) {
                break;
            }
            intercambiar(i, mayor);
            i = mayor;
        }
    }

    // ================= Programa de prueba =================

    public static void main(String[] args) {
        ColaConPrioridad<String> cola = new ColaConPrioridad<>();

        System.out.println("Esta vacia? " + cola.esVacia());

        cola.insertar("Tarea de bajo impacto", 1);
        cola.insertar("Incendio en el servidor", 10);
        cola.insertar("Revisar mails", 2);
        cola.insertar("Caida de produccion", 9);
        cola.insertar("Reunion de equipo", 5);

        System.out.println("Esta vacia? " + cola.esVacia());
        System.out.println("Elemento de mayor prioridad: " + cola.verMax());

        System.out.println("\nExtrayendo elementos en orden de prioridad:");
        while (!cola.esVacia()) {
            System.out.println("-> " + cola.verMax());
            cola.extraerMax();
        }

        System.out.println("\nEsta vacia? " + cola.esVacia());

        try {
            cola.verMax();
        } catch (IllegalStateException e) {
            System.out.println("Error esperado al pedir verMax() en cola vacia: " + e.getMessage());
        }
    }
}
