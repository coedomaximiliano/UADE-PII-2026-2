// TP2 - Algoritmos y Estructuras de Datos II
// Pedro Stella - Bautista Chavarri

/**
 * Parte 4: metodos que usan solo las operaciones de la interfaz Diccionario -- les
 * alcanza con claves, obtener y definir --, por eso funcionan igual con
 * DiccionarioDinamico o con DiccionarioEstatico y no tocan ni los nodos ni los
 * arreglos internos.
 * Los diccionarios que se devuelven son dinamicos: no hay que estimar de antemano
 * cuantas claves van a entrar.
 * En los costos, n es la cantidad de claves de los diccionarios involucrados.
 */
public class MetodosDiccionario {

    // combinarDiccionarios(d1: Diccionario, d2: Diccionario): Diccionario
    // devuelve un diccionario nuevo con las claves de los dos; si una clave esta en
    // ambos, gana el valor de d2. d1 y d2 quedan igual que antes.
    // costo: O(n^2). Son 2n vueltas y en cada una se recorre un diccionario o la lista
    // de claves, que son O(n).
    public static Diccionario combinarDiccionarios(Diccionario d1, Diccionario d2) {
        Diccionario resultado = new DiccionarioDinamico();
        copiarPares(d1, resultado);
        copiarPares(d2, resultado);   // lo que ya estaba de d1 queda pisado por d2
        return resultado;
    }

    // copia los pares de origen en destino, sin modificar origen
    private static void copiarPares(Diccionario origen, Diccionario destino) {
        ListaDinamica claves = origen.claves();
        for (int i = 0; i < claves.cantidad(); i++) {
            Object clave = claves.obtener(i);
            destino.definir(clave, origen.obtener(clave));
        }
    }

    // invertir(d: Diccionario): Diccionario
    // devuelve un diccionario nuevo donde cada valor de d pasa a ser clave y cada clave
    // pasa a ser valor. Se supone que los valores de d tambien son unicos.
    // d queda igual que antes.
    // costo: O(n^2), por la misma razon que combinarDiccionarios.
    public static Diccionario invertir(Diccionario d) {
        Diccionario resultado = new DiccionarioDinamico();
        ListaDinamica claves = d.claves();
        for (int i = 0; i < claves.cantidad(); i++) {
            Object clave = claves.obtener(i);
            resultado.definir(d.obtener(clave), clave);
        }
        return resultado;
    }

    // contarValoresMayoresA(d: Diccionario, umbral: entero): entero
    // cuenta cuantos pares de d tienen valor estrictamente mayor a umbral. Se supone que
    // los valores son enteros. d queda igual que antes.
    // costo: O(n^2): n vueltas, y cada obtener() recorre el diccionario, O(n).
    public static int contarValoresMayoresA(Diccionario d, int umbral) {
        int cuenta = 0;
        ListaDinamica claves = d.claves();
        for (int i = 0; i < claves.cantidad(); i++) {
            int valor = ((Integer) d.obtener(claves.obtener(i))).intValue();
            if (valor > umbral) {
                cuenta++;
            }
        }
        return cuenta;
    }

    // clavesOrdenadas(d: Diccionario): Lista
    // devuelve una lista dinamica con las claves de d ordenadas alfabeticamente. Se
    // supone que las claves son de tipo texto. d queda igual que antes.
    // costo: O(n^2). El ordenamiento por insercion compara O(n^2) veces en el peor caso,
    // y leer la lista de claves por posicion tambien cuesta O(n^2) porque obtener(i)
    // recorre la cadena desde la cabeza.
    public static ListaDinamica clavesOrdenadas(Diccionario d) {
        ListaDinamica claves = d.claves();
        int n = claves.cantidad();
        String[] ordenadas = new String[n];
        for (int i = 0; i < n; i++) {
            String clave = (String) claves.obtener(i);
            int j = i - 1;
            while (j >= 0 && ordenadas[j].compareTo(clave) > 0) {
                ordenadas[j + 1] = ordenadas[j];   // corre un lugar a la derecha
                j--;
            }
            ordenadas[j + 1] = clave;
        }
        ListaDinamica resultado = new ListaDinamica();
        for (int i = 0; i < n; i++) {
            resultado.agregar(ordenadas[i]);
        }
        return resultado;
    }
}
