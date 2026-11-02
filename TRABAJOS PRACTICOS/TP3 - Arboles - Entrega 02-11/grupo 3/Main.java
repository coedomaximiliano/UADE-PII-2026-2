// TP3 - Algoritmos y Estructuras de Datos II
// Pedro Stella - Bautista Chavarri

/**
 * Demo del TP3: prueba el ABB, los cuatro recorridos y los metodos de la Parte 4.
 * Primero imprime los cuatro recorridos del arbol de ejemplo que pide el enunciado, y
 * despues verifica todo: cada linea muestra lo obtenido, lo esperado y OK o ERROR.
 * Al final se cuentan las verificaciones.
 */
public class Main {

    private static int cantidadOk = 0;
    private static int cantidadError = 0;

    public static void main(String[] args) {
        ArbolBinarioBusqueda ejemplo = arbolDeEjemplo();

        System.out.println("=== Parte 3: recorridos del arbol de ejemplo ===");
        System.out.println("(insertando 50, 30, 70, 20, 40, 60, 80, 10, 25, 65, 90)");
        System.out.print("inorder():             ");
        ejemplo.inorder();
        System.out.print("preorder():            ");
        ejemplo.preorder();
        System.out.print("postorder():           ");
        ejemplo.postorder();
        System.out.print("recorridoPorNiveles(): ");
        ejemplo.recorridoPorNiveles();

        parte2Operaciones();
        parte3Verificacion(ejemplo);
        parte4Metodos(ejemplo);
        casosBorde();

        System.out.println();
        System.out.println("Verificaciones: " + cantidadOk + " OK, " + cantidadError + " con error");
    }

    // el arbol del enunciado, insertando los valores en ese orden
    private static ArbolBinarioBusqueda arbolDeEjemplo() {
        ArbolBinarioBusqueda a = new ArbolBinarioBusqueda();
        int[] valores = {50, 30, 70, 20, 40, 60, 80, 10, 25, 65, 90};
        for (int i = 0; i < valores.length; i++) {
            a.insertar(valores[i]);
        }
        return a;
    }

    private static void parte2Operaciones() {
        System.out.println();
        System.out.println("=== Parte 2: insertar, pertenece, esVacio, cantidadNodos ===");
        ArbolBinarioBusqueda a = new ArbolBinarioBusqueda();
        verificar("esVacio() de un arbol recien creado", a.esVacio(), true);
        verificar("cantidadNodos()", a.cantidadNodos(), 0);
        verificar("pertenece(50) en un arbol vacio", a.pertenece(50), false);

        a.insertar(50);
        a.insertar(30);
        a.insertar(70);
        verificar("insertar(50), insertar(30), insertar(70), esVacio()", a.esVacio(), false);
        verificar("cantidadNodos()", a.cantidadNodos(), 3);
        verificar("pertenece(30)", a.pertenece(30), true);
        verificar("pertenece(70)", a.pertenece(70), true);
        verificar("pertenece(31)", a.pertenece(31), false);

        a.insertar(50);
        verificar("insertar(50) repetido no agrega", a.cantidadNodos(), 3);
        verificar("el arbol no cambio", a.inorderTexto(), "30 50 70");

        a.insertar(20);
        a.insertar(40);
        verificar("insertar(20) e insertar(40) cuelgan de 30", a.inorderTexto(), "20 30 40 50 70");
        verificar("cantidadNodos()", a.cantidadNodos(), 5);
    }

    private static void parte3Verificacion(ArbolBinarioBusqueda ejemplo) {
        System.out.println();
        System.out.println("=== Parte 3: verificacion de los recorridos ===");
        verificar("inorder(), sale en orden creciente", ejemplo.inorderTexto(),
                "10 20 25 30 40 50 60 65 70 80 90");
        verificar("preorder()", ejemplo.preorderTexto(),
                "50 30 20 10 25 40 70 60 65 80 90");
        verificar("postorder()", ejemplo.postorderTexto(),
                "10 25 20 40 30 65 60 90 80 70 50");
        verificar("recorridoPorNiveles()", ejemplo.recorridoPorNivelesTexto(),
                "50 30 70 20 40 60 80 10 25 65 90");
        verificar("cantidadNodos() del arbol de ejemplo", ejemplo.cantidadNodos(), 11);
    }

