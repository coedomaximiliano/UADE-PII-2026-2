import Implementaciones.DiccionarioDinamico;
import Implementaciones.ListaDinamica;
import TDAs.DiccionarioTDA;
import TDAs.ListaTDA;

public class Metodos {
    // # Aclaracion: Todos los metodos funcionan cualquiera de las dos implementaciones del DiccionarioTDA

    public static DiccionarioTDA combinarDiccionarios(DiccionarioTDA d1, DiccionarioTDA d2) {
        DiccionarioTDA nuevoDiccionario = new DiccionarioDinamico();

        // 1. Copia todas las claves y valores de d1.
        ListaTDA claves1 = d1.claves();
        while (!claves1.esVacia()) {
            Object clave = claves1.elegir();
            nuevoDiccionario.definir(clave, d1.obtener(clave));
            claves1.eliminar(clave);
        }

        // 2. Copia las claves y valores de d2.
        // Si la clave ya existía, definir la reemplaza por la de d2.
        ListaTDA claves2 = d2.claves();
        while (!claves2.esVacia()) {
            Object clave = claves2.elegir();
            nuevoDiccionario.definir(clave, d2.obtener(clave));
            claves2.eliminar(clave);
        }

        return nuevoDiccionario;
    }

    public static DiccionarioTDA invertir(DiccionarioTDA d) {
        DiccionarioTDA resultado = new DiccionarioDinamico();

        // 1. Elige una clave cualquiera y obtiene su valor.
        ListaTDA claves = d.claves();
        while (!claves.esVacia()) {
            Object clave = claves.elegir();
            Object valor = d.obtener(clave);

            // 2. El valor original pasa a ser clave, y la clave pasa a ser valor.
            resultado.definir(valor, clave);

            claves.eliminar(clave);
        }

        return resultado;
    }

    public static int contarValoresMayoresA(DiccionarioTDA d, int umbral) {
        int contador = 0;
        ListaTDA claves = d.claves();

        // 1. Elige una clave cualquiera y obtiene su valor.
        while (!claves.esVacia()) {
            Object clave = claves.elegir();
            Object valor = d.obtener(clave);

            // 2. Convierte el valor a numérico (Integer/Number).
            if (valor instanceof Number) {
                if (((Number) valor).doubleValue() > umbral) {
                    contador++;
                }
            }
            claves.eliminar(clave);
        }

        return contador;
    }

    public static ListaTDA clavesOrdenadas(DiccionarioTDA d) {
        ListaTDA nuevaLista = new ListaDinamica();
        ListaTDA pendientes = d.claves();

        while (!pendientes.esVacia()) {
            String menor = null;
            ListaTDA aux = new ListaDinamica();

            // 1. Recorre las claves pendientes para encontrar la menor.
            while (!pendientes.esVacia()) {
                String claveActual = (String) pendientes.elegir();
                pendientes.eliminar(claveActual);
                aux.agregar(claveActual);

                if (menor == null || claveActual.compareTo(menor) < 0) {
                    menor = claveActual;
                }
            }

            // 2. Agrega menor a la lista.
            nuevaLista.agregar(menor);

            // 3. La saca de la lista auxiliar y restaura 'pendientes' sin el elemento extraído.
            aux.eliminar(menor);
            pendientes = aux;
        }

        return nuevaLista;
    }
}
