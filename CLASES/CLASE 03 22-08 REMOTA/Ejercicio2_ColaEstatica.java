/**
 * ALGORITMOS Y ESTRUCTURAS DE DATOS II - Clase 2
 * Ejercicio 2: Implementar TDA Cola con array estatico
 *
 * Consigna:
 *  - Atributos sugeridos: array datos, capacidad, indices inicio y fin (y/o cantidad).
 *  - Implementar: crear (constructor), encolar, desencolar, primero, esVacia, esLlena.
 *  - Desafio: al desencolar, NO correr todos los elementos del array;
 *    usar un indice 'inicio' que avance (cola circular con el operador %).
 *  - Prueba: encolar 5 elementos, desencolar 2, encolar 2 mas, y verificar
 *    que la cola circular reutiliza los lugares que quedaron libres al principio.
 */
public class Ejercicio2_ColaEstatica {

    // ============================================================
    // Clase ColaEstatica (misma solucion vista en la puesta en comun)
    // ============================================================
    static class ColaEstatica {
        private int[] datos;
        private int inicio;     // indice del primer elemento
        private int fin;        // indice del proximo lugar libre
        private int cantidad;   // cuantos elementos hay actualmente
        private int capacidad;

        // crear() -> Cola
        public ColaEstatica(int capacidad) {
            this.capacidad = capacidad;
            this.datos = new int[capacidad];
            this.inicio = 0;
            this.fin = 0;
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

        // encolar(c, x) -> Cola
        public void encolar(int x) {
            if (esLlena()) {
                throw new RuntimeException("Cola llena");
            }
            datos[fin] = x;
            fin = (fin + 1) % capacidad;   // clave: circular, "da la vuelta" al 0
            cantidad++;
        }

        // desencolar(c) -> Cola
        public int desencolar() {
            if (esVacia()) {
                throw new RuntimeException("Cola vacia");
            }
            int valor = datos[inicio];
            inicio = (inicio + 1) % capacidad;  // clave: circular
            cantidad--;
            return valor;
        }

        // primero(c) -> elemento
        public int primero() {
            if (esVacia()) {
                throw new RuntimeException("Cola vacia");
            }
            return datos[inicio];
        }

        // utilidad para las pruebas
        public int tamanio() {
            return cantidad;
        }
    }

    // ============================================================
    // main(): prueba paso a paso del desafio de la circularidad
    // ============================================================
    public static void main(String[] args) {

        ColaEstatica c = new ColaEstatica(5);

        System.out.println("=== Encolando 5 elementos (10,20,30,40,50) ===");
        c.encolar(10);
        c.encolar(20);
        c.encolar(30);
        c.encolar(40);
        c.encolar(50);
        System.out.println("Tamanio: " + c.tamanio() + " (deberia ser 5, cola llena)");

        System.out.println("\n=== Desencolando 2 elementos ===");
        System.out.println("Desencolado: " + c.desencolar());  // 10
        System.out.println("Desencolado: " + c.desencolar());  // 20
        System.out.println("Tamanio: " + c.tamanio() + " (deberia ser 3)");

        System.out.println("\n=== Encolando 2 elementos mas (60,70) ===");
        // En este punto, sin indices circulares, esto fallaria por
        // "falta de lugar" aunque en realidad quedaron 2 lugares
        // libres al principio del array (donde estaban el 10 y el 20).
        // Gracias al operador %, fin "da la vuelta" y los reutiliza.
        c.encolar(60);
        c.encolar(70);
        System.out.println("Tamanio: " + c.tamanio() + " (deberia ser 5, cola llena de nuevo)");

        System.out.println("\n=== Vaciando la cola completa (deberia salir 30,40,50,60,70) ===");
        while (!c.esVacia()) {
            System.out.println(c.desencolar());
        }

        System.out.println("\n=== Probando la excepcion de cola vacia ===");
        try {
            c.desencolar();
        } catch (RuntimeException e) {
            System.out.println("Excepcion capturada correctamente: " + e.getMessage());
        }

        System.out.println("\n=== Que pasa si NO fuera circular (para comparar) ===");
        System.out.println("Sin el operador %, 'fin' seguiria creciendo sin volver a 0.");
        System.out.println("Al llegar a fin == capacidad, encolar() lanzaria 'Cola llena'");
        System.out.println("aunque hubiera lugares libres al principio del array.");
        System.out.println("Esa es la ventaja de la cola circular: reutiliza el espacio,");
        System.out.println("y sus operaciones siguen siendo O(1).");
    }
}
