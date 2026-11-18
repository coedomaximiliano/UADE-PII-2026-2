package com.example.tp4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Tp4ApplicationTests {

    @Test
    void testOperacionesBasicasGrafo() {
        Grafo g = Main.construirGrafoEjemplo();
        assertEquals(8, g.cantidadVertices());
        assertTrue(g.existeArista("Buenos Aires", "Cordoba"));
        assertTrue(g.existeArista("Cordoba", "Buenos Aires")); // Simetría
        assertFalse(g.existeArista("Buenos Aires", "Salta"));
        assertArrayEquals(
                new String[]{"Rosario", "Cordoba", "Mendoza"},
                g.vecinos("Buenos Aires")
        );
        assertEquals(0, g.vecinos("Ushuaia").length);
    }

    @Test
    void testRecorridosParte3() {
        Grafo g = Main.construirGrafoEjemplo();

        assertArrayEquals(
                new String[]{"Buenos Aires", "Rosario", "Cordoba", "Mendoza", "Santa Fe", "Tucuman", "Salta"},
                Main.bfs(g, "Buenos Aires")
        );

        assertArrayEquals(
                new String[]{"Buenos Aires", "Rosario", "Cordoba", "Mendoza", "Tucuman", "Santa Fe", "Salta"},
                Main.dfs(g, "Buenos Aires")
        );

        assertArrayEquals(
                new String[]{"Buenos Aires", "Mendoza", "Cordoba", "Tucuman", "Salta", "Santa Fe", "Rosario"},
                Main.dfsIterativo(g, "Buenos Aires")
        );
    }

    @Test
    void testUtilizacionParte4() {
        Grafo g = Main.construirGrafoEjemplo();

        assertTrue(Main.estanConectados(g, "Buenos Aires", "Salta"));
        assertTrue(Main.estanConectados(g, "Mendoza", "Santa Fe"));
        assertFalse(Main.estanConectados(g, "Buenos Aires", "Ushuaia"));

        assertEquals(2, Main.componentesConexas(g));

        assertEquals(0, Main.caminoMasCorto(g, "Buenos Aires", "Buenos Aires"));
        assertEquals(1, Main.caminoMasCorto(g, "Buenos Aires", "Cordoba"));
        assertEquals(2, Main.caminoMasCorto(g, "Buenos Aires", "Tucuman"));
        assertEquals(3, Main.caminoMasCorto(g, "Buenos Aires", "Salta"));
        assertEquals(-1, Main.caminoMasCorto(g, "Buenos Aires", "Ushuaia"));
    }
}
