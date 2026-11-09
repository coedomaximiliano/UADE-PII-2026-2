package com.example.tp3;

/** Métodos recursivos de la Parte 4. */
public final class UtilizacionArbol {
    private UtilizacionArbol() { }

    public static int altura(ArbolBinarioBusqueda arbol) { return altura(arbol.getRaiz()); }

    private static int altura(NodoArbol nodo) {
        if (nodo == null) return 0;
        return 1 + Math.max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho()));
    }

    public static int contarHojas(ArbolBinarioBusqueda arbol) {
        return contarHojas(arbol.getRaiz());
    }

    private static int contarHojas(NodoArbol nodo) {
        if (nodo == null) return 0;
        if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) return 1;
        return contarHojas(nodo.getIzquierdo()) + contarHojas(nodo.getDerecho());
    }

    // Acumulación en long para evitar el desbordamiento de sumas de enteros int.
    public static long sumaNodos(ArbolBinarioBusqueda arbol) {
        return sumaNodos(arbol.getRaiz());
    }

    private static long sumaNodos(NodoArbol nodo) {
        if (nodo == null) return 0L;
        return (long) nodo.getDato() + sumaNodos(nodo.getIzquierdo())
                + sumaNodos(nodo.getDerecho());
    }

    public static boolean esABB(NodoArbol raiz) {
        return esABB(raiz, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean esABB(NodoArbol nodo, long minimo, long maximo) {
        if (nodo == null) return true;
        int dato = nodo.getDato();
        if (dato <= minimo || dato >= maximo) return false;
        return esABB(nodo.getIzquierdo(), minimo, dato)
                && esABB(nodo.getDerecho(), dato, maximo);
    }
}
