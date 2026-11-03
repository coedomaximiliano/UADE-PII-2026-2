// TP2 - Algoritmos y Estructuras de Datos II
// Pedro Stella - Bautista Chavarri

/**
 * Parte 3.1: mide el tiempo real de definir() y obtener() en las dos implementaciones
 * del Diccionario, con n = 1.000 / 10.000 / 100.000 claves ya cargadas.
 * La tecnica de medicion es la de clase (medirNanos): unas vueltas de warmup sin medir,
 * para que la JVM termine de optimizar el codigo, y despues el MEJOR tiempo de varias
 * repeticiones, para bajar el ruido de lo que este haciendo la maquina en ese momento.
 *
 * Dos detalles para que la comparacion sea justa:
 * - definir(): cada llamada medida usa una clave nueva, nunca antes vista, asi siempre
 *   se mide una insercion de verdad y no una actualizacion de valor.
 * - obtener(): se busca la clave del PEOR CASO de cada implementacion, que no es la
 *   misma en las dos. La dinamica agrega cada par al principio de la cadena, asi que la
 *   primera clave cargada termina ultima; la estatica agrega al final del arreglo y lo
 *   recorre desde el principio, asi que la peor es la ultima cargada. En los dos casos
 *   se recorren las n claves.
 */
public class MedicionTiempos {

    private static final int REPETICIONES = 20;
    private static final int WARMUP = 10;                                // vueltas sin medir, como en clase
    private static final int CLAVES_NUEVAS = REPETICIONES + WARMUP;      // una por cada llamada medida
    private static final int[] TAMANIOS = {1_000, 10_000, 100_000};

    // misma interfaz funcional que el codigo de clase, para poder pasar la operacion a medir
    interface Operacion {
        void ejecutar();
    }

    static long medirNanos(Operacion op, int repeticiones) {
        for (int i = 0; i < Math.min(WARMUP, repeticiones); i++) {
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
        System.out.println("TP2 - Parte 3.1: medicion empirica (definir y obtener)");
        System.out.println("Java " + System.getProperty("java.version") + " - " + System.getProperty("os.name"));
        System.out.println("Mejor tiempo de " + REPETICIONES + " repeticiones, con " + WARMUP + " de warmup");
        System.out.println();
        calentar();

        for (int n : TAMANIOS) {
            String[] claves = clavesCargadas(n);
            String[] nuevas = clavesNuevas(n);

            Diccionario estatico = new DiccionarioEstatico(n + CLAVES_NUEVAS);
            long cargaEstatica = cargar(estatico, claves);
            long obtenerEstatico = medirObtener(estatico, claves[n - 1]);   // peor caso: ultima del arreglo
            long definirEstatico = medirDefinir(estatico, nuevas);

            Diccionario dinamico = new DiccionarioDinamico();
            long cargaDinamica = cargar(dinamico, claves);
            long obtenerDinamico = medirObtener(dinamico, claves[0]);       // peor caso: ultima de la cadena
            long definirDinamico = medirDefinir(dinamico, nuevas);

            System.out.println("n = " + n);
            System.out.println("  definir() estatica: " + formatear(definirEstatico) + "  (" + definirEstatico + " ns)");
            System.out.println("  definir() dinamica: " + formatear(definirDinamico) + "  (" + definirDinamico + " ns)");
            System.out.println("  obtener() estatica: " + formatear(obtenerEstatico) + "  (" + obtenerEstatico + " ns)");
            System.out.println("  obtener() dinamica: " + formatear(obtenerDinamico) + "  (" + obtenerDinamico + " ns)");
            System.out.println("  (carga de las " + n + " claves: estatica " + formatear(cargaEstatica)
                    + ", dinamica " + formatear(cargaDinamica) + ")");
            System.out.println();
        }
    }

    // Calentamiento general, antes de la primera medicion: hace trabajar a las dos
    // implementaciones un rato para que la JVM termine de compilar el codigo de busqueda.
    // Sin esto, la primera fila de la tabla sale inflada y no es comparable con el resto:
    // estaria midiendo el arranque de la JVM y no la estructura.
    private static void calentar() {
        String[] claves = clavesCargadas(2_000);
        Diccionario estatico = new DiccionarioEstatico(2_000);
        Diccionario dinamico = new DiccionarioDinamico();
        cargar(estatico, claves);
        cargar(dinamico, claves);
        for (int i = 0; i < 200; i++) {
            estatico.obtener(claves[claves.length - 1]);
            dinamico.obtener(claves[0]);
        }
    }

    // claves ya cargadas: clave-0 .. clave-(n-1)
    private static String[] clavesCargadas(int n) {
        String[] claves = new String[n];
        for (int i = 0; i < n; i++) {
            claves[i] = "clave-" + i;
        }
        return claves;
    }

    // claves que no estan en el diccionario, una por cada llamada medida de definir()
    private static String[] clavesNuevas(int n) {
        String[] nuevas = new String[CLAVES_NUEVAS];
        for (int i = 0; i < CLAVES_NUEVAS; i++) {
            nuevas[i] = "clave-nueva-" + n + "-" + i;
        }
        return nuevas;
    }

    // carga las claves y devuelve cuanto tardo (no es parte de la medicion pedida:
    // sirve para ver el costo de armar cada estructura)
    private static long cargar(Diccionario d, String[] claves) {
        long inicio = System.nanoTime();
        for (int i = 0; i < claves.length; i++) {
            d.definir(claves[i], Integer.valueOf(i));
        }
        return System.nanoTime() - inicio;
    }

    private static long medirObtener(Diccionario d, String clave) {
        return medirNanos(() -> d.obtener(clave), REPETICIONES);
    }

    // el arreglo de un solo lugar hace de contador: la lambda necesita que la variable
    // de afuera no cambie, pero si puede cambiar el contenido del arreglo
    private static long medirDefinir(Diccionario d, String[] nuevas) {
        int[] proxima = {0};
        return medirNanos(() -> {
            d.definir(nuevas[proxima[0]], Integer.valueOf(proxima[0]));
            proxima[0]++;
        }, REPETICIONES);
    }
}
