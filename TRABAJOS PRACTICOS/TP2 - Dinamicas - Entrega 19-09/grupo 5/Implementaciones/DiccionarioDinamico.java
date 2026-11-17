package Implementaciones;

import TDAs.DiccionarioTDA;
import TDAs.ListaTDA;

public class DiccionarioDinamico extends DiccionarioTDA {
    // Clase interna para el nodo.
    private static class NodoDiccionario {
        Object clave;
        Object valor;
        NodoDiccionario siguiente;
    }
    private NodoDiccionario origen;
    private int cantidad;

    public DiccionarioDinamico() {
        this.origen = null;
        this.cantidad = 0;
    }

    @Override
    public void definir(Object clave, Object valor) {
        NodoDiccionario aux = buscarNodo(clave);

        if (aux != null) {
            // 1. Si la clave ya existe, se reemplaza su valor.
            aux.valor = valor;
        } else {
            // 2. Si no existe, se inserta un nuevo nodo al inicio de la cadena.
            NodoDiccionario nuevo = new NodoDiccionario();
            nuevo.clave = clave;
            nuevo.valor = valor;
            nuevo.siguiente = this.origen;

            this.origen = nuevo;
            this.cantidad++;
        }
    }

    @Override
    public Object obtener(Object clave) {
        NodoDiccionario aux = buscarNodo(clave);
        if (aux == null) {  // Para que no tire NullPointerException.
            throw new RuntimeException("obtener(): la clave no existe -> " + clave);
        }
        return aux.valor;
    }

    @Override
    public void eliminar(Object clave) {
        if (this.origen == null) {
            return;
        }

        // Caso 1: La clave a eliminar está en la cabeza.
        if (esIgual(this.origen.clave, clave)) {
            this.origen = this.origen.siguiente;
            this.cantidad--;
            return;
        }

        // Caso 2: La clave está en el resto de la cadena.
        NodoDiccionario actual = this.origen;
        while (actual.siguiente != null && !esIgual(actual.siguiente.clave, clave)) {
            actual = actual.siguiente;
        }

        if (actual.siguiente != null) {
            actual.siguiente = actual.siguiente.siguiente;
            this.cantidad--;
        }
    }

    @Override
    public boolean existeClave(Object clave) {
        return buscarNodo(clave) != null;
    }

    @Override
    public boolean esVacio() {
        return this.cantidad == 0;
    }

    @Override
    public int cantidadClaves() {
        return this.cantidad;
    }

    @Override
    public ListaTDA claves() {
        ListaTDA listaClaves = new ListaDinamica();
        NodoDiccionario actual = this.origen;

        while (actual != null) {
            listaClaves.agregar(actual.clave);
            actual = actual.siguiente;
        }

        return listaClaves;
    }

    // Métodos auxiliares
    private NodoDiccionario buscarNodo(Object clave) {
        NodoDiccionario actual = this.origen;
        while (actual != null) {
            if (esIgual(actual.clave, clave)) {
                return actual;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    private boolean esIgual(Object a, Object b) {
        if (a == b) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return a.equals(b);
    }
}
