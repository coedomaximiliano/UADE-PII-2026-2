# TP2 — TDA Diccionario e Implementaciones Dinámicas

**Algoritmos y Estructuras de Datos II — Informe**

**Alumnos:** Pedro Stella — Bautista Chavarri

Especificación del TDA Diccionario, implementación dinámica con cadena de nodos, implementación estática con arreglos paralelos, comparación empírica de las dos y los métodos de utilización pedidos en la Parte 4.

## Cómo compilar y ejecutar

Requiere JDK 21. Todas las clases están en el paquete por defecto y no usan bibliotecas. Desde la carpeta que contiene el TP:

```
cd "TP2 - Dinamicas"
javac *.java
java Main               (pruebas de las dos implementaciones y de los métodos)
java MedicionTiempos    (medición de la Parte 3)
```

| Archivo | Qué es |
|---|---|
| `Diccionario` | interfaz: el contrato del TDA, con la especificación de cada operación |
| `NodoDiccionario` | nodo de la cadena clave-valor (Parte 2) |
| `DiccionarioDinamico` | implementación dinámica (Parte 2) |
| `DiccionarioEstatico` | implementación estática con dos arreglos paralelos (Parte 3) |
| `NodoLista`, `ListaDinamica` | Lista dinámica: es lo que devuelven `claves()` y `clavesOrdenadas()` |
| `MetodosDiccionario` | los cuatro métodos de la Parte 4 |
| `MedicionTiempos` | medición empírica de la Parte 3.1 |
| `Main` | programa de prueba: cada línea muestra lo obtenido, lo esperado y OK o ERROR |

Los dos diccionarios implementan la misma interfaz `Diccionario`, así que los métodos de la Parte 4 y las pruebas del `Main` corren sin cambios con cualquiera de las dos implementaciones. En el código, cada operación está precedida por su especificación en el formato de clase (`// definir(d, clave, valor) -> Diccionario`, `// pre: …`, `// post: …`).

Las citas (Clase N, diap. M) remiten al material de clase; en el anexo final está de dónde sale cada parte y lo que no está en las diapositivas, con el motivo.

## Parte 0 — Preguntas teóricas

**1. ¿Qué diferencia hay entre el TDA Diccionario y el TDA Conjunto? ¿Por qué el Diccionario necesita una operación como `existeClave` antes de `definir`?**

El Conjunto guarda elementos sueltos y sólo sabe responder si un elemento pertenece o no; el Diccionario guarda pares (clave, valor), donde cada clave es única y tiene asociado un valor, así que además de saber si la clave está permite recuperar el valor asociado (Clase 4, diap. 15). Dicho en una línea: el Conjunto responde "¿está?", el Diccionario responde "¿está y qué tiene asociado?".

`definir` necesita esa pregunta porque tiene dos comportamientos distintos según el caso: si la clave ya existía debe **actualizar** el valor, y si no existía debe **agregar** el par nuevo. Sin distinguir los dos casos, la misma clave quedaría duplicada y `cantidadClaves` dejaría de ser correcto. Es exactamente la misma razón por la que `agregar` del Conjunto llama primero a `pertenece` (Clase 4, diap. 15; Clase 3, diap. 15).

**2. ¿Qué es un Nodo en una implementación dinámica? ¿Qué información mínima debe guardar para poder encadenar los elementos entre sí?**

Un Nodo es la unidad mínima de una estructura dinámica: un objeto que se pide con `new` cuando hace falta guardar un elemento más. Como mínimo guarda dos cosas: **el dato** y **una referencia al próximo nodo** de la cadena, que vale `null` si es el último (Clase 4, diap. 18 y 19). En el Diccionario el "dato" son dos datos, la clave y el valor, porque cada elemento es un par. La estructura en sí sólo guarda la referencia a la cabeza de la cadena: desde ahí se llega al resto saltando de nodo en nodo, y por eso no hay arreglo ni capacidad fija (Clase 4, diap. 20).

