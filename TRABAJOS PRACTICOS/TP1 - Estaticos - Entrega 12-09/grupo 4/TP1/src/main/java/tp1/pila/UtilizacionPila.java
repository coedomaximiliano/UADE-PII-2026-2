package tp1.pila;

public class UtilizacionPila {

    // 1. pasarPila
    public Pila pasarPila(Pila origen) {
        Pila nueva = new PilaA();
        nueva.crear();
        Pila aux = new PilaA();
        aux.crear();

        while (!origen.esVacia()) {
            aux.apilar(origen.tope());
            origen.desapilar();
        }

        while (!aux.esVacia()) {
            nueva.apilar(aux.tope());
            aux.desapilar();
        }
        return nueva;
    }

    // 2. copiarPila
    public Pila copiarPila(Pila p) {
        Pila copia = new PilaA();
        copia.crear();
        Pila aux = new PilaA();
        aux.crear();

        while (!p.esVacia()) {
            aux.apilar(p.tope());
            p.desapilar();
        }

        while (!aux.esVacia()) {
            p.apilar(aux.tope());
            copia.apilar(aux.tope());
            aux.desapilar();
        }
        return copia;
    }

    // 3. invertirPila
    public Pila invertirPila(Pila p) {
        Pila invertida = new PilaA();
        invertida.crear();
        invertirPilaRec(p, invertida);
        return invertida;
    }

    private void invertirPilaRec(Pila p, Pila invertida) {
        if (!p.esVacia()) {
            int tope = p.tope();
            p.desapilar();
            invertida.apilar(tope);
            invertirPilaRec(p, invertida);
            p.apilar(tope);
        }
    }

    // 4. masDeUnaOcurrencia
    public boolean masDeUnaOcurrencia(Pila p) {
        Pila aux = new PilaA();
        aux.crear();
        boolean encontrado = false;

        while (!p.esVacia() && !encontrado) {
            int elem = p.tope();
            p.desapilar();
            aux.apilar(elem);

            Pila busqueda = new PilaA();
            busqueda.crear();

            while (!p.esVacia() && !encontrado) {
                if (p.tope() == elem) {
                    encontrado = true;
                }
                busqueda.apilar(p.tope());
                p.desapilar();
            }

            while (!busqueda.esVacia()) {
                p.apilar(busqueda.tope());
                busqueda.desapilar();
            }
        }

        while (!aux.esVacia()) {
            p.apilar(aux.tope());
            aux.desapilar();
        }

        return encontrado;
    }

    // 5. eliminarImpares
    public Pila eliminarImpares(Pila p) {
        Pila pares = new PilaA();
        pares.crear();
        Pila aux = new PilaA();
        aux.crear();

        while (!p.esVacia()) {
            aux.apilar(p.tope());
            p.desapilar();
        }

        while (!aux.esVacia()) {
            int elem = aux.tope();
            p.apilar(elem);
            if (elem % 2 == 0) {
                pares.apilar(elem);
            }
            aux.desapilar();
        }

        return pares;
    }
}
