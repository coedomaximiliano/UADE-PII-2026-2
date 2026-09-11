import Implementaciones.ColaEstaticaA;
import Implementaciones.ColaPrioridadEstaticaA;
import Implementaciones.PilaEstaticaA;
import TDAs.ColaPrioridadTDA;
import TDAs.ColaTDA;
import TDAs.PilaTDA;

public class Metodos {


    // SECCION METODOS PILA
    // # Aclaración: siempre que creamos una Pila utilizamos PilaEstaticaA, pero todos los metodos funcionan
    // de igual manera con PilaEstaticaB.

    public static PilaTDA pasarPila(PilaTDA origen) {
        PilaTDA aux = new PilaEstaticaA(100);
        PilaTDA nuevaPila = new PilaEstaticaA(100);

        // 1. Apila el tope de origen en aux y desapila origen
        while (!origen.esVacia()) {
            aux.apilar(origen.tope());
            origen.desapilar();
        }

        // 2. Lo mismo que antes pero de aux a la nuevaPila
        while (!aux.esVacia()) {
            nuevaPila.apilar(aux.tope());
            aux.desapilar();
        }

        return nuevaPila;
    }

    public static PilaTDA copiarPila(PilaTDA p){
        PilaTDA aux = new PilaEstaticaA(100);
        PilaTDA copiaPila = new PilaEstaticaA(100);

        // 1. Apila el tope de p en aux y lo desapila
        while (!p.esVacia()) {
            aux.apilar(p.tope());
            p.desapilar();
        }

        // 2. Lo mismo que antes pero de aux a la nuevaPila
        while (!aux.esVacia()) {
            copiaPila.apilar(aux.tope());
            p.apilar(aux.tope());
            aux.desapilar();
        }

        return copiaPila;
    }

    public static PilaTDA invertirPila(PilaTDA p){
        PilaTDA pilaInvertida = new PilaEstaticaA(100);
        invertirRecursivo(p, pilaInvertida);
        return pilaInvertida;
    }

    private static void invertirRecursivo(PilaTDA p, PilaTDA pilaInvertida) {
        // 1. Tomamos el tope de p y lo desapilamos
        if (!p.esVacia()) {
            int elem = p.tope();
            p.desapilar();

            // 2. Lo apilamos de forma inversa
            pilaInvertida.apilar(elem);

            // 3. Llamada recursiva
            invertirRecursivo(p, pilaInvertida);

            // 4. Volvemos a apilar p al terminar toda la recursion
            p.apilar(elem);
        }
    }

    public static boolean masDeUnaOcurrencia(PilaTDA p){
        PilaTDA copia = copiarPila(p); // Usamos el metodo copiarPila(p) anterior
        boolean duplicado = false;

        // 1. Tomamos el tope de la copia de p
        while (!copia.esVacia() && !duplicado){
            int actual = copia.tope();
            copia.desapilar();

            PilaTDA aux = new PilaEstaticaA(100);

            // 2. Lo comparamos con todos los elementos de copia
            while (!copia.esVacia()) {
                int elem = copia.tope();
                copia.desapilar();

                if (elem == actual)
                    duplicado = true;

                // 3. Guardamos todos los elementos en una pila auxiliar, para poder reconstruir la original mas tarde
                aux.apilar(elem);
            }

            // 4. Reconstruimos copia y vaciamos aux
            while (!aux.esVacia()) {
                copia.apilar(aux.tope());
                aux.desapilar();
            }
        }

        return duplicado;
    }

    public static PilaTDA eliminarImpares(PilaTDA  p){
        PilaTDA pilaPares = new PilaEstaticaA(100);
        // 1. Invertimos p, para despues poder apilar los pares en el orden correcto
        PilaTDA pilaInvertida = invertirPila(p);

        // 2. Si tope es par, se apila y sino se descarta. Al final desapilamos en cualquier caso
        while (!pilaInvertida.esVacia()) {
            int elemPar = pilaInvertida.tope();
            if (elemPar % 2 == 0)
                pilaPares.apilar(elemPar);

            pilaInvertida.desapilar();
        }

        return pilaPares;
    }


    // SECCION METODOS COLA
    // # Aclaración: siempre que creamos una Cola utilizamos ColaEstaticaA, pero todos los metodos funcionan
    // de igual manera con ColaEstaticaB.

    public static ColaTDA pasarCola(ColaTDA origen) {
        ColaTDA nuevaCola = new ColaEstaticaA(100);

        // Pasamos todos los elementos a una cola nueva
        while (!nuevaCola.esVacia()) {
            nuevaCola.encolar(origen.primero());
            origen.desencolar();
        }
        return nuevaCola;
    }

