// TP2 - Algoritmos y Estructuras de Datos II
// Pedro Stella - Bautista Chavarri

/**
 * TDA Diccionario: coleccion de pares (clave, valor) donde cada clave es unica y
 * aparece asociada a lo sumo a un valor. Esta interfaz es el CONTRATO del TDA: que
 * operaciones hay y que garantizan (pre/post), sin decir como se implementan.
 * Las dos implementaciones del TP (DiccionarioDinamico y DiccionarioEstatico) la
 * respetan, por eso los metodos de la Parte 4 funcionan igual con cualquiera de las dos.
 * crear() -> Diccionario no esta aca: en Java es el constructor de cada implementacion.
 */
public interface Diccionario {

    // definir(d: Diccionario, clave, valor) -> Diccionario
    // pre:  d no esta lleno, o clave ya pertenece a d
    // post: valor queda asociado a clave en d; si la clave ya existia se actualiza su
    //       valor y la cantidad de claves no cambia
    void definir(Object clave, Object valor);

    // obtener(d: Diccionario, clave) -> valor
    // pre:  existeClave(d, clave)
    // post: devuelve el valor asociado a clave; d no se modifica
    Object obtener(Object clave);

    // eliminar(d: Diccionario, clave) -> Diccionario
    // pre:  existeClave(d, clave)
    // post: clave y su valor ya no pertenecen a d
    void eliminar(Object clave);

    // existeClave(d: Diccionario, clave) -> boolean
    // post: devuelve true si clave pertenece a d; d no se modifica
    boolean existeClave(Object clave);

    // esVacio(d: Diccionario) -> boolean
    // post: devuelve true si d no tiene ninguna clave
    boolean esVacio();

    // cantidadClaves(d: Diccionario) -> entero
    // post: devuelve cuantas claves tiene d
    int cantidadClaves();

    // claves(d: Diccionario) -> Lista
    // post: devuelve una lista con todas las claves de d, sin ningun orden particular;
    //       d no se modifica
    ListaDinamica claves();

    // esLleno(d: Diccionario) -> boolean
    // post: devuelve true si d alcanzo su capacidad maxima
    // (auxiliar: la implementacion dinamica no tiene capacidad maxima y siempre da false)
    boolean esLleno();
}
