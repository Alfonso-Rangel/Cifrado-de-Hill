package com.uam.CrifradodeHill.Controllers;
import org.springframework.web.bind.annotation.*;

import static com.uam.CrifradodeHill.Model.CifradoHill.cifrar;

@RestController
public class CifrarController {

    @PostMapping("/cifrar")
    public String encriptarTexto(@RequestBody TextoConClave textoConClave) {
        String texto = textoConClave.getTexto();
        String clave = textoConClave.getClave();
        String[] claveMatriz = clave.split(" ");

        int tam = (int) Math.sqrt(claveMatriz.length);
        if (Math.pow(tam, 2) != claveMatriz.length) {
            return "El texto no forma una matriz cuadrada.";
        }
        int[][] matriz = new int[tam][tam];
        int i = 0, j = 0;
        for (String valor : claveMatriz) {
            matriz[i][j++] = Integer.parseInt(valor);
            if (j == tam) {
                i++;
                j = 0;
            }
        }
        return cifrar(texto, matriz);
    }

    public static class TextoConClave {
        private String texto;
        private String clave;

        public String getTexto() {
            return texto;
        }

        public void setTexto(String texto) {
            this.texto = texto;
        }

        public String getClave() {
            return this.clave;
        }

        public void setClave(String clave) {this.clave = clave;}
    }
}

