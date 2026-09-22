//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("--- 1 Prueba de pasarCola ---");
        ColaA origen = new ColaA(10);
        origen.encolar(10);
        origen.encolar(20);
        origen.encolar(30);
        origen.encolar(40);
        ColaA destino = origen.pasarCola(origen);
        System.out.println("Cola destino armada : ");
        while(!destino.esVacia()){
            System.out.println(" " + destino.desencolar());
        }
        System.out.println("Cola origen final :  (¿Está vacía?: " + origen.esVacia() + ")\\n");
        while(!origen.esVacia()){
            System.out.println(" " + origen.desencolar());
        }

        // ---------------------------------------------------------------------
        // PRUEBA 2: invertirColaConPila
        // ---------------------------------------------------------------------
        System.out.println("--- 2 Prueba de invertirColaConPila ---");
        ColaA c1 = new ColaA(10);
        c1.encolar(1);
        c1.encolar(2);
        c1.encolar(3);
        c1.encolar(4);
        c1.encolar(5);
        ColaA c1Invertida = (ColaA) c1.invertirColaConPila(c1);
        System.out.println("Nueva cola invertida: ");
        while(!c1Invertida.esVacia()){
            System.out.println(" " + c1Invertida.desencolar());
        }
        System.out.println("Cola c1 preservada : \\n");
        while(!c1.esVacia()){
            System.out.println(" " + c1.desencolar());
        }
        // ---------------------------------------------------------------------
        // PRUEBA 3: invertirColaSinPila
        // ---------------------------------------------------------------------
        System.out.println("--- 3 Prueba de invertirColaSinPila (Recursivo) ---");
        ColaA c2 = new ColaA(10);
        c2.encolar(100);
        c2.encolar(200);
        c2.encolar(300);
        c2.invertirSinPila(c2);
        System.out.println("Cola luego de invertir: \\n");
        while(!c2.esVacia()){
            System.out.println(" " + c2.desencolar());
        }
        // ---------------------------------------------------------------------
        // PRUEBA 4: finalCoincide
        // ---------------------------------------------------------------------
        System.out.println("--- 4 Prueba de finalCoincide ---");
        ColaA cola1 = new ColaA(10);
        cola1.encolar(5);
        cola1.encolar(10);
        cola1.encolar(15);
        cola1.encolar(20);
        cola1.encolar(25);
        ColaA cola2 = new ColaA(10);

        cola2.encolar(99);
        cola2.encolar(88);
        cola2.encolar(15);
        cola2.encolar(20);
        cola2.encolar(25);
        boolean coincideK3 = cola1.finalCoincide(cola1, cola2, 3);
        System.out.println("¿Coinciden los últimos 3 elementos?: " + coincideK3 + " (Esperado: true)");
        boolean coincideK4 = cola2.finalCoincide(cola1, cola2, 4);
        System.out.println("¿Coinciden los últimos 4 elementos?: " + coincideK4 + " (Esperado: false)");
        System.out.println("=================================================");
    }
}