package Implementaciones;

import TDAs.ColaPrioridadTDA;

public class ColaPrioridadB extends ColaPrioridadTDA {

        private int[] valores;
        private int[] prioridades;
        private int cantidad;
        private int capacidad;

        public ColaPrioridadB(int capacidad) {
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
            int i = cantidad - 1;
            // '>=' en vez de '>': el nuevo elemento queda ubicado ANTES
            // de los ya existentes con igual prioridad -> desempate FIFO
            while (i >= 0 && prioridades[i] >= p) {
                prioridades[i + 1] = prioridades[i];
                valores[i + 1] = valores[i];
                i--;
            }
            prioridades[i + 1] = p;
            valores[i + 1] = elemento;
            cantidad++;
        }

        @Override
        public int verMax() {
            if (esVacia()) throw new RuntimeException("Vacia");
            return valores[cantidad - 1];
        }

        @Override
        public int verPrioridadMax() {
            if (esVacia()) throw new RuntimeException("Vacia");
            return prioridades[cantidad - 1];
        }


        @Override
        public int extraerMax() {
            if (esVacia()) throw new RuntimeException("Vacia");
            cantidad--;
            return valores[cantidad];
        }

}
