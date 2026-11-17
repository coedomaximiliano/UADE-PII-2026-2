import java.util.ArrayList;

public class UtilizacionColaConPrioridad {

    public static <T> ColaConPrioridad<T> combinar(ColaConPrioridad<T> cp1,
                                                     ColaConPrioridad<T> cp2) {
        ArrayList<ElementoConPrioridad<T>> extraidosCp1 = new ArrayList<>();
        while (!cp1.esVacia()) {
            extraidosCp1.add(cp1.extraerMax());
        }
        ArrayList<ElementoConPrioridad<T>> extraidosCp2 = new ArrayList<>();
        while (!cp2.esVacia()) {
            extraidosCp2.add(cp2.extraerMax());
        }

        int capacidad = extraidosCp1.size() + extraidosCp2.size();
        ColaConPrioridad<T> resultado = new ColaConPrioridadOrdenada<>(capacidad);

        for (ElementoConPrioridad<T> par : extraidosCp1) {
            resultado.insertar(par.getElemento(), par.getPrioridad());
        }
        for (ElementoConPrioridad<T> par : extraidosCp2) {
            resultado.insertar(par.getElemento(), par.getPrioridad());
        }
        return resultado;
    }

    public static <T> Cola<T> invertirColaConColaPrioridad(Cola<T> c) {
        ArrayList<T> buffer = new ArrayList<>();
        while (!c.esVacia()) {
            buffer.add(c.desencolar());
        }
        ColaConPrioridad<T> auxiliar = new ColaConPrioridadOrdenada<>(buffer.size());
        for (int i = 0; i < buffer.size(); i++) {
            auxiliar.insertar(buffer.get(i), i);
        }

        Cola<T> resultado = new ColaEstaticaGenerica<>(buffer.size());
        while (!auxiliar.esVacia()) {
            resultado.encolar(auxiliar.extraerMax().getElemento());
        }
        return resultado;
    }

    public static int sumarValoresPrioridadPar(ColaConPrioridad<Integer> cp) {
        ArrayList<ElementoConPrioridad<Integer>> extraidos = new ArrayList<>();
        int suma = 0;

        while (!cp.esVacia()) {
            ElementoConPrioridad<Integer> par = cp.extraerMax();
            if (par.getPrioridad() % 2 == 0) {
                suma += par.getElemento();
            }
            extraidos.add(par);
        }

        for (ElementoConPrioridad<Integer> par : extraidos) {
            cp.insertar(par.getElemento(), par.getPrioridad());
        }
        return suma;
    }
}
