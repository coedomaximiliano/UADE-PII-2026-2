package com.example.tp4;

/**
 * TDA Diccionario Simple (reutilizado de TP2).
 * Almacena pares (clave -> valor) sin claves duplicadas.
 * Implementado mediante tabla de dispersión (hash) abierta con encadenamiento en nodos propios
 * y redimensionamiento dinámico, sin utilizar java.util.HashMap ni colecciones estándar.
 *
 * @param <K> Tipo de la clave.
 * @param <V> Tipo del valor asociado.
 */
public class Diccionario<K, V> {

    private static class Entrada<K, V> {
        private final K clave;
        private V valor;
        private Entrada<K, V> siguiente;

        private Entrada(K clave, V valor, Entrada<K, V> siguiente) {
            this.clave = clave;
            this.valor = valor;
            this.siguiente = siguiente;
        }
    }

    private static final int CAPACIDAD_INICIAL = 16;
    private static final double FACTOR_CARGA_MAXIMO = 0.75;

    private Entrada<K, V>[] tabla;
    private int cantidad;

    /**
     * Crea un diccionario vacío.
     * Complejidad temporal: O(1).
     */
    @SuppressWarnings("unchecked")
    public Diccionario() {
        this.tabla = (Entrada<K, V>[]) new Entrada[CAPACIDAD_INICIAL];
        this.cantidad = 0;
    }

    /**
     * Operación constructora según especificación algebraica: crear(): Diccionario.
     * Complejidad temporal: O(1).
     */
    public static <K, V> Diccionario<K, V> crear() {
        return new Diccionario<>();
    }

    private int indiceBucket(K clave, int longitudTabla) {
        return (clave.hashCode() & 0x7fffffff) % longitudTabla;
    }

    /**
     * Asocia la clave con el valor indicado. Si la clave ya existe, actualiza su valor.
     * Precondición: clave != null.
     * Postcondición: la clave queda asociada al valor en el diccionario.
     * Complejidad temporal: O(1) promedio.
     */
    public void agregar(K clave, V valor) {
        if (clave == null) {
            throw new IllegalArgumentException("La clave del diccionario no puede ser null.");
        }
        if ((double) (this.cantidad + 1) / this.tabla.length > FACTOR_CARGA_MAXIMO) {
            redimensionar();
        }
        int indice = indiceBucket(clave, this.tabla.length);
        Entrada<K, V> actual = this.tabla[indice];
        while (actual != null) {
            if (actual.clave.equals(clave)) {
                actual.valor = valor;
                return;
            }
            actual = actual.siguiente;
        }
        this.tabla[indice] = new Entrada<>(clave, valor, this.tabla[indice]);
        this.cantidad++;
    }

    /**
     * Obtiene el valor asociado a la clave.
     * Precondición: la clave debe existir en el diccionario (existeClave(clave)).
     * Postcondición: devuelve el valor asociado a la clave sin modificar el diccionario.
     * Complejidad temporal: O(1) promedio.
     */
    public V obtener(K clave) {
        if (clave == null) {
            throw new IllegalArgumentException("La clave no puede ser null.");
        }
        int indice = indiceBucket(clave, this.tabla.length);
        Entrada<K, V> actual = this.tabla[indice];
        while (actual != null) {
            if (actual.clave.equals(clave)) {
                return actual.valor;
            }
            actual = actual.siguiente;
        }
        throw new IllegalArgumentException("La clave '" + clave + "' no existe en el diccionario.");
    }

    /**
     * Indica si la clave pertenece al diccionario.
     * Precondición: clave != null.
     * Postcondición: retorna true si la clave está registrada, false en caso contrario.
     * Complejidad temporal: O(1) promedio.
     */
    public boolean existeClave(K clave) {
        if (clave == null) {
            return false;
        }
        int indice = indiceBucket(clave, this.tabla.length);
        Entrada<K, V> actual = this.tabla[indice];
        while (actual != null) {
            if (actual.clave.equals(clave)) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    /**
     * Elimina la clave y su valor asociado del diccionario (si existe).
     * Complejidad temporal: O(1) promedio.
     */
    public void eliminar(K clave) {
        if (clave == null) {
            return;
        }
        int indice = indiceBucket(clave, this.tabla.length);
        Entrada<K, V> actual = this.tabla[indice];
        Entrada<K, V> anterior = null;
        while (actual != null) {
            if (actual.clave.equals(clave)) {
                if (anterior == null) {
                    this.tabla[indice] = actual.siguiente;
                } else {
                    anterior.siguiente = actual.siguiente;
                }
                this.cantidad--;
                return;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
    }

    /**
     * Devuelve la cantidad de pares (clave, valor) almacenados.
     * Complejidad temporal: O(1).
     */
    public int cantidadElementos() {
        return this.cantidad;
    }

    /**
     * Indica si el diccionario está vacío.
     * Complejidad temporal: O(1).
     */
    public boolean estaVacio() {
        return this.cantidad == 0;
    }

    @SuppressWarnings("unchecked")
    private void redimensionar() {
        int nuevaCapacidad = this.tabla.length * 2;
        Entrada<K, V>[] nuevaTabla = (Entrada<K, V>[]) new Entrada[nuevaCapacidad];
        for (int i = 0; i < this.tabla.length; i++) {
            Entrada<K, V> actual = this.tabla[i];
            while (actual != null) {
                Entrada<K, V> siguiente = actual.siguiente;
                int nuevoIndice = indiceBucket(actual.clave, nuevaCapacidad);
                actual.siguiente = nuevaTabla[nuevoIndice];
                nuevaTabla[nuevoIndice] = actual;
                actual = siguiente;
            }
        }
        this.tabla = nuevaTabla;
    }
}
