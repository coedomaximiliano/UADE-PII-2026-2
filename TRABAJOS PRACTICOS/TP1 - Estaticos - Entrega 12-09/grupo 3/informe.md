# TP1 — TDAs: Conceptos Básicos

**Algoritmos y Estructuras de Datos II — Informe**

**Alumnos:** Pedro Stella — Bautista Chavarri

Implementación estática (arreglo de tamaño fijo) de los TDA Pila, Cola y Cola con Prioridad, con dos variantes cada uno, y los métodos de utilización pedidos en cada parte.

## Cómo compilar y ejecutar

Requiere JDK 21. Todas las clases están en el paquete por defecto y no usan bibliotecas. Desde la carpeta que contiene el TP:

```
cd "TP1 - Estaticos"
javac *.java
java Main
```

Una clase por TDA y por variante, más una clase con los métodos de utilización de cada parte (cada archivo se llama como su clase: `Pila.java`, `PilaTopeFinal.java`, …):

| Parte | Interfaz (contrato del TDA) | Variante A | Variante B | Métodos de utilización |
|---|---|---|---|---|
| 1 – Pila | `Pila` | `PilaTopeFinal` (tope en la última posición ocupada) | `PilaTopeInicio` (tope en la posición 0) | `MetodosPila` |
| 2 – Cola | `Cola` | `ColaEstatica` (lineal, no circular) | `ColaCircular` | `MetodosCola` |
| 3 – Cola con prioridad | `ColaPrioridad` | `ColaPrioridadDesordenada` | `ColaPrioridadOrdenada` | `MetodosColaPrioridad` |

`Main` es el programa de prueba de las implementaciones y de los métodos. Los elementos son `int`, como en el código de las diapositivas. En el código, cada operación del TDA está precedida por su especificación en el formato de clase (`// apilar(p: Pila, x: elemento) -> Pila`, `// pre: …`, `// post: …`), así que se puede leer junto a las secciones 1.1, 2.1 y 3.1.

Las citas (Clase N, diap. M) remiten al material de clase. En el anexo final están la carpeta de cada presentación, de dónde sale cada parte del TP y lo que no está en las diapositivas, con el motivo.

## Parte 1 — TDA Pila

### 1.1 Especificación

```text
TDA Pila

Operaciones:
  crear() -> Pila
    post: devuelve una pila vacia
  apilar(p: Pila, x: elemento) -> Pila
    pre:  p no esta llena
    post: x queda en el tope de p
  desapilar(p: Pila) -> Pila
    pre:  p no esta vacia
    post: se elimina el elemento del tope de p
  tope(p: Pila) -> elemento
    pre:  p no esta vacia
    post: devuelve el elemento del tope sin eliminarlo
  esVacia(p: Pila) -> boolean
    post: devuelve true si p no tiene elementos
  esLlena(p: Pila) -> boolean
    post: devuelve true si p alcanzo su capacidad maxima

Dominio: Pila = secuencia finita y ordenada de elementos, donde el
         ultimo agregado es accesible primero (LIFO).
```

- Es la especificación de la Clase 2 (diap. 8) y la Clase 6 (diap. 16). `esLlena` es la operación auxiliar de la implementación estática: "no forma parte de la especificación original" (Clase 6, diap. 13), pero hace falta porque el arreglo tiene tamaño fijo, y es la precondición de `apilar` ("apilar necesita 'no llena'", Clase 2 diap. 12).
- En Java (Clase 1, diap. 21 y 27), `crear()` es el constructor `(int capacidad)` y las demás operaciones son métodos de instancia de `p`. Las que en la especificación devuelven `Pila` modifican `p` en el lugar: `apilar` es `void` y `desapilar` además devuelve el elemento que elimina, como en el código de la Clase 2 (diap. 12). Si no se cumple la precondición se lanza `RuntimeException("Pila llena")` o `RuntimeException("Pila vacia")`.

### 1.2 Implementación y complejidad

`PilaTopeFinal` guarda `datos`, `tope` (índice del próximo lugar libre, que coincide con la cantidad de elementos) y `capacidad`. `PilaTopeInicio` guarda `datos`, `cantidad` y `capacidad`.

| Operación | Tope al final (A) | Tope al inicio (B) | Por qué |
|---|---|---|---|
| apilar | O(1) | O(n) | A: `datos[tope] = x; tope++`. B: antes de escribir en `datos[0]` corre los n elementos un lugar a la derecha. |
| desapilar | O(1) | O(n) | A: `tope--; return datos[tope]`. B: lee `datos[0]` y corre los n − 1 restantes un lugar a la izquierda. |
| tope | O(1) | O(1) | A lee `datos[tope - 1]`; B lee `datos[0]`. Ninguna corre nada. |
| esVacia / esLlena | O(1) | O(1) | Comparan un contador con 0 o con `capacidad`. |

