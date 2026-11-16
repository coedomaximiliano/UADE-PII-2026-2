public class MainTPI {
    static String unir(Lista l) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < l.tamanio(); i++) {
            if (i > 0) {
                sb.append(" -> ");
            }
            sb.append(l.obtener(i));
        }
        return sb.toString();
    }

    static String enCorchetes(Lista l) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < l.tamanio(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(l.obtener(i));
        }
        return sb.append("]").toString();
    }

    static String describirCamino(int aristas) {
        return aristas == -1 ? "-1 (no existe camino)" : aristas + " arista(s)";
    }

    public static void main(String[] args) {
        Grafo g = new GrafoMatriz(8);
        String[] personas = {
            "Juan", "Alejo", "Mateo", "Fran",
            "Maxi", "Ayelen", "Ramiro", "Camila"
        };
        for (int i = 0; i < personas.length; i++) {
            g.agregarVertice(personas[i]);
        }
        g.agregarArista("Juan", "Alejo");
        g.agregarArista("Juan", "Mateo");
        g.agregarArista("Juan", "Fran");
        g.agregarArista("Alejo", "Fran");
        g.agregarArista("Mateo", "Maxi");
        g.agregarArista("Maxi", "Ayelen");
        g.agregarArista("Ramiro", "Camila");

        System.out.println("=== GRAFO DE EJEMPLO ===");
        System.out.println("cantidadVertices() = " + g.cantidadVertices());
        Lista vs = g.vertices();
        for (int i = 0; i < vs.tamanio(); i++) {
            String v = (String) vs.obtener(i);
            System.out.println("  vecinos(" + v + ") = " + enCorchetes(g.vecinos(v)));
        }
        System.out.println("existeArista(Juan, Mateo) = " + g.existeArista("Juan", "Mateo"));
        System.out.println("existeArista(Mateo, Juan) = " + g.existeArista("Mateo", "Juan"));
        System.out.println("existeArista(Juan, Ayelen) = " + g.existeArista("Juan", "Ayelen"));

        System.out.println("\n=== PRECONDICIONES ===");
        try {
            g.agregarVertice("Mateo");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        try {
            g.agregarVertice("Ivan");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        try {
            g.agregarArista("Mateo", "Ivan");
        } catch (RuntimeException e) {
            System.out.println("agregarArista(): " + e.getMessage());
        }

        String origen = "Juan";
        System.out.println("\n=== PARTE 3: RECORRIDOS desde " + origen + " ===");
        System.out.println("bfs:                    " + unir(g.bfs(origen)));
        System.out.println("dfs (recursivo):        " + unir(g.dfs(origen)));
        System.out.println("dfsIterativo:           " + unir(g.dfsIterativo(origen)));
        System.out.println("dfsIterativoInvertido:  " + unir(g.dfsIterativoInvertido(origen)));

        System.out.println("\n=== PARTE 4: UTILIZACION ===");
        System.out.println("estanConectados(Alejo, Ayelen)  = " + UtilizacionGrafo.estanConectados(g, "Alejo", "Ayelen"));
        System.out.println("estanConectados(Ramiro, Camila) = " + UtilizacionGrafo.estanConectados(g, "Ramiro", "Camila"));
        System.out.println("estanConectados(Juan, Ramiro)   = " + UtilizacionGrafo.estanConectados(g, "Juan", "Ramiro"));
        System.out.println();
        System.out.println("componentesConexas(g) = " + UtilizacionGrafo.componentesConexas(g));
        System.out.println();
        System.out.println("caminoMasCorto(Juan, Ayelen)  = " + describirCamino(UtilizacionGrafo.caminoMasCorto(g, "Juan", "Ayelen")));
        System.out.println("caminoMasCorto(Fran, Maxi)    = " + describirCamino(UtilizacionGrafo.caminoMasCorto(g, "Fran", "Maxi")));
        System.out.println("caminoMasCorto(Alejo, Fran)   = " + describirCamino(UtilizacionGrafo.caminoMasCorto(g, "Alejo", "Fran")));
        System.out.println("caminoMasCorto(Mateo, Ramiro) = " + describirCamino(UtilizacionGrafo.caminoMasCorto(g, "Mateo", "Ramiro")));
    }
}
