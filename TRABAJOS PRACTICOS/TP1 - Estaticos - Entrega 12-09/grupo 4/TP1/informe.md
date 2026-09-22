# Informe - Trabajo Práctico 1

## Parte 1 - TDA Pila

### 1.1 Especificación
**Dominio:**
Una Pila es una colección lineal de elementos (enteros) donde las inserciones y eliminaciones se realizan por el mismo extremo, llamado `tope` (comportamiento LIFO: Last In, First Out).

**Operaciones:**
- `crear(Pila)`: Inicializa la pila vacía.
  * *Precondición:* Ninguna.
  * *Postcondición:* La pila queda creada y sin elementos.
- `apilar(Pila, x: entero)`: Agrega el elemento `x` al tope de la pila.
  * *Precondición:* La pila está inicializada y no está llena (en implementación estática).
  * *Postcondición:* La pila contiene un elemento más, y `x` pasa a ser el nuevo tope.
- `desapilar(Pila)`: Elimina el elemento en el tope de la pila.
  * *Precondición:* La pila no está vacía.
  * *Postcondición:* La pila tiene un elemento menos, el elemento ingresado inmediatamente antes que el tope eliminado pasa a ser el nuevo tope.
- `tope(Pila) -> entero`: Devuelve el valor del elemento en el tope.
  * *Precondición:* La pila no está vacía.
  * *Postcondición:* Devuelve el valor del tope. La pila no se modifica.
- `esVacia(Pila) -> boolean`: Devuelve verdadero si la pila no tiene elementos, falso en caso contrario.
  * *Precondición:* La pila está inicializada.
  * *Postcondición:* La pila no se modifica.

### 1.2 Complejidad de Implementaciones y Justificación
| Operación | Variante A (Tope = último ocupado) | Variante B (Tope = índice 0) |
|---|---|---|
| `apilar` | $O(1)$ | $O(N)$ |
| `desapilar` | $O(1)$ | $O(N)$ |
| `tope` | $O(1)$ | $O(1)$ |
| `esVacia` | $O(1)$ | $O(1)$ |

**Justificación y elección:**
En la **Variante A**, se apila y desapila en el último espacio ocupado (índice dictado por una variable de control), por lo que solo basta con actualizar el índice y asignar/leer el valor en el arreglo. Esto es tiempo constante $O(1)$.
En la **Variante B**, al mantener el tope siempre fijo en el índice 0, cada vez que se apila o desapila es necesario desplazar todos los $N$ elementos del arreglo una posición (hacia la derecha para abrir lugar, o hacia la izquierda para tapar el hueco), lo que requiere un ciclo de $N$ pasos, resultando en $O(N)$.
**¿Cuál conviene usar?**
Siempre conviene usar la **Variante A**, ya que todas sus operaciones críticas toman $O(1)$ en el peor de los casos, a diferencia de la Variante B que requiere trabajo lineal $O(N)$. No depende de ningún contexto; la Variante A es estrictamente superior en términos de rendimiento y realiza menos modificaciones en memoria.

### 1.3 Complejidad de los métodos de Utilización (Pila)
- **`pasarPila`**: $O(N)$ donde $N$ es la cantidad de elementos. Cada elemento se desapila y apila en un auxiliar, y luego se hace lo mismo hacia la pila resultante.
- **`copiarPila`**: $O(N)$. Usa la misma lógica secuencial que `pasarPila`, con costo constante por elemento.
- **`invertirPila` (recursiva)**: $O(N)$. Hay $N$ llamadas recursivas. En cada nivel se realiza trabajo $O(1)$ (apilar/desapilar).
- **`masDeUnaOcurrencia`**: $O(N^2)$. Por cada elemento extraído (ciclo de $N$ iteraciones), se recorre el resto de la pila (hasta $N-1$ iteraciones) buscando coincidencias y utilizando una pila de búsqueda auxiliar.
- **`eliminarImpares`**: $O(N)$. Se recorre linealmente la pila hacia un auxiliar y viceversa, tomando $O(1)$ por elemento filtrado.

