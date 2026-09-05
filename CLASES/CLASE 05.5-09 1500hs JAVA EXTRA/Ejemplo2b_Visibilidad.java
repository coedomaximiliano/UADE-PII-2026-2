/*
 * BLOQUE 2 (ejemplo extra): VISIBILIDAD
 * -----------------------------------------
 * Mismo concepto que Ejemplo2_Visibilidad (public vs private), ahora con
 * un Empleado y su sueldo. La idea a remarcar es siempre la misma: el
 * atributo se protege como "private" y solo se modifica a través de un
 * método "public" que puede validar la operación.
 */
public class Ejemplo2b_Visibilidad {

    public static void main(String[] args) {

        System.out.println("=== EmpleadoMal (sueldo public) ===");
        EmpleadoMal em = new EmpleadoMal("Carla", 800000.0);
        em.sueldo = -50000.0;   // nada impide poner un sueldo negativo
        System.out.println("Sueldo (inválido): " + em.sueldo);

        System.out.println();
        System.out.println("=== EmpleadoBien (sueldo private) ===");
        EmpleadoBien eb = new EmpleadoBien("Diego", 800000.0);
        eb.aumentarSueldo(15);       // aumento del 15%, vía método controlado
        System.out.println("Sueldo tras el aumento: " + eb.getSueldo());

        boolean seAplico = eb.aumentarSueldo(-200);  // porcentaje absurdo
        System.out.println("¿Se aplicó el aumento inválido? " + seAplico);
        System.out.println("Sueldo final: " + eb.getSueldo());

        // La siguiente línea, si la descomentamos, NO COMPILA:
        // eb.sueldo = -1.0;   // error: sueldo tiene visibilidad private
    }
}

class EmpleadoMal {
    public String nombre;
    public double sueldo;

    public EmpleadoMal(String nombre, double sueldo) {
        this.nombre = nombre;
        this.sueldo = sueldo;
    }
}

class EmpleadoBien {
    private String nombre;
    private double sueldo;

    public EmpleadoBien(String nombre, double sueldo) {
        this.nombre = nombre;
        this.sueldo = sueldo;
    }

    // Devuelve true/false según si pudo aplicar el aumento, en vez de
    // dejar que cualquiera deje el sueldo en un valor sin sentido.
    public boolean aumentarSueldo(double porcentaje) {
        if (porcentaje <= 0 || porcentaje > 100) {
            return false;
        }
        sueldo = sueldo + sueldo * (porcentaje / 100.0);
        return true;
    }

    public double getSueldo() {
        return sueldo;
    }

    public String getNombre() {
        return nombre;
    }
}
