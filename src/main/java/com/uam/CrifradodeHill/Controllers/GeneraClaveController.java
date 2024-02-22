package com.uam.CrifradodeHill.Controllers;

import com.uam.CrifradodeHill.Model.CifradoHill;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase que se encarga de generar una clave aleatoria para el cifrado de Hill
 * @autor Alfonso-Rangel
 */
@RestController
public class GeneraClaveController {

    @PostMapping("/generaClave")
    public String generaClave() {
        return CifradoHill.generaClave();
    }
}
