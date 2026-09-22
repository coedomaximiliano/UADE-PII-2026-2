public class PilaVarianteA implements Pila {
    private int[] datos;
    private int tope;
    private int capacidad;
    
    private static final int CAPACIDAD_DEFAULT = 1000;
    
    public PilaVarianteA() {
        this(CAPACIDAD_DEFAULT);
    }
    
    public PilaVarianteA(int capacidad) {
        this.capacidad = capacidad;
        this.datos = new int[capacidad];
        this.tope = 0;
    }
    
    @Override
    public void apilar(int x) {
        if (tope == capacidad) {
            throw new RuntimeException("Pila llena");
        }
        datos[tope] = x;
        tope++;
    }
    
    @Override
    public int desapilar() {
        if (esVacia()) {
            throw new RuntimeException("Pila vacía");
        }
        tope--;
        return datos[tope];
    }
    
    @Override
    public int tope() {
        if (esVacia()) {
            throw new RuntimeException("Pila vacía");
        }
        return datos[tope - 1];
    }
    
    @Override
    public boolean esVacia() {
        return tope == 0;
    }
}