package Implementaciones;

public class Grafo {
    public boolean[][] adyacencia;
    public String[] etiquetas;   // etiquetas[i] = etiqueta del vertice de indice i
    public Diccionario indices;  // etiqueta -> indice
    public int capacidad;
    public int cantidadVertices;

    public Grafo(int n) {
        this.capacidad = n;
        this.adyacencia = new boolean[n][n];
        this.etiquetas = new String[n];
        this.indices = new Diccionario();
        this.cantidadVertices = 0;
    }

    public void agregarVertice(String etiqueta) {
        if (cantidadVertices == capacidad) {
            throw new RuntimeException("Grafo lleno");
        }
        if (indices.existeClave(etiqueta)) {
            throw new RuntimeException("La etiqueta ya existe: " + etiqueta);
        }
        indices.insertar(etiqueta, cantidadVertices);
        etiquetas[cantidadVertices] = etiqueta;
        cantidadVertices++;
    }

    public void agregarArista(String v1, String v2) {
        int i = indices.obtener(v1);
        int j = indices.obtener(v2);
        adyacencia[i][j] = true;
        adyacencia[j][i] = true;
    }

    public boolean existeArista(String v1, String v2) {
        int i = indices.obtener(v1);
        int j = indices.obtener(v2);
        return adyacencia[i][j];
    }

    public int cantidadVertices() {
        return cantidadVertices;
    }

    public int[] vecinos(String etiqueta) {
        return vecinosPorIndice(indices.obtener(etiqueta));
    }

    public int[] vecinosPorIndice(int v) {
        int cant = 0;
        for (int j = 0; j < cantidadVertices; j++) {
            if (adyacencia[v][j]) {
                cant++;
            }
        }
        int[] resultado = new int[cant];
        int k = 0;
        for (int j = 0; j < cantidadVertices; j++) {
            if (adyacencia[v][j]) {
                resultado[k] = j;
                k++;
            }
        }
        return resultado;
    }

    public String etiquetaDe(int indice) {
        return etiquetas[indice];
    }

    public int indiceDe(String etiqueta) {
        return indices.obtener(etiqueta);
    }
}
