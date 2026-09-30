
public class ColaCircular {
    private int[] datos;
    private int inicio;
    private int fin;
    private int cantidad;   
    private int capacidad;

    public ColaCircular(int capacidad) {
        this.capacidad = capacidad;
        this.datos = new int[capacidad];
        this.inicio = 0;
        this.fin = 0;
        this.cantidad = 0;
    }

    public boolean esVacia() {
        return cantidad == 0;
    }

    public boolean esLlena() {
        return cantidad == capacidad;
    }

    public void encolar(int x) {
        if (esLlena()) {
            throw new RuntimeException("Cola llena");
        }
        datos[fin] = x;
        fin = (fin + 1) % capacidad;   // clave: circular
        cantidad++;
    }

    public int desencolar() {
        if (esVacia()) {
            throw new RuntimeException("Cola vacia");
        }
        int valor = datos[inicio];
        inicio = (inicio + 1) % capacidad;   // clave: circular
        cantidad--;
        return valor;
    }

    public int frente() {
        if (esVacia()) {
            throw new RuntimeException("Cola vacia");
        }
        return datos[inicio];
    }

    /** Prueba sugerida en el enunciado: encolar 5, desencolar 2, encolar 2 mas. */
    public static void main(String[] args) {
        ColaCircular c = new ColaCircular(5);
        for (int i = 1; i <= 5; i++) {
            c.encolar(i);
        }
        System.out.println("Desencolo: " + c.desencolar()); // 1
        System.out.println("Desencolo: " + c.desencolar()); // 2

        // a diferencia de la variante lineal, esto SI funciona:
        // inicio=2, fin=5%5=0 -> hay dos lugares libres (0 y 1) reutilizables
        c.encolar(6);
        c.encolar(7);
        System.out.println("Encole 6 y 7 sin problema (reutilizando lugares liberados)");

        while (!c.esVacia()) {
            System.out.println("Desencolo: " + c.desencolar());
        }
        // deberia imprimir 3,4,5,6,7 -> el orden original se mantuvo
    }
}
