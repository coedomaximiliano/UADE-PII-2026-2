public class ItemPrioridad {
    public int valor;
    public int prioridad;

    public ItemPrioridad(int valor, int prioridad) {
        this.valor = valor;
        this.prioridad = prioridad;
    }

    @Override
    public String toString() {
        return "(" + valor + ", prioridad=" + prioridad + ")";
    }
}
