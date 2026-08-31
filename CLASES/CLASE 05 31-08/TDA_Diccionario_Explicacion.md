# TDA Diccionario y su primera implementación dinámica

Algoritmos y Estructuras de Datos II — UADE, FAIN, 2do Cuatrimestre 2026 — Clase 4

> Todo el código Java de este apunte fue escrito, compilado con `javac` y ejecutado con `java`
> para verificar su correctitud. Todos los bloques de salida muestran la salida **real** obtenida
> al correrlo — no son valores inventados ni estimados.

---

## 1. De el Conjunto al Diccionario

El TDA Conjunto (Clase 3) ya resuelve una pregunta muy útil: *"¿este elemento pertenece?"*. Pero
un Conjunto no guarda nada más sobre ese elemento — solo sabe si está o no está.

Muchos problemas reales necesitan un paso más: no solo saber si algo "está", sino tener **algo
asociado** a esa clave. Una agenda de contactos no solo responde "¿existe Juan?" — responde
"¿cuál es el teléfono de Juan?". Un índice de un libro no solo dice "¿aparece esta palabra?" —
dice "¿en qué páginas aparece?". Una caché no solo dice "¿ya calculé esta consulta?" — dice
"¿cuál fue el resultado?".

El **TDA Diccionario** formaliza esa idea: un conjunto de pares **clave-valor**, donde cada clave
es única y tiene asociado, a lo sumo, un valor.

---

## 2. Especificación formal

Es exactamente la misma especificación que se usó en el TP2 — no hay una versión "de clase"
distinta:

```
TDA Diccionario

crear() -> Diccionario
  post: devuelve un diccionario vacio

definir(d: Diccionario, clave: elemento, valor: elemento) -> Diccionario
  post: si existeClave(d, clave), se reemplaza el valor asociado a clave
        por valor; si no, se agrega el par (clave, valor) a d.

obtener(d: Diccionario, clave: elemento) -> elemento
  pre:  existeClave(d, clave)
  post: devuelve el valor asociado a clave en d

eliminar(d: Diccionario, clave: elemento) -> Diccionario
  pre:  existeClave(d, clave)
  post: se quita de d el par cuya clave es clave; el resto de los
        pares de d no se modifica

existeClave(d: Diccionario, clave: elemento) -> boolean
  post: devuelve true si y solo si hay un par en d cuya clave es clave

esVacio(d: Diccionario) -> boolean
  post: devuelve true si y solo si cantidadClaves(d) = 0

cantidadClaves(d: Diccionario) -> entero
  post: devuelve la cantidad de pares clave-valor que tiene d

claves(d: Diccionario) -> Lista
  post: devuelve una lista (sin ningun orden particular) que contiene
        exactamente una vez cada clave de d; d no se modifica
```

**¿Por qué `existeClave` antes de `definir`?** `definir()` necesita saber si la clave ya existía
para decidir entre *actualizar* el valor o *agregar* un par nuevo — sin esa distinción,
`cantidadClaves()` no podría mantenerse correcta. Es la misma razón por la que `agregar()` del
Conjunto llama primero a `pertenece()`.

**¿Por qué `claves(d)`?** Sin una forma de enumerar los pares desde afuera del TDA, no sería
posible recorrer todo un diccionario usando solo su interfaz pública — la vamos a necesitar en
los tres ejercicios de combinación de TDAs de este apunte.

---

## 3. Implementación dinámica: nodos por referencia

Hasta la Clase 2 vimos implementaciones **estáticas**: un array de tamaño fijo. Hoy aparece la
primera implementación **dinámica**: en vez de un array, una cadena de objetos `NodoDiccionario`
enlazados por referencia.

```java
public class NodoDiccionario {
    Object clave;
    Object valor;
    NodoDiccionario siguiente;
}
```

Es la misma idea de Nodo que se usa en cualquier estructura dinámica (dato + referencia al
próximo) — la única diferencia es que acá el "dato" es, en realidad, un **par** (clave, valor).
`DiccionarioDinamico` solo necesita guardar una referencia a la **cabeza** de la cadena: no hay
array, no hay capacidad fija, y por lo tanto no existe una operación `esLlena()` — cada
`definir()` de una clave nueva simplemente pide un nodo más.

> Esta clase es la **primera** exposición a la idea de implementación dinámica. La Clase 5 retoma
> el concepto de Nodo en más profundidad y lo compara operación por operación (y en tiempos
> reales medidos) contra una implementación estática — acá el foco es entender el mecanismo y
> tener una primera implementación funcionando correctamente.

