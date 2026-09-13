package tp1.colaprioridad;

import tp1.cola.Cola;

public class UtilizacionColaPrioridad {

    // 10. combinar
    public ColaPrioridad combinar(ColaPrioridad cp1, ColaPrioridad cp2) {
        ColaPrioridad resultado = new ColaPrioridadA();
        resultado.crear();

        while (!cp1.esVacia()) {
            resultado.insertar(cp1.verMax(), cp1.prioridadMax());
            cp1.extraerMax();
        }

        while (!cp2.esVacia()) {
            resultado.insertar(cp2.verMax(), cp2.prioridadMax());
            cp2.extraerMax();
        }

        return resultado;
    }

    // 11. invertirColaConColaPrioridad
    public Cola invertirColaConColaPrioridad(Cola c) {
        ColaPrioridad cp = new ColaPrioridadA();
        cp.crear();

        int prioridad = 1;
        while (!c.esVacia()) {
            cp.insertar(c.frente(), prioridad);
            c.desencolar();
            prioridad++;
        }

        Cola invertida = new tp1.cola.ColaA();
        invertida.crear();

        while (!cp.esVacia()) {
            invertida.encolar(cp.verMax());
            cp.extraerMax();
        }

        return invertida;
    }

    // 12. sumarValoresPrioridadPar
    public int sumarValoresPrioridadPar(ColaPrioridad cp) {
        ColaPrioridad aux = new ColaPrioridadA();
        aux.crear();
        int suma = 0;

        while (!cp.esVacia()) {
            int elem = cp.verMax();
            int prio = cp.prioridadMax();

            if (prio % 2 == 0) {
                suma += elem;
            }

            aux.insertar(elem, prio);
            cp.extraerMax();
        }

        while (!aux.esVacia()) {
            cp.insertar(aux.verMax(), aux.prioridadMax());
            aux.extraerMax();
        }

        return suma;
    }
}
