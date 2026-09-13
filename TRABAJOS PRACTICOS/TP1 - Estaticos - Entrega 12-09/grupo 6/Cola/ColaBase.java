public class ColaBase {

    public Cola invertirSinPila(Cola cola) {
        if (!cola.esVacia()) {
            int elem = cola.desencolar();
            invertirSinPila(cola);
            cola.encolar(elem);
        }
        return cola;
    }





}
