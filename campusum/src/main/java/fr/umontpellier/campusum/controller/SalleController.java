package fr.umontpellier.campusum.controller;

import fr.umontpellier.campusum.entity.Batiment;
import fr.umontpellier.campusum.entity.Salle;
import fr.umontpellier.campusum.service.BatimentService;
import fr.umontpellier.campusum.service.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/salle")
public class SalleController {

    @Autowired
    private SalleService salleService;

    @Autowired
    private BatimentService batimentService;


    @GetMapping("/batiment/{codeB}/salles")
    public String listSallesDuBatiment(@PathVariable String codeB, Model model) {
        Batiment batiment = batimentService.getBatimentById(codeB)
                .orElseThrow(() -> new RuntimeException("Bâtiment introuvable : " + codeB));

        List<Salle> salles = salleService.getSallesByBatiment(codeB);

        model.addAttribute("batiment", batiment);
        model.addAttribute("salles", salles);
        return "salle/list";
    }


    @GetMapping("/batiment/{codeB}/salles/create")
    public String formCreateSalle(@PathVariable String codeB, Model model) {
        Batiment batiment = batimentService.getBatimentById(codeB)
                .orElseThrow(() -> new RuntimeException("Bâtiment introuvable: " + codeB));

        Salle salle = new Salle();
        salle.setBatiment(batiment);

        model.addAttribute("batiment", batiment);
        model.addAttribute("salle", salle);
        model.addAttribute("isEdit", false);

        return "salle/form";
    }


    @PostMapping("/batiment/{codeB}/salles/create")
    public String createSalle(@PathVariable String codeB, @ModelAttribute Salle salle, Model model) {
        Batiment batiment = batimentService.getBatimentById(codeB)
                .orElseThrow(() -> new RuntimeException("Bâtiment invalide: " + codeB));

        salle.setBatiment(batiment);

        try {
            salleService.clearSession(); // 💡 Nettoyer la session Hibernate avant insertion
            salleService.detachSalle(salle); // 💡 Détache avant l'insertion
            salleService.saveSalle(salle);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("salle", salle);
            model.addAttribute("batiment", batiment);
            model.addAttribute("isEdit", false);
            return "salle/form";
        }

        return "redirect:/salle/batiment/" + codeB + "/salles";
    }


    @GetMapping("/edit/{numS}")
    public String showEditForm(@PathVariable String numS, Model model) {
        Salle salle = salleService.getSalleById(numS)
                .orElseThrow(() -> new RuntimeException("Salle introuvable : " + numS));

        model.addAttribute("salle", salle);
        model.addAttribute("isEdit", true);
        return "salle/form";
    }


    @PostMapping("/edit/{numS}")
    public String updateSalle(@PathVariable String numS, @ModelAttribute Salle salle) {
        salleService.clearSession(); // 💡 Nettoyer la session Hibernate avant mise à jour
        salleService.saveSalle(salle);
        return "redirect:/salle/batiment/" + salle.getBatiment().getCodeB() + "/salles";
    }


    @GetMapping("/delete/{numS}")
    public String deleteSalle(@PathVariable String numS) {
        Salle salle = salleService.getSalleById(numS)
                .orElseThrow(() -> new RuntimeException("Salle introuvable : " + numS));

        String codeB = salle.getBatiment().getCodeB();
        salleService.deleteSalle(numS);

        return "redirect:/salle/batiment/" + codeB + "/salles";
    }
}
