package com.uam.CrifradodeHill.Model;
/**
 * Clase que contiene métodos para realizar operaciones con matrices.
 * Los métodos de esta clase son estáticos y no se requiere instanciar la clase para utilizarlos.
 * La clase contiene métodos para realizar las siguientes operaciones:
 * - Multiplicación de matrices.
 * - Cálculo del inverso modular de una matriz.
 * - Cálculo del determinante de una matriz.
 * @author Alfonso-Rangel
 */
public class Matriz {
    /**
     * Módulo para realizar las operaciones de la matriz.
     * Se utiliza el módulo 94 para que las operaciones se realicen en el rango de los caracteres imprimibles.
     * Los caracteres imprimibles van del 32 al 126 en ASCII.
     * Cambiar el módulo no afecta la funcionalidad de los métodos.
     * Es public para que pueda leerse desde otras clases.
     */
    public final static int MOD = 94;

    /**
     * Realiza la multiplicación de dos matrices enteras.
     * Las matrices deben ser compatibles para la multiplicación.
     *
     * @param A La primera matriz a multiplicar de tamaño n×m.
     * @param B La segunda matriz a multiplicar de tamaño m×p.
     * @return Una nueva matriz de tamaño n×p, que es el resultado de la multiplicación de A y B.
     * @throws IllegalArgumentException Si se cumple alguna de las siguientes condiciones:
     *                                 - Una de las matrices es nula.
     *                                 - Una de las matrices está vacía.
     *                                 - Las matrices no son compatibles para multiplicación.
     */
    public static int[][] multiplicacion(int[][] A, int[][] B) {
        // Verifica que las matrices no sean nulas
        if (A == null || B == null) {
            throw new IllegalArgumentException("Una de las matrices es nula.");
        }

        // Verifica que las matrices no estén vacías
        if (A.length == 0 || B.length == 0 || A[0].length == 0 || B[0].length == 0) {
            throw new IllegalArgumentException("Una de las matrices está vacía.");
        }

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

    /**
     * Calcula la inversa modular de una matriz cuadrada dada.
     * @param clave La matriz cuadrada de la cual se desea calcular la inversa.
     *              La matriz debe ser cuadrada, no nula y no vacía.
     * @return La matriz inversa de la matriz dada.
     * @throws ArithmeticException Si la matriz no es invertible en el módulo MOD.
     * @throws IllegalArgumentException Si la matriz no es cuadrada.
     */
    public static int[][] inversa(int[][] clave) {
        int tam = clave.length;
        if (tam != clave[0].length) {
            throw new IllegalArgumentException("La matriz no es cuadrada");
        }
        int det = Matriz.determinante(clave);
        int detInv = inversoModular(det); // Calcular el inverso modular del determinante
        if (detInv == -1) {
            throw new ArithmeticException("La matriz no es invertible en modulo " + MOD);
        }
        int[][] matrizAdjunta = Matriz.adjunta(clave);
        int[][] matrizInversa = new int[tam][tam];
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                matrizInversa[i][j] = ((matrizAdjunta[i][j] * detInv) % MOD + MOD) % MOD;
            }
        }
        return matrizInversa;
    }

    /**
     * Calcula el inverso modular de un número entero.
     *
     * @param num El número del cual se quiere calcular el inverso modular.
     * @return El inverso modular de num si existe, -1 en caso contrario.
     */
    private static int inversoModular(int num) {
        num = num % MOD;
        for (int x = 1; x < MOD; x++) {
            if ((num * x) % MOD == 1) {
                return x;
            }
        }
        return -1; // Retornar -1 si el inverso modular no existe
    }

    /**
     * Calcula el determinante de una matriz cuadrada utilizando el método de Laplace.
     * El método asume que la matriz es cuadrada.
     *
     * @param matriz La matriz cuadrada de la cual se quiere calcular el determinante.
     * @return El valor del determinante de la matriz.
     */
    private static int determinante(int[][] matriz) {
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

    /**
     * Método utilizado como auxiliar para calcular la matriz de cofactores.
     * Crea y devuelve una submatriz de una matriz dada eliminando una fila y una columna específicadas.
     * La submatriz es una matriz cuadrada de un tamaño menor en 1 a la matriz original.
     *
     * @param matriz   La matriz de la cual se desea crear la submatriz.
     *                 La matriz debe ser cuadrada.
     * @param fila     La fila que se eliminará de la matriz para crear la submatriz.
     *                 Debe ser un número entre 0 y n-1, donde n es el tamaño de la matriz.
     * @param columna  La columna que se eliminará de la matriz para crear la submatriz.
     *                 Debe ser un número entre 0 y n-1, donde n es el tamaño de la matriz.
     * @return Una nueva matriz que representa la submatriz.
     */
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

    /**
     * Calcula la transpuesta de una matriz cuadrada dada.
     *
     * @param matriz La matriz cuadrada de la cual se desea calcular la transpuesta.
     *               La matriz debe ser cuadrada, no nula y no vacía.
     * @return La transpuesta de la matriz dada.
     */
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

    /**
     * Calcula la matriz adjunta de una matriz cuadrada dada.
     * La matriz adjunta es la transpuesta de la matriz de cofactores.
     *
     * @param matriz La matriz cuadrada de la cual se desea calcular la matriz adjunta.
     *               La matriz debe ser cuadrada, no nula y no vacía.
     * @return La matriz adjunta de la matriz dada.
     * @see #transpuesta(int[][])
     */
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