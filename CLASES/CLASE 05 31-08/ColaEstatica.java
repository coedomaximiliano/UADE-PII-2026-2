public class ColaEstatica {
    private Object[] datos;
    private int frente, fin, cantidad;

    public ColaEstatica(int capacidad) {
        this.datos = new Object[capacidad];
        this.frente = 0;
        this.fin = 0;
        this.cantidad = 0;
    }

    public void encolar(Object x) {
        if (cantidad == datos.length) throw new RuntimeException("cola llena");
        datos[fin] = x;
        fin = (fin + 1) % datos.length;
        cantidad++;
    }

    public Object desencolar() {
        if (esVacia()) throw new RuntimeException("desencolar(): cola vacia");
        Object x = datos[frente];
        datos[frente] = null;
        frente = (frente + 1) % datos.length;
        cantidad--;
        return x;
    }

    public Object frente() {
        if (esVacia()) throw new RuntimeException("frente(): cola vacia");
        return datos[frente];
    }

    public boolean esVacia() { return cantidad == 0; }

    public static void main(String[] args) {
        ColaEstatica c = new ColaEstatica(4);
        c.encolar(10);
        c.encolar(20);
        c.encolar(30);
        System.out.println("frente() = " + c.frente() + "  (esperado 10)");
        Object x = c.desencolar();
        System.out.println("desencolar() devolvio " + x + "  (esperado 10)");
        c.encolar(40);
        System.out.println("frente() = " + c.frente() + "  (esperado 20, sin correr nada)");
        System.out.println("esVacia() = " + c.esVacia());
    }
}
