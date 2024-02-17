package com.uam.CrifradodeHill.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CifradoTest {
    @Test
    public void cifradoTest() {
        String texto = "Hola, esto es una prueba";
        int [][] clave = {{1, 1, 0}, {2, 3, 1}, {1, 1, 1}};
        // Cifrar el texto
        String textoCifrado = CrifradoHill.cifrar(texto, clave);
        // Descifrar el texto
        String textoDescifrado = CrifradoHill.descifrar(textoCifrado, clave);
        assertEquals(texto, textoDescifrado);
    }

    @Test
    public void claveIncorrectaTest() {
        String texto = "Hola";
        int [][] clave = {{8, 7, 5}, {7, 3, 3}, {2, 5, 8}};
        String textoCifrado = CrifradoHill.cifrar(texto, clave);
        assertEquals("La matriz no es invertible en modulo 94", textoCifrado);
    }
}
