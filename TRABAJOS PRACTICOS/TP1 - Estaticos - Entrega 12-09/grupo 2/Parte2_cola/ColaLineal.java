
public class ColaLineal {
    private int[] datos;
    private int inicio;    
    private int fin;       
    private int capacidad;

    public ColaLineal(int capacidad) {
        this.capacidad = capacidad;
        this.datos = new int[capacidad];
        this.inicio = 0;
        this.fin = 0;
    }

    public boolean esVacia() {
        return inicio == fin;
    }

    public boolean esLlena() {
        // no hay mas lugar HACIA ADELANTE, aunque queden huecos libres
        // al principio del array (entre 0 e inicio-1)
        return fin == capacidad;
    }

    public void encolar(int x) {
        if (esLlena()) {
            throw new RuntimeException("Cola llena");
        }
        datos[fin] = x;
        fin++;
    }

    public int desencolar() {
        if (esVacia()) {
            throw new RuntimeException("Cola vacia");
        }
        int valor = datos[inicio];
        inicio++;
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
        ColaLineal c = new ColaLineal(5);
        for (int i = 1; i <= 5; i++) {
            c.encolar(i);
        }
        System.out.println("Desencolo: " + c.desencolar()); // 1
        System.out.println("Desencolo: " + c.desencolar()); // 2

        try {
            c.encolar(6);
            System.out.println("Encolo 6 sin problema");
        } catch (RuntimeException e) {
            // fin ya llego a "capacidad" (5) aunque solo hay 3 elementos vivos
            // (inicio=2, fin=5): la variante lineal da los lugares 0 y 1 por perdidos
            System.out.println("No se pudo encolar 6: " + e.getMessage());
        }

        System.out.println("Frente actual: " + c.frente()); // 3
    }
}
