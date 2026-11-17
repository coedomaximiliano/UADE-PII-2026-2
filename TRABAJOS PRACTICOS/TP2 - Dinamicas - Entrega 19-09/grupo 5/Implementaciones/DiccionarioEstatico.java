package Implementaciones;

import TDAs.DiccionarioTDA;
import TDAs.ListaTDA;

public class DiccionarioEstatico extends DiccionarioTDA {
    private Object[] claves;
    private Object[] valores;
    private int cantidad;

    public DiccionarioEstatico(int capacidad) {
        this.claves = new Object[capacidad];
        this.valores = new Object[capacidad];
        this.cantidad = 0;
    }

    @Override
    public void definir(Object clave, Object valor) {
        int pos = buscarIndice(clave);

        if (pos != -1) {
            // 1. Si la clave ya existe, se reemplaza su valor.
            valores[pos] = valor;
        } else {
            // 2. Si no existe, se agrega en la primera posicion libre.
            if (this.cantidad == this.claves.length) {
                throw new RuntimeException("Diccionario lleno");
            }
            this.claves[this.cantidad] = clave;
            this.valores[this.cantidad] = valor;
            this.cantidad++;
        }
    }

    @Override
    public Object obtener(Object clave) {
        int pos = buscarIndice(clave);
        if (pos == -1) {
            throw new RuntimeException("obtener(): la clave no existe -> " + clave);
        }
        return this.valores[pos];
    }

    @Override
    public void eliminar(Object clave) {
        int pos = buscarIndice(clave);
        if (pos == -1) {
            return;
        }
        // El hueco se llena con el ultimo par en lugar de correr todos los elementos una posicion.
        int ultimo = this.cantidad - 1;
        this.claves[pos] = this.claves[ultimo];
        this.valores[pos] = this.valores[ultimo];
        this.claves[ultimo] = null;
        this.valores[ultimo] = null;
        this.cantidad--;
    }

    @Override
    public boolean existeClave(Object clave) {
        return buscarIndice(clave) != -1;
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

        for (int i = 0; i < this.cantidad; i++) {
            listaClaves.agregar(this.claves[i]);
        }

        return listaClaves;
    }

    // Métodos auxiliares
    private int buscarIndice(Object clave) {
        for (int i = 0; i < this.cantidad; i++) {
            if (esIgual(this.claves[i], clave)) {
                return i;
            }
        }
        return -1;
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
