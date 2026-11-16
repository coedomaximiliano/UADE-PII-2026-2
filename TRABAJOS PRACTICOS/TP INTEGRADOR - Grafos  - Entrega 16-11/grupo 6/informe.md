## Parte 1 — Especificación del TDA Grafo

**Dominio:** grafo no dirigido y no pesado (las aristas no tienen sentido ni peso). Cada vértice se identifica con una etiqueta única. El grafo tiene una capacidad máxima de n vértices, fijada al crearlo.

### crear(n: entero) -> Grafo
- **Pre:** n > 0 (si no, devolver error)
- **Post:** devuelve grafo vacío con capacidad para n vértices.

### agregarVertice(g: Grafo, etiqueta) -> Grafo
- **Pre:** debe existir un grafo al cual agregar vértices, no debe superar la capacidad del grafo (devolver error), la etiqueta no puede ser nula (devolver error), la etiqueta no debe existir en el grafo (devolver error)
- **Post:** devuelve el grafo con un nuevo vértice identificado por etiqueta; cantidadVertices(g) aumenta en 1.

### agregarArista(g: Grafo, v1, v2) -> Grafo
- **Pre:** debe existir un grafo al cual agregar arista, no debe pre-existir una arista entre v1-v2, v1 y v2 deben pertenecer al mismo grafo, v1 debe ser distinto de v2. Si no se cumple alguna, devolver error.
- **Post:** devuelve el grafo con una arista entre los vértices v1 y v2: existeArista(g, v1, v2) = existeArista(g, v2, v1) = true.

### existeArista(g: Grafo, v1, v2) -> boolean
- **Pre:** existe el grafo, v1 y v2 pertenecen a él (devolver error).
- **Post:** devuelve true si existe una arista entre v1 y v2, caso contrario false. Si v1 es igual a v2 devuelve false, porque no se permiten aristas de un vértice consigo mismo.

### vecinos(g: Grafo, v) -> conjunto (lista de etiquetas)
- **Pre:** existe el grafo, v pertenece al grafo (si no, devolver error)
- **Post:** devuelve una lista con las etiquetas de los vértices adyacentes a v, sin repetidos. Si v no tiene aristas, la lista es vacía.

### cantidadVertices(g: Grafo) -> entero
- **Pre:** existe el grafo
- **Post:** devuelve la cantidad de vértices actuales de g.

**Operaciones auxiliares para los recorridos:**

### indiceDe(g: Grafo, etiqueta) -> entero
- **Pre:** existe el grafo, la etiqueta pertenece al grafo (si no, devolver error).
- **Post:** devuelve el índice del vértice identificado por etiqueta. No modifica el grafo.

### etiquetaDe(g: Grafo, indice: entero) -> etiqueta
- **Pre:** existe el grafo, 0 <= indice < cantidadVertices(g) (si no, devolver error).
- **Post:** devuelve la etiqueta del vértice que tiene ese índice. No modifica el grafo.

### vecinosPorIndice(g: Grafo, indice: entero) -> arreglo de enteros
- **Pre:** existe el grafo, 0 <= indice < cantidadVertices(g) (si no, devolver error).
- **Post:** devuelve un arreglo nuevo con los índices de los vecinos, sin repetidos y en orden creciente. Si no hay vecinos devuelve un arreglo vacío. No modifica el grafo.

## Parte 2 — Implementación

El grafo se implementa con una matriz de adyacencia `boolean[n][n]`, donde `n` es la capacidad indicada en `crear(n)`. La posición `[i][j]` vale `true` si hay una arista entre el vértice de índice `i` y el de índice `j`. Como el grafo es no dirigido, `agregarArista` escribe `[i][j]` y `[j][i]`, así que la matriz siempre queda simétrica.

Para traducir etiquetas a índices se usa el TDA Diccionario (implementación dinámica): clave = etiqueta, valor = índice en la matriz. Para el camino inverso (índice → etiqueta, necesario para mostrar resultados) el grafo guarda además un arreglo `etiquetas[]`, donde `etiquetas[i]` es la etiqueta del vértice de índice `i`.

### 2.1 Análisis de complejidad espacial

La matriz tiene `n × n` celdas, así que ocupa **O(n²)** espacio. A eso se suman el arreglo `etiquetas[]` (n posiciones) y el Diccionario (un nodo por vértice), que son O(n) cada uno. El total queda en **O(n²)**, dominado por la matriz.

Ese espacio no depende de la cantidad de aristas `m`: se reserva completo en `crear(n)`. Un grafo con n vértices y ninguna arista ocupa lo mismo que uno donde todos están conectados con todos. Tampoco depende de cuántos vértices se agregaron realmente: la matriz se dimensiona por la capacidad, no por `cantidadVertices`.

