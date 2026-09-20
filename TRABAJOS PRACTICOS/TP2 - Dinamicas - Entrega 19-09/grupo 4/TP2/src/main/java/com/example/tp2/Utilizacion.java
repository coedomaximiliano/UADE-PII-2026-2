package com.example.tp2;

public class Utilizacion {

    /**
     * 7. combinarDiccionarios(d1, d2)
     */
    public static Diccionario combinarDiccionarios(Diccionario d1, Diccionario d2) {
        Diccionario nuevo = new DiccionarioDinamico(); // puede ser estatico también, el tipo concreto no importa
        nuevo.crear();

        Lista claves1 = d1.claves();
        for (int i = 0; i < claves1.longitud(); i++) {
            Object clave = claves1.obtener(i);
            nuevo.definir(clave, d1.obtener(clave));
        }

        Lista claves2 = d2.claves();
        for (int i = 0; i < claves2.longitud(); i++) {
            Object clave = claves2.obtener(i);
            nuevo.definir(clave, d2.obtener(clave)); // Sobreescribe si ya existe, gana d2
        }

        return nuevo;
    }

    /**
     * 8. invertir(d)
     */
    public static Diccionario invertir(Diccionario d) {
        Diccionario nuevo = new DiccionarioDinamico();
        nuevo.crear();

        Lista claves = d.claves();
        for (int i = 0; i < claves.longitud(); i++) {
            Object clave = claves.obtener(i);
            Object valor = d.obtener(clave);
            nuevo.definir(valor, clave);
        }

        return nuevo;
    }

    /**
     * 9. contarValoresMayoresA(d, umbral)
     */
    public static int contarValoresMayoresA(Diccionario d, int umbral) {
        int contador = 0;
        Lista claves = d.claves();
        for (int i = 0; i < claves.longitud(); i++) {
            Object clave = claves.obtener(i);
            Object valor = d.obtener(clave);
            if (valor instanceof Integer && (Integer) valor > umbral) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * 10. clavesOrdenadas(d)
     */
    public static Lista clavesOrdenadas(Diccionario d) {
        Lista claves = d.claves();
        Lista dinamica = new ListaDinamica();
        dinamica.crear();

        for (int i = 0; i < claves.longitud(); i++) {
            dinamica.agregar(claves.obtener(i));
        }

        // Ordenamiento por selección (Selection Sort)
        int n = dinamica.longitud();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                String str1 = (String) dinamica.obtener(j);
                String strMin = (String) dinamica.obtener(minIdx);
                if (str1.compareTo(strMin) < 0) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                Object temp = dinamica.obtener(i);
                Object min = dinamica.obtener(minIdx);
                
                // Intercambio sin métodos especiales
                dinamica.eliminar(i);
                dinamica.insertar(min, i);
                
                dinamica.eliminar(minIdx);
                dinamica.insertar(temp, minIdx);
            }
        }

        return dinamica;
    }
}
