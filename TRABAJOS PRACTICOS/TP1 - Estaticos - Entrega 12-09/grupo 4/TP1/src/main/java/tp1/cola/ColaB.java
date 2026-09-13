package tp1.cola;

public class ColaB implements Cola {
    private int[] elementos;
    private int frente;
    private int cantidad;
    private int MAX;

    @Override
    public void crear() {
        MAX = 10000;
        elementos = new int[MAX];
        frente = 0;
        cantidad = 0;
    }

    @Override
    public void encolar(int x) {
        int fin = (frente + cantidad) % MAX;
        elementos[fin] = x;
        cantidad++;
    }

    @Override
    public void desencolar() {
        frente = (frente + 1) % MAX;
        cantidad--;
    }

    @Override
    public int frente() {
        return elementos[frente];
    }

    @Override
    public boolean esVacia() {
        return cantidad == 0;
    }
}
