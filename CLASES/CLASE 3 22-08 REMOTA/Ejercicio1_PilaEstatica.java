/**
 * ALGORITMOS Y ESTRUCTURAS DE DATOS II - Clase 2
 * Ejercicio 1: Probar y extender la Pila estatica
 *
 * Consigna:
 *  - Crear una pila de capacidad 5 y apilar los numeros del 1 al 5.
 *  - Intentar apilar un 6to elemento y verificar que se lanza la excepcion "Pila llena".
 *  - Desapilar todos los elementos e imprimirlos (deben salir en orden inverso: 5,4,3,2,1).
 *  - Agregar un metodo tamanio() que devuelva la cantidad de elementos actuales.
 *  - (Si termina antes) Agregar un metodo vaciar() que deje la pila como recien creada.
 */
public class Ejercicio1_PilaEstatica {

    // ============================================================
    // Clase PilaEstatica (la misma vista en el Bloque 4 de la Clase 2)
    // ============================================================
    static class PilaEstatica {
        private int[] datos;
        private int tope;       // cantidad de elementos / proximo lugar libre
        private int capacidad;

        // crear() -> Pila
        public PilaEstatica(int capacidad) {
            this.capacidad = capacidad;
            this.datos = new int[capacidad];
            this.tope = 0;
        }

        // esVacia(p) -> boolean
        public boolean esVacia() {
            return tope == 0;
        }

        // esLlena(p) -> boolean
        public boolean esLlena() {
            return tope == capacidad;
        }

        // apilar(p, x) -> Pila
        public void apilar(int x) {
            if (esLlena()) {
                throw new RuntimeException("Pila llena");
            }
            datos[tope] = x;
            tope++;
        }

        // desapilar(p) -> Pila
        public int desapilar() {
            if (esVacia()) {
                throw new RuntimeException("Pila vacia");
            }
            tope--;
            return datos[tope];
        }

        // tope(p) -> elemento
        public int tope() {
            if (esVacia()) {
                throw new RuntimeException("Pila vacia");
            }
            return datos[tope - 1];
        }

        // ---------- Extensiones pedidas en el ejercicio ----------

        // tamanio(): cantidad de elementos actuales.
        // Es directamente el atributo 'tope', ya que tope siempre
        // representa "cuantos elementos hay apilados".
        public int tamanio() {
            return tope;
        }

        // vaciar(): deja la pila en el mismo estado que devuelve crear().
        // No hace falta "borrar" los valores del array: alcanza con
        // resetear tope a 0, porque las posiciones viejas quedan
        // logicamente inaccesibles (se van a sobreescribir en el
        // proximo apilar()).
        public void vaciar() {
            tope = 0;
        }
    }

    // ============================================================
    // main(): prueba paso a paso de la consigna
    // ============================================================
    public static void main(String[] args) {

        PilaEstatica p = new PilaEstatica(5);

        System.out.println("=== Apilando 1 al 5 ===");
        for (int i = 1; i <= 5; i++) {
            p.apilar(i);
            System.out.println("Apilado: " + i + "  (tamanio actual: " + p.tamanio() + ")");
        }

        System.out.println("\n=== Intentando apilar un 6to elemento ===");
        try {
            p.apilar(6);
            System.out.println("ERROR: no deberia llegar aca, la pila estaba llena.");
        } catch (RuntimeException e) {
            System.out.println("Excepcion capturada correctamente: " + e.getMessage());
        }

        System.out.println("\n=== Desapilando todo (deberia salir 5,4,3,2,1) ===");
        while (!p.esVacia()) {
            System.out.println(p.desapilar());
        }

        System.out.println("\n=== Probando vaciar() ===");
        p.apilar(100);
        p.apilar(200);
        System.out.println("Tamanio antes de vaciar: " + p.tamanio());
        p.vaciar();
        System.out.println("Tamanio despues de vaciar: " + p.tamanio());
        System.out.println("esVacia() despues de vaciar: " + p.esVacia());
    }
}
