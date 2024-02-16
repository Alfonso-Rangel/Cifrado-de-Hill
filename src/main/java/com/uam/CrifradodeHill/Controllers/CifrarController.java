package com.uam.CrifradodeHill.Controllers;
import org.springframework.web.bind.annotation.*;

@RestController
public class CifrarController {

    @PostMapping("/cifrar")
    public String encriptarTexto(@RequestBody TextoConLlave textoConLlave) {
        String texto = textoConLlave.getTexto();
        int[] llave = textoConLlave.getLlave();
        // Lógica para encriptar el texto con la llave
        //String textoCifrado = cifrar(texto, llave);
        return "";
    }

    public static class TextoConLlave {
        private String texto;
        private int[] llave;

        public String getTexto() {
            return texto;
        }

        public void setTexto(String texto) {
            this.texto = texto;
        }

        public int[] getLlave() {
            return llave;
        }

        public void setLlave(int[] llave) {
            this.llave = llave;
        }
    }
}

