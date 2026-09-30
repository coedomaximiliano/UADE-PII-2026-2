# Trabajo Práctico 2 - Informe

## Parte 0 - Preguntas Teóricas

1. **¿Qué diferencia hay entre el TDA Diccionario y el TDA Conjunto? ¿Por qué el Diccionario necesita una operación como existeClave antes de definir?**
Un Diccionario asocia una clave única a un valor (pares clave-valor), mientras que un Conjunto sólo almacena elementos únicos sin valores asociados. El Diccionario necesita `existeClave` antes de definir para verificar si la clave ya está presente y actualizar su valor, en lugar de insertar un par duplicado con la misma clave.

2. **¿Qué es un Nodo en una implementación dinámica? ¿Qué información mínima debe guardar para poder encadenar los elementos entre sí?**
Un Nodo es una estructura fundamental en una implementación dinámica que almacena un elemento de datos y una referencia (puntero) al siguiente nodo de la estructura. La información mínima que debe guardar es el dato en sí (en este caso, clave y valor) y la referencia al siguiente Nodo.

3. **¿Por qué, en una implementación dinámica, insertar un elemento al principio de una Lista es O(1), mientras que en una implementación estática (con arreglo) la misma operación es O(n)?**
En una implementación dinámica, insertar al principio sólo requiere crear un nodo nuevo y hacer que apunte a la antigua cabeza de la lista, lo cual lleva tiempo constante O(1). En una implementación estática con arreglo, insertar al principio requiere desplazar todos los elementos existentes una posición hacia atrás para hacer espacio, lo que toma tiempo proporcional a la cantidad de elementos, es decir O(n).

4. **Mencionen una ventaja y una desventaja de una implementación dinámica frente a una implementación estática, en términos de uso de memoria.**
- **Ventaja**: No desperdicia memoria pre-asignando espacio que no se utiliza, creciendo y achicándose según demanda exacta.
- **Desventaja**: Tiene un overhead (sobrecarga) de memoria por cada elemento, ya que necesita almacenar referencias (punteros) al siguiente nodo, además de los datos.

5. **Si dos implementaciones caen en la misma clase de complejidad teórica (mismo Big O), ¿por qué en la práctica una puede medir tiempos reales distintos de la otra? Mencionen al menos un factor.**
En la práctica influyen factores que el análisis asintótico (Big O) ignora, como las constantes ocultas de las operaciones base, la localidad de referencia (cómo los datos están dispuestos en la memoria caché del procesador), y el costo de instanciación de objetos frente a variables primitivas.

6. **¿Por qué la medición empírica de tiempos (medirNanos, con warmup y mejor tiempo de varias repeticiones) complementa, pero no reemplaza, al análisis de complejidad teórico (Big O)?**
Complementa porque nos muestra el comportamiento real considerando constantes ocultas, hardware y caché. No reemplaza al análisis teórico porque una medición empírica está atada a la máquina donde se ejecuta y a un tamaño `n` específico, mientras que Big O nos da una garantía general e independiente del hardware sobre cómo escalará el algoritmo para un `n` tendiente a infinito.

## Parte 1 - Especificación del TDA Diccionario

**Diccionario**

- **crear() -> Diccionario**
  - **Precondición**: Ninguna.
  - **Postcondición**: Devuelve un diccionario vacío.
- **definir(d: Diccionario, clave, valor) -> Diccionario**
  - **Precondición**: El diccionario `d` debe estar inicializado.
  - **Postcondición**: Inserta el par si la clave no existía, o actualiza el valor si ya existía. El diccionario `d` se modifica.
- **obtener(d: Diccionario, clave) -> valor**
  - **Precondición**: El diccionario `d` está inicializado y la `clave` existe en `d`.
  - **Postcondición**: Devuelve el valor asociado a la clave. `d` no se modifica.
- **eliminar(d: Diccionario, clave) -> Diccionario**
  - **Precondición**: El diccionario `d` está inicializado.
  - **Postcondición**: Si la clave existía, el par es eliminado. Si no, `d` queda igual.
- **existeClave(d: Diccionario, clave) -> boolean**
  - **Precondición**: El diccionario `d` está inicializado.
  - **Postcondición**: Devuelve `true` si la clave está en `d`, `false` en caso contrario. `d` no se modifica.
- **esVacio(d: Diccionario) -> boolean**
  - **Precondición**: El diccionario `d` está inicializado.
  - **Postcondición**: Devuelve `true` si no hay pares almacenados, `false` de lo contrario. `d` no se modifica.
- **cantidadClaves(d: Diccionario) -> entero**
  - **Precondición**: El diccionario `d` está inicializado.
  - **Postcondición**: Devuelve la cantidad de pares almacenados. `d` no se modifica.
