
public class EjerciciosCola {


    public static ColaCircular pasarCola(ColaCircular origen, int capacidadDestino) {
        ColaCircular destino = new ColaCircular(capacidadDestino);
        while (!origen.esVacia()) {
            int x = origen.frente();
            origen.desencolar();
            destino.encolar(x);
        }
        return destino;
    }

   
    public static ColaCircular invertirColaConPila(ColaCircular c, int capacidad) {
        PilaEstatica p = new PilaEstatica(capacidad);
        ColaCircular nueva = new ColaCircular(capacidad);

        while (!c.esVacia()) {
            int x = c.frente();
            c.desencolar();
            p.apilar(x);
        }
        while (!p.esVacia()) {
            int x = p.tope();
            p.desapilar();
            nueva.encolar(x);
        }
        return nueva;
    }

    public static ColaCircular invertirColaSinPila(ColaCircular c, int capacidad) {
        if (c.esVacia()) {
            return new ColaCircular(capacidad);
        }
        int x = c.frente();
        c.desencolar();

        ColaCircular resto = invertirColaSinPila(c, capacidad);
        resto.encolar(x);   // x era el primero -> ahora va al final

        return resto;
    }

    private static class ResultadoUltimosK {
        ColaCircular ultimosK;
        int n;
        ResultadoUltimosK(ColaCircular ultimosK, int n) {
            this.ultimosK = ultimosK;
            this.n = n;
        }
    }

    private static ResultadoUltimosK ultimosKYRestaurar(ColaCircular c, int k, int capacidad) {
        ColaCircular copia = new ColaCircular(capacidad);
        int n = 0;

        while (!c.esVacia()) {
            int x = c.frente();
            c.desencolar();
            copia.encolar(x);
            n++;
        }

        ColaCircular ultimosK = new ColaCircular(capacidad);
        int i = 0;
        while (!copia.esVacia()) {
            int x = copia.frente();
            copia.desencolar();
            c.encolar(x);        // restaura c en su orden original
            i++;
            if (i > n - k) {
                ultimosK.encolar(x);
            }
        }

        return new ResultadoUltimosK(ultimosK, n);
    }

    public static boolean finalCoincide(ColaCircular c1, ColaCircular c2, int k, int capacidad) {
        if (k == 0) {
            return true;   // los "ultimos 0 elementos" coinciden vacuamente
        }
        if (k < 0) {
            return false;
        }

        ResultadoUltimosK r1 = ultimosKYRestaurar(c1, k, capacidad);
        ResultadoUltimosK r2 = ultimosKYRestaurar(c2, k, capacidad);

        if (k > r1.n || k > r2.n) {
            return false;   // no hay k elementos para comparar
        }

        boolean iguales = true;
        while (!r1.ultimosK.esVacia()) {
            int x1 = r1.ultimosK.desencolar();
            int x2 = r2.ultimosK.desencolar();
            if (x1 != x2) {
                iguales = false;
            }
        }
        return iguales;
    }

    public static void main(String[] args) {
        // 6. pasarCola
        ColaCircular origen = new ColaCircular(5);
        for (int i = 1; i <= 4; i++) origen.encolar(i);
        ColaCircular destino = pasarCola(origen, 5);
        System.out.print("pasarCola -> destino: ");
        while (!destino.esVacia()) System.out.print(destino.desencolar() + " ");
        System.out.println("| origen.esVacia() = " + origen.esVacia());

        // 7. invertirColaConPila
        ColaCircular c1 = new ColaCircular(5);
        for (int i = 1; i <= 4; i++) c1.encolar(i);
        ColaCircular invertidaConPila = invertirColaConPila(c1, 5);
        System.out.print("invertirColaConPila([1,2,3,4]) -> ");
        while (!invertidaConPila.esVacia()) System.out.print(invertidaConPila.desencolar() + " ");
        System.out.println();

        // 8. invertirColaSinPila
        ColaCircular c2 = new ColaCircular(5);
        for (int i = 1; i <= 4; i++) c2.encolar(i);
        ColaCircular invertidaSinPila = invertirColaSinPila(c2, 5);
        System.out.print("invertirColaSinPila([1,2,3,4]) -> ");
        while (!invertidaSinPila.esVacia()) System.out.print(invertidaSinPila.desencolar() + " ");
        System.out.println();

        // 9. finalCoincide
        ColaCircular a = new ColaCircular(10);
        ColaCircular b = new ColaCircular(10);
        int[] valoresA = {9, 1, 2, 3, 4};
        int[] valoresB = {7, 7, 2, 3, 4};
        for (int x : valoresA) a.encolar(x);
        for (int x : valoresB) b.encolar(x);

        boolean resultado = finalCoincide(a, b, 3, 10);
        System.out.println("finalCoincide([9,1,2,3,4], [7,7,2,3,4], k=3) -> " + resultado + " (esperado: true)");

        // se verifica que a y b quedaron intactas
        System.out.print("a restaurada -> ");
        while (!a.esVacia()) System.out.print(a.desencolar() + " ");
        System.out.println();
        System.out.print("b restaurada -> ");
        while (!b.esVacia()) System.out.print(b.desencolar() + " ");
        System.out.println();
    }
}
