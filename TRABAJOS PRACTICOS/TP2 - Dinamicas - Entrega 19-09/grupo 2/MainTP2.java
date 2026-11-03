/**
 * Demo de la Parte 4: prueba los cuatro metodos de utilizacion sobre las
 * DOS implementaciones del Diccionario, para mostrar que el mismo codigo
 * funciona igual con cualquiera (trabaja contra la interfaz).
 */
public class MainTP2 {

    // carga un diccionario de ejemplo (nombre -> edad) sobre la
    // implementacion que se le pase.
    static void cargarEjemplo(Diccionario d) {
        d.definir("ana", 30);
        d.definir("beto", 17);
        d.definir("caro", 45);
        d.definir("dario", 12);
    }

    static void probar(String titulo, Diccionario d1, Diccionario d2) {
        System.out.println("================ " + titulo + " ================");
        cargarEjemplo(d1);
        d2.definir("caro", 99);      // clave repetida con d1
        d2.definir("eze", 21);

        System.out.println("d1 = " + d1);
        System.out.println("d2 = " + d2);

        Diccionario comb = UtilidadesDiccionario.combinarDiccionarios(d1, d2);
        System.out.println("combinar(d1,d2)      = " + comb + "   (caro gana el valor de d2 = 99)");

        Diccionario inv = UtilidadesDiccionario.invertir(d1);
        System.out.println("invertir(d1)         = " + inv);

        int cuantos = UtilidadesDiccionario.contarValoresMayoresA(d1, 18);
        System.out.println("contarValoresMayoresA(d1, 18) = " + cuantos + "   (ana=30 y caro=45)");

        Lista ordenadas = UtilidadesDiccionario.clavesOrdenadas(d1);
        System.out.println("clavesOrdenadas(d1)  = " + ordenadas);

        System.out.println("d1 sin modificar     = " + d1);
        System.out.println("d2 sin modificar     = " + d2);
        System.out.println();
    }

    public static void main(String[] args) {
        // mismas pruebas, una vez con cada implementacion
        probar("Implementacion DINAMICA",
                new DiccionarioDinamico(), new DiccionarioDinamico());

        probar("Implementacion ESTATICA",
                new DiccionarioEstatico(20), new DiccionarioEstatico(20));
    }
}
