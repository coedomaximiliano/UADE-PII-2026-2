# TP3 — TDA Árbol Binario de Búsqueda

**Algoritmos y Estructuras de Datos II — Informe**

**Alumnos:** Pedro Stella — Bautista Chavarri

Especificación del TDA Árbol Binario de Búsqueda, implementación dinámica con nodos enlazados, los cuatro recorridos y los métodos de utilización recursivos.

## Cómo compilar y ejecutar

Requiere JDK 21. Todas las clases están en el paquete por defecto y no usan bibliotecas: el árbol y sus nodos son propios, sin nada de `java.util`. Desde la carpeta del TP:

```
javac *.java
java Main
```

| Archivo | Qué es |
|---|---|
| `NodoArbol` | nodo del árbol: dato, hijo izquierdo y hijo derecho (Parte 2) |
| `ArbolBinarioBusqueda` | el ABB: `insertar`, `pertenece`, `esVacio`, `cantidadNodos` y los cuatro recorridos (Partes 2 y 3) |
| `ColaNodos` | la Cola circular de los TP anteriores, adaptada para encolar nodos; la usa el recorrido por niveles (Parte 3.2) |
| `MetodosArbol` | `altura`, `contarHojas`, `sumaNodos` y `esABB` (Parte 4) |
| `Main` | imprime los cuatro recorridos del árbol de ejemplo y verifica todo: cada línea muestra lo obtenido, lo esperado y OK o ERROR |

`Main` termina con el resumen `Verificaciones: 42 OK, 0 con error`.

## Parte 1 — Especificación del TDA Árbol Binario de Búsqueda

### 1.1 Especificación

```text
TDA Arbol Binario de Busqueda (ABB)

Dominio: ABB = conjunto finito de elementos comparables, sin repetidos, organizados en
         nodos con a lo sumo dos hijos (izquierdo y derecho), donde para todo nodo del
         arbol se cumple que todos los elementos de su subarbol izquierdo son menores
         que el elemento del nodo, y todos los de su subarbol derecho son mayores.

Operaciones:

  crear() -> Arbol
    post: devuelve un arbol vacio

  insertar(a: Arbol, x: elemento) -> Arbol
    post: x pertenece a a y se sigue cumpliendo la propiedad de ABB; si x ya pertenecia
          a a, a queda igual (no se admiten repetidos)

  pertenece(a: Arbol, x: elemento) -> boolean
    post: devuelve true si x pertenece a a; a no se modifica

  eliminar(a: Arbol, x: elemento) -> Arbol
    post: x ya no pertenece a a; los demas elementos siguen perteneciendo a a y se sigue
          cumpliendo la propiedad de ABB; si x no pertenecia a a, a queda igual

  esVacio(a: Arbol) -> boolean
    post: devuelve true si a no tiene elementos

  cantidadNodos(a: Arbol) -> entero
    post: devuelve la cantidad de elementos de a
```

### Sobre `eliminar` y el caso de los dos hijos

`eliminar` no se implementa en este TP, pero la postcondición tiene que ser precisa, porque sacar un nodo puede romper la propiedad de ABB. Hay tres situaciones:

- **El nodo es una hoja:** se lo saca y listo, no queda nada colgando.
- **El nodo tiene un solo hijo:** ese hijo (con todo su subárbol) ocupa el lugar del nodo eliminado. Sigue cumpliendo la propiedad, porque todo ese subárbol ya estaba del lado correcto respecto de los ancestros.
- **El nodo tiene dos hijos:** no alcanza con borrarlo, porque quedarían dos subárboles sueltos y un solo lugar. Hay que reemplazar el valor del nodo por **el máximo de su subárbol izquierdo** (su predecesor inmediato) o por **el mínimo de su subárbol derecho** (su sucesor inmediato), y después eliminar ese otro nodo, que por ser el extremo de su subárbol tiene a lo sumo un hijo, o sea que cae en alguno de los dos casos anteriores.

Se usa justamente el predecesor o el sucesor porque son los únicos dos valores que siguen dejando a todo el subárbol izquierdo por debajo y a todo el derecho por encima: cualquier otro valor del árbol rompería la propiedad de alguno de los dos lados.

## Parte 2 — Implementación

`NodoArbol` guarda el dato y dos referencias, `izquierdo` y `derecho`, que valen `null` cuando ese hijo no existe. `ArbolBinarioBusqueda` guarda una única referencia a la raíz, `null` si el árbol está vacío, que es exactamente lo que pide el enunciado: desde la raíz se llega a todo el árbol bajando por los hijos.