**3. ¿Por qué, en una implementación dinámica, insertar un elemento al principio de una Lista es O(1), mientras que en una implementación estática (con arreglo) la misma operación es O(n)?**

En la dinámica, insertar al principio son tres asignaciones: crear el nodo, apuntar su `siguiente` a la cabeza actual y mover la cabeza al nodo nuevo. No se toca ningún otro nodo, así que el costo no depende de cuántos elementos haya: O(1) (Clase 4, diap. 22).

En la estática, las posiciones del arreglo son contiguas y fijas: la posición 0 ya está ocupada, y la única forma de dejarla libre es **correr** todos los elementos un lugar a la derecha. Ese corrimiento recorre los n elementos, así que la misma operación cuesta O(n). Es el mismo corrimiento que hace la variante "tope al inicio" de la Pila del TP1, y el de `encolar` en la cola de prioridad ordenada (Clase 6, diap. 35).

**4. Mencionen una ventaja y una desventaja de una implementación dinámica frente a una implementación estática, en términos de uso de memoria.**

- **Ventaja:** pide memoria de a un nodo por vez, exactamente los que se usan. No hay que estimar la capacidad de antemano ni queda espacio reservado sin ocupar, que es justo la limitación de la implementación estática: tamaño fijo, desperdicio si se reserva de más y error si se necesita más (Clase 2, diap. 16; Clase 6, diap. 22).
- **Desventaja:** cada elemento ocupa bastante más. En un arreglo, un `int` son 4 bytes y nada más; un nodo son unos 16 a 24 bytes, porque al dato hay que sumarle el encabezado del objeto (12-16 bytes) y la referencia al siguiente (4-8 bytes) (Clase 6, diap. 58).

**5. Si dos implementaciones caen en la misma clase de complejidad teórica (mismo Big O), ¿por qué en la práctica una puede medir tiempos reales distintos de la otra? Mencionen al menos un factor.**

Porque Big O describe cómo **escala** el costo con n y, para eso, descarta las constantes y los factores multiplicativos (Clase 6, diap. 56). Esas constantes existen igual y se pagan en tiempo real.

El factor más importante en esta comparación es la **localidad de referencia**: el material dice que la implementación estática tiene "buena localidad de memoria (el array es contiguo, lo que ayuda al rendimiento real)" (Clase 2, diap. 16), y que cada nodo ocupa 16-24 bytes contra los 4 de un `int` en un arreglo (Clase 6, diap. 58). El mecanismo que hay detrás de esa ventaja —la caché del procesador, que trae la memoria de a bloques, y los fallos de caché cuando los datos están desparramados— no aparece en las diapositivas: es nuestra explicación de por qué la contigüidad ayuda (ver anexo, punto 10). Los nodos de una cadena quedan donde el `new` encontró lugar, así que cada `actual = actual.siguiente` es un salto a otra dirección; a eso se suma el costo de crear un objeto por elemento.

**6. ¿Por qué la medición empírica de tiempos (`medirNanos`, con warmup y mejor tiempo de varias repeticiones) complementa, pero no reemplaza, al análisis de complejidad teórico (Big O)?**

Porque miden cosas distintas. La medición dice cuánto tarda **esta** implementación, en **esta** máquina, con **esta** JVM y para los tamaños de n que se probaron; es lo único que muestra las constantes que Big O descarta. Pero no puede probar cómo se comporta para cualquier n —siempre se miden unos pocos tamaños— y depende de condiciones que no tienen que ver con el algoritmo: el JIT todavía compilando, la caché, lo que esté haciendo la máquina en ese momento. Justamente por eso la técnica hace warmup y se queda con el mejor tiempo de varias repeticiones.

El Big O, al revés, es independiente de la máquina y vale para todo n, pero no dice nada de las constantes: dos implementaciones O(n) pueden diferir varias veces en tiempo real. Uno elige el algoritmo, la otra confirma la tendencia y muestra el costo concreto (Clase 6, diap. 52 y 56).