**¿Cuál conviene? ¿Depende de algo?** Conviene el tope al final, y no depende del uso. Como pide explicar la pregunta 1 del parcial (Clase 6, diap. 61), con el tope al final `apilar` y `desapilar` solo tocan la posición `tope` y mueven un índice. Con el tope al inicio hace falta una operación extra: correr todos los elementos una posición para abrir (al apilar) o cerrar (al desapilar) el hueco en el índice 0. A cambio no se gana nada: `tope` es O(1) en las dos y ocupan la misma memoria. Como usar una pila es justamente apilar y desapilar, no hay un uso en el que el tope al inicio sea mejor; el código de clase lo incluye "solo con fines didácticos, para comparar contra PilaTopeFinal" (`CLASE 06 07-09/PILA CODIGO/PILA CON INTERFAZ/PilaTopeInicio.java`).

### 1.3 Métodos (`MetodosPila`)

Las pilas nuevas (resultados y auxiliares) se crean con `new PilaTopeFinal(CAPACIDAD)`, la variante con todas sus operaciones O(1). n es la cantidad de elementos de la entrada; los costos suponen una entrada con operaciones O(1) (para una `PilaTopeInicio` de entrada, ver el resumen).

- **`pasarPila(origen: Pila): Pila` — O(n).** Desapila cada elemento de `origen` y lo apila en una pila nueva. Al pasarlos de a uno, el resultado queda en orden inverso (el tope de `origen` queda en el fondo); el enunciado no pide conservar el orden. `origen` queda vacía.
- **`copiarPila(p: Pila): Pila` — O(n).** Pasa `p` a una pila auxiliar (queda invertida) y la vuelve a pasar, apilando cada elemento en `p` y en la copia. La copia tiene el mismo contenido y orden que `p`, y `p` queda como estaba.
- **`invertirPila(p: Pila): Pila` — O(n), recursivo.** Un auxiliar privado recursivo, sin ciclos, desapila el tope de `p`, lo apila en el resultado, se llama con el resto y, al volver, lo reapila en `p`: una llamada por elemento. El resultado recibe los elementos de tope a fondo, así que queda en orden inverso, y `p` queda como estaba.
- **`masDeUnaOcurrencia(p: Pila): boolean` — O(n²).** Desapila los elementos de `p` de a uno hacia una pila auxiliar. Por cada uno, un auxiliar privado busca un valor igual en lo que queda de `p` y la deja como estaba; para eso usa una segunda pila auxiliar, que se crea una sola vez y que cada búsqueda deja vacía. Se detiene apenas encuentra un repetido y al final devuelve a `p` los elementos sacados. Sin repetidos (peor caso) compara cada elemento con todos los que tiene debajo: n(n − 1)/2 comparaciones, la "detección de duplicados ingenua" que la Clase 6 (diap. 57) pone como ejemplo de O(n²). `p` queda con su contenido y orden original.
- **`eliminarImpares(p: Pila): Pila` — O(n).** Pasa `p` a una pila auxiliar y la devuelve a `p`. En la vuelta los elementos salen de fondo a tope, y los pares (`x % 2 == 0`, incluidos el 0 y los pares negativos) se apilan también en el resultado, que así conserva su orden relativo. `p` queda como estaba.

## Parte 2 — TDA Cola

### 2.1 Especificación

```text
TDA Cola

Operaciones:
  crear() -> Cola
    post: devuelve una cola vacia
  encolar(c: Cola, x: elemento) -> Cola
    pre:  c no esta llena
    post: x queda al final de c
  desencolar(c: Cola) -> Cola
    pre:  c no esta vacia
    post: se elimina el elemento del frente de c
  frente(c: Cola) -> elemento
    pre:  c no esta vacia
    post: devuelve el elemento del frente sin eliminarlo
  esVacia(c: Cola) -> boolean
    post: devuelve true si c no tiene elementos
  esLlena(c: Cola) -> boolean
    post: devuelve true si c alcanzo su capacidad maxima

Dominio: Cola = secuencia finita y ordenada de elementos, donde el
         primero en entrar es el primero en salir (FIFO).
```

