package com.uam.CrifradodeHill.Controllers;
import org.springframework.web.bind.annotation.*;

@RestController
public class DescifrarController {

    @PostMapping("/descifrar")
    public String desencriptarTexto(@RequestBody TextoConLlave textoConLlave) {
        String texto = textoConLlave.getTexto();
        String llave = textoConLlave.getLlave();
        return "Prueba descifrar\n" + texto + "\n" + llave;
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

