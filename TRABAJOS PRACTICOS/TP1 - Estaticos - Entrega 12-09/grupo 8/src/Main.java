import Implementaciones.PilaEstaticaA;
import TDAs.PilaTDA;

public class Main {

    public static void main(String[] args) {
        // Pruebas de los métodos de Pila (está incompleto) -- "ES OPCIONAL, LO HICE POR NECESIDAD"
        PilaTDA p1 = new PilaEstaticaA(100);
        p1.apilar(1);
        p1.apilar(4);
        p1.apilar(6);
        p1.apilar(7);

        System.out.println("Pila: " + p1);
        System.out.println("Tope: " + p1.tope());

        PilaTDA p2 = Metodos.pasarPila(p1);

        System.out.println("Pila: " + p2);
        System.out.println("Tope: " + p2.tope());
    }
}
