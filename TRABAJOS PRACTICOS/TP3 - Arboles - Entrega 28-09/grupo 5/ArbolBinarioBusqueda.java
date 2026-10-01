public class ArbolBinarioBusqueda {
    private NodoArbol raiz;

    public ArbolBinarioBusqueda() {
        this.raiz = null;
    }

    public void insertar(int x) {
        raiz = insertarAux(raiz, x);
    }
    private NodoArbol insertarAux(NodoArbol nodo, int x) {
        if (nodo == null) {
            return new NodoArbol(x);
        }
        if (x < nodo.dato) {
            nodo.izquierdo = insertarAux(nodo.izquierdo, x);
        } else if (x > nodo.dato) {
            nodo.derecho = insertarAux(nodo.derecho, x);
        }
        // si x == nodo.dato: no se admiten repetidos, el arbol no cambia
        return nodo;
    }

    public boolean pertenece(int x) {
        return perteneceAux(raiz, x);
    }
    private boolean perteneceAux(NodoArbol nodo, int x) {
        if (nodo == null) {
            return false;
        }
        if (x == nodo.dato) {
            return true;
        }
        if (x < nodo.dato) {
            return perteneceAux(nodo.izquierdo, x);
        }
        return perteneceAux(nodo.derecho, x);
    }

    public boolean esVacio() {
        return raiz == null;
    }

    public int cantidadNodos() {
        return cantidadNodosAux(raiz);
    }
    private int cantidadNodosAux(NodoArbol nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + cantidadNodosAux(nodo.izquierdo) + cantidadNodosAux(nodo.derecho);
    }


    // 3.1
    public void inorder() {
        inorderAux(raiz);
        System.out.println();
    }
    private void inorderAux(NodoArbol nodo) {
        if (nodo != null) {
            inorderAux(nodo.izquierdo);
            System.out.print(nodo.dato + " ");
            inorderAux(nodo.derecho);
        }
    }

    public void preorder() {
        preorderAux(raiz);
        System.out.println();
    }
    private void preorderAux(NodoArbol nodo) {
        if (nodo != null) {
            System.out.print(nodo.dato + " ");
            preorderAux(nodo.izquierdo);
            preorderAux(nodo.derecho);
        }
    }

    public void postorder() {
        postorderAux(raiz);
        System.out.println();
    }
    private void postorderAux(NodoArbol nodo) {
        if (nodo != null) {
            postorderAux(nodo.izquierdo);
            postorderAux(nodo.derecho);
            System.out.print(nodo.dato + " ");
        }
    }


    // 3.2
    public void recorridoPorNiveles() {
        ColaAuxiliar cola = new ColaAuxiliar();
        if (raiz != null) {
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


    // Getter para las funciones de utilizacion (altura, contarHojas, sumaNodos y esABB).
    public NodoArbol getRaiz() {
        return raiz;
    }
}
