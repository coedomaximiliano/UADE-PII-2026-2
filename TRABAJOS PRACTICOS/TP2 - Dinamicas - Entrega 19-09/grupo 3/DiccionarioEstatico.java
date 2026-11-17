// TP2 - Algoritmos y Estructuras de Datos II
// Pedro Stella - Bautista Chavarri

/**
 * TDA Diccionario, implementacion ESTATICA: dos arreglos paralelos de tamano fijo, uno
 * de claves y otro de valores, sin ningun orden particular entre las claves
 * (valoresGuardados[i] es el valor de clavesGuardadas[i]).
 * Usa la misma estrategia que la dinamica, buscar la clave recorriendo linealmente, asi
 * la comparacion de la Parte 3 mide el mecanismo de almacenamiento -- arreglo contra
 * cadena de nodos -- y no dos algoritmos distintos.
 */
public class DiccionarioEstatico implements Diccionario {

    private Object[] clavesGuardadas;
    private Object[] valoresGuardados;
    private int cantidad;
    private int capacidad;

    // crear() -> Diccionario
    // post: devuelve un diccionario vacio de capacidad fija
    public DiccionarioEstatico(int capacidad) {
        this.capacidad = capacidad;
        this.clavesGuardadas = new Object[capacidad];
        this.valoresGuardados = new Object[capacidad];
        this.cantidad = 0;
    }

    // auxiliar interno, no es una operacion del TDA: devuelve la posicion de la clave,
    // o -1 si no esta. Recorre desde el principio, O(n).
    private int claveAIndice(Object clave) {
        for (int i = 0; i < cantidad; i++) {
            if (clavesGuardadas[i].equals(clave)) {
                return i;
            }
        }
        return -1;
    }

    // definir(d, clave, valor) -> Diccionario
    // pre:  d no esta lleno, o clave ya pertenece a d
    // post: si la clave ya existia se le pisa el valor; si no, el par nuevo queda en el
    //       primer lugar libre del arreglo
    @Override
    public void definir(Object clave, Object valor) {
        int pos = claveAIndice(clave);
        if (pos == -1) {
            if (esLleno()) {
                throw new RuntimeException("Diccionario lleno");
            }
            pos = cantidad;
            clavesGuardadas[pos] = clave;
            cantidad++;
        }
        valoresGuardados[pos] = valor;
    }

    // obtener(d, clave) -> valor
    // pre: existeClave(d, clave)
    @Override
    public Object obtener(Object clave) {
        int pos = claveAIndice(clave);
        if (pos == -1) {
            throw new RuntimeException("obtener(): la clave no existe -> " + clave);
        }
        return valoresGuardados[pos];
    }

    // eliminar(d, clave) -> Diccionario
    // pre: existeClave(d, clave)
    // como no hay orden que preservar, el ultimo par tapa el hueco: O(1) una vez
    // encontrada la posicion
    @Override
    public void eliminar(Object clave) {
        int pos = claveAIndice(clave);
        if (pos == -1) {
            throw new RuntimeException("eliminar(): la clave no existe -> " + clave);
        }
        clavesGuardadas[pos] = clavesGuardadas[cantidad - 1];
        valoresGuardados[pos] = valoresGuardados[cantidad - 1];
        clavesGuardadas[cantidad - 1] = null;
        valoresGuardados[cantidad - 1] = null;
        cantidad--;
    }

    // existeClave(d, clave) -> boolean
    @Override
    public boolean existeClave(Object clave) {
        return claveAIndice(clave) != -1;
    }

    // esVacio(d) -> boolean
    @Override
    public boolean esVacio() {
        return cantidad == 0;
    }

    // cantidadClaves(d) -> entero
    @Override
    public int cantidadClaves() {
        return cantidad;
    }

    // claves(d) -> Lista
    // post: devuelve las claves en una lista, en el orden en que quedaron en el arreglo
    @Override
    public ListaDinamica claves() {
        ListaDinamica resultado = new ListaDinamica();
        for (int i = 0; i < cantidad; i++) {
            resultado.agregar(clavesGuardadas[i]);
        }
        return resultado;
    }

    // esLleno(d) -> boolean
    @Override
    public boolean esLleno() {
        return cantidad == capacidad;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{ ");
        for (int i = 0; i < cantidad; i++) {
            sb.append(clavesGuardadas[i]).append("=").append(valoresGuardados[i]).append(" ");
        }
        sb.append("}");
        return sb.toString();
    }
}
