import Implementaciones.*;
import TDAs.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("   PRUEBAS GENERALES DE ESTRUCTURAS DE DATOS     ");
        System.out.println("=================================================\n");

        probarPilas();
        probarColas();
        probarColasPrioridad();

        System.out.println("=================================================");
        System.out.println("        ¡TODAS LAS PRUEBAS FINALIZADAS!          ");
        System.out.println("=================================================");
    }

    // ==========================================
    // 1. SECCIÓN PILAS
    // ==========================================
    private static void probarPilas() {
        System.out.println("-------------------------------------------------");
        System.out.println("             1. PRUEBAS DE PILA                  ");
        System.out.println("-------------------------------------------------");

        // --- Test 1: pasarPila ---
        System.out.println("\n[Pila] 1. pasarPila:");
        PilaTDA pOrigen = new PilaEstaticaA(100);
        pOrigen.apilar(10); pOrigen.apilar(20); pOrigen.apilar(30);
        System.out.print("Pila origen inicial (A): "); imprimirPila(pOrigen);

        PilaTDA pPasada = Metodos.pasarPila(pOrigen);
        System.out.print("Pila nueva devuelta:     "); imprimirPila(pPasada);
        System.out.print("Pila origen (debe vaciarse): "); imprimirPila(pOrigen);

        // --- Test 2: copiarPila ---
        System.out.println("\n[Pila] 2. copiarPila:");
        PilaTDA pOriginal = new PilaEstaticaB(100);
        pOriginal.apilar(5); pOriginal.apilar(15); pOriginal.apilar(25);
        System.out.print("Pila original (B):       "); imprimirPila(pOriginal);

        PilaTDA pCopia = Metodos.copiarPila(pOriginal);
        System.out.print("Copia devuelta:          "); imprimirPila(pCopia);
        System.out.print("Original (se preserva):  "); imprimirPila(pOriginal);

        // --- Test 3: invertirPila (Recursivo) ---
        System.out.println("\n[Pila] 3. invertirPila (Recursivo):");
        PilaTDA pParaInvertir = new PilaEstaticaA(100);
        pParaInvertir.apilar(1); pParaInvertir.apilar(2); pParaInvertir.apilar(3);
        System.out.print("Pila original (A):       "); imprimirPila(pParaInvertir);

        PilaTDA pInvertida = Metodos.invertirPila(pParaInvertir);
        System.out.print("Pila invertida:          "); imprimirPila(pInvertida);
        System.out.print("Original (se preserva):  "); imprimirPila(pParaInvertir);

        // --- Test 4: masDeUnaOcurrencia ---
        System.out.println("\n[Pila] 4. masDeUnaOcurrencia:");
        PilaTDA pSinDuplicados = new PilaEstaticaA(100);
        pSinDuplicados.apilar(10); pSinDuplicados.apilar(20); pSinDuplicados.apilar(30);

        PilaTDA pConDuplicados = new PilaEstaticaB(100);
        pConDuplicados.apilar(10); pConDuplicados.apilar(20); pConDuplicados.apilar(10);

        System.out.println("Pila 1 [10, 20, 30] -> ¿Tiene duplicados?: " + Metodos.masDeUnaOcurrencia(pSinDuplicados));
        System.out.println("Pila 2 [10, 20, 10] -> ¿Tiene duplicados?: " + Metodos.masDeUnaOcurrencia(pConDuplicados));

        // --- Test 5: eliminarImpares ---
        System.out.println("\n[Pila] 5. eliminarImpares:");
        PilaTDA pConImpares = new PilaEstaticaA(100);
        pConImpares.apilar(1); pConImpares.apilar(2); pConImpares.apilar(3); pConImpares.apilar(4); pConImpares.apilar(5);
        System.out.print("Pila original (A):       "); imprimirPila(pConImpares);

        PilaTDA pSoloPares = Metodos.eliminarImpares(pConImpares);
        System.out.print("Pila resultante (pares): "); imprimirPila(pSoloPares);
        System.out.println();
    }

    // ==========================================
    // 2. SECCIÓN COLAS
    // ==========================================
    private static void probarColas() {
        System.out.println("-------------------------------------------------");
        System.out.println("             2. PRUEBAS DE COLA                  ");
        System.out.println("-------------------------------------------------");

        // --- Test 1: pasarCola ---
        System.out.println("\n[Cola] 1. pasarCola:");
        ColaTDA cOrigen = new ColaEstaticaA(100);
        cOrigen.encolar(100); cOrigen.encolar(200); cOrigen.encolar(300);
        System.out.print("Cola origen inicial (A): "); imprimirCola(cOrigen);

        ColaTDA cPasada = Metodos.pasarCola(cOrigen);
        System.out.print("Cola nueva devuelta:     "); imprimirCola(cPasada);
        System.out.print("Cola origen (debe vaciarse): "); imprimirCola(cOrigen);

        // --- Test 2: invertirColaConPila ---
        System.out.println("\n[Cola] 2. invertirColaConPila:");
        ColaTDA cNormal = new ColaEstaticaB(100);
        cNormal.encolar(1); cNormal.encolar(2); cNormal.encolar(3);
        System.out.print("Cola original (B Circular): "); imprimirCola(cNormal);

        ColaTDA cInvertidaPila = Metodos.invertirColaConPila(cNormal);
        System.out.print("Cola invertida con Pila:    "); imprimirCola(cInvertidaPila);

        // --- Test 3: invertirColaSinPila ---
        System.out.println("\n[Cola] 3. invertirColaSinPila (Recursivo puro):");
        ColaTDA cParaRecursion = new ColaEstaticaA(100);
        cParaRecursion.encolar(10); cParaRecursion.encolar(20); cParaRecursion.encolar(30);
        System.out.print("Cola antes de invertir:  "); imprimirCola(cParaRecursion);

        Metodos.invertirColaSinPila(cParaRecursion);
        System.out.print("Cola invertida (in-place): "); imprimirCola(cParaRecursion);

        // --- Test 4: finalCoincide ---
        System.out.println("\n[Cola] 4. finalCoincide:");
        ColaTDA c1 = new ColaEstaticaA(100);
        c1.encolar(1); c1.encolar(2); c1.encolar(8); c1.encolar(9);

        ColaTDA c2 = new ColaEstaticaB(100);
        c2.encolar(5); c2.encolar(8); c2.encolar(9);

        System.out.print("Cola 1: "); imprimirCola(c1);
        System.out.print("Cola 2: "); imprimirCola(c2);
        System.out.println("¿Coinciden los últimos k=2 elementos?: " + Metodos.finalCoincide(c1, c2, 2));
        System.out.println("¿Coinciden los últimos k=3 elementos?: " + Metodos.finalCoincide(c1, c2, 3));
        System.out.print("Cola 1 restaurada: "); imprimirCola(c1);
        System.out.print("Cola 2 restaurada: "); imprimirCola(c2);
        System.out.println();
    }

    // ==========================================
    // 3. SECCIÓN COLAS DE PRIORIDAD
    // ==========================================
    private static void probarColasPrioridad() {
        System.out.println("-------------------------------------------------");
        System.out.println("        3. PRUEBAS DE COLA DE PRIORIDAD          ");
        System.out.println("-------------------------------------------------");

        // --- Test 1: combinar ---
        System.out.println("\n[ColaPrioridad] 1. combinar:");
        ColaPrioridadTDA cp1 = new ColaPrioridadEstaticaA(100);
        cp1.insertar(100, 1);
        cp1.insertar(300, 3);

        ColaPrioridadTDA cp2 = new ColaPrioridadEstaticaB(100);
        cp2.insertar(200, 2);
        cp2.insertar(400, 4);

        System.out.print("CP1 (A Desordenada): "); imprimirCP(cp1);
        System.out.print("CP2 (B Ordenada):    "); imprimirCP(cp2);

        ColaPrioridadTDA cpCombinada = Metodos.combinar(cp1, cp2);
        System.out.print("CP Combinada:        "); imprimirCP(cpCombinada);

        // --- Test 2: invertirColaConColaPrioridad ---
        System.out.println("\n[ColaPrioridad] 2. invertirColaConColaPrioridad:");
        ColaTDA colaNormal = new ColaEstaticaA(100);
        colaNormal.encolar(5); colaNormal.encolar(10); colaNormal.encolar(15);
        System.out.print("Cola normal inicial: "); imprimirCola(colaNormal);

        ColaTDA colaInvertidaCP = Metodos.invertirColaConColaPrioridad(colaNormal);
        System.out.print("Cola invertida con CP: "); imprimirCola(colaInvertidaCP);

        // --- Test 3: sumarValoresPrioridadPar ---
        System.out.println("\n[ColaPrioridad] 3. sumarValoresPrioridadPar:");
        ColaPrioridadTDA cpPar = new ColaPrioridadEstaticaA(100);
        cpPar.insertar(10, 2); // Par -> suma 10
        cpPar.insertar(15, 3); // Impar -> no suma
        cpPar.insertar(20, 4); // Par -> suma 20
        cpPar.insertar(25, 1); // Impar -> no suma

        System.out.print("CP Original: "); imprimirCP(cpPar);
        int sumaPares = Metodos.sumarValoresPrioridadPar(cpPar);
        System.out.println("Suma de valores con prioridad par (esperado 30): " + sumaPares);
        System.out.print("CP Restaurada: "); imprimirCP(cpPar);
        System.out.println();
    }

    // ==========================================
    // IMPRESORES AUXILIARES
    // ==========================================

    private static void imprimirPila(PilaTDA p) {
        PilaTDA copia = Metodos.copiarPila(p);
        System.out.print("[ ");
        while (!copia.esVacia()) {
            System.out.print(copia.tope() + " ");
            copia.desapilar();
        }
        System.out.println("] (Tope a la izquierda)");
    }

    private static void imprimirCola(ColaTDA c) {
        ColaTDA copia = Metodos.pasarCola(Metodos.pasarCola(c));
        System.out.print("[ ");
        while (!copia.esVacia()) {
            System.out.print(copia.primero() + " ");
            copia.desencolar();
        }
        System.out.println("] (Frente a la izquierda)");
    }

    private static void imprimirCP(ColaPrioridadTDA cp) {
        ColaPrioridadTDA aux = new ColaPrioridadEstaticaA(100);

        System.out.print("[ ");
        while (!cp.esVacia()) {
            int val = cp.verMax();
            int prio = cp.verPrioridadMax();
            System.out.print("(" + val + ", P:" + prio + ") ");
            aux.insertar(val, prio);
            cp.extraerMax();
        }
        System.out.println("]");

        while (!aux.esVacia()) {
            int val = aux.verMax();
            int prio = aux.verPrioridadMax();
            cp.insertar(val, prio);
            aux.extraerMax();
        }
    }
}
