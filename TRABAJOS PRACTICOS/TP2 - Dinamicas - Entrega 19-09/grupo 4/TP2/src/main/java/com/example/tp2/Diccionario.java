package com.example.tp2;

public interface Diccionario {
    /**
     * Inicializa el diccionario.
     * @post El diccionario queda vacío.
     */
    void crear();

    /**
     * Agrega un par (clave, valor) al diccionario. Si la clave ya existe, actualiza su valor.
     * @param clave la clave a insertar o actualizar.
     * @param valor el valor asociado a la clave.
     * @post El diccionario contiene el par (clave, valor).
     */
    void definir(Object clave, Object valor);

    /**
     * Obtiene el valor asociado a una clave.
     * @param clave la clave a buscar.
     * @pre La clave debe existir en el diccionario.
     * @return El valor asociado a la clave.
     */
    Object obtener(Object clave);

    /**
     * Elimina el par clave-valor asociado a la clave dada.
     * @param clave la clave a eliminar.
     * @post Si la clave existía, el par es eliminado.
     */
    void eliminar(Object clave);

    /**
     * Verifica si una clave existe en el diccionario.
     * @param clave la clave a buscar.
     * @return true si la clave existe, false en caso contrario.
     */
    boolean existeClave(Object clave);

    /**
     * Verifica si el diccionario está vacío.
     * @return true si no tiene elementos, false en caso contrario.
     */
    boolean esVacio();

    /**
     * Devuelve la cantidad de claves (elementos) en el diccionario.
     * @return el número de claves.
     */
    int cantidadClaves();

    /**
     * Devuelve una lista con todas las claves del diccionario.
     * @return una Lista (sin orden particular) con las claves.
     * @post El diccionario no se modifica.
     */
    Lista claves();
}
