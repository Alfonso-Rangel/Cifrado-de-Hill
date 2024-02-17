package com.uam.CrifradodeHill.Model;


public class test {
    public static void main(String[] args) {
        String texto = "Hola, esto es una prueba";
        int [][] base = {{1, 1, 0}, {2, 3, 1}, {1, 1, 1}};
        int[][] clave = Matriz.generaMatriz();
        for (int i = 0; i < clave.length; i++) {
            for (int j = 0; j < clave[0].length; j++) {
                System.out.print(clave[i][j] + " ");
            }
            System.out.println();
        }
        // Cifrar el texto
        String textoCifrado = CrifradoHill.cifrar(texto, clave);
        System.out.println("Texto cifrado: " + textoCifrado);
        // Descifrar el texto
        String textoDescifrado = CrifradoHill.descifrar(textoCifrado, clave);
        System.out.println("Texto descifrado: " + textoDescifrado);
    }
}
