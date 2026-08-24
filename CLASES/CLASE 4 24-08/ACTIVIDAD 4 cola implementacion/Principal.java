/**
 * Clase principal que usa ColaEstatica.java (clase separada)
 */
public class Principal {
    public static void main(String[] args) {

        ColaEstatica c = new ColaEstatica(5);

        System.out.println("=== Encolando 5 elementos (10,20,30,40,50) ===");
        c.encolar(10);
        c.encolar(20);
        c.encolar(30);
        c.encolar(40);
        c.encolar(50);
        System.out.println("Tamanio: " + c.tamanio() + " (deberia ser 5, cola llena)");

        System.out.println("\n=== Desencolando 2 elementos ===");
        System.out.println("Desencolado: " + c.desencolar());  // 10
        System.out.println("Desencolado: " + c.desencolar());  // 20
        System.out.println("Tamanio: " + c.tamanio() + " (deberia ser 3)");

        System.out.println("\n=== Encolando 2 elementos mas (60,70) ===");
        // En este punto, sin indices circulares, esto fallaria por
        // "falta de lugar" aunque en realidad quedaron 2 lugares
        // libres al principio del array (donde estaban el 10 y el 20).
        // Gracias al operador %, fin "da la vuelta" y los reutiliza.
        c.encolar(60);
        c.encolar(70);
        System.out.println("Tamanio: " + c.tamanio() + " (deberia ser 5, cola llena de nuevo)");

        System.out.println("\n=== Vaciando la cola completa (deberia salir 30,40,50,60,70) ===");
        while (!c.esVacia()) {
            System.out.println(c.desencolar());
        }

        System.out.println("\n=== Probando la excepcion de cola vacia ===");
        try {
            c.desencolar();
        } catch (RuntimeException e) {
            System.out.println("Excepcion capturada correctamente: " + e.getMessage());
        }

        System.out.println("\n=== Que pasa si NO fuera circular (para comparar) ===");
        System.out.println("Sin el operador %, 'fin' seguiria creciendo sin volver a 0.");
        System.out.println("Al llegar a fin == capacidad, encolar() lanzaria 'Cola llena'");
        System.out.println("aunque hubiera lugares libres al principio del array.");
        System.out.println("Esa es la ventaja de la cola circular: reutiliza el espacio,");
        System.out.println("y sus operaciones siguen siendo O(1).");
    }
}
