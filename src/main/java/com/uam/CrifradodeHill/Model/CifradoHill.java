package com.uam.CrifradodeHill.Model;

import java.util.Random;
import static com.uam.CrifradodeHill.Model.Matriz.inversa;

/**
 * Clase que implementa el cifrado de Hill.
 * La clase contiene métodos para cifrar y descifrar un texto, dada una clave.
 * Además, contiene métodos para generar una clave aleatoria.
 * Los métodos de esta clase son estáticos y no se requiere instanciar la clase para utilizarlos.
 * @author Alfonso-Rangel
 */
public class CifradoHill {

    /**
     * Cifra un texto utilizando el cifrado de Hill con una clave dada.
     *
     * @param texto El texto a cifrar.
     * @param clave Texto que representa la clave del cifrado.
     * @return El texto cifrado. Si la clave no es invertible, se devuelve un mensaje de error.
     */
    public static String cifrar(String texto, String clave) {
        int[][] claveMatriz;
        try {
            claveMatriz = textoAMatriz(clave);
            inversa(claveMatriz);
        } catch (ArithmeticException | IllegalArgumentException e) {
            return e.getMessage();
        }
        // Se añaden espacios al final para completar el múltiplo y que sea compatible para la multiplicación con la clave
        int resto = texto.length() % claveMatriz.length;
        if (resto != 0) {
            texto += " ".repeat(claveMatriz.length - resto); // Se añaden espacios al final para completar el múltiplo
        }

        return aplicarConversion(texto, claveMatriz);
    }

    /**
     * Descifra un texto cifrado utilizando el cifrado de Hill con una clave dada.
     *
     * @param textoCifrado El texto cifrado a descifrar.
     * @param clave        Texto que representa la clave del cifrado.
     * @return El texto descifrado. Si la clave no es invertible, se devuelve un mensaje de error.
     */
    public static String descifrar(String textoCifrado, String clave) {
        int[][] claveMatriz;
        try {
            claveMatriz = textoAMatriz(clave);
        } catch (ArithmeticException | IllegalArgumentException e) {
            return e.getMessage();
        }
        int[][] matrizInversa = inversa(claveMatriz);
        return aplicarConversion(textoCifrado, matrizInversa);
    }

    /**
     * Método privado para realizar la conversión de texto cifrado a texto plano o viceversa,
     * utilizando la matriz clave para realizar la operación matricial requerida.
     *
     * @param texto El texto a cifrar o descifrar.
     * @param clave La matriz cuadrada que representa la clave del cifrado.
     * @return El texto luego de aplicar la operación matricial con la clave.
     */
    private static String aplicarConversion(String texto, int[][] clave) {
        StringBuilder textoConvertido = new StringBuilder();
        int longitudTexto = texto.length();
        int longitudClave = clave.length;
        // Se recorre el texto en bloques de longitudClave
        for (int i = 0; i < longitudTexto; i += longitudClave) {
            int[] bloque = new int[longitudClave];
            // Se convierte cada bloque de texto a un bloque de números
            for (int j = 0; j < longitudClave; j++) {
                char letra = texto.charAt(i + j);
                int num = letraANumero(letra);
                bloque[j] = num;
            }
            // Se multiplica el bloque de números por la clave
            int[] resultado = Matriz.multiplicacion(new int[][]{bloque}, clave)[0];
            // Se convierte el bloque de números resultante a un bloque de texto
            for (int num : resultado) {
                char letra = numeroALetra(num);
                textoConvertido.append(letra);
            }
        }
        return textoConvertido.toString();
    }

    /**
     * Convierte un número entero en un caracter de acuerdo con el módulo definido en la clase Matriz.
     * @param numero El número entero a convertir.
     * @return El caracter correspondiente al número.
     * @see Matriz#MOD
     */
    private static char numeroALetra(int numero) {
        // Se le suma el 32 p|ara que el rango de caracteres sea de 32 a 126.
        return (char) ((numero + Matriz.MOD) % Matriz.MOD + 32);
    }

    /**
     * Convierte un caracter en un número entero de acuerdo con el módulo definido en la clase Matriz.
     *
     * @param letra El caracter a convertir.
     * @return El número entero correspondiente al caracter.
     * @see Matriz#MOD
     */
    private static int letraANumero(char letra) {
        // Se le resta el 32 para que el rango de caracteres sea de 32 a 126.
        return (letra - 32 + Matriz.MOD) % Matriz.MOD;
    }

    /**
     * Convierte un texto que representa una clave a una matriz cuadrada.
     * @param clave El texto que representa la clave.
     * @return La matriz cuadrada generada a partir del texto.
     * @throws IllegalArgumentException Si el texto no forma una matriz cuadrada.
     */
    private static int[][] textoAMatriz(String clave) {
        String[] matrizClave = clave.split(" ");

        int tam = (int) Math.sqrt(matrizClave.length);
        if (Math.pow(tam, 2) != matrizClave.length) {
            throw new IllegalArgumentException("La clave no forma una matriz cuadrada.");
        }
        int[][] matriz = new int[tam][tam];
        int i = 0, j = 0;
        for (String valor : matrizClave) {
            matriz[i][j++] = Integer.parseInt(valor);
            if (j == tam) {
                i++;
                j = 0;
            }
        }
        return matriz;
    }

    /**
     * Genera una clave aleatoria para el cifrado de Hill.
     * La clave generada está garantizada de tener inversa modular.
     *
     * @return La clave generada en formato de texto, separando los valores de la matriz por espacios.
     */
    public static String generaClave() {
        int[][] matrizInvertible = generaMatrizInvertible();
        StringBuilder clave = new StringBuilder();
        for (int i = 0; i < matrizInvertible.length; i++) {
            for (int j = 0; j < matrizInvertible[0].length; j++) {
                clave.append(matrizInvertible[i][j]).append(" ");
            }
        }
        return clave.toString();
    }

    /**
     * Genera una matriz cuadrada de 3x3 con valores aleatorios.
     * La matriz generada está garantizada de tener inversa modular.
     * @return La matriz generada.
     */
    public static int[][] generaMatrizInvertible() {
        // Se realizará un procedimiento similar a Gauss-Jordan para generar una matriz partir de la
        // matriz identidad, esto garantiza que la matriz generada tenga inversa modular.

        // dimension es el tamaño de la matriz, puede ser modificado para generar claves de mayor tamaño,
        // pero he ele|gido 3 para que sea más fácil de visualizar en la interfaz gráfica
        int dimension = 3;
        // Genera e inicializa la matriz identidad -----------------------
        int[][] matrizInvertible = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            matrizInvertible[i][i] = 1;
        }

        // ---------------------------------------------------------------
        Random random = new Random();
        // delta es la cantidad de veces que se va a modificar la matriz identidad
        // he elegido 20 para no tener una matriz con números excesivamente grandes
        int delta = 20;
        // Se elige una fila aleatoria para multiplicar por un escalar y sumar a otra fila diferente (Gauss-Jordan)
        while (delta > 0) {
            int filaPivote = random.nextInt(dimension);
            int escalar = random.nextInt(4) + 1;
            int filaAModificar;
            do {
                filaAModificar = random.nextInt(dimension);
            } while (filaAModificar == filaPivote);
            for (int j = 0; j < dimension; j++) {
                matrizInvertible[filaAModificar][j] += matrizInvertible[filaPivote][j] * escalar;
            }
            delta--;
        }
        return matrizInvertible;
    }
}
