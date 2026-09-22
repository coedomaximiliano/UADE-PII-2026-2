package tp1.colaprioridad;

public class ColaPrioridadA implements ColaPrioridad {
    private int[] elementos;
    private int[] prioridades;
    private int cantidad;

    @Override
    public void crear() {
        elementos = new int[10000];
        prioridades = new int[10000];
        cantidad = 0;
    }

    @Override
    public void insertar(int x, int prioridad) {
        elementos[cantidad] = x;
        prioridades[cantidad] = prioridad;
        cantidad++;
    }

    @Override
    public void extraerMax() {
        if (cantidad == 0) return;
        int maxIdx = 0;
        for (int i = 1; i < cantidad; i++) {
            if (prioridades[i] > prioridades[maxIdx]) {
                maxIdx = i;
            }
        }
        for (int i = maxIdx; i < cantidad - 1; i++) {
            elementos[i] = elementos[i + 1];
            prioridades[i] = prioridades[i + 1];
        }
        cantidad--;
    }

    @Override
    public int verMax() {
        int maxIdx = 0;
        for (int i = 1; i < cantidad; i++) {
            if (prioridades[i] > prioridades[maxIdx]) {
                maxIdx = i;
            }
        }
        return elementos[maxIdx];
    }

    @Override
    public int prioridadMax() {
        int maxIdx = 0;
        for (int i = 1; i < cantidad; i++) {
            if (prioridades[i] > prioridades[maxIdx]) {
                maxIdx = i;
            }
        }
        return prioridades[maxIdx];
    }

    @Override
    public boolean esVacia() {
        return cantidad == 0;
    }
}
