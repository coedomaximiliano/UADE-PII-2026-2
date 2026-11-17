package TDAs;

public abstract class DiccionarioTDA {
    // Dominio:
    // Diccionario = Conjunto de pares clave-valor donde cada clave es única y está asociada, a lo sumo, a un valor.

    // Operaciones:
    // crear(); --> Lo hace el constructor.
    // post: devuelve un diccionario vacío.

    public abstract void definir(Object clave, Object valor);
    // post: si existeClave(d, clave), se reemplaza el valor asociado a clave por valor; si no, se agrega el par (clave, valor) a d.

    public abstract Object obtener(Object clave);
    // pre: existeClave(d, clave).
    // post: devuelve el valor asociado a clave en d.

    public abstract void eliminar(Object clave);
    // pre: existeClave(d, clave)
    // post: se quita de d el par cuya clave es clave; el resto de los pares de d no se modifica.

    public abstract boolean existeClave(Object clave);
    // post: devuelve true si y sólo si hay un par en d cuya clave es clave.

    public abstract boolean esVacio();
    // post: devuelve true si y sólo si cantidadClaves(d) = 0.

    public abstract int cantidadClaves();
    // post: devuelve la cantidad de pares clave-valor que tiene d.

    public abstract ListaTDA claves();
    // post: devuelve una lista (sin ningún orden particular) que contiene exactamente una vez cada clave de d; d no se modifica.
}
