public class UtilizacionArbol {
    public static int altura(ArbolBinarioBusqueda a) {
        return altura(a.getRaiz());
    }

    private static int altura(NodoArbol nodo) {
        if (nodo == null) {
            return 0;
        }
        int izquierda = altura(nodo.izquierdo);
        int derecha = altura(nodo.derecho);
        return 1 + Math.max(izquierda, derecha);
    }

    public static int contarHojas(ArbolBinarioBusqueda a) {
        return contarHojas(a.getRaiz());
    }

    private static int contarHojas(NodoArbol nodo) {
        if (nodo == null) {
            return 0;
        }
        if (nodo.izquierdo == null && nodo.derecho == null) {
            return 1;
        }
        return contarHojas(nodo.izquierdo) + contarHojas(nodo.derecho);
    }

    public static int sumaNodos(ArbolBinarioBusqueda a) {
        return sumaNodos(a.getRaiz());
    }

    private static int sumaNodos(NodoArbol nodo) {
        if (nodo == null) {
            return 0;
        }
        return nodo.dato + sumaNodos(nodo.izquierdo) + sumaNodos(nodo.derecho);
    }

    public static boolean esABB(NodoArbol a) {
        return esABB(a, null, null);
    }

    private static boolean esABB(NodoArbol nodo, NodoArbol minimo, NodoArbol maximo) {
        if (nodo == null) {
            return true;
        }
        // Los limites vienen de todos los ancestros, no solo del padre.
        if (minimo != null && nodo.dato <= minimo.dato) {
            return false;
        }
        if (maximo != null && nodo.dato >= maximo.dato) {
            return false;
        }
        return esABB(nodo.izquierdo, minimo, nodo)
                && esABB(nodo.derecho, nodo, maximo);
    }
}
