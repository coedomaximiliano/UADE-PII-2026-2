import Implementaciones.DiccionarioEstatico;
import Implementaciones.DiccionarioDinamico;

public class TestBigO {
    // ================================================================
    // Herramienta de medicion: corre una operacion varias veces y
    // devuelve el MEJOR tiempo (en nanosegundos), tras un warmup.
    // ================================================================
    interface Operacion {
        void ejecutar();
    }

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
            return String.format("%.2f microsegundos", nanos / 1_000.0);
        } else {
            return String.format("%.2f ms", nanos / 1_000_000.0);
        }
    }

    // Carga n claves "clave0".."clave(n-1)" (orden de insercion)
    static DiccionarioEstatico crearEstatico(int n, int margen) {
        DiccionarioEstatico d = new DiccionarioEstatico(n + margen);
        for (int i = 0; i < n; i++) {
            d.definir("clave" + i, i);
        }
        return d;
    }

    static DiccionarioDinamico crearDinamico(int n) {
        DiccionarioDinamico d = new DiccionarioDinamico();
        for (int i = 0; i < n; i++) {
            d.definir("clave" + i, i);
        }
        return d;
    }

    public static void main(String[] args) {
        int[] tamanios = {1000, 10000, 100000};
        int repeticiones = 15;
        int margenNuevasClaves = repeticiones + 5; // lugar de sobra para las claves nuevas de definir()

        long[] defEst = new long[tamanios.length];
        long[] defDin = new long[tamanios.length];
        long[] obtEst = new long[tamanios.length];
        long[] obtDin = new long[tamanios.length];

        for (int idx = 0; idx < tamanios.length; idx++) {
            int n = tamanios[idx];

            // ---------- DEFINIR() ----------
            // Estatico: cada llamada medida usa una clave nueva, nunca vista antes.
            DiccionarioEstatico dEst = crearEstatico(n, margenNuevasClaves);
            final int[] contadorEst = {0};
            defEst[idx] = medirNanos(() -> {
                dEst.definir("nuevaEst" + contadorEst[0]++, 0);
            }, repeticiones);

            // Dinamico: idem, clave nueva en cada llamada.
            DiccionarioDinamico dDin = crearDinamico(n);
            final int[] contadorDin = {0};
            defDin[idx] = medirNanos(() -> {
                dDin.definir("nuevaDin" + contadorDin[0]++, 0);
            }, repeticiones);

            // ---------- OBTENER() ----------
            // Estatico: busca linealmente desde el indice 0, en orden de insercion.
            // El peor caso es la ULTIMA clave insertada (la que esta al final del arreglo).
            DiccionarioEstatico dEstObt = crearEstatico(n, 0);
            String claveEst = "clave" + (n - 1);
            obtEst[idx] = medirNanos(() -> {
                dEstObt.obtener(claveEst);
            }, repeticiones);

            // Dinamico: cada definir() nuevo se inserta a la CABEZA de la cadena.
            // Por lo tanto el peor caso es la PRIMERA clave insertada (quedo al final de la cadena).
            DiccionarioDinamico dDinObt = crearDinamico(n);
            String claveDin = "clave0";
            obtDin[idx] = medirNanos(() -> {
                dDinObt.obtener(claveDin);
            }, repeticiones);
        }

        // ---------------------------------------------------------
        // RESUMEN FINAL
        // ---------------------------------------------------------
        System.out.println("====================================================");
        System.out.println(" MEDICION DE TIEMPOS REALES ");
        System.out.println("====================================================");
        System.out.println(" .DEFINIR() ");
        System.out.println(" -Estatico-");
        for (int i = 0; i < tamanios.length; i++) {
            System.out.println(" n=" + tamanios[i] + " -- " + formatear(defEst[i]));
        }
        System.out.println(" -Dinamico-");
        for (int i = 0; i < tamanios.length; i++) {
            System.out.println(" n=" + tamanios[i] + " -- " + formatear(defDin[i]));
        }
        System.out.println("====================================================");
        System.out.println(" .OBTENER() ");
        System.out.println(" -Estatico-");
        for (int i = 0; i < tamanios.length; i++) {
            System.out.println(" n=" + tamanios[i] + " -- " + formatear(obtEst[i]));
        }
        System.out.println(" -Dinamico-");
        for (int i = 0; i < tamanios.length; i++) {
            System.out.println(" n=" + tamanios[i] + " -- " + formatear(obtDin[i]));
        }
        System.out.println("====================================================");
    }
}
