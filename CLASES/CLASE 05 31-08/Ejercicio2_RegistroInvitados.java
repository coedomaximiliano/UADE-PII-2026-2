/**
 * ALGORITMOS Y ESTRUCTURAS DE DATOS II - Clase 4
 * Ejercicio 2: resolucion de problemas combinando TDAs -- Registro de
 * ingreso a un evento.
 *
 * Combina:
 *   - TDA Conjunto (Clase 3, implementacion estatica): para responder
 *     rapido "?este DNI ya ingreso hoy?" sin guardar ningun dato extra
 *     -- es exactamente lo que el Conjunto especifica (pertenece/agregar).
 *   - TDA Diccionario dinamico (esta clase): para asociar cada DNI con
 *     el nombre completo del invitado, algo que un Conjunto NO puede
 *     hacer porque no guarda valores asociados.
 *
 * Idea del problema: en la entrada de un evento se escanea el DNI de
 * cada invitado. Si el DNI ya habia ingresado, hay que rechazarlo (no
 * se puede entrar dos veces); si es la primera vez, se registra el
 * ingreso y se guarda el nombre para poder imprimir despues una lista
 * de invitados presentes.
 */
public class Ejercicio2_RegistroInvitados {

    // ============================================================
    // Conjunto estatico (identico en espiritu al de Clase 3): solo
    // responde pertenece/agregar, sin valores asociados.
    // ============================================================
    static class Conjunto {
        private Object[] elementos;
        private int cantidad, capacidad;

        Conjunto(int capacidad) {
            this.capacidad = capacidad;
            this.elementos = new Object[capacidad];
            this.cantidad = 0;
        }

        boolean pertenece(Object x) {
            for (int i = 0; i < cantidad; i++) {
                if (elementos[i].equals(x)) return true;
            }
            return false;
        }

        void agregar(Object x) {
            if (pertenece(x)) return;
            if (cantidad == capacidad) {
                throw new RuntimeException("Conjunto lleno");
            }
            elementos[cantidad] = x;
            cantidad++;
        }

        int tamanio() {
            return cantidad;
        }
    }

    // ============================================================
    // RegistroEvento: combina Conjunto (deteccion de duplicados) +
    // DiccionarioDinamico (dni -> nombre completo).
    // ============================================================
    static class RegistroEvento {
        private Conjunto dniRegistrados;    // solo para pertenece(dni)
        private DiccionarioDinamico datos;  // dni -> nombre completo

        RegistroEvento(int capacidadMax) {
            this.dniRegistrados = new Conjunto(capacidadMax);
            this.datos = new DiccionarioDinamico();
        }

        // Devuelve true si el ingreso se registro; false si el DNI ya
        // habia ingresado antes (intento de duplicado).
        boolean registrarIngreso(String dni, String nombreCompleto) {
            if (dniRegistrados.pertenece(dni)) {
                return false;
            }
            dniRegistrados.agregar(dni);
            datos.definir(dni, nombreCompleto);
            return true;
        }

        String nombreDe(String dni) {
            if (!datos.existeClave(dni)) {
                return null;
            }
            return (String) datos.obtener(dni);
        }

        int cantidadIngresos() {
            return datos.cantidadClaves();
        }

        void imprimirPresentes() {
            Object[] dnis = datos.claves();
            System.out.println("Invitados presentes (" + dnis.length + "):");
            for (Object dni : dnis) {
                System.out.println("  - " + datos.obtener(dni) + " (DNI " + dni + ")");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Registro de ingreso, capacidad para 50 invitados ===");
        RegistroEvento evento = new RegistroEvento(50);

        System.out.println(evento.registrarIngreso("30111222", "Sofia Gimenez") ? "OK: ingreso Sofia Gimenez" : "RECHAZADO");
        System.out.println(evento.registrarIngreso("29888777", "Nicolas Ferrari") ? "OK: ingreso Nicolas Ferrari" : "RECHAZADO");
        System.out.println(evento.registrarIngreso("31444555", "Camila Duarte") ? "OK: ingreso Camila Duarte" : "RECHAZADO");

        System.out.println();
        System.out.println("=== Intento de reingreso con un DNI ya registrado ===");
        boolean ok = evento.registrarIngreso("29888777", "Nicolas Ferrari");
        System.out.println("registrarIngreso(\"29888777\", ...) = " + ok + " (rechazado: ya habia ingresado)");

        System.out.println();
        System.out.println("=== cantidadIngresos() ===");
        System.out.println("cantidadIngresos() = " + evento.cantidadIngresos() + " (el intento duplicado no sumo)");

        System.out.println();
        System.out.println("=== nombreDe(dni) ===");
        System.out.println("nombreDe(\"31444555\") = " + evento.nombreDe("31444555"));
        System.out.println("nombreDe(\"99999999\") = " + evento.nombreDe("99999999") + " (nunca ingreso)");

        System.out.println();
        evento.imprimirPresentes();
    }
}