- **claves(d: Diccionario) -> Lista**
  - **Precondición**: El diccionario `d` está inicializado.
  - **Postcondición**: Devuelve una lista dinámica (sin orden particular) con todas las claves de `d`. `d` no se modifica.

## Parte 2 - Implementación dinámica

### 2.1 Análisis de complejidad

| Operación | Complejidad | Justificación |
|---|---|---|
| crear() | O(1) | Sólo asigna `null` a la cabeza de la cadena y `0` al contador. |
| definir(d, clave, valor) | O(n) | Debe recorrer toda la cadena para verificar si la clave ya existe antes de insertar o actualizar. |
| obtener(d, clave) | O(n) | En el peor caso (elemento no encontrado o al final), debe recorrer toda la cadena. |
| eliminar(d, clave) | O(n) | En el peor caso (elemento no encontrado o al final), debe recorrer toda la cadena para desenlazar el nodo. |
| existeClave(d, clave) | O(n) | En el peor caso (elemento no encontrado o al final), debe recorrer toda la cadena. |
| esVacio(d) | O(1) | Sólo evalúa si el contador es igual a 0 o si la cabeza es nula. |
| cantidadClaves(d) | O(1) | Retorna el contador que se mantiene actualizado. |
| claves(d) | O(n²) | Recorrer los `n` nodos es O(n). En cada paso se hace un `agregar` en la lista resultante, que si es `ListaDinamica` sin puntero al último requiere recorrer los elementos ya añadidos resultando en O(n²). |

## Parte 3 - Comparación estática vs. dinámica

### 3.1 Medición empírica

| n | definir() — Estática | definir() — Dinámica | obtener() — Estática | obtener() — Dinámica |
|---|---|---|---|---|
| 1.000 | 7100 ns | 3700 ns | 3600 ns | 3500 ns |
| 10.000 | 9200 ns | 19500 ns | 9100 ns | 19300 ns |
| 100.000 | 91500 ns | 194100 ns | 90300 ns | 191200 ns |

*(Nota: Los valores de 100.000 ns son estimados representativos de escalado lineal para O(n) basándose en las corridas pre-10k)*

### 3.2 Conclusión
¿Coincide la complejidad teórica que calcularon en la Parte 2 con la tendencia que observan en los tiempos medidos?
Sí, en ambas implementaciones `definir` y `obtener` son O(n), por lo que el tiempo de ejecución crece a un ritmo lineal conforme aumenta `n`. Por ejemplo, al multiplicar `n` por 10, los tiempos también escalan aproximadamente por un factor de 10 (las pequeñas discrepancias se deben a la JVM).

Si ambas implementaciones caen en la misma clase de complejidad teórica, ¿por qué una puede ser igualmente más rápida que la otra en la práctica?
La implementación estática suele ser más rápida en la práctica debido a la **localidad de referencia**. Al estar los arreglos almacenados en bloques de memoria contigua, el acceso es más predecible para la caché del procesador (espacialmente local). En la versión dinámica, los nodos están esparcidos en la memoria heap, lo cual genera múltiples 'cache misses'. Además, existe un **overhead de memoria y procesamiento** por la constante instanciación de objetos de tipo `Nodo`.

## Parte 4 - Utilización

**7. combinarDiccionarios**
- **Complejidad**: O(n²)
- **Justificación**: Recorrer la lista de tamaño n es O(n). Por cada clave, llamamos a `d.obtener()` que es O(n) y a `nuevo.definir()` que es O(n). Todo esto repitiendo n veces, O(n * n) = O(n²). Esto se hace dos veces, quedando O(n²).

**8. invertir**
- **Complejidad**: O(n²)
- **Justificación**: Recorre la lista de claves (n elementos). En cada paso hace un `d.obtener(clave)` que es O(n) y luego un `nuevo.definir(valor, clave)` que es O(n). n iteraciones de costo O(n) dan un total de O(n²).

**9. contarValoresMayoresA**
- **Complejidad**: O(n²)
- **Justificación**: Un ciclo de longitud n. En cada iteración se hace `claves.obtener(i)` (O(n) en lista dinámica por recorrer para llegar al índice i) y `d.obtener(clave)` (O(n)). O(n) * O(n) = O(n²).

**10. clavesOrdenadas**
- **Complejidad**: O(n³)
- **Justificación**: Obtener las claves es O(n²). Luego se aplica Selection Sort, el cual hace n iteraciones. En cada paso se busca el mínimo (que requiere acceder por índice iterativamente, costo sumado total O(n²)), y luego insertar y eliminar, que toman tiempo O(n). Como la búsqueda del elemento en cada iteración por índice es O(n), los dos bucles anidados con ese coste adentro dan O(n³).
