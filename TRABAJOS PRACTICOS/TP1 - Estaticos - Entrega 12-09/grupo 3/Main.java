// TP1 - Algoritmos y Estructuras de Datos II
// Pedro Stella - Bautista Chavarri

/**
 * Demo del TP1: prueba Pila, Cola y ColaPrioridad con sus dos variantes y los metodos
 * de utilizacion, usando las estructuras solo a traves de sus interfaces.
 * Cada linea muestra lo obtenido, lo esperado y OK o ERROR; al final se cuentan.
 */
public class Main {

    private static final int CAPACIDAD = 100;   // estructuras de prueba y auxiliares
    private static final String SIN_EXCEPCION = "sin excepcion";

    // cuentan las verificaciones para el resumen final
    private static int cantidadOk = 0;
    private static int cantidadError = 0;

    public static void main(String[] args) {
        parte1Pila();
        parte2Cola();
        parte3ColaPrioridad();
        System.out.println("\nVerificaciones: " + cantidadOk + " OK, " + cantidadError + " con error");
    }

    // ---------- Parte 1: Pila ----------

    private static void parte1Pila() {
        System.out.println("=== Parte 1: Pila ===");
        System.out.println("(las pilas se muestran como [fondo, ..., tope])");
        operacionesPila(new PilaTopeFinal(3), "PilaTopeFinal (Variante A)");
        operacionesPila(new PilaTopeInicio(3), "PilaTopeInicio (Variante B)");
        System.out.println("\n-- MetodosPila con entradas PilaTopeFinal (Variante A) --");
        metodosPila(true);
        System.out.println("\n-- MetodosPila con entradas PilaTopeInicio (Variante B) --");
        metodosPila(false);
    }

