/*
 * BLOQUE 4 (ejemplo extra): EXCEPCIONES
 * -----------------------------------------
 * Mismo mecanismo que Ejemplo4_Excepciones (throw / throws / try-catch /
 * excepción propia), aplicado a otra precondición: la edad de una
 * persona tiene que estar en un rango válido.
 */
public class Ejemplo4b_Excepciones {

    public static void main(String[] args) {
        Persona persona = new Persona("Sofía");

        int[] edadesAProbar = { 25, -3, 200, 40 };

        for (int edad : edadesAProbar) {
            try {
                persona.setEdad(edad);
                System.out.println("Edad seteada correctamente: " + persona.getEdad());
            } catch (EdadInvalidaException e) {
                System.out.println("No se pudo setear edad=" + edad + " -> " + e.getMessage());
            }
        }

        System.out.println("Edad final válida: " + persona.getEdad());
    }
}

// Excepción propia: se dispara cuando se viola la precondición
// "0 <= edad <= 130".
class EdadInvalidaException extends Exception {
    public EdadInvalidaException(String mensaje) {
        super(mensaje);
    }
}

class Persona {
    private String nombre;
    private int edad;

    public Persona(String nombre) {
        this.nombre = nombre;
        this.edad = 0;
    }

    public void setEdad(int edad) throws EdadInvalidaException {
        if (edad < 0 || edad > 130) {
            throw new EdadInvalidaException("La edad " + edad + " está fuera del rango permitido (0-130)");
        }
        this.edad = edad;
    }

    public int getEdad() {
        return edad;
    }

    public String getNombre() {
        return nombre;
    }
}
