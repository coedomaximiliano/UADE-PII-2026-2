# TP3 - TDA Árbol Binario de Búsqueda

## Ejecución

Requiere Java 17. Desde la carpeta del proyecto, en PowerShell:

```powershell
.\gradlew.bat bootRun
.\gradlew.bat test
```

También se puede ejecutar `com.example.tp3.Tp3Application` desde el IDE.
El programa es una demostración por consola. Se conserva la configuración Gradle
original, pero los algoritmos no dependen de Spring ni de colecciones de Java.

Archivos de la solución, en `src/main/java/com/example/tp3/`:

- `NodoArbol.java`: dato entero y referencias a los dos hijos.
- `ArbolBinarioBusqueda.java`: raíz, inserción, búsqueda, conteo y recorridos.
- `Cola.java`: FIFO enlazada de nodos, con encolar y desencolar en O(1).
- `UtilizacionArbol.java`: los cuatro métodos recursivos de la Parte 4.
- `Tp3Application.java`: demostración con el árbol del enunciado y un árbol inválido.

No se incluyó en este proyecto el código del TP2: la cola entregada implementa
el TDA FIFO con nodos enlazados y está adaptada para almacenar NodoArbol.

## Parte 1. Especificación

### Dominio e invariante

Arbol es el conjunto de árboles binarios finitos de elementos enteros distintos.
Cada nodo tiene un dato y hasta dos hijos. Para todo nodo, todos los datos de su
subárbol izquierdo son estrictamente menores y todos los del derecho son
estrictamente mayores. Un árbol vacío se representa por una raíz null.

Sea E(a) el conjunto de elementos de a. Se escribe a₀ para el estado previo y a'
para el posterior. En toda operación que recibe un Arbol se presupone una
referencia no nula a un ABB válido. Ninguna operación modifica otros árboles.
Los enteros de la implementación son valores Java int.

### crear() -> Arbol

Precondición: ninguna.
Postcondición: devuelve un árbol nuevo a con E(a) = conjunto vacío y raíz null.

### insertar(a: Arbol, x: entero) -> Arbol

