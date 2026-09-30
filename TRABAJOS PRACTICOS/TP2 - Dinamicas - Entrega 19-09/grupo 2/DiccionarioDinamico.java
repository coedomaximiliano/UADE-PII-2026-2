/**
 * TDA Diccionario -- implementacion DINAMICA (cadena de NodoDiccionario).
 *
 * Cada par (clave, valor) se guarda en un NodoDiccionario. Los pares
 * nuevos se agregan al PRINCIPIO de la cadena, que es la insercion mas
 * barata cuando no hay que mantener ningun orden. No hay capacidad:
 * crece un nodo por vez, exactamente lo que hace falta.
 *
 * Complejidad (n = cantidad de claves):
 *   crear()          O(1)
 *   definir()        O(n)  -- buscarNodo recorre la cadena
 *   obtener()        O(n)  -- buscarNodo recorre la cadena
 *   eliminar()       O(n)  -- recorre buscando la clave
 *   existeClave()    O(n)  -- buscarNodo recorre la cadena
 *   esVacio()        O(1)
 *   cantidadClaves() O(1)  -- se mantiene un contador
 *   claves()         O(n)  -- recorre toda la cadena una vez
 */
public class DiccionarioDinamico implements Diccionario {

    private NodoDiccionario cabeza;
    private int cantidad;

    // crear() -> Diccionario
    public DiccionarioDinamico() {
        this.cabeza = null;
        this.cantidad = 0;
    }

    // Auxiliar interno (NO es parte de la especificacion): busca el
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

    @Override
    public void definir(Object clave, Object valor) {
        NodoDiccionario existente = buscarNodo(clave);
        if (existente != null) {
            existente.valor = valor;            // actualiza, no duplica
        } else {
            NodoDiccionario nuevo = new NodoDiccionario(clave, valor);
            nuevo.siguiente = cabeza;           // insercion al principio: O(1)
            cabeza = nuevo;
            cantidad++;
        }
    }

    @Override
    public Object obtener(Object clave) {
        NodoDiccionario nodo = buscarNodo(clave);
        if (nodo == null) {
            throw new RuntimeException("obtener(): la clave no existe -> " + clave);
        }
        return nodo.valor;
    }

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
        // si la clave no estaba, el diccionario queda igual (tolerante)
    }

    @Override
    public boolean existeClave(Object clave) {
        return buscarNodo(clave) != null;
    }

    @Override
    public boolean esVacio() {
        return cabeza == null;
    }

    @Override
    public int cantidadClaves() {
        return cantidad;
    }

    @Override
    public Lista claves() {
        Lista resultado = new Lista();
        NodoDiccionario actual = cabeza;
        while (actual != null) {
            resultado.agregar(actual.clave);
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
}