**Grafo disperso.** En un grafo no dirigido sin aristas de un vértice consigo mismo hay como máximo `n(n−1)/2` aristas, y cada arista ocupa dos celdas en `true` (`[i][j]` y `[j][i]`). Si el grafo tiene muchos vértices pero pocas aristas, casi toda la matriz queda en `false`: de las `n²` celdas solo `2m` tienen información. Por ejemplo, con 1.000 vértices y 1.000 aristas la matriz tiene 1.000.000 de celdas y solo 2.000 en `true` (el 0,2 %). El resto es espacio reservado que no aporta nada. Además, como la matriz es simétrica, las `(n² − n)/2` celdas de un lado de la diagonal repiten la información del otro lado, y las `n` celdas de la diagonal quedan siempre en `false`.

### 2.2 Matriz vs. listas de adyacencia

**Listas de adyacencia.** En lugar de una matriz, cada vértice tiene su propia lista (por ejemplo, un TDA Lista o un Diccionario) con los vértices adyacentes. En un grafo no dirigido, la arista v1–v2 aparece dos veces: v2 en la lista de v1 y v1 en la lista de v2. Un vértice sin aristas tiene una lista vacía.

Sea `n` la cantidad de vértices y `m` la cantidad de aristas:

| Aspecto | Matriz de adyacencia | Listas de adyacencia |
|---|---|---|
| Espacio | O(n²), siempre | O(n + m): una lista por vértice y 2m entradas en total |
| `existeArista(g, v1, v2)` | O(1): se mira la celda `[i][j]` | O(grado(v1)): hay que recorrer la lista de v1 buscando v2 |
| `vecinos(g, v)` | O(n): hay que recorrer la fila completa de v, aunque tenga pocos vecinos | O(grado(v)): la lista ya tiene exactamente los vecinos |

Estos costos suponen que ya se conocen los índices de los vértices. En esta implementación, traducir cada etiqueta a su índice con el Diccionario dinámico es una búsqueda lineal, O(n). Por eso `existeArista` y `vecinos` terminan siendo O(n) en la práctica. Con listas de adyacencia también habría que encontrar primero el vértice por su etiqueta, con un costo similar.

**¿Cuándo conviene cada una?**

- **Matriz de adyacencia:** conviene en grafos densos, donde `m` es del orden de `n²` (cerca del máximo `n(n−1)/2`) y casi todas las celdas se usan. También cuando la operación más frecuente es preguntar si existe una arista entre dos vértices, y cuando la cantidad máxima de vértices se conoce de antemano y es chica, de modo que reservar `n²` celdas no es un problema.
- **Listas de adyacencia:** conviene en grafos dispersos, donde `m` es mucho menor que `n²`, porque solo se guarda lo que existe. También cuando se recorren los vecinos de cada vértice muchas veces, como en BFS y DFS: con listas, recorrer todos los vecinos de todos los vértices cuesta O(n + m); con matriz cuesta O(n²), porque cada fila se recorre completa. Además, las listas pueden crecer sin una capacidad fija.

## Parte 3 — Recorridos

### Grafo de ejemplo

Se construye en `Main` un grafo no dirigido y no pesado con los vértices A, B, C, D, E, F y G, agregados en ese orden. Sus aristas son A–B, A–C, B–D, B–E, C–E y D–F. El origen elegido para los recorridos es A.

El grafo tiene ramificaciones y el ciclo A–B–E–C–A, por lo que hace falta registrar los vértices visitados para no repetirlos. G está aislado: no tiene ninguna arista.

### 3.1 BFS — Búsqueda en anchura

El método `bfs(grafo, origen)` de `RecorridosGrafo` devuelve una `Lista` propia con las etiquetas en orden de visita. El origen debe pertenecer al grafo; si no pertenece, `indiceDe` lanza una excepción. El recorrido no modifica el grafo.

Se usa `ColaCircular` a través de la interfaz `Cola` para guardar los índices de los vértices pendientes. Primero se marca y encola el origen. Mientras la cola no esté vacía, se desencola un vértice, se agrega su etiqueta al resultado y se encolan sus vecinos todavía no visitados. Se marca cada vecino al encolarlo, para evitar que dos vértices distintos lo dejen pendiente más de una vez. Por eso una cola con capacidad para la cantidad de vértices es suficiente, incluso si hay ciclos.

El arreglo `visitados` guarda una marca por índice. Para consultar los vecinos y recuperar las etiquetas se usan los métodos públicos del Grafo: `vecinosPorIndice` y `etiquetaDe`. No se accede a la matriz ni al Diccionario desde el recorrido y no se usan colecciones de la biblioteca estándar.