Precondición: a es un ABB válido y x pertenece al dominio de los elementos.
Postcondición: E(a') = E(a₀) unión {x}; se preserva el invariante de ABB.
Si x ya pertenece, el árbol no cambia, incluyendo su estructura.
Si no pertenece, se agrega una hoja en la posición alcanzada al comparar x con
cada nodo, descendiendo a izquierda si es menor y a derecha si es mayor.
Se devuelve el árbol actualizado. En Java se expresa como `a.insertar(x)`,
que modifica el receptor y retorna void.

### pertenece(a: Arbol, x: entero) -> boolean

Precondición: a es un ABB válido y x pertenece al dominio de los elementos.
Postcondición: devuelve verdadero si y solo si x pertenece a E(a). No modifica a.

### eliminar(a: Arbol, x: entero) -> Arbol

Precondición: a es un ABB válido y x pertenece al dominio de los elementos.
Postcondición: E(a') = E(a₀) menos {x}; se preserva el invariante y se devuelve
el árbol actualizado. Si x no pertenece, a queda sin cambios.
Para precisar el cambio estructural cuando x existe:

- Sin hijos: se reemplaza su enlace por null.
- Con un hijo: se reemplaza su enlace por el hijo existente.
- Con dos hijos: se reemplaza su dato por el sucesor inorder, el menor dato del
  subárbol derecho, y se elimina el nodo que contenía ese sucesor. Ese nodo no
  tiene hijo izquierdo y se reemplaza por su hijo derecho, si existe.

El enlace mencionado puede ser la raíz. El sucesor es mayor que todos los datos
del subárbol izquierdo y menor que todos los datos restantes del derecho;
por eso se preserva el orden estricto. La cantidad disminuye en uno únicamente
si x existía. Esta operación se especifica pero no se implementa, tal como pide
la aclaración de la Parte 2.

### esVacio(a: Arbol) -> boolean

Precondición: a es un ABB válido.
Postcondición: devuelve verdadero si y solo si E(a) es vacío. No modifica a.

### cantidadNodos(a: Arbol) -> entero

Precondición: a es un ABB válido.
Postcondición: devuelve |E(a)|; para el árbol vacío devuelve 0. No modifica a.

## Parte 2. Complejidades

Sea n la cantidad de nodos y h la altura medida en nodos. Insertar y pertenece
realizan trabajo constante por llamada y descienden por un solo camino:
su tiempo es O(h + 1) y su espacio auxiliar recursivo O(h + 1). En un árbol
no vacío se escribe habitualmente O(h). Buscar la raíz o detectar un duplicado
en ella cuesta O(1); los límites siguientes son los peores casos de cada forma.

### 1. Árbol balanceado

Con h = Θ(log n), ambas operaciones cuestan O(log n), para n mayor que 1.
La propiedad de ABB descarta un subárbol completo con cada comparación.
No se visitan todos los nodos: se visitan como máximo los de un camino cuya
longitud está determinada por la altura. En árboles vacíos o de un nodo, O(1).

### 2. Peor caso

Insertar 1, 2, 3, ..., n genera una cadena hacia la derecha: cada nuevo elemento
es mayor que todos los anteriores, por lo que se desciende hasta el último.
La secuencia inversa produce una cadena hacia la izquierda. En ambos casos
h = n, y buscar el extremo, buscar un valor ausente más allá del extremo o
insertar el siguiente valor cuesta Θ(n). Construir toda la cadena cuesta Θ(n²),
pues se suman recorridos de longitudes 0, 1, ..., n - 1.

### 3. Secuencia que garantice balance

Una permutación fija de posiciones que ignore las comparaciones entre los datos
no garantiza balance para valores arbitrarios: se pueden asignar valores
crecientes en ese orden de inserción y obtener una cadena.

Si todos los valores distintos se conocen previamente, sí hay una estrategia
que funciona para cualquier conjunto: ordenarlos, insertar primero la mediana
y luego repetir recursivamente con las mitades inferior y superior. Por ejemplo,
para 1..7: 4, 2, 1, 3, 6, 5, 7. Cada partición deja tamaños que difieren a lo sumo
en uno, de modo que se obtiene altura Θ(log n). El orden depende del orden
relativo de los valores, no de sus magnitudes. Esto no garantiza que inserciones
futuras arbitrarias mantengan el balance; el ABB implementado no se autoequilibra.

Además, esVacio cuesta O(1) y cantidadNodos cuesta Θ(n), con espacio auxiliar
O(h), porque no se almacena un contador adicional en el árbol.

## Parte 3. Recorridos

Entrada: 50, 30, 70, 20, 40, 60, 80, 10, 25, 65, 90.

```text
Inorder: 10 20 25 30 40 50 60 65 70 80 90
Preorder: 50 30 20 10 25 40 70 60 65 80 90
Postorder: 10 25 20 40 30 65 60 90 80 70 50
Por niveles: 50 30 70 20 40 60 80 10 25 65 90
```

Inorder imprime en orden creciente: por la propiedad del ABB todos los valores
izquierdos son menores que la raíz y los derechos son mayores. Aplicando el
mismo razonamiento recursivamente, cada subárbol se imprime ordenado; concatenar
izquierdo, raíz y derecho mantiene el orden estricto. El caso vacío es trivial.

Los tres recorridos en profundidad visitan cada nodo una vez: Θ(n) tiempo y
O(h) espacio auxiliar de llamadas. BFS usa una cola FIFO propia, encola primero
el hijo izquierdo y luego el derecho, y visita cada nodo una vez: Θ(n) tiempo y
O(w) espacio auxiliar, donde w es la máxima cantidad de nodos de un nivel.
La cola puede contener partes de dos niveles consecutivos; su tamaño sigue
siendo O(w), a lo sumo O(n). En un árbol vacío todos imprimen una línea vacía.

## Parte 4. Utilización

Los métodos están en UtilizacionArbol y no modifican los árboles. Los tres
primeros reciben un objeto ArbolBinarioBusqueda no nulo. esABB acepta una raíz
null o un árbol binario finito, sin ciclos ni nodos compartidos entre ramas.

### altura

Caso base: null devuelve 0. Caso recursivo: 1 + máximo de las alturas de ambos
hijos. Así una hoja tiene altura 1. Tiempo Θ(n) y espacio auxiliar O(h).

### contarHojas

Caso base: null devuelve 0. Si ambos hijos son null, devuelve 1. En otro caso
suma los resultados de los hijos. Tiempo Θ(n) y espacio auxiliar O(h).

### sumaNodos

Caso base: null devuelve 0. Caso recursivo: dato + suma izquierda + suma derecha.
Tiempo Θ(n) y espacio auxiliar O(h). Se usa long como entero de acumulación
para admitir sumas que exceden el rango int de los datos; por ejemplo,
2147483647 + 2147483646 = 4294967293.

### esABB

Cada llamada recibe un intervalo abierto (mínimo, máximo) permitido para el dato.
Si el nodo es null devuelve true. Si el dato no está estrictamente entre los
límites, devuelve false. El hijo izquierdo recibe (mínimo, dato) y el derecho
(dato, máximo). Ambos deben cumplir la condición. Los límites iniciales son
Long.MIN_VALUE y Long.MAX_VALUE, para admitir incluso Integer.MIN_VALUE e
Integer.MAX_VALUE sin restar ni sumar 1. Los límites estrictos rechazan duplicados.

Los límites acumulan las restricciones de todos los ancestros. Si el método
acepta, cada nodo satisface esas restricciones, lo que implica la propiedad
ABB global. Recíprocamente, un ABB cumple cada intervalo que se transmite.
El tiempo en el peor caso es Θ(n), el espacio auxiliar O(h); el cortocircuito
permite terminar antes cuando se detecta un error (incluso O(1)).

Contraejemplo de tres niveles:

```text
    50
   /  \
  30  70
    \
    60
```

60 es mayor que su padre 30, pero está en el subárbol izquierdo de 50 y debería
ser menor que 50. La llamada para ese nodo recibe (30, 50), detecta la violación
y devuelve false. También se prueba el caso simétrico en el subárbol derecho.

Para el árbol del enunciado: cantidad = 11, altura = 4, hojas = 5,
suma = 540, esABB = true. Las hojas son 10, 25, 40, 65 y 90.

## Verificación

La suite de JUnit cubre árbol vacío, una hoja, las cuatro salidas del enunciado,
búsquedas presentes y ausentes, duplicados, cadenas crecientes y decrecientes,
violaciones de ancestros en ambos lados, valores int extremos, sumas mayores
que int y orden FIFO con reutilización de la cola después de vaciarla.

La implementación es recursiva como exige la consigna. Un árbol muy profundo
puede agotar la pila de Java; no se simula la recursión ni se agrega balanceo.
