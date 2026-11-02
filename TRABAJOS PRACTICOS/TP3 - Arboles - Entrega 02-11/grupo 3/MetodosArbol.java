// TP3 - Algoritmos y Estructuras de Datos II
// Pedro Stella - Bautista Chavarri

/**
 * Parte 4: metodos de utilizacion sobre el ABB, todos recursivos.
 * Los tres primeros reciben el arbol y arrancan desde su raiz; esABB trabaja
 * directamente sobre un NodoArbol, porque tiene que poder revisar arboles armados a
 * mano que quiza no cumplan la propiedad de ABB.
 * En los costos, n es la cantidad de nodos del arbol.
 */
public class MetodosArbol {

    // altura(a: Arbol): entero
    // cantidad de nodos del camino mas largo entre la raiz y una hoja:
    // un arbol vacio tiene altura 0 y uno de un solo nodo, altura 1
    // costo: O(n), visita cada nodo una vez
    public static int altura(ArbolBinarioBusqueda a) {
        return alturaRec(a.raiz());
    }

    private static int alturaRec(NodoArbol actual) {
        if (actual == null) {
            return 0;
        }
        int izquierda = alturaRec(actual.izquierdo);
        int derecha = alturaRec(actual.derecho);
        if (izquierda > derecha) {
            return izquierda + 1;      // el mas alto de los dos lados, mas este nodo
        }
        return derecha + 1;
    }

    // contarHojas(a: Arbol): entero
    // cantidad de nodos sin hijos
    // costo: O(n)
    public static int contarHojas(ArbolBinarioBusqueda a) {
        return contarHojasRec(a.raiz());
    }

    private static int contarHojasRec(NodoArbol actual) {
        if (actual == null) {
            return 0;
        }
        if (actual.izquierdo == null && actual.derecho == null) {
            return 1;
        }
        return contarHojasRec(actual.izquierdo) + contarHojasRec(actual.derecho);
    }

    // sumaNodos(a: Arbol): entero
    // suma de los valores de todos los nodos
    // costo: O(n)
    public static int sumaNodos(ArbolBinarioBusqueda a) {
        return sumaNodosRec(a.raiz());
    }

    private static int sumaNodosRec(NodoArbol actual) {
        if (actual == null) {
            return 0;
        }
        return actual.dato + sumaNodosRec(actual.izquierdo) + sumaNodosRec(actual.derecho);
    }

    // esABB(a: NodoArbol): boolean
    // devuelve true si el arbol cumple la propiedad de ABB en todos sus nodos
    // costo: O(n)
    //
    // No alcanza con comparar cada nodo con sus dos hijos directos. Por ejemplo:
    //
    //         50
    //        /  \
    //      30    70
    //        \
    //         60
    //
    // 60 es hijo derecho de 30 y es mayor que 30, asi que mirando solo al padre parece
    // correcto; pero 60 esta en el subarbol izquierdo de 50 y es mayor que 50, o sea que
    // viola la propiedad respecto de un ancestro mas lejano.
    // Por eso cada llamada baja con el rango de valores permitidos: al ir a la izquierda
    // el maximo pasa a ser el dato del nodo, y al ir a la derecha, el minimo.
    public static boolean esABB(NodoArbol a) {
        return esABBEntre(a, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // los limites van en long para poder arrancar con Long.MIN_VALUE y Long.MAX_VALUE
    // como "sin limite": cualquier int cae siempre adentro de ese rango
    private static boolean esABBEntre(NodoArbol actual, long minimo, long maximo) {
        if (actual == null) {
            return true;
        }
        if (actual.dato <= minimo || actual.dato >= maximo) {
            return false;
        }
        return esABBEntre(actual.izquierdo, minimo, actual.dato)
                && esABBEntre(actual.derecho, actual.dato, maximo);
    }
}
