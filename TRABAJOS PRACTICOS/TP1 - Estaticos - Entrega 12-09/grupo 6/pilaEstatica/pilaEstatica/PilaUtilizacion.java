

public class PilaUtilizacion {

    // Clase de utilidades (solo métodos estáticos): no se instancia.
    private PilaUtilizacion() {
    }

    // Único lugar donde se elige la variante: cambiando esta línea,
    // todos los métodos pasan a usar la otra implementación.
    private static Pila crear() {
        return new PilaVarianteA();
    }

    // Devuelve una nueva pila con todos los elementos de origen,
    // dejando origen vacía.
    public static Pila pasarPila(Pila origen) {
        Pila destino = crear();
        
        while (!origen.esVacia()) {
            destino.apilar(origen.desapilar());
        }
        
        return destino;
    }
    
    // Devuelve una copia de p, dejando p en su estado original
    // (mismo contenido y mismo orden).

    public static Pila copiarPila(Pila p) {
        Pila auxiliar = crear();
        Pila copia = crear();
        
        // Desapila todo de p hacia auxiliar
        while (!p.esVacia()) {
            auxiliar.apilar(p.desapilar());
        }
        
        // Desapila de auxiliar, apila en copia y en p (restaura p original)
        while (!auxiliar.esVacia()) {
            int x = auxiliar.desapilar();
            copia.apilar(x);
            p.apilar(x);
        }
        
        return copia;
    }
    
    // Devuelve una nueva pila con los elementos de p en orden inverso.

    public static Pila invertirPila(Pila p) {
        Pila resultado = crear();
        invertirPilaRecursivo(p, resultado);
        return resultado;
    }


    private static void invertirPilaRecursivo(Pila origen, Pila destino) {
    if (origen.esVacia()) {
        return;
    }
    
    int x = origen.desapilar();
    destino.apilar(x);
    invertirPilaRecursivo(origen, destino);
}
    
    // Indica si algún elemento aparece más de una vez en p.
    // Al finalizar, p debe quedar con su contenido y orden original.

    public static boolean masDeUnaOcurrencia(Pila p) {
        Pila auxiliar = crear();
        boolean hayDuplicado = false;
        
        // Mueve elementos de p a auxiliar, verificando duplicados
        while (!p.esVacia()) {
            int x = p.desapilar();
            
            if (contieneElemento(auxiliar, x)) {
                hayDuplicado = true;
            }
            
            auxiliar.apilar(x);
        }
        
        // Restaura p original
        while (!auxiliar.esVacia()) {
            p.apilar(auxiliar.desapilar());
        }
        
        return hayDuplicado;
    }
    
    private static boolean contieneElemento(Pila p, int x) {
        Pila temp = crear();
        boolean encontrado = false;
        
        while (!p.esVacia()) {
            int elem = p.desapilar();
            if (elem == x) {
                encontrado = true;
            }
            temp.apilar(elem);
        }
        
        // Restaura p
        while (!temp.esVacia()) {
            p.apilar(temp.desapilar());
        }
        
        return encontrado;
    }
    
    // Devuelve una nueva pila con los elementos de p que son pares,
    // en el mismo orden relativo en que estaban en p.

    public static Pila eliminarImpares(Pila p) {
        Pila auxiliar = crear();
        Pila pares = crear();
        
        // Desapila p hacia auxiliar (invierte orden)
        while (!p.esVacia()) {
            auxiliar.apilar(p.desapilar());
        }
        
        // Procesa auxiliar: extrae solo los pares en orden original
        while (!auxiliar.esVacia()) {
            int x = auxiliar.desapilar();
            p.apilar(x);  // Restaura p
            
            if (x % 2 == 0) {  // Es par
                pares.apilar(x);
            }
        }
        
        return pares;
    }
}
