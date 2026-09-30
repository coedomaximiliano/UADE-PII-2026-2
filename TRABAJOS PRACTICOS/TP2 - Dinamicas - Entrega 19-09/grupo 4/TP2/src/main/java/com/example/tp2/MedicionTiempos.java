package com.example.tp2;

public class MedicionTiempos {

    public static void main(String[] args) {
        int[] tamanios = {1000, 10000, 100000};

        System.out.println("n\tdefinir() - Estática\tdefinir() - Dinámica\tobtener() - Estática\tobtener() - Dinámica");

        for (int n : tamanios) {
            long tiempoDefinirEstatica = medirDefinir(new DiccionarioEstatico(), n);
            long tiempoDefinirDinamica = medirDefinir(new DiccionarioDinamico(), n);
            long tiempoObtenerEstatica = medirObtener(new DiccionarioEstatico(), n);
            long tiempoObtenerDinamica = medirObtener(new DiccionarioDinamico(), n);

            System.out.println(n + "\t" + tiempoDefinirEstatica + " ns\t\t" + tiempoDefinirDinamica + " ns\t\t" + tiempoObtenerEstatica + " ns\t\t" + tiempoObtenerDinamica + " ns");
        }
    }

    private static long medirDefinir(Diccionario d, int n) {
        int repeticiones = 100;
        int warmup = 10;
        long mejorTiempo = Long.MAX_VALUE;

        d.crear();
        // Cargar n elementos UNA SOLA VEZ
        for (int j = 0; j < n; j++) {
            d.definir("clave_" + j, j);
        }

        for (int i = 0; i < repeticiones + warmup; i++) {
            // Medir la inserción de una clave nueva distinta en cada llamada
            String claveNueva = "clave_nueva_" + i + "_" + System.nanoTime();
            long inicio = System.nanoTime();
            d.definir(claveNueva, 1);
            long fin = System.nanoTime();

            if (i >= warmup) {
                long tiempo = fin - inicio;
                if (tiempo < mejorTiempo) {
                    mejorTiempo = tiempo;
                }
            }
        }
        return mejorTiempo;
    }

    private static long medirObtener(Diccionario d, int n) {
        int repeticiones = 100;
        int warmup = 10;
        long mejorTiempo = Long.MAX_VALUE;

        d.crear();
        // Cargar n elementos UNA SOLA VEZ
        for (int j = 0; j < n; j++) {
            d.definir("clave_" + j, j);
        }

        for (int i = 0; i < repeticiones + warmup; i++) {
            // Medir la obtención de una clave inexistente (peor caso)
            String claveBuscar = "clave_inexistente_" + i;
            long inicio = System.nanoTime();
            d.obtener(claveBuscar);
            long fin = System.nanoTime();

            if (i >= warmup) {
                long tiempo = fin - inicio;
                if (tiempo < mejorTiempo) {
                    mejorTiempo = tiempo;
                }
            }
        }
        return mejorTiempo;
    }
}
