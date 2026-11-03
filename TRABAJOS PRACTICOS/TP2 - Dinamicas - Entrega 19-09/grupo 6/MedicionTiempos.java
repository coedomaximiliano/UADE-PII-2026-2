public class MedicionTiempos {
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

    static void cargar(Diccionario d, int n) {
        for (int i = 0; i < n; i++) {
            d.definir(i, i * 10);
        }
    }

    static String medirTamanio(int n, int repeticiones) {
        Diccionario estatico = new DiccionarioEstatico(n + 3 + repeticiones);
        Diccionario dinamico = new DiccionarioDinamico();
        cargar(estatico, n);
        cargar(dinamico, n);

        long obtenerEstatica = medirNanos(() -> estatico.obtener(n - 1), repeticiones);
        long obtenerDinamica = medirNanos(() -> dinamico.obtener(0), repeticiones);
        long definirEstatica = medirNanos(new DefinirClaveNueva(estatico, n), repeticiones);
        long definirDinamica = medirNanos(new DefinirClaveNueva(dinamico, n), repeticiones);

        return String.format("%-10s %-24s %-24s %-24s %-24s", "" + n,
                formatear(definirEstatica), formatear(definirDinamica),
                formatear(obtenerEstatica), formatear(obtenerDinamica));
    }

    public static void main(String[] args) {
        int repeticiones = 20;
        int[] tamanios = { 1_000, 10_000, 100_000 };

        System.out.println("Rondas de calentamiento (se descartan)...");
        medirTamanio(1_000, repeticiones);
        medirTamanio(10_000, repeticiones);

        System.out.println("Mejor tiempo de " + repeticiones + " repeticiones (tras warmup)");
        System.out.printf("%-10s %-24s %-24s %-24s %-24s%n",
                "n", "definir() Estatica", "definir() Dinamica",
                "obtener() Estatica", "obtener() Dinamica");
        for (int t = 0; t < tamanios.length; t++) {
            System.out.println(medirTamanio(tamanios[t], repeticiones));
        }
    }
}

interface Operacion {
    void ejecutar();
}

class DefinirClaveNueva implements Operacion {
    private Diccionario d;
    private int proximaClave;

    public DefinirClaveNueva(Diccionario d, int primeraClave) {
        this.d = d;
        this.proximaClave = primeraClave;
    }

    @Override
    public void ejecutar() {
        d.definir(proximaClave, proximaClave * 10);
        proximaClave++;
    }
}
