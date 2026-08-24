/**
 * ALGORITMOS Y ESTRUCTURAS DE DATOS II - Clase 3
 * Ejercicio 2: Implementar el TDA Conjunto completo
 *
 * Consigna:
 *  - Implementar ConjuntoEstatico completo (pertenece, agregar,
 *    eliminar, esVacio) y probarlo.
 *  - Crear un conjunto de capacidad 10 y agregar 3, 7, 3, 5, 7, 9
 *    (repetidos a proposito).
 *  - Verificar que pertenece() funciona antes y despues de cada agregar().
 *  - Eliminar el 7 y comprobar con pertenece(7) que ya no esta.
 *  - Imprimir el conjunto final: cuantos elementos quedaron? en que
 *    orden? por que ese orden puede parecer "raro"?
 *  - (Si termina antes) metodo interseccion(otro) -> nuevo Conjunto
 *    con los elementos comunes.
 */
public class Ejercicio2_Conjunto {

    // ============================================================
    // Clase ConjuntoEstatico (array sin duplicados)
    // ============================================================
    static class ConjuntoEstatico {
        private int[] elementos;
        private int cantidad;
        private int capacidad;

        // crear() -> Conjunto
        public ConjuntoEstatico(int capacidad) {
            this.capacidad = capacidad;
            this.elementos = new int[capacidad];
            this.cantidad = 0;
        }

        // pertenece(c, x) -> boolean
        public boolean pertenece(int x) {
            for (int i = 0; i < cantidad; i++) {
                if (elementos[i] == x) {
                    return true;
                }
            }
            return false;
        }

        // agregar(c, x) -> Conjunto
        public void agregar(int x) {
            if (pertenece(x)) {
                return; // no se duplica
            }
            if (cantidad == capacidad) {
                throw new RuntimeException("Conjunto lleno");
            }
            elementos[cantidad] = x;
            cantidad++;
        }

        // eliminar(c, x) -> Conjunto
        public void eliminar(int x) {
            for (int i = 0; i < cantidad; i++) {
                if (elementos[i] == x) {
                    // se "tapa" el hueco con el ultimo elemento;
                    // valido porque el Conjunto no tiene orden definido
                    elementos[i] = elementos[cantidad - 1];
                    cantidad--;
                    return;
                }
            }
        }

        // esVacio(c) -> boolean
        public boolean esVacio() {
            return cantidad == 0;
        }

        // tamanio(c) -> entero
        public int tamanio() {
            return cantidad;
        }

        // ---------- Desafio opcional ----------
        // interseccion(otro) -> Conjunto con los elementos comunes
        public ConjuntoEstatico interseccion(ConjuntoEstatico otro) {
            ConjuntoEstatico resultado = new ConjuntoEstatico(this.capacidad);
            for (int i = 0; i < this.cantidad; i++) {
                if (otro.pertenece(this.elementos[i])) {
                    resultado.agregar(this.elementos[i]);
                }
            }
            return resultado;
        }

        public void imprimir() {
            System.out.print("{ ");
            for (int i = 0; i < cantidad; i++) {
                System.out.print(elementos[i] + " ");
            }
            System.out.println("}");
        }
    }

    // ============================================================
    // main(): prueba paso a paso de la consigna
    // ============================================================
    public static void main(String[] args) {

        ConjuntoEstatico c = new ConjuntoEstatico(10);

        System.out.println("=== Agregando 3, 7, 3, 5, 7, 9 (con repetidos a proposito) ===");
        int[] aAgregar = {3, 7, 3, 5, 7, 9};
        for (int x : aAgregar) {
            boolean estabaAntes = c.pertenece(x);
            c.agregar(x);
            boolean estaDespues = c.pertenece(x);
            System.out.println("agregar(" + x + ")  ->  pertenece antes: " + estabaAntes
                    + " | pertenece despues: " + estaDespues
                    + " | tamanio: " + c.tamanio());
        }

        System.out.print("\nConjunto tras las 6 inserciones: ");
        c.imprimir();
        System.out.println("Tamanio: " + c.tamanio() + " (deberia ser 4: no se duplicaron el 3 ni el 7)");

        System.out.println("\n=== Eliminando el 7 ===");
        c.eliminar(7);
        System.out.println("pertenece(7) despues de eliminar: " + c.pertenece(7) + " (deberia ser false)");
        System.out.print("Conjunto final: ");
        c.imprimir();
        System.out.println("Tamanio final: " + c.tamanio() + " (deberia ser 3)");

        System.out.println("\n=== Por que el orden puede parecer 'raro' ===");
        System.out.println("eliminar() 'tapa' el hueco copiando el ULTIMO elemento en el");
        System.out.println("lugar del que se borro, en vez de correr todo el array. Es");
        System.out.println("valido porque el TDA Conjunto no define ningun orden entre sus");
        System.out.println("elementos -- por eso el array puede terminar en un orden que no");
        System.out.println("coincide con el orden en que se fueron insertando.");

        System.out.println("\n=== Desafio opcional: interseccion() ===");
        ConjuntoEstatico c2 = new ConjuntoEstatico(10);
        c2.agregar(5);
        c2.agregar(9);
        c2.agregar(100);
        System.out.print("Conjunto c2: ");
        c2.imprimir();
        ConjuntoEstatico inter = c.interseccion(c2);
        System.out.print("interseccion(c, c2): ");
        inter.imprimir();
        System.out.println("(deberia contener los elementos que estan en AMBOS: 5 y 9)");
    }
}
