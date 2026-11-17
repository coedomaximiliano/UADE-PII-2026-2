// TP2 - Algoritmos y Estructuras de Datos II
// Pedro Stella - Bautista Chavarri

/**
 * TDA Diccionario, implementacion DINAMICA: una cadena de NodoDiccionario enganchados
 * por referencia, sin ningun orden entre ellos (ni por clave, ni por insercion).
 * Los pares nuevos se agregan al principio de la cadena, que es la insercion mas barata
 * cuando no hay orden que mantener. No hay capacidad maxima: crece de a un nodo.
 * Costo: todo lo que necesita localizar una clave recorre la cadena, O(n).
 */
public class DiccionarioDinamico implements Diccionario {

    private NodoDiccionario cabeza;
    private int cantidad;

    // crear() -> Diccionario
    // post: devuelve un diccionario vacio
    public DiccionarioDinamico() {
        this.cabeza = null;
        this.cantidad = 0;
    }

    // auxiliar interno, no es una operacion del TDA: devuelve el nodo cuya clave
    // coincide, o null si no esta. Lo reutilizan definir, obtener y existeClave.
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

    // definir(d, clave, valor) -> Diccionario
    // post: si la clave ya existia se actualiza su valor; si no, el par nuevo queda
    //       al principio de la cadena
    @Override
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

    // obtener(d, clave) -> valor
    // pre: existeClave(d, clave)
    @Override
    public Object obtener(Object clave) {
        NodoDiccionario nodo = buscarNodo(clave);
        if (nodo == null) {
            throw new RuntimeException("obtener(): la clave no existe -> " + clave);
        }
        return nodo.valor;
    }

    // eliminar(d, clave) -> Diccionario
    // pre: existeClave(d, clave)
    // hay que recordar el nodo anterior para poder saltear al que se elimina
    @Override
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

    // existeClave(d, clave) -> boolean
    @Override
    public boolean existeClave(Object clave) {
        return buscarNodo(clave) != null;
    }

    // esVacio(d) -> boolean
    @Override
    public boolean esVacio() {
        return cabeza == null;
    }

    // cantidadClaves(d) -> entero
    // no recorre nada: cantidad se mantiene al dia en definir y eliminar
    @Override
    public int cantidadClaves() {
        return cantidad;
    }

    // claves(d) -> Lista
    // post: devuelve las claves en una lista, sin ningun orden particular (quedan en el
    //       orden de la cadena, o sea al reves del orden de insercion)
    @Override
    public ListaDinamica claves() {
        ListaDinamica resultado = new ListaDinamica();
        NodoDiccionario actual = cabeza;
        while (actual != null) {
            resultado.agregar(actual.clave);
            actual = actual.siguiente;
        }
        return resultado;
    }

    // esLleno(d) -> boolean
    // una implementacion dinamica no tiene capacidad maxima: nunca esta llena
    @Override
    public boolean esLleno() {
        return false;
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
}
