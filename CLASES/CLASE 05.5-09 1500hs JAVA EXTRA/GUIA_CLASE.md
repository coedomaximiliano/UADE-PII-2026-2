# Clase Java 05/09 — 15:00 hs (3 horas)

Objetivo: dejar instalados los conceptos de Java que hacen falta para poder
empezar con TDA (Unidad 2 del apunte) la clase siguiente: clases/objetos,
visibilidad, interfaces y excepciones.

Los ejemplos `1`–`4` usan el mismo hilo conductor (una `CuentaBancaria`) para
que se vea cómo se va construyendo una clase "bien hecha" paso a paso, y
terminan enlazando directo con el patrón que van a usar en TDA: interfaz
(especificación) + una o más clases que la implementan, con excepciones para
señalar precondiciones violadas (igual que "la pila no debe estar vacía").

Por cada bloque hay además un ejemplo **`b`** con el mismo concepto pero en
otro dominio (productos, empleados, figuras geométricas, edad de una
persona), para reforzar que la idea no es específica de las cuentas
bancarias. Uso sugerido: los `1`–`4` para explicar en vivo, los `b` como
segundo ejemplo si sobra tiempo en algún bloque, o como material para que
repasen después de la clase / practiquen antes del parcial.

Archivos: `Ejemplo1_ClasesYObjetos.java` / `Ejemplo1b_ClasesYObjetos.java`,
`Ejemplo2_Visibilidad.java` / `Ejemplo2b_Visibilidad.java`,
`Ejemplo3_Interfaces.java` / `Ejemplo3b_Interfaces.java`,
`Ejemplo4_Excepciones.java` / `Ejemplo4b_Excepciones.java`. Cada uno compila
y corre solo (`javac NombreDelArchivo.java && java NombreDelArchivo`).

Nota de encoding: los archivos están en UTF-8. Si al ejecutar desde una
consola de Windows ven acentos como "?", es la consola (cp1252), no el
archivo — corriendo desde un IDE (IntelliJ/Eclipse/VS Code) se ve bien. Si
quieren forzarlo por consola: `java -Dfile.encoding=UTF-8 NombreDelArchivo`.

---

## 0:00 – 0:10 | Apertura (10 min)

Contexto rápido: hoy no vemos TDA todavía — vemos las herramientas de Java
que un TDA necesita para existir (una clase que implementa una interfaz,
con atributos protegidos y que puede fallar de forma controlada). La clase
que viene ya arranca con Pila.

## 0:10 – 0:55 | Bloque 1: Clases y objetos (45 min) — `Ejemplo1`

- Clase = molde. Objeto = instancia (`new`).
- Atributos = estado propio de cada objeto. Métodos = comportamiento.
- Constructor, `this`.
- Vivo en el pizarrón: crear una segunda clase simple entre todos (por
  ejemplo `Alumno` con `nombre` y `legajo`) para que lo escriban ellos.
- Remarcar: dos objetos de la misma clase son independientes (cambiar el
  saldo de Ana no toca el de Luis).

**Chequeo rápido antes de seguir:** pedirles que agreguen un método
`transferir(CuentaBancaria destino, double monto)` a `CuentaBancaria`. Es
la excusa perfecta para el Bloque 2 (¿debería poder tocar `destino.saldo`
directamente desde afuera de la clase?).

*Ejemplo extra: `Ejemplo1b_ClasesYObjetos.java` (clase `Producto`, con
`nombre`, `precioUnitario`, `stock` y un método `vender(cantidad)`).*

## 0:55 – 1:00 | Transición (5 min)

Mostrar qué pasaría si `transferir` accede a `destino.saldo` directo:
funciona, pero rompe el control que tiene `CuentaBancaria` sobre su propio
estado. Gancho directo al Bloque 2.

## 1:00 – 1:35 | Bloque 2: Visibilidad (35 min) — `Ejemplo2`

- `public` vs `private`. (Mencionar que existen `protected` y default/
  paquete, pero que por ahora con estas dos alcanza.)
- Correr `CuentaMal` en vivo y mostrar cómo se puede dejar el saldo en
  cualquier valor absurdo desde afuera.
- Correr `CuentaBien` y mostrar que la única vía es `depositar(...)`, que
  además puede validar.
- Conexión con el apunte: esto es literalmente por qué un TDA "oculta el
  cómo" (2.1) — la interfaz pública es el contrato, los atributos internos
  quedan protegidos.

*Ejemplo extra: `Ejemplo2b_Visibilidad.java` (`EmpleadoMal` vs
`EmpleadoBien`, con `aumentarSueldo(porcentaje)` que valida y devuelve
`true`/`false` según si pudo aplicarse).*

## 1:35 – 1:45 | Pausa (10 min)

## 1:45 – 2:25 | Bloque 3: Interfaces (40 min) — `Ejemplo3`

- Una interfaz declara *qué* operaciones hay, no *cómo* se hacen.
- `implements`.
- Punto clave del bloque: `usarCuenta(OperacionesCuenta cuenta)` no cambia
  ni una línea entre `CuentaSimple` y `CuentaConDescubierto`. El código que
  usa la interfaz no sabe (ni necesita saber) qué implementación recibió.
- Decirlo explícito: esto es exactamente lo que van a hacer con
  `PilaTDA` la próxima clase — una interfaz, y después varias
  implementaciones (`PilaTF`, `PilaTI`, etc.) intercambiables.
- Ejercicio en vivo: agregar una tercera implementación entre todos (por
  ejemplo `CuentaConComision`, que le cobra algo fijo en cada extracción).

*Ejemplo extra: `Ejemplo3b_Interfaces.java` (interfaz `Figura`, implementada
por `Circulo` y `Rectangulo`, con un arreglo `Figura[]` recorrido de forma
polimórfica) — el clásico de figuras geométricas, útil para mostrar que la
interfaz aplica a cualquier dominio, no solo a cuentas.*

## 2:25 – 2:55 | Bloque 4: Excepciones (30 min) — `Ejemplo4`

- Por qué no alcanza con un `if` y un mensaje por consola: hay que poder
  *interrumpir* la operación y que quien la llamó se entere.
- `throw`, `throws`, `try/catch`, excepción propia (`extends Exception`).
- Correr los dos casos del `main`: uno que extrae de más (dispara la
  excepción) y uno que no.
- Cierre conceptual (el más importante para lo que viene): la próxima
  clase, cuando `Desapilar()` se llame sobre una pila vacía, van a usar
  este mismo mecanismo en vez de dejar que el programa siga con datos
  inválidos.

*Ejemplo extra: `Ejemplo4b_Excepciones.java` (`EdadInvalidaException`,
disparada por `Persona.setEdad(edad)` cuando la edad está fuera de
0–130; el `main` recorre un arreglo de edades válidas e inválidas).*

## 2:55 – 3:00 | Cierre (5 min)

Resumen de una línea por bloque:
- Clase/objeto → cómo se modela algo.
- Visibilidad → cómo se protege ese modelo.
- Interfaz → cómo se separa "qué hace" de "cómo lo hace" (el corazón de
  un TDA).
- Excepción → cómo se avisa que no se cumplió una precondición.

Puente a la próxima clase: "Todo esto que vimos hoy es la caja de
herramientas. La semana que viene la usamos para escribir el primer TDA:
`PilaTDA`, con sus operaciones `Apilar`, `Desapilar`, `Tope`, `PilaVacía`
e `InicializarPila`, y más de una forma de implementarlo."
