package com.uam.CrifradodeHill.Controllers;

import com.uam.CrifradodeHill.Model.CifradoHill;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneraClaveController {

    @PostMapping("/generaClave")
    public String generaClave() {
        return CifradoHill.generaClave();
    }
}
