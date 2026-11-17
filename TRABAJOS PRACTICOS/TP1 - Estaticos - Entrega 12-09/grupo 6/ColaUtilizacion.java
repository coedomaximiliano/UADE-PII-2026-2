public class ColaUtilizacion {

    // Clase de utilidades (solo métodos estáticos): no se instancia.
    private ColaUtilizacion() {
    }

    // Único lugar donde se elige la variante: cambiando esta línea,
    // todos los métodos pasan a usar la otra implementación.
    private static Cola crear(int capacidad) {
        return new ColaB(capacidad);
    }

    // Devuelve una nueva cola con todos los elementos de origen,
    // en el mismo orden, dejando origen vacía.
    public static Cola pasarCola(Cola origen) {
        Cola destino = crear(origen.tamanio());

        while (!origen.esVacia()) {
            destino.encolar(origen.desencolar());
        }

        return destino;
    }

    // Devuelve una nueva cola con los elementos de c en orden inverso,
    // usando una Pila auxiliar. c queda con su contenido original.
    public static Cola invertirColaConPila(Cola c) {
        Pila p = new PilaVarianteA(c.tamanio());
        Cola auxiliar = crear(c.tamanio());

        while (!c.esVacia()) {
            int x = c.desencolar();
            p.apilar(x);
            auxiliar.encolar(x);
        }

        // Restaura c
        while (!auxiliar.esVacia()) {
            c.encolar(auxiliar.desencolar());
        }

        Cola invertida = crear(c.tamanio());
        while (!p.esVacia()) {
            invertida.encolar(p.desapilar());
        }

        return invertida;
    }

    // Invierte c usando solo recursión, sin estructuras auxiliares.
    public static Cola invertirColaSinPila(Cola c) {
        if (!c.esVacia()) {
            int x = c.desencolar();
            invertirColaSinPila(c);
            c.encolar(x);
        }
        return c;
    }

    // Indica si los últimos k elementos de c1 coinciden, en el mismo orden,
    // con los últimos k de c2. Ambas colas quedan con su contenido original.
    public static boolean finalCoincide(Cola c1, Cola c2, int k) {
        Cola invertida1 = invertirColaSinPila(copiarCola(c1));
        Cola invertida2 = invertirColaSinPila(copiarCola(c2));
        boolean coinciden = true;

        for (int i = 0; i < k; i++) {
            if (invertida1.esVacia() || invertida2.esVacia()) {
                coinciden = false;
                break;
            }
            if (invertida1.desencolar() != invertida2.desencolar()) {
                coinciden = false;
                break;
            }
        }

        return coinciden;
    }

    // Devuelve una copia de origen, dejando origen con su contenido original.
    private static Cola copiarCola(Cola origen) {
        Cola copia = crear(origen.tamanio());
        Cola auxiliar = crear(origen.tamanio());

        while (!origen.esVacia()) {
            int x = origen.desencolar();
            copia.encolar(x);
            auxiliar.encolar(x);
        }

        // Restaura origen
        while (!auxiliar.esVacia()) {
            origen.encolar(auxiliar.desencolar());
        }

        return copia;
    }
}