## Parte 1 — Especificación del TDA Diccionario

```text
TDA Diccionario

Dominio: Diccionario = coleccion finita de pares (clave, valor) donde cada clave es
         unica, es decir aparece asociada a lo sumo a un valor. No hay ningun orden
         definido entre los pares.

Operaciones:

  crear() -> Diccionario
    post: devuelve un diccionario vacio

  definir(d: Diccionario, clave, valor) -> Diccionario
    pre:  d no esta lleno, o clave pertenece a d
    post: valor queda asociado a clave en d; si clave ya pertenecia a d se actualiza
          su valor y la cantidad de claves no cambia; si no, se agrega el par nuevo

  obtener(d: Diccionario, clave) -> valor
    pre:  existeClave(d, clave)
    post: devuelve el valor asociado a clave; d no se modifica

  eliminar(d: Diccionario, clave) -> Diccionario
    pre:  existeClave(d, clave)
    post: clave y su valor ya no pertenecen a d

  existeClave(d: Diccionario, clave) -> boolean
    post: devuelve true si clave pertenece a d; d no se modifica

  esVacio(d: Diccionario) -> boolean
    post: devuelve true si d no tiene ninguna clave

  cantidadClaves(d: Diccionario) -> entero
    post: devuelve la cantidad de claves de d

  claves(d: Diccionario) -> Lista
    post: devuelve una lista con todas las claves de d, sin ningun orden particular y
          con tantos elementos como cantidadClaves(d); d no se modifica

  esLleno(d: Diccionario) -> boolean          (auxiliar, no esta en la especificacion
    post: devuelve true si d alcanzo su         original: hace falta para escribir la
          capacidad maxima                      precondicion de definir)
```

Tres aclaraciones sobre la especificación:

- **`crear()` no aparece como método** en el código. Como dice la tabla de traducción de TDA a Java, la operación `crear()` es el **constructor** de cada implementación (Clase 1, diap. 27): `new DiccionarioDinamico()` y `new DiccionarioEstatico(capacidad)`.
- **`definir` tiene precondición** porque una de las dos implementaciones es estática y tiene capacidad fija. Si la clave ya está, `definir` sólo pisa el valor y no necesita lugar nuevo; por eso la precondición es "d no está lleno **o** clave ya pertenece a d". `esLleno` es la operación auxiliar que permite escribirla, igual que `esLlena` en la Pila y la Cola del TP1 (Clase 6, diap. 13 y 46).
- **`eliminar` tiene precondición** `existeClave`: eliminar algo que no está no tiene sentido, y las dos implementaciones lanzan `RuntimeException` si se viola, igual que `obtener` (Clase 4, diap. 23 y 24).

## Parte 2 — Implementación dinámica

`DiccionarioDinamico` guarda sólo la referencia a la **cabeza** de una cadena de `NodoDiccionario` (clave, valor, siguiente) y un contador de cuántas claves hay. No hay arreglo ni capacidad: cada clave nueva pide un nodo y nada más (Clase 4, diap. 19 y 20).

Los pares nuevos se enganchan **al principio** de la cadena, que es la inserción más barata cuando no hay ningún orden que mantener: el enunciado pide justamente que no se mantenga orden, ni por clave ni por inserción (Clase 4, diap. 22).

Tres de las operaciones que necesitan localizar una clave —`definir`, `obtener` y `existeClave`— reutilizan el auxiliar privado `buscarNodo(clave)`, que recorre la cadena desde la cabeza y devuelve el nodo o `null`. No es parte de la especificación: es interno, y existe para no repetir el mismo recorrido en cada una (Clase 4, diap. 21). `eliminar` no puede usarlo, porque además del nodo que saca necesita el **anterior** para reenganchar la cadena (Clase 4, diap. 24), así que hace su propio recorrido; en el código de clase pasa exactamente lo mismo.

### 2.1 Análisis de complejidad

n es la cantidad de claves del diccionario. Como pide la consigna, es el peor caso.

