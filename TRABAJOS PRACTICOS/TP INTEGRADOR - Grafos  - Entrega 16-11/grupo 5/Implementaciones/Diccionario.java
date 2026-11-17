package Implementaciones;

public class Diccionario {
    private static class NodoDic {
        String clave;
        int valor;
        NodoDic siguiente;

        NodoDic(String clave, int valor) {
            this.clave = clave;
            this.valor = valor;
            this.siguiente = null;
        }
    }
    private NodoDic cabeza;
    private int cantidad;

    public Diccionario() {
        this.cabeza = null;
        this.cantidad = 0;
    }

    public boolean esVacio() {
        return cabeza == null;
    }

    public int cantidad() {
        return cantidad;
    }

    public boolean existeClave(String clave) {
        NodoDic actual = cabeza;
        while (actual != null) {
            if (actual.clave.equals(clave)) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public void insertar(String clave, int valor) {
        if (existeClave(clave)) {
            throw new RuntimeException("La clave ya existe: " + clave);
        }
        NodoDic nuevo = new NodoDic(clave, valor);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
        cantidad++;
    }

    public int obtener(String clave) {
        NodoDic actual = cabeza;
        while (actual != null) {
            if (actual.clave.equals(clave)) {
                return actual.valor;
            }
            actual = actual.siguiente;
        }
        throw new RuntimeException("Clave inexistente: " + clave);
    }
}
