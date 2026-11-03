public class NodoDiccionario {
    Object clave;
    Object valor;
    NodoDiccionario siguiente;

    public NodoDiccionario(Object clave, Object valor) {
        this.clave = clave;
        this.valor = valor;
        this.siguiente = null;
    }
}
