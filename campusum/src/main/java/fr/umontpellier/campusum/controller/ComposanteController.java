package fr.umontpellier.campusum.controller;

import fr.umontpellier.campusum.entity.Composante;
import fr.umontpellier.campusum.service.BatimentService;
import fr.umontpellier.campusum.service.ComposanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/composante")
public class ComposanteController {

    @Autowired
    private ComposanteService composanteService;
    @Autowired
    private BatimentService batimentService;
    /**
     * Liste des Composantes => GET /composante
     * => templates/composante/list.html
     */
    @GetMapping
    public String listComposantes(Model model) {
        model.addAttribute("composantes", composanteService.getAllComposantes());
        return "composante/list";
    }

    /**
     * Formulaire de création => GET /composante/create
     * => templates/composante/form.html (on peut réutiliser le même template pour créer/éditer)
     */
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("composante", new Composante());
        model.addAttribute("isEdit", false); // Pour distinguer création / édition dans le form
        model.addAttribute("batiments", batimentService.getAllBatiments()); // Fournir les batiments
        return "composante/form";
    }

    /**
     * Traitement de la création => POST /composante/create
     */
    @PostMapping("/create")
    public String createComposante(@ModelAttribute Composante composante) {
        composanteService.saveComposante(composante);
        return "redirect:/composante";
    }

    /**
     * Formulaire d'édition => GET /composante/edit/{acronyme}
     */
    @GetMapping("/edit/{acronyme}")
    public String showEditForm(@PathVariable String acronyme, Model model) {
        Optional<Composante> cOpt = composanteService.getComposanteById(acronyme);
        if (cOpt.isPresent()) {
            model.addAttribute("composante", cOpt.get());
            model.addAttribute("isEdit", true);
            model.addAttribute("batiments", batimentService.getAllBatiments()); // Fournir les batiments
            return "composante/form";
        }
        return "redirect:/composante";
    }

    /**
     * Traitement de l'édition => POST /composante/edit/{acronyme}
     */
    @PostMapping("/edit/{acronyme}")
    public String updateComposante(@PathVariable String acronyme, @ModelAttribute Composante composante) {
        composanteService.saveComposante(composante);
        return "redirect:/composante";
    }

    /**
     * Suppression => GET /composante/delete/{acronyme}
     */
    @GetMapping("/delete/{acronyme}")
    public String deleteComposante(@PathVariable String acronyme) {
        composanteService.deleteComposante(acronyme);
        return "redirect:/composante";
    }
}
