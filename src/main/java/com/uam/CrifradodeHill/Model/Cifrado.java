package com.uam.CrifradodeHill.Model;
import static com.uam.CrifradodeHill.Model.Matriz.multiplicacion;
import static com.uam.CrifradodeHill.Model.ProcesarCadena.cadenaAMatriz;

public class Cifrado {
    public static String cifrar(String texto, double[][] llave) {
        double[][] textoCifrado = cadenaAMatriz(texto.toCharArray());
        mod256(textoCifrado);
        double[][] matrizResultante = multiplicacion(llave, textoCifrado);
        return String.copyValueOf(ProcesarCadena.matrizACadena(matrizResultante));
    }

    private static void mod256(double[][] matriz) {
        for(double[] fila : matriz) {
            for (int j = 0; j < matriz[0].length; j++) {
                fila[j] %= 256;
            }
        }
    }


}
