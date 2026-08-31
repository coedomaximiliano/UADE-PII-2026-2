public class ColaPrioridadEstatica {
    private Object[] valores;
    private int[] prioridades;
    private int cantidad;

    public ColaPrioridadEstatica(int capacidad) {
        this.valores = new Object[capacidad];
        this.prioridades = new int[capacidad];
        this.cantidad = 0;
    }

    public void insertar(Object x, int prioridad) {
        if (cantidad == valores.length) throw new RuntimeException("llena");
        valores[cantidad] = x;
        prioridades[cantidad] = prioridad;
        cantidad++;
    }

    private int indiceMax() {
        int iMax = 0;
        for (int i = 1; i < cantidad; i++) {
            if (prioridades[i] > prioridades[iMax]) iMax = i;
        }
        return iMax;
    }

    public Object verMax() {
        if (esVacia()) throw new RuntimeException("verMax(): vacia");
        return valores[indiceMax()];
    }

    public void extraerMax() {
        if (esVacia()) throw new RuntimeException("extraerMax(): vacia");
        int iMax = indiceMax();
        valores[iMax] = valores[cantidad - 1];
        prioridades[iMax] = prioridades[cantidad - 1];
        cantidad--;
    }

    public boolean esVacia() { return cantidad == 0; }

    public static void main(String[] args) {
        ColaPrioridadEstatica cp = new ColaPrioridadEstatica(5);
        cp.insertar("Incendio en el servidor", 10);
        cp.insertar("Reunion de equipo", 5);
        cp.insertar("Revisar mails", 2);
        System.out.println("verMax() = " + cp.verMax() + "  (esperado Incendio en el servidor)");
        cp.extraerMax();
        System.out.println("tras extraerMax(), verMax() = " + cp.verMax() + "  (esperado Reunion de equipo)");
        System.out.println("esVacia() = " + cp.esVacia());
    }
}