| Operación | Complejidad | Justificación |
|---|---|---|
| `crear()` | O(1) | Pone `cabeza` en `null` y `cantidad` en 0. No depende de n porque no hay nada que reservar ni recorrer. |
| `definir(d, clave, valor)` | O(n) | Primero llama a `buscarNodo`, que en el peor caso (clave nueva) recorre la cadena entera. El enganche posterior es O(1): tres asignaciones. El costo lo pone la búsqueda, no la inserción. |
| `obtener(d, clave)` | O(n) | `buscarNodo` otra vez: sin orden entre los nodos no hay forma de saltear ninguno, y la clave buscada puede ser la última de la cadena. |
| `eliminar(d, clave)` | O(n) | Recorre la cadena hasta encontrar la clave, recordando el nodo anterior para poder reenganchar. Desenganchar es O(1); lo caro es llegar. |
| `existeClave(d, clave)` | O(n) | Es `buscarNodo != null`: mismo recorrido. |
| `esVacio(d)` | O(1) | Compara `cabeza == null`. |
| `cantidadClaves(d)` | O(1) | Devuelve el contador, que se mantiene al día en `definir` y `eliminar`. Si no se guardara, habría que recorrer la cadena para contar y sería O(n) (Clase 4, diap. 20). |
| `claves(d)` | O(n) | Recorre la cadena una sola vez y agrega cada clave a la lista. Cada `agregar` es O(1) porque la `ListaDinamica` guarda también el último nodo; sin esa referencia habría que recorrer la lista en cada agregado y `claves` sería O(n²). |

Las cinco operaciones O(n) lo son por la misma razón: **una cadena de nodos sólo se puede recorrer desde la cabeza, de a un salto**. No hay acceso directo por posición como en un arreglo, y tampoco hay orden que permita descartar la mitad, como sí pasa en el conjunto ordenado con búsqueda binaria (Clase 6, diap. 42).

## Parte 3 — Comparación estática vs. dinámica

`DiccionarioEstatico` guarda lo mismo en **dos arreglos paralelos** de tamaño fijo: `clavesGuardadas[i]` y `valoresGuardados[i]` son la clave y el valor del mismo par. No mantiene ningún orden: las claves nuevas van al primer lugar libre, y al eliminar, el último par tapa el hueco, que es el truco que ya se usó en el Conjunto desordenado y en el Diccionario estático de clase (Clase 6, diap. 47 y 49).

Para que la comparación sea justa, las dos implementaciones resuelven el problema **con la misma estrategia**: buscar la clave recorriendo linealmente (`buscarNodo` en una, `claveAIndice` en la otra). Lo único que cambia es dónde están guardados los datos: un arreglo contiguo contra una cadena de nodos enlazados.

### 3.1 Medición empírica

Técnica: la de clase (`medirNanos` de `BigO_Tiempos.java`), o sea unas vueltas de warmup sin medir y después el **mejor tiempo** de varias repeticiones. Acá son 10 de warmup y el mejor de 20 repeticiones. Además, antes de la primera fila se hace una vuelta de calentamiento general con las dos implementaciones: sin eso la primera medición sale inflada y no es comparable con el resto (en la corrida sin calentar, `definir` estática con n = 1.000 daba 22,80 us contra los 2,60 us de la tabla).

Dos cuidados que pide la consigna:

- **`definir()`**: cada llamada medida usa una clave nueva, nunca antes vista (`clave-nueva-…`), así siempre se mide una inserción de verdad y no la actualización de un valor.
- **`obtener()`**: se busca la clave del **peor caso de cada implementación**, que no es la misma en las dos. La dinámica engancha cada par nuevo al principio de la cadena, así que la primera clave cargada (`clave-0`) queda última; la estática agrega al final del arreglo y lo recorre desde el principio, así que la peor es la última cargada (`clave-(n-1)`). En los dos casos se recorren las n claves: eso es lo que hace comparables los tiempos.

