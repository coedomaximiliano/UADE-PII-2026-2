class VectorEstatico {

    private int[] datos;
    private int cantidad;
    private int capacidad;

    public VectorEstatico(int capacidad) {
        this.capacidad = capacidad;
        this.datos = new int[capacidad];
        this.cantidad = 0;
    }

    public boolean agregar(int valor) {
        if (cantidad == capacidad) {
            return false;
        }

        datos[cantidad] = valor;
        cantidad++;
        return true;
    }

    public void mostrar() {
        for (int i = 0; i < cantidad; i++) {
            System.out.println(datos[i]);
        }
    }

    public int obtenerCantidad() {
        return cantidad;
    }
}

public class Main {

    public static void main(String[] args) {
        VectorEstatico algo = new VectorEstatico(2);

        algo.agregar(10);
        algo.agregar(20);

        boolean agregado = algo.agregar(30);

        algo.mostrar();

        System.out.println("Cantidad: " + algo.obtenerCantidad());
        System.out.println("¿Se agregó el 30?: " + agregado);
    }
}