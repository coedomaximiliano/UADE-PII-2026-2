package tp1.pila;

public class PilaB implements Pila {
    private int[] elementos;
    private int tope;

    @Override
    public void crear() {
        elementos = new int[10000];
        tope = 0;
    }

    @Override
    public void apilar(int x) {
        for (int i = tope; i > 0; i--) {
            elementos[i] = elementos[i - 1];
        }
        elementos[0] = x;
        tope++;
    }

    @Override
    public void desapilar() {
        for (int i = 0; i < tope - 1; i++) {
            elementos[i] = elementos[i + 1];
        }
        tope--;
    }

    @Override
    public int tope() {
        return elementos[0];
    }

    @Override
    public boolean esVacia() {
        return tope == 0;
    }
}
