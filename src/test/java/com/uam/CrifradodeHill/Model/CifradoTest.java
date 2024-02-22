package com.uam.CrifradodeHill.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CifradoTest {
    @Test
    public void cifradoTest() {
        String texto = "Hola, esto es una prueba";
        int[][] clave = {{1, 1, 0}, {2, 3, 1}, {1, 1, 1}};
        // Cifrar el texto
        String textoCifrado = CifradoHill.cifrar(texto, clave);
        // Descifrar el texto
        String textoDescifrado = CifradoHill.descifrar(textoCifrado, clave);
        assertEquals(texto, textoDescifrado);
    }

    @Test
    public void claveNoInvertible() {
        String texto = "Hola, esto es una prueba";
        int[][] clave = {{8, 7, 5}, {7, 3, 3}, {2, 5, 8}};
        String textoCifrado = CifradoHill.cifrar(texto, clave);
        assertEquals("La matriz no es invertible en modulo 94", textoCifrado);
    }

    @Test
    public void claveTamInvalido() {
        String texto = "Hola, esto es una prueba";
        int[][] clave = {{8, 7, 5}, {7, 3, 3}};
        String textoCifrado = CifradoHill.cifrar(texto, clave);
        assertEquals("La matriz no es cuadrada", textoCifrado);
    }

    @Test
    public void claveAleatoria() {
        String texto = "Hola, esto es una prueba";
        int[][] clave = CifradoHill.generaMatrizInvertible();
        String textoCifrado = CifradoHill.cifrar(texto, clave);
        String textoDescifrado = CifradoHill.descifrar(textoCifrado, clave);
        assertEquals(texto, textoDescifrado);
    }
}
