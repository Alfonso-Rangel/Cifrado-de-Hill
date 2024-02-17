package com.uam.CrifradodeHill.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.uam.CrifradodeHill.Model.Matriz.generaMatriz;

@RestController
public class GeneraClaveController {

    @PostMapping("/generaClave")
    public String generaClave() {
        int[][] matrizInvertible = generaMatriz();
        StringBuilder clave = new StringBuilder();
        for (int i = 0; i < matrizInvertible.length; i++) {
            for (int j = 0; j < matrizInvertible[0].length; j++) {
                clave.append(matrizInvertible[i][j]).append(" ");
            }
        }
        clave.deleteCharAt(clave.length() - 1);
        return clave.toString();
    }
}
