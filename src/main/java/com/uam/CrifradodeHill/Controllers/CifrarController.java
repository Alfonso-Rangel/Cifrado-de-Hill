package com.uam.CrifradodeHill.Controllers;
import org.springframework.web.bind.annotation.*;

@RestController
public class CifrarController {

    @PostMapping("/cifrar")
    public String encriptarTexto(@RequestBody TextoConLlave textoConLlave) {
        String texto = textoConLlave.getTexto();
        String llave = textoConLlave.getLlave();
        // Lógica para encriptar el texto con la llave
        //String textoCifrado = cifrar(texto, llave);
        return "Prueba cifrar\n" + texto + "\n" + llave;
    }

    public static class TextoConLlave {
        private String texto;
        private String llave;

        public String getTexto() {
            return texto;
        }

        public void setTexto(String texto) {
            this.texto = texto;
        }

        public String getLlave() {
            return llave;
        }

        public void setLlave(String llave) {
            this.llave = llave;
        }
    }
}

