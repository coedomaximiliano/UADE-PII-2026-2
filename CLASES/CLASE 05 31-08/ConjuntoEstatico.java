public class ConjuntoEstatico {
    private int[] elementos;
    private int cantidad;

    public ConjuntoEstatico(int capacidad) {
        this.elementos = new int[capacidad];
        this.cantidad = 0;
    }

    public boolean pertenece(int x) {
        for (int i = 0; i < cantidad; i++) {
            if (elementos[i] == x) return true;
        }
        return false;
    }

    public void agregar(int x) {
        if (pertenece(x)) return;
        if (cantidad == elementos.length) throw new RuntimeException("lleno");
        elementos[cantidad] = x;
        cantidad++;
    }

    public void eliminar(int x) {
        for (int i = 0; i < cantidad; i++) {
            if (elementos[i] == x) {
                elementos[i] = elementos[cantidad - 1];
                cantidad--;
                return;
            }
        }
    }

    public boolean esVacio() { return cantidad == 0; }

    public static void main(String[] args) {
        ConjuntoEstatico c = new ConjuntoEstatico(10);
        c.agregar(3);
        c.agregar(7);
        c.agregar(3); // duplicado: no debe agregarse de nuevo
        System.out.println("pertenece(7) = " + c.pertenece(7) + "  (esperado true)");
        System.out.println("pertenece(9) = " + c.pertenece(9) + "  (esperado false)");
        c.eliminar(3);
        System.out.println("tras eliminar(3), pertenece(3) = " + c.pertenece(3) + "  (esperado false)");
        System.out.println("esVacio() = " + c.esVacio());
    }
}
