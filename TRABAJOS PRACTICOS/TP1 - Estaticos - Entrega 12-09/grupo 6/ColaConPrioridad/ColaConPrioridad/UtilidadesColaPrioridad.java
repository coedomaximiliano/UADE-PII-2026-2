public class UtilidadesColaPrioridad {

    private UtilidadesColaPrioridad() {
    }

    private static ColaPrioridad crear() {
        return new ColaPrioridadVarianteA();
    }

    public static ColaPrioridad combinar(ColaPrioridad cp1, ColaPrioridad cp2) {
        ColaPrioridad resultado = crear();

        while (!cp1.esVacia()) {
            ItemPrioridad item = cp1.extraerMax();
            resultado.insertar(item.valor, item.prioridad);
        }

        while (!cp2.esVacia()) {
            ItemPrioridad item = cp2.extraerMax();
            resultado.insertar(item.valor, item.prioridad);
        }

        return resultado;
    }

    public static Cola invertirColaConColaPrioridad(Cola c) {
        ColaPrioridad auxiliar = crear();
        Cola invertida = new Cola();
        int prioridad = 0;

        while (!c.esVacia()) {
            auxiliar.insertar(c.desacolar(), prioridad);
            prioridad++;
        }

        while (!auxiliar.esVacia()) {
            invertida.acolar(auxiliar.extraerMax().valor);
        }

        return invertida;
    }

    public static int sumarValoresPrioridadPar(ColaPrioridad cp) {
        ColaPrioridad auxiliar = crear();
        int suma = 0;

        while (!cp.esVacia()) {
            ItemPrioridad item = cp.extraerMax();
            if (item.prioridad % 2 == 0) {
                suma += item.valor;
            }
            auxiliar.insertar(item.valor, item.prioridad);
        }

        while (!auxiliar.esVacia()) {
            ItemPrioridad item = auxiliar.extraerMax();
            cp.insertar(item.valor, item.prioridad);
        }

        return suma;
    }
}