---

## Parte 2 - TDA Cola

### 2.1 Especificación
**Dominio:**
Una Cola es una colección lineal de elementos (enteros) donde las inserciones se realizan por un extremo (`fin`) y las eliminaciones por el extremo opuesto (`frente`), comportándose como FIFO (First In, First Out).

**Operaciones:**
- `crear(Cola)`: Inicializa la cola vacía.
  * *Precondición:* Ninguna.
  * *Postcondición:* La cola queda inicializada y vacía.
- `encolar(Cola, x: entero)`: Agrega `x` al fin de la cola.
  * *Precondición:* La cola está inicializada y hay espacio disponible.
  * *Postcondición:* La cola contiene un elemento más, ingresado en la posición de fin.
- `desencolar(Cola)`: Elimina el elemento en el frente de la cola.
  * *Precondición:* La cola no está vacía.
  * *Postcondición:* La cola tiene un elemento menos. El elemento que le seguía pasa a ser el nuevo frente.
- `frente(Cola) -> entero`: Devuelve el valor del frente de la cola.
  * *Precondición:* La cola no está vacía.
  * *Postcondición:* Devuelve el frente. La cola no se modifica.
- `esVacia(Cola) -> boolean`: Devuelve verdadero si la cola no tiene elementos.
  * *Precondición:* La cola está inicializada.
  * *Postcondición:* La cola no se modifica.

### 2.2 Complejidad de Implementaciones y Justificación
| Operación | Variante A (Lineal Simple) | Variante B (Circular) |
|---|---|---|
| `encolar` | $O(1)$ | $O(1)$ |
| `desencolar` | $O(1)$ | $O(1)$ |

**Justificación y elección:**
En ambas variantes, el costo temporal para `encolar` y `desencolar` es $O(1)$, ya que solo involucran incrementar índices e insertar/leer del arreglo directamente.
**¿Cuál conviene usar?**
En la práctica conviene utilizar la **Variante B (Cola Circular)**. La Variante A (lineal simple) tiene un fallo estructural grave para uso estático: los índices `frente` y `fin` avanzan perpetuamente. Los espacios al principio del arreglo que quedan liberados tras desencolar jamás se reutilizan, lo que eventualmente provocará un desbordamiento del arreglo (falso "lleno") aunque la cola posea pocos elementos reales en ese momento. La Variante B, mediante aritmética modular, reutiliza los espacios circularmente y garantiza un uso correcto de la memoria.

### 2.3 Complejidad de los métodos de Utilización (Cola)
- **`pasarCola`**: $O(N)$. Recorre la cola de origen en un solo ciclo, desencolando y encolando en $O(1)$.
- **`invertirColaConPila`**: $O(N)$. Se vacía la cola a una pila ($N$ operaciones) y luego de la pila a la nueva cola ($N$ operaciones).
- **`invertirColaSinPila` (recursiva)**: $O(N)$. $N$ llamadas recursivas, desencolando antes de la llamada y encolando después, con costo $O(1)$ en cada marco.
- **`finalCoincide`**: $O(N_1 + N_2)$ donde $N_1$ y $N_2$ son los tamaños de las colas. La estrategia de vaciar hacia auxiliares para contar sus elementos y volver a llenarlas es estrictamente lineal con respecto a sus longitudes totales, ya que no se permiten contadores internos de tamaño en la interfaz.

---

## Parte 3 - TDA Cola con Prioridad

### 3.1 Especificación
**Dominio:**
Una Cola con Prioridad es una colección de elementos asociados a una prioridad (entero). La operación de extracción remueve siempre el elemento de mayor prioridad. Ante elementos de igual prioridad, se desempata manteniendo el orden de llegada (FIFO).

**Operaciones:**
- `crear(CP)`: Inicializa la cola de prioridad vacía.
  * *Precondición:* Ninguna.
  * *Postcondición:* La estructura queda inicializada y vacía.
