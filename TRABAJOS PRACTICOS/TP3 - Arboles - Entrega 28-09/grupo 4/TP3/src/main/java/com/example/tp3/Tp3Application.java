package com.example.tp3;

public class Tp3Application {

    public static void main(String[] args) {
        ArbolBinarioBusqueda arbol = ArbolBinarioBusqueda.crear();
        int[] valores = {50, 30, 70, 20, 40, 60, 80, 10, 25, 65, 90};
        for (int valor : valores) arbol.insertar(valor);

        System.out.print("Inorder: ");
        arbol.inorder();
        System.out.print("Preorder: ");
        arbol.preorder();
        System.out.print("Postorder: ");
        arbol.postorder();
        System.out.print("Por niveles: ");
        arbol.recorridoPorNiveles();
        System.out.println("Cantidad de nodos: " + arbol.cantidadNodos());
        System.out.println("Altura: " + UtilizacionArbol.altura(arbol));
        System.out.println("Hojas: " + UtilizacionArbol.contarHojas(arbol));
        System.out.println("Suma: " + UtilizacionArbol.sumaNodos(arbol));
        System.out.println("Es ABB: " + UtilizacionArbol.esABB(arbol.getRaiz()));

        // 60 > 30, pero 60 está a la izquierda de 50: viola al ancestro.
        NodoArbol invalido = new NodoArbol(50,
                new NodoArbol(30, null, new NodoArbol(60)), new NodoArbol(70));
        System.out.println("Árbol con violación de ancestro: " + UtilizacionArbol.esABB(invalido));
    }

}