- Es la especificación de la Clase 6 (diap. 23). `frente` es la operación `primero` (`obtenerPrimero` en el código) de la Clase 6, con el nombre que usan el enunciado y la Clase 4 (diap. 7). `esLlena` es, como en la Pila, la auxiliar de la implementación estática (Clase 6, diap. 13) y la precondición de `encolar` (diap. 28). En la `ColaEstatica`, "alcanzó su capacidad" significa que no quedan lugares después del último elemento, aunque haya lugares libres antes del frente (ver 2.2).
- En Java `encolar` es `void` y `desencolar` además devuelve el elemento que elimina (Clase 6, diap. 28). Las precondiciones lanzan `RuntimeException("Cola llena")` y `RuntimeException("Cola vacia")`.

### 2.2 Implementación y complejidad

`ColaEstatica` guarda `datos`, `frente`, `cantidad` y `capacidad` (Clase 6, diap. 27). `ColaCircular` agrega `fin` (Clase 2 diap. 24; Clase 4 diap. 8).

| Operación | `ColaEstatica` | `ColaCircular` | Por qué |
|---|---|---|---|
| encolar | O(1) | O(1) | Estática: `datos[frente + cantidad] = x; cantidad++`. Circular: `datos[fin] = x; fin = (fin + 1) % capacidad; cantidad++`. Ninguna corre elementos. |
| desencolar | O(1) | O(1) | Las dos leen `datos[frente]` y avanzan `frente` (la circular, módulo `capacidad`). |
| frente | O(1) | O(1) | Lee `datos[frente]`. |
| esVacia | O(1) | O(1) | `cantidad == 0`. |
| esLlena | O(1) | O(1) | Estática: `frente + cantidad == capacidad`. Circular: `cantidad == capacidad`. |

**¿Cuál conviene en la práctica?** La circular. En tiempo son iguales; la diferencia está en el espacio, que es lo que plantea la pregunta 2 del parcial (Clase 6, diap. 61). En la `ColaEstatica`, `encolar` escribe en `frente + cantidad` y `desencolar` avanza `frente`, que nunca vuelve a 0. Por eso el próximo lugar libre es `frente + cantidad`, y la cola está llena cuando esa posición llega a `capacidad` aunque `cantidad` sea menor: si `esLlena` fuera `cantidad == capacidad`, `encolar` escribiría fuera del arreglo (`datos[frente + cantidad] = x`, Clase 6 diap. 28). Los lugares anteriores a `frente` quedan desperdiciados, y una cola vacía puede estar llena: en la demo de `CLASE 06 07-09/COLA CODIGO/ColaEstatica.java` (capacidad 3), después de encolar 3 elementos y desencolarlos, `esVacia()` y `esLlena()` dan `true`. La `ColaCircular` reutiliza esos lugares con el operador `%` ("el % reutiliza el lugar que dejó libre el 10", Clase 4 diap. 8), con el mismo costo O(1), y solo está llena cuando `cantidad == capacidad`. Es lo que resume la Clase 2 (diap. 25): la cola estática "necesita índices circulares (inicio, fin con % capacidad) para ser eficiente". La otra forma de reutilizar el espacio sería correr los elementos en cada `desencolar`, que lo haría O(n); es lo que el desafío de la Clase 2 (diap. 23) pide evitar.

**Consecuencia para los métodos.** Recorrer una `ColaEstatica` y volver a llenarla gasta lugares nuevos: cada elemento que vuelve a entrar ocupa una posición después del último, y los lugares liberados al principio no se recuperan. Por ejemplo, una `ColaEstatica` de capacidad 10 con 6 elementos, después de desencolarlos todos, solo acepta 4 más. Por eso los métodos de inversión dejan `c` vacía (el enunciado no pide conservarla), y `finalCoincide`, que sí tiene que restaurar sus colas, documenta la precondición correspondiente. Las colas que crean los métodos son `ColaCircular`, así que el problema no aparece en los resultados ni en las auxiliares.

### 2.3 Métodos (`MetodosCola`)

Las colas nuevas se crean con `new ColaCircular(CAPACIDAD)` y la pila auxiliar con `new PilaTopeFinal(CAPACIDAD)`. Los costos son los mismos con las dos variantes de entrada, porque todas sus operaciones son O(1).