`insertar` y `pertenece` son recursivos y en cada llamada comparan `x` contra un solo nodo, con trabajo constante: si `x` es menor que el dato del nodo siguen por el subárbol izquierdo y si es mayor por el derecho, así que **descartan el otro subárbol entero**. Nunca hace falta mirar los dos lados en la misma llamada, y ahí está toda la ventaja de un ABB frente a una lista.

`insertar` devuelve el subárbol ya actualizado y el llamador lo vuelve a enganchar (`actual.izquierdo = insertarRec(actual.izquierdo, x)`). Cuando la recursión llega a un `null`, ese hueco es el lugar que le corresponde a `x`: se crea el nodo y se lo devuelve, para que el padre lo enganche. Si el valor ya estaba, la llamada no hace nada y el árbol queda igual, que es lo que pide la consigna sobre los repetidos.

`esVacio` compara la raíz contra `null`, así que es O(1). `cantidadNodos` cuenta recorriendo el árbol, O(n). En el Diccionario dinámico de clase se guardaba además un contador para tener la cantidad en O(1) (Clase 4, diap. 20), pero el enunciado pide que `ArbolBinarioBusqueda` tenga una única referencia a la raíz, así que no se agrega ese atributo y la cantidad se cuenta sobre la estructura.

### 2.2 Análisis de complejidad

**a) Con el árbol balanceado, `insertar` y `pertenece` son O(log n).** Las dos operaciones bajan un nivel por llamada y en cada nivel hacen trabajo constante: una comparación y seguir por un lado. Entonces el costo no es la cantidad de nodos sino **la cantidad de niveles que hay que bajar**, o sea la altura del árbol. Por eso la complejidad se mide contra la altura y no contra n: de los n nodos, una búsqueda visita a lo sumo uno por nivel y nunca toca el resto. Cuando el árbol está balanceado la altura es aproximadamente log₂(n) —cada nivel tiene el doble de nodos que el anterior, así que con h niveles entran del orden de 2^h nodos— y de ahí sale el O(log n). Es la misma idea de la búsqueda binaria: en cada paso se descarta la mitad de lo que queda por revisar (Clase 6, diap. 42).

**b) En el peor caso son O(n).** El peor caso es un árbol **degenerado**, donde cada nodo tiene a lo sumo un hijo: ahí la altura es n y bajar hasta el fondo cuesta lo mismo que recorrer una lista enlazada. Una secuencia que lo produce es insertar los valores **ya ordenados**, por ejemplo 10, 20, 30, 40, 50: cada valor nuevo es mayor que todos los anteriores, así que en cada comparación se va siempre para la derecha y termina colgado como hijo derecho del último insertado. El árbol queda como una rama única hacia la derecha. Insertar en orden decreciente da lo mismo, pero hacia la izquierda. El `Main` lo verifica: con esos cinco valores, `altura()` da 5 con 5 nodos.

Hay una consecuencia práctica del árbol degenerado que no se ve en el Big O: como todos los métodos son recursivos y la profundidad de la recursión es la altura, un árbol degenerado consume también O(n) de pila de llamadas. Probándolo con la pila por defecto de la JVM, insertar valores en orden llega hasta unos 17.000 nodos y a partir de unos 18.000 salta `StackOverflowError`; recorrer o contar ese mismo árbol ya falla antes, entre 10.000 y 12.000 nodos. El número exacto depende de la JVM y de la máquina. El recorrido por niveles, aunque es iterativo, hereda ese límite, porque dimensiona su cola con `cantidadNodos()`, que es recursivo. Con árboles armados con valores desordenados —altura del orden de log n— no pasa ni de cerca. Es otro argumento a favor de los árboles que se rebalancean solos.

**c) Sí, pero sólo teniendo todos los valores de antemano: si llegan de a uno en un orden que no se controla, no hay ninguna.** Un ABB común no se rebalancea solo: la forma del árbol la decide el orden en que se insertan los valores, y si los valores llegan de a uno en un orden que no se controla, el árbol puede degenerar. Lo que sí se puede es, **teniendo todos los valores de antemano**, ordenarlos e insertar siempre el del medio de cada mitad, de forma recursiva: eso reparte los valores en dos mitades parejas en cada paso y garantiza un árbol balanceado, sin importar cuáles sean los valores concretos. Con los mismos 10, 20, 30, 40, 50 del punto anterior, insertados en el orden 30, 20, 40, 10, 50, la altura baja de 5 a 3 (también verificado en el `Main`). Para mantener el balanceo con inserciones que llegan en cualquier orden hacen falta árboles que se rebalancean solos, que se ven más adelante en la materia.

## Parte 3 — Recorridos