    public static ColaTDA invertirColaConPila(ColaTDA origen){
        ColaTDA colaInvertida = new ColaEstaticaA(100);
        PilaTDA aux = new PilaEstaticaA(100);

        // 1. Metemos todos los elementos en una pila
        while(!origen.esVacia()){
            aux.apilar(origen.primero());
            origen.desencolar();
        }
        // 2. Los metemos en una cola (entran invertidos)
        while(!aux.esVacia()){
            colaInvertida.encolar(aux.tope());
            aux.desapilar();
        }

        return colaInvertida;
    }

    public static ColaTDA invertirColaSinPila(ColaTDA origen){
        if (origen.esVacia()) {
            return origen; // 1. Caso base: Cola vacia
        }

        int primero = origen.primero();
        origen.desencolar();

        // 1. Inviertimos recursivamente el resto de la cola
        invertirColaSinPila(origen);

        // 2. Al volver, el que era el primero pasa al final
        origen.encolar(primero);

        return origen;
    }

    public static boolean finalCoincide(ColaTDA c1, ColaTDA c2, int k){
        PilaTDA aux1 = new PilaEstaticaA(100);
        PilaTDA aux2 = new PilaEstaticaA(100);

        // 1. Vaciamos las dos colas y metemos sus elementos en dos pilas
        while(!c1.esVacia()){
            aux1.apilar(c1.primero());
            c1.desencolar();
        }
        while(!c2.esVacia()){
            aux2.apilar(c2.primero());
            c2.desencolar();
        }

        // 2. Buscamos coincidencias entre los elementos del tope de las pilas, con el objetivo de que coincidencias == k
        int cont = 0;
        int coincidencia = 0;
        while (k>cont){

            // Suma si coinciden
            if (aux1.tope() == aux2.tope()) {
                coincidencia ++;
            }

            // 3. Vamos vaciando las pilas y metiendo los elementos en colas a medida que vamos comparando
            c1.encolar(aux1.tope());
            aux1.desapilar();
            c2.encolar(aux2.tope());
            aux2.desapilar();

            cont ++;
        }

        // 4. Terminamos de vacias las pilas y llenar las colas
        while(!aux1.esVacia()){
            c1.encolar(aux1.tope());
            aux1.desapilar();
        }
        while(!aux2.esVacia()){
            c2.encolar(aux2.tope());
            aux2.desapilar();
        }

        // 5. Invertimos las colas, porque al pasar de cola a pila, se invirten los elementos. Volvemos al orden original
        invertirColaSinPila(c1);
        invertirColaSinPila(c2);

        return coincidencia == k;
    }


    // SECCION METODOS COLA CON PRIORIDAD
    // # Aclaración: siempre que creamos una Cola utilizamos ColaPrioridadEstaticaA, pero todos los metodos funcionan
    // de igual manera con ColaPrioridadEstaticaB.

    public static ColaPrioridadTDA combinar(ColaPrioridadTDA cp1, ColaPrioridadTDA cp2) {
        ColaPrioridadTDA combinada = new ColaPrioridadEstaticaA(100);

        // 1. Vaciamos cp1 por completo, pasando cada elemento a la cola combinada
        while (!cp1.esVacia()) {
            int prioridad = cp1.verPrioridadMax();
            int elemento  = cp1.extraerMax();
            combinada.insertar(elemento, prioridad);
        }

        // 2. Lo mismo con cp2
        while (!cp2.esVacia()) {
            int prioridad = cp2.verPrioridadMax();
            int elemento  = cp2.extraerMax();
            combinada.insertar(elemento, prioridad);
        }

        return combinada;
    }

    public static int sumarValoresPrioridadPar(ColaPrioridadTDA cp) {
        ColaPrioridadTDA aux = new ColaPrioridadEstaticaA(100);

        // 1. Vaciamos cp guardando cada par (valor, prioridad) en aux,
        // y de paso acumulamos los valores cuya prioridad es par
        int suma = 0;
        while (!cp.esVacia()) {
            int prioridad = cp.verPrioridadMax();
            int elemento  = cp.extraerMax();

            if (prioridad % 2 == 0)
                suma = suma + elemento;

            aux.insertar(elemento, prioridad);
        }

        // 2. Devolvemos todos los elementos a cp en el mismo orden en que salió de aux
        while (!aux.esVacia()) {
            int prioridad = aux.verPrioridadMax();
            int elemento  = aux.extraerMax();
            cp.insertar(elemento, prioridad);
        }

        return suma;
    }

    public static ColaTDA invertirColaConColaPrioridad(ColaTDA c) {

        ColaPrioridadTDA aux = new ColaPrioridadEstaticaA(100);
        ColaTDA invertida = new ColaEstaticaA(100);

        int prioridad = 1;

        // Pasamos los elementos de la cola a la cola con prioridad
        while (!c.esVacia()) {
            aux.insertar(c.primero(), prioridad);
            c.desencolar();
            prioridad++;
        }

        // Los elementos de mayor prioridad salen primero
        while (!aux.esVacia()) {
            invertida.encolar(aux.verMax());
            aux.extraerMax();
        }

        return invertida;
    }
}