FIFO hace que se procesen primero los vértices que están más cerca del origen: A; luego B y C; luego D y E; finalmente F. Los vecinos se consultan por índice creciente, que coincide con el orden en que se agregaron los vértices. G no aparece porque no se puede llegar a él desde A.

Salida obtenida al ejecutar `Main`:

```text
=== 3.1 BFS desde A ===
Orden de visita = [ A B C D E F ]
```

**Complejidad temporal:** O(n²) en el peor caso, donde n es la cantidad de vértices actuales. Cada vértice alcanzable se encola y desencola una sola vez, con costo O(1) por operación. Para cada uno, `vecinosPorIndice` recorre dos veces las n posiciones de su fila de la matriz: O(n). Si se alcanzan todos los vértices, se realizan n consultas de ese costo. La búsqueda inicial del origen con el Diccionario dinámico cuesta O(n), y no cambia el orden total. Se trabaja con los arreglos de índices devueltos por el Grafo para no repetir búsquedas de etiquetas por cada vecino.

**Espacio adicional:** O(n), por las marcas, la cola, el arreglo de vecinos de la consulta actual y la lista devuelta. El espacio de la matriz del grafo ya existía antes de iniciar el recorrido.

### 3.2 DFS — Búsqueda en profundidad (recursiva)

El método `dfs(grafo, origen)` de `RecorridosGrafo` devuelve una `Lista` propia con las etiquetas en orden de visita. Se usa el mismo grafo de ejemplo y el mismo origen A que en BFS. El origen debe pertenecer al grafo; si no pertenece, `indiceDe` lanza una excepción. El recorrido no modifica el grafo.

Se resuelve con un auxiliar recursivo que recibe el índice actual, el arreglo `visitados` y la lista con el orden. Primero marca el vértice y agrega su etiqueta al resultado. Después consulta sus vecinos y llama recursivamente al auxiliar por cada uno que todavía no esté visitado. Al terminar esa llamada, sigue con el próximo vecino. Si todos sus vecinos ya están visitados, la llamada termina y vuelve a la anterior. Las marcas se comparten entre todas las llamadas para no repetir vértices ni seguir indefinidamente por un ciclo.

Los vecinos se consultan por índice creciente, igual que en BFS. Desde A se visita B, después D y después F. Como F no tiene vecinos sin visitar, se vuelve hasta B y se sigue por E y C. G no aparece porque está aislado y no se puede llegar a él desde A. Se usan los métodos públicos del Grafo, sin acceder a su matriz ni al Diccionario desde afuera.

Salida obtenida al ejecutar `Main`:

```text
=== 3.2 DFS recursivo desde A ===
Orden de visita = [ A B D F E C ]
```

**Comparación con BFS:** BFS devuelve A, B, C, D, E, F porque avanza por niveles. DFS devuelve A, B, D, F, E, C porque sigue por un vecino hasta que no puede avanzar más y recién entonces vuelve para continuar por otro. Los dos visitan los mismos vértices alcanzables desde A, sin repetidos, pero en distinto orden.

**Complejidad temporal:** O(n²) en el peor caso, donde n es la cantidad de vértices actuales. Cada vértice alcanzable se procesa una sola vez. La consulta `vecinosPorIndice` cuesta O(n), porque recorre dos veces la fila de la matriz. Si se visitan todos los vértices, se hacen n consultas de ese costo. Marcar y agregar cada etiqueta a la lista cuesta O(1). La búsqueda inicial del origen cuesta O(n) y no cambia el orden total.

**Espacio adicional:** las marcas, el resultado y las llamadas recursivas ocupan O(n). Además, cada llamada pendiente conserva su arreglo de vecinos mientras recorre los siguientes. En un grafo denso puede haber O(n) llamadas pendientes con arreglos de O(n) elementos cada una, por lo que esta implementación puede usar O(n²) de espacio adicional en el peor caso.

### 3.3 DFS iterativo

Se usa la misma `PilaVarianteA` que ya está en el proyecto, a través de la interfaz `Pila`. El método `dfsIterativo(grafo, origen)` devuelve el orden en una `Lista`, como los otros recorridos. El origen debe pertenecer al grafo y no se modifica el grafo.

Primero se marca y apila el origen. Después se mira el tope y se busca su primer vecino sin visitar. Si hay uno, se marca, se apila y se agrega al resultado. Si no queda ninguno, se desapila ese vértice y se vuelve al anterior. La pila guarda el camino que se está recorriendo, en lugar de usar llamadas recursivas. Cada vértice se apila una sola vez, por eso alcanza con una capacidad de n elementos.

Salida desde A sobre el mismo ejemplo:

```text
=== 3.3 DFS iterativo desde A ===
Orden de visita = [ A B D F E C ]
```