- **`pasarCola(origen: Cola): Cola` — O(n).** Desencola cada elemento de `origen` y lo encola en una cola nueva, que queda en el mismo orden. `origen` queda vacía.
- **`invertirColaConPila(c: Cola): Cola` — O(n).** Pasa `c` a una pila auxiliar y después la desapila encolando en una cola nueva: el último de `c` sale primero. `c` queda vacía.
- **`invertirColaSinPila(c: Cola): Cola` — O(n), recursivo.** Recursión pura, sin ninguna estructura auxiliar: si `c` está vacía devuelve una cola nueva; si no, desencola `x`, invierte el resto con la llamada recursiva y encola `x` al final de la cola que esta devuelve. La única cola que se crea es la que se devuelve; hasta que vuelve la llamada recursiva, cada elemento queda en la variable `x` de su llamada (una llamada por elemento). `c` queda vacía.
- **`finalCoincide(c1: Cola, c2: Cola, k: entero): boolean` — O(n1 + n2).** Si `k < 0` lanza `IllegalArgumentException("k debe ser >= 0")`. Pasa cada cola a una auxiliar contando sus elementos (n1 y n2), devuelve sin comparar los primeros n1 − k y n2 − k, y devuelve los últimos k de las dos comparándolos de a pares. Si aparece una diferencia sigue devolviendo, así las dos quedan con su contenido y orden original. Devuelve `true` si las dos tienen al menos k elementos y sus últimos k coinciden en el mismo orden (con `k == 0`, `true`; si alguna tiene menos de k, `false`). Precondiciones: `c1` y `c2` son colas distintas, y una `ColaEstatica` de entrada tiene, después de su último elemento, al menos tantos lugares libres como elementos, porque se vacía y se vuelve a llenar sin reutilizar lo liberado. Con k ≥ 1 ninguna solución que use solo las operaciones de Cola puede evitar esta condición: la cola solo deja ver su frente y solo `esVacia` dice dónde termina, así que para saber cuáles son los últimos k hay que desencolar los n elementos, y para que la cola recupere su contenido hay que volver a encolarlos; en la `ColaEstatica` cada `encolar` ocupa un lugar nuevo (Clase 6 diap. 28). Si no se cumple, `encolar` lanza "Cola llena" a mitad de camino y `c1` y `c2` pueden quedar incompletas, también la que no es `ColaEstatica`: con la precondición sin cumplir, la operación queda indefinida (Clase 6 diap. 5).

## Parte 3 — TDA Cola con Prioridad

### 3.1 Especificación

```text
TDA ColaConPrioridad

Operaciones:
  crear() -> CCP
    post: devuelve una CCP vacia
  insertar(c: CCP, x: elemento, prioridad: entero) -> CCP
    pre:  c no esta llena
    post: x queda agregado con esa prioridad
  extraerMax(c: CCP) -> CCP
    pre:  c no esta vacia
    post: se quita el elemento de mayor prioridad; si hay varios
          con esa prioridad, el que se inserto primero
  verMax(c: CCP) -> elemento
    pre:  c no esta vacia
    post: devuelve el elemento de mayor prioridad (el que quitaria
          extraerMax), sin sacarlo
  esVacia(c: CCP) -> boolean
    post: devuelve true si c no tiene elementos
  esLlena(c: CCP) -> boolean
    post: devuelve true si c alcanzo su capacidad maxima
  prioridadMax(c: CCP) -> entero
    pre:  c no esta vacia
    post: devuelve la prioridad del elemento que devuelve verMax,
          sin sacarlo

Dominio: CCP = secuencia finita de elementos con una prioridad entera
         asociada, donde sale primero el de mayor prioridad (no
         necesariamente el que entro primero); entre elementos de
         igual prioridad sale primero el que se inserto antes.
```

- Es la especificación de la Clase 4 (diap. 9), con el dominio de la Clase 6 (diap. 32). Al final se agregan `esLlena`, que ya está en la especificación de la Clase 6 (diap. 32) y es la auxiliar de la implementación estática y precondición de `insertar` (Clase 3 diap. 6), y `prioridadMax`. En Java la interfaz se llama `ColaPrioridad`. Los comentarios del código escriben las mismas operaciones con el nombre de la Clase 6 (`cp: ColaPrioridad`, diap. 32) y llaman `valor` al elemento, porque así se llama el parámetro en Java, como en `insertar(int valor, int prioridad)` de `CLASE 05 31-08/Ejercicio3_GuardiaPrioridad.java` (código de la Clase 4).
- **Criterio de desempate:** entre elementos de igual prioridad sale primero el que se insertó antes (FIFO). Si todas las prioridades son iguales, se comporta como una Cola. El código de la Clase 3 (`CLASE 04 24-08/Ejercicio1_ColaPrioridad.java`) llama "implementación estable" a quedarse, ante un empate, con el primero encontrado.
- **Por qué `prioridadMax`:** `extraerMax` y `verMax` devuelven solo el valor. `combinar` tiene que reinsertar cada elemento con su misma prioridad y `sumarValoresPrioridadPar` tiene que saber qué prioridades son pares; sin una operación que la devuelva, la prioridad no se puede leer desde afuera del TDA (ver "Lo que no está en las diapositivas").
- En Java, `insertar(int valor, int prioridad)` es `void`, `extraerMax` además devuelve el valor que quita (Clase 3 diap. 7) y las precondiciones lanzan `RuntimeException("Cola llena")` y `RuntimeException("Cola vacia")` (Clase 6 diap. 35 y 37).

