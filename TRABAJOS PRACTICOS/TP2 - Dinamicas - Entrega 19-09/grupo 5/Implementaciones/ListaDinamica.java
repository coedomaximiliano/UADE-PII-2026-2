package Implementaciones;

import TDAs.ListaTDA;
import java.util.Objects;

public class ListaDinamica extends ListaTDA {
    // Clase interna para el nodo.
    private static class NodoLista {
        Object dato;
        NodoLista siguiente;
    }
    private NodoLista origen;
    private int cantidad;

    public ListaDinamica() {
        this.origen = null;
        this.cantidad = 0;
    }

    @Override
    public void agregar(Object x) {
        NodoLista nuevo = new NodoLista();
        nuevo.dato = x;
        nuevo.siguiente = origen;
        origen = nuevo;
        cantidad++;
    }

    @Override
    public void eliminar(Object x) {
        NodoLista anterior = null;
        NodoLista actual = origen;
        while (actual != null && !Objects.equals(actual.dato, x)) {
            anterior = actual;
            actual = actual.siguiente;
        }
        if (actual == null) {
            throw new RuntimeException("eliminar(): el elemento no pertenece -> " + x);
        }
        if (anterior == null) {   // caso especial: era el origen
            origen = actual.siguiente;
        } else {
            anterior.siguiente = actual.siguiente;
        }
        cantidad--;
    }

    @Override
    public boolean pertenece(Object x) {
        NodoLista actual = origen;
        while (actual != null) {
            if (Objects.equals(actual.dato, x)) return true;
            actual = actual.siguiente;
        }
        return false;
    }

    @Override
    public Object elegir() {
        if (cantidad == 0) {
            throw new RuntimeException("elegir(): lista vacia");
        }
        return origen.dato;
    }

    @Override
    public int tamanio() {
        return cantidad;
    }

    @Override
    public boolean esVacia() {
        return cantidad == 0;
    }
}
