public class Persona {
    private String nombre;
    private int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void cumplirAnios() {
        edad++;
    }

    @Override
    public String toString() {
        return nombre + " tiene " + edad + " años";
    }
}