### 3.2 Implementación y complejidad

Las dos variantes guardan `valores` y `prioridades` en arreglos paralelos (`prioridades[i]` es la prioridad de `valores[i]`, Clase 3 diap. 6), más `cantidad` y `capacidad`.

| Operación | Desordenada (A) | Ordenada (B) | Por qué |
|---|---|---|---|
| insertar | O(1) | O(n) | A: agrega en la posición `cantidad`. B: corre a la derecha los elementos con prioridad `>=` a la nueva hasta encontrar su lugar; en el peor caso (la nueva es la menor) los corre todos. |
| extraerMax | O(n) | O(1) | A: busca el máximo recorriendo todo (`indiceMax`) y corre los siguientes un lugar a la izquierda. B: el máximo está en `cantidad - 1`, alcanza con `cantidad--`. |
| verMax | O(n) | O(1) | A: `valores[indiceMax()]`. B: `valores[cantidad - 1]`. |
| prioridadMax | O(n) | O(1) | Igual que `verMax`, leyendo `prioridades`. |
| esVacia / esLlena | O(1) | O(1) | Comparan `cantidad`. |

**Cómo respeta cada una el desempate.**

- *Desordenada:* `insertar` agrega al final, así que el arreglo queda en orden de inserción. `indiceMax` compara con `>` estricto, como en la Clase 3 (diap. 7): ante un empate gana el índice menor, que es el insertado antes. `extraerMax` tapa el hueco corriendo los siguientes (Clase 3 diap. 7), lo que conserva el orden de inserción. El truco de la Clase 6 (diap. 37) lo rompería: con (A, 9), (B, 1), (C, 1), al extraer A el último (C) pasaría a la posición 0 y el siguiente `extraerMax` devolvería C antes que B. Correr no empeora la cota, porque la búsqueda ya es O(n).
- *Ordenada:* el arreglo se mantiene de menor a mayor prioridad, con el máximo en `cantidad - 1` (Clase 6 diap. 34). Con `>=`, el nuevo queda debajo de los de igual prioridad, así que los insertados antes quedan más cerca del final y salen primero. Sigue siendo O(n) en el peor caso, y O(1) cuando la nueva prioridad es mayor que todas.

**¿Hay una variante mejor en todos los casos?** No. Como dice la Clase 3 (diap. 10), "no existe una respuesta única — depende de qué operación se hace con más frecuencia en el problema": cada variante es barata en una operación y cara en la otra, y respetar el desempate no cambia esos costos. Si se insertan muchos elementos y se extrae pocas veces, conviene la desordenada (insertar barato), que es la respuesta a la pregunta 3 del parcial (Clase 6 diap. 61). Si se extrae el máximo constantemente, o se consultan mucho `verMax` y `prioridadMax`, y se inserta poco, conviene la ordenada (extraer barato). También influye el orden en que llegan las prioridades: en la ordenada, `insertar` corre los elementos con prioridad mayor o igual a la nueva (en el peor caso, todos: Clase 6 diap. 35), así que no corre nada si cada prioridad nueva es mayor que las anteriores; eso se aprovecha en 3.3.

### 3.3 Métodos (`MetodosColaPrioridad`)

Cada método elige la variante de la estructura que crea con el criterio de la Clase 3 (diap. 10): según el costo de las operaciones que el problema usa sobre ella.

| Estructura nueva | Variante | Por qué |
|---|---|---|
| Resultado de `combinar` | `ColaPrioridadDesordenada` | Solo se inserta en ella, y su `insertar` es O(1). |
| Auxiliar de `invertirColaConColaPrioridad` | `ColaPrioridadOrdenada` | Las prioridades llegan crecientes: `insertar` nunca corre nada y `extraerMax` es O(1). |
| Auxiliar de `sumarValoresPrioridadPar` | `ColaPrioridadDesordenada` | `insertar` O(1); el método es O(n²) con cualquier auxiliar (ver abajo). |
| Cola resultado de `invertirColaConColaPrioridad` | `ColaCircular` | Como en la Parte 2. |