Medido con `java MedicionTiempos` (Java 21.0.11, Windows 11). Tiempos reales, no estimados:

| n | `definir()` — Estática | `definir()` — Dinámica | `obtener()` — Estática | `obtener()` — Dinámica |
|---|---|---|---|---|
| 1.000 | 2,60 us | 3,60 us | 5,70 us | 4,50 us |
| 10.000 | 19,60 us | 43,70 us | 38,60 us | 38,00 us |
| 100.000 | 274,10 us | 771,00 us | 546,60 us | 774,00 us |

Como dato extra, cargar las 100.000 claves (100.000 `definir` seguidos, o sea O(n²) en total) tardó 32,85 s con la estática y 44,01 s con la dinámica.

### 3.2 Conclusión

**¿Coincide la complejidad teórica con la tendencia medida?** Sí. En la Parte 2.1 las dos operaciones dieron O(n), y los tiempos crecen en proporción directa con n: cada vez que n se multiplica por 10, el tiempo se multiplica aproximadamente por 10 también.

| Salto | `definir()` Est. | `definir()` Din. | `obtener()` Est. | `obtener()` Din. |
|---|---|---|---|---|
| 1.000 → 10.000 | x7,5 | x12,1 | x6,8 | x8,4 |
| 10.000 → 100.000 | x14,0 | x17,6 | x14,2 | x20,4 |

No da exactamente x10 —eso sería el caso ideal— y el segundo salto crece un poco más que el primero. La explicación más probable es la misma que la del punto 5 de la Parte 0: con 1.000 claves la estructura entra entera en la caché del procesador y con 100.000 ya no, así que cada elemento recorrido cuesta un poco más. Es una hipótesis nuestra: no la medimos, y el material de clase no trata el tema (ver anexo, punto 10). La tendencia igual es claramente lineal: ninguna curva se aplana (no es O(1)) ni se dispara al cuadrado.

**¿Por qué una es más rápida que la otra si las dos son O(n)?** Con n = 100.000 la estática le gana a la dinámica en las dos operaciones: `definir` 274,10 us contra 771,00 us (casi 3 veces más rápido) y `obtener` 546,60 us contra 774,00 us. Las dos hacen el mismo recorrido lineal y la misma cantidad de comparaciones; lo que cambia es la constante que Big O descarta (Clase 6, diap. 56):

- **Localidad de referencia.** Es lo que el material llama "buena localidad de memoria": el arreglo es contiguo y eso ayuda al rendimiento real (Clase 2, diap. 16). La cadena de nodos, en cambio, está armada con objetos que el `new` fue dejando donde había lugar, así que cada `actual = actual.siguiente` puede saltar a otra zona de memoria. El porqué —el procesador trae la memoria de a bloques a la caché, y los saltos la desaprovechan— es explicación nuestra, no de las diapositivas (ver anexo, punto 10).
- **Overhead de memoria por elemento.** Cada nodo son unos 16-24 bytes entre el encabezado del objeto, el dato y la referencia, contra los 4 bytes de un `int` en un arreglo (Clase 6, diap. 58). Más bytes recorridos para la misma cantidad de claves es más memoria que traer.
- **Un salto de referencia extra por elemento.** El arreglo avanza sumando 1 a un índice; la cadena tiene que leer el campo `siguiente` de cada nodo antes de poder seguir.

Esto se ve todavía mejor en la carga de las 100.000 claves: 32,85 s contra 44,01 s, el mismo algoritmo con la misma cantidad de comparaciones.

Con n = 1.000 los tiempos son prácticamente iguales (incluso `obtener` dinámica salió un poco más rápida). Es lo esperable: a esa escala todo entra en la caché, las constantes se emparejan y la diferencia queda dentro del ruido de la medición.

**Lo que no dice la tabla.** La estática paga esa ventaja con la limitación de siempre: hay que fijar la capacidad al crearla y no se puede pasar de ahí, mientras que la dinámica crece de a un nodo sin estimar nada (Clase 2, diap. 16; Clase 6, diap. 22). Ninguna de las dos es "mejor" en abstracto: la elección depende de si conviene más la velocidad o la flexibilidad, que es el mismo criterio de "depende del uso" de la Clase 3 (diap. 10).

