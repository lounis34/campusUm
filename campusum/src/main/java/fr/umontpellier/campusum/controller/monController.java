package fr.umontpellier.campusum.controller;

import fr.umontpellier.campusum.entity.Campus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller

public class monController {

   @GetMapping("/")
    public String index() {
        return "index"; // Le nom du fichier sans l'extension.html
    }
}
