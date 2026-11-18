package com.example.tp4;

/**
 * TDA Grafo (No Dirigido y No Pesado).
 * Implementado mediante una matriz de adyacencia booleana de tamaño n x n
 * y el TDA Diccionario (de TP2) para mapear las etiquetas de los vértices (String)
 * a sus índices internos (0 .. n-1) en la matriz.
 */
public class Grafo {

    private final int capacidadMaxima;
    private int cantidadActual;
    private final boolean[][] matrizAdyacencia;
    private final Diccionario<String, Integer> indicesPorEtiqueta;
    private final String[] etiquetasPorIndice;

    /**
     * Crea un grafo vacío con capacidad máxima para n vértices.
     * Precondición: n > 0.
     * Postcondición: se inicializa un grafo sin vértices ni aristas con matriz de n x n.
     * Complejidad espacial: O(n^2).
     * Complejidad temporal: O(n^2) por la inicialización de la matriz de booleanos en memoria.
     */
    public Grafo(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("La capacidad del grafo debe ser mayor a 0.");
        }
        this.capacidadMaxima = n;
        this.cantidadActual = 0;
        this.matrizAdyacencia = new boolean[n][n];
        this.indicesPorEtiqueta = Diccionario.crear();
        this.etiquetasPorIndice = new String[n];
    }

    /**
     * Operación constructora según la especificación: crear(n): Grafo.
     */
    public static Grafo crear(int n) {
        return new Grafo(n);
    }

    /**
     * Agrega un nuevo vértice identificado por su etiqueta.
     * Precondición: etiqueta != null, cantidadVertices() < n (capacidad máxima),
     *               y la etiqueta no debe pertenecer ya al grafo (!existeVertice(etiqueta)).
     * Postcondición: el vértice queda registrado con el próximo índice disponible en la matriz.
     * Complejidad temporal: O(1) promedio (usando Diccionario con tabla de hash).
     */
    public Grafo agregarVertice(String etiqueta) {
        if (etiqueta == null || etiqueta.trim().isEmpty()) {
            throw new IllegalArgumentException("La etiqueta del vértice no puede ser nula ni vacía.");
        }
        if (this.cantidadActual >= this.capacidadMaxima) {
            throw new IllegalStateException(
                    "El grafo ya alcanzó su capacidad máxima de " + this.capacidadMaxima + " vértices.");
        }
        if (this.indicesPorEtiqueta.existeClave(etiqueta)) {
            throw new IllegalArgumentException(
                    "El vértice '" + etiqueta + "' ya existe en el grafo.");
        }
        int nuevoIndice = this.cantidadActual;
        this.indicesPorEtiqueta.agregar(etiqueta, nuevoIndice);
        this.etiquetasPorIndice[nuevoIndice] = etiqueta;
        this.cantidadActual++;
        return this;
    }

    /**
     * Sobrecarga estática acorde a la signatura de la especificación: agregarVertice(g, etiqueta): Grafo.
     */
    public static Grafo agregarVertice(Grafo g, String etiqueta) {
        return g.agregarVertice(etiqueta);
    }

    /**
     * Agrega una arista no dirigida entre los vértices v1 y v2.
     * Precondición: tanto v1 como v2 deben pertenecer al grafo.
     * Postcondición: matriz[i][j] = true y matriz[j][i] = true (simétrica).
     * Complejidad temporal: O(1) promedio.
     */
    public Grafo agregarArista(String v1, String v2) {
        validarVerticeExistente(v1);
        validarVerticeExistente(v2);
        int i = this.indicesPorEtiqueta.obtener(v1);
        int j = this.indicesPorEtiqueta.obtener(v2);
        this.matrizAdyacencia[i][j] = true;
        this.matrizAdyacencia[j][i] = true;
        return this;
    }

    /**
     * Sobrecarga estática acorde a la signatura de la especificación: agregarArista(g, v1, v2): Grafo.
     */
    public static Grafo agregarArista(Grafo g, String v1, String v2) {
        return g.agregarArista(v1, v2);
    }

    /**
     * Indica si existe una arista entre los vértices v1 y v2.
     * Precondición: tanto v1 como v2 deben pertenecer al grafo.
     * Postcondición: devuelve true si están conectados por una arista directa, false en caso contrario.
     * Complejidad temporal: O(1) promedio.
     */
    public boolean existeArista(String v1, String v2) {
        validarVerticeExistente(v1);
        validarVerticeExistente(v2);
        int i = this.indicesPorEtiqueta.obtener(v1);
        int j = this.indicesPorEtiqueta.obtener(v2);
        return this.matrizAdyacencia[i][j];
    }

    /**
     * Sobrecarga estática acorde a la signatura de la especificación: existeArista(g, v1, v2): boolean.
     */
    public static boolean existeArista(Grafo g, String v1, String v2) {
        return g.existeArista(v1, v2);
    }

    /**
     * Devuelve el conjunto (arreglo) de vértices adyacentes a v.
     * Precondición: el vértice v debe pertenecer al grafo.
     * Postcondición: devuelve un arreglo con las etiquetas de todos los vecinos directos de v.
     * Complejidad temporal: O(n), siendo n la cantidad de vértices (se recorre la fila de v en la matriz).
     */
    public String[] vecinos(String v) {
        validarVerticeExistente(v);
        int i = this.indicesPorEtiqueta.obtener(v);
        int contador = 0;
        for (int j = 0; j < this.cantidadActual; j++) {
            if (this.matrizAdyacencia[i][j]) {
                contador++;
            }
        }
        String[] resultado = new String[contador];
        int pos = 0;
        for (int j = 0; j < this.cantidadActual; j++) {
            if (this.matrizAdyacencia[i][j]) {
                resultado[pos++] = this.etiquetasPorIndice[j];
            }
        }
        return resultado;
    }

    /**
     * Sobrecarga estática acorde a la signatura de la especificación: vecinos(g, v): conjunto de vértices.
     */
    public static String[] vecinos(Grafo g, String v) {
        return g.vecinos(v);
    }

    /**
     * Devuelve la cantidad de vértices actuales del grafo.
     * Precondición: ninguna.
     * Postcondición: retorna un entero entre 0 y n.
     * Complejidad temporal: O(1).
     */
    public int cantidadVertices() {
        return this.cantidadActual;
    }

    /**
     * Sobrecarga estática acorde a la signatura de la especificación: cantidadVertices(g): entero.
     */
    public static int cantidadVertices(Grafo g) {
        return g.cantidadVertices();
    }

    /**
     * Indica si una etiqueta corresponde a un vértice existente en el grafo.
     * Complejidad temporal: O(1) promedio.
     */
    public boolean existeVertice(String etiqueta) {
        return this.indicesPorEtiqueta.existeClave(etiqueta);
    }

    /**
     * Devuelve un arreglo con las etiquetas de todos los vértices actuales del grafo.
     * Permite iterar sobre los vértices desde algoritmos externos respetando el encapsulamiento.
     * Complejidad temporal: O(n).
     */
    public String[] vertices() {
        String[] copia = new String[this.cantidadActual];
        for (int i = 0; i < this.cantidadActual; i++) {
            copia[i] = this.etiquetasPorIndice[i];
        }
        return copia;
    }

    /**
     * Devuelve la capacidad máxima del grafo indicada en crear(n).
     * Complejidad temporal: O(1).
     */
    public int capacidadMaxima() {
        return this.capacidadMaxima;
    }

    private void validarVerticeExistente(String etiqueta) {
        if (etiqueta == null || !this.indicesPorEtiqueta.existeClave(etiqueta)) {
            throw new IllegalArgumentException(
                    "El vértice '" + etiqueta + "' no pertenece al grafo.");
        }
    }
}
