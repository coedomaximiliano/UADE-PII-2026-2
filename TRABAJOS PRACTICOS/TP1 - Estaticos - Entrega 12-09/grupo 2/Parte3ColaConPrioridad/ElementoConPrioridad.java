public class ElementoConPrioridad<T> {
    private final T elemento;
    private final int prioridad;

    public ElementoConPrioridad(T elemento, int prioridad) {
        this.elemento = elemento;
        this.prioridad = prioridad;
    }

    public T getElemento() {
        return elemento;
    }

    public int getPrioridad() {
        return prioridad;
    }
}
