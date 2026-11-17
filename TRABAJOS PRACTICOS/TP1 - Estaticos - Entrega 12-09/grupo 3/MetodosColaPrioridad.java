// TP1 - Algoritmos y Estructuras de Datos II
// Pedro Stella - Bautista Chavarri

/**
 * Metodos de utilizacion del TDA Cola con Prioridad (Parte 3.3).
 * Sobre los parametros solo se usan las operaciones de las interfaces ColaPrioridad y Cola,
 * asi que funcionan igual con cualquier variante. Las estructuras nuevas se crean con
 * capacidad CAPACIDAD: las colas recibidas deben tener a lo sumo CAPACIDAD elementos.
 */
public class MetodosColaPrioridad {

    private static final int CAPACIDAD = 100;

    // combinar(cp1: ColaPrioridad, cp2: ColaPrioridad): ColaPrioridad
    // devuelve una cola con prioridad nueva con todos los elementos de cp1 y cp2 (con igual
    // prioridad se conserva el orden de insercion y los de cp1 salen antes); cp1 y cp2 quedan vacias
    // pre:  cp1 y cp2 son colas distintas y entre las dos tienen a lo sumo CAPACIDAD elementos
    // costo: O(n^2), n = n1 + n2: n extracciones de O(n) si las entradas son desordenadas
    //        (O(n) si son ordenadas); insertar en el resultado desordenado es O(1)
    public static ColaPrioridad combinar(ColaPrioridad cp1, ColaPrioridad cp2) {
        ColaPrioridad resultado = new ColaPrioridadDesordenada(CAPACIDAD);
        // todo cp1 antes que todo cp2: por eso, con igual prioridad, salen primero los de cp1
        moverTodo(cp1, resultado);
        moverTodo(cp2, resultado);
        return resultado;
    }

    // invertirColaConColaPrioridad(c: Cola): Cola
    // devuelve una cola nueva con los elementos de c en orden inverso (usa una ColaPrioridad
    // auxiliar); c queda vacia
    // costo: O(n): con la auxiliar ordenada cada insertar cae al final sin correr nada
    //        y cada extraerMax es O(1)
    public static Cola invertirColaConColaPrioridad(Cola c) {
        ColaPrioridad aux = new ColaPrioridadOrdenada(CAPACIDAD);
        // prioridades crecientes 0, 1, 2, ...: el ultimo de c queda con la mayor y sale primero
        int prioridad = 0;
        while (!c.esVacia()) {
            aux.insertar(c.desencolar(), prioridad);
            prioridad++;
        }
        Cola resultado = new ColaCircular(CAPACIDAD);
        while (!aux.esVacia()) {
            resultado.encolar(aux.extraerMax());
        }
        return resultado;
    }

    // sumarValoresPrioridadPar(cp: ColaPrioridad): entero
    // devuelve la suma de los valores cuya prioridad es par; cp queda con su contenido y orden original
    // costo: O(n^2): cp se vacia y se vuelve a llenar, y la auxiliar desordenada extrae en O(n)
    public static int sumarValoresPrioridadPar(ColaPrioridad cp) {
        ColaPrioridad aux = new ColaPrioridadDesordenada(CAPACIDAD);
        int suma = 0;
        while (!cp.esVacia()) {
            int prioridad = cp.prioridadMax();
            int valor = cp.extraerMax();
            if (prioridad % 2 == 0) {  // tambien vale para negativas: -3 % 2 es -1
                suma = suma + valor;
            }
            aux.insertar(valor, prioridad);
        }
        // vuelven en el mismo orden en que salieron: se conserva el orden entre iguales
        moverTodo(aux, cp);
        return suma;
    }

    // pasa cada par (valor, prioridad) de origen a destino en orden de salida; origen queda vacia
    private static void moverTodo(ColaPrioridad origen, ColaPrioridad destino) {
        while (!origen.esVacia()) {
            int prioridad = origen.prioridadMax();
            int valor = origen.extraerMax();
            destino.insertar(valor, prioridad);
        }
    }
}
