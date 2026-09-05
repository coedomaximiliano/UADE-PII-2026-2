/*
 * BLOQUE 3: INTERFACES
 * -----------------------------------------
 * Una interfaz declara QUÉ operaciones existen (nombre, parámetros, qué
 * devuelven) pero no CÓMO se implementan. Cualquier clase que la
 * implemente ("implements") debe definir el cuerpo de todos sus métodos.
 *
 * Esto es EXACTAMENTE la idea de "especificación" vs. "implementación"
 * que van a usar con los TDA (Pila, Cola, Conjunto, etc.): la interfaz
 * dice QUÉ hace la operación, cada clase que la implementa decide CÓMO.
 * Por eso, para un mismo comportamiento, puede haber varias
 * implementaciones distintas y el código que las usa no necesita saber
 * cuál de todas está "por dentro".
 */
public class Ejemplo3_Interfaces {

    public static void main(String[] args) {

        // La variable se declara del tipo INTERFAZ, no de una clase concreta.
        OperacionesCuenta cuenta1 = new CuentaSimple("Ana", 1000.0);
        usarCuenta(cuenta1);

        System.out.println();

        // Misma interfaz, OTRA implementación (permite quedar en negativo
        // hasta un límite de descubierto). El método usarCuenta() no cambia
        // ni una línea: no le importa cuál implementación está usando.
        OperacionesCuenta cuenta2 = new CuentaConDescubierto("Luis", 1000.0, 500.0);
        usarCuenta(cuenta2);
    }

    // Este método solo conoce la INTERFAZ. No sabe (ni le importa) si
    // recibe una CuentaSimple o una CuentaConDescubierto.
    static void usarCuenta(OperacionesCuenta cuenta) {
        cuenta.depositar(200.0);
        cuenta.extraer(300.0);
        System.out.println("Saldo: " + cuenta.consultarSaldo());
    }
}

// ---- La especificación (el "contrato") ----
interface OperacionesCuenta {
    void depositar(double monto);
    void extraer(double monto);
    double consultarSaldo();
}

// ---- Implementación 1: la más simple ----
class CuentaSimple implements OperacionesCuenta {
    private String titular;
    private double saldo;

    public CuentaSimple(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public void depositar(double monto) {
        saldo += monto;
    }

    public void extraer(double monto) {
        saldo -= monto;   // por ahora, sin validar (eso lo vemos en el Bloque 4)
    }

    public double consultarSaldo() {
        return saldo;
    }
}

// ---- Implementación 2: distinta "por dentro", mismo "por fuera" ----
class CuentaConDescubierto implements OperacionesCuenta {
    private String titular;
    private double saldo;
    private double limiteDescubierto;

    public CuentaConDescubierto(String titular, double saldoInicial, double limiteDescubierto) {
        this.titular = titular;
        this.saldo = saldoInicial;
        this.limiteDescubierto = limiteDescubierto;
    }

    public void depositar(double monto) {
        saldo += monto;
    }

    public void extraer(double monto) {
        if (saldo - monto < -limiteDescubierto) {
            System.out.println("Operación rechazada: supera el límite de descubierto.");
            return;
        }
        saldo -= monto;
    }

    public double consultarSaldo() {
        return saldo;
    }
}
