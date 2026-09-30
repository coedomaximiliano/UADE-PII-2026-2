package com.example.tp2;

public class DiccionarioEstatico implements Diccionario {
    private static final int MAX_CAPACIDAD = 200000;
    private Object[] claves;
    private Object[] valores;
    private int cantidad;

    @Override
    public void crear() {
        claves = new Object[MAX_CAPACIDAD];
        valores = new Object[MAX_CAPACIDAD];
        cantidad = 0;
    }

    @Override
    public void definir(Object clave, Object valor) {
        for (int i = 0; i < cantidad; i++) {
            if (claves[i].equals(clave)) {
                valores[i] = valor;
                return;
            }
        }
        if (cantidad < MAX_CAPACIDAD) {
            claves[cantidad] = clave;
            valores[cantidad] = valor;
            cantidad++;
        }
    }

    @Override
    public Object obtener(Object clave) {
        for (int i = 0; i < cantidad; i++) {
            if (claves[i].equals(clave)) {
                return valores[i];
            }
        }
        return null;
    }

    @Override
    public void eliminar(Object clave) {
        for (int i = 0; i < cantidad; i++) {
            if (claves[i].equals(clave)) {
                claves[i] = claves[cantidad - 1];
                valores[i] = valores[cantidad - 1];
                claves[cantidad - 1] = null;
                valores[cantidad - 1] = null;
                cantidad--;
                return;
            }
        }
    }

    @Override
    public boolean existeClave(Object clave) {
        for (int i = 0; i < cantidad; i++) {
            if (claves[i].equals(clave)) {
                return true;
            }
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
        ListaDinamica lista = new ListaDinamica();
        lista.crear();
        for (int i = 0; i < cantidad; i++) {
            lista.agregar(claves[i]);
        }
        return lista;
    }
}
