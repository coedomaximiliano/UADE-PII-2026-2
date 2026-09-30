package tp1.colaprioridad;

public class ColaPrioridadB implements ColaPrioridad {
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
        int i = cantidad - 1;
        while (i >= 0 && prioridades[i] >= prioridad) {
            elementos[i + 1] = elementos[i];
            prioridades[i + 1] = prioridades[i];
            i--;
        }
        elementos[i + 1] = x;
        prioridades[i + 1] = prioridad;
        cantidad++;
    }

    @Override
    public void extraerMax() {
        if (cantidad > 0) {
            cantidad--;
        }
    }

    @Override
    public int verMax() {
        return elementos[cantidad - 1];
    }

    @Override
    public int prioridadMax() {
        return prioridades[cantidad - 1];
    }

    @Override
    public boolean esVacia() {
        return cantidad == 0;
    }
}
