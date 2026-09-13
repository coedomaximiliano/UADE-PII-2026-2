public class PilaVarianteB implements Pila {
    private int[] datos;
    private int tope;
    private int capacidad;
    
    private static final int CAPACIDAD_DEFAULT = 1000;
    
    public PilaVarianteB() {
        this(CAPACIDAD_DEFAULT);
    }
    
    public PilaVarianteB(int capacidad) {
        this.capacidad = capacidad;
        this.datos = new int[capacidad];
        this.tope = 0;
    }
    
    @Override
    public void apilar(int x) {
        if (tope == capacidad) {
            throw new RuntimeException("Pila llena");
        }
        // Corre elementos hacia adelante para hacer espacio en posición 0
        for (int i = tope; i > 0; i--) {
            datos[i] = datos[i - 1];
        }
        datos[0] = x;
        tope++;
    }
    
    @Override
    public int desapilar() {
        if (esVacia()) {
            throw new RuntimeException("Pila vacía");
        }
        int x = datos[0];
        // Corre elementos hacia atrás
        for (int i = 0; i < tope - 1; i++) {
            datos[i] = datos[i + 1];
        }
        tope--;
        return x;
    }
    
    @Override
    public int tope() {
        if (esVacia()) {
            throw new RuntimeException("Pila vacía");
        }
        return datos[0];
    }
    
    @Override
    public boolean esVacia() {
        return tope == 0;
    }
}