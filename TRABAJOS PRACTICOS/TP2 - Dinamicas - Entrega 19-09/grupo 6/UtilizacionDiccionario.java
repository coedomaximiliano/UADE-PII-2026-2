public class UtilizacionDiccionario {
    public static Diccionario combinarDiccionarios(Diccionario d1, Diccionario d2) {
        Diccionario resultado = new DiccionarioDinamico();

        Lista claves1 = d1.claves();
        for (int i = 0; i < claves1.tamanio(); i++) {
            Object clave = claves1.obtener(i);
            resultado.definir(clave, d1.obtener(clave));
        }

        Lista claves2 = d2.claves();
        for (int i = 0; i < claves2.tamanio(); i++) {
            Object clave = claves2.obtener(i);
            resultado.definir(clave, d2.obtener(clave));
        }
        return resultado;
    }

    public static Diccionario invertir(Diccionario d) {
        Diccionario resultado = new DiccionarioDinamico();
        Lista claves = d.claves();
        for (int i = 0; i < claves.tamanio(); i++) {
            Object clave = claves.obtener(i);
            resultado.definir(d.obtener(clave), clave);
        }
        return resultado;
    }

    public static int contarValoresMayoresA(Diccionario d, int umbral) {
        int contador = 0;
        Lista claves = d.claves();
        for (int i = 0; i < claves.tamanio(); i++) {
            int valor = (Integer) d.obtener(claves.obtener(i));
            if (valor > umbral) {
                contador++;
            }
        }
        return contador;
    }

    public static Lista clavesOrdenadas(Diccionario d) {
        Lista claves = d.claves();
        int n = claves.tamanio();

        String[] aux = new String[n];
        for (int i = 0; i < n; i++) {
            aux[i] = (String) claves.obtener(i);
        }

        for (int i = 0; i < n - 1; i++) {
            int idxMin = i;
            for (int j = i + 1; j < n; j++) {
                if (aux[j].compareTo(aux[idxMin]) < 0) {
                    idxMin = j;
                }
            }
            String temp = aux[i];
            aux[i] = aux[idxMin];
            aux[idxMin] = temp;
        }

        Lista resultado = new Lista();
        for (int i = 0; i < n; i++) {
            resultado.agregar(aux[i]);
        }
        return resultado;
    }

    public static void main(String[] args) {
        probar(new DiccionarioEstatico(10), new DiccionarioEstatico(10), "Estatico");
        probar(new DiccionarioDinamico(), new DiccionarioDinamico(), "Dinamico");
    }

    private static void probar(Diccionario edades, Diccionario otras, String nombre) {
        System.out.println("=== " + nombre + " ===");
        edades.definir("juan", 30);
        edades.definir("maria", 25);
        edades.definir("pedro", 40);
        otras.definir("maria", 26);
        otras.definir("ana", 19);

        System.out.println("d1 = " + edades + " | d2 = " + otras);

        Diccionario combinado = combinarDiccionarios(edades, otras);
        System.out.println("combinarDiccionarios(d1, d2) = " + combinado
                + " (cantidad = " + combinado.cantidadClaves() + ")");
        System.out.println("obtener(\"maria\") en combinado = " + combinado.obtener("maria"));

        Diccionario invertido = invertir(edades);
        System.out.println("invertir(d1) = " + invertido);
        System.out.println("obtener(40) en invertido = " + invertido.obtener(40));

        System.out.println("contarValoresMayoresA(d1, 28) = " + contarValoresMayoresA(edades, 28));
        System.out.println("contarValoresMayoresA(d1, 40) = " + contarValoresMayoresA(edades, 40));

        System.out.println("clavesOrdenadas(combinado) = " + clavesOrdenadas(combinado));

        System.out.println("d1 sin cambios = " + edades + " | d2 sin cambios = " + otras);
        System.out.println();
    }
}
