import Implementaciones.Cola;
import Implementaciones.Grafo;

public class Metodos {

    public boolean estanConectados(Grafo g, String v1, String v2) {
        String[] alcanzables = Main.BFS(g, v1);
        for (int i = 0; i < alcanzables.length; i++) {
            if (alcanzables[i] != null && alcanzables[i].equals(v2)) {
                return true;
            }
        }
        return false;
    }

    public int componentesConexas(Grafo g) {
        int n = g.cantidadVertices();
        boolean[] visitado = new boolean[n];
        int componentes = 0;

        for (int inicio = 0; inicio < n; inicio++) {
            if (!visitado[inicio]) {
                componentes++;
                Cola cola = new Cola(n);
                cola.encolar(inicio);
                visitado[inicio] = true;

                while (!cola.esVacia()) {
                    int actual = cola.primero();
                    cola.desencolar();
                    int[] vecinos = g.vecinosPorIndice(actual);
                    for (int i = 0; i < vecinos.length; i++) {
                        int w = vecinos[i];
                        if (!visitado[w]) {
                            visitado[w] = true;
                            cola.encolar(w);
                        }
                    }
                }
            }
        }
        return componentes;
    }

    public int caminoMasCorto(Grafo g, String origen, String destino) {
        int n = g.cantidadVertices();
        int inicio = g.indiceDe(origen);
        int fin = g.indiceDe(destino);
        if (inicio == -1 || fin == -1) return -1;  // validacion
        if (inicio == fin) return 0;               // validacion
        int[] distancia = new int[n];
        for (int i = 0; i < n; i++) distancia[i] = -1;  // -1 = no visitado
        Cola cola = new Cola(n);

        cola.encolar(inicio);
        distancia[inicio] = 0;
        while (!cola.esVacia()) {
            int actual = cola.primero();
            cola.desencolar();
            if (actual == fin) return distancia[actual];
            int[] vecinos = g.vecinosPorIndice(actual);
            for (int i = 0; i < vecinos.length; i++) {
                int w = vecinos[i];
                if (distancia[w] == -1) {
                    distancia[w] = distancia[actual] + 1;
                    cola.encolar(w);
                }
            }
        }
        return -1;  // no hay camino
    }
}
