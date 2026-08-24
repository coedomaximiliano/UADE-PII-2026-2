/**
 * ALGORITMOS Y ESTRUCTURAS DE DATOS II - Clase 3
 * Ejercicio 1: Probar la Cola con Prioridad
 *
 * Consigna:
 *  - Implementar ColaPrioridadEstatica (version desordenada).
 *  - Insertar 5 tareas con distintas prioridades
 *    (Backup=1, Incendio=5, Email=2, Reunion=3, Deploy=4).
 *  - Extraer todas con extraerMax() e imprimir: deberian salir
 *    en orden de prioridad descendente.
 *  - Pensar: con 1.000.000 de elementos, cuantas comparaciones
 *    hace CADA extraerMax()?
 */
public class Ejercicio1_ColaPrioridad {

    // ============================================================
    // Clase ColaPrioridadEstatica (array desordenado)
    // ============================================================
    static class ColaPrioridadEstatica {
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

        // indice del elemento de mayor prioridad; ante empate, se queda
        // con el primero encontrado -> implementacion estable
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

    // ============================================================
    // main(): prueba paso a paso de la consigna
    // ============================================================
    public static void main(String[] args) {

        ColaPrioridadEstatica cp = new ColaPrioridadEstatica(5);

        System.out.println("=== Insertando 5 tareas con distintas prioridades ===");
        cp.insertar("Backup", 1);
        cp.insertar("Incendio", 5);
        cp.insertar("Email", 2);
        cp.insertar("Reunion", 3);
        cp.insertar("Deploy", 4);
        System.out.println("Tamanio: " + cp.tamanio() + " (deberia ser 5)");

        System.out.println("\n=== verMax() antes de extraer ===");
        System.out.println("verMax(): " + cp.verMax() + " (deberia ser Incendio, prioridad 5)");

        System.out.println("\n=== Extrayendo todas con extraerMax() ===");
        System.out.println("(deberian salir en orden de prioridad DESCENDENTE)");
        while (!cp.esVacia()) {
            System.out.println("Extraido: " + cp.extraerMax());
        }
        System.out.println("Tamanio final: " + cp.tamanio() + " (deberia ser 0)");

        System.out.println("\n=== Probando la excepcion de cola vacia ===");
        try {
            cp.extraerMax();
        } catch (RuntimeException e) {
            System.out.println("Excepcion capturada correctamente: " + e.getMessage());
        }

        System.out.println("\n=== Respuesta a la pregunta de razonamiento ===");
        System.out.println("Con 1.000.000 de elementos, indiceMax() recorre el array");
        System.out.println("desde i=1 hasta cantidad-1, es decir 999.999 comparaciones");
        System.out.println("en CADA llamada a extraerMax(). Eso es O(n) por extraccion.");
    }
}
