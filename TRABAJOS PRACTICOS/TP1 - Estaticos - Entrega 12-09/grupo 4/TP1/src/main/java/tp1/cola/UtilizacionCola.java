package tp1.cola;

import tp1.pila.Pila;
import tp1.pila.PilaA;

public class UtilizacionCola {

    // 6. pasarCola
    public Cola pasarCola(Cola origen) {
        Cola nueva = new ColaA();
        nueva.crear();

        while (!origen.esVacia()) {
            nueva.encolar(origen.frente());
            origen.desencolar();
        }
        return nueva;
    }

    // 7. invertirColaConPila
    public Cola invertirColaConPila(Cola c) {
        Cola invertida = new ColaA();
        invertida.crear();
        Pila p = new PilaA();
        p.crear();

        while (!c.esVacia()) {
            p.apilar(c.frente());
            c.desencolar();
        }

        while (!p.esVacia()) {
            invertida.encolar(p.tope());
            p.desapilar();
        }

        return invertida;
    }

    // 8. invertirColaSinPila (recursiva)
    public Cola invertirColaSinPila(Cola c) {
        Cola invertida = new ColaA();
        invertida.crear();
        invertirColaSinPilaRec(c, invertida);
        return invertida;
    }

    private void invertirColaSinPilaRec(Cola c, Cola invertida) {
        if (!c.esVacia()) {
            int frente = c.frente();
            c.desencolar();
            invertirColaSinPilaRec(c, invertida);
            invertida.encolar(frente);
        }
    }

    // 9. finalCoincide
    public boolean finalCoincide(Cola c1, Cola c2, int k) {
        Cola aux1 = new ColaA();
        aux1.crear();
        Cola aux2 = new ColaA();
        aux2.crear();

        int len1 = 0;
        while (!c1.esVacia()) {
            aux1.encolar(c1.frente());
            c1.desencolar();
            len1++;
        }

        int len2 = 0;
        while (!c2.esVacia()) {
            aux2.encolar(c2.frente());
            c2.desencolar();
            len2++;
        }

        // Restaurar c1 y c2
        while (!aux1.esVacia()) {
            c1.encolar(aux1.frente());
            aux1.desencolar();
        }
        while (!aux2.esVacia()) {
            c2.encolar(aux2.frente());
            aux2.desencolar();
        }

        if (len1 < k || len2 < k) {
            return false;
        }

        boolean coincide = true;

        for (int i = 0; i < len1 - k; i++) {
            aux1.encolar(c1.frente());
            c1.desencolar();
        }
        for (int i = 0; i < len2 - k; i++) {
            aux2.encolar(c2.frente());
            c2.desencolar();
        }

        for (int i = 0; i < k; i++) {
            int v1 = c1.frente();
            int v2 = c2.frente();
            if (v1 != v2) {
                coincide = false;
            }
            aux1.encolar(v1);
            c1.desencolar();
            aux2.encolar(v2);
            c2.desencolar();
        }

        while (!aux1.esVacia()) {
            c1.encolar(aux1.frente());
            aux1.desencolar();
        }
        while (!aux2.esVacia()) {
            c2.encolar(aux2.frente());
            aux2.desencolar();
        }

        return coincide;
    }
}
