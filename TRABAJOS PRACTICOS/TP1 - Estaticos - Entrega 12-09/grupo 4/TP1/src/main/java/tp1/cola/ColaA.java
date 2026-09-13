package tp1.cola;

public class ColaA implements Cola {
    private int[] elementos;
    private int frente;
    private int fin;

    @Override
    public void crear() {
        elementos = new int[10000];
        frente = 0;
        fin = 0;
    }

    @Override
    public void encolar(int x) {
        elementos[fin] = x;
        fin++;
    }

    @Override
    public void desencolar() {
        frente++;
    }

    @Override
    public int frente() {
        return elementos[frente];
    }

    @Override
    public boolean esVacia() {
        return frente == fin;
    }
}
