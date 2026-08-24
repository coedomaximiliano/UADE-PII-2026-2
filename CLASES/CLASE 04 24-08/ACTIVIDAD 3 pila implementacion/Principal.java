/**
 * Clase principal que usa PilaEstatica.java (clase separada)
 */
public class Principal {
    public static void main(String[] args) {

        PilaEstatica p = new PilaEstatica(5);

        System.out.println("=== Apilando 1 al 5 ===");
        for (int i = 1; i <= 5; i++) {
            p.apilar(i);
            System.out.println("Apilado: " + i + "  (tamanio actual: " + p.tamanio() + ")");
        }

        System.out.println("\n=== Intentando apilar un 6to elemento ===");
        try {
            p.apilar(6);
            System.out.println("ERROR: no deberia llegar aca, la pila estaba llena.");
        } catch (RuntimeException e) {
            System.out.println("Excepcion capturada correctamente: " + e.getMessage());
        }

        System.out.println("\n=== Desapilando todo (deberia salir 5,4,3,2,1) ===");
        while (!p.esVacia()) {
            System.out.println(p.desapilar());
        }

        System.out.println("\n=== Probando vaciar() ===");
        p.apilar(100);
        p.apilar(200);
        System.out.println("Tamanio antes de vaciar: " + p.tamanio());
        p.vaciar();
        System.out.println("Tamanio despues de vaciar: " + p.tamanio());
        System.out.println("esVacia() despues de vaciar: " + p.esVacia());
    }
}
