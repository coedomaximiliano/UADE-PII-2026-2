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


    public static boolean estanConectados(Grafo grafo, String v1, String v2) {
        int origen = grafo.indiceDe(v1);
        int destino = grafo.indiceDe(v2);
        boolean[] visitados = new boolean[grafo.cantidadVertices()];
        Cola pendientes = new ColaCircular(grafo.cantidadVertices());

        visitados[origen] = true;
        pendientes.encolar(origen);

        while (!pendientes.esVacia()) {
            int actual = pendientes.desencolar();
            if (actual == destino) {
                return true;
            }

            int[] vecinos = grafo.vecinosPorIndice(actual);
            for (int i = 0; i < vecinos.length; i++) {
                int vecino = vecinos[i];
                if (!visitados[vecino]) {
                    visitados[vecino] = true;
                    pendientes.encolar(vecino);
                }
            }
        }

        return false;
    }

    public static int componentesConexas(Grafo grafo) {
        int cantidad = grafo.cantidadVertices();
        boolean[] visitados = new boolean[cantidad];
        int componentes = 0;

        for (int i = 0; i < cantidad; i++) {
            if (!visitados[i]) {
                componentes++;
                Cola pendientes = new ColaCircular(cantidad);
                visitados[i] = true;
                pendientes.encolar(i);

                while (!pendientes.esVacia()) {
                    int actual = pendientes.desencolar();
                    int[] vecinos = grafo.vecinosPorIndice(actual);
                    for (int j = 0; j < vecinos.length; j++) {
                        int vecino = vecinos[j];
                        if (!visitados[vecino]) {
                            visitados[vecino] = true;
                            pendientes.encolar(vecino);
                        }
                    }
                }
            }
        }

        return componentes;
    }

    public static int caminoMasCorto(Grafo grafo, String origen, String destino) {
        int indiceOrigen = grafo.indiceDe(origen);
        int indiceDestino = grafo.indiceDe(destino);
        int cantidad = grafo.cantidadVertices();
        boolean[] visitados = new boolean[cantidad];
        int[] distancia = new int[cantidad];
        Cola pendientes = new ColaCircular(cantidad);

        visitados[indiceOrigen] = true;
        distancia[indiceOrigen] = 0;
        pendientes.encolar(indiceOrigen);

        while (!pendientes.esVacia()) {
            int actual = pendientes.desencolar();
            if (actual == indiceDestino) {
                return distancia[actual];
            }

            int[] vecinos = grafo.vecinosPorIndice(actual);
            for (int i = 0; i < vecinos.length; i++) {
                int vecino = vecinos[i];
                if (!visitados[vecino]) {
                    visitados[vecino] = true;
                    distancia[vecino] = distancia[actual] + 1;
                    pendientes.encolar(vecino);
                }
            }
        }

        return -1;
    }

}