    private static void parte4Metodos(ArbolBinarioBusqueda ejemplo) {
        System.out.println();
        System.out.println("=== Parte 4: altura, contarHojas, sumaNodos, esABB ===");
        verificar("altura() del arbol de ejemplo", MetodosArbol.altura(ejemplo), 4);
        verificar("contarHojas(), son 10, 25, 40, 65 y 90", MetodosArbol.contarHojas(ejemplo), 5);
        verificar("sumaNodos()", MetodosArbol.sumaNodos(ejemplo), 540);
        verificar("esABB() del arbol de ejemplo", MetodosArbol.esABB(ejemplo.raiz()), true);

        // arbol armado a mano que NO es un ABB: 60 cumple con su padre 30, pero esta en
        // el subarbol izquierdo de 50 y es mayor que 50
        NodoArbol raiz = new NodoArbol(50);
        raiz.izquierdo = new NodoArbol(30);
        raiz.derecho = new NodoArbol(70);
        raiz.izquierdo.derecho = new NodoArbol(60);
        verificar("esABB() con 60 colgando de 30, que viola la propiedad con 50",
                MetodosArbol.esABB(raiz), false);

        // el mismo arbol con 35 en vez de 60: ahi si es un ABB
        raiz.izquierdo.derecho = new NodoArbol(35);
        verificar("esABB() con 35 en ese mismo lugar", MetodosArbol.esABB(raiz), true);

        // violacion directa contra el padre
        NodoArbol otro = new NodoArbol(50);
        otro.izquierdo = new NodoArbol(60);
        verificar("esABB() con un hijo izquierdo mayor que su padre", MetodosArbol.esABB(otro), false);

        // repetido: la propiedad pide menores estrictos a la izquierda
        NodoArbol conRepetido = new NodoArbol(50);
        conRepetido.izquierdo = new NodoArbol(50);
        verificar("esABB() con un valor repetido", MetodosArbol.esABB(conRepetido), false);

        verificar("esABB() de un solo nodo", MetodosArbol.esABB(new NodoArbol(7)), true);
        verificar("esABB() de un arbol vacio", MetodosArbol.esABB(null), true);
    }

    private static void casosBorde() {
        System.out.println();
        System.out.println("=== Casos borde ===");
        ArbolBinarioBusqueda vacio = new ArbolBinarioBusqueda();
        verificar("altura() de un arbol vacio", MetodosArbol.altura(vacio), 0);
        verificar("contarHojas() de un arbol vacio", MetodosArbol.contarHojas(vacio), 0);
        verificar("sumaNodos() de un arbol vacio", MetodosArbol.sumaNodos(vacio), 0);
        verificar("inorder() de un arbol vacio", vacio.inorderTexto(), "");
        verificar("recorridoPorNiveles() de un arbol vacio", vacio.recorridoPorNivelesTexto(), "");

        ArbolBinarioBusqueda uno = new ArbolBinarioBusqueda();
        uno.insertar(7);
        verificar("altura() de un arbol de un solo nodo", MetodosArbol.altura(uno), 1);
        verificar("contarHojas() de un arbol de un solo nodo", MetodosArbol.contarHojas(uno), 1);

        // insertar en orden creciente degenera el arbol en una lista
        ArbolBinarioBusqueda degenerado = new ArbolBinarioBusqueda();
        int[] ordenados = {10, 20, 30, 40, 50};
        for (int i = 0; i < ordenados.length; i++) {
            degenerado.insertar(ordenados[i]);
        }
        verificar("altura() insertando 10, 20, 30, 40, 50 en orden", MetodosArbol.altura(degenerado), 5);
        verificar("cantidadNodos()", degenerado.cantidadNodos(), 5);
        verificar("contarHojas(), solo el ultimo", MetodosArbol.contarHojas(degenerado), 1);
        verificar("preorder() baja siempre por la derecha", degenerado.preorderTexto(), "10 20 30 40 50");
        verificar("recorridoPorNiveles(), un nodo por nivel", degenerado.recorridoPorNivelesTexto(),
                "10 20 30 40 50");
        verificar("pertenece(40) en el degenerado", degenerado.pertenece(40), true);

        // el mismo conjunto insertado de otra forma queda balanceado
        ArbolBinarioBusqueda balanceado = new ArbolBinarioBusqueda();
        int[] porMedio = {30, 20, 40, 10, 50};
        for (int i = 0; i < porMedio.length; i++) {
            balanceado.insertar(porMedio[i]);
        }
        verificar("altura() con los mismos valores insertados 30, 20, 40, 10, 50",
                MetodosArbol.altura(balanceado), 3);
        verificar("inorder() da lo mismo en los dos", balanceado.inorderTexto(), degenerado.inorderTexto());
    }

    // ---------- Auxiliares ----------

    // imprime "operacion: obtenido  (esperado ...)" con OK o ERROR, y lo cuenta
    private static void verificar(String operacion, String obtenido, String esperado) {
        String marca = "OK";
        if (obtenido.equals(esperado)) {
            cantidadOk++;
        } else {
            marca = "ERROR";
            cantidadError++;
        }
        System.out.println(operacion + ": " + obtenido + "  (esperado " + esperado + ")  " + marca);
    }

    private static void verificar(String operacion, int obtenido, int esperado) {
        verificar(operacion, "" + obtenido, "" + esperado);
    }

    private static void verificar(String operacion, boolean obtenido, boolean esperado) {
        verificar(operacion, "" + obtenido, "" + esperado);
    }
}
