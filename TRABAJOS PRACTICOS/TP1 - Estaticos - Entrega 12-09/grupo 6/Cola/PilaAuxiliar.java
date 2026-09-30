public class PilaAuxiliar {
        private int[] datos;
        private int tope;

        public PilaAuxiliar(int capacidad) {
            this.datos = new int[capacidad];
            this.tope = 0;
        }

        public void apilar(int x) {
            if (tope == datos.length) throw new RuntimeException("pila llena");
            datos[tope] = x;
            tope++;
        }

        public int desapilar() {
            if (esVacia()) throw new RuntimeException("desapilar(): pila vacia");
            tope--;
            int x = datos[tope];
            datos[tope] = -1;
            return x;
        }

        public int tope() {
            if (esVacia()) throw new RuntimeException("tope(): pila vacia");
            return datos[tope - 1];
        }

        public boolean esVacia() {
            return tope == 0;
        }
}

