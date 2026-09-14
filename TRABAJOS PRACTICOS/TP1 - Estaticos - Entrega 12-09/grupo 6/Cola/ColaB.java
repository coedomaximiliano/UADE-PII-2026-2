public class ColaB extends ColaBase implements Cola {
    private final int[] datos;
    private int frente;   // indice del primer elemento
    private int fin;   // indice del ultimo elemento
    private int cantidad; // cuantos elementos hay
    private int capacidad;

    public ColaB(int capacidad) {
        this.capacidad = capacidad;
        this.datos = new int[capacidad];
        this.fin = 0;
        this.frente = 0;
        this.cantidad = 0;
    }

    /**
     * post: x queda al final de la cola
     */
    @Override
    public void encolar(int x) {
        if (esLlena()) {
            throw new IllegalStateException("Cola llena");
        }
        datos[fin] = x;
        fin = (fin +1) % datos.length;
        cantidad++;
    }

    /**
     * pre:  la cola no esta vacia
     * post: se elimina el elemento del frente y se devuelve
     */
    @Override
    public int desencolar() {
        if (esVacia()) {
            throw new IllegalStateException("Cola vacia");
        }
        int x = datos[frente];
        frente = (frente +1) % datos.length;   // O(1) — no se corre nada
        cantidad--;
        return x;
    }

    /**
     * pre:  la cola no esta vacia
     * post: devuelve el elemento del frente sin eliminarlo
     */
    @Override
    public int primero() {
        if (esVacia()) {
            throw new IllegalStateException("Cola vacia");
        }
        return datos[frente]; // O(1)
    }

    @Override
    public int tamanio() {
        return cantidad;
    }

    /**
     * post: devuelve true si la cola no tiene elementos
     */
    @Override
    public boolean esVacia() {
        return cantidad == 0;
    }

    /**
     * post: devuelve true si la cola alcanzo su capacidad maxima
     */
    @Override
    public boolean esLlena() {
        return cantidad == capacidad;
    }

    public ColaB pasarCola(ColaB origen) {
        ColaB destino = new ColaB(origen.capacidad);
        int cantidad = origen.tamanio();
        for (int i = 0; i < cantidad; i++) {
            destino.encolar(origen.desencolar());
        }
        destino.frente = 0;
        destino.fin = this.cantidad % this.capacidad;
        destino.cantidad = this.cantidad;
        return destino;
    }

    public Cola invertirColaConPila(Cola c) {
        PilaAuxiliar p = new PilaAuxiliar(100);
        ColaB cCopia = new ColaB(100);
        while (!c.esVacia()) {
            int elem = c.desencolar();
            p.apilar(elem);
            cCopia.encolar(elem);
        }
        while (!cCopia.esVacia()) {
            c.encolar(cCopia.desencolar());
        }
        Cola invertida = new ColaB(100);
        while (!p.esVacia()) {
            invertida.encolar(p.desapilar());
        }
        return invertida;
    }
    public Cola copy(Cola origen) {
        ColaB copia = new ColaB(capacidad);
        ColaB aux = new ColaB(capacidad);

        while (!esVacia()) {
            int elemento = origen.desencolar();
            copia.encolar(elemento);
            aux.encolar(elemento);
        }

        while (!esVacia()) {
            origen.encolar(aux.desencolar());
        }

        return copia;
    }

    public boolean finalCoincide(ColaB c1, ColaB c2, int k) {
        Cola copia1 = c1.copy(c1);
        Cola copia2 = c2.copy(c2);
        Cola aux1 = invertirSinPila(copia1);
        Cola aux2 = invertirSinPila(copia2);
        boolean coinciden = true;
        for (int i = 0; i < k; i++) {
            if(!aux1.esVacia() && !aux2.esVacia()){
                if (aux1.desencolar() != aux2.desencolar()) {
                    coinciden = false;
                    break;
                }
            } else {
                coinciden = false;
                break;
            }
        }
        return coinciden;
    }


}
