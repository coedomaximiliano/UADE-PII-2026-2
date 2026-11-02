# TDA Grafo

**Dominio:** grafo no dirigido y no pesado (las aristas no tienen sentido ni peso). Cada vértice se identifica con una etiqueta única. El grafo tiene una capacidad máxima de n vértices, fijada al crearlo.

## crear(n) -> Grafo
- **Pre:** n > 0 (si no, devolver error)
- **Post:** devuelve grafo vacío con capacidad para n vértices.

## agregarVertice(g: Grafo, etiqueta) -> Grafo
- **Pre:** debe existir un grafo al cual agregar vértices, no debe superar la capacidad del grafo (devolver error), la etiqueta no puede ser nula (devolver error), la etiqueta no debe existir en el grafo (devolver error)
- **Post:** devuelve el grafo con un nuevo vértice identificado por etiqueta; cantidadVertices(g) aumenta en 1.

## agregarArista(g: Grafo, v1, v2) -> Grafo
- **Pre:** debe existir un grafo al cual agregar arista, no debe pre-existir una arista entre v1-v2, v1 y v2 deben pertenecer al mismo grafo, v1 debe ser distinto de v2. Si no se cumple alguna, devolver error.
- **Post:** devuelve el grafo con una arista entre los vértices v1 y v2; ambos sentidos son válidos.

## existeArista(g: Grafo, v1, v2) -> boolean
- **Pre:** existe el grafo, v1 y v2 pertenecen a él (devolver error).
- **Post:** devuelve true si existe una arista entre v1 y v2, caso contrario false. Si v1 es igual a v2 devuelve false, porque no se permiten aristas de un vértice consigo mismo.

## vecinos(g: Grafo, v) -> conjunto (lista de etiquetas)
- **Pre:** existe el grafo, v pertenece al grafo (si no, devolver error)
- **Post:** devuelve una lista con las etiquetas de los vértices adyacentes a v, sin repetidos. Si v no tiene aristas, la lista es vacía.

## cantidadVertices(g: Grafo) -> entero
- **Pre:** existe el grafo
- **Post:** devuelve la cantidad de vértices actuales de g.

## Operaciones auxiliares para los recorridos

## indiceDe(g: Grafo, etiqueta) -> entero
- **Pre:** existe el grafo, la etiqueta pertenece al grafo (si no, devolver error).
- **Post:** devuelve el índice del vértice identificado por etiqueta. No modifica el grafo.

## etiquetaDe(g: Grafo, indice: entero) -> etiqueta
- **Pre:** existe el grafo, 0 <= indice < cantidadVertices(g) (si no, devolver error).
- **Post:** devuelve la etiqueta del vértice que tiene ese índice. No modifica el grafo.

## vecinosPorIndice(g: Grafo, indice: entero) -> arreglo de enteros
- **Pre:** existe el grafo, 0 <= indice < cantidadVertices(g) (si no, devolver error).
- **Post:** devuelve un arreglo nuevo con los índices de los vecinos, sin repetidos y en orden creciente. Si no hay vecinos devuelve un arreglo vacío. No modifica el grafo.