- `insertar(CP, x: entero, prioridad: entero)`: Inserta el elemento `x` con la prioridad dada.
  * *Precondición:* La estructura no está llena.
  * *Postcondición:* El elemento ingresa a la cola de prioridad de forma que pueda extraerse según el criterio establecido.
- `extraerMax(CP)`: Elimina el elemento de mayor prioridad. A igual prioridad, remueve el más antiguo.
  * *Precondición:* La cola no está vacía.
  * *Postcondición:* Se elimina dicho elemento de la estructura.
- `verMax(CP) -> entero`: Devuelve el valor del elemento de mayor prioridad.
  * *Precondición:* La cola no está vacía.
  * *Postcondición:* Devuelve el valor del elemento máximo. La estructura no se modifica.
- *(Extendida)* `prioridadMax(CP) -> entero`: Devuelve la prioridad del elemento de mayor prioridad (Añadido por necesidad funcional para resolver problemas prácticos).
  * *Precondición:* La cola no está vacía.
  * *Postcondición:* Devuelve la prioridad. La estructura no se modifica.
- `esVacia(CP) -> boolean`: Devuelve verdadero si no hay elementos.
  * *Precondición:* La cola está inicializada.
  * *Postcondición:* La cola no se modifica.

### 3.2 Complejidad de Implementaciones y Justificación
| Operación | Variante A (Desordenada) | Variante B (Ordenada) |
|---|---|---|
| `insertar` | $O(1)$ | $O(N)$ |
| `extraerMax` | $O(N)$ | $O(1)$ |
| `verMax` | $O(N)$ | $O(1)$ |

**Justificación:**
En la Variante A, `insertar` es $O(1)$ porque simplemente apendiza al final del arreglo. `verMax` y `extraerMax` toman $O(N)$ porque requieren recorrer todo el arreglo para ubicar el máximo (y en la extracción se realiza un corrimiento en $O(N)$ para rellenar el hueco preservando el orden relativo para FIFO).
En la Variante B, `insertar` toma $O(N)$ ya que, para mantener el arreglo ordenado según prioridad (y asegurar el criterio FIFO), es necesario encontrar su posición adecuada y desplazar los elementos preexistentes. A cambio, `extraerMax` y `verMax` toman $O(1)$ al estar el máximo posicionado en el extremo derecho del arreglo.
**¿Existe una mejor en todos los casos?**
No, la elección **depende estrictamente del contexto de uso**:
- Si la aplicación realiza inserciones de forma intensiva y pocas extracciones (o se generan de a bloques), conviene la **Variante A** para agilizar ingresos.
- Si las consultas de máximos (`verMax`/`extraerMax`) son mucho más frecuentes que las inserciones, o se requiere acceso en tiempo real estricto al mayor en cualquier instante, es preferible la **Variante B**.

### 3.3 Complejidad de los métodos de Utilización (Cola Prioridad)
*La complejidad depende de si se usa la Variante A o B. De forma genérica en el peor de los casos, si el TDA tiene implementación de arreglos (como las descritas), toda rutina que encole/desencole masivamente tendrá costo cuadrático.*

- **`combinar`**: $O(N^2 + M^2)$ en el peor caso. Se procesan los $N$ elementos de `cp1` y $M$ de `cp2`. Si la implementación es la Variante B, la extracción es $O(1)$ pero cada inserción toma $O(N)$; si es Variante A, cada extracción es $O(N)$.
- **`invertirColaConColaPrioridad`**: $O(N^2)$ en el peor caso, asumiendo operaciones lineales de `insertar` o `extraerMax` dependiendo de la variante de la CP en uso.
- **`sumarValoresPrioridadPar`**: $O(N^2)$ en el peor caso. Extraer y trasladar los elementos hacia una CP auxiliar y regresarlos involucra $2N$ operaciones sobre el TDA. Independientemente de la variante, el traslado costará en tiempo cuadrático por los desplazamientos internos.
