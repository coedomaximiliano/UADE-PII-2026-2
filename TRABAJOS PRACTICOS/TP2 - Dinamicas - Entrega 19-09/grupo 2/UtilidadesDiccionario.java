/**
 * Parte 4 -- Utilizacion del TDA Diccionario.
 *
 * Todos los metodos trabajan UNICAMENTE contra la interfaz Diccionario
 * (definir, obtener, existeClave, claves, ...), sin acceder a los nodos
 * ni al arreglo interno. Por eso funcionan igual con DiccionarioDinamico
 * y con DiccionarioEstatico, sin cambiar una linea.
 *
 * Los diccionarios auxiliares que se crean adentro usan la
 * implementacion dinamica (no necesita capacidad prefijada).
 *
 * Complejidad (n = cantidad de claves involucradas):
 *   combinarDiccionarios  O(n^2)  -- n claves, y definir/obtener son O(n)
 *   invertir              O(n^2)  -- idem
 *   contarValoresMayoresA O(n^2)  -- n claves, obtener es O(n)
 *   clavesOrdenadas       O(n^2)  -- el ordenamiento simple es O(n^2)
 */
public class UtilidadesDiccionario {

    // combinarDiccionarios(d1, d2): nuevo diccionario con las claves de
    // ambos; si una clave esta en los dos, gana el valor de d2. d1 y d2
    // no se modifican (solo se leen).
    public static Diccionario combinarDiccionarios(Diccionario d1, Diccionario d2) {
        Diccionario resultado = new DiccionarioDinamico();

        Lista claves1 = d1.claves();
        for (int i = 0; i < claves1.tamanio(); i++) {
            Object clave = claves1.obtener(i);
            resultado.definir(clave, d1.obtener(clave));
        }

        // las de d2 se definen despues: si la clave ya estaba (venia de
        // d1), definir la sobrescribe -> gana el valor de d2.
        Lista claves2 = d2.claves();
        for (int i = 0; i < claves2.tamanio(); i++) {
            Object clave = claves2.obtener(i);
            resultado.definir(clave, d2.obtener(clave));
        }

        return resultado;
    }

    // invertir(d): nuevo diccionario donde las claves pasan a ser valores
    // y los valores pasan a ser claves. Se asume que los valores de d son
    // unicos. d no se modifica.
    public static Diccionario invertir(Diccionario d) {
        Diccionario resultado = new DiccionarioDinamico();

        Lista claves = d.claves();
        for (int i = 0; i < claves.tamanio(); i++) {
            Object clave = claves.obtener(i);
            Object valor = d.obtener(clave);
            resultado.definir(valor, clave);    // valor <-> clave
        }

        return resultado;
    }

    // contarValoresMayoresA(d, umbral): cuenta cuantos pares de d tienen
    // valor numerico estrictamente mayor a umbral. d no se modifica.
    public static int contarValoresMayoresA(Diccionario d, int umbral) {
        int contador = 0;

        Lista claves = d.claves();
        for (int i = 0; i < claves.tamanio(); i++) {
            Object clave = claves.obtener(i);
            int valor = ((Number) d.obtener(clave)).intValue();
            if (valor > umbral) {
                contador++;
            }
        }

        return contador;
    }

    // clavesOrdenadas(d): Lista dinamica con las claves de d ordenadas
    // alfabeticamente. Se asume que las claves son texto. d no se modifica.
    public static Lista clavesOrdenadas(Diccionario d) {
        Lista claves = d.claves();
        int n = claves.tamanio();

        // paso las claves a un arreglo local para ordenarlas comodo
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (String) claves.obtener(i);
        }

        // ordenamiento por insercion (simple), alfabetico
        for (int i = 1; i < n; i++) {
            String actual = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].compareTo(actual) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = actual;
        }

        // devuelvo una Lista dinamica con las claves ya ordenadas
        Lista ordenada = new Lista();
        for (int i = 0; i < n; i++) {
            ordenada.agregar(arr[i]);
        }
        return ordenada;
    }
}
