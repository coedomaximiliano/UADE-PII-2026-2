public class Main {

    public static void main(String[] args) {
        probarPila();
        // La variante lineal no reutiliza los lugares liberados: cada vez que
        // una utilidad vacía y restaura la cola consume capacidad nueva.
        probarCola(new ColaA(20), new ColaA(20), new ColaA(20), new ColaA(20), new ColaA(20), "ColaA (lineal)");
        probarCola(new ColaB(10), new ColaB(10), new ColaB(10), new ColaB(10), new ColaB(10), "ColaB (circular)");
        probarColaPrioridad();
    }

    // ---------------------------------------------------------------------
    // PARTE 1: TDA Pila
    // ---------------------------------------------------------------------
    private static void probarPila() {
        System.out.println("=== PRUEBAS DE USO DEL TDA PILA ===\n");

        // Test 1: pasarPila
        System.out.println("TEST 1: pasarPila()");
        System.out.println("-------------------");
        Pila p1 = crearPilaConElementos(1, 2, 3, 4, 5);
        System.out.print("Pila original: ");
        imprimirPila(p1);
        Pila p1Pasada = PilaUtilizacion.pasarPila(p1);
        System.out.print("Pila después de pasar: ");
        imprimirPila(p1Pasada);
        System.out.println("¿Origen vacía? " + p1.esVacia());
        System.out.println();

        // Test 2: copiarPila
        System.out.println("TEST 2: copiarPila()");
        System.out.println("-------------------");
        Pila p2 = crearPilaConElementos(10, 20, 30, 40);
        System.out.print("Pila original: ");
        imprimirPila(p2);
        Pila p2Copia = PilaUtilizacion.copiarPila(p2);
        System.out.print("Copia: ");
        imprimirPila(p2Copia);
        System.out.print("¿Original sin cambios? ");
        imprimirPila(p2);
        System.out.println();

        // Test 3: invertirPila (RECURSIVO)
        System.out.println("TEST 3: invertirPila() [RECURSIVO]");
        System.out.println("---------------------------------");
        Pila p3 = crearPilaConElementos(7, 8, 9, 10);
        System.out.print("Pila original: ");
        imprimirPila(p3);
        Pila p3Invertida = PilaUtilizacion.invertirPila(p3);
        System.out.print("Pila invertida: ");
        imprimirPila(p3Invertida);
        System.out.println();

        // Test 4: masDeUnaOcurrencia
        System.out.println("TEST 4: masDeUnaOcurrencia()");
        System.out.println("----------------------------");
        Pila p4Sin = crearPilaConElementos(100, 200, 300);
        System.out.print("Pila sin duplicados: ");
        imprimirPila(p4Sin);
        boolean tieneRepetidos1 = PilaUtilizacion.masDeUnaOcurrencia(p4Sin);
        System.out.println("¿Tiene duplicados? " + tieneRepetidos1);
        System.out.print("¿Pila sin cambios? ");
        imprimirPila(p4Sin);

        Pila p4Con = crearPilaConElementos(5, 10, 5, 15, 10);
        System.out.print("Pila con duplicados: ");
        imprimirPila(p4Con);
        boolean tieneRepetidos2 = PilaUtilizacion.masDeUnaOcurrencia(p4Con);
        System.out.println("¿Tiene duplicados? " + tieneRepetidos2);
        System.out.print("¿Pila sin cambios? ");
        imprimirPila(p4Con);
        System.out.println();

        // Test 5: eliminarImpares
        System.out.println("TEST 5: eliminarImpares()");
        System.out.println("------------------------");
        Pila p5 = crearPilaConElementos(1, 2, 3, 4, 5, 6, 7, 8);
        System.out.print("Pila original (1,2,3,4,5,6,7,8): ");
        imprimirPila(p5);
        Pila p5Pares = PilaUtilizacion.eliminarImpares(p5);
        System.out.print("Solo pares (en orden): ");
        imprimirPila(p5Pares);
        System.out.println();
    }

    // Método auxiliar: crear pila con elementos (el último es tope)
    private static Pila crearPilaConElementos(int... elementos) {
        Pila p = new PilaVarianteA();
        for (int x : elementos) {
            p.apilar(x);
        }
        return p;
    }

    // Método auxiliar: imprimir pila sin modificarla
    private static void imprimirPila(Pila p) {
        if (p.esVacia()) {
            System.out.println("[ ]");
            return;
        }

        Pila temp = new PilaVarianteA();

        // Desapila p hacia temp (invierte)
        while (!p.esVacia()) {
            temp.apilar(p.desapilar());
        }

        // Desapila temp y vuelve a apilar en p (restaura)
        System.out.print("[ ");
        while (!temp.esVacia()) {
            int x = temp.desapilar();
            p.apilar(x);
            System.out.print(x + " ");
        }
        System.out.println("]");
    }

    // ---------------------------------------------------------------------
    // PARTE 2: TDA Cola (se corre con cada variante)
    // ---------------------------------------------------------------------
    private static void probarCola(Cola origen, Cola c1, Cola c2, Cola cola1, Cola cola2, String variante) {
        System.out.println("=== PRUEBAS DE USO DEL TDA COLA: " + variante + " ===\n");

        System.out.println("--- 1 Prueba de pasarCola ---");
        origen.encolar(10);
        origen.encolar(20);
        origen.encolar(30);
        origen.encolar(40);
        Cola destino = ColaUtilizacion.pasarCola(origen);
        System.out.print("Cola destino armada: ");
        imprimirYVaciarCola(destino);
        System.out.println("¿Origen vacía? " + origen.esVacia());
        System.out.println();

        System.out.println("--- 2 Prueba de invertirColaConPila ---");
        c1.encolar(1);
        c1.encolar(2);
        c1.encolar(3);
        c1.encolar(4);
        c1.encolar(5);
        Cola c1Invertida = ColaUtilizacion.invertirColaConPila(c1);
        System.out.print("Nueva cola invertida: ");
        imprimirYVaciarCola(c1Invertida);
        System.out.print("Cola c1 preservada: ");
        imprimirYVaciarCola(c1);
        System.out.println();

        System.out.println("--- 3 Prueba de invertirColaSinPila (Recursivo) ---");
        c2.encolar(100);
        c2.encolar(200);
        c2.encolar(300);
        ColaUtilizacion.invertirColaSinPila(c2);
        System.out.print("Cola luego de invertir: ");
        imprimirYVaciarCola(c2);
        System.out.println();

        System.out.println("--- 4 Prueba de finalCoincide ---");
        cola1.encolar(5);
        cola1.encolar(10);
        cola1.encolar(15);
        cola1.encolar(20);
        cola1.encolar(25);
        cola2.encolar(99);
        cola2.encolar(88);
        cola2.encolar(15);
        cola2.encolar(20);
        cola2.encolar(25);
        boolean coincideK3 = ColaUtilizacion.finalCoincide(cola1, cola2, 3);
        System.out.println("¿Coinciden los últimos 3 elementos?: " + coincideK3 + " (Esperado: true)");
        boolean coincideK4 = ColaUtilizacion.finalCoincide(cola1, cola2, 4);
        System.out.println("¿Coinciden los últimos 4 elementos?: " + coincideK4 + " (Esperado: false)");
        System.out.println("Tamaño de las colas después: " + cola1.tamanio() + " y " + cola2.tamanio() + " (Esperado: 5 y 5)");
        System.out.print("cola1 preservada: ");
        imprimirYVaciarCola(cola1);
        System.out.print("cola2 preservada: ");
        imprimirYVaciarCola(cola2);
        System.out.println();
    }

    // Método auxiliar: imprime la cola desencolando todos sus elementos
    private static void imprimirYVaciarCola(Cola c) {
        System.out.print("[ ");
        while (!c.esVacia()) {
            System.out.print(c.desencolar() + " ");
        }
        System.out.println("]");
    }

    // ---------------------------------------------------------------------
    // PARTE 3: TDA Cola con Prioridad
    // ---------------------------------------------------------------------
    private static void probarColaPrioridad() {
        System.out.println("=== PRUEBAS DE USO DEL TDA COLA CON PRIORIDAD ===\n");

        ColaPrioridad cp1 = new ColaPrioridadVarianteA();
        cp1.insertar(10, 2);
        cp1.insertar(20, 5);
        cp1.insertar(30, 5);

        ColaPrioridad cp2 = new ColaPrioridadVarianteB();
        cp2.insertar(40, 4);
        cp2.insertar(50, 5);

        ColaPrioridad combinada = UtilidadesColaPrioridad.combinar(cp1, cp2);
        System.out.println("Cola combinada:");
        imprimirYVaciar(combinada);

        Cola cola = new ColaB(10);
        cola.encolar(1);
        cola.encolar(2);
        cola.encolar(3);
        Cola invertida = UtilidadesColaPrioridad.invertirColaConColaPrioridad(cola);
        System.out.print("Cola invertida: ");
        imprimirYVaciarCola(invertida);

        ColaPrioridad cp3 = new ColaPrioridadVarianteB();
        cp3.insertar(7, 1);
        cp3.insertar(8, 2);
        cp3.insertar(9, 4);
        cp3.insertar(10, 5);
        int suma = UtilidadesColaPrioridad.sumarValoresPrioridadPar(cp3);
        System.out.println("Suma con prioridad par: " + suma);
        System.out.println("cp3 luego de sumar:");
        imprimirYVaciar(cp3);
    }

    private static void imprimirYVaciar(ColaPrioridad cp) {
        while (!cp.esVacia()) {
            System.out.println(cp.extraerMax());
        }
    }
}
