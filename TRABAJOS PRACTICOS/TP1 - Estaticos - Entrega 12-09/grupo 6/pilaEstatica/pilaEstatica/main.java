public class main {
    
    public static void main(String[] args) {
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
        
        System.out.println("=== FIN DE PRUEBAS ===");
    }
    
    // Método auxiliar: crear pila con elementos (el último es tope)
    private static Pila crearPilaConElementos(int... elementos) {
        Pila p = new PilaVarianteA();
        for (int x : elementos) {
            p.apilar(x);
        }
        return p;
    }
    
    // Método auxiliar: imprimir pila 
    private static void imprimirPila(Pila p) {
        if (p.esVacia()) {
            System.out.println("[ ]");
            return;
        }
        
        Pila temp = new PilaVarianteA();
        Pila invertida = new PilaVarianteA();
        
        // Desapila p hacia temp (invierte)
        while (!p.esVacia()) {
            temp.apilar(p.desapilar());
        }
        
        // Desapila temp hacia invertida (vuelve al orden original) y a p (restaura)
        System.out.print("[ ");
        while (!temp.esVacia()) {
            int x = temp.desapilar();
            invertida.apilar(x);
            p.apilar(x);
            System.out.print(x + " ");
        }
        System.out.println("]");
    }
}