### 3.1 Diseño de `DiccionarioDinamico`

- Atributo `cabeza`: referencia al primer nodo de la cadena (`null` si está vacío).
- Atributo `cantidad`: contador mantenido aparte, para que `cantidadClaves()` sea O(1) y no
  tenga que recorrer la cadena para contar.
- Auxiliar privado `buscarNodo(clave)`: recorre la cadena desde `cabeza` y devuelve el nodo cuya
  clave coincide, o `null` si no existe. **No** es parte de la especificación del TDA —
  `existeClave`, `definir`, `obtener` y `eliminar` lo reutilizan, evitando repetir el mismo
  recorrido cuatro veces.
- Los pares nuevos se agregan **al principio** de la cadena (enganchar como cabeza es la
  inserción más barata cuando no hay que mantener ningún orden particular).

```java
public class DiccionarioDinamico {

    private NodoDiccionario cabeza;
    private int cantidad;

    public DiccionarioDinamico() {           // crear()
        this.cabeza = null;
        this.cantidad = 0;
    }

    private NodoDiccionario buscarNodo(Object clave) {
        NodoDiccionario actual = cabeza;
        while (actual != null) {
            if (actual.clave.equals(clave)) {
                return actual;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public boolean existeClave(Object clave) {
        return buscarNodo(clave) != null;
    }

    public void definir(Object clave, Object valor) {
        NodoDiccionario existente = buscarNodo(clave);
        if (existente != null) {
            existente.valor = valor;               // actualiza
        } else {
            NodoDiccionario nuevo = new NodoDiccionario(clave, valor);
            nuevo.siguiente = cabeza;               // engancha...
            cabeza = nuevo;                         // ...como cabeza
            cantidad++;
        }
    }

    public Object obtener(Object clave) {
        NodoDiccionario nodo = buscarNodo(clave);
        if (nodo == null) {
            throw new RuntimeException("obtener(): la clave no existe -> " + clave);
        }
        return nodo.valor;
    }

    public void eliminar(Object clave) {
        NodoDiccionario actual = cabeza;
        NodoDiccionario anterior = null;
        while (actual != null) {
            if (actual.clave.equals(clave)) {
                if (anterior == null) {
                    cabeza = actual.siguiente;
                } else {
                    anterior.siguiente = actual.siguiente;
                }
                cantidad--;
                return;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
        throw new RuntimeException("eliminar(): la clave no existe -> " + clave);
    }

    public boolean esVacio() {
        return cabeza == null;
    }

    public int cantidadClaves() {
        return cantidad;
    }

    public Object[] claves() {
        Object[] resultado = new Object[cantidad];
        NodoDiccionario actual = cabeza;
        int i = 0;
        while (actual != null) {
            resultado[i] = actual.clave;
            i++;
            actual = actual.siguiente;
        }
        return resultado;
    }
}
```

`eliminar()` necesita recordar el nodo **anterior** al que se está por sacar: sin esa referencia
no habría forma de "saltear" el nodo eliminado sin dejar un agujero en la cadena.

`claves(d)` se devuelve como `Object[]` en este apunte (en vez de un TDA Lista propiamente dicho,
que todavía no fue formalizado como tal en el curso) — alcanza para poder recorrer todas las
claves desde afuera del Diccionario, que es lo único que hace falta para los ejercicios de la
sección 5.

### 3.2 Prueba real: agenda de contactos

```java
DiccionarioDinamico agenda = new DiccionarioDinamico();
agenda.definir("juan", "11-2345-6789");
agenda.definir("maria", "11-9876-5432");
agenda.definir("pedro", "11-5555-1234");
agenda.definir("juan", "11-0000-1111");   // clave repetida: actualiza, no agrega
```

Salida real (`javac DiccionarioDinamico.java && java DiccionarioDinamico`):

```
=== crear() ===
esVacio() = true | cantidadClaves() = 0

=== definir("juan",...), definir("maria",...), definir("pedro",...) ===
{ pedro=11-5555-1234 maria=11-9876-5432 juan=11-2345-6789 }
cantidadClaves() = 3

=== definir("juan", nuevoTelefono)  (actualiza, no agrega) ===
{ pedro=11-5555-1234 maria=11-9876-5432 juan=11-0000-1111 }
cantidadClaves() = 3 (no crecio)

=== obtener("juan") ===
obtener("juan") = 11-0000-1111

=== existeClave("maria") / existeClave("lucas") ===
existeClave("maria") = true
existeClave("lucas") = false

=== claves() ===
claves() = [ pedro maria juan ] (cantidad = 3)

=== eliminar("maria") ===
{ pedro=11-5555-1234 juan=11-0000-1111 }
cantidadClaves() = 2

=== esVacio() sobre un diccionario no vacio ===
esVacio() = false

=== precondicion violada: obtener("maria") tras eliminarla ===
RuntimeException capturada: obtener(): la clave no existe -> maria
```