    // mismo codigo para las dos variantes: solo usa la interfaz Pila
    private static void operacionesPila(Pila p, String nombre) {
        System.out.println("\n-- " + nombre + ", capacidad 3 --");
        verificar("esVacia()", p.esVacia(), true);
        verificar("esLlena()", p.esLlena(), false);
        String error = SIN_EXCEPCION;
        try {
            p.desapilar();
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        verificar("desapilar() con la pila vacia lanza", error, "Pila vacia");
        error = SIN_EXCEPCION;
        try {
            p.tope();
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        verificar("tope() con la pila vacia lanza", error, "Pila vacia");
        p.apilar(1);
        p.apilar(2);
        p.apilar(3);
        verificar("apilar(1), apilar(2), apilar(3)", texto(p), "[1, 2, 3]");
        verificar("tope()", p.tope(), 3);
        verificar("esLlena()", p.esLlena(), true);
        error = SIN_EXCEPCION;
        try {
            p.apilar(4);
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        verificar("apilar(4) con la pila llena lanza", error, "Pila llena");
        verificar("desapilar()", p.desapilar(), 3);
        verificar("tope()", p.tope(), 2);
        verificar("desapilar() dos veces", p.desapilar() + ", " + p.desapilar(), "2, 1");
        verificar("esVacia()", p.esVacia(), true);
    }

    // entradas de la Variante A (varianteA = true) o de la B; los resultados son pilas nuevas
    private static void metodosPila(boolean varianteA) {
        int[] v123 = {1, 2, 3};
        int[] v1234 = {1, 2, 3, 4};
        int[] v4124 = {4, 1, 2, 4};
        int[] conImpares = {1, 2, 3, 4, -2, 0, 7};
        int[] vacia = new int[0];

        Pila origen = pilaDe(varianteA, v123);
        verificar("pasarPila([1, 2, 3])", texto(MetodosPila.pasarPila(origen)), "[3, 2, 1]");
        verificar("  origen despues", texto(origen), "[]");

        Pila p = pilaDe(varianteA, v123);
        Pila copia = MetodosPila.copiarPila(p);
        verificar("copiarPila([1, 2, 3])", texto(copia), "[1, 2, 3]");
        verificar("  p despues", texto(p), "[1, 2, 3]");
        copia.apilar(9);
        verificar("  p despues de apilar(9) en la copia", texto(p), "[1, 2, 3]");

        p = pilaDe(varianteA, v1234);
        verificar("invertirPila([1, 2, 3, 4])", texto(MetodosPila.invertirPila(p)), "[4, 3, 2, 1]");
        verificar("  p despues", texto(p), "[1, 2, 3, 4]");
        verificar("invertirPila([])", texto(MetodosPila.invertirPila(pilaDe(varianteA, vacia))), "[]");

        p = pilaDe(varianteA, v4124);
        verificar("masDeUnaOcurrencia([4, 1, 2, 4])", MetodosPila.masDeUnaOcurrencia(p), true);
        verificar("  p despues", texto(p), "[4, 1, 2, 4]");
        p = pilaDe(varianteA, v123);
        verificar("masDeUnaOcurrencia([1, 2, 3])", MetodosPila.masDeUnaOcurrencia(p), false);
        verificar("  p despues", texto(p), "[1, 2, 3]");

        p = pilaDe(varianteA, conImpares);
        verificar("eliminarImpares([1, 2, 3, 4, -2, 0, 7])", texto(MetodosPila.eliminarImpares(p)), "[2, 4, -2, 0]");
        verificar("  p despues", texto(p), "[1, 2, 3, 4, -2, 0, 7]");
    }

    // ---------- Parte 2: Cola ----------

    private static void parte2Cola() {
        System.out.println("\n=== Parte 2: Cola ===");
        System.out.println("(las colas se muestran como [frente, ..., fin])");
        operacionesCola(new ColaEstatica(3), "ColaEstatica (Variante A)");
        operacionesCola(new ColaCircular(3), "ColaCircular (Variante B)");
        estaticaVsCircular();
        System.out.println("\n-- MetodosCola con entradas ColaEstatica (Variante A) --");
        metodosCola(true);
        System.out.println("\n-- MetodosCola con entradas ColaCircular (Variante B) --");
        metodosCola(false);

        System.out.println("\n-- finalCoincide con una ColaEstatica llena (limitacion documentada) --");
        System.out.println("(c1 se vacia y se vuelve a llenar; una ColaEstatica no reutiliza los lugares liberados)");
        Cola llena = new ColaEstatica(3);
        llena.encolar(1);
        llena.encolar(2);
        llena.encolar(3);
        int[] v123 = {1, 2, 3};
        String error = SIN_EXCEPCION;
        try {
            MetodosCola.finalCoincide(llena, colaDe(false, v123), 2);
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        verificar("finalCoincide(ColaEstatica(3) con [1, 2, 3], [1, 2, 3], 2) lanza", error, "Cola llena");
    }

    // mismo codigo para las dos variantes: solo usa la interfaz Cola
    private static void operacionesCola(Cola c, String nombre) {
        System.out.println("\n-- " + nombre + ", capacidad 3 --");
        verificar("esVacia()", c.esVacia(), true);
        verificar("esLlena()", c.esLlena(), false);
        String error = SIN_EXCEPCION;
        try {
            c.desencolar();
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        verificar("desencolar() con la cola vacia lanza", error, "Cola vacia");
        error = SIN_EXCEPCION;
        try {
            c.frente();
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        verificar("frente() con la cola vacia lanza", error, "Cola vacia");
        c.encolar(10);
        c.encolar(20);
        c.encolar(30);
        // sin texto(c): vaciar y volver a llenar una ColaEstatica(3) llena lanzaria "Cola llena"
        verificar("encolar(10), encolar(20), encolar(30) -> frente()", c.frente(), 10);
        verificar("esLlena()", c.esLlena(), true);
        error = SIN_EXCEPCION;
        try {
            c.encolar(40);
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        verificar("encolar(40) con la cola llena lanza", error, "Cola llena");
        verificar("desencolar()", c.desencolar(), 10);
        verificar("frente()", c.frente(), 20);
        verificar("desencolar() dos veces", c.desencolar() + ", " + c.desencolar(), "20, 30");
        verificar("esVacia()", c.esVacia(), true);
    }

    // demo de clase: la ColaEstatica pierde los lugares que libera, la ColaCircular los reutiliza
    private static void estaticaVsCircular() {
        System.out.println("\n-- ColaEstatica, capacidad 3: encolar 1, 2, 3 y desencolar todo --");
        Cola estatica = new ColaEstatica(3);
        estatica.encolar(1);
        estatica.encolar(2);
        estatica.encolar(3);
        verificar("desencolar() tres veces",
                estatica.desencolar() + ", " + estatica.desencolar() + ", " + estatica.desencolar(), "1, 2, 3");
        verificar("esVacia()", estatica.esVacia(), true);
        verificar("esLlena()", estatica.esLlena(), true);   // frente = 3 y cantidad = 0
        String error = SIN_EXCEPCION;
        try {
            estatica.encolar(4);
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        verificar("encolar(4) con la cola vacia lanza", error, "Cola llena");

        System.out.println("\n-- ColaCircular, capacidad 5: encolar 5, desencolar 2, encolar 2 --");
        Cola circular = new ColaCircular(5);
        for (int i = 1; i <= 5; i++) {
            circular.encolar(i * 10);
        }
        verificar("encolar 10, 20, 30, 40, 50 -> esLlena()", circular.esLlena(), true);
        verificar("desencolar() dos veces", circular.desencolar() + ", " + circular.desencolar(), "10, 20");
        error = SIN_EXCEPCION;
        try {
            circular.encolar(60);
            circular.encolar(70);
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        verificar("encolar(60), encolar(70) en los lugares liberados", error, SIN_EXCEPCION);
        verificar("esLlena()", circular.esLlena(), true);
        verificar("contenido", texto(circular), "[30, 40, 50, 60, 70]");
    }

    // entradas de la Variante A (varianteA = true) o de la B; los resultados son colas nuevas
    private static void metodosCola(boolean varianteA) {
        int[] v12 = {1, 2};
        int[] v123 = {1, 2, 3};
        int[] v1234 = {1, 2, 3, 4};
        int[] v12345 = {1, 2, 3, 4, 5};
        int[] v345 = {3, 4, 5};
        int[] v193 = {1, 9, 3};
        int[] v9 = {9};
        int[] vacia = new int[0];

        Cola origen = colaDe(varianteA, v123);
        verificar("pasarCola([1, 2, 3])", texto(MetodosCola.pasarCola(origen)), "[1, 2, 3]");
        verificar("  origen despues", texto(origen), "[]");

        Cola c = colaDe(varianteA, v1234);
        verificar("invertirColaConPila([1, 2, 3, 4])", texto(MetodosCola.invertirColaConPila(c)), "[4, 3, 2, 1]");
        verificar("  c despues", texto(c), "[]");
        c = colaDe(varianteA, v1234);
        verificar("invertirColaSinPila([1, 2, 3, 4])", texto(MetodosCola.invertirColaSinPila(c)), "[4, 3, 2, 1]");
        verificar("  c despues", texto(c), "[]");
        verificar("invertirColaSinPila([])", texto(MetodosCola.invertirColaSinPila(colaDe(varianteA, vacia))), "[]");

        casoFinalCoincide(colaDe(varianteA, v123), colaDe(varianteA, v9), 0, true);        // k = 0
        casoFinalCoincide(colaDe(varianteA, v12345), colaDe(varianteA, v345), 3, true);    // k = largo de c2
        casoFinalCoincide(colaDe(varianteA, v12345), colaDe(varianteA, v345), 4, false);   // k > largo de c2
        casoFinalCoincide(colaDe(varianteA, v123), colaDe(varianteA, v193), 1, true);
        casoFinalCoincide(colaDe(varianteA, v123), colaDe(varianteA, v193), 2, false);     // 2 != 9
        String error = SIN_EXCEPCION;
        try {
            MetodosCola.finalCoincide(colaDe(varianteA, v12), colaDe(varianteA, v12), -1);
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        verificar("finalCoincide([1, 2], [1, 2], -1) lanza", error, "k debe ser >= 0");
    }

    // resultado de finalCoincide y estado de c1 y c2 despues (tienen que quedar como antes)
    private static void casoFinalCoincide(Cola c1, Cola c2, int k, boolean esperado) {
        String antes1 = texto(c1);
        String antes2 = texto(c2);
        verificar("finalCoincide(" + antes1 + ", " + antes2 + ", " + k + ")",
                MetodosCola.finalCoincide(c1, c2, k), esperado);
        verificar("  c1 y c2 despues", texto(c1) + " y " + texto(c2), antes1 + " y " + antes2);
    }

    // ---------- Parte 3: Cola con prioridad ----------

    private static void parte3ColaPrioridad() {
        System.out.println("\n=== Parte 3: Cola con prioridad ===");
        System.out.println("(se muestran como [(valor,prioridad), ...] en el orden en que saldrian;");
        System.out.println(" desempate: a igual prioridad sale primero el que se inserto antes)");
        operacionesColaPrioridad(new ColaPrioridadDesordenada(3), "ColaPrioridadDesordenada (Variante A)");
        operacionesColaPrioridad(new ColaPrioridadOrdenada(3), "ColaPrioridadOrdenada (Variante B)");
        System.out.println("\n-- MetodosColaPrioridad con entradas ColaPrioridadDesordenada y ColaEstatica --");
        metodosColaPrioridad(true);
        System.out.println("\n-- MetodosColaPrioridad con entradas ColaPrioridadOrdenada y ColaCircular --");
        metodosColaPrioridad(false);
    }

    // mismo codigo para las dos variantes: solo usa la interfaz ColaPrioridad
    private static void operacionesColaPrioridad(ColaPrioridad cp, String nombre) {
        System.out.println("\n-- " + nombre + ", capacidad 3 --");
        verificar("esVacia()", cp.esVacia(), true);
        String error = SIN_EXCEPCION;
        try {
            cp.extraerMax();
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        verificar("extraerMax() con la cola vacia lanza", error, "Cola vacia");
        error = SIN_EXCEPCION;
        try {
            cp.verMax();
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        verificar("verMax() con la cola vacia lanza", error, "Cola vacia");
        cp.insertar(10, 2);
        cp.insertar(20, 5);
        cp.insertar(30, 2);
        verificar("insertar(10, 2), insertar(20, 5), insertar(30, 2)", texto(cp), "[(20,5), (10,2), (30,2)]");
        verificar("verMax()", cp.verMax(), 20);
        verificar("prioridadMax()", cp.prioridadMax(), 5);
        verificar("esLlena()", cp.esLlena(), true);
        error = SIN_EXCEPCION;
        try {
            cp.insertar(40, 9);
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        verificar("insertar(40, 9) con la cola llena lanza", error, "Cola llena");
        verificar("extraerMax()", cp.extraerMax(), 20);
        cp.insertar(40, 2);
        verificar("insertar(40, 2): tres empatados en prioridad 2", texto(cp), "[(10,2), (30,2), (40,2)]");
        verificar("verMax()", cp.verMax(), 10);
        verificar("extraerMax() tres veces", cp.extraerMax() + ", " + cp.extraerMax() + ", " + cp.extraerMax(),
                "10, 30, 40");
        verificar("esVacia()", cp.esVacia(), true);
    }

    // entradas de la Variante A (varianteA = true) o de la B, tambien la Cola de
    // invertirColaConColaPrioridad; los arreglos de pares son valor, prioridad, valor, ...
    private static void metodosColaPrioridad(boolean varianteA) {
        int[] pares1 = {1, 5, 2, 3, 3, 5};
        int[] pares2 = {4, 5, 5, 3, 6, 7};
        int[] par20 = {20, 4};
        int[] par10 = {10, 4};
        int[] paresSuma = {10, 2, 20, 3, 30, 4, 40, 2, -5, 0, 7, -2, 100, -1};
        int[] v1234 = {1, 2, 3, 4};
        int[] vacia = new int[0];

        ColaPrioridad cp1 = colaPrioridadDe(varianteA, pares1);
        ColaPrioridad cp2 = colaPrioridadDe(varianteA, pares2);
        verificar("combinar([(1,5), (3,5), (2,3)], [(6,7), (4,5), (5,3)])",
                texto(MetodosColaPrioridad.combinar(cp1, cp2)), "[(6,7), (1,5), (3,5), (4,5), (2,3), (5,3)]");
        verificar("  cp1 y cp2 despues", texto(cp1) + " y " + texto(cp2), "[] y []");
        // a igual prioridad salen primero los de cp1, aunque tengan mayor valor
        cp1 = colaPrioridadDe(varianteA, par20);
        cp2 = colaPrioridadDe(varianteA, par10);
        verificar("combinar([(20,4)], [(10,4)])", texto(MetodosColaPrioridad.combinar(cp1, cp2)), "[(20,4), (10,4)]");

        Cola c = colaDe(varianteA, v1234);
        verificar("invertirColaConColaPrioridad([1, 2, 3, 4])",
                texto(MetodosColaPrioridad.invertirColaConColaPrioridad(c)), "[4, 3, 2, 1]");
        verificar("  c despues", texto(c), "[]");

        ColaPrioridad cp = colaPrioridadDe(varianteA, paresSuma);
        String original = "[(30,4), (20,3), (10,2), (40,2), (-5,0), (100,-1), (7,-2)]";
        verificar("cp", texto(cp), original);
        verificar("sumarValoresPrioridadPar(cp) = 30 + 10 + 40 - 5 + 7",
                MetodosColaPrioridad.sumarValoresPrioridadPar(cp), 82);
        verificar("  cp despues", texto(cp), original);
        verificar("sumarValoresPrioridadPar([])",
                MetodosColaPrioridad.sumarValoresPrioridadPar(colaPrioridadDe(varianteA, vacia)), 0);
    }

    // ---------- Auxiliares ----------

    // imprime "operacion: obtenido  (esperado ...)" con OK o ERROR, y lo cuenta
    private static void verificar(String operacion, String obtenido, String esperado) {
        String marca = "OK";
        if (obtenido.equals(esperado)) {
            cantidadOk++;
        } else {
            marca = "ERROR";
            cantidadError++;
        }
        System.out.println(operacion + ": " + obtenido + "  (esperado " + esperado + ")  " + marca);
    }

    private static void verificar(String operacion, int obtenido, int esperado) {
        verificar(operacion, "" + obtenido, "" + esperado);
    }

    private static void verificar(String operacion, boolean obtenido, boolean esperado) {
        verificar(operacion, "" + obtenido, "" + esperado);
    }

    // pila nueva (Variante A: PilaTopeFinal; si no, PilaTopeInicio) con los valores de fondo a tope
    private static Pila pilaDe(boolean varianteA, int[] valores) {
        Pila p;
        if (varianteA) {
            p = new PilaTopeFinal(CAPACIDAD);
        } else {
            p = new PilaTopeInicio(CAPACIDAD);
        }
        for (int i = 0; i < valores.length; i++) {
            p.apilar(valores[i]);
        }
        return p;
    }

    // cola nueva (Variante A: ColaEstatica; si no, ColaCircular) con los valores de frente a fin
    private static Cola colaDe(boolean varianteA, int[] valores) {
        Cola c;
        if (varianteA) {
            c = new ColaEstatica(CAPACIDAD);
        } else {
            c = new ColaCircular(CAPACIDAD);
        }
        for (int i = 0; i < valores.length; i++) {
            c.encolar(valores[i]);
        }
        return c;
    }

    // cola con prioridad nueva (Variante A: Desordenada; si no, Ordenada); recibe pares valor, prioridad
    private static ColaPrioridad colaPrioridadDe(boolean varianteA, int[] pares) {
        ColaPrioridad cp;
        if (varianteA) {
            cp = new ColaPrioridadDesordenada(CAPACIDAD);
        } else {
            cp = new ColaPrioridadOrdenada(CAPACIDAD);
        }
        for (int i = 0; i < pares.length; i = i + 2) {
            cp.insertar(pares[i], pares[i + 1]);
        }
        return cp;
    }

    // [fondo, ..., tope]: pasa p a una pila auxiliar y la vuelve a armar, asi p queda igual
    private static String texto(Pila p) {
        Pila aux = new PilaTopeFinal(CAPACIDAD);
        while (!p.esVacia()) {
            aux.apilar(p.desapilar());
        }
        String s = "";
        String separador = "";
        while (!aux.esVacia()) {
            int x = aux.desapilar();
            p.apilar(x);
            s = s + separador + x;
            separador = ", ";
        }
        return "[" + s + "]";
    }

    // [frente, ..., fin]: pasa c a una cola auxiliar y la vuelve a armar, asi c queda igual
    // (en una ColaEstatica cada llamada gasta tantos lugares como elementos tiene)
    private static String texto(Cola c) {
        Cola aux = new ColaCircular(CAPACIDAD);
        String s = "";
        String separador = "";
        while (!c.esVacia()) {
            int x = c.desencolar();
            aux.encolar(x);
            s = s + separador + x;
            separador = ", ";
        }
        while (!aux.esVacia()) {
            c.encolar(aux.desencolar());
        }
        return "[" + s + "]";
    }

    // [(valor,prioridad), ...] en orden de salida: extrae todo y lo reinserta con la misma prioridad
    private static String texto(ColaPrioridad cp) {
        ColaPrioridad aux = new ColaPrioridadDesordenada(CAPACIDAD);
        String s = "";
        String separador = "";
        while (!cp.esVacia()) {
            int prioridad = cp.prioridadMax();
            int valor = cp.extraerMax();
            aux.insertar(valor, prioridad);
            s = s + separador + "(" + valor + "," + prioridad + ")";
            separador = ", ";
        }
        while (!aux.esVacia()) {
            int prioridad = aux.prioridadMax();
            cp.insertar(aux.extraerMax(), prioridad);
        }
        return "[" + s + "]";
    }
}
