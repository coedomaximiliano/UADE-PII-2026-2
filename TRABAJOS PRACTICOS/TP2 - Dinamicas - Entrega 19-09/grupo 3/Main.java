// TP2 - Algoritmos y Estructuras de Datos II
// Pedro Stella - Bautista Chavarri

/**
 * Demo del TP2: prueba las dos implementaciones del TDA Diccionario, la Lista dinamica
 * y los metodos de la Parte 4, usando los diccionarios solo a traves de la interfaz.
 * Cada linea muestra lo obtenido, lo esperado y OK o ERROR; al final se cuentan.
 * Los diccionarios se imprimen con las claves ordenadas, para que la salida sea la misma
 * en las dos implementaciones (ninguna garantiza un orden entre las claves).
 */
public class Main {

    private static final int CAPACIDAD = 100;
    private static final String SIN_EXCEPCION = "sin excepcion";

    private static int cantidadOk = 0;
    private static int cantidadError = 0;

    public static void main(String[] args) {
        System.out.println("=== Operaciones del TDA Diccionario ===");
        operacionesDiccionario(new DiccionarioDinamico(), "DiccionarioDinamico");
        operacionesDiccionario(new DiccionarioEstatico(CAPACIDAD), "DiccionarioEstatico, capacidad " + CAPACIDAD);
        capacidadDelEstatico();
        listaDinamica();

        System.out.println();
        System.out.println("=== Parte 4: metodos de utilizacion ===");
        System.out.println();
        System.out.println("-- entradas dinamicas --");
        metodosParte4(true);
        System.out.println();
        System.out.println("-- entradas estaticas --");
        metodosParte4(false);

        System.out.println();
        System.out.println("Verificaciones: " + cantidadOk + " OK, " + cantidadError + " con error");
    }

