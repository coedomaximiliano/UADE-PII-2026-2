package com.example.tp2;

public class ListaDinamica implements Lista {
    private NodoLista origen;
    private int cantidad;

    @Override
    public void crear() {
        origen = null;
        cantidad = 0;
    }

    @Override
    public void agregar(Object elemento) {
        NodoLista nuevo = new NodoLista();
        nuevo.elemento = elemento;
        nuevo.siguiente = null;
        
        if (origen == null) {
            origen = nuevo;
        } else {
            NodoLista actual = origen;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
        cantidad++;
    }

    @Override
    public Object obtener(int indice) {
        NodoLista actual = origen;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }
        return actual.elemento;
    }

    @Override
    public int longitud() {
        return cantidad;
    }

    @Override
    public boolean esVacia() {
        return cantidad == 0;
    }

    @Override
    public void insertar(Object elemento, int indice) {
        NodoLista nuevo = new NodoLista();
        nuevo.elemento = elemento;

        if (indice == 0) {
            nuevo.siguiente = origen;
            origen = nuevo;
        } else {
            NodoLista actual = origen;
            for (int i = 0; i < indice - 1; i++) {
                actual = actual.siguiente;
            }
            nuevo.siguiente = actual.siguiente;
            actual.siguiente = nuevo;
        }
        cantidad++;
    }

    @Override
    public void eliminar(int indice) {
        if (indice == 0) {
            origen = origen.siguiente;
        } else {
            NodoLista actual = origen;
            for (int i = 0; i < indice - 1; i++) {
                actual = actual.siguiente;
            }
            actual.siguiente = actual.siguiente.siguiente;
        }
        cantidad--;
    }
}
