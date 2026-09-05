/*
 * BLOQUE 1 (ejemplo extra): CLASES Y OBJETOS
 * -----------------------------------------
 * Mismo concepto que Ejemplo1_ClasesYObjetos, pero con otro dominio
 * (productos de un depósito) para que quede claro que "clase y objeto"
 * no es algo exclusivo de las cuentas bancarias: sirve para modelar
 * cualquier cosa del mundo real.
 */
public class Ejemplo1b_ClasesYObjetos {

    public static void main(String[] args) {
        Producto lapicera = new Producto("Lapicera", 350.0, 120);
        Producto cuaderno = new Producto("Cuaderno", 1200.0, 40);

        System.out.println(lapicera);
        System.out.println(cuaderno);

        // Cada objeto calcula su propio valor total en base a SU estado.
        System.out.println("Valor total en stock (lapiceras): " + lapicera.valorTotalEnStock());
        System.out.println("Valor total en stock (cuadernos): " + cuaderno.valorTotalEnStock());

        // Vender modifica el estado del objeto (baja el stock).
        lapicera.vender(20);
        System.out.println("--- Después de vender 20 lapiceras ---");
        System.out.println(lapicera);
        System.out.println("Nuevo valor total en stock: " + lapicera.valorTotalEnStock());
    }
}

class Producto {

    String nombre;
    double precioUnitario;
    int stock;

    public Producto(String nombre, double precioUnitario, int stock) {
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.stock = stock;
    }

    public void vender(int cantidad) {
        stock = stock - cantidad;
    }

    public double valorTotalEnStock() {
        return precioUnitario * stock;
    }

    @Override
    public String toString() {
        return "Producto[nombre=" + nombre + ", precio=" + precioUnitario + ", stock=" + stock + "]";
    }
}
