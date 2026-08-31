/**
 * ALGORITMOS Y ESTRUCTURAS DE DATOS II - Clase 4
 * Ejercicio 1: resolucion de problemas combinando TDAs -- Cache con
 * reemplazo FIFO.
 *
 * Combina:
 *   - TDA Cola (Clase 3, implementacion estatica circular): guarda las
 *     claves EN ORDEN DE LLEGADA, para saber cual descartar primero
 *     cuando la cache se llena.
 *   - TDA Diccionario dinamico (esta clase): guarda clave -> valor,
 *     para poder consultar y actualizar en O(n) sin recorrer un array
 *     con logica de posiciones.
 *
 * Idea del problema: una cache de resultados con capacidad maxima fija.
 * Si se pide poner() una clave nueva y la cache ya esta llena, hay que
 * descartar la clave MAS VIEJA (la que llego primero) antes de agregar
 * la nueva -- politica FIFO (First In, First Out), la misma idea de la
 * Cola. El Diccionario, por su parte, es quien realmente responde
 * "?cual es el valor de esta clave?" -- cada TDA hace lo que mejor sabe
 * hacer, y el problema se resuelve combinando ambos.
 */
public class Ejercicio1_CacheFIFO {

    // ============================================================
    // Cola estatica circular (identica en espiritu a la de Clase 3),
    // aca especializada para guardar claves (String).
    // ============================================================
    static class Cola {
        private Object[] datos;
        private int inicio, fin, cantidad, capacidad;

        Cola(int capacidad) {
            this.capacidad = capacidad;
            this.datos = new Object[capacidad];
            this.inicio = 0;
            this.fin = 0;
            this.cantidad = 0;
        }

        boolean esVacia() {
            return cantidad == 0;
        }

        void encolar(Object x) {
            if (cantidad == capacidad) {
                throw new RuntimeException("Cola llena");
            }
            datos[fin] = x;
            fin = (fin + 1) % capacidad;
            cantidad++;
        }

        Object desencolar() {
            if (esVacia()) {
                throw new RuntimeException("Cola vacia");
            }
            Object valor = datos[inicio];
            inicio = (inicio + 1) % capacidad;
            cantidad--;
            return valor;
        }
    }

    // ============================================================
    // CacheFIFO: combina Cola (orden de llegada) + DiccionarioDinamico
    // (clave -> valor).
    // ============================================================
    static class CacheFIFO {
        private DiccionarioDinamico valores;   // clave -> valor
        private Cola ordenLlegada;             // claves, en orden de insercion
        private int capacidad;

        CacheFIFO(int capacidad) {
            this.capacidad = capacidad;
            this.valores = new DiccionarioDinamico();
            this.ordenLlegada = new Cola(capacidad);
        }

        void poner(String clave, String valor) {
            if (valores.existeClave(clave)) {
                // ya estaba: se actualiza el valor, no cuenta como
                // insercion nueva (no hace falta tocar la Cola).
                valores.definir(clave, valor);
                System.out.println("poner(\"" + clave + "\", ...) -> ya existia, se actualiza el valor");
                return;
            }
            if (valores.cantidadClaves() == capacidad) {
                String claveDescartada = (String) ordenLlegada.desencolar();
                valores.eliminar(claveDescartada);
                System.out.println("poner(\"" + clave + "\", ...) -> cache llena, se descarta \""
                        + claveDescartada + "\" (la mas vieja)");
            } else {
                System.out.println("poner(\"" + clave + "\", ...) -> insercion nueva");
            }
            valores.definir(clave, valor);
            ordenLlegada.encolar(clave);
        }

        String obtener(String clave) {
            if (!valores.existeClave(clave)) {
                return null;
            }
            return (String) valores.obtener(clave);
        }

        int cantidad() {
            return valores.cantidadClaves();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Cache con capacidad 3, politica FIFO ===");
        CacheFIFO cache = new CacheFIFO(3);

        cache.poner("consulta_A", "resultado_A");
        cache.poner("consulta_B", "resultado_B");
        cache.poner("consulta_C", "resultado_C");
        System.out.println("cantidad() = " + cache.cantidad() + " (cache llena)");

        System.out.println();
        System.out.println("=== poner(\"consulta_D\", ...) -> la cache esta llena, debe descartar la mas vieja ===");
        cache.poner("consulta_D", "resultado_D");
        System.out.println("cantidad() = " + cache.cantidad());

        System.out.println();
        System.out.println("=== obtener(\"consulta_A\") tras haber sido descartada ===");
        System.out.println("obtener(\"consulta_A\") = " + cache.obtener("consulta_A") + " (null: ya no esta)");
        System.out.println("obtener(\"consulta_D\") = " + cache.obtener("consulta_D"));

        System.out.println();
        System.out.println("=== poner(\"consulta_C\", ...) sobre una clave ya presente (actualiza, no descarta) ===");
        cache.poner("consulta_C", "resultado_C_v2");
        System.out.println("obtener(\"consulta_C\") = " + cache.obtener("consulta_C"));
        System.out.println("cantidad() = " + cache.cantidad() + " (no crecio)");

        System.out.println();
        System.out.println("=== poner(\"consulta_E\", ...) -> vuelve a llenarse y descarta a \"consulta_B\" ===");
        cache.poner("consulta_E", "resultado_E");
        System.out.println("obtener(\"consulta_B\") = " + cache.obtener("consulta_B") + " (null: fue descartada)");
        System.out.println("obtener(\"consulta_C\") = " + cache.obtener("consulta_C")
                + " (sigue: se actualizo, no se re-encolo, asi que no era la mas vieja)");
    }
}