Se comporta exactamente como especifica el TDA: `definir()` sobre una clave existente actualiza
el valor sin cambiar `cantidadClaves()`, y violar la precondición de `obtener()` lanza una
excepción en vez de devolver cualquier cosa.

---

## 4. Resolución de problemas combinando distintos TDAs

Un problema real casi nunca se resuelve con un solo TDA. Los tres ejercicios siguientes combinan
el Diccionario dinámico de hoy con TDAs ya vistos (implementados de forma **estática**, tal como
se construyeron en las Clases 2 y 3) — cada estructura resuelve la parte del problema en la que
es mejor, y ninguna de las dos, sola, alcanza.

### 4.1 Ejercicio 1 — Caché con reemplazo FIFO (Cola + Diccionario)

**Problema:** una caché de resultados con capacidad fija. Si se agrega una clave nueva y la
caché ya está llena, hay que descartar la clave **más vieja** antes de guardar la nueva.

- La **Cola** (estática, circular) guarda las claves en **orden de llegada** — decide a quién le
  toca ser descartado (política FIFO, *First In, First Out*).
- El **Diccionario** resuelve el acceso directo `clave -> valor`.

```java
void poner(String clave, String valor) {
    if (valores.existeClave(clave)) {
        valores.definir(clave, valor);   // actualiza, no descarta
        return;
    }
    if (valores.cantidadClaves() == capacidad) {
        String claveDescartada = (String) ordenLlegada.desencolar();
        valores.eliminar(claveDescartada);
    }
    valores.definir(clave, valor);
    ordenLlegada.encolar(clave);
}
```

Salida real (`javac Ejercicio1_CacheFIFO.java && java Ejercicio1_CacheFIFO`):

```
=== Cache con capacidad 3, politica FIFO ===
poner("consulta_A", ...) -> insercion nueva
poner("consulta_B", ...) -> insercion nueva
poner("consulta_C", ...) -> insercion nueva
cantidad() = 3 (cache llena)

=== poner("consulta_D", ...) -> la cache esta llena, debe descartar la mas vieja ===
poner("consulta_D", ...) -> cache llena, se descarta "consulta_A" (la mas vieja)
cantidad() = 3

=== obtener("consulta_A") tras haber sido descartada ===
obtener("consulta_A") = null (null: ya no esta)
obtener("consulta_D") = resultado_D

=== poner("consulta_C", ...) sobre una clave ya presente (actualiza, no descarta) ===
poner("consulta_C", ...) -> ya existia, se actualiza el valor
obtener("consulta_C") = resultado_C_v2
cantidad() = 3 (no crecio)

=== poner("consulta_E", ...) -> vuelve a llenarse y descarta a "consulta_B" ===
poner("consulta_E", ...) -> cache llena, se descarta "consulta_B" (la mas vieja)
obtener("consulta_B") = null (null: fue descartada)
obtener("consulta_C") = resultado_C_v2 (sigue: se actualizo, no se re-encolo, asi que no era la mas vieja)
```

Notar el último caso: actualizar el valor de una clave ya presente (`consulta_C`) **no** vuelve a
encolarla — sigue en su posición original de la Cola, y por eso es descartada más tarde que si
hubiera sido tratada como una inserción nueva.

### 4.2 Ejercicio 2 — Registro de ingreso a un evento (Conjunto + Diccionario)

**Problema:** en la entrada de un evento se escanea el DNI de cada invitado. Si el DNI ya había
ingresado, hay que rechazarlo; si es la primera vez, se registra el ingreso y se guarda el nombre
completo.

- El **Conjunto** (estático) responde rápido "¿este DNI ya está?" sin guardar nada más sobre él
  — exactamente lo que especifica `pertenece`/`agregar`.
- El **Diccionario** asocia cada DNI con el nombre completo, algo que un Conjunto no puede hacer
  porque no guarda valores asociados.

```java
boolean registrarIngreso(String dni, String nombreCompleto) {
    if (dniRegistrados.pertenece(dni)) {
        return false;                     // intento de duplicado
    }
    dniRegistrados.agregar(dni);
    datos.definir(dni, nombreCompleto);
    return true;
}
```

