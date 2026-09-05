/*
 * BLOQUE 1: CLASES Y OBJETOS (POO básico)
 * -----------------------------------------
 * Una CLASE es un molde: define qué datos (atributos) y qué comportamiento
 * (métodos) va a tener cada OBJETO que se construya a partir de ella.
 * Un OBJETO es una instancia concreta de esa clase, creada con "new".
 *
 * Vamos a modelar una CuentaBancaria muy simple. Esta misma clase la vamos
 * a ir retocando en los próximos ejemplos (visibilidad, interfaces,
 * excepciones), así que conviene mirarla con atención.
 */
public class Ejemplo1_ClasesYObjetos {

    public static void main(String[] args) {
        // "new" crea un OBJETO (una instancia) de la clase CuentaBancaria.
        // Internamente, se ejecuta el CONSTRUCTOR con los valores que le pasamos.
        CuentaBancaria cuentaDeAna = new CuentaBancaria("Ana", 1000.0);
        CuentaBancaria cuentaDeLuis = new CuentaBancaria("Luis", 500.0);

        // Cada objeto tiene sus PROPIOS valores de atributos (su propio estado).
        System.out.println(cuentaDeAna);
        System.out.println(cuentaDeLuis);

        // Invocamos MÉTODOS sobre cada objeto. El método modifica el estado
        // interno del objeto sobre el que se llama.
        cuentaDeAna.depositar(250.0);
        cuentaDeLuis.depositar(50.0);

        System.out.println("--- Después de depositar ---");
        System.out.println(cuentaDeAna);
        System.out.println(cuentaDeLuis);

        // Dos objetos de la misma clase son independientes entre sí.
        System.out.println("Saldo de Ana: " + cuentaDeAna.consultarSaldo());
        System.out.println("Saldo de Luis: " + cuentaDeLuis.consultarSaldo());
    }
}

/*
 * La clase CuentaBancaria:
 *  - ATRIBUTOS: titular y saldo (el "estado" de cada objeto).
 *  - CONSTRUCTOR: inicializa los atributos cuando se crea el objeto.
 *  - MÉTODOS: definen qué operaciones se pueden hacer con una cuenta.
 *
 * Ojo: todavía no hablamos de "private" / "public" en los atributos
 * (eso es el Bloque 2), acá el foco está solo en clase vs. objeto.
 */
class CuentaBancaria {

    String titular;
    double saldo;

    // Constructor: mismo nombre que la clase, sin tipo de retorno.
    public CuentaBancaria(String titular, double saldoInicial) {
        this.titular = titular;   // "this" distingue el atributo del parámetro
        this.saldo = saldoInicial;
    }

    public void depositar(double monto) {
        saldo = saldo + monto;
    }

    public double consultarSaldo() {
        return saldo;
    }

    // toString() define cómo se "imprime" un objeto de esta clase.
    @Override
    public String toString() {
        return "Cuenta[titular=" + titular + ", saldo=" + saldo + "]";
    }
}