Los tres recorridos en profundidad son recursivos y se diferencian sólo en **cuándo** se procesa el nodo: antes de los hijos (preorder), entre los dos (inorder) o después de los dos (postorder). El recorrido por niveles no sale con recursión simple, porque hay que procesar los nodos en el orden en que se descubren, y para eso se usa la `ColaNodos`: se encola la raíz y, mientras la cola no esté vacía, se desencola un nodo, se lo imprime y se encolan sus hijos.

**Por qué el inorder de un ABB sale en orden creciente.** Por la propiedad del ABB, todo lo que está en el subárbol izquierdo de un nodo es menor que él, y todo lo del derecho es mayor. El inorder imprime primero todo el subárbol izquierdo, después el nodo y después todo el derecho: o sea, primero todos los menores, luego el valor del nodo y luego todos los mayores. Y como eso mismo vale dentro de cada subárbol, aplicado recursivamente, el resultado completo queda ordenado de menor a mayor.

### Salida de los cuatro recorridos

Árbol construido insertando 50, 30, 70, 20, 40, 60, 80, 10, 25, 65, 90:

```
          50
        /    \
      30      70
     /  \    /  \
   20    40 60   80
  /  \      \      \
10    25     65     90
```

Salida real de `java Main`:

```
inorder():             10 20 25 30 40 50 60 65 70 80 90
preorder():            50 30 20 10 25 40 70 60 65 80 90
postorder():           10 25 20 40 30 65 60 90 80 70 50
recorridoPorNiveles(): 50 30 70 20 40 60 80 10 25 65 90
```

Los cuatro recorridos son O(n): visitan cada nodo exactamente una vez. El de niveles, además, usa la cola, cuyas operaciones son O(1). La cola se crea con capacidad `cantidadNodos()`, porque nunca hay más nodos esperando que los que tiene el árbol; contarlos es una pasada más por el árbol, O(n), así que el total sigue siendo O(n).

## Parte 4 — Utilización

Los cuatro métodos están en `MetodosArbol` y son recursivos. Los tres primeros reciben el árbol y arrancan desde su raíz; `esABB` trabaja directamente sobre un `NodoArbol`, porque tiene que poder revisar árboles armados a mano que quizá no cumplan la propiedad.

| Método | Complejidad | Justificación |
|---|---|---|
| `altura(a)` | O(n) | Para saber cuál es el camino más largo hay que mirar los dos lados de cada nodo, así que visita los n nodos una vez y en cada uno hace trabajo constante: comparar las dos alturas y sumar uno. |
| `contarHojas(a)` | O(n) | Misma forma: baja por los dos subárboles de cada nodo. No se puede podar nada, porque una hoja puede estar en cualquier rama. |
| `sumaNodos(a)` | O(n) | Suma el dato de cada nodo una sola vez; visita los n nodos. Acumula en `int`, como dice la signatura del enunciado (`sumaNodos(a: Arbol): entero`), así que el resultado vale mientras la suma entre en un `int`. |
| `esABB(a)` | O(n) | Cada nodo se visita una vez y se lo compara contra los dos límites que baja la recursión: dos comparaciones por nodo, o sea trabajo constante. Si encuentra una violación corta antes, pero el peor caso es recorrer todo. |

A diferencia de `pertenece`, que descarta medio árbol en cada paso, estos cuatro métodos **no pueden descartar nada**: la respuesta depende de todos los nodos, así que son O(n) tanto con el árbol balanceado como degenerado.

### `esABB` y el nodo que cumple con su padre pero no con su abuelo

No alcanza con comparar cada nodo contra sus dos hijos directos. Por ejemplo, con tres niveles:

```
      50
     /  \
   30    70
     \
      60
```

`60` es hijo derecho de `30` y es mayor que `30`, así que mirando sólo al padre parece correcto. Pero `60` está en el **subárbol izquierdo de 50** y es mayor que `50`: viola la propiedad respecto de un ancestro más lejano. Con ese árbol, una solución que compare sólo padre e hijos contestaría `true`, que es justamente el error.

La solución baja en cada llamada el **rango de valores permitidos**. La raíz arranca sin límites; al ir hacia la izquierda, el dato del nodo pasa a ser el máximo permitido; al ir hacia la derecha, pasa a ser el mínimo. Así, cuando la recursión llega a `60`, el rango que arrastra es "mayor que 30 y menor que 50", y `60` no entra: devuelve `false`. Los límites se guardan en `long` para poder arrancar con `Long.MIN_VALUE` y `Long.MAX_VALUE` como "sin límite", y que cualquier `int` caiga siempre dentro del rango.

El `Main` prueba exactamente ese árbol, más el mismo con `35` en lugar de `60` (que sí es un ABB), uno con un hijo izquierdo mayor que su padre, uno con un valor repetido, un solo nodo y el árbol vacío.

