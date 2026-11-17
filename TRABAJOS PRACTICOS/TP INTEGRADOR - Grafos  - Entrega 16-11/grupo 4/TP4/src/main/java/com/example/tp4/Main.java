package com.example.tp4;

/**
 * Clase principal del Trabajo Práctico Integrador — TDA Grafo.
 * Incluye la implementación de los recorridos (Parte 3), los métodos de utilización (Parte 4)
 * y el método main() con el grafo de ejemplo y las pruebas completas por consola.
 *
 * Nota: Ningún método accede a la estructura interna de Grafo, Pila, Cola ni Diccionario;
 * todos operan exclusivamente a través de la interfaz pública de cada TDA.
 */
public class Main {

    // =========================================================================
    // PARTE 3 — RECORRIDOS
    // =========================================================================

    /**
     * 3.1 BFS — Búsqueda en anchura.
     * Visita los vértices por niveles de distancia desde el vértice origen utilizando el TDA Cola (TP1)
     * y el TDA Diccionario (TP2) para registrar los vértices visitados.
     *
     * Precondición: el vértice origen debe pertenecer al grafo g.
     * Complejidad temporal: O(n^2) sobre matriz de adyacencia (cada vértice alcanzable se encola/desencola
     * a lo sumo una vez en O(1), y consultar g.vecinos(actual) recorre una fila de tamaño n en O(n)).
     */
    public static String[] bfs(Grafo g, String origen) {
        if (!g.existeVertice(origen)) {
            throw new IllegalArgumentException("El vértice origen '" + origen + "' no pertenece al grafo.");
        }

        Cola<String> cola = Cola.crear();
        Diccionario<String, Boolean> visitados = Diccionario.crear();
        String[] temporal = new String[g.cantidadVertices()];
        int cantidadVisitados = 0;

        visitados.agregar(origen, true);
        cola.encolar(origen);

        while (!cola.estaVacia()) {
            String actual = cola.desencolar();
            temporal[cantidadVisitados++] = actual;

            String[] adyacentes = g.vecinos(actual);
            for (int i = 0; i < adyacentes.length; i++) {
                String vecino = adyacentes[i];
                if (!visitados.existeClave(vecino)) {
                    visitados.agregar(vecino, true);
                    cola.encolar(vecino);
                }
            }
        }

        return recortarArreglo(temporal, cantidadVisitados);
    }

    /**
     * 3.2 DFS — Búsqueda en profundidad (recursiva).
     * Visita los vértices en profundidad de forma recursiva partiendo desde origen.
     *
     * Precondición: el vértice origen debe pertenecer al grafo g.
     * Complejidad temporal: O(n^2) sobre matriz de adyacencia (cada vértice alcanzable se visita
     * exactamente una vez y g.vecinos(actual) cuesta O(n) por cada vértice visitado).
     */
    public static String[] dfs(Grafo g, String origen) {
        if (!g.existeVertice(origen)) {
            throw new IllegalArgumentException("El vértice origen '" + origen + "' no pertenece al grafo.");
        }

        Diccionario<String, Boolean> visitados = Diccionario.crear();
        String[] temporal = new String[g.cantidadVertices()];
        int[] contador = new int[]{0};

        dfsRecursivo(g, origen, visitados, temporal, contador);

        return recortarArreglo(temporal, contador[0]);
    }

    private static void dfsRecursivo(
            Grafo g,
            String actual,
            Diccionario<String, Boolean> visitados,
            String[] resultado,
            int[] contador) {

        visitados.agregar(actual, true);
        resultado[contador[0]++] = actual;

        String[] adyacentes = g.vecinos(actual);
        for (int i = 0; i < adyacentes.length; i++) {
            String vecino = adyacentes[i];
            if (!visitados.existeClave(vecino)) {
                dfsRecursivo(g, vecino, visitados, resultado, contador);
            }
        }
    }

