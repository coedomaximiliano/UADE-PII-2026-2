package com.example.tp3;

/** ABB de enteros sin repetidos; su único atributo es la raíz. */
public class ArbolBinarioBusqueda {
    private NodoArbol raiz;

    public static ArbolBinarioBusqueda crear() { return new ArbolBinarioBusqueda(); }
    public NodoArbol getRaiz() { return raiz; }
    public boolean esVacio() { return raiz == null; }

    public void insertar(int dato) { raiz = insertar(raiz, dato); }

    private NodoArbol insertar(NodoArbol nodo, int dato) {
        if (nodo == null) return new NodoArbol(dato);
        if (dato < nodo.getDato()) {
            nodo.setIzquierdo(insertar(nodo.getIzquierdo(), dato));
        } else if (dato > nodo.getDato()) {
            nodo.setDerecho(insertar(nodo.getDerecho(), dato));
        }
        return nodo;
    }

    public boolean pertenece(int dato) { return pertenece(raiz, dato); }

    private boolean pertenece(NodoArbol nodo, int dato) {
        if (nodo == null) return false;
        if (dato == nodo.getDato()) return true;
        return dato < nodo.getDato()
                ? pertenece(nodo.getIzquierdo(), dato)
                : pertenece(nodo.getDerecho(), dato);
    }

    public int cantidadNodos() { return cantidadNodos(raiz); }

    private int cantidadNodos(NodoArbol nodo) {
        if (nodo == null) return 0;
        return 1 + cantidadNodos(nodo.getIzquierdo()) + cantidadNodos(nodo.getDerecho());
    }

    public void inorder() {
        inorder(raiz);
        System.out.println();
    }

    private void inorder(NodoArbol nodo) {
        if (nodo == null) return;
        inorder(nodo.getIzquierdo());
        System.out.print(nodo.getDato() + " ");
        inorder(nodo.getDerecho());
    }

    public void preorder() {
        preorder(raiz);
        System.out.println();
    }

    private void preorder(NodoArbol nodo) {
        if (nodo == null) return;
        System.out.print(nodo.getDato() + " ");
        preorder(nodo.getIzquierdo());
        preorder(nodo.getDerecho());
    }

    public void postorder() {
        postorder(raiz);
        System.out.println();
    }

    private void postorder(NodoArbol nodo) {
        if (nodo == null) return;
        postorder(nodo.getIzquierdo());
        postorder(nodo.getDerecho());
        System.out.print(nodo.getDato() + " ");
    }

    public void recorridoPorNiveles() {
        Cola cola = new Cola();
        if (!esVacio()) cola.encolar(raiz);
        while (!cola.esVacia()) {
            NodoArbol nodo = cola.desencolar();
            System.out.print(nodo.getDato() + " ");
            if (nodo.getIzquierdo() != null) cola.encolar(nodo.getIzquierdo());
            if (nodo.getDerecho() != null) cola.encolar(nodo.getDerecho());
        }
        System.out.println();
    }
}

