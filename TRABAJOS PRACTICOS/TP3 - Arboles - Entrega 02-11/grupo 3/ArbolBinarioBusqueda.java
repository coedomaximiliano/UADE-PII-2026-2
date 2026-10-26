// TP3 - Algoritmos y Estructuras de Datos II
// Pedro Stella - Bautista Chavarri

/**
 * TDA Arbol Binario de Busqueda (ABB) de enteros, implementado de forma dinamica con
 * nodos enlazados. La clase guarda una unica referencia a la raiz, null si el arbol
 * esta vacio: desde ahi se llega a todo el arbol bajando por los hijos.
 * Invariante: para todo nodo, los elementos del subarbol izquierdo son menores que el
 * del nodo y los del derecho son mayores. No se admiten repetidos.
 * Esa propiedad es la que permite que insertar y pertenece descarten un subarbol entero
 * en cada paso, en vez de recorrer el arbol completo.
 */
public class ArbolBinarioBusqueda {

    private NodoArbol raiz;

    // crear() -> Arbol
    // post: devuelve un arbol vacio
    public ArbolBinarioBusqueda() {
        this.raiz = null;
    }

    // insertar(a: Arbol, x: elemento) -> Arbol
    // post: x pertenece a a y se mantiene la propiedad de ABB; si x ya estaba, a no cambia
    public void insertar(int x) {
        raiz = insertarRec(raiz, x);
    }

    // baja por el lado que corresponde y cuelga el nodo nuevo del hueco que encuentra.
    // Devuelve el subarbol ya actualizado, que el llamador vuelve a enganchar.
    private NodoArbol insertarRec(NodoArbol actual, int x) {
        if (actual == null) {
            return new NodoArbol(x);       // este hueco es el lugar que le toca a x
        }
        if (x < actual.dato) {
            actual.izquierdo = insertarRec(actual.izquierdo, x);
        } else if (x > actual.dato) {
            actual.derecho = insertarRec(actual.derecho, x);
        }
        // si x == actual.dato no se hace nada: no se admiten repetidos
        return actual;
    }

    // pertenece(a: Arbol, x: elemento) -> boolean
    // post: devuelve true si x esta en a; a no se modifica
    public boolean pertenece(int x) {
        return perteneceRec(raiz, x);
    }

    // en cada llamada compara una sola vez y sigue por un solo lado: el otro subarbol
    // queda descartado entero por la propiedad de ABB
    private boolean perteneceRec(NodoArbol actual, int x) {
        if (actual == null) {
            return false;
        }
        if (x == actual.dato) {
            return true;
        }
        if (x < actual.dato) {
            return perteneceRec(actual.izquierdo, x);
        }
        return perteneceRec(actual.derecho, x);
    }

    // esVacio(a: Arbol) -> boolean
    // post: devuelve true si a no tiene elementos
    public boolean esVacio() {
        return raiz == null;
    }

    // cantidadNodos(a: Arbol) -> entero
    // post: devuelve cuantos elementos tiene a
    // cuenta recorriendo el arbol, asi el resultado sale siempre de la estructura real
    public int cantidadNodos() {
        return cantidadNodosRec(raiz);
    }

    private int cantidadNodosRec(NodoArbol actual) {
        if (actual == null) {
            return 0;
        }
        return 1 + cantidadNodosRec(actual.izquierdo) + cantidadNodosRec(actual.derecho);
    }

    // devuelve la raiz para que los metodos de la Parte 4 puedan recorrer el arbol.
    // No es una operacion del TDA: es el acceso minimo que necesita MetodosArbol.
    public NodoArbol raiz() {
        return raiz;
    }

    // ---------- Parte 3: recorridos ----------
    // Cada recorrido imprime los datos separados por un espacio. El texto lo arma la
    // version ...Texto(), que devuelve lo mismo que se imprime: asi el Main puede
    // comparar cada recorrido con lo esperado en vez de revisar la salida a ojo.

    // inorder: subarbol izquierdo, nodo, subarbol derecho.
    // En un ABB sale siempre en orden creciente.
    public void inorder() {
        System.out.println(inorderTexto());
    }

    public String inorderTexto() {
        StringBuilder sb = new StringBuilder();
        inorderRec(raiz, sb);
        return sb.toString();
    }

    private void inorderRec(NodoArbol actual, StringBuilder sb) {
        if (actual != null) {
            inorderRec(actual.izquierdo, sb);
            agregar(sb, actual.dato);
            inorderRec(actual.derecho, sb);
        }
    }

    // preorder: nodo, subarbol izquierdo, subarbol derecho
    public void preorder() {
        System.out.println(preorderTexto());
    }

    public String preorderTexto() {
        StringBuilder sb = new StringBuilder();
        preorderRec(raiz, sb);
        return sb.toString();
    }

    private void preorderRec(NodoArbol actual, StringBuilder sb) {
        if (actual != null) {
            agregar(sb, actual.dato);
            preorderRec(actual.izquierdo, sb);
            preorderRec(actual.derecho, sb);
        }
    }

    // postorder: subarbol izquierdo, subarbol derecho, nodo
    public void postorder() {
        System.out.println(postorderTexto());
    }

    public String postorderTexto() {
        StringBuilder sb = new StringBuilder();
        postorderRec(raiz, sb);
        return sb.toString();
    }

    private void postorderRec(NodoArbol actual, StringBuilder sb) {
        if (actual != null) {
            postorderRec(actual.izquierdo, sb);
            postorderRec(actual.derecho, sb);
            agregar(sb, actual.dato);
        }
    }

    // recorrido por niveles (BFS): de arriba hacia abajo y, en cada nivel, de izquierda
    // a derecha. No sale con recursion simple: hace falta una estructura FIFO que guarde
    // los nodos pendientes. Se encola la raiz y, mientras la cola no este vacia, se
    // desencola un nodo, se lo procesa y se encolan sus hijos.
    public void recorridoPorNiveles() {
        System.out.println(recorridoPorNivelesTexto());
    }

    public String recorridoPorNivelesTexto() {
        StringBuilder sb = new StringBuilder();
        if (esVacio()) {
            return sb.toString();
        }
        ColaNodos pendientes = new ColaNodos(cantidadNodos());   // nunca esperan mas nodos que los que hay
        pendientes.encolar(raiz);
        while (!pendientes.esVacia()) {
            NodoArbol actual = pendientes.desencolar();
            agregar(sb, actual.dato);
            if (actual.izquierdo != null) {
                pendientes.encolar(actual.izquierdo);
            }
            if (actual.derecho != null) {
                pendientes.encolar(actual.derecho);
            }
        }
        return sb.toString();
    }

    // agrega un dato al texto del recorrido, separando con un espacio del anterior
    private void agregar(StringBuilder sb, int dato) {
        if (sb.length() > 0) {
            sb.append(" ");
        }
        sb.append(dato);
    }
}
