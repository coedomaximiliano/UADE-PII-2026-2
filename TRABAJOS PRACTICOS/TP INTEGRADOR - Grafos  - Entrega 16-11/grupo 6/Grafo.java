public class Grafo {
    private boolean[][] adyacencia;
    private String[] etiquetas;
    private Diccionario indices;
    private int capacidad;
    private int cantidadVertices;

    public Grafo(int n) {
        if (n <= 0) {
            throw new RuntimeException("crear(): la capacidad debe ser mayor a 0 -> " + n);
        }
        this.capacidad = n;
        this.adyacencia = new boolean[n][n];
        this.etiquetas = new String[n];
        this.indices = new DiccionarioDinamico();
        this.cantidadVertices = 0;
    }

    public Grafo agregarVertice(String etiqueta) {
        if (etiqueta == null) {
            throw new RuntimeException("agregarVertice(): la etiqueta no puede ser nula");
        }
        if (cantidadVertices == capacidad) {
            throw new RuntimeException("agregarVertice(): grafo lleno");
        }
        if (indices.existeClave(etiqueta)) {
            throw new RuntimeException("agregarVertice(): la etiqueta ya existe -> " + etiqueta);
        }
        indices.definir(etiqueta, cantidadVertices);
        etiquetas[cantidadVertices] = etiqueta;
        cantidadVertices++;
        return this;
    }

    public Grafo agregarArista(String v1, String v2) {
        int i = indiceExistente(v1, "agregarArista");
        int j = indiceExistente(v2, "agregarArista");
        if (i == j) {
            throw new RuntimeException("agregarArista(): no se permite una arista de un vertice consigo mismo -> " + v1);
        }
        if (adyacencia[i][j]) {
            throw new RuntimeException("agregarArista(): la arista ya existe -> " + v1 + "-" + v2);
        }
        adyacencia[i][j] = true;
        adyacencia[j][i] = true;
        return this;
    }

    public boolean existeArista(String v1, String v2) {
        int i = indiceExistente(v1, "existeArista");
        int j = indiceExistente(v2, "existeArista");
        return adyacencia[i][j];
    }

    public Lista vecinos(String v) {
        int i = indiceExistente(v, "vecinos");
        Lista resultado = new Lista();
        for (int j = 0; j < cantidadVertices; j++) {
            if (adyacencia[i][j]) {
                resultado.agregar(etiquetas[j]);
            }
        }
        return resultado;
    }

    public int cantidadVertices() {
        return cantidadVertices;
    }

    public int indiceDe(String etiqueta) {
        return indiceExistente(etiqueta, "indiceDe");
    }

    public String etiquetaDe(int indice) {
        validarIndice(indice, "etiquetaDe");
        return etiquetas[indice];
    }

    public int[] vecinosPorIndice(int indice) {
        validarIndice(indice, "vecinosPorIndice");
        int cant = 0;
        for (int j = 0; j < cantidadVertices; j++) {
            if (adyacencia[indice][j]) {
                cant++;
            }
        }
        int[] resultado = new int[cant];
        int k = 0;
        for (int j = 0; j < cantidadVertices; j++) {
            if (adyacencia[indice][j]) {
                resultado[k] = j;
                k++;
            }
        }
        return resultado;
    }

    private int indiceExistente(String etiqueta, String operacion) {
        if (!indices.existeClave(etiqueta)) {
            throw new RuntimeException(operacion + "(): el vertice no pertenece al grafo -> " + etiqueta);
        }
        return (Integer) indices.obtener(etiqueta);
    }

    private void validarIndice(int indice, String operacion) {
        if (indice < 0 || indice >= cantidadVertices) {
            throw new RuntimeException(operacion + "(): indice invalido -> " + indice);
        }
    }
}
