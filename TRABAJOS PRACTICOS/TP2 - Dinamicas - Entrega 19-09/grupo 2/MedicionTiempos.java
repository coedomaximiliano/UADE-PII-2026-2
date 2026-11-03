/**
 * Parte 3.1 -- Medicion empirica.
 *
 * Mide el TIEMPO REAL de definir() y obtener() en las dos
 * implementaciones del Diccionario, para n = 1.000 / 10.000 / 100.000
 * claves ya cargadas, usando la tecnica medirNanos() de la Clase 5
 * (warmup + mejor tiempo de varias repeticiones).
 *
 * Detalles pedidos por el enunciado:
 *  - definir(): cada llamada medida usa una clave NUEVA (nunca vista),
 *    tomada de un contador que arranca en n. Como se miden pocas
 *    repeticiones, el tamano crece de forma despreciable frente a n.
 *  - obtener(): el peor caso NO es la misma clave en las dos. En la
 *    dinamica se busca la PRIMER clave insertada (la 0), que quedo al
 *    final de la cadena. En la estatica se busca la ULTIMA insertada
 *    (indice n-1), que es la que la busqueda lineal recorre entera.
 *    Asi ambas recorren las n claves y la comparacion es justa.
 *
 * Los tiempos reales se vuelcan a mano en la tabla del informe (3.1).
 */
public class MedicionTiempos {

    interface Operacion {
        void ejecutar();
    }

    // corre la operacion con un warmup previo y devuelve el MEJOR tiempo
    // (en nanosegundos) de 'repeticiones' corridas.
    static long medirNanos(Operacion op, int repeticiones) {
        for (int i = 0; i < Math.min(3, repeticiones); i++) {
            op.ejecutar();
        }
        long mejor = Long.MAX_VALUE;
        for (int i = 0; i < repeticiones; i++) {
            long inicio = System.nanoTime();
            op.ejecutar();
            long fin = System.nanoTime();
            mejor = Math.min(mejor, fin - inicio);
        }
        return mejor;
    }

    static String formatear(long nanos) {
        if (nanos < 1_000) {
            return nanos + " ns";
        } else if (nanos < 1_000_000) {
            return String.format("%.2f us", nanos / 1_000.0);
        } else {
            return String.format("%.2f ms", nanos / 1_000_000.0);
        }
    }

    public static void main(String[] args) {
        int[] tamanios = { 1_000, 10_000, 100_000 };
        int reps = 7;
        int margen = reps + 5;     // lugar extra para las claves nuevas de definir()

        System.out.println("==========================================================================");
        System.out.println(" TIEMPOS REALES -- definir() y obtener()  (mejor de " + reps + " repeticiones)");
        System.out.println("==========================================================================");
        System.out.printf("%-10s | %-14s | %-14s | %-14s | %-14s%n",
                "n", "definir EST", "definir DIN", "obtener EST", "obtener DIN");
        System.out.println("--------------------------------------------------------------------------");

        for (int n : tamanios) {
            // ---- estatico con n claves cargadas ----
            final DiccionarioEstatico est = new DiccionarioEstatico(n + margen);
            for (int i = 0; i < n; i++) {
                est.definir(i, i * 10);
            }
            final int[] nuevaEst = { n };   // contador de claves nuevas
            long defEst = medirNanos(() -> est.definir(nuevaEst[0]++, 0), reps);
            // peor caso estatica: claveAIndice recorre i=0..cantidad-1, asi que
            // la mas cara es la ULTIMA insertada (queda en el indice n-1).
            final int ultimaEst = n - 1;
            long getEst = medirNanos(() -> est.obtener(ultimaEst), reps);

            // ---- dinamico con n claves cargadas ----
            final DiccionarioDinamico din = new DiccionarioDinamico();
            for (int i = 0; i < n; i++) {
                din.definir(i, i * 10);
            }
            final int[] nuevaDin = { n };
            long defDin = medirNanos(() -> din.definir(nuevaDin[0]++, 0), reps);
            // peor caso dinamica: buscarNodo arranca desde la cabeza (ultima
            // insertada); la mas cara es la PRIMERA insertada (clave 0), que
            // quedo al final de la cadena.
            long getDin = medirNanos(() -> din.obtener(0), reps);

            System.out.printf("%-10d | %-14s | %-14s | %-14s | %-14s%n",
                    n, formatear(defEst), formatear(defDin), formatear(getEst), formatear(getDin));
        }

        System.out.println("--------------------------------------------------------------------------");
        System.out.println("(Volcar estos valores en la tabla 3.1 del informe.)");
    }
}
