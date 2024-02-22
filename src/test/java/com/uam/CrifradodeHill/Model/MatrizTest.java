package com.uam.CrifradodeHill.Model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MatrizTest {

    @Test
    public void multiplicacionTest() {
        int[][] C = {
                {35, 53, 12},
                {12, 21, 5},
                {2, 4, 1}};
        int[][] A = {
                {12, 4, 20, 20, 18},
                {0, 26, 18, 11, 26},
                {19, 12, 2, 14, 26}};

        int[][] resultado = Matriz.multiplicacion(C, A);

        int[][] esperado = {
                {648, 1662, 1678, 1451, 2320},
                {239, 654, 628, 541, 892},
                {43, 124, 114, 98, 166}};
        assertArrayEquals(esperado, resultado);
    }

    @Test
    public void multiplicacionMatrizNula() {
        int[][] A = null;
        int[][] B = {{1, 2}, {3, 4}};
        assertThrows(IllegalArgumentException.class, () -> Matriz.multiplicacion(A, B));
    }

    @Test
    public void multiplicacionMatrizVacia() {
        int[][] A = {};
        int[][] B = {{1, 2}, {3, 4}};
        assertThrows(IllegalArgumentException.class, () -> Matriz.multiplicacion(A, B));
    }

    @Test
    public void inversaMatrizNoInvertible() {
        int[][] matriz = {{1, 2}, {2, 4}};
        assertThrows(ArithmeticException.class, () -> Matriz.inversa(matriz));
    }

    @Test
    public void multiplicacionDimensionesIncorrectas() {
        int[][] A = {
                {1, 2},
                {3, 4}};
        int[][] B = {
                {5, 6},
                {8, 9},
                {7, 4}};
        assertThrows(IllegalArgumentException.class, () -> Matriz.multiplicacion(A, B));
    }
}