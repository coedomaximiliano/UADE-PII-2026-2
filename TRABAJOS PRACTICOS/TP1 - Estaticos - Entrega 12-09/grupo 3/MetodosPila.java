// TP1 - Algoritmos y Estructuras de Datos II
// Pedro Stella - Bautista Chavarri

/**
 * Parte 1.3: metodos que usan solo las operaciones de la interfaz Pila, por eso
 * funcionan igual con cualquier variante. Las pilas nuevas son PilaTopeFinal de
 * capacidad CAPACIDAD, asi que p puede tener a lo sumo CAPACIDAD elementos.
 * Los costos suponen operaciones O(1) (Variante A); si p es una PilaTopeInicio,
 * cada apilar/desapilar sobre p cuesta O(n) y el costo se multiplica por n.
 */
public class MetodosPila {

    private static final int CAPACIDAD = 100;

    // pasarPila(origen: Pila): Pila
    // devuelve una pila nueva con los elementos de origen en orden inverso; origen queda vacia
    // costo: O(n)
    public static Pila pasarPila(Pila origen) {
        Pila destino = new PilaTopeFinal(CAPACIDAD);
        while (!origen.esVacia()) {
            destino.apilar(origen.desapilar());
        }
        return destino;
    }

    // copiarPila(p: Pila): Pila
    // devuelve una copia de p con el mismo orden; p queda con su contenido y orden original
    // costo: O(n)
    public static Pila copiarPila(Pila p) {
        Pila aux = pasarPila(p);   // aux tiene p invertida y p queda vacia
        Pila copia = new PilaTopeFinal(CAPACIDAD);
        while (!aux.esVacia()) {
            int x = aux.desapilar();
            p.apilar(x);
            copia.apilar(x);
        }
        return copia;
    }

    // invertirPila(p: Pila): Pila
    // devuelve una pila nueva con los elementos de p en orden inverso; p queda como estaba
    // costo: O(n): una llamada recursiva por elemento
    public static Pila invertirPila(Pila p) {
        Pila invertida = new PilaTopeFinal(CAPACIDAD);
        invertirRecursivo(p, invertida);
        return invertida;
    }

    // saca el tope de p y lo apila en invertida antes de seguir con el resto (asi queda al fondo);
    // al volver de la recursion lo devuelve a p, por eso p termina como estaba
    private static void invertirRecursivo(Pila p, Pila invertida) {
        if (!p.esVacia()) {
            int x = p.desapilar();
            invertida.apilar(x);
            invertirRecursivo(p, invertida);
            p.apilar(x);
        }
    }

    // masDeUnaOcurrencia(p: Pila): boolean
    // devuelve true si algun elemento aparece mas de una vez en p; p queda como estaba
    // costo: O(n^2): cada elemento que se saca se busca en lo que queda de p
    public static boolean masDeUnaOcurrencia(Pila p) {
        Pila sacados = new PilaTopeFinal(CAPACIDAD);
        Pila auxBusqueda = new PilaTopeFinal(CAPACIDAD);   // pertenece() la deja vacia: se reusa
        boolean repetido = false;
        while (!p.esVacia() && !repetido) {
            int x = p.desapilar();
            repetido = pertenece(p, x, auxBusqueda);
            sacados.apilar(x);
        }
        while (!sacados.esVacia()) {
            p.apilar(sacados.desapilar());
        }
        return repetido;
    }

    // devuelve true si x esta en p; p queda como estaba.
    // aux llega vacia y vuelve a quedar vacia
    private static boolean pertenece(Pila p, int x, Pila aux) {
        boolean encontrado = false;
        while (!p.esVacia() && !encontrado) {
            int y = p.desapilar();
            if (y == x) {
                encontrado = true;
            }
            aux.apilar(y);
        }
        while (!aux.esVacia()) {
            p.apilar(aux.desapilar());
        }
        return encontrado;
    }

    // eliminarImpares(p: Pila): Pila
    // devuelve una pila nueva con los pares de p en el mismo orden relativo; p queda como estaba
    // costo: O(n)
    public static Pila eliminarImpares(Pila p) {
        Pila aux = pasarPila(p);   // aux tiene p invertida y p queda vacia
        Pila pares = new PilaTopeFinal(CAPACIDAD);
        while (!aux.esVacia()) {
            int x = aux.desapilar();
            p.apilar(x);
            if (x % 2 == 0) {   // vale para el 0 y los negativos: -3 % 2 da -1
                pares.apilar(x);
            }
        }
        return pares;
    }
}
