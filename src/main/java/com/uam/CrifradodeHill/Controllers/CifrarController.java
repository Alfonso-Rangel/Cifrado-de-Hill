package com.uam.CrifradodeHill.Controllers;

import org.springframework.web.bind.annotation.*;
import static com.uam.CrifradodeHill.Model.CifradoHill.cifrar;

@RestController
public class CifrarController {

    @PostMapping("/cifrar")
    public String encriptarTexto(@RequestBody TextoConClave textoConClave) {
        String texto = textoConClave.getTexto();
        String clave = textoConClave.getClave();
        return cifrar(texto, clave);
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

