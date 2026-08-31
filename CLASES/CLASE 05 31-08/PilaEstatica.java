public class PilaEstatica {
    private Object[] datos;
    private int tope; // indice del proximo lugar libre; tope-1 = cima

    public PilaEstatica(int capacidad) {
        this.datos = new Object[capacidad];
        this.tope = 0;
    }

    public void apilar(Object x) {
        if (tope == datos.length) throw new RuntimeException("pila llena");
        datos[tope] = x;
        tope++;
    }

    public Object desapilar() {
        if (esVacia()) throw new RuntimeException("desapilar(): pila vacia");
        tope--;
        Object x = datos[tope];
        datos[tope] = null;
        return x;
    }

    public Object tope() {
        if (esVacia()) throw new RuntimeException("tope(): pila vacia");
        return datos[tope - 1];
    }

    public boolean esVacia() { return tope == 0; }

    public static void main(String[] args) {
        PilaEstatica p = new PilaEstatica(5);
        p.apilar(3);
        p.apilar(7);
        p.apilar(2);
        System.out.println("tope() = " + p.tope() + "  (esperado 2)");
        Object x = p.desapilar();
        System.out.println("desapilar() devolvio " + x + "  (esperado 2)");
        System.out.println("tope() = " + p.tope() + "  (esperado 7)");
        System.out.println("esVacia() = " + p.esVacia());
    }
}
