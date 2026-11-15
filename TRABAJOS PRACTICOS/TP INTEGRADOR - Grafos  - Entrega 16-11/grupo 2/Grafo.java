public interface Grafo {
    void agregarVertice(String etiqueta);

    void agregarArista(String v1, String v2);

    boolean existeArista(String v1, String v2);

    Lista vecinos(String v);

    int cantidadVertices();

    boolean existeVertice(String v);

    Lista vertices();

    Lista bfs(String origen);

    Lista dfs(String origen);

    Lista dfsIterativo(String origen);

    Lista dfsIterativoInvertido(String origen);

    Diccionario distanciasBFS(String origen);
}
