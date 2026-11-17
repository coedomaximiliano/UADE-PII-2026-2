public class UtilizacionGrafo {
    public static boolean estanConectados(Grafo g, String v1, String v2) {
        if (!g.existeVertice(v2)) {
            throw new RuntimeException("estanConectados(): el vertice no pertenece al grafo -> " + v2);
        }
        Lista alcanzados = g.bfs(v1);
        for (int i = 0; i < alcanzados.tamanio(); i++) {
            if (alcanzados.obtener(i).equals(v2)) {
                return true;
            }
        }
        return false;
    }

    public static int componentesConexas(Grafo g) {
        Diccionario visitados = new DiccionarioDinamico();
        Lista vertices = g.vertices();
        int componentes = 0;

        for (int i = 0; i < vertices.tamanio(); i++) {
            String v = (String) vertices.obtener(i);
            if (!visitados.existeClave(v)) {
                componentes++;
                Lista componente = g.bfs(v);
                for (int k = 0; k < componente.tamanio(); k++) {
                    visitados.definir(componente.obtener(k), true);
                }
            }
        }
        return componentes;
    }

    public static int caminoMasCorto(Grafo g, String origen, String destino) {
        if (!g.existeVertice(destino)) {
            throw new RuntimeException("caminoMasCorto(): el vertice no pertenece al grafo -> " + destino);
        }
        Diccionario distancias = g.distanciasBFS(origen);
        if (!distancias.existeClave(destino)) {
            return -1;
        }
        return (Integer) distancias.obtener(destino);
    }
}