Salida real (`javac Ejercicio2_RegistroInvitados.java && java Ejercicio2_RegistroInvitados`):

```
=== Registro de ingreso, capacidad para 50 invitados ===
OK: ingreso Sofia Gimenez
OK: ingreso Nicolas Ferrari
OK: ingreso Camila Duarte

=== Intento de reingreso con un DNI ya registrado ===
registrarIngreso("29888777", ...) = false (rechazado: ya habia ingresado)

=== cantidadIngresos() ===
cantidadIngresos() = 3 (el intento duplicado no sumo)

=== nombreDe(dni) ===
nombreDe("31444555") = Camila Duarte
nombreDe("99999999") = null (nunca ingreso)

Invitados presentes (3):
  - Camila Duarte (DNI 31444555)
  - Nicolas Ferrari (DNI 29888777)
  - Sofia Gimenez (DNI 30111222)
```

El Conjunto filtra los duplicados en su propio terreno; el Diccionario nunca se entera de los
intentos rechazados — solo guarda los datos de quien realmente ingresó.

### 4.3 Ejercicio 3 — Guardia médica por urgencia (Cola con Prioridad + Diccionario)

**Problema:** cada paciente que llega recibe un número de turno y un nivel de urgencia (1 = más
urgente). Hay que atender primero al más urgente, sin importar el orden de llegada, y anunciar su
**nombre**, no solo su número.

- La **Cola con Prioridad** (estática, sin ordenar — `insertar` es O(1), `extraerMax` recorre
  todo, O(n)) decide **el orden** de atención según la urgencia.
- El **Diccionario** asocia cada número de turno con el nombre del paciente.

```java
String atenderSiguiente() {
    if (turnos.esVacia()) {
        return null;
    }
    int numero = turnos.extraerMax();               // el mas urgente
    String nombre = (String) pacientes.obtener(numero);
    pacientes.eliminar(numero);
    return nombre + " (turno #" + numero + ")";
}
```

Salida real (`javac Ejercicio3_GuardiaPrioridad.java && java Ejercicio3_GuardiaPrioridad`):

```
=== Llegada de pacientes (nombre, nivel de urgencia; 1 = mas urgente) ===
registrarPaciente("Roberto Sanz", urgencia=4) -> turno #1
registrarPaciente("Elena Prieto", urgencia=2) -> turno #2
registrarPaciente("Damian Ortiz", urgencia=5) -> turno #3
registrarPaciente("Valeria Nunez", urgencia=1) -> turno #4

=== Orden real de atencion (NO es el orden de llegada) ===
1) atenderSiguiente() = Valeria Nunez (turno #4)
2) atenderSiguiente() = Elena Prieto (turno #2)
3) atenderSiguiente() = Roberto Sanz (turno #1)
4) atenderSiguiente() = Damian Ortiz (turno #3)

=== atenderSiguiente() con la sala de espera vacia ===
atenderSiguiente() = null (null: no queda nadie)
```

El orden de atención no coincide con el de llegada: Valeria (urgencia 1, la más urgente) llegó
última y es atendida primera. La Cola con Prioridad decide el **orden**; el Diccionario resuelve
el **nombre** — la misma división de responsabilidades que en los dos ejercicios anteriores.

---

## 5. Resumen

- El TDA Diccionario asocia claves únicas a valores: `definir`, `obtener`, `eliminar`,
  `existeClave`, `esVacio`, `cantidadClaves`, `claves` — la misma especificación usada en el TP2.
- Una implementación dinámica reemplaza el array por una cadena de `NodoDiccionario`
  (clave + valor + siguiente), enganchados por referencia y sin capacidad fija.
- `buscarNodo()` es el auxiliar interno que reutilizan `existeClave`, `definir`, `obtener` y
  `eliminar` — recorrer la cadena es el costo común a casi todas las operaciones.
- Resolver un problema real casi nunca alcanza con un solo TDA: Cola+Diccionario (caché FIFO),
  Conjunto+Diccionario (registro sin duplicados) y ColaConPrioridad+Diccionario (atención por
  urgencia) combinan cada estructura en lo que mejor sabe hacer.

## 6. Lo que viene: Clase 5

Quedó pendiente una pregunta: ¿esta implementación dinámica es más rápida o más lenta que una
implementación estática equivalente? La Clase 5 retoma la idea de Nodo vista hoy y la analiza en
profundidad — complejidad temporal operación por operación, complejidad espacial, y una
comparación empírica con tiempos reales medidos — usando este mismo TDA Diccionario como base.
