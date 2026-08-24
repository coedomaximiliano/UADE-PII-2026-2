import java.util.Arrays;

/**
 * ALGORITMOS Y ESTRUCTURAS DE DATOS II - Big O
 *
 * Mide y loguea el TIEMPO REAL de ejecucion de los 4 tipos de costo,
 * cada uno probado con POCOS datos y con MUCHOS datos, para ver en la
 * practica como escala (o no escala) cada complejidad.
 *
 *   O(1)      - Constante    -> primero()
 *   O(n)      - Lineal       -> pertenece()
 *   O(n^2)    - Cuadratico   -> sumaDeTodosLosPares()
 *   O(log n)  - Logaritmico  -> busquedaBinaria()
 *
 * Tecnica de medicion: se corre cada operacion varias veces (con un
 * "warmup" previo para que la JVM termine de optimizar el codigo) y
 * se toma el MEJOR tiempo obtenido, para reducir el ruido de otras
 * cosas que puedan estar pasando en la maquina en ese momento.
 */
public class BigO_Tiempos {

    // ================================================================
    // Las 4 operaciones de ejemplo (version "silenciosa": sin println
    // adentro, para que el System.out no distorsione la medicion)
    // ================================================================

    // O(1) - CONSTANTE
    static int primero(int[] datos) {
        return datos[0];
    }

    // O(n) - LINEAL
    static boolean pertenece(int[] datos, int x) {
        for (int i = 0; i < datos.length; i++) {
            if (datos[i] == x) {
                return true;
            }
        }
        return false;
    }

