public class GrafoMatriz implements Grafo {
    private boolean[][] matriz;
    private String[] etiquetas;
    private Diccionario indices;
    private int cantidad;
    private int capacidad;
    private int aristas;

    public GrafoMatriz(int capacidad) {
        if (capacidad < 0) {
            throw new RuntimeException("crear(): la capacidad no puede ser negativa");
        }
        this.capacidad = capacidad;
        this.matriz = new boolean[capacidad][capacidad];
        this.etiquetas = new String[capacidad];
        this.indices = new DiccionarioDinamico();
        this.cantidad = 0;
        this.aristas = 0;
    }

    private int indiceDe(String v) {
        if (!existeVertice(v)) {
            throw new RuntimeException("el vertice no pertenece al grafo -> " + v);
        }
        return (Integer) indices.obtener(v);
    }

    @Override
    public void agregarVertice(String etiqueta) {
        if (etiqueta == null) {
            throw new RuntimeException("agregarVertice(): la etiqueta no puede ser null");
        }
        if (indices.existeClave(etiqueta)) {
            throw new RuntimeException("agregarVertice(): el vertice ya existe -> " + etiqueta);
        }
        if (cantidad == capacidad) {
            throw new RuntimeException("agregarVertice(): grafo lleno (capacidad " + capacidad + ")");
        }
        indices.definir(etiqueta, cantidad);
        etiquetas[cantidad] = etiqueta;
        cantidad++;
    }

    @Override
    public void agregarArista(String v1, String v2) {
        int i = indiceDe(v1);
        int j = indiceDe(v2);
        if (i == j) {
            throw new RuntimeException("agregarArista(): no se permiten aristas de un vertice consigo mismo -> " + v1);
        }
        if (!matriz[i][j]) {
            matriz[i][j] = true;
            matriz[j][i] = true;
            aristas++;
        }
    }

    @Override
    public boolean existeArista(String v1, String v2) {
        return matriz[indiceDe(v1)][indiceDe(v2)];
    }

    @Override
    public Lista vecinos(String v) {
        int i = indiceDe(v);
        Lista resultado = new Lista();
        for (int j = 0; j < cantidad; j++) {
            if (matriz[i][j]) {
                resultado.agregar(etiquetas[j]);
            }
        }
        return resultado;
    }

    @Override
    public int cantidadVertices() {
        return cantidad;
    }

    @Override
    public boolean existeVertice(String v) {
        return v != null && indices.existeClave(v);
    }

    @Override
    public Lista vertices() {
        Lista resultado = new Lista();
        for (int i = 0; i < cantidad; i++) {
            resultado.agregar(etiquetas[i]);
        }
        return resultado;
    }

    @Override
    public Lista bfs(String origen) {
        int o = indiceDe(origen);
        Lista orden = new Lista();
        boolean[] visitado = new boolean[cantidad];
        ColaCircular cola = new ColaCircular(cantidad);

        visitado[o] = true;
        cola.encolar(o);
        while (!cola.esVacia()) {
            int actual = cola.desencolar();
            orden.agregar(etiquetas[actual]);
            for (int j = 0; j < cantidad; j++) {
                if (matriz[actual][j] && !visitado[j]) {
                    visitado[j] = true;
                    cola.encolar(j);
                }
            }
        }
        return orden;
    }

    @Override
    public Lista dfs(String origen) {
        int o = indiceDe(origen);
        Lista orden = new Lista();
        dfsRec(o, new boolean[cantidad], orden);
        return orden;
    }

    private void dfsRec(int v, boolean[] visitado, Lista orden) {
        visitado[v] = true;
        orden.agregar(etiquetas[v]);
        for (int j = 0; j < cantidad; j++) {
            if (matriz[v][j] && !visitado[j]) {
                dfsRec(j, visitado, orden);
            }
        }
    }

    @Override
    public Lista dfsIterativo(String origen) {
        return dfsConPila(origen, false);
    }

    @Override
    public Lista dfsIterativoInvertido(String origen) {
        return dfsConPila(origen, true);
    }

    private Lista dfsConPila(String origen, boolean invertirVecinos) {
        int o = indiceDe(origen);
        Lista orden = new Lista();
        boolean[] visitado = new boolean[cantidad];
        Pila pila = new PilaVarianteA(2 * aristas + 1);

        pila.apilar(o);
        while (!pila.esVacia()) {
            int actual = pila.desapilar();
            if (visitado[actual]) {
                continue;
            }
            visitado[actual] = true;
            orden.agregar(etiquetas[actual]);

            if (invertirVecinos) {
                for (int j = cantidad - 1; j >= 0; j--) {
                    if (matriz[actual][j] && !visitado[j]) {
                        pila.apilar(j);
                    }
                }
            } else {
                for (int j = 0; j < cantidad; j++) {
                    if (matriz[actual][j] && !visitado[j]) {
                        pila.apilar(j);
                    }
                }
            }
        }
        return orden;
    }

    @Override
    public Diccionario distanciasBFS(String origen) {
        int o = indiceDe(origen);
        int[] distancia = new int[cantidad];
        for (int i = 0; i < cantidad; i++) {
            distancia[i] = -1;
        }
        ColaCircular cola = new ColaCircular(cantidad);

        distancia[o] = 0;
        cola.encolar(o);
        while (!cola.esVacia()) {
            int actual = cola.desencolar();
            for (int j = 0; j < cantidad; j++) {
                if (matriz[actual][j] && distancia[j] == -1) {
                    distancia[j] = distancia[actual] + 1;
                    cola.encolar(j);
                }
            }
        }

        Diccionario resultado = new DiccionarioDinamico();
        for (int i = 0; i < cantidad; i++) {
            if (distancia[i] != -1) {
                resultado.definir(etiquetas[i], distancia[i]);
            }
        }
        return resultado;
    }
}
