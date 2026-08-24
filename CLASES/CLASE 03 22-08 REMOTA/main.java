public class Main {
    public static void main(String[] args) {
    PilaEstatica p = new PilaEstatica(5);

    System.out.println("Prueba de PilaEstatica");

    System.out.println(p.esVacia());   // true
    
    p.apilar(10);
    p.apilar(20);
    p.apilar(30);

    System.out.println(p.tope());      // 30 (no elimina)
    System.out.println(p.esVacia());   // false

    p.desapilar();                     // saca el 30
    System.out.println(p.tope());      // 20

    p.desapilar();
    p.desapilar();
    System.out.println(p.esVacia());   // true

    // p.desapilar();   // esto lanzaría RuntimeException("Pila vacia")
    }
}