- **`combinar(cp1: ColaPrioridad, cp2: ColaPrioridad): ColaPrioridad` — O(n²) en el peor caso, con n = n1 + n2.** Pasa al resultado todo `cp1` y después todo `cp2`: por cada elemento lee `prioridadMax`, lo quita con `extraerMax` y lo inserta en el resultado con esa prioridad. Cada entrada entrega sus elementos de igual prioridad en orden de inserción y se reinsertan en ese orden, así que el desempate se conserva dentro de cada entrada; ante igual prioridad, los de `cp1` cuentan como insertados antes que los de `cp2`. Las dos entradas quedan vacías. El costo lo ponen las extracciones: O(n) cada una si las entradas son desordenadas (O(n²) en total) y O(1) si son ordenadas (O(n) en total). Precondiciones: n1 + n2 ≤ `CAPACIDAD`, y `cp1` y `cp2` son estructuras distintas.
- **`invertirColaConColaPrioridad(c: Cola): Cola` — O(n).** Desencola `c` insertando cada elemento en la auxiliar con prioridades crecientes 0, 1, 2, …, así el último recibe la mayor. Después aplica `extraerMax` hasta vaciarla, encolando cada valor en una cola nueva: salen del último al primero. Con la auxiliar ordenada, cada `insertar` ubica el elemento al final sin correr nada y cada `extraerMax` es O(1); con una desordenada, cada `extraerMax` recorrería todo y el método sería O(n²). `c` queda vacía.
- **`sumarValoresPrioridadPar(cp: ColaPrioridad): entero` — O(n²).** Pasa cada par de `cp` a la auxiliar (`prioridadMax`, `extraerMax` e `insertar` con la misma prioridad), sumando los valores cuya prioridad es par (`prioridad % 2 == 0`, incluidas las negativas), y después los devuelve todos a `cp` de la misma forma. Los elementos salen por prioridad y, entre iguales, en orden de inserción, y se reinsertan en ese orden, así que `cp` queda con el mismo contenido y el mismo orden entre elementos de igual prioridad. Es O(n²) con cualquier `cp`: la vuelta desde la auxiliar desordenada cuesta O(n) por `extraerMax`. Con una auxiliar ordenada tampoco bajaría, porque los elementos le llegarían con prioridad no creciente, el peor caso de su `insertar`.

## Resumen de costos

Peor caso, que es la convención de la materia (Clase 6 diap. 56). n es la cantidad de elementos de la entrada (en `combinar`, n = n1 + n2). "Queda igual" significa mismo contenido y mismo orden.

| Parte | Método | Costo | Qué pasa con la entrada |
|---|---|---|---|
| Pila | `pasarPila` | O(n) | queda vacía |
| Pila | `copiarPila` | O(n) | queda igual |
| Pila | `invertirPila` | O(n) | queda igual |
| Pila | `masDeUnaOcurrencia` | O(n²) | queda igual |
| Pila | `eliminarImpares` | O(n) | queda igual |
| Cola | `pasarCola` | O(n) | queda vacía |
| Cola | `invertirColaConPila` | O(n) | queda vacía |
| Cola | `invertirColaSinPila` | O(n) | queda vacía |
| Cola | `finalCoincide` | O(n1 + n2) | las dos quedan iguales |
| Cola con prioridad | `combinar` | O(n²); O(n) si las dos entradas son ordenadas | las dos quedan vacías |
| Cola con prioridad | `invertirColaConColaPrioridad` | O(n) (sería O(n²) con una auxiliar desordenada) | queda vacía |
| Cola con prioridad | `sumarValoresPrioridadPar` | O(n²) | queda igual (mismo orden entre iguales) |

- **Pila:** los costos suponen una entrada con operaciones O(1). Con una `PilaTopeInicio` de entrada, cada `apilar` o `desapilar` sobre ella cuesta O(n) y el total se multiplica por n: `pasarPila`, `copiarPila`, `invertirPila` y `eliminarImpares` pasan a O(n²), y `masDeUnaOcurrencia` a O(n³). Las pilas que crean los métodos son siempre `PilaTopeFinal`.
- **Cola:** los costos son los mismos con `ColaEstatica` y con `ColaCircular`, porque todas sus operaciones son O(1).
- **Cola con prioridad:** el costo depende de la variante de cada estructura, como se detalla en 3.3.

## Anexo — Base en el material de clase

