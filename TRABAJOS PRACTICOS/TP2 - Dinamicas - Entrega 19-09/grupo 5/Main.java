import Implementaciones.DiccionarioDinamico;
import Implementaciones.DiccionarioEstatico;
import TDAs.DiccionarioTDA;
import TDAs.ListaTDA;

public class Main {
    private static int ok = 0;
    private static int fallos = 0;

    public static void main(String[] args) {
        System.out.println("===== Pruebas con DiccionarioDinamico =====");
        probarTodo(false);

        System.out.println("\n===== Pruebas con DiccionarioEstatico =====");
        probarTodo(true);

        System.out.println("\n===== Resultado: " + ok + " OK, " + fallos + " FALLOS =====");
    }

    private static void probarTodo(boolean estatico) {
        probarCombinarDiccionarios(estatico);
        probarInvertir(estatico);
        probarContarValoresMayoresA(estatico);
        probarClavesOrdenadas(estatico);
    }

    // ---------- combinarDiccionarios ----------
    private static void probarCombinarDiccionarios(boolean estatico) {
        System.out.println("\n-- combinarDiccionarios --");

        // Caso 1: claves distintas.
        DiccionarioTDA d1 = crear(estatico);
        d1.definir("a", 1);
        d1.definir("b", 2);
        DiccionarioTDA d2 = crear(estatico);
        d2.definir("c", 3);
        d2.definir("d", 4);
        DiccionarioTDA r = Metodos.combinarDiccionarios(d1, d2);
        mostrar("d1", d1);
        mostrar("d2", d2);
        mostrar("resultado", r);
        verificar("sin repetidas: 4 claves", r.cantidadClaves() == 4);
        verificar("sin repetidas: a=1, b=2, c=3, d=4",
                r.obtener("a").equals(1) && r.obtener("b").equals(2)
                        && r.obtener("c").equals(3) && r.obtener("d").equals(4));

        // Caso 2: clave repetida, gana el valor de d2.
        DiccionarioTDA d3 = crear(estatico);
        d3.definir("x", 10);
        d3.definir("y", 20);
        DiccionarioTDA d4 = crear(estatico);
        d4.definir("y", 99);
        d4.definir("z", 30);
        r = Metodos.combinarDiccionarios(d3, d4);
        mostrar("resultado (y repetida)", r);
        verificar("repetida: 3 claves", r.cantidadClaves() == 3);
        verificar("repetida: y toma el valor de d2 (99)", r.obtener("y").equals(99));
        verificar("repetida: x=10 y z=30", r.obtener("x").equals(10) && r.obtener("z").equals(30));

        // Caso 3: no modifica los originales.
        verificar("no modifica d3", d3.cantidadClaves() == 2 && d3.obtener("y").equals(20));
        verificar("no modifica d4", d4.cantidadClaves() == 2 && d4.obtener("y").equals(99));

        // Caso 4: con un diccionario vacio.
        DiccionarioTDA vacio = crear(estatico);
        r = Metodos.combinarDiccionarios(vacio, d3);
        verificar("vacio + d3 = 2 claves", r.cantidadClaves() == 2);
        r = Metodos.combinarDiccionarios(d3, vacio);
        verificar("d3 + vacio = 2 claves", r.cantidadClaves() == 2);

        // Caso 5: ambos vacios.
        r = Metodos.combinarDiccionarios(crear(estatico), crear(estatico));
        verificar("vacio + vacio = vacio", r.esVacio());
    }

    // ---------- invertir ----------
    private static void probarInvertir(boolean estatico) {
        System.out.println("\n-- invertir --");

        // Caso 1: valores distintos.
        DiccionarioTDA d = crear(estatico);
        d.definir("uno", 1);
        d.definir("dos", 2);
        d.definir("tres", 3);
        DiccionarioTDA r = Metodos.invertir(d);
        mostrar("original", d);
        mostrar("invertido", r);
        verificar("invertido: 3 claves", r.cantidadClaves() == 3);
        verificar("invertido: 1->uno, 2->dos, 3->tres",
                r.obtener(1).equals("uno") && r.obtener(2).equals("dos") && r.obtener(3).equals("tres"));
        verificar("no modifica el original", d.cantidadClaves() == 3 && d.obtener("uno").equals(1));

        // Caso 2: valores repetidos (se pierde una de las claves originales).
        DiccionarioTDA dRep = crear(estatico);
        dRep.definir("a", 5);
        dRep.definir("b", 5);
        r = Metodos.invertir(dRep);
        mostrar("invertido (valores repetidos)", r);
        verificar("valores repetidos: queda 1 sola clave", r.cantidadClaves() == 1);
        verificar("valores repetidos: 5 -> 'a' o 'b'", r.obtener(5).equals("a") || r.obtener(5).equals("b"));

        // Caso 3: vacio.
        r = Metodos.invertir(crear(estatico));
        verificar("invertir vacio = vacio", r.esVacio());

        // Caso 4: doble inversion recupera el original (valores unicos).
        DiccionarioTDA doble = Metodos.invertir(Metodos.invertir(d));
        verificar("invertir dos veces recupera el original",
                doble.cantidadClaves() == 3 && doble.obtener("uno").equals(1)
                        && doble.obtener("dos").equals(2) && doble.obtener("tres").equals(3));
    }

