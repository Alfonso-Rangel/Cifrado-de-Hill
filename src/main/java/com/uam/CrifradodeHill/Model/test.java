package com.uam.CrifradodeHill.Model;


public class test {
    public static void main(String[] args) {
        String texto = "Hola";
        int [][] ejemplo = {{8, 7, 5}, {7, 3, 3}, {2, 5, 8}};
        int [][] base = {{1, 1, 0}, {2, 3, 1}, {1, 1, 1}};
        int[][] clave = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};

        int[][] test = base;

        // Cifrar el texto
        String textoCifrado = CrifradoHill.cifrar(texto, test);
        System.out.println("Texto cifrado: " + textoCifrado);
        // Descifrar el texto
        String textoDescifrado = CrifradoHill.descifrar(textoCifrado, test);
        System.out.println("Texto descifrado: " + textoDescifrado);
    }
}
