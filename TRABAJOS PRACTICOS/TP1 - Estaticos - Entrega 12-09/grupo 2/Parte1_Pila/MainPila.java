package TP1.Parte1_Pila;

public class MainPila {

    public static void main(String[] args) {
        System.out.println("- PRUEBAS TDA PILA -\n");

        System.out.println(">> Variante A (tope en el ultimo lugar ocupado):");
        Pila a = new PilaVarianteA(5);
        a.apilar(1);
        a.apilar(2);
        a.apilar(3);
        System.out.println("   tope      = " + a.tope() + "   (esperado 3)");
        System.out.println("   desapilar = " + a.desapilar() + "   (esperado 3)");
        System.out.println("   tope      = " + a.tope() + "   (esperado 2)\n");

        System.out.println(">> Variante B (tope en la posicion 0):");
        Pila b = new PilaVarianteB(5);
        b.apilar(1);
        b.apilar(2);
        b.apilar(3);
        System.out.println("   tope      = " + b.tope() + "   (esperado 3)");
        System.out.println("   desapilar = " + b.desapilar() + "   (esperado 3)");
        System.out.println("   tope      = " + b.tope() + "   (esperado 2)\n");

        System.out.println(">> pasarPila:");
        Pila origen = crearPila(new int[]{1, 2, 3, 4});
        System.out.print("   origen:      ");
        imprimir(origen);
        Pila pasada = UtilidadesPila.pasarPila(origen);
        System.out.print("   nueva:       ");
        imprimir(pasada);
        System.out.println("   origen vacia? " + origen.esVacia() + "   (esperado true)\n");

        System.out.println(">> copiarPila:");
        Pila p = crearPila(new int[]{5, 6, 7});
        Pila copia = UtilidadesPila.copiarPila(p);
        System.out.print("   p (original):");
        imprimir(p);
        System.out.print("   copia:       ");
        imprimir(copia);
        System.out.println();

        System.out.println(">> invertirPila (recursiva):");
        Pila pi = crearPila(new int[]{1, 2, 3, 4});
        Pila inv = UtilidadesPila.invertirPila(pi);
        System.out.print("   p (original):");
        imprimir(pi);
        System.out.print("   invertida:   ");
        imprimir(inv);
        System.out.println();

        System.out.println(">> masDeUnaOcurrencia:");
        Pila sinRep = crearPila(new int[]{1, 2, 3, 4});
        Pila conRep = crearPila(new int[]{1, 2, 3, 2});
        System.out.println("   {1,2,3,4} -> " + UtilidadesPila.masDeUnaOcurrencia(sinRep) + "   (esperado false)");
        System.out.println("   {1,2,3,2} -> " + UtilidadesPila.masDeUnaOcurrencia(conRep) + "   (esperado true)");
        System.out.print("   conRep intacta: ");
        imprimir(conRep);
        System.out.println();

        System.out.println(">> eliminarImpares:");
        Pila pe = crearPila(new int[]{1, 2, 3, 4, 5, 6});
        Pila pares = UtilidadesPila.eliminarImpares(pe);
        System.out.print("   p (original):");
        imprimir(pe);
        System.out.print("   pares:       ");
        imprimir(pares);
    }

    private static Pila crearPila(int[] valores) {
        Pila p = new PilaVarianteA(valores.length);
        for (int v : valores) {
            p.apilar(v);
        }
        return p;
    }

    private static void imprimir(Pila p) {
        Pila copia = UtilidadesPila.copiarPila(p);
        System.out.print(" [tope] ");
        while (!copia.esVacia()) {
            System.out.print(copia.desapilar() + " ");
        }
        System.out.println("[fondo]");
    }
}
