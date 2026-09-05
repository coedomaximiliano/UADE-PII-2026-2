/*
 * BLOQUE 2: VISIBILIDAD (encapsulamiento)
 * -----------------------------------------
 * "public"  -> accesible desde cualquier otra clase.
 * "private" -> accesible SOLO dentro de la propia clase.
 *
 * La idea central: los ATRIBUTOS casi siempre van "private", y se accede
 * a ellos a través de MÉTODOS "public" (getters/setters, u operaciones
 * como depositar/extraer). Así la clase controla y protege su propio
 * estado interno: nadie de afuera puede dejarlo en un estado inválido.
 *
 * Comparamos dos versiones de la "misma idea" de cuenta:
 *   - CuentaMal: atributos public -> cualquiera los puede romper.
 *   - CuentaBien: atributos private -> protegidos por sus métodos.
 */
public class Ejemplo2_Visibilidad {

    public static void main(String[] args) {

        System.out.println("=== CuentaMal (atributos public) ===");
        CuentaMal cm = new CuentaMal("Ana", 1000.0);
        cm.saldo = cm.saldo + 250.0;   // esto "funciona", pero es peligroso...
        cm.saldo = -999999.0;          // ...porque también permite esto.
        System.out.println("Saldo final (inválido): " + cm.saldo);

        System.out.println();
        System.out.println("=== CuentaBien (atributos private) ===");
        CuentaBien cb = new CuentaBien("Luis", 1000.0);
        cb.depositar(250.0);           // única forma permitida de modificar el saldo
        System.out.println("Saldo final: " + cb.consultarSaldo());

        // La siguiente línea, si la descomentamos, NO COMPILA:
        // cb.saldo = -999999.0;   // error: saldo tiene visibilidad private
    }
}

// ---- Versión SIN encapsular (para mostrar el problema) ----
class CuentaMal {
    public String titular;
    public double saldo;

    public CuentaMal(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
    }
}

// ---- Versión BIEN encapsulada ----
class CuentaBien {
    private String titular;
    private double saldo;

    public CuentaBien(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    // El único camino para modificar "saldo" desde afuera es este método,
    // que puede además validar reglas (por ejemplo, no depositar montos negativos).
    public void depositar(double monto) {
        if (monto <= 0) {
            System.out.println("Monto inválido, no se depositó nada.");
            return;
        }
        saldo = saldo + monto;
    }

    public double consultarSaldo() {
        return saldo;
    }

    public String getTitular() {
        return titular;
    }
}

/*
 * Para pensar con el curso:
 * ¿Qué pasaría si "PilaTDA"
 * expusiera su arreglo interno como "public"? Cualquiera podría escribir
 * directamente en cualquier posición y romper el invariante de la pila.
 * La visibilidad es la herramienta que hace posible el concepto de
 * "abstracción" de un TDA: el que usa la pila solo puede interactuar
 * con ella a través de sus operaciones públicas.
 */
