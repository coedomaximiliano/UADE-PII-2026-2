package com.example.tp3;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Tp3ApplicationTests {
    private ArbolBinarioBusqueda ejemplo() {
        ArbolBinarioBusqueda a = ArbolBinarioBusqueda.crear();
        for (int x : new int[]{50, 30, 70, 20, 40, 60, 80, 10, 25, 65, 90}) a.insertar(x);
        return a;
    }

    private String salida(Runnable recorrido) {
        PrintStream original = System.out;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (PrintStream captura = new PrintStream(bytes)) {
            System.setOut(captura);
            recorrido.run();
        } finally {
            System.setOut(original);
        }
        return bytes.toString().trim();
    }

    @Test void arbolVacio() {
        ArbolBinarioBusqueda a = ArbolBinarioBusqueda.crear();
        assertTrue(a.esVacio());
        assertFalse(a.pertenece(1));
        assertEquals(0, a.cantidadNodos());
        assertEquals(0, UtilizacionArbol.altura(a));
        assertEquals(0, UtilizacionArbol.contarHojas(a));
        assertEquals(0L, UtilizacionArbol.sumaNodos(a));
        assertTrue(UtilizacionArbol.esABB(null));
        assertEquals("", salida(a::inorder));
        assertEquals("", salida(a::preorder));
        assertEquals("", salida(a::postorder));
        assertEquals("", salida(a::recorridoPorNiveles));
    }

    @Test void recorridosDelEnunciado() {
        ArbolBinarioBusqueda a = ejemplo();
        assertEquals("10 20 25 30 40 50 60 65 70 80 90", salida(a::inorder));
        assertEquals("50 30 20 10 25 40 70 60 65 80 90", salida(a::preorder));
        assertEquals("10 25 20 40 30 65 60 90 80 70 50", salida(a::postorder));
        assertEquals("50 30 70 20 40 60 80 10 25 65 90", salida(a::recorridoPorNiveles));
        assertEquals(11, a.cantidadNodos());
        assertEquals(4, UtilizacionArbol.altura(a));
        assertEquals(5, UtilizacionArbol.contarHojas(a));
        assertEquals(540L, UtilizacionArbol.sumaNodos(a));
        assertTrue(UtilizacionArbol.esABB(a.getRaiz()));
    }

    @Test void insercionBusquedaYDuplicados() {
        ArbolBinarioBusqueda a = ejemplo();
        NodoArbol raiz = a.getRaiz();
        for (int x : new int[]{50, 30, 70, 20, 40, 60, 80, 10, 25, 65, 90}) {
            assertTrue(a.pertenece(x));
            a.insertar(x);
        }
        assertSame(raiz, a.getRaiz());
        assertEquals(11, a.cantidadNodos());
        assertFalse(a.pertenece(0));
        assertFalse(a.pertenece(66));
        assertFalse(a.pertenece(100));
    }

    @Test void arbolesDegeneradosYHojaUnica() {
        ArbolBinarioBusqueda creciente = new ArbolBinarioBusqueda();
        ArbolBinarioBusqueda decreciente = new ArbolBinarioBusqueda();
        creciente.insertar(1);
        assertEquals(1, UtilizacionArbol.altura(creciente));
        assertEquals(1, UtilizacionArbol.contarHojas(creciente));
        for (int x = 2; x <= 100; x++) creciente.insertar(x);
        for (int x = 100; x >= 1; x--) decreciente.insertar(x);
        for (ArbolBinarioBusqueda a : new ArbolBinarioBusqueda[]{creciente, decreciente}) {
            assertEquals(100, UtilizacionArbol.altura(a));
            assertEquals(1, UtilizacionArbol.contarHojas(a));
            assertEquals(5050L, UtilizacionArbol.sumaNodos(a));
            assertTrue(UtilizacionArbol.esABB(a.getRaiz()));
        }
    }

    @Test void validacionGlobalDuplicadosYExtremos() {
        assertFalse(UtilizacionArbol.esABB(new NodoArbol(50,
                new NodoArbol(30, null, new NodoArbol(60)), new NodoArbol(70))));
        assertFalse(UtilizacionArbol.esABB(new NodoArbol(50, null,
                new NodoArbol(70, new NodoArbol(40), null))));
        assertFalse(UtilizacionArbol.esABB(new NodoArbol(5, new NodoArbol(5), null)));
        assertFalse(UtilizacionArbol.esABB(new NodoArbol(5, null, new NodoArbol(5))));
        ArbolBinarioBusqueda a = new ArbolBinarioBusqueda();
        a.insertar(0);
        a.insertar(Integer.MIN_VALUE);
        a.insertar(Integer.MAX_VALUE);
        assertTrue(UtilizacionArbol.esABB(a.getRaiz()));
        assertEquals(-1L, UtilizacionArbol.sumaNodos(a));
        ArbolBinarioBusqueda grandes = new ArbolBinarioBusqueda();
        grandes.insertar(Integer.MAX_VALUE);
        grandes.insertar(Integer.MAX_VALUE - 1);
        assertEquals(4294967293L, UtilizacionArbol.sumaNodos(grandes));
    }

    @Test void colaFIFOYReutilizacion() {
        Cola cola = new Cola();
        NodoArbol a = new NodoArbol(1);
        NodoArbol b = new NodoArbol(2);
        assertTrue(cola.esVacia());
        assertThrows(IllegalStateException.class, cola::desencolar);
        assertThrows(IllegalArgumentException.class, () -> cola.encolar(null));
        cola.encolar(a);
        cola.encolar(b);
        assertSame(a, cola.desencolar());
        assertSame(b, cola.desencolar());
        assertTrue(cola.esVacia());
        cola.encolar(b);
        assertSame(b, cola.desencolar());
        assertTrue(cola.esVacia());
    }
}
