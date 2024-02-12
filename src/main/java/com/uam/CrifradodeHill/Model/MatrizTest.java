package com.uam.CrifradodeHill.Model;

public class MatrizTest {

    public static void main(String[] args) {
        // Ejecuta las pruebas
        testMultiplicacion();
        testInversa();
        testDeterminante();
        testMultiplicacionDimensionesIncorrectas();
        testInversaMatrizNoCuadrada();
    }

    public static void testMultiplicacion() {
        // Define matrices para la prueba de multiplicación
        double[][] C = {
                {35, 53, 12},
                {12, 21, 5},
                {2, 4, 1}};
        double[][] A = {
                {12, 4, 20, 20, 18},
                {0, 26, 18, 11, 26},
                {19, 12, 2, 14, 26}};

        // Llama al método multiplicacion
        double[][] resultado = Matriz.multiplicacion(C, A);

        // Verifica el resultado esperado
        double[][] esperado = {
                {648, 1662, 1678, 1451, 2320},
                {239, 654, 628, 541, 892},
                {43, 124, 114, 98, 166}};
        assertMatricesIguales(resultado, esperado, "Test Multiplicacion");

    }

    public static void testInversa() {
        // Define una matriz para la prueba de inversa
        double[][] matriz = {
                {1, 3},
                {-2, 6}};

        // Llama al método inversa
        double[][] resultado = Matriz.inversa(matriz);

        // Verifica el resultado esperado
        double[][] esperado = {
                {0.5, -0.25},
                {0.16666666666666666, 0.08333333333333333}};
        assertMatricesIguales(resultado, esperado, "Test Inversa");
    }

    public static void testDeterminante() {
        // Define una matriz para la prueba de determinante
        double[][] matriz = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};

        // Llama al método determinante
        double resultado = Matriz.determinante(matriz);

        // Verifica el resultado esperado
        double esperado = 0;
        System.out.println("Test Determinante: " + (resultado == esperado ? "PASSED" : "FAILED"));
    }

    // Método auxiliar para verificar si dos matrices son iguales
    private static void assertMatricesIguales(double[][] matriz1, double[][] matriz2, String mensaje) {
        for (int i = 0; i < matriz1.length; i++) {
            for (int j = 0; j < matriz1[0].length; j++) {
                if (matriz1[i][j] != matriz2[i][j]) {
                    System.out.println(mensaje + ": FAILED");
                    return;
                }
            }
        }
        System.out.println(mensaje + ": PASSED");
    }

    public static void testMultiplicacionDimensionesIncorrectas() {
        // Define matrices con dimensiones incorrectas para la prueba de multiplicación
        double[][] A = {
                {1, 2},
                {3, 4}};
        double[][] B = {
                {5, 6},
                {8, 9},
                {7, 4}};

        // Intenta llamar al método multiplicacion y captura la excepción
        try {
            Matriz.multiplicacion(A, B);
            System.out.println("Test Multiplicacion Dimensiones Incorrectas: FAILED");
        } catch (IllegalArgumentException e) {
            System.out.println("Test Multiplicacion Dimensiones Incorrectas: PASSED");
        }
    }

    public static void testInversaMatrizNoCuadrada() {
        // Define una matriz no cuadrada para la prueba de inversa
        double[][] matriz = {
                {1, 2, 3},
                {4, 5, 6}};

        // Intenta llamar al método inversa y captura la excepción
        try {
            Matriz.inversa(matriz);
            System.out.println("Test Inversa Matriz No Cuadrada: FAILED");
        } catch (ArithmeticException  | IllegalArgumentException e) {
            System.out.println("Test Inversa Matriz No Cuadrada: PASSED");
        }
    }
}
