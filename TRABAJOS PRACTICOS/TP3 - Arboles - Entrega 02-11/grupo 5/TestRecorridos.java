public class TestRecorridos {
    public static void main(String[] args) {

        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();

        // insertamos todos los elementos en un orden aleatorio (dado en el enunciado).
        arbol.insertar(50);
        arbol.insertar(30);
        arbol.insertar(70);
        arbol.insertar(20);
        arbol.insertar(40);
        arbol.insertar(60);
        arbol.insertar(80);
        arbol.insertar(10);
        arbol.insertar(25);
        arbol.insertar(65);
        arbol.insertar(90);

        // impresion de los metodos de recorrido.
        arbol.inorder();              // 10 20 25 30 40 50 60 65 70 80 90
        arbol.preorder();             // 50 30 20 10 25 40 70 60 65 80 90
        arbol.postorder();            // 10 25 20 40 30 65 60 90 80 70 50
        arbol.recorridoPorNiveles();  // 50 30 70 20 40 60 80 10 25 65 90
    }
}