## Parte 4 — Utilización

Los cuatro métodos están en `MetodosDiccionario` y usan **sólo** operaciones de la interfaz `Diccionario`: les alcanza con `claves`, `obtener` y `definir`, y no necesitan ninguna otra. No tocan ni los nodos ni los arreglos internos, así que corren igual con la implementación dinámica y con la estática. El `Main` los prueba con las dos.

Los diccionarios que devuelven son **dinámicos**: como no se sabe de antemano cuántas claves van a entrar, una implementación sin capacidad fija evita tener que estimarla (Clase 2, diap. 16).

En todos los costos, n es la cantidad de claves de los diccionarios involucrados.

| Método | Complejidad | Justificación |
|---|---|---|
| `combinarDiccionarios(d1, d2)` | O(n²) | Recorre las claves de los dos diccionarios (2n vueltas) y en cada vuelta hace un `obtener` sobre el origen y un `definir` sobre el resultado, que son O(n) cada uno. Además, leer la lista de claves por posición cuesta O(i) por acceso, porque `obtener(i)` recorre la cadena desde la cabeza. |
| `invertir(d)` | O(n²) | Misma forma: n vueltas, y en cada una un `obtener` O(n) sobre d y un `definir` O(n) sobre el resultado. |
| `contarValoresMayoresA(d, umbral)` | O(n²) | n vueltas y en cada una un `obtener` O(n). La comparación con el umbral es O(1). |
| `clavesOrdenadas(d)` | O(n²) | El ordenamiento por inserción hace O(n²) comparaciones en el peor caso (claves al revés), y leer las claves de la lista por posición cuesta otro O(n²). Armar la lista del resultado es O(n), porque cada `agregar` es O(1). |

Esto es exactamente lo que advierte el enunciado: **cada operación del TDA es O(n), pero llamarla dentro de un bucle que recorre las n claves da O(n²)**, no O(n). En `combinarDiccionarios`, `invertir` y `contarValoresMayoresA` el costo no está en la lógica —cada uno hace una sola pasada por las claves— sino en que cada operación del Diccionario tiene que buscar la clave recorriendo la estructura. `clavesOrdenadas` es distinto: ahí el O(n²) lo pone el ordenamiento por inserción, con su bucle de corrimiento adentro del recorrido.

Aun si el Diccionario tuviera un `obtener` de costo constante, los cuatro métodos seguirían siendo O(n²): leer la lista de claves por posición ya cuesta O(n²) en total, porque cada `obtener(i)` de la Lista recorre la cadena desde la cabeza. Para bajar de ahí habría que poder recorrer la Lista de corrido, en vez de pedirle un índice por vez.

Detalles de cada uno:

- **`combinarDiccionarios`**: copia primero los pares de `d1` y después los de `d2`; como `definir` pisa el valor de una clave que ya estaba, las claves repetidas quedan con el valor de `d2`, que es lo que pide la consigna. Ninguno de los dos diccionarios de entrada se modifica: sólo se les pregunta.
- **`invertir`**: por cada clave define el par al revés en el diccionario nuevo. Se asume, como dice el enunciado, que los valores originales también son únicos; si no lo fueran, dos claves distintas con el mismo valor colapsarían en una sola y se perdería una.
- **`contarValoresMayoresA`**: asume valores enteros, así que los convierte a `Integer` para compararlos con el umbral. La comparación es estricta (`>`).
- **`clavesOrdenadas`**: asume claves de texto y las ordena alfabéticamente con `compareTo`, usando ordenamiento por inserción: el mismo corrimiento que hace `encolar` en la cola de prioridad ordenada (Clase 6, diap. 35). Devuelve una `ListaDinamica` nueva; `d` no se toca.

## Resumen de costos

