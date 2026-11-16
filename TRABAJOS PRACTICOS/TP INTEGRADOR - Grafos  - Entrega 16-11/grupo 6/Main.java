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
        System.out.println("existeArista(A, B) = " + grafo.existeArista("A", "B"));
        System.out.println("existeArista(A, D) = " + grafo.existeArista("A", "D"));

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

        System.out.println();
        System.out.println("=== 4.1 estanConectados ===");
        System.out.println("estanConectados(A, F) = " + RecorridosGrafo.estanConectados(grafo, "A", "F"));
        System.out.println("estanConectados(A, G) = " + RecorridosGrafo.estanConectados(grafo, "A", "G"));

        System.out.println();
        System.out.println("=== 4.2 componentesConexas ===");
        System.out.println("componentesConexas = " + RecorridosGrafo.componentesConexas(grafo));

        System.out.println();
        System.out.println("=== 4.3 caminoMasCorto ===");
        System.out.println("caminoMasCorto(A, F) = " + RecorridosGrafo.caminoMasCorto(grafo, "A", "F"));
        System.out.println("caminoMasCorto(A, G) = " + RecorridosGrafo.caminoMasCorto(grafo, "A", "G"));

        System.out.println();
        System.out.println("=== Casos borde ===");
        System.out.println("existeArista(B, A) = " + grafo.existeArista("B", "A"));
        System.out.println("BFS desde G = " + RecorridosGrafo.bfs(grafo, "G"));
        System.out.println("estanConectados(A, A) = " + RecorridosGrafo.estanConectados(grafo, "A", "A"));
        System.out.println("caminoMasCorto(A, A) = " + RecorridosGrafo.caminoMasCorto(grafo, "A", "A"));

        Grafo chico = new Grafo(3);
        chico.agregarVertice("A");
        try {
            chico.agregarVertice("A");
        } catch (RuntimeException e) {
            System.out.println("Error esperado: " + e.getMessage());
        }
        try {
            grafo.agregarVertice("H");
        } catch (RuntimeException e) {
            System.out.println("Error esperado: " + e.getMessage());
        }
        try {
            grafo.agregarArista("A", "A");
        } catch (RuntimeException e) {
            System.out.println("Error esperado: " + e.getMessage());
        }
        try {
            grafo.agregarArista("A", "B");
        } catch (RuntimeException e) {
            System.out.println("Error esperado: " + e.getMessage());
        }
        try {
            RecorridosGrafo.bfs(grafo, "Z");
        } catch (RuntimeException e) {
            System.out.println("Error esperado: " + e.getMessage());
        }
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
