// TP1 - Algoritmos y Estructuras de Datos II
// Pedro Stella - Bautista Chavarri

/**
 * Metodos de utilizacion del TDA Cola (Parte 2.3).
 * Reciben y devuelven la interfaz Cola y solo usan sus operaciones, asi que funcionan
 * igual con ColaEstatica o con ColaCircular. Las colas y pilas nuevas se crean con
 * capacidad CAPACIDAD: las colas recibidas deben tener a lo sumo CAPACIDAD elementos.
 */
public class MetodosCola {

    private static final int CAPACIDAD = 100;

    // pasarCola(origen: Cola): Cola
    // devuelve una cola nueva con los elementos de origen en el mismo orden; origen queda vacia
    // costo: O(n)
    public static Cola pasarCola(Cola origen) {
        Cola resultado = new ColaCircular(CAPACIDAD);
        while (!origen.esVacia()) {
            resultado.encolar(origen.desencolar());
        }
        return resultado;
    }

    // invertirColaConPila(c: Cola): Cola
    // devuelve una cola nueva con los elementos de c en orden inverso (usa una Pila auxiliar); c queda vacia
    // costo: O(n)
    public static Cola invertirColaConPila(Cola c) {
        Pila aux = new PilaTopeFinal(CAPACIDAD);
        while (!c.esVacia()) {
            aux.apilar(c.desencolar());
        }
        // la pila los devuelve al reves: el ultimo de c sale primero
        Cola resultado = new ColaCircular(CAPACIDAD);
        while (!aux.esVacia()) {
            resultado.encolar(aux.desapilar());
        }
        return resultado;
    }

    // invertirColaSinPila(c: Cola): Cola
    // devuelve una cola nueva con los elementos de c en orden inverso usando solo recursion
    // (ni pila, ni arreglo, ni cola extra: la unica cola creada es la que se devuelve); c queda vacia
    // costo: O(n): una llamada recursiva por elemento
    public static Cola invertirColaSinPila(Cola c) {
        if (c.esVacia()) {
            return new ColaCircular(CAPACIDAD);
        }
        int x = c.desencolar();
        Cola resultado = invertirColaSinPila(c);
        resultado.encolar(x);   // al volver de la recursion se encola del ultimo al primero
        return resultado;
    }

    // finalCoincide(c1: Cola, c2: Cola, k: entero): boolean
    // devuelve true si los ultimos k elementos de c1 y de c2 coinciden en el mismo orden
    // (k == 0 da true; si alguna tiene menos de k elementos da false); ambas quedan como estaban
    // pre:  k >= 0 y c1, c2 son colas distintas. Si una es ColaEstatica, despues de su ultimo
    //       elemento necesita tantos lugares libres como elementos tiene: se la vacia y se la
    //       vuelve a llenar sin reutilizar lo liberado (si no, encolar lanza "Cola llena")
    // costo: O(n1 + n2): cada elemento se mueve dos veces
    public static boolean finalCoincide(Cola c1, Cola c2, int k) {
        if (k < 0) {
            throw new IllegalArgumentException("k debe ser >= 0");
        }
        Cola aux1 = new ColaCircular(CAPACIDAD);
        Cola aux2 = new ColaCircular(CAPACIDAD);
        int n1 = pasarContando(c1, aux1);
        int n2 = pasarContando(c2, aux2);
        if (n1 < k || n2 < k) {
            pasarN(aux1, c1, n1);
            pasarN(aux2, c2, n2);
            return false;
        }
        // los primeros n - k de cada una vuelven sin compararse
        pasarN(aux1, c1, n1 - k);
        pasarN(aux2, c2, n2 - k);
        // los ultimos k se comparan de a pares mientras vuelven; se sigue aunque
        // haya una diferencia para que las dos colas queden completas
        boolean coinciden = true;
        for (int i = 0; i < k; i++) {
            int x = aux1.desencolar();
            int y = aux2.desencolar();
            if (x != y) {
                coinciden = false;
            }
            c1.encolar(x);
            c2.encolar(y);
        }
        return coinciden;
    }

    // pasa todo origen a destino en el mismo orden y devuelve cuantos elementos eran
    private static int pasarContando(Cola origen, Cola destino) {
        int cantidad = 0;
        while (!origen.esVacia()) {
            destino.encolar(origen.desencolar());
            cantidad++;
        }
        return cantidad;
    }

    // pasa los primeros n elementos de origen a destino
    private static void pasarN(Cola origen, Cola destino, int n) {
        for (int i = 0; i < n; i++) {
            destino.encolar(origen.desencolar());
        }
    }
}