| Operación o método | Dinámica | Estática |
|---|---|---|
| `crear()` | O(1) | O(1) |
| `definir(d, clave, valor)` | O(n) | O(n) |
| `obtener(d, clave)` | O(n) | O(n) |
| `eliminar(d, clave)` | O(n) | O(n) |
| `existeClave(d, clave)` | O(n) | O(n) |
| `esVacio(d)` | O(1) | O(1) |
| `cantidadClaves(d)` | O(1) | O(1) |
| `claves(d)` | O(n) | O(n) |
| `esLleno(d)` | O(1) | O(1) |
| `combinarDiccionarios`, `invertir`, `contarValoresMayoresA`, `clavesOrdenadas` | O(n²) | O(n²) |

Las dos implementaciones caen en la misma clase de complejidad en todas las operaciones, porque las dos resuelven el problema igual: buscar la clave recorriendo linealmente. Lo que las diferencia no es el orden de crecimiento sino la constante, y eso es justamente lo que mide la Parte 3.

## Anexo — Base en el material de clase

Todo el TP sale de la carpeta `Clases`. De dónde viene cada parte:

| Carpeta | Qué se tomó |
|---|---|
| `CLASE 01 03-08` (`Clase1 .pptx`) | El formato de especificación: dominio, operaciones con signatura, pre y post (diap. 11 y 12). La tabla de traducción de TDA a Java: el dominio son los atributos privados, `crear()` es el constructor, cada operación es un método de instancia y cada precondición es una validación con excepción al principio del método (diap. 27). |
| `CLASE 04 24-08` (`ACTIVIDAD 6 Big O/BigO_Tiempos.java`) | La técnica de medición `medirNanos`: warmup y mejor tiempo de varias repeticiones, con la interfaz `Operacion` para poder pasar la operación a medir. También el `formatear` de nanosegundos. |
| `CLASE 05 31-08` (`Clase4.pptx`, `NodoDiccionario.java`, `DiccionarioDinamico.java`) | Todo el Diccionario dinámico: qué es un nodo y qué guarda (diap. 18 y 19), la clase con `cabeza` y contador (diap. 20), el auxiliar `buscarNodo` (diap. 21), `existeClave`/`definir` con el enganche al principio de la cadena (diap. 22), `obtener` y `eliminar` con su precondición y su excepción (diap. 23 y 24), `esVacio`/`cantidadClaves`/`claves` (diap. 25), y por qué hace falta `existeClave` antes de `definir` (diap. 15). |
| `CLASE 06 07-09` (`Clase6_ar.pptx`, `DICCIONARIO CODIGO/DiccionarioEstatico.java`) | La implementación estática: estructura con arreglo, `cantidad` y `capacidad` (diap. 13 y 48), el par clave-valor guardado en paralelo, el truco de tapar el hueco con el último al eliminar (diap. 47 y 49), `esVacio`/`esLleno` (diap. 51), el TDA Diccionario con `esLleno` (diap. 46), las reglas de Big O y la convención de peor caso (diap. 56), la tabla resumen (diap. 57) y la comparación de memoria entre arreglo y nodo (diap. 58). El corrimiento del ordenamiento por inserción sale de la cola de prioridad ordenada (diap. 35). |
| `CLASE 03 22-08 REMOTA` (`Clase3.pptx`) | El criterio de "depende del uso" para elegir implementación (diap. 10) y el recorrido lineal de `pertenece` (diap. 15). |
| `CLASE 02 10-08` (`Clase2.pptx`) | Ventajas y limitaciones de la implementación estática: acceso directo y buena localidad de memoria contra tamaño fijo y desperdicio (diap. 16). |

### Lo que no está en las diapositivas y por qué