Las citas indican el número de la presentación (Clase 1, 2, 3, 4 y 6) y el de la diapositiva en el orden de la presentación. Cada presentación está en una carpeta de `Clases`: Clase 1 en `CLASE 01 03-08`, Clase 2 en `CLASE 02 10-08`, Clase 3 en `CLASE 03 22-08 REMOTA` (con una copia idéntica en `CLASE 04 24-08`), Clase 4 en `CLASE 05 31-08` y Clase 6 en `CLASE 06 07-09`. El código de clase se cita con su ruta completa dentro de `Clases`; por eso `CLASE 04 24-08/Ejercicio1_ColaPrioridad.java` es código de la Clase 3 y `CLASE 05 31-08/Ejercicio3_GuardiaPrioridad.java`, de la Clase 4.

| Parte del TP | De dónde sale |
|---|---|
| Formato de especificación: dominio, signatura, pre y post | Clase 1 diap. 11-12; Clase 2 diap. 8; Clase 6 diap. 5, 16, 23 y 32 |
| Traducción TDA → Java: dominio → atributos privados, `crear()` → constructor, `operacion(t: TDA, …)` → método de instancia, `pre` → validación al inicio que lanza una excepción | Clase 1 diap. 21 y 27 (repetida en Clase 2 diap. 5 y Clase 6 diap. 12); mensajes de las excepciones: Clase 2 diap. 12 y 24, Clase 6 diap. 20, 28, 35 y 37 |
| Estructura estática: arreglo + contador o índice + `capacidad`, constructor `(int capacidad)` | Clase 2 diap. 9-11 |
| Métodos que usan solo las operaciones del TDA | Clase 1 diap. 5 y 8 (encapsulamiento); Clase 4 diap. 27-33 (problemas resueltos combinando TDAs por sus operaciones) |
| `PilaTopeFinal` | Clase 2 diap. 11-13 y 21; Clase 6 diap. 15 y 18-21 ("PILA - TOPE AL FINAL"); `CLASE 06 07-09/PILA CODIGO/PILA CON INTERFAZ/PilaTopeFinal.java` |
| `PilaTopeInicio` | Clase 6 diap. 14 y pregunta 1 del parcial (diap. 61); `CLASE 06 07-09/PILA CODIGO/PILA CON INTERFAZ/PilaTopeInicio.java` |
| `ColaEstatica` (no circular, con `frente` y `cantidad`) | Clase 6 diap. 25-29 ("COLA - ARREGLO ESTÁTICO") y pregunta 2 del parcial (diap. 61); `CLASE 06 07-09/COLA CODIGO/ColaEstatica.java` |
| `ColaCircular` (en las diapositivas, "Cola con array circular") | Clase 2 diap. 23-24; Clase 4 diap. 7-8; `CLASE 02 10-08/Ejercicio2_ColaEstatica.java` |
| Cola con prioridad con dos arreglos paralelos (`ColaPrioridadDesordenada`) | Clase 3 diap. 4 y 6-7; Clase 4 diap. 9-10; `CLASE 04 24-08/Ejercicio1_ColaPrioridad.java` |
| Variante ordenada (`ColaPrioridadOrdenada`) | Clase 6 diap. 33-35; `CLASE 06 07-09/COLA CODIGO/ColaPrioridadOrdenada.java` |
| "¿Cuál conviene? Depende del uso": elegir la implementación por el costo de las operaciones que usa el problema | Clase 3 diap. 9-10; Clase 6 diap. 33 y pregunta 3 del parcial (diap. 61) |
| Big O y convención de reportar el peor caso | Clase 2 diap. 18-19; Clase 6 diap. 56-57 |

Donde el código de clase difiere de las diapositivas que implementan cada estructura (la `Pila<T>` genérica con `Object[]` y `tope = -1` de `CLASE 06 07-09/PILA CODIGO/PILA CON INTERFAZ`, o `IllegalStateException` en lugar de `RuntimeException`), se siguen las diapositivas: Clase 2 diap. 11-13 y Clase 6 diap. 19-21 para la pila, Clase 6 diap. 27-29 para la cola, y Clase 3 diap. 6-7 y Clase 6 diap. 34-35 para la cola con prioridad.

### Lo que no está en las diapositivas y por qué

