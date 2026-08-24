import java.util.Arrays;

/**
 * ALGORITMOS Y ESTRUCTURAS DE DATOS II - Clase 2 / Big O
 *
 * Los 4 tipos de costo en Notacion Big O, cada uno con un metodo
 * de ejemplo y una pequena prueba en main() que muestra el resultado.
 *
 *   O(1)      - Constante
 *   O(n)      - Lineal
 *   O(n^2)    - Cuadratico
 *   O(log n)  - Logaritmico
 */
public class EjemplosBigO {

    // ============================================================
    // O(1) - CONSTANTE
    // El codigo toma el mismo tiempo sin importar el tamano de los datos.
    // ============================================================
    static int primero(int[] datos) {
        return datos[0];   // siempre 1 sola operacion,
                            // sin importar si el array tiene 10 o 10 millones de elementos
    }

    // ============================================================
    // O(n) - LINEAL
    // El tiempo crece de forma directa con la cantidad de datos.
    // ============================================================
    static boolean pertenece(int[] datos, int x) {
        for (int i = 0; i < datos.length; i++) {   // en el peor caso, recorre TODO el array
            if (datos[i] == x) {
                return true;
            }
        }
        return false;
    }

    // ============================================================
    // O(n^2) - CUADRATICO
    // El tiempo crece muy rapido: comun en bucles dentro de bucles.
    // ============================================================
    static void mostrarTodosLosPares(int[] datos) {
        int n = datos.length;
        for (int i = 0; i < n; i++) {          // se ejecuta n veces
            for (int j = 0; j < n; j++) {      // por cada i, se ejecuta n veces mas
                System.out.println("  (" + datos[i] + ", " + datos[j] + ")");
            }
        }
        // total de impresiones: n * n = n^2
    }

    // ============================================================
    // O(log n) - LOGARITMICO
    // El tiempo crece muy lento: muy eficiente al buscar en listas ordenadas.
    // Requiere que el array este ORDENADO.
    // ============================================================
    static boolean busquedaBinaria(int[] datosOrdenados, int x) {
        int inicio = 0;
        int fin = datosOrdenados.length - 1;
        int pasos = 0;

        while (inicio <= fin) {
            pasos++;
            int medio = (inicio + fin) / 2;

            if (datosOrdenados[medio] == x) {
                System.out.println("  Encontrado en " + pasos + " paso(s) (de "
                        + datosOrdenados.length + " elementos totales)");
                return true;
            } else if (datosOrdenados[medio] < x) {
                inicio = medio + 1;   // descarta la mitad izquierda
            } else {
                fin = medio - 1;      // descarta la mitad derecha
            }
        }

        System.out.println("  No encontrado, luego de " + pasos + " paso(s)");
        return false;
    }

    // ============================================================
    // main(): prueba cada uno de los 4 ejemplos
    // ============================================================
    public static void main(String[] args) {

        System.out.println("=== O(1) - Constante ===");
        int[] datosO1 = {10, 20, 30, 40, 50};
        System.out.println("Array: " + Arrays.toString(datosO1));
        System.out.println("primero(datos) = " + primero(datosO1));
        System.out.println("(el costo es el mismo sin importar si el array tuviera 5 o 5 millones de elementos)\n");

        System.out.println("=== O(n) - Lineal ===");
        int[] datosOn = {4, 8, 15, 16, 23, 42};
        System.out.println("Array: " + Arrays.toString(datosOn));
        System.out.println("pertenece(datos, 23) = " + pertenece(datosOn, 23));
        System.out.println("pertenece(datos, 99) = " + pertenece(datosOn, 99));
        System.out.println("(en el peor caso, recorre los " + datosOn.length + " elementos)\n");

        System.out.println("=== O(n^2) - Cuadratico ===");
        int[] datosOn2 = {1, 2, 3};
        System.out.println("Array: " + Arrays.toString(datosOn2));
        System.out.println("Mostrando todos los pares (" + datosOn2.length + " x " + datosOn2.length
                + " = " + (datosOn2.length * datosOn2.length) + " combinaciones):");
        mostrarTodosLosPares(datosOn2);
        System.out.println();

        System.out.println("=== O(log n) - Logaritmico ===");
        int[] datosOrdenados = {2, 5, 8, 12, 16, 23, 38, 45, 56, 72, 91};
        System.out.println("Array ordenado: " + Arrays.toString(datosOrdenados));
        System.out.println("Buscando el 45:");
        busquedaBinaria(datosOrdenados, 45);
        System.out.println("Buscando el 100 (no esta):");
        busquedaBinaria(datosOrdenados, 100);
        System.out.println("(con " + datosOrdenados.length + " elementos, nunca hacen falta mas de "
                + (int) Math.ceil(Math.log(datosOrdenados.length) / Math.log(2)) + " pasos)");
    }
}
