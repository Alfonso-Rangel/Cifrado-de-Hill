package com.uam.CrifradodeHill.Model;

public class Matriz {

    public static double[][] multiplicacion(double[][] A, double[][] B) {
        int filasA = A.length;
        int columnasA = A[0].length;
        int filasB = B.length;
        int columnasB = B[0].length;

        // Verifica que las matrices se pueden multiplicar
        if (columnasA != filasB) {
            throw new IllegalArgumentException("Las matrices no son compatibles para multiplicación.");
        }

        double[][] C = new double[filasA][columnasB];

        for (int i = 0; i < filasA; i++) {
            for (int j = 0; j < columnasB; j++) {
                double suma = 0;
                for (int k = 0; k < columnasA; k++) {
                    suma += A[i][k] * B[k][j];
                }
                C[i][j] = suma;
            }
        }
        return C;
    }


    public static double[][] inversa(double[][] matriz) {
        if (matriz.length != matriz[0].length) {
            throw new IllegalArgumentException("La matriz no es cuadrada.");
        }
        double det = determinante(matriz);
        if (det == 0) {
            throw new ArithmeticException("La matriz no tiene inversa");
        }

        int tam = matriz[0].length;
        double[][] matrizInversa = new double[tam][tam];
        double[][] t = transpuesta(adjunta(matriz));

        for (int i = 0; i < tam ; i++) {
            for (int j = 0; j < tam; j++) {
                matrizInversa[i][j] = t[i][j] / det;
            }
        }
        return matrizInversa;
    }

    public static double determinante(double[][] matriz) {
        int tam = matriz[0].length;
        if (tam == 1) {
            return matriz[0][0];
        }
        else if (tam == 2) {
            return matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];
        }
        else {
            double det = 0;
            int j = 0;
            for (int i = 0; i < tam; i++) {
                det += Math.pow(-1, i + j) * matriz[i][j] * determinante(creaSubMatriz(matriz, i, j));
            }
            return det;
        }
    }

    private static double[][] creaSubMatriz(double[][] matriz, int fila, int columna) {
        int n = matriz[0].length;
        double[][] subMatriz = new double[n - 1][n - 1];

        int a = 0, b;

        for (int i = 0; i < n; i++) {
            if (i == fila) {
                continue;
            }

            b = 0;
            for (int j = 0; j < n; j++) {
                if (j != columna) {
                    subMatriz[a][b++] = matriz[i][j];
                }
            }

            a++;
        }
        return subMatriz;
    }

    private static double[][] transpuesta(double[][] matriz) {
        int tam = matriz[0].length;
        double[][] t =  new double[tam][tam];
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                t[j][i] = matriz[i][j];
            }
        }
        return t;
    }

    private static double[][] adjunta(double[][] matriz) {
        int tam = matriz[0].length;
        double[][] adjunta = new double[tam][tam];

        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                adjunta[i][j] = Math.pow(-1, i + j) * determinante(creaSubMatriz(matriz, i, j));
            }
        }
        return adjunta;
    }
}