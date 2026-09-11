package Implementaciones;

import TDAs.ColaPrioridadTDA;

public class ColaPrioridadA extends ColaPrioridadTDA {

    private int[] valores;
    private int[] prioridades;
    private int cantidad;
    private int capacidad;

    public ColaPrioridadA(int capacidad) {
        this.capacidad = capacidad;
        this.valores = new int[capacidad];
        this.prioridades = new int[capacidad];
        this.cantidad = 0;
    }

    @Override
    public boolean esVacia() {
        return cantidad == 0;
    }

    private boolean esLlena() {
        return cantidad == capacidad;
    }

    @Override
    public void insertar(int elemento, int p) {
        if (esLlena()) {
            throw new RuntimeException("Cola llena");
        }
        valores[cantidad] = elemento;
        prioridades[cantidad] = p;
        cantidad++;
    }

    private int indiceMax() {
        int idxMax = 0;
        for (int i = 1; i < cantidad; i++) {
            if (prioridades[i] > prioridades[idxMax]) {
                idxMax = i;
            }
        }
        return idxMax;
    }

    @Override
    public int verMax() {
        if (esVacia()) throw new RuntimeException("Vacia");
        return valores[indiceMax()];
    }

    @Override
    public int verPrioridadMax() {
        if (esVacia()) throw new RuntimeException("Vacia");
        return prioridades[indiceMax()];
    }

    @Override
    public int extraerMax() {
        if (esVacia()) throw new RuntimeException("Vacia");
        int idx = indiceMax();
        int valor = valores[idx];

        for (int i = idx; i < cantidad - 1; i++) {
            valores[i] = valores[i + 1];
            prioridades[i] = prioridades[i + 1];
        }
        cantidad--;
        return valor;
    }
}
