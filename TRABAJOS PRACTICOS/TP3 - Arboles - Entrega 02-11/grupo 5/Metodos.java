public class Metodos {

    public static int altura(ArbolBinarioBusqueda a) {
        return alturaAux(a.getRaiz());
    }
    private static int alturaAux(NodoArbol nodo) {
        if (nodo == null) {
            return 0;
        }
        int alturaIzq = alturaAux(nodo.izquierdo);
        int alturaDer = alturaAux(nodo.derecho);
        return 1 + Math.max(alturaIzq, alturaDer);
    }

    public static int contarHojas(ArbolBinarioBusqueda a) {
        return contarHojasAux(a.getRaiz());
    }
    private static int contarHojasAux(NodoArbol nodo) {
        if (nodo == null) {
            return 0;
        }
        if (nodo.izquierdo == null && nodo.derecho == null) {
            return 1;
        }
        return contarHojasAux(nodo.izquierdo) + contarHojasAux(nodo.derecho);
    }

    public static int sumaNodos(ArbolBinarioBusqueda a) {
        return sumaNodosAux(a.getRaiz());
    }
    private static int sumaNodosAux(NodoArbol nodo) {
        if (nodo == null) {
            return 0;
        }
        return nodo.dato + sumaNodosAux(nodo.izquierdo) + sumaNodosAux(nodo.derecho);
    }

    public static boolean esABB(NodoArbol raizArbol) {
        return esABBAux(raizArbol, null, null);
    }
    private static boolean esABBAux(NodoArbol nodo, Integer minimo, Integer maximo) {
        if (nodo == null) {
            return true;
        }
        if (minimo != null && nodo.dato <= minimo) {
            return false;
        }
        if (maximo != null && nodo.dato >= maximo) {
            return false;
        }
        return esABBAux(nodo.izquierdo, minimo, nodo.dato)
                && esABBAux(nodo.derecho, nodo.dato, maximo);
    }
}
