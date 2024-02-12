package com.uam.CrifradodeHill.Model;

public class ProcesarCadena {
    public static char[] numeroACaracter(int[] valores) {
        char[] cadena = new char[valores.length];
        int i = 0;
        for (int valor : valores) {
            if (valor >= 0 && valor <= 255) {
                cadena[i++] = (char) valor;
            } else cadena[i++] = ' ';
        }
        return cadena;
    }

    public static int[] caracterANumero(char[] cadena) {
        int[] valores = new int[cadena.length];
        int i = 0;
        for (char caracter : cadena) {
            valores[i++] = caracter;
        }
        return valores;
    }

    public static double[][] cadenaAMatriz(char[] cadena) {
        int tam = cadena.length;
        int filas = 3;
        int columnas = tam % 3 == 0 ? tam / 3 : tam / 3 + 1;
        double[][] matriz = new double[filas][columnas];

        int i = 0;
        int j = 0;
        for (int n : cadena) {
            matriz[i][j++] = n;
            if (j == columnas) {
                i++;
                j = 0;
            }
        }
        while (j < columnas) {
            matriz[i][j++] = 36;
        }

        return  matriz;
    }

    public static char[] matrizACadena(double[][] matriz) {
        char[] cadena = new char[matriz.length * matriz[0].length];

        int k = 0;
        for (double[] fila : matriz) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (fila[j] >= 0 && fila[j] <= 255) {
                    cadena[k++] = (char) fila[j];
                } else cadena[k++] = ' ';
            }
        }

        return cadena;
    }
}
