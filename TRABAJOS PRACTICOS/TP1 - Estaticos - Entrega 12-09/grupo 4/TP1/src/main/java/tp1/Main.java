package tp1;

import tp1.pila.*;
import tp1.cola.*;
import tp1.colaprioridad.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println("       PRUEBAS TP1 - ESTRUCTURAS     ");
        System.out.println("=====================================\n");

        System.out.println("=== PRUEBAS TDA PILA ===");
        probarPila();
        
        System.out.println("\n=== PRUEBAS TDA COLA ===");
        probarCola();
        
        System.out.println("\n=== PRUEBAS TDA COLA CON PRIORIDAD ===");
        probarColaPrioridad();
    }
    
    private static void probarPila() {
        UtilizacionPila up = new UtilizacionPila();
        
        Pila p1 = new PilaA(); p1.crear();
        p1.apilar(1); p1.apilar(2); p1.apilar(3); p1.apilar(4); p1.apilar(5);
        
        Pila p2 = up.copiarPila(p1);
        imprimirPila(p2, "copiarPila(p1)"); // 5 4 3 2 1
        
        Pila p3 = up.invertirPila(p1);
        imprimirPila(p3, "invertirPila(p1)"); // 1 2 3 4 5
        
        Pila p4 = up.eliminarImpares(p1);
        imprimirPila(p4, "eliminarImpares(p1)"); // 4 2
        
        p1.apilar(3); // Agregamos un 3 para que haya duplicados (ya había un 3 original)
        System.out.println("masDeUnaOcurrencia(p1) [esperado true]: " + up.masDeUnaOcurrencia(p1));
    }

    private static void probarCola() {
        UtilizacionCola uc = new UtilizacionCola();
        
        Cola c1 = new ColaA(); c1.crear();
        c1.encolar(1); c1.encolar(2); c1.encolar(3);
        
        Cola c2 = uc.invertirColaConPila(c1); // esto la consume en nuestra impl
        imprimirCola(c2, "invertirColaConPila(c1)"); // 3 2 1
        
        Cola c3 = new ColaB(); c3.crear();
        c3.encolar(10); c3.encolar(20); c3.encolar(30);
        Cola c4 = uc.invertirColaSinPila(c3);
        imprimirCola(c4, "invertirColaSinPila(c3)"); // 30 20 10
        
        Cola c5 = new ColaA(); c5.crear();
        c5.encolar(100); c5.encolar(1); c5.encolar(2);
        Cola c6 = new ColaA(); c6.crear();
        c6.encolar(200); c6.encolar(300); c6.encolar(1); c6.encolar(2);
        
        System.out.println("finalCoincide(c5, c6, 2) [esperado true]: " + uc.finalCoincide(c5, c6, 2));
    }

    private static void probarColaPrioridad() {
        UtilizacionColaPrioridad ucp = new UtilizacionColaPrioridad();
        
        ColaPrioridad cp1 = new ColaPrioridadA(); cp1.crear();
        cp1.insertar(10, 1);
        cp1.insertar(20, 2);
        
        ColaPrioridad cp2 = new ColaPrioridadB(); cp2.crear();
        cp2.insertar(30, 2);
        cp2.insertar(40, 3);
        
        ColaPrioridad combinada = ucp.combinar(cp1, cp2);
        imprimirCP(combinada, "combinar(cp1, cp2)"); 
        
        Cola c1 = new ColaA(); c1.crear();
        c1.encolar(1); c1.encolar(2); c1.encolar(3);
        Cola cInv = ucp.invertirColaConColaPrioridad(c1);
        imprimirCola(cInv, "invertirColaConColaPrioridad(c1)"); // 3 2 1
        
        ColaPrioridad cp3 = new ColaPrioridadB(); cp3.crear();
        cp3.insertar(100, 2); // prio par (suma 100)
        cp3.insertar(200, 3); // prio impar (ignora)
        cp3.insertar(50, 4);  // prio par (suma 50)
        System.out.println("sumarValoresPrioridadPar(cp3) [esperado 150]: " + ucp.sumarValoresPrioridadPar(cp3));
    }
    
    // Funciones auxiliares destructivas para imprimir por consola
    private static void imprimirPila(Pila p, String nombre) {
        System.out.print(nombre + " (tope -> fondo): ");
        while (!p.esVacia()) {
            System.out.print(p.tope() + " ");
            p.desapilar();
        }
        System.out.println();
    }

    private static void imprimirCola(Cola c, String nombre) {
        System.out.print(nombre + " (frente -> fin): ");
        while (!c.esVacia()) {
            System.out.print(c.frente() + " ");
            c.desencolar();
        }
        System.out.println();
    }
    
    private static void imprimirCP(ColaPrioridad cp, String nombre) {
        System.out.print(nombre + " (mayor prio -> menor): ");
        while (!cp.esVacia()) {
            System.out.print("[" + cp.verMax() + " (p:" + cp.prioridadMax() + ")] ");
            cp.extraerMax();
        }
        System.out.println();
    }
}