    // mismo codigo para las dos implementaciones: solo usa la interfaz Diccionario
    private static void operacionesDiccionario(Diccionario d, String nombre) {
        System.out.println();
        System.out.println("-- " + nombre + " --");
        verificar("esVacio()", d.esVacio(), true);
        verificar("cantidadClaves()", d.cantidadClaves(), 0);
        verificar("claves()", d.claves().toString(), "[]");
        verificar("esLleno()", d.esLleno(), false);

        String error = SIN_EXCEPCION;
        try {
            d.obtener("ana");
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        verificar("obtener(ana) con la clave ausente lanza", error, "obtener(): la clave no existe -> ana");

        error = SIN_EXCEPCION;
        try {
            d.eliminar("ana");
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        verificar("eliminar(ana) con la clave ausente lanza", error, "eliminar(): la clave no existe -> ana");

        d.definir("ana", Integer.valueOf(30));
        d.definir("beto", Integer.valueOf(25));
        d.definir("caro", Integer.valueOf(41));
        verificar("definir(ana,30), definir(beto,25), definir(caro,41)", texto(d), "{ana=30, beto=25, caro=41}");
        verificar("cantidadClaves()", d.cantidadClaves(), 3);
        verificar("esVacio()", d.esVacio(), false);
        verificar("existeClave(beto)", d.existeClave("beto"), true);
        verificar("existeClave(dani)", d.existeClave("dani"), false);
        verificar("obtener(beto)", "" + d.obtener("beto"), "25");
        verificar("claves() devuelve 3 claves", d.claves().cantidad(), 3);

        d.definir("ana", Integer.valueOf(31));
        verificar("definir(ana,31) sobre una clave que ya estaba", texto(d), "{ana=31, beto=25, caro=41}");
        verificar("cantidadClaves() no crecio", d.cantidadClaves(), 3);

        d.eliminar("beto");
        verificar("eliminar(beto)", texto(d), "{ana=31, caro=41}");
        verificar("cantidadClaves()", d.cantidadClaves(), 2);
        verificar("existeClave(beto)", d.existeClave("beto"), false);

        d.eliminar("ana");
        d.eliminar("caro");
        verificar("eliminar el resto, esVacio()", d.esVacio(), true);
        verificar("cantidadClaves()", d.cantidadClaves(), 0);
    }

    // la capacidad es propia de la implementacion estatica: la dinamica nunca se llena
    private static void capacidadDelEstatico() {
        System.out.println();
        System.out.println("-- capacidad del DiccionarioEstatico, capacidad 2 --");
        Diccionario d = new DiccionarioEstatico(2);
        d.definir("ana", Integer.valueOf(30));
        d.definir("beto", Integer.valueOf(25));
        verificar("esLleno() con 2 de 2 claves", d.esLleno(), true);

        String error = SIN_EXCEPCION;
        try {
            d.definir("caro", Integer.valueOf(41));
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        verificar("definir() de una clave nueva con el diccionario lleno lanza", error, "Diccionario lleno");

        d.definir("ana", Integer.valueOf(99));
        verificar("definir() sobre una clave que ya estaba no necesita lugar", texto(d), "{ana=99, beto=25}");

        Diccionario dinamico = new DiccionarioDinamico();
        for (int i = 0; i < 50; i++) {
            dinamico.definir("clave-" + i, Integer.valueOf(i));
        }
        verificar("el dinamico con 50 claves sigue sin estar lleno", dinamico.esLleno(), false);
        verificar("cantidadClaves() del dinamico", dinamico.cantidadClaves(), 50);
    }

    private static void listaDinamica() {
        System.out.println();
        System.out.println("-- ListaDinamica --");
        ListaDinamica l = new ListaDinamica();
        verificar("esVacia()", l.esVacia(), true);
        verificar("cantidad()", l.cantidad(), 0);
        l.agregar("a");
        l.agregar("b");
        l.agregar("c");
        verificar("agregar(a), agregar(b), agregar(c)", l.toString(), "[a, b, c]");
        verificar("cantidad()", l.cantidad(), 3);
        verificar("obtener(0)", "" + l.obtener(0), "a");
        verificar("obtener(2)", "" + l.obtener(2), "c");
        verificar("esVacia()", l.esVacia(), false);

        String error = SIN_EXCEPCION;
        try {
            l.obtener(3);
        } catch (RuntimeException e) {
            error = e.getMessage();
        }
        verificar("obtener(3) fuera de rango lanza", error, "obtener(): indice fuera de rango -> 3");
    }

    // mismos casos para las dos implementaciones: los metodos solo usan la interfaz
    private static void metodosParte4(boolean dinamicos) {
        Diccionario d1 = diccionarioDe(dinamicos,
                new String[]{"ana", "beto", "caro"}, new int[]{30, 25, 41});
        Diccionario d2 = diccionarioDe(dinamicos,
                new String[]{"beto", "dani"}, new int[]{99, 18});
        Diccionario vacio = diccionarioDe(dinamicos, new String[]{}, new int[]{});

        Diccionario combinado = MetodosDiccionario.combinarDiccionarios(d1, d2);
        verificar("combinarDiccionarios(d1, d2), en beto gana el valor de d2",
                texto(combinado), "{ana=30, beto=99, caro=41, dani=18}");
        verificar("cantidadClaves() del combinado", combinado.cantidadClaves(), 4);
        verificar("d1 quedo igual", texto(d1), "{ana=30, beto=25, caro=41}");
        verificar("d2 quedo igual", texto(d2), "{beto=99, dani=18}");
        verificar("combinarDiccionarios(d1, vacio)", texto(MetodosDiccionario.combinarDiccionarios(d1, vacio)),
                "{ana=30, beto=25, caro=41}");
        verificar("combinarDiccionarios(vacio, vacio) es vacio",
                MetodosDiccionario.combinarDiccionarios(vacio, vacio).esVacio(), true);

        Diccionario invertido = MetodosDiccionario.invertir(d1);
        verificar("invertir(d1)", texto(invertido), "{25=beto, 30=ana, 41=caro}");
        verificar("d1 quedo igual", texto(d1), "{ana=30, beto=25, caro=41}");
        verificar("invertir(invertir(d1)) vuelve al original",
                texto(MetodosDiccionario.invertir(invertido)), "{ana=30, beto=25, caro=41}");
        verificar("invertir(vacio) es vacio", MetodosDiccionario.invertir(vacio).esVacio(), true);

        verificar("contarValoresMayoresA(d1, 26)", MetodosDiccionario.contarValoresMayoresA(d1, 26), 2);
        verificar("contarValoresMayoresA(d1, 41)", MetodosDiccionario.contarValoresMayoresA(d1, 41), 0);
        verificar("contarValoresMayoresA(d1, 0)", MetodosDiccionario.contarValoresMayoresA(d1, 0), 3);
        verificar("contarValoresMayoresA(vacio, 0)", MetodosDiccionario.contarValoresMayoresA(vacio, 0), 0);
        verificar("d1 quedo igual", texto(d1), "{ana=30, beto=25, caro=41}");

        Diccionario d3 = diccionarioDe(dinamicos,
                new String[]{"zeta", "ana", "mario", "beto"}, new int[]{1, 2, 3, 4});
        verificar("clavesOrdenadas(d3)", MetodosDiccionario.clavesOrdenadas(d3).toString(),
                "[ana, beto, mario, zeta]");
        verificar("clavesOrdenadas(d3) devuelve 4 claves", MetodosDiccionario.clavesOrdenadas(d3).cantidad(), 4);
        verificar("d3 quedo igual", texto(d3), "{ana=2, beto=4, mario=3, zeta=1}");
        verificar("clavesOrdenadas(vacio)", MetodosDiccionario.clavesOrdenadas(vacio).toString(), "[]");
    }

    // ---------- Auxiliares ----------

    // imprime "operacion: obtenido  (esperado ...)" con OK o ERROR, y lo cuenta
    private static void verificar(String operacion, String obtenido, String esperado) {
        String marca = "OK";
        if (obtenido.equals(esperado)) {
            cantidadOk++;
        } else {
            marca = "ERROR";
            cantidadError++;
        }
        System.out.println(operacion + ": " + obtenido + "  (esperado " + esperado + ")  " + marca);
    }

    private static void verificar(String operacion, int obtenido, int esperado) {
        verificar(operacion, "" + obtenido, "" + esperado);
    }

    private static void verificar(String operacion, boolean obtenido, boolean esperado) {
        verificar(operacion, "" + obtenido, "" + esperado);
    }

    // diccionario nuevo, dinamico o estatico, con los pares clave-valor dados
    private static Diccionario diccionarioDe(boolean dinamico, String[] claves, int[] valores) {
        Diccionario d;
        if (dinamico) {
            d = new DiccionarioDinamico();
        } else {
            d = new DiccionarioEstatico(CAPACIDAD);
        }
        for (int i = 0; i < claves.length; i++) {
            d.definir(claves[i], Integer.valueOf(valores[i]));
        }
        return d;
    }

    // devuelve {clave=valor, clave=valor} con las claves ordenadas, asi la salida no
    // depende de en que orden las guarde cada implementacion. Ordena por el texto de la
    // clave, para que sirva tambien cuando las claves son numeros (invertir()).
    private static String texto(Diccionario d) {
        ListaDinamica claves = d.claves();
        int n = claves.cantidad();
        Object[] ordenadas = new Object[n];
        for (int i = 0; i < n; i++) {
            Object clave = claves.obtener(i);
            int j = i - 1;
            while (j >= 0 && ordenadas[j].toString().compareTo(clave.toString()) > 0) {
                ordenadas[j + 1] = ordenadas[j];
                j--;
            }
            ordenadas[j + 1] = clave;
        }
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < n; i++) {
            sb.append(ordenadas[i]).append("=").append(d.obtener(ordenadas[i]));
            if (i < n - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
