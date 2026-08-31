/**
 * Ejemplo: la precondicion del TDA SI se implementa.
 *
 * desapilar(p: Pila) -> Pila
 *   pre: p no esta vacia
 *   post: se quita el elemento del tope de p
 *
 * Antes de tocar el arreglo interno, el codigo verifica la precondicion
 * "a mano" y lanza una RuntimeException con un mensaje claro si no se
 * cumple. Quien use la Pila se entera exactamente de que operacion violo
 * que precondicion.
 */
public class PilaConExcepcion {
    private Object[] datos;
    private int tope;

    public PilaConExcepcion(int capacidad) {
        this.datos = new Object[capacidad];
        this.tope = 0;
    }

    public void apilar(Object x) {
        if (tope == datos.length) {
            throw new RuntimeException("apilar(): la pila esta llena");
        }
        datos[tope] = x;
        tope++;
    }

    public Object desapilar() {
        if (esVacia()) {                       // <-- verifica la precondicion
            throw new RuntimeException("desapilar(): la pila esta vacia");
        }
        tope--;
        return datos[tope];
    }

    public Object tope() {
        if (esVacia()) {                       // <-- verifica la precondicion
            throw new RuntimeException("tope(): la pila esta vacia");
        }
        return datos[tope - 1];
    }

    public boolean esVacia() { return tope == 0; }

    public static void main(String[] args) {
        PilaConExcepcion p = new PilaConExcepcion(3);
        p.apilar(10);
        p.desapilar();
        System.out.println("Pila vacia, ahora intento desapilar() de nuevo...");
        try {
            p.desapilar();                     // viola la precondicion a proposito
        } catch (RuntimeException e) {
            System.out.println("Se atrapo la excepcion. Mensaje: \"" + e.getMessage() + "\"");
            System.out.println("Clase de la excepcion: " + e.getClass().getSimpleName());
        }
        System.out.println("El programa sigue corriendo normalmente despues del catch.");
    }
}
