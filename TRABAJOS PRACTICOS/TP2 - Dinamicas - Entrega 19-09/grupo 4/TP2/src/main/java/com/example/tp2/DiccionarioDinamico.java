package com.example.tp2;

public class DiccionarioDinamico implements Diccionario {
    private NodoDiccionario cabeza;
    private int cantidad;

    @Override
    public void crear() {
        cabeza = null;
        cantidad = 0;
    }

    @Override
    public void definir(Object clave, Object valor) {
        NodoDiccionario actual = cabeza;
        while (actual != null) {
            if (actual.clave.equals(clave)) {
                actual.valor = valor;
                return;
            }
            actual = actual.siguiente;
        }

        NodoDiccionario nuevo = new NodoDiccionario();
        nuevo.clave = clave;
        nuevo.valor = valor;
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
        cantidad++;
    }

    @Override
    public Object obtener(Object clave) {
        NodoDiccionario actual = cabeza;
        while (actual != null) {
            if (actual.clave.equals(clave)) {
                return actual.valor;
            }
            actual = actual.siguiente;
        }
        return null; // o lanzar excepción, según especificación, pero null es aceptable si precondición se cumple.
    }

    @Override
    public void eliminar(Object clave) {
        if (cabeza == null) return;

        if (cabeza.clave.equals(clave)) {
            cabeza = cabeza.siguiente;
            cantidad--;
            return;
        }

        NodoDiccionario actual = cabeza;
        while (actual.siguiente != null) {
            if (actual.siguiente.clave.equals(clave)) {
                actual.siguiente = actual.siguiente.siguiente;
                cantidad--;
                return;
            }
            actual = actual.siguiente;
        }
    }

    @Override
    public boolean existeClave(Object clave) {
        NodoDiccionario actual = cabeza;
        while (actual != null) {
            if (actual.clave.equals(clave)) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    @Override
    public boolean esVacio() {
        return cantidad == 0;
    }

    @Override
    public int cantidadClaves() {
        return cantidad;
    }

    @Override
    public Lista claves() {
        ListaDinamica listaClaves = new ListaDinamica();
        listaClaves.crear();
        NodoDiccionario actual = cabeza;
        while (actual != null) {
            listaClaves.agregar(actual.clave);
            actual = actual.siguiente;
        }
        return listaClaves;
    }
}
