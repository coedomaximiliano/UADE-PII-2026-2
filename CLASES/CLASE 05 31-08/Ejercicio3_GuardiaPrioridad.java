/**
 * ALGORITMOS Y ESTRUCTURAS DE DATOS II - Clase 4
 * Ejercicio 3: resolucion de problemas combinando TDAs -- Sala de
 * espera de una guardia medica, atendida por urgencia.
 *
 * Combina:
 *   - TDA Cola con Prioridad (Clase 3, implementacion estatica sin
 *     ordenar): decide A QUIEN atender primero segun el nivel de
 *     urgencia -- insertar es O(1), extraerMax recorre para encontrar
 *     la mayor prioridad, O(n).
 *   - TDA Diccionario dinamico (esta clase): asocia cada numero de
 *     turno con el nombre del paciente, para poder anunciar "atender
 *     al paciente X" en vez de solo un numero.
 *
 * Idea del problema: cada paciente que llega recibe un numero de turno
 * y un nivel de urgencia (1 = mas urgente). La Cola con Prioridad
 * decide el ORDEN de atencion; el Diccionario resuelve, para el numero
 * de turno elegido, QUIEN es el paciente -- cada TDA resuelve una
 * mitad del problema.
 */
public class Ejercicio3_GuardiaPrioridad {

    // ============================================================
    // Cola con prioridad estatica sin ordenar (identica en espiritu a
    // la de Clase 3): insertar es O(1); extraerMax recorre todo, O(n).
    // Convencion: menor numero = mayor urgencia (1 es el mas urgente).
    // ============================================================
    static class ColaConPrioridad {
        private int[] valores;       // numeros de turno
        private int[] prioridades;   // nivel de urgencia (menor = mas urgente)
        private int cantidad, capacidad;

        ColaConPrioridad(int capacidad) {
            this.capacidad = capacidad;
            this.valores = new int[capacidad];
            this.prioridades = new int[capacidad];
            this.cantidad = 0;
        }

        boolean esVacia() {
            return cantidad == 0;
        }

        void insertar(int valor, int prioridad) {
            if (cantidad == capacidad) {
                throw new RuntimeException("Cola con prioridad llena");
            }
            valores[cantidad] = valor;
            prioridades[cantidad] = prioridad;
            cantidad++;
        }

        // Quita y devuelve el valor de MAYOR urgencia (menor numero de
        // prioridad). Recorre todo el arreglo: O(n).
        int extraerMax() {
            if (esVacia()) {
                throw new RuntimeException("Cola con prioridad vacia");
            }
            int idxMax = 0;
            for (int i = 1; i < cantidad; i++) {
                if (prioridades[i] < prioridades[idxMax]) {
                    idxMax = i;
                }
            }
            int valor = valores[idxMax];
            valores[idxMax] = valores[cantidad - 1];
            prioridades[idxMax] = prioridades[cantidad - 1];
            cantidad--;
            return valor;
        }
    }

    // ============================================================
    // GuardiaMedica: combina ColaConPrioridad (orden de atencion) +
    // DiccionarioDinamico (numero de turno -> nombre del paciente).
    // ============================================================
    static class GuardiaMedica {
        private ColaConPrioridad turnos;         // ordena por urgencia
        private DiccionarioDinamico pacientes;   // numero de turno -> nombre
        private int proximoNumero;

        GuardiaMedica(int capacidadMax) {
            this.turnos = new ColaConPrioridad(capacidadMax);
            this.pacientes = new DiccionarioDinamico();
            this.proximoNumero = 1;
        }

        int registrarPaciente(String nombre, int urgencia) {
            int numero = proximoNumero;
            proximoNumero++;
            pacientes.definir(numero, nombre);
            turnos.insertar(numero, urgencia);
            return numero;
        }

        // Atiende al paciente mas urgente en espera; devuelve null si
        // no queda nadie.
        String atenderSiguiente() {
            if (turnos.esVacia()) {
                return null;
            }
            int numero = turnos.extraerMax();
            String nombre = (String) pacientes.obtener(numero);
            pacientes.eliminar(numero);
            return nombre + " (turno #" + numero + ")";
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Llegada de pacientes (nombre, nivel de urgencia; 1 = mas urgente) ===");
        GuardiaMedica guardia = new GuardiaMedica(20);

        int t1 = guardia.registrarPaciente("Roberto Sanz", 4);
        System.out.println("registrarPaciente(\"Roberto Sanz\", urgencia=4) -> turno #" + t1);
        int t2 = guardia.registrarPaciente("Elena Prieto", 2);
        System.out.println("registrarPaciente(\"Elena Prieto\", urgencia=2) -> turno #" + t2);
        int t3 = guardia.registrarPaciente("Damian Ortiz", 5);
        System.out.println("registrarPaciente(\"Damian Ortiz\", urgencia=5) -> turno #" + t3);
        int t4 = guardia.registrarPaciente("Valeria Nunez", 1);
        System.out.println("registrarPaciente(\"Valeria Nunez\", urgencia=1) -> turno #" + t4);

        System.out.println();
        System.out.println("=== Orden real de atencion (NO es el orden de llegada) ===");
        for (int i = 1; i <= 4; i++) {
            System.out.println(i + ") atenderSiguiente() = " + guardia.atenderSiguiente());
        }

        System.out.println();
        System.out.println("=== atenderSiguiente() con la sala de espera vacia ===");
        System.out.println("atenderSiguiente() = " + guardia.atenderSiguiente() + " (null: no queda nadie)");
    }
}
