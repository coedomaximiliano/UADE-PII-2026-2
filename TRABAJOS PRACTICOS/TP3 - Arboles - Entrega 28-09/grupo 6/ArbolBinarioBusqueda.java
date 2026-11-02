public class ArbolBinarioBusqueda {
    private NodoArbol raiz;

    public ArbolBinarioBusqueda() {
        this.raiz = null;
    }

    public void insertar(int x) {
        raiz = insertar(raiz, x);
    }

    private NodoArbol insertar(NodoArbol nodo, int x) {
        if (nodo == null) {
            return new NodoArbol(x);
        }
        if (x < nodo.dato) {
            nodo.izquierdo = insertar(nodo.izquierdo, x);
        } else if (x > nodo.dato) {
            nodo.derecho = insertar(nodo.derecho, x);
        }
        // Si x ya esta, no se agrega otro nodo.
        return nodo;
    }

    public boolean pertenece(int x) {
        return pertenece(raiz, x);
    }

    private boolean pertenece(NodoArbol nodo, int x) {
        if (nodo == null) {
            return false;
        }
        if (x == nodo.dato) {
            return true;
        }
        if (x < nodo.dato) {
            return pertenece(nodo.izquierdo, x);
        }
        return pertenece(nodo.derecho, x);
    }

    public boolean esVacio() {
        return raiz == null;
    }

    public int cantidadNodos() {
        return cantidadNodos(raiz);
    }

    private int cantidadNodos(NodoArbol nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + cantidadNodos(nodo.izquierdo) + cantidadNodos(nodo.derecho);
    }

    // Permite iniciar los metodos recursivos de utilizacion.
    // Los metodos que consultan esta referencia no deben modificar sus nodos.
    public NodoArbol getRaiz() {
        return raiz;
    }

    public void inorder() {
        inorder(raiz);
        System.out.println();
    }

    private void inorder(NodoArbol nodo) {
        if (nodo != null) {
            inorder(nodo.izquierdo);
            System.out.print(nodo.dato + " ");
            inorder(nodo.derecho);
        }
    }

    public void preorder() {
        preorder(raiz);
        System.out.println();
    }

    private void preorder(NodoArbol nodo) {
        if (nodo != null) {
            System.out.print(nodo.dato + " ");
            preorder(nodo.izquierdo);
            preorder(nodo.derecho);
        }
    }

    public void postorder() {
        postorder(raiz);
        System.out.println();
    }

    private void postorder(NodoArbol nodo) {
        if (nodo != null) {
            postorder(nodo.izquierdo);
            postorder(nodo.derecho);
            System.out.print(nodo.dato + " ");
        }
    }

    public void recorridoPorNiveles() {
        Cola cola = new ColaDinamica();
        if (!esVacio()) {
            cola.encolar(raiz);
        }
        while (!cola.esVacia()) {
            NodoArbol actual = cola.desencolar();
            System.out.print(actual.dato + " ");
            if (actual.izquierdo != null) {
                cola.encolar(actual.izquierdo);
            }
            if (actual.derecho != null) {
                cola.encolar(actual.derecho);
            }
        }
        System.out.println();
    }
}
