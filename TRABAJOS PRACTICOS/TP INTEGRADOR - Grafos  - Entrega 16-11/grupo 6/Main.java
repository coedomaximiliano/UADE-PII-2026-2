public class Main {
    public static void main(String[] args) {
        Grafo grafo = crearGrafoEjemplo();

        System.out.println("=== Grafo de ejemplo ===");
        System.out.println("Cantidad de vertices = " + grafo.cantidadVertices());
        System.out.println("Origen elegido para los recorridos: A");
        System.out.println();

        for (int i = 0; i < grafo.cantidadVertices(); i++) {
            String etiqueta = grafo.etiquetaDe(i);
            System.out.println("Vecinos de " + etiqueta + " = " + grafo.vecinos(etiqueta));
        }

        System.out.println();
        System.out.println("G esta aislado = " + grafo.vecinos("G").esVacia());

        System.out.println();
        System.out.println("=== 3.1 BFS desde A ===");
        Lista recorrido = RecorridosGrafo.bfs(grafo, "A");
        System.out.println("Orden de visita = " + recorrido);

        System.out.println();
        System.out.println("=== 3.2 DFS recursivo desde A ===");
        Lista recorridoDfs = RecorridosGrafo.dfs(grafo, "A");
        System.out.println("Orden de visita = " + recorridoDfs);

        System.out.println();
        System.out.println("=== 3.3 DFS iterativo desde A ===");
        Lista recorridoIterativo = RecorridosGrafo.dfsIterativo(grafo, "A");
        System.out.println("Orden de visita = " + recorridoIterativo);
    }

    private static Grafo crearGrafoEjemplo() {
        Grafo grafo = new Grafo(7);
        String[] vertices = { "A", "B", "C", "D", "E", "F", "G" };
        for (int i = 0; i < vertices.length; i++) {
            grafo.agregarVertice(vertices[i]);
        }

        grafo.agregarArista("A", "B");
        grafo.agregarArista("A", "C");
        grafo.agregarArista("B", "D");
        grafo.agregarArista("B", "E");
        grafo.agregarArista("C", "E");
        grafo.agregarArista("D", "F");

        return grafo;
    }
}
