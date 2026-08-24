/**
 * Clase principal que usa ColaPrioridadEstatica.java (clase separada)
 * Reproduce la prueba del Ejercicio 1 de la Clase 3.
 */
public class Principal {
    public static void main(String[] args) {

        ColaPrioridadEstatica cp = new ColaPrioridadEstatica(5);

        System.out.println("=== Insertando 5 tareas con distintas prioridades ===");
        cp.insertar("Backup", 1);
        cp.insertar("Incendio", 5);
        cp.insertar("Email", 2);
        cp.insertar("Reunion", 3);
        cp.insertar("Deploy", 4);
        System.out.println("Tamanio: " + cp.tamanio() + " (deberia ser 5)");

        System.out.println("\n=== verMax() antes de extraer ===");
        System.out.println("verMax(): " + cp.verMax() + " (deberia ser Incendio, prioridad 5)");

        System.out.println("\n=== Extrayendo todas con extraerMax() ===");
        System.out.println("(deberian salir en orden de prioridad DESCENDENTE: Incendio, Deploy, Reunion, Email, Backup)");
        while (!cp.esVacia()) {
            System.out.println("Extraido: " + cp.extraerMax());
        }
        System.out.println("Tamanio final: " + cp.tamanio() + " (deberia ser 0)");

        System.out.println("\n=== Probando la excepcion de cola vacia ===");
        try {
            cp.extraerMax();
        } catch (RuntimeException e) {
            System.out.println("Excepcion capturada correctamente: " + e.getMessage());
        }

        System.out.println("\n=== Costo de extraerMax() con 1.000.000 de elementos ===");
        System.out.println("Cada extraerMax() recorre TODO el array para encontrar el maximo:");
        System.out.println("son 1.000.000 de comparaciones en el peor caso -> O(n).");
        System.out.println("En cambio, insertar() es siempre O(1), sin importar cuantos elementos haya.");
    }
}
