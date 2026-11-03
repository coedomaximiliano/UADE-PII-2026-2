package TP1.Parte1_Pila;

public class UtilidadesPila {

    public static Pila pasarPila(Pila origen) {
        Pila nueva = new PilaVarianteA(origen.capacidad());
        while (!origen.esVacia()) {
            nueva.apilar(origen.desapilar());
        }
        return nueva;
    }

    public static Pila copiarPila(Pila p) {
        Pila aux = new PilaVarianteA(p.capacidad());
        while (!p.esVacia()) {
            aux.apilar(p.desapilar());
        }
        Pila copia = new PilaVarianteA(p.capacidad());
        while (!aux.esVacia()) {
            int x = aux.desapilar();
            p.apilar(x);
            copia.apilar(x);
        }
        return copia;
    }

    public static Pila invertirPila(Pila p) {
        Pila resultado = new PilaVarianteA(p.capacidad());
        invertirRec(p, resultado);
        return resultado;
    }

    private static void invertirRec(Pila p, Pila resultado) {
        if (p.esVacia()) {
            return;
        }
        int x = p.desapilar();
        resultado.apilar(x);
        invertirRec(p, resultado);
        p.apilar(x);
    }

    public static boolean masDeUnaOcurrencia(Pila p) {
        Pila copia = copiarPila(p);
        boolean hayRepetido = false;
        while (!copia.esVacia() && !hayRepetido) {
            int x = copia.desapilar();
            if (contiene(copia, x)) {
                hayRepetido = true;
            }
        }
        return hayRepetido;
    }

    private static boolean contiene(Pila p, int valor) {
        Pila aux = new PilaVarianteA(p.capacidad());
        boolean encontrado = false;
        while (!p.esVacia()) {
            int x = p.desapilar();
            aux.apilar(x);
            if (x == valor) {
                encontrado = true;
            }
        }
        while (!aux.esVacia()) {
            p.apilar(aux.desapilar());
        }
        return encontrado;
    }

    public static Pila eliminarImpares(Pila p) {
        Pila aux = new PilaVarianteA(p.capacidad());
        while (!p.esVacia()) {
            aux.apilar(p.desapilar());
        }
        Pila pares = new PilaVarianteA(p.capacidad());
        while (!aux.esVacia()) {
            int x = aux.desapilar();
            p.apilar(x);
            if (x % 2 == 0) {
                pares.apilar(x);
            }
        }
        return pares;
    }
}
