/*
 * BLOQUE 3 (ejemplo extra): INTERFACES
 * -----------------------------------------
 * El ejemplo "de manual" para interfaces: figuras geométricas. Es útil
 * porque el concepto de "misma operación, cálculo distinto según la
 * figura" se entiende de un vistazo, y ayuda a separar la idea de
 * interfaz de la de "cuentas bancarias" del Ejemplo3.
 */
public class Ejemplo3b_Interfaces {

    public static void main(String[] args) {
        Figura circulo = new Circulo(5.0);
        Figura rectangulo = new Rectangulo(4.0, 6.0);

        // El método no sabe (ni le importa) qué figura concreta recibe.
        imprimirDatos(circulo);
        imprimirDatos(rectangulo);

        // También podemos guardarlas juntas en un arreglo de la interfaz:
        Figura[] figuras = { circulo, rectangulo, new Rectangulo(2.0, 2.0) };
        double areaTotal = 0.0;
        for (Figura f : figuras) {
            areaTotal += f.area();
        }
        System.out.println("Área total de las " + figuras.length + " figuras: " + areaTotal);
    }

    static void imprimirDatos(Figura f) {
        System.out.println("Área: " + f.area() + " | Perímetro: " + f.perimetro());
    }
}

// ---- La especificación ----
interface Figura {
    double area();
    double perimetro();
}

// ---- Implementación 1 ----
class Circulo implements Figura {
    private double radio;

    public Circulo(double radio) {
        this.radio = radio;
    }

    public double area() {
        return Math.PI * radio * radio;
    }

    public double perimetro() {
        return 2 * Math.PI * radio;
    }
}

// ---- Implementación 2: cálculo completamente distinto, misma interfaz ----
class Rectangulo implements Figura {
    private double base;
    private double altura;

    public Rectangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    public double area() {
        return base * altura;
    }

    public double perimetro() {
        return 2 * (base + altura);
    }
}
