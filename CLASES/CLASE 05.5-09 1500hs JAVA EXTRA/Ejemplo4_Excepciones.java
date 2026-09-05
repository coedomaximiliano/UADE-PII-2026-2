/*
 * BLOQUE 4: EXCEPCIONES
 * -----------------------------------------
 * Una excepción es la forma que tiene Java de avisar "algo salió mal" y
 * cortar el flujo normal del programa. Se usa mucho para señalar que no
 * se cumplió una PRECONDICIÓN de una operación.
 *
 * Esto conecta directo con lo que van a ver en TDA: por ejemplo, la
 * operación "Desapilar" del TDA Pila tiene como precondición que la
 * pila NO esté vacía. En vez de dejar que el programa siga con datos
 * inválidos (o se rompa con un error críptico), se lanza una excepción
 * clara indicando qué precondición se violó.
 *
 * Vocabulario:
 *   throw          -> lanzar una excepción.
 *   throws         -> declarar que un método PUEDE lanzar una excepción.
 *   try / catch    -> intentar una operación y capturar el error si ocurre.
 *   Exception propia -> clase que extiende Exception (o RuntimeException).
 */
public class Ejemplo4_Excepciones {

    public static void main(String[] args) {
        CuentaConValidacion cuenta = new CuentaConValidacion("Ana", 1000.0);

        // Caso 1: operación válida.
        try {
            cuenta.extraer(300.0);
            System.out.println("Extracción OK. Saldo: " + cuenta.consultarSaldo());
        } catch (SaldoInsuficienteException e) {
            System.out.println("No se pudo extraer: " + e.getMessage());
        }

        // Caso 2: operación que viola la precondición (no hay saldo suficiente).
        try {
            cuenta.extraer(5000.0);
            System.out.println("Esto no debería imprimirse.");
        } catch (SaldoInsuficienteException e) {
            System.out.println("No se pudo extraer: " + e.getMessage());
        }

        // El programa sigue ejecutándose normalmente después del catch.
        System.out.println("Saldo final: " + cuenta.consultarSaldo());
    }
}

// ---- Excepción propia ----
// Extiende Exception (excepción "chequeada": el compilador OBLIGA a
// declararla con "throws" o a atraparla con try/catch).
class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(String mensaje) {
        super(mensaje);
    }
}

class CuentaConValidacion {
    private String titular;
    private double saldo;

    public CuentaConValidacion(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    // "throws" avisa a quien use este método que debe manejar la excepción.
    public void extraer(double monto) throws SaldoInsuficienteException {
        if (monto > saldo) {
            throw new SaldoInsuficienteException(
                "Se intentó extraer " + monto + " pero el saldo es " + saldo);
        }
        saldo -= monto;
    }

    public double consultarSaldo() {
        return saldo;
    }
}

/*
 * Para la próxima clase (TDA):
 * Cuando implementen PilaTDA, la operación Desapilar() va a poder lanzar
 * una excepción si se llama con la pila vacía, en vez de simplemente
 * fallar o devolver cualquier cosa. Ese es el mismo mecanismo que
 * acabamos de ver acá, aplicado a la precondición de un TDA.
 */
