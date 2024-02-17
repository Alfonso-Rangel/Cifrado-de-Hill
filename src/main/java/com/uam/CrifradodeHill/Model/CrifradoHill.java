package com.uam.CrifradodeHill.Model;

import static com.uam.CrifradodeHill.Model.Matriz.inversa;

public class CrifradoHill {

    public static String cifrar(String texto, int[][] clave) {
        try {
            inversa(clave);
        } catch (ArithmeticException e) {
            return e.getMessage();
        }

        int longitudClave = clave.length;
        int n = texto.length();
        int resto = n % longitudClave;
        if (resto != 0) {
            texto += " ".repeat(longitudClave - resto); // Añadir espacios al final para completar el múltiplo
            n = texto.length();
        }

        StringBuilder textoCifrado = new StringBuilder();
        for (int i = 0; i < n; i += longitudClave) {
            int[] bloque = new int[longitudClave];
            for (int j = 0; j < longitudClave; j++) {
                char letra = texto.charAt(i + j);
                int num = letraANumero(letra);
                bloque[j] = num;
            }
            int[] resultado = Matriz.multiplicacion(new int[][]{bloque}, clave)[0];
            for (int num : resultado) {
                char letra = numeroALetra(num);
                textoCifrado.append(letra);
            }
        }
        return textoCifrado.toString();
    }

    public static String descifrar(String textoCifrado, int[][] clave) {
        int longitudClave = clave.length;
        int n = textoCifrado.length();
        StringBuilder textoDescifrado = new StringBuilder();
        int[][] matrizInversa = inversa(clave);
        for (int i = 0; i < n; i += longitudClave) {
            int[] bloque = new int[longitudClave];
            for (int j = 0; j < longitudClave; j++) {
                char letra = textoCifrado.charAt(i + j);
                int num = letraANumero(letra);
                bloque[j] = num;
            }
            int[] resultado = Matriz.multiplicacion(new int[][]{bloque}, matrizInversa)[0];
            for (int num : resultado) {
                char letra = numeroALetra(num);
                textoDescifrado.append(letra);
            }
        }
        return textoDescifrado.toString();
    }

    public static char numeroALetra(int numero) {
        return (char) ((numero + Matriz.MOD) % Matriz.MOD + 32);
    }

    public static int letraANumero(char letra) {
        return (letra - 32 + Matriz.MOD) % Matriz.MOD;
    }

}