- **Las interfaces `Pila`, `Cola` y `ColaPrioridad`.** Las diapositivas implementan cada variante como una clase suelta. La interfaz sale del código de la Clase 6 (`CLASE 06 07-09/PILA CODIGO/PILA CON INTERFAZ`): `Pila.java` "define el CONTRATO del TDA" y cualquier clase que la implemente "es una pila válida mientras respete este contrato", y `Main.java` usa las dos variantes a través de la interfaz. Hace falta porque el enunciado pide que los métodos funcionen igual con cualquier variante: reciben y devuelven el tipo de la interfaz y solo usan sus operaciones. `Cola` y `ColaPrioridad` siguen el mismo patrón, y las tres son de `int` (la `Pila<T>` de clase es genérica).
- **`prioridadMax`.** No está en ninguna especificación de clase. `extraerMax` y `verMax` devuelven solo el valor, pero `combinar` tiene que reinsertar cada elemento con su misma prioridad y `sumarValoresPrioridadPar` tiene que saber qué prioridades son pares. Como el enunciado prohíbe leer el arreglo interno, la prioridad solo se puede conocer si el TDA la ofrece. Es la misma razón por la que la Clase 4 (diap. 16) agrega `claves()` al Diccionario: "sin ella, no habría forma de enumerar los pares desde afuera del TDA".
- **La recursión de `invertirPila` e `invertirColaSinPila`.** No aparece en las diapositivas ni en el código de clase; la pide el enunciado (1.3: "Debe resolverse en forma recursiva"; 2.3: "la inversión debe lograrse únicamente con recursión pura"). Las dos usan la forma más simple: si la estructura está vacía se termina, y si no se saca un elemento y la llamada sigue con el resto. `invertirPila` delega en un auxiliar privado recursivo, como los auxiliares privados de clase (`indiceMax`, Clase 3 diap. 7; `buscarNodo`, Clase 4 diap. 21), que recibe también la pila resultado: el resultado se arma a la ida y `p` se restaura a la vuelta. Sin ese parámetro, cada llamada tendría que poner su elemento en el fondo de la pila que le devuelve la siguiente, y una pila solo agrega en el tope.
- **`>=` en el `insertar` de la ordenada.** La Clase 6 (diap. 35) corre los elementos mientras `datos[i] > x`: ahí el valor es la prioridad y dos elementos iguales no se distinguen. Con dos arreglos sí se distinguen, y con `prioridades[i] >= prioridad` el nuevo queda debajo de los de igual prioridad, así sale después que ellos (el desempate FIFO de 3.1). Con `>` saldría antes que ellos.
- **Correr los elementos en el `extraerMax` de la desordenada, sin "tapar con el último".** Se hace como en la Clase 3 (diap. 7): "tapar el hueco" corriendo los siguientes un lugar. El truco de la Clase 6 (diap. 37) pone el último elemento en el hueco y rompe el orden de inserción, que es lo que decide los empates (ejemplo en 3.2). La Clase 3 (diap. 16 y 21) ya aclara que "tapar con el último" vale en el Conjunto porque "no hay orden que preservar"; acá sí lo hay.
- **La constante `CAPACIDAD = 100` de las clases `Metodos`.** Las diapositivas no dicen cómo un método crea las estructuras que necesita (resultado o auxiliares). Cada clase `Metodos` declara `private static final int CAPACIDAD = 100;` y las crea con un constructor concreto, guardándolas en una variable del tipo de la interfaz, como `CLASE 06 07-09/PILA CODIGO/PILA CON INTERFAZ/Main.java` (`Pila<Integer> pilaFinal = new PilaTopeFinal<>(n);`). El valor 100 es arbitrario; por eso las entradas pueden tener a lo sumo 100 elementos (en `combinar`, entre las dos).
- **`IllegalArgumentException` en `finalCoincide` con `k < 0`.** Las diapositivas usan `RuntimeException` para las precondiciones de las operaciones; para un argumento inválido se sigue al Conjunto de la Clase 6 (diap. 42 y 44), que lanza `IllegalArgumentException`.
- **Lo que usa `Main`.** `Main` es solo la demo: el enunciado no la pide y no forma parte de ningún TDA ni de los métodos. Usa lo del código de clase: métodos `static` que reciben el tipo de la interfaz y una estructura recién creada (`probarPila` en `CLASE 06 07-09/PILA CODIGO/PILA CON INTERFAZ/Main.java`), arreglos inicializados con llaves (la demo de `CLASE 06 07-09/COLA CODIGO/ColaPrioridadDesordenada.java`), `try`/`catch` de `RuntimeException` (`CLASE 02 10-08/Ejercicio2_ColaEstatica.java`), sobrecarga de métodos (Clase 1 diap. 24) y la salida "(esperado …)" de `CLASE 05 31-08/PilaEstatica.java`. Agrega dos cosas simples que no están en el material: un parámetro `boolean` que elige con qué variante se crean las entradas, para correr las mismas pruebas con las dos, y dos contadores `static` para la línea final (`Verificaciones: … OK, … con error`).
