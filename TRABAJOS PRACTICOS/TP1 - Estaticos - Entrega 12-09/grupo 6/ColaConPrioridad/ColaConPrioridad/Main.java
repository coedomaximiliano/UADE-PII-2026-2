public class Main {
    public static void main(String[] args) {
        ColaPrioridad cp1 = new ColaPrioridadVarianteA();
        cp1.insertar(10, 2);
        cp1.insertar(20, 5);
        cp1.insertar(30, 5);

        ColaPrioridad cp2 = new ColaPrioridadVarianteB();
        cp2.insertar(40, 4);
        cp2.insertar(50, 5);

        ColaPrioridad combinada = UtilidadesColaPrioridad.combinar(cp1, cp2);
        System.out.println("Cola combinada:");
        imprimirYVaciar(combinada);

        Cola cola = new Cola();
        cola.acolar(1);
        cola.acolar(2);
        cola.acolar(3);
        Cola invertida = UtilidadesColaPrioridad.invertirColaConColaPrioridad(cola);
        System.out.println("Cola invertida: " + invertida);

        ColaPrioridad cp3 = new ColaPrioridadVarianteB();
        cp3.insertar(7, 1);
        cp3.insertar(8, 2);
        cp3.insertar(9, 4);
        cp3.insertar(10, 5);
        int suma = UtilidadesColaPrioridad.sumarValoresPrioridadPar(cp3);
        System.out.println("Suma con prioridad par: " + suma);
        System.out.println("cp3 luego de sumar:");
        imprimirYVaciar(cp3);
    }

    private static void imprimirYVaciar(ColaPrioridad cp) {
        while (!cp.esVacia()) {
            System.out.println(cp.extraerMax());
        }
    }
}
