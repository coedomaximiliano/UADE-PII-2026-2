# ¿Qué es la Notación Big O?

La notación **Big O** (O grande) es la forma estándar de describir **cuánto crece el tiempo de ejecución** (o el uso de memoria) de un algoritmo a medida que crece el tamaño de los datos de entrada (`n`).

No mide segundos exactos — mide **cómo escala** el costo cuando `n` aumenta. Es lo mismo que veníamos analizando en la Clase 2 cuando dijimos que `apilar()` es O(1) o que `pertenece()` en un Conjunto es O(n): Big O es simplemente el nombre formal de esa idea.

---

## Tipos de costo en Notación Big O

### O(1) — Constante
El código toma el **mismo tiempo** sin importar el tamaño de los datos.

```java
public int primerElemento(int[] datos) {
    return datos[0];   // siempre 1 sola operacion,
                        // sin importar si el array tiene 10 o 10 millones de elementos
}
```

**Por qué es O(1):** no hay ningún bucle ni nada que dependa de `n`. Acceder a `datos[0]` cuesta lo mismo si el array tiene 5 elementos o 5 millones.

**Ejemplos ya vistos en la materia:** `apilar()`, `desapilar()`, `tope()` y `esVacia()` de la Pila estática (Clase 2) — todas trabajan siempre sobre el `tope`, nunca recorren nada.

---

### O(n) — Lineal
El tiempo crece de forma **directa** con la cantidad de datos: el doble de datos, el doble de tiempo.

```java
public boolean pertenece(int[] datos, int cantidad, int x) {
    for (int i = 0; i < cantidad; i++) {   // en el peor caso, recorre TODO el array
        if (datos[i] == x) {
            return true;
        }
    }
    return false;
}
```

**Por qué es O(n):** en el peor caso (el elemento no está, o está al final), el bucle se ejecuta una vez por cada elemento. Si `cantidad` se duplica, el trabajo se duplica.

**Ejemplo ya visto en la materia:** `pertenece()`, `agregar()` y `eliminar()` del TDA Conjunto (Clase 3) son O(n) porque tienen que recorrer el array para buscar el elemento.

---

### O(n²) — Cuadrático
El tiempo crece **muy rápido**: es común en bucles anidados (un bucle dentro de otro bucle, ambos recorriendo los mismos `n` datos).

```java
public void mostrarTodosLosPares(int[] datos, int n) {
    for (int i = 0; i < n; i++) {          // se ejecuta n veces
        for (int j = 0; j < n; j++) {      // por cada i, se ejecuta n veces mas
            System.out.println(datos[i] + ", " + datos[j]);
        }
    }
    // total de impresiones: n * n = n²
}
```

**Por qué es O(n²):** el bucle externo se ejecuta `n` veces, y por cada una de esas vueltas, el bucle interno se ejecuta `n` veces más. Con 10 elementos son 100 operaciones; con 100 elementos son 10.000 — el costo se dispara mucho más rápido que la cantidad de datos.

**Ejemplo relacionado con la materia:** un algoritmo "ingenuo" para detectar si un array tiene elementos duplicados, comparando cada elemento contra todos los demás, es O(n²) — es exactamente el problema que el TDA Conjunto (con su chequeo interno de `pertenece()`) ayuda a evitar cuando se usa una estructura más eficiente.

---

### O(log n) — Logarítmico
El tiempo crece **muy lento** — es muy eficiente. Típico de algoritmos que, en cada paso, **descartan la mitad** de los datos restantes en vez de recorrerlos uno por uno.

```java
public boolean busquedaBinaria(int[] datosOrdenados, int x) {
    int inicio = 0;
    int fin = datosOrdenados.length - 1;

    while (inicio <= fin) {
        int medio = (inicio + fin) / 2;

        if (datosOrdenados[medio] == x) {
            return true;                    // encontrado
        } else if (datosOrdenados[medio] < x) {
            inicio = medio + 1;             // descarta la mitad izquierda
        } else {
            fin = medio - 1;                // descarta la mitad derecha
        }
    }
    return false;
}
```

**Por qué es O(log n):** en cada vuelta del `while`, el rango de búsqueda se **divide a la mitad**. Con 1.000 elementos, hacen falta como máximo ~10 comparaciones (no 1.000) para encontrar cualquier valor — porque 2¹⁰ ≈ 1.000. Duplicar la cantidad de datos solo agrega **una** comparación más, no el doble de trabajo.

**Requisito importante:** para que esto funcione, el array tiene que estar **ordenado** — es el mismo trade-off que discutimos en la Clase 3 con la Cola con Prioridad (array ordenado vs. desordenado): pagás un poco más al insertar, pero ganás mucho al buscar.

**Referencia:** [explicación en video de Big O](https://www.youtube.com/watch?v=aR3UX2DjDXQ)

---

## Tabla resumen (de mejor a peor)

| Notación | Nombre | Ejemplo de la materia | ¿Qué tan rápido crece? |
|---|---|---|---|
| O(1) | Constante | `apilar()` de la Pila estática | No crece — siempre igual |
| O(log n) | Logarítmico | Búsqueda binaria (más adelante: ABB) | Crece muy lento |
| O(n) | Lineal | `pertenece()` del Conjunto | Crece proporcional a `n` |
| O(n²) | Cuadrático | Comparar todos los pares de un array | Crece mucho más rápido que `n` |

## Para tener en cuenta

Big O siempre describe el **peor caso** (o, según el contexto, el caso general de crecimiento), no un caso particular con suerte. Por ejemplo, `pertenece(x)` en un Conjunto podría encontrar `x` en la primera posición (muy rápido en ese caso puntual), pero como el peor caso posible es recorrer todo el array, se clasifica como O(n).

Este es exactamente el criterio que usamos en las Clases 2 y 3 para decidir qué implementación conviene en cada situación: no importa cuánto tarde "a veces", importa cuánto puede llegar a tardar en el peor escenario.
