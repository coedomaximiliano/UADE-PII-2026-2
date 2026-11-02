public class RecorridosGrafo {
    public static Lista bfs(Grafo grafo, String origen) {
        int indiceOrigen = grafo.indiceDe(origen);
        int cantidad = grafo.cantidadVertices();
        boolean[] visitados = new boolean[cantidad];
        Cola pendientes = new ColaCircular(cantidad);
        Lista orden = new Lista();

        visitados[indiceOrigen] = true;
        pendientes.encolar(indiceOrigen);

        while (!pendientes.esVacia()) {
            int actual = pendientes.desencolar();
            orden.agregar(grafo.etiquetaDe(actual));

            int[] vecinos = grafo.vecinosPorIndice(actual);
            for (int i = 0; i < vecinos.length; i++) {
                int vecino = vecinos[i];
                if (!visitados[vecino]) {
                    // Se marca al encolar para que no quede pendiente dos veces.
                    visitados[vecino] = true;
                    pendientes.encolar(vecino);
                }
            }
        }

        return orden;
    }

    public static Lista dfs(Grafo grafo, String origen) {
        int indiceOrigen = grafo.indiceDe(origen);
        boolean[] visitados = new boolean[grafo.cantidadVertices()];
        Lista orden = new Lista();

        dfsRecursivo(grafo, indiceOrigen, visitados, orden);
        return orden;
    }

    private static void dfsRecursivo(Grafo grafo, int actual, boolean[] visitados, Lista orden) {
        visitados[actual] = true;
        orden.agregar(grafo.etiquetaDe(actual));

        int[] vecinos = grafo.vecinosPorIndice(actual);
        for (int i = 0; i < vecinos.length; i++) {
            int vecino = vecinos[i];
            if (!visitados[vecino]) {
                dfsRecursivo(grafo, vecino, visitados, orden);
            }
        }
    }

    public static Lista dfsIterativo(Grafo grafo, String origen) {
        int indiceOrigen = grafo.indiceDe(origen);
        boolean[] visitados = new boolean[grafo.cantidadVertices()];
        Pila pendientes = new PilaVarianteA(grafo.cantidadVertices());
        Lista orden = new Lista();

        visitados[indiceOrigen] = true;
        pendientes.apilar(indiceOrigen);
        orden.agregar(grafo.etiquetaDe(indiceOrigen));

        while (!pendientes.esVacia()) {
            int actual = pendientes.tope();
            int[] vecinos = grafo.vecinosPorIndice(actual);
            boolean avanzo = false;

            for (int i = 0; i < vecinos.length && !avanzo; i++) {
                int vecino = vecinos[i];
                if (!visitados[vecino]) {
                    visitados[vecino] = true;
                    pendientes.apilar(vecino);
                    orden.agregar(grafo.etiquetaDe(vecino));
                    avanzo = true;
                }
            }

            if (!avanzo) {
                pendientes.desapilar();
            }
        }

        return orden;
    }
}
