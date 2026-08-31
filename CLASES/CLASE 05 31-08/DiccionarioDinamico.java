/**
 * ALGORITMOS Y ESTRUCTURAS DE DATOS II - Clase 4
 * TDA Diccionario -- implementacion dinamica (cadena de NodoDiccionario).
 *
 * Especificacion (igual a la usada en TP2, sin cambios):
 *   crear() -> Diccionario
 *   definir(d, clave, valor) -> Diccionario
 *   obtener(d, clave) -> elemento          pre: existeClave(d, clave)
 *   eliminar(d, clave) -> Diccionario      pre: existeClave(d, clave)
 *   existeClave(d, clave) -> boolean
 *   esVacio(d) -> boolean
 *   cantidadClaves(d) -> entero
 *   claves(d) -> lista de claves (sin orden particular)
 *
 * Cada par clave-valor se guarda en un NodoDiccionario. Los pares nuevos
 * se agregan al principio de la cadena (la insercion mas barata cuando
 * no hay que mantener ningun orden). No hay "capacidad": crece un nodo
 * por vez, exactamente lo que hace falta.
 */
public class DiccionarioDinamico {

    private NodoDiccionario cabeza;
    private int cantidad;

    // crear() -> Diccionario
    public DiccionarioDinamico() {
        this.cabeza = null;
        this.cantidad = 0;
    }

    // Auxiliar interno (no es parte de la especificacion): busca el
    // nodo cuya clave coincide, o null si no existe. Todas las
    // operaciones que necesitan localizar una clave lo reutilizan.
    private NodoDiccionario buscarNodo(Object clave) {
        NodoDiccionario actual = cabeza;
        while (actual != null) {
            if (actual.clave.equals(clave)) {
                return actual;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    // existeClave(d, clave) -> boolean
    public boolean existeClave(Object clave) {
        return buscarNodo(clave) != null;
    }

    // definir(d, clave, valor) -> Diccionario
    // post: si la clave ya existia, se reemplaza su valor; si no, se
    //       agrega el par nuevo al principio de la cadena.
    public void definir(Object clave, Object valor) {
        NodoDiccionario existente = buscarNodo(clave);
        if (existente != null) {
            existente.valor = valor;
        } else {
            NodoDiccionario nuevo = new NodoDiccionario(clave, valor);
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
            cantidad++;
        }
    }

    // obtener(d, clave) -> elemento     pre: existeClave(d, clave)
    public Object obtener(Object clave) {
        NodoDiccionario nodo = buscarNodo(clave);
        if (nodo == null) {
            throw new RuntimeException("obtener(): la clave no existe -> " + clave);
        }
        return nodo.valor;
    }

    // eliminar(d, clave) -> Diccionario     pre: existeClave(d, clave)
    public void eliminar(Object clave) {
        NodoDiccionario actual = cabeza;
        NodoDiccionario anterior = null;
        while (actual != null) {
            if (actual.clave.equals(clave)) {
                if (anterior == null) {
                    cabeza = actual.siguiente;
                } else {
                    anterior.siguiente = actual.siguiente;
                }
                cantidad--;
                return;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
        throw new RuntimeException("eliminar(): la clave no existe -> " + clave);
    }

    // esVacio(d) -> boolean
    public boolean esVacio() {
        return cabeza == null;
    }

    // cantidadClaves(d) -> entero
    public int cantidadClaves() {
        return cantidad;
    }

    // claves(d) -> lista de claves, sin ningun orden particular.
    // Se devuelve como Object[] para no depender de otro TDA todavia
    // no visto en esta clase (la Lista dinamica generica se retoma en
    // los TPs); el resultado tiene exactamente cantidadClaves(d) claves.
    public Object[] claves() {
        Object[] resultado = new Object[cantidad];
        NodoDiccionario actual = cabeza;
        int i = 0;
        while (actual != null) {
            resultado[i] = actual.clave;
            i++;
            actual = actual.siguiente;
        }
        return resultado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{ ");
        NodoDiccionario actual = cabeza;
        while (actual != null) {
            sb.append(actual.clave).append("=").append(actual.valor).append(" ");
            actual = actual.siguiente;
        }
        sb.append("}");
        return sb.toString();
    }

    // ================================================================
    // Demo: agenda de contactos (nombre -> telefono). Ejemplo distinto
    // del usado en TP2 (que trabajaba con edades), a proposito.
    // ================================================================
    public static void main(String[] args) {
        System.out.println("=== crear() ===");
        DiccionarioDinamico agenda = new DiccionarioDinamico();
        System.out.println("esVacio() = " + agenda.esVacio() + " | cantidadClaves() = " + agenda.cantidadClaves());

        System.out.println();
        System.out.println("=== definir(\"juan\",...), definir(\"maria\",...), definir(\"pedro\",...) ===");
        agenda.definir("juan", "11-2345-6789");
        agenda.definir("maria", "11-9876-5432");
        agenda.definir("pedro", "11-5555-1234");
        System.out.println(agenda);
        System.out.println("cantidadClaves() = " + agenda.cantidadClaves());

        System.out.println();
        System.out.println("=== definir(\"juan\", nuevoTelefono)  (actualiza, no agrega) ===");
        agenda.definir("juan", "11-0000-1111");
        System.out.println(agenda);
        System.out.println("cantidadClaves() = " + agenda.cantidadClaves() + " (no crecio)");

        System.out.println();
        System.out.println("=== obtener(\"juan\") ===");
        System.out.println("obtener(\"juan\") = " + agenda.obtener("juan"));

        System.out.println();
        System.out.println("=== existeClave(\"maria\") / existeClave(\"lucas\") ===");
        System.out.println("existeClave(\"maria\") = " + agenda.existeClave("maria"));
        System.out.println("existeClave(\"lucas\") = " + agenda.existeClave("lucas"));

        System.out.println();
        System.out.println("=== claves() ===");
        Object[] claves = agenda.claves();
        StringBuilder sb = new StringBuilder("[ ");
        for (Object c : claves) sb.append(c).append(" ");
        sb.append("]");
        System.out.println("claves() = " + sb + " (cantidad = " + claves.length + ")");

        System.out.println();
        System.out.println("=== eliminar(\"maria\") ===");
        agenda.eliminar("maria");
        System.out.println(agenda);
        System.out.println("cantidadClaves() = " + agenda.cantidadClaves());

        System.out.println();
        System.out.println("=== esVacio() sobre un diccionario no vacio ===");
        System.out.println("esVacio() = " + agenda.esVacio());

        System.out.println();
        System.out.println("=== precondicion violada: obtener(\"maria\") tras eliminarla ===");
        try {
            agenda.obtener("maria");
        } catch (RuntimeException e) {
            System.out.println("RuntimeException capturada: " + e.getMessage());
        }
    }
}
