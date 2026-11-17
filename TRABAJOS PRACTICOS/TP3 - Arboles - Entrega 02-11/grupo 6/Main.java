public class Main {
    public static void main(String[] args) {
        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();
        int[] valores = { 50, 30, 70, 20, 40, 60, 80, 10, 25, 65, 90 };
        for (int i = 0; i < valores.length; i++) {
            arbol.insertar(valores[i]);
        }

        System.out.println("=== Arbol de la consigna ===");
        System.out.print("inorder: ");
        arbol.inorder();
        System.out.print("preorder: ");
        arbol.preorder();
        System.out.print("postorder: ");
        arbol.postorder();
        System.out.print("recorridoPorNiveles: ");
        arbol.recorridoPorNiveles();
        mostrarConsultas(arbol);
        System.out.println("pertenece(65) = " + arbol.pertenece(65));
        System.out.println("pertenece(55) = " + arbol.pertenece(55));

        System.out.println("\n=== Repetidos y conservacion del arbol ===");
        arbol.insertar(50);
        arbol.insertar(25);
        System.out.println("Cantidad despues de insertar 50 y 25 otra vez = " + arbol.cantidadNodos());
        System.out.print("inorder despues de las consultas y los repetidos: ");
        arbol.inorder();
        System.out.print("recorridoPorNiveles por segunda vez: ");
        arbol.recorridoPorNiveles();

        System.out.println("\n=== Arbol vacio ===");
        ArbolBinarioBusqueda vacio = new ArbolBinarioBusqueda();
        mostrarConsultas(vacio);
        System.out.println("pertenece(50) = " + vacio.pertenece(50));
        System.out.print("inorder: ");
        vacio.inorder();
        System.out.print("preorder: ");
        vacio.preorder();
        System.out.print("postorder: ");
        vacio.postorder();
        System.out.print("recorridoPorNiveles: ");
        vacio.recorridoPorNiveles();

        System.out.println("\n=== Un solo nodo ===");
        ArbolBinarioBusqueda unico = new ArbolBinarioBusqueda();
        unico.insertar(7);
        mostrarConsultas(unico);

        System.out.println("\n=== Arbol degenerado: 1, 2, 3, 4, 5 ===");
        ArbolBinarioBusqueda degenerado = new ArbolBinarioBusqueda();
        for (int i = 1; i <= 5; i++) {
            degenerado.insertar(i);
        }
        mostrarConsultas(degenerado);

        System.out.println("\n=== esABB sobre arboles armados a mano ===");
        NodoArbol valido = new NodoArbol(50);
        valido.izquierdo = new NodoArbol(30);
        valido.izquierdo.derecho = new NodoArbol(40);
        valido.derecho = new NodoArbol(70);
        System.out.println("Arbol manual valido = " + UtilizacionArbol.esABB(valido));

        NodoArbol invalido = new NodoArbol(50);
        invalido.izquierdo = new NodoArbol(30);
        invalido.izquierdo.derecho = new NodoArbol(60);
        System.out.println("60 a la derecha de 30 pero a la izquierda de 50 = "
                + UtilizacionArbol.esABB(invalido));

        NodoArbol invalidoDerecho = new NodoArbol(50);
        invalidoDerecho.derecho = new NodoArbol(70);
        invalidoDerecho.derecho.izquierdo = new NodoArbol(40);
        System.out.println("40 a la izquierda de 70 pero a la derecha de 50 = "
                + UtilizacionArbol.esABB(invalidoDerecho));

        NodoArbol repetido = new NodoArbol(50);
        repetido.izquierdo = new NodoArbol(30);
        repetido.izquierdo.derecho = new NodoArbol(50);
        System.out.println("Valor 50 repetido en otro nivel = " + UtilizacionArbol.esABB(repetido));

        ArbolBinarioBusqueda negativos = new ArbolBinarioBusqueda();
        negativos.insertar(-10);
        negativos.insertar(-20);
        negativos.insertar(-5);
        System.out.println("\n=== Valores negativos ===");
        mostrarConsultas(negativos);
    }

    private static void mostrarConsultas(ArbolBinarioBusqueda arbol) {
        System.out.println("esVacio() = " + arbol.esVacio());
        System.out.println("cantidadNodos() = " + arbol.cantidadNodos());
        System.out.println("altura() = " + UtilizacionArbol.altura(arbol));
        System.out.println("contarHojas() = " + UtilizacionArbol.contarHojas(arbol));
        System.out.println("sumaNodos() = " + UtilizacionArbol.sumaNodos(arbol));
        System.out.println("esABB() = " + UtilizacionArbol.esABB(arbol.getRaiz()));
    }
}