    // O(n^2) - CUADRATICO
    // Recorre todos los pares (i, j) y acumula una suma -- mismo trabajo
    // que "mostrar todos los pares", pero sin imprimir cada uno (imprimir
    // millones de lineas ensuciaria la medicion y tardaria muchisimo mas
    // por el costo de la consola, no por el algoritmo en si).
    static long sumaDeTodosLosPares(int[] datos) {
        long suma = 0;
        int n = datos.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                suma += datos[i] + datos[j];
            }
        }
        return suma;
    }

    // O(log n) - LOGARITMICO (requiere array ordenado)
    static boolean busquedaBinaria(int[] datosOrdenados, int x) {
        int inicio = 0;
        int fin = datosOrdenados.length - 1;
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            if (datosOrdenados[medio] == x) {
                return true;
            } else if (datosOrdenados[medio] < x) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return false;
    }

    // ================================================================
    // Herramienta de medicion: corre una operacion varias veces y
    // devuelve el MEJOR tiempo (en nanosegundos), tras un warmup.
    // ================================================================
    interface Operacion {
        void ejecutar();
    }

    static long medirNanos(Operacion op, int repeticiones) {
        // warmup: se corre unas cuantas veces sin medir, para que la
        // JVM alcance a "calentar" (compilar JIT) el codigo
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

    // formatea nanosegundos en la unidad mas legible
    static String formatear(long nanos) {
        if (nanos < 1_000) {
            return nanos + " ns";
        } else if (nanos < 1_000_000) {
            return String.format("%.2f microsegundos", nanos / 1_000.0);
        } else {
            return String.format("%.2f ms", nanos / 1_000_000.0);
        }
    }

    static int[] arrayAleatorio(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = (int) (Math.random() * n * 10);
        }
        return a;
    }

    static int[] arrayOrdenado(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i * 2; // pares, ordenados de menor a mayor
        }
        return a;
    }

    // ================================================================
    // main(): corre cada tipo de Big O con POCOS y con MUCHOS datos
    // ================================================================
    public static void main(String[] args) {

        System.out.println("======================================================");
        System.out.println(" COMPARACION DE TIEMPOS REALES POR TIPO DE BIG O");
        System.out.println("======================================================\n");

        // ---------------------------------------------------------
        // O(1) - CONSTANTE
        // Como cada llamado es instantaneo, se mide el tiempo de
        // hacer 1.000.000 de llamados seguidos, para poder verlo
        // en el reloj. La idea a observar: ese tiempo NO cambia
        // (o cambia muy poco) aunque el array sea mucho mas grande.
        // ---------------------------------------------------------
        System.out.println("---- O(1) - CONSTANTE: primero(datos) ----");
        final int LLAMADOS_O1 = 1_000_000;

        int[] o1Chico = arrayAleatorio(100);
        int[] o1Grande = arrayAleatorio(10_000_000);

        long tO1Chico = medirNanos(() -> {
            for (int i = 0; i < LLAMADOS_O1; i++) primero(o1Chico);
        }, 5);
        long tO1Grande = medirNanos(() -> {
            for (int i = 0; i < LLAMADOS_O1; i++) primero(o1Grande);
        }, 5);

        System.out.println("Pocos datos   (n=" + o1Chico.length + "):   "
                + LLAMADOS_O1 + " llamados en " + formatear(tO1Chico));
        System.out.println("Muchos datos  (n=" + o1Grande.length + "): "
                + LLAMADOS_O1 + " llamados en " + formatear(tO1Grande));
        System.out.println("-> El tiempo es prácticamente el mismo: no depende de n.\n");

        // ---------------------------------------------------------
        // O(n) - LINEAL
        // Se busca un valor que NO esta en el array (peor caso: hay
        // que recorrerlo entero). El tiempo deberia crecer en
        // proporcion directa al tamaño del array.
        // ---------------------------------------------------------
        System.out.println("---- O(n) - LINEAL: pertenece(datos, x) ----");

        int[] onChico = arrayAleatorio(10_000);
        int[] onGrande = arrayAleatorio(10_000_000);
        int valorAusenteOn = -1; // nunca generado por arrayAleatorio (siempre >= 0)

        long tOnChico = medirNanos(() -> pertenece(onChico, valorAusenteOn), 20);
        long tOnGrande = medirNanos(() -> pertenece(onGrande, valorAusenteOn), 20);

        System.out.println("Pocos datos   (n=" + onChico.length + "):  " + formatear(tOnChico));
        System.out.println("Muchos datos  (n=" + onGrande.length + "): " + formatear(tOnGrande));
        double factorOn = (double) onGrande.length / onChico.length;
        double crecOn = (double) tOnGrande / tOnChico;
        System.out.printf("-> El array creció x%.0f y el tiempo creció x%.1f (parecido: es lineal).%n%n",
                factorOn, crecOn);

        // ---------------------------------------------------------
        // O(n^2) - CUADRATICO
        // Con "pocos" y "muchos" datos MUCHO mas chicos que los
        // anteriores, porque n^2 crece brutalmente: n=4.000 ya
        // significa 16.000.000 de operaciones en el bucle doble.
        // ---------------------------------------------------------
        System.out.println("---- O(n²) - CUADRATICO: sumaDeTodosLosPares(datos) ----");

        int[] on2Chico = arrayAleatorio(200);
        int[] on2Grande = arrayAleatorio(4_000);

        long tOn2Chico = medirNanos(() -> sumaDeTodosLosPares(on2Chico), 5);
        long tOn2Grande = medirNanos(() -> sumaDeTodosLosPares(on2Grande), 5);

        System.out.println("Pocos datos   (n=" + on2Chico.length + ", "
                + (on2Chico.length * on2Chico.length) + " pares): " + formatear(tOn2Chico));
        System.out.println("Muchos datos  (n=" + on2Grande.length + ", "
                + ((long) on2Grande.length * on2Grande.length) + " pares): " + formatear(tOn2Grande));
        double factorN2 = (double) on2Grande.length / on2Chico.length;
        double crecN2 = (double) tOn2Grande / tOn2Chico;
        System.out.printf("-> El array creció x%.0f, pero el tiempo creció x%.1f "
                + "(≈ %.0f² = %.0f -- crecimiento cuadrático).%n%n",
                factorN2, crecN2, factorN2, factorN2 * factorN2);

        // ---------------------------------------------------------
        // O(log n) - LOGARITMICO
        // Igual que en O(1), un solo llamado es demasiado rapido
        // para medir con precision, asi que se miden 1.000.000 de
        // llamados seguidos. La idea a observar: aunque el array
        // grande tiene 1000 VECES mas elementos, el tiempo casi
        // no cambia (unos pocos pasos mas por llamado).
        // ---------------------------------------------------------
        System.out.println("---- O(log n) - LOGARITMICO: busquedaBinaria(datos, x) ----");
        final int LLAMADOS_OLOGN = 1_000_000;

        int[] ologNChico = arrayOrdenado(10_000);
        int[] ologNGrande = arrayOrdenado(10_000_000);
        int valorAusenteLog = -1; // no existe en un array de pares >= 0

        long tOlogNChico = medirNanos(() -> {
            for (int i = 0; i < LLAMADOS_OLOGN; i++) busquedaBinaria(ologNChico, valorAusenteLog);
        }, 5);
        long tOlogNGrande = medirNanos(() -> {
            for (int i = 0; i < LLAMADOS_OLOGN; i++) busquedaBinaria(ologNGrande, valorAusenteLog);
        }, 5);

        System.out.println("Pocos datos   (n=" + ologNChico.length + "):   "
                + LLAMADOS_OLOGN + " llamados en " + formatear(tOlogNChico));
        System.out.println("Muchos datos  (n=" + ologNGrande.length + "): "
                + LLAMADOS_OLOGN + " llamados en " + formatear(tOlogNGrande));
        System.out.printf("-> El array creció x%.0f y el tiempo casi no cambió "
                + "(log2(%d) ≈ %.0f pasos como máximo).%n%n",
                (double) ologNGrande.length / ologNChico.length,
                ologNGrande.length,
                Math.ceil(Math.log(ologNGrande.length) / Math.log(2)));

        // ---------------------------------------------------------
        // RESUMEN FINAL
        // ---------------------------------------------------------
        System.out.println("======================================================");
        System.out.println(" RESUMEN");
        System.out.println("======================================================");
        System.out.printf("%-12s %-22s %-22s%n", "Tipo", "Pocos datos", "Muchos datos");
        System.out.printf("%-12s %-22s %-22s%n", "O(1)", formatear(tO1Chico), formatear(tO1Grande));
        System.out.printf("%-12s %-22s %-22s%n", "O(n)", formatear(tOnChico), formatear(tOnGrande));
        System.out.printf("%-12s %-22s %-22s%n", "O(n²)", formatear(tOn2Chico), formatear(tOn2Grande));
        System.out.printf("%-12s %-22s %-22s%n", "O(log n)", formatear(tOlogNChico), formatear(tOlogNGrande));
        System.out.println("\nCuanto más lento crece el tiempo de 'pocos' a 'muchos' datos,");
        System.out.println("más eficiente (escalable) es el algoritmo.");
    }
}
