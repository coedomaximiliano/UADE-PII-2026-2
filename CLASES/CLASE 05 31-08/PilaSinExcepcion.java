/**
 * Ejemplo: la precondicion NO se implementa.
 *
 * Misma operacion desapilar(p: Pila) -> Pila / pre: p no esta vacia,
 * pero esta version NUNCA verifica esa precondicion: confia en que
 * quien la usa siempre la respeta. Si no la respeta, el codigo no
 * lanza un error propio y explicativo -- deja que el problema explote
 * mas abajo, en el propio arreglo de Java, con una excepcion generica
 * que no dice nada sobre el TDA.
 */
public class PilaSinExcepcion {
    private Object[] datos;
    private int tope;

    public PilaSinExcepcion(int capacidad) {
        this.datos = new Object[capacidad];
        this.tope = 0;
    }

    public void apilar(Object x) {
        datos[tope] = x;                       // tampoco valida "pila llena"
        tope++;
    }

    public Object desapilar() {
        tope--;                                 // <-- NO verifica pre: p no esta vacia
        return datos[tope];                     // si tope ya era 0, tope pasa a -1
    }

    public Object tope() {
        return datos[tope - 1];                 // <-- NO verifica pre: p no esta vacia
    }

    public boolean esVacia() { return tope == 0; }

    public static void main(String[] args) {
        PilaSinExcepcion p = new PilaSinExcepcion(3);
        p.apilar(10);
        p.desapilar();
        System.out.println("Pila vacia, ahora intento desapilar() de nuevo...");
        try {
            p.desapilar();                     // viola la precondicion, pero nadie la controla
        } catch (RuntimeException e) {
            System.out.println("Igual se atrapo ALGO, pero no fue a proposito.");
            System.out.println("Clase de la excepcion: " + e.getClass().getSimpleName());
            System.out.println("Mensaje: \"" + e.getMessage() + "\"");
            System.out.println("-> No dice nada de \"la pila estaba vacia\": es un detalle");
            System.out.println("   interno (el indice del arreglo) que se filtro para afuera.");
        }
    }
}
