public class DiccionarioDinamico implements Diccionario {
    private NodoDiccionario cabeza;
    private int cantidad;

    public DiccionarioDinamico() {
        this.cabeza = null;
        this.cantidad = 0;
    }

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
            existente.valor = valor;
        } else {
            NodoDiccionario nuevo = new NodoDiccionario(clave, valor);
            nuevo.siguiente = cabeza;
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
        throw new RuntimeException("eliminar(): la clave no existe -> " + clave);
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
