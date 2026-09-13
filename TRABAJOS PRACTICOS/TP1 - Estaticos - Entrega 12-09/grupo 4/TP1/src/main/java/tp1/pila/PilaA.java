package tp1.pila;

public class PilaA implements Pila {
    private int[] elementos;
    private int tope;

    @Override
    public void crear() {
        elementos = new int[10000];
        tope = -1;
    }

    @Override
    public void apilar(int x) {
        tope++;
        elementos[tope] = x;
    }

    @Override
    public void desapilar() {
        tope--;
    }

    @Override
    public int tope() {
        return elementos[tope];
    }

    @Override
    public boolean esVacia() {
        return tope == -1;
    }
}