    // ---------- contarValoresMayoresA ----------
    private static void probarContarValoresMayoresA(boolean estatico) {
        System.out.println("\n-- contarValoresMayoresA --");

        DiccionarioTDA d = crear(estatico);
        d.definir("a", 5);
        d.definir("b", 10);
        d.definir("c", 15);
        d.definir("d", 20);
        mostrar("diccionario", d);
        verificar("umbral 10 -> 2 (15 y 20)", Metodos.contarValoresMayoresA(d, 10) == 2);
        verificar("umbral 0 -> 4", Metodos.contarValoresMayoresA(d, 0) == 4);
        verificar("umbral 20 -> 0 (estricto)", Metodos.contarValoresMayoresA(d, 20) == 0);
        verificar("umbral 100 -> 0", Metodos.contarValoresMayoresA(d, 100) == 0);
        verificar("umbral negativo -> 4", Metodos.contarValoresMayoresA(d, -1) == 4);

        // Valores que no son numeros se ignoran; Double tambien cuenta.
        DiccionarioTDA mixto = crear(estatico);
        mixto.definir("a", 7);
        mixto.definir("b", "hola");
        mixto.definir("c", 3.5);
        mixto.definir("d", 12.7);
        mixto.definir("e", null);
        mostrar("mixto", mixto);
        verificar("mixto umbral 5 -> 2 (7 y 12.7)", Metodos.contarValoresMayoresA(mixto, 5) == 2);
        verificar("mixto umbral 3 -> 3 (7, 3.5 y 12.7)", Metodos.contarValoresMayoresA(mixto, 3) == 3);

        verificar("vacio -> 0", Metodos.contarValoresMayoresA(crear(estatico), 0) == 0);
    }

    // ---------- clavesOrdenadas ----------
    private static void probarClavesOrdenadas(boolean estatico) {
        System.out.println("\n-- clavesOrdenadas --");

        DiccionarioTDA d = crear(estatico);
        d.definir("mango", 1);
        d.definir("banana", 2);
        d.definir("cereza", 3);
        d.definir("anana", 4);
        d.definir("durazno", 5);
        mostrar("diccionario", d);

        ListaTDA lista = Metodos.clavesOrdenadas(d);
        String[] esperado = {"anana", "banana", "cereza", "durazno", "mango"};
        String obtenido = listaAString(lista);
        System.out.println("  claves ordenadas: " + obtenido);
        verificar("tamanio 5", lista.tamanio() == 5);
        verificar("orden alfabetico correcto", obtenido.equals(String.join(", ", esperado)));

        // El diccionario no se modifica.
        verificar("no modifica el diccionario", d.cantidadClaves() == 5 && d.obtener("mango").equals(1));

        // Un solo elemento.
        DiccionarioTDA uno = crear(estatico);
        uno.definir("solo", 1);
        verificar("un elemento", listaAString(Metodos.clavesOrdenadas(uno)).equals("solo"));

        // Vacio.
        verificar("vacio -> lista vacia", Metodos.clavesOrdenadas(crear(estatico)).esVacia());

        // Mayusculas y minusculas (compareTo: las mayusculas van antes).
        DiccionarioTDA caso = crear(estatico);
        caso.definir("b", 1);
        caso.definir("B", 2);
        caso.definir("a", 3);
        caso.definir("A", 4);
        verificar("mayusculas antes que minusculas", listaAString(Metodos.clavesOrdenadas(caso)).equals("A, B, a, b"));
    }

    // ---------- Auxiliares ----------
    private static DiccionarioTDA crear(boolean estatico) {
        return estatico ? new DiccionarioEstatico(20) : new DiccionarioDinamico();
    }

    private static void verificar(String descripcion, boolean condicion) {
        if (condicion) {
            ok++;
            System.out.println("  [OK]    " + descripcion);
        } else {
            fallos++;
            System.out.println("  [FALLO] " + descripcion);
        }
    }

    // Muestra un diccionario sin modificarlo (claves() devuelve una copia).
    private static void mostrar(String nombre, DiccionarioTDA d) {
        StringBuilder sb = new StringBuilder("{");
        ListaTDA claves = d.claves();
        boolean primero = true;
        while (!claves.esVacia()) {
            Object clave = claves.elegir();
            if (!primero) sb.append(", ");
            sb.append(clave).append("=").append(d.obtener(clave));
            primero = false;
            claves.eliminar(clave);
        }
        sb.append("}");
        System.out.println("  " + nombre + ": " + sb);
    }

    // Convierte una lista a String vaciando una copia por elegir/eliminar.
    // OJO: consume la lista que recibe; se usa solo sobre listas de resultado.
    private static String listaAString(ListaTDA lista) {
        // Se conserva el orden en que quedo la lista de resultado (ultimo agregado primero).
        // Como clavesOrdenadas agrega de menor a mayor y ListaDinamica agrega al inicio,
        // se recorre y se invierte para leerla de menor a mayor.
        StringBuilder sb = new StringBuilder();
        ListaTDA copia = lista;
        java.util.ArrayList<Object> elementos = new java.util.ArrayList<>();
        while (!copia.esVacia()) {
            Object e = copia.elegir();
            elementos.add(e);
            copia.eliminar(e);
        }
        // Se restaura la lista original.
        for (int i = elementos.size() - 1; i >= 0; i--) {
            lista.agregar(elementos.get(i));
        }
        java.util.Collections.reverse(elementos);
        for (int i = 0; i < elementos.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(elementos.get(i));
        }
        return sb.toString();
    }
}
