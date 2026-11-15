public interface Diccionario {
    void definir(Object clave, Object valor);

    Object obtener(Object clave);

    void eliminar(Object clave);

    boolean existeClave(Object clave);

    boolean esVacio();

    int cantidadClaves();

    Lista claves();
}
