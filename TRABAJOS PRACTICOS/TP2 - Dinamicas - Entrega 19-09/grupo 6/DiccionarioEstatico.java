public class DiccionarioEstatico implements Diccionario {
    private Object[] claves;
    private Object[] valores;
    private int cantidad;
    private int capacidad;

    public DiccionarioEstatico(int capacidad) {
        this.capacidad = capacidad;
        this.claves = new Object[capacidad];
        this.valores = new Object[capacidad];
        this.cantidad = 0;
    }

    private int indiceDe(Object clave) {
        for (int i = 0; i < cantidad; i++) {
            if (claves[i].equals(clave)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void definir(Object clave, Object valor) {
        int pos = indiceDe(clave);
        if (pos != -1) {
            valores[pos] = valor;
        } else {
            if (cantidad == capacidad) {
                throw new RuntimeException("definir(): diccionario lleno");
            }
            claves[cantidad] = clave;
            valores[cantidad] = valor;
            cantidad++;
        }
    }

    @Override
    public Object obtener(Object clave) {
        int pos = indiceDe(clave);
        if (pos == -1) {
            throw new RuntimeException("obtener(): la clave no existe -> " + clave);
        }
        return valores[pos];
    }

    @Override
    public void eliminar(Object clave) {
        int pos = indiceDe(clave);
        if (pos == -1) {
            throw new RuntimeException("eliminar(): la clave no existe -> " + clave);
        }
        claves[pos] = claves[cantidad - 1];
        valores[pos] = valores[cantidad - 1];
        claves[cantidad - 1] = null;
        valores[cantidad - 1] = null;
        cantidad--;
    }

    @Override
    public boolean existeClave(Object clave) {
        return indiceDe(clave) != -1;
    }

    @Override
    public boolean esVacio() {
        return cantidad == 0;
    }

    @Override
    public int cantidadClaves() {
        return cantidad;
    }

    @Override
    public Lista claves() {
        Lista resultado = new Lista();
        for (int i = 0; i < cantidad; i++) {
            resultado.agregar(claves[i]);
        }
        return resultado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{ ");
        for (int i = 0; i < cantidad; i++) {
            sb.append(claves[i]).append("=").append(valores[i]).append(" ");
        }
        sb.append("}");
        return sb.toString();
    }
}