1. **La interfaz `Diccionario`.** Las diapositivas no muestran una interfaz para el Diccionario, pero el código de la Clase 6 sí la usa para la Pila (carpeta `PILA CODIGO/PILA CON INTERFAZ`), con la misma idea: la interfaz es el contrato y las implementaciones son intercambiables. Acá es necesaria porque el enunciado pide que la Parte 4 funcione sin cambios con las dos implementaciones.
2. **`ListaDinamica` y `NodoLista`.** El enunciado pide que `claves()` devuelva una Lista y que `clavesOrdenadas()` devuelva "la Lista dinámica vista en la Clase 5", pero en la carpeta `Clases` no hay ninguna Lista: la Clase 5 no tiene presentación propia y el `DiccionarioDinamico.java` de clase devuelve `Object[]`, aclarando en un comentario que "la Lista dinámica genérica se retoma en los TPs". Así que se implementó con la misma idea de nodo del material (dato + siguiente), y con lo mínimo necesario: `agregar`, `obtener`, `cantidad` y `esVacia`.
3. **La referencia `ultimo` de la Lista.** No está en clase. Sirve para que `agregar` sea O(1) y `claves()` quede en O(n); sin ella habría que recorrer la lista entera en cada agregado y `claves()` sería O(n²).
4. **`esLleno` en la interfaz.** Está en el TDA Diccionario de la Clase 6 (diap. 46) pero no en la lista mínima del enunciado. Hace falta para escribir la precondición de `definir`, porque una de las implementaciones tiene capacidad fija. No se cuenta entre las 8 operaciones de la tabla 2.1, que sigue exactamente la lista del enunciado.
5. **`eliminar` unificado.** La Clase 4 lanza excepción si la clave no existe (diap. 24 y `DiccionarioDinamico.java`); la Clase 6 no hace nada (diap. 49). Las dos implementaciones tienen que comportarse igual para que la Parte 4 corra con cualquiera, así que se tomó el criterio de la Clase 4, que además es el que respeta la precondición `existeClave`.
6. **Búsqueda hacia adelante en la estática.** El `claveAIndice` de la Clase 6 recorre el arreglo desde el final; acá se recorre desde el principio, como el `pertenece` del Conjunto (Clase 3, diap. 15). El costo es el mismo, O(n), y así el peor caso de la estática es la última clave cargada, distinto del peor caso de la cadena dinámica: es justo la comparación que pide la Parte 3.1.
7. **Ordenamiento por inserción en `clavesOrdenadas`.** En las diapositivas no hay ningún algoritmo de ordenamiento; el enunciado permite "cualquier algoritmo de ordenamiento simple". Se usa el mismo corrimiento que `encolar` de la cola de prioridad ordenada (Clase 6, diap. 35).
8. **El calentamiento general antes de medir.** `medirNanos` ya hace warmup en cada medición, pero la primera medición del programa igual salía inflada, midiendo el arranque de la JVM y no la estructura. Por eso se agregó una vuelta previa con las dos implementaciones; está explicado en la Parte 3.1 con los dos números.
9. **El contador de OK y ERROR del `Main`.** Las demos de clase imprimen "(esperado X)" al lado de cada resultado; el contador final es un agregado nuestro, para ver de un vistazo si algo falló.
10. **La explicación de la caché del procesador.** El material dice que el arreglo tiene "buena localidad de memoria" por ser contiguo (Clase 2, diap. 16; Clase 6, diap. 22) y da el costo en bytes de un nodo contra un `int` en un arreglo (Clase 6, diap. 58), pero en ninguna diapositiva aparece el mecanismo: líneas de caché, fallos de caché, ni que una estructura chica entre entera en la caché. Eso lo agregamos nosotros para explicar los tiempos de la Parte 3, y está marcado como agregado en cada lugar donde aparece. El enunciado pide hablar del tema ("lo visto en la Clase 5 sobre localidad de referencia"), pero la Clase 5 no tiene presentación en la carpeta `Clases`.
11. **Claves y valores como `Object`.** Es lo que usa el nodo de la Clase 4 (`Object clave; Object valor;`). Sobre esa base, `contarValoresMayoresA` asume valores enteros y `clavesOrdenadas` asume claves de texto, tal como aclara el enunciado en cada caso.
