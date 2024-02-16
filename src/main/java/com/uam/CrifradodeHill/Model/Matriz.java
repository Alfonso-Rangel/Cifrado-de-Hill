package com.uam.CrifradodeHill.Model;

public class Matriz {
    public final static int MOD = 94;

    public static int[][] multiplicacion(int[][] A, int[][] B) {
        int filasA = A.length;
        int columnasA = A[0].length;
        int filasB = B.length;
        int columnasB = B[0].length;

        // Verifica que las matrices se pueden multiplicar
        if (columnasA != filasB) {
            throw new IllegalArgumentException("Las matrices no son compatibles para multiplicación.");
        }

        int[][] C = new int[filasA][columnasB];

        for (int i = 0; i < filasA; i++) {
            for (int j = 0; j < columnasB; j++) {
                int suma = 0;
                for (int k = 0; k < columnasA; k++) {
                    suma += A[i][k] * B[k][j];
                }
                C[i][j] = suma;
            }
        }
        return C;
    }

    public static int[][] inversa(int[][] clave) {
        int det = Matriz.determinante(clave);
        int detInv = inversoModular(det); // Calcular el inverso modular del determinante
        if (detInv == -1) {
            throw new ArithmeticException("La matriz no es invertible en módulo " + MOD);
        }
        int[][] matrizAdjunta = Matriz.adjunta(clave);
        int[][] matrizInversa = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrizInversa[i][j] = ((matrizAdjunta[i][j] * detInv) % MOD + MOD) % MOD;
            }
        }
        return matrizInversa;
    }

    // Función para obtener el inverso modular de un número
    private static int inversoModular(int num) {
        num = num % MOD;
        for (int x = 1; x < MOD; x++) {
            if ((num * x) % MOD == 1) {
                return x;
            }
        }
        return -1; // Retornar -1 si el inverso modular no existe
    }

    static int determinante(int[][] matriz) {
        int tam = matriz[0].length;
        if (tam == 1) {
            return matriz[0][0];
        }
        else if (tam == 2) {
            return matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];
        }
        else {
            int det = 0;
            int j = 0;
            for (int i = 0; i < tam; i++) {
                det += (int)Math.pow(-1, i + j) * matriz[i][j] * determinante(creaSubMatriz(matriz, i, j));
            }
            return det;
        }
    }

    private static int[][] creaSubMatriz(int[][] matriz, int fila, int columna) {
        int n = matriz[0].length;
        int[][] subMatriz = new int[n - 1][n - 1];

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

    private static int[][] transpuesta(int[][] matriz) {
        int tam = matriz[0].length;
        int[][] t =  new int[tam][tam];
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                t[j][i] = matriz[i][j];
            }
        }
        return t;
    }

    private static int[][] adjunta(int[][] matriz) {
        int tam = matriz[0].length;
        int[][] adjunta = new int[tam][tam];

        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                adjunta[i][j] = (int) Math.pow(-1, i + j) * determinante(creaSubMatriz(matriz, i, j));
            }
        }
        return transpuesta(adjunta);
    }
}