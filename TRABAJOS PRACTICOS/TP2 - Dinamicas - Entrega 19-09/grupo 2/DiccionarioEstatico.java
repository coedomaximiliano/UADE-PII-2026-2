/**
 * TDA Diccionario -- implementacion ESTATICA (dos arreglos paralelos de
 * tamano fijo, uno de claves y otro de valores) SIN ningun orden
 * particular entre las claves.
 *
 * Para que la comparacion con la version dinamica sea justa, usa la
 * MISMA estrategia: buscar linealmente la clave (claveAIndice). Solo
 * cambia el mecanismo de almacenamiento (arreglo vs. nodos).
 *
 * Al eliminar no hace falta correr el resto del arreglo: se reemplaza la
 * posicion eliminada por el ultimo elemento y se decrementa cantidad.
 *
 * Complejidad (n = cantidad de claves):
 *   crear()          O(1)
 *   definir()        O(n)  -- claveAIndice recorre el arreglo
 *   obtener()        O(n)  -- claveAIndice recorre el arreglo
 *   eliminar()       O(n)  -- claveAIndice recorre el arreglo
 *   existeClave()    O(n)  -- claveAIndice recorre el arreglo
 *   esVacio()        O(1)
 *   cantidadClaves() O(1)
 *   claves()         O(n)
 */
public class DiccionarioEstatico implements Diccionario {

    private Object[] claves;
    private Object[] valores;
    private int cantidad;
    private int capacidad;

    // crear() -> Diccionario (con capacidad maxima fija)
    public DiccionarioEstatico(int capacidad) {
        this.capacidad = capacidad;
        this.claves = new Object[capacidad];
        this.valores = new Object[capacidad];
        this.cantidad = 0;
    }

    // busca la posicion de clave recorriendo el arreglo, o -1 si no esta. O(n)
    private int claveAIndice(Object clave) {
        for (int i = 0; i < cantidad; i++) {
            if (claves[i].equals(clave)) {
                return i;
            }
        }
        return -1;
    }

    public boolean esLleno() {
        return cantidad == capacidad;
    }

    @Override
    public void definir(Object clave, Object valor) {
        int pos = claveAIndice(clave);
        if (pos == -1) {
            if (esLleno()) {
                throw new RuntimeException("Diccionario lleno");
            }
            pos = cantidad;
            claves[pos] = clave;
            cantidad++;
        }
        valores[pos] = valor;                   // agrega o actualiza
    }

    @Override
    public Object obtener(Object clave) {
        int pos = claveAIndice(clave);
        if (pos == -1) {
            throw new RuntimeException("obtener(): la clave no existe -> " + clave);
        }
        return valores[pos];
    }

    @Override
    public void eliminar(Object clave) {
        int pos = claveAIndice(clave);
        if (pos != -1) {
            claves[pos] = claves[cantidad - 1];   // pisa con el ultimo
            valores[pos] = valores[cantidad - 1];
            claves[cantidad - 1] = null;
            valores[cantidad - 1] = null;
            cantidad--;
        }
        // si no estaba, queda igual (tolerante)
    }

    @Override
    public boolean existeClave(Object clave) {
        return claveAIndice(clave) != -1;
    }

    @Override
    public boolean esVacio() {
        return cantidad == 0;
    }

    @Override
    public int cantidadClaves() {
        return cantidad;
    }

    @Override
    public Lista claves() {
        Lista resultado = new Lista();
        for (int i = 0; i < cantidad; i++) {
            resultado.agregar(claves[i]);
        }
        return resultado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{ ");
        for (int i = 0; i < cantidad; i++) {
            sb.append(claves[i]).append("=").append(valores[i]).append(" ");
        }
        sb.append("}");
        return sb.toString();
    }
}