    /**
     * 3.3 DFS iterativo (desafío adicional).
     * Resuelve el recorrido en profundidad de forma iterativa empleando el TDA Pila (TP1)
     * en lugar de la pila de llamadas de la recursión.
     *
     * Precondición: el vértice origen debe pertenecer al grafo g.
     * Complejidad temporal: O(n^2) sobre matriz de adyacencia.
     */
    public static String[] dfsIterativo(Grafo g, String origen) {
        if (!g.existeVertice(origen)) {
            throw new IllegalArgumentException("El vértice origen '" + origen + "' no pertenece al grafo.");
        }

        Pila<String> pila = Pila.crear();
        Diccionario<String, Boolean> visitados = Diccionario.crear();
        String[] temporal = new String[g.cantidadVertices()];
        int cantidadVisitados = 0;

        pila.apilar(origen);

        while (!pila.estaVacia()) {
            String actual = pila.desapilar();

            if (!visitados.existeClave(actual)) {
                visitados.agregar(actual, true);
                temporal[cantidadVisitados++] = actual;

                String[] adyacentes = g.vecinos(actual);
                for (int i = 0; i < adyacentes.length; i++) {
                    String vecino = adyacentes[i];
                    if (!visitados.existeClave(vecino)) {
                        pila.apilar(vecino);
                    }
                }
            }
        }

        return recortarArreglo(temporal, cantidadVisitados);
    }

    // =========================================================================
    // PARTE 4 — UTILIZACIÓN
    // =========================================================================