## Resumen de costos

| Operación o método | Complejidad |
|---|---|
| `crear()` | O(1) |
| `insertar(a, x)`, `pertenece(a, x)` — árbol balanceado | O(log n) |
| `insertar(a, x)`, `pertenece(a, x)` — peor caso (árbol degenerado) | O(n) |
| `esVacio(a)` | O(1) |
| `cantidadNodos(a)` | O(n) |
| `inorder`, `preorder`, `postorder`, `recorridoPorNiveles` | O(n) |
| `altura`, `contarHojas`, `sumaNodos`, `esABB` | O(n) |
| `encolar`, `desencolar`, `frente` de `ColaNodos` | O(1) |

## Anexo — Base en el material de clase

**Todavía no hay material de árboles**: las presentaciones de clase llegan hasta la Clase 6 (Pila, Cola, Cola con Prioridad, Conjunto, Diccionario y Big O), y en el repositorio de la cátedra las carpetas de las clases 7 en adelante están creadas pero vacías. Así que la estructura de este TP sale del enunciado, y la forma de escribirlo sigue las convenciones de las clases anteriores:

Los números de diapositiva son los del panel de PowerPoint; el número impreso al pie de la diapositiva puede diferir en hasta tres en las primeras clases.

| Material | Qué se tomó |
|---|---|
| Clase 1 (`Clase1 .pptx`) | El formato de especificación: dominio, operaciones con signatura y pre/post (diap. 11). La traducción de TDA a Java: `crear()` es el constructor, cada operación es un método de instancia y cada precondición se valida al principio del método (diap. 27). |
| Clase 2 (`Clase2.pptx`) | La cola circular con `% capacidad` (diap. 24), que es la base de `ColaNodos`, y el ejemplo de búsqueda binaria como caso de O(log n) (diap. 19). |
| Clase 4 (`Clase4.pptx` y el código `NodoDiccionario.java`, `DiccionarioDinamico.java` y `PilaConExcepcion.java`, en la carpeta `CLASE 05 31-08`) | La idea de nodo de una estructura dinámica: un objeto con el dato y una referencia al siguiente (diap. 18 y 19), y la clase que guarda la cabeza de la cadena (diap. 20, donde además guarda un contador que acá no se usa, porque el enunciado pide una única referencia a la raíz). Acá el nodo tiene dos referencias en vez de una y la clase guarda la raíz en vez de la cabeza. También el armado de texto con `StringBuilder` que usa `DiccionarioDinamico` para mostrar la estructura, y el validar la precondición al principio del método lanzando `RuntimeException` con un mensaje claro (`PilaConExcepcion.java`), que es lo que hace `ColaNodos`. |
| Clase 6 (`Clase6_ar.pptx`) | La cola estática con `frente` y `cantidad` (diap. 28), la búsqueda binaria como ejemplo de O(log n) (diap. 42), las reglas de Big O y la convención de reportar el peor caso (diap. 56) y la tabla resumen de costos (diap. 57). |

### Lo que no está en las diapositivas y por qué

1. **Todo el ABB.** No hay material de árboles en la carpeta. La estructura (`NodoArbol` con dos hijos, `ArbolBinarioBusqueda` con la raíz, `insertar` y `pertenece` recursivos) sale directamente de lo que describe el enunciado.
2. **`ColaNodos`.** El enunciado pide usar "el TDA Cola visto en el TP2, adaptado para encolar nodos del árbol". En nuestras entregas la Cola está en el **TP1** (`ColaCircular`), no en el TP2, que fue de Diccionario; `ColaNodos` es esa misma cola circular con `NodoArbol` en lugar de `int`.
3. **`raiz()`.** La especificación del TDA no expone la raíz, pero los métodos de la Parte 4 son recursivos sobre nodos y el enunciado pide que estén en otra clase, así que necesitan ese acceso mínimo. `esABB` directamente recibe un `NodoArbol`, como pide la consigna.
4. **Las versiones `...Texto()` de los recorridos.** Cada recorrido imprime, como pide el enunciado; el texto lo arma un método que devuelve lo mismo que se imprime, para que el `Main` pueda comparar cada recorrido con lo esperado en vez de revisar la salida a ojo.
5. **Los límites en `long` de `esABB`.** Es para tener un "sin límite" seguro (`Long.MIN_VALUE` y `Long.MAX_VALUE`): si los límites fueran `int`, un nodo con el valor `Integer.MIN_VALUE` daría falso negativo.
6. **El contador de OK y ERROR del `Main`.** Las demos de clase imprimen "(esperado X)" al lado de cada resultado; el contador final es un agregado nuestro, para ver de un vistazo si algo falló.
