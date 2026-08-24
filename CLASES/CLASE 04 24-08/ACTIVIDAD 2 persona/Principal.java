public class Principal {
    public static void main(String[] args) {
        Persona p1 = new Persona("Ana", 20);
        Persona p2 = new Persona("Juan", 25);

        System.out.println(p1);
        System.out.println(p2);

        p1.cumplirAnios();
        System.out.println("Después del cumpleaños: " + p1);

        System.out.println("Nombre de p2: " + p2.getNombre());
        System.out.println("Edad de p2: " + p2.getEdad());
    }
}