    /**
     * 4.1 Indica si existe algún camino entre v1 y v2 (sin importar la cantidad de aristas).
     * Utiliza el recorrido BFS (aunque DFS es indistinto para alcanzabilidad) y verifica
     * si v2 fue alcanzado partiendo desde v1.
     *
     * Precondición: tanto v1 como v2 deben pertenecer al grafo g.
     * Complejidad temporal: O(n^2) sobre matriz de adyacencia.
     */
    public static boolean estanConectados(Grafo g, String v1, String v2) {
        if (!g.existeVertice(v1) || !g.existeVertice(v2)) {
            throw new IllegalArgumentException("Ambos vértices deben pertenecer al grafo.");
        }
        if (v1.equals(v2)) {
            return true;
        }

        String[] alcanzables = bfs(g, v1);
        for (int i = 0; i < alcanzables.length; i++) {
            if (alcanzables[i].equals(v2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 4.2 Devuelve la cantidad de componentes conexas del grafo no dirigido.
     * Recorre todos los vértices del grafo; cada vez que encuentra un vértice no visitado,
     * contabiliza una nueva componente conexa y explora todos los vértices de dicha componente
     * mediante BFS para marcarlos como visitados.
     *
     * Complejidad temporal: O(n^2) sobre matriz de adyacencia (aunque se itera sobre los n vértices,
     * el recorrido BFS solo se inicia una vez por componente conexa y, en total, cada vértice
     * es visitado y su fila de tamaño n es inspeccionada exactamente una vez en todo el algoritmo).
     */
    public static int componentesConexas(Grafo g) {
        String[] todosLosVertices = g.vertices();
        Diccionario<String, Boolean> visitados = Diccionario.crear();
        int cantidadComponentes = 0;

        for (int i = 0; i < todosLosVertices.length; i++) {
            String vertice = todosLosVertices[i];
            if (!visitados.existeClave(vertice)) {
                cantidadComponentes++;
                String[] alcanzablesEnComponente = bfs(g, vertice);
                for (int j = 0; j < alcanzablesEnComponente.length; j++) {
                    visitados.agregar(alcanzablesEnComponente[j], true);
                }
            }
        }

        return cantidadComponentes;
    }

    /**
     * 4.3 Devuelve la cantidad de aristas del camino más corto entre origen y destino,
     * o -1 si no existe ningún camino que los conecte.
     * Resuelto mediante BFS registrando en un TDA Diccionario la distancia (nivel) de cada vértice
     * respecto al origen.
     *
     * Precondición: tanto origen como destino deben pertenecer al grafo g.
     * Complejidad temporal: O(n^2) sobre matriz de adyacencia.
     */
    public static int caminoMasCorto(Grafo g, String origen, String destino) {
        if (!g.existeVertice(origen) || !g.existeVertice(destino)) {
            throw new IllegalArgumentException("Tanto el origen como el destino deben pertenecer al grafo.");
        }
        if (origen.equals(destino)) {
            return 0;
        }

        Cola<String> cola = Cola.crear();
        Diccionario<String, Integer> distancias = Diccionario.crear();

        distancias.agregar(origen, 0);
        cola.encolar(origen);

        while (!cola.estaVacia()) {
            String actual = cola.desencolar();
            int distanciaActual = distancias.obtener(actual);

            if (actual.equals(destino)) {
                return distanciaActual;
            }

            String[] adyacentes = g.vecinos(actual);
            for (int i = 0; i < adyacentes.length; i++) {
                String vecino = adyacentes[i];
                if (!distancias.existeClave(vecino)) {
                    distancias.agregar(vecino, distanciaActual + 1);
                    cola.encolar(vecino);
                }
            }
        }

        return -1; // Indica que no existe camino entre origen y destino
    }

    // =========================================================================
    // MÉTODOS AUXILIARES Y DEMOSTRACIÓN (MAIN)
    // =========================================================================

    private static String[] recortarArreglo(String[] arreglo, int longitud) {
        String[] resultado = new String[longitud];
        for (int i = 0; i < longitud; i++) {
            resultado[i] = arreglo[i];
        }
        return resultado;
    }

    private static String formatearSecuencia(String[] elementos) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < elementos.length; i++) {
            sb.append(elementos[i]);
            if (i < elementos.length - 1) {
                sb.append(" -> ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private static String formatearConjunto(String[] elementos) {
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < elementos.length; i++) {
            sb.append(elementos[i]);
            if (i < elementos.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * Construye el grafo de ejemplo de 8 ciudades argentinas conectadas por rutas directas:
     * - Componente conexa principal (7 ciudades):
     *   Buenos Aires, Rosario, Cordoba, Mendoza, Santa Fe, Tucuman, Salta.
     * - Componente desconectada (1 ciudad insular sin conexión terrestre directa en la red):
     *   Ushuaia.
     */
    public static Grafo construirGrafoEjemplo() {
        Grafo g = Grafo.crear(8);

        // Agregamos los 8 vértices (índices 0 a 7)
        g.agregarVertice("Buenos Aires"); // 0
        g.agregarVertice("Rosario");      // 1
        g.agregarVertice("Cordoba");      // 2
        g.agregarVertice("Mendoza");      // 3
        g.agregarVertice("Santa Fe");     // 4
        g.agregarVertice("Tucuman");      // 5
        g.agregarVertice("Salta");        // 6
        g.agregarVertice("Ushuaia");      // 7 (Vértice sin aristas -> 2da componente conexa)

        // Agregamos las aristas (rutas directas no dirigidas)
        g.agregarArista("Buenos Aires", "Rosario");
        g.agregarArista("Buenos Aires", "Cordoba");
        g.agregarArista("Buenos Aires", "Mendoza");
        g.agregarArista("Rosario", "Cordoba");
        g.agregarArista("Rosario", "Santa Fe");
        g.agregarArista("Cordoba", "Mendoza");
        g.agregarArista("Cordoba", "Tucuman");
        g.agregarArista("Santa Fe", "Tucuman");
        g.agregarArista("Tucuman", "Salta");

        return g;
    }

    public static void main(String[] args) {
        Grafo redRutas = construirGrafoEjemplo();

        System.out.println("======================================================================");
        System.out.println("          TRABAJO PRACTICO INTEGRADOR - TDA GRAFO (GRUPO 4)           ");
        System.out.println("======================================================================");

        System.out.println("\n--- PARTE 1 y 2: Operaciones basicas del TDA Grafo ---");
        System.out.println("Cantidad de vertices actuales: " + redRutas.cantidadVertices());
        System.out.println("Vertices del grafo: " + formatearConjunto(redRutas.vertices()));
        System.out.println("¿Existe arista (Buenos Aires - Cordoba)?: "
                + redRutas.existeArista("Buenos Aires", "Cordoba"));
        System.out.println("¿Existe arista (Buenos Aires - Salta)?: "
                + redRutas.existeArista("Buenos Aires", "Salta"));
        System.out.println("Vecinos de 'Buenos Aires': "
                + formatearConjunto(redRutas.vecinos("Buenos Aires")));
        System.out.println("Vecinos de 'Cordoba':      "
                + formatearConjunto(redRutas.vecinos("Cordoba")));
        System.out.println("Vecinos de 'Ushuaia':      "
                + formatearConjunto(redRutas.vecinos("Ushuaia")));

        System.out.println("\n--- PARTE 3: Recorridos del Grafo (Origen: 'Buenos Aires') ---");
        String origen = "Buenos Aires";
        String[] ordenBfs = bfs(redRutas, origen);
        String[] ordenDfsRecursivo = dfs(redRutas, origen);
        String[] ordenDfsIterativo = dfsIterativo(redRutas, origen);

        System.out.println("3.1 BFS (con TDA Cola):          " + formatearSecuencia(ordenBfs));
        System.out.println("3.2 DFS Recursivo:               " + formatearSecuencia(ordenDfsRecursivo));
        System.out.println("3.3 DFS Iterativo (con TDA Pila):" + formatearSecuencia(ordenDfsIterativo));

        System.out.println("\n--- PARTE 4: Utilizacion del TDA Grafo ---");
        System.out.println("4.1 estanConectados:");
        System.out.println("  - ¿'Buenos Aires' y 'Salta' estan conectados?:   "
                + estanConectados(redRutas, "Buenos Aires", "Salta") + " (esperado: true)");
        System.out.println("  - ¿'Mendoza' y 'Santa Fe' estan conectados?:     "
                + estanConectados(redRutas, "Mendoza", "Santa Fe") + " (esperado: true)");
        System.out.println("  - ¿'Buenos Aires' y 'Ushuaia' estan conectados?: "
                + estanConectados(redRutas, "Buenos Aires", "Ushuaia") + " (esperado: false)");

        System.out.println("\n4.2 componentesConexas:");
        System.out.println("  - Cantidad de componentes conexas del grafo: "
                + componentesConexas(redRutas)
                + " (Comp. 1: red continental de 7 ciudades | Comp. 2: Ushuaia aislada)");

        System.out.println("\n4.3 caminoMasCorto (en cantidad de aristas, usando BFS):");
        System.out.println("  - Camino mas corto 'Buenos Aires' -> 'Buenos Aires': "
                + caminoMasCorto(redRutas, "Buenos Aires", "Buenos Aires") + " aristas");
        System.out.println("  - Camino mas corto 'Buenos Aires' -> 'Cordoba':      "
                + caminoMasCorto(redRutas, "Buenos Aires", "Cordoba") + " arista (ruta directa)");
        System.out.println("  - Camino mas corto 'Buenos Aires' -> 'Tucuman':      "
                + caminoMasCorto(redRutas, "Buenos Aires", "Tucuman")
                + " aristas (Buenos Aires -> Cordoba -> Tucuman)");
        System.out.println("  - Camino mas corto 'Buenos Aires' -> 'Salta':        "
                + caminoMasCorto(redRutas, "Buenos Aires", "Salta")
                + " aristas (Buenos Aires -> Cordoba -> Tucuman -> Salta)");
        System.out.println("  - Camino mas corto 'Buenos Aires' -> 'Ushuaia':      "
                + caminoMasCorto(redRutas, "Buenos Aires", "Ushuaia")
                + " (no existe camino)");
        System.out.println("======================================================================");
    }
}
