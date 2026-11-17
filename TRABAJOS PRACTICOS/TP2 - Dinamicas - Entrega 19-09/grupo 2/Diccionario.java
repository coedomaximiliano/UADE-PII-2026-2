/**
 * TDA Diccionario -- interfaz (el contrato del TDA).
 *
 * Define QUE operaciones ofrece el Diccionario, sin decir COMO se
 * implementan. Las dos implementaciones (DiccionarioDinamico con cadena
 * de nodos, y DiccionarioEstatico con arreglos) cumplen esta interfaz,
 * y por eso los metodos de la Parte 4 -- escritos contra Diccionario --
 * funcionan igual con cualquiera de las dos, sin tocar la estructura
 * interna.
 *
 * crear() es el constructor de cada implementacion, por eso no aparece
 * aca. El resto de las operaciones son las de la Parte 1.
 */
public interface Diccionario {

    // definir(d, clave, valor): si la clave no existia, agrega el par;
    // si ya existia, actualiza su valor (no duplica la clave).
    void definir(Object clave, Object valor);

    // obtener(d, clave): devuelve el valor asociado a clave.
    // pre: existeClave(d, clave)
    Object obtener(Object clave);

    // eliminar(d, clave): si la clave esta, elimina el par; si no, no cambia nada.
    void eliminar(Object clave);

    // existeClave(d, clave): true si clave pertenece a d.
    boolean existeClave(Object clave);

    // esVacio(d): true si d no tiene pares.
    boolean esVacio();

    // cantidadClaves(d): cantidad de pares que contiene d.
    int cantidadClaves();

    // claves(d): Lista con todas las claves de d, sin orden particular.
    //            No modifica d.
    Lista claves();
}