**Comparación con el recursivo:** en esta versión da exactamente el mismo orden: A, B, D, F, E, C. Se elige el primer vecino sin visitar por índice creciente, igual que en el recursivo, y se termina esa rama antes de seguir con otra. No se apilan todos los vecinos juntos. G tampoco aparece porque está aislado.

**Complejidad temporal:** O(n²) en el peor caso. Cada vuelta del `while` apila un vértice nuevo o desapila uno que ya terminó, así que hay como máximo 2n - 1 vueltas. En cada vuelta se consultan los vecinos del tope, con costo O(n), y se busca uno sin visitar. Aunque se vuelva a consultar un vértice al regresar de una rama, el total sigue siendo O(n²).

**Espacio adicional:** O(n), por la pila, las marcas, la lista de resultado y el arreglo de vecinos de la vuelta actual. No hay llamadas recursivas.

## Parte 4 — Utilización

Los tres métodos están en `RecorridosGrafo` y se prueban en `Main` sobre el mismo grafo de ejemplo de la Parte 3. Los tres recorren el grafo con BFS, usando `ColaCircular` a través de la interfaz `Cola`, y solo usan métodos públicos del Grafo. Ninguno modifica el grafo.

### 4.1 estanConectados(g, v1, v2)

Se hace un BFS desde v1. Si en algún momento se desencola v2, devuelve true; si la cola se vacía sin encontrarlo, devuelve false.

Se eligió BFS porque recorre el grafo por niveles; sin embargo, como el objetivo es solo verificar si existe algún camino entre dos vértices, DFS y BFS son indistintos: ambos recorren el mismo conjunto de vértices alcanzables desde el origen y, si v2 pertenece a esa región, devuelve verdadero; si no, devuelve falso.

Salida obtenida al ejecutar `Main`:

```text
=== 4.1 estanConectados ===
estanConectados(A, F) = true
estanConectados(A, G) = false
```

A y F están conectados por el camino A–B–D–F. G está aislado, así que ningún recorrido desde A llega a él.

**Complejidad temporal:** O(n²) en el peor caso, que es cuando v2 no es alcanzable o se encuentra al final. Cada vértice se encola y desencola a lo sumo una vez, y por cada vértice desencolado `vecinosPorIndice` recorre su fila de la matriz, con costo O(n). Las dos búsquedas iniciales en el Diccionario cuestan O(n) y no cambian el orden total. Con listas de adyacencia, el mismo recorrido costaría O(n + m).

### 4.2 componentesConexas(g)

Se recorren los vértices por índice. Cada vez que aparece uno todavía no visitado, empieza una componente nueva: se suma 1 y se hace un BFS desde ese vértice, que marca como visitados a todos los de su componente. Las marcas se comparten entre todos los BFS, así que cada vértice se cuenta en una sola componente.

En el ejemplo, el BFS que arranca en A marca A, B, C, D, E y F. El siguiente vértice sin visitar es G, que forma una segunda componente él solo.

Salida obtenida al ejecutar `Main`:

```text
=== 4.2 componentesConexas ===
componentesConexas = 2
```

**Complejidad temporal:** O(n²). Entre todos los BFS, cada vértice se encola y desencola una sola vez, y por cada uno se consulta `vecinosPorIndice` con costo O(n). Además, cada componente crea una cola nueva de capacidad n, que cuesta O(n); como hay a lo sumo n componentes, eso también queda en O(n²). Con listas de adyacencia, el recorrido completo costaría O(n + m).

### 4.3 caminoMasCorto(g, origen, destino)

Se hace un BFS desde el origen con un arreglo `distancia`: el origen tiene distancia 0 y cada vecino que se encola recibe la distancia del vértice actual más 1. Cuando se desencola el destino, se devuelve su distancia. Si la cola se vacía sin encontrarlo, devuelve -1 para indicar que no existe camino.

Se utiliza BFS porque el grafo es no pesado, es decir, todas las aristas tienen igual costo. BFS recorre los vértices en orden creciente de distancia desde el origen. Por lo tanto, cuando se desencola el destino, ya se obtuvo la ruta con menor número de aristas. Si no llega a encontrarse, entonces no existe camino entre ambos vértices.

Salida obtenida al ejecutar `Main`:

```text
=== 4.3 caminoMasCorto ===
caminoMasCorto(A, F) = 3
caminoMasCorto(A, G) = -1
```

El camino más corto de A a F es A–B–D–F, con 3 aristas. Para G devuelve -1 porque no hay camino.

**Complejidad temporal:** O(n²) en el peor caso, por el mismo motivo que en 4.1: cada vértice se encola a lo sumo una vez y por cada uno se recorre su fila de la matriz. Actualizar la distancia de cada vecino cuesta O(1). Con listas de adyacencia costaría O(n + m).

