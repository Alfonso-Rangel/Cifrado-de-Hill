package com.uam.CrifradodeHill.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CifradoTest {
    @Test
    public void cifradoTest() {
        // Considerar que el descifrado a veces agrega espacio en blanco al final del texto
        // No es un problema para el funcionamiento del programa
        String texto = " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_abcdefghijklmnopqrstuvwxyz{|}";
        String clave = "1 1 0 2 3 1 1 1 1";
        // Cifrar el texto
        String textoCifrado = CifradoHill.cifrar(texto, clave);
        // Descifrar el texto
        String textoDescifrado = CifradoHill.descifrar(textoCifrado, clave);
        assertEquals(texto, textoDescifrado);
    }

    @Test
    public void claveNoInvertible() {
        String texto = "Hola, esto es una prueba";
        String clave = "8 7 5 7 3 3 2 5 8";
        String textoCifrado = CifradoHill.cifrar(texto, clave);
        assertEquals("La matriz no es invertible en modulo 94", textoCifrado);
    }

    @Test
    public void claveTamInvalido() {
        String texto = "Hola, esto es una prueba";
        String clave = "8 7 5 7 3 3";
        String textoCifrado = CifradoHill.cifrar(texto, clave);
        assertEquals("La clave no forma una matriz cuadrada.", textoCifrado);
    }

    @Test
    public void claveAleatoria() {
        String texto = "Hola, esto es una prueba";
        String clave = CifradoHill.generaClave();
        String textoCifrado = CifradoHill.cifrar(texto, clave);
        String textoDescifrado = CifradoHill.descifrar(textoCifrado, clave);
        assertEquals(texto, textoDescifrado);
    }
}
