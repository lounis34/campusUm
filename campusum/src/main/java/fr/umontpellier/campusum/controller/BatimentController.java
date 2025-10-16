package fr.umontpellier.campusum.controller;

import fr.umontpellier.campusum.entity.Batiment;
import fr.umontpellier.campusum.entity.Campus;
import fr.umontpellier.campusum.entity.Composante;
import fr.umontpellier.campusum.entity.Salle;
import fr.umontpellier.campusum.repository.CampusRepository;
import fr.umontpellier.campusum.service.BatimentService;
import fr.umontpellier.campusum.service.CampusService;
import fr.umontpellier.campusum.service.ComposanteService;
import fr.umontpellier.campusum.service.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/batiment")
public class BatimentController {

    @Autowired
    private BatimentService batimentService;
    @Autowired
    private CampusService campusService;

    @Autowired
    private ComposanteService composanteService;

    @Autowired
    private CampusRepository campusRepository;

    @GetMapping
    public String listBatiments(Model model) {
        model.addAttribute("batiments", batimentService.getAllBatiments());
        return "batiment/list";
    }

    @GetMapping("/view/{codeB}")
    public String viewBatiment(@PathVariable String codeB, Model model) {
        // Récupère le batiment
        Batiment batiment = batimentService.getBatimentById(codeB).orElse(null);
        if (batiment == null) {
            return "redirect:/batiment";
        }
        model.addAttribute("batiment", batiment);
        // Retourne "batiment/view" => resources/templates/batiment/view.html
        return "batiment/view";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        List<Campus> campuses = campusService.getAllCampus();

        model.addAttribute("batiment", new Batiment());
        model.addAttribute("campuses", campuses);
        return "batiment/form";
    }


    @GetMapping("/{codeB}/composantes")
    public String listComposantesDuBatiment(@PathVariable String codeB, Model model) {
        Optional<Batiment> bOpt = batimentService.getBatimentById(codeB);
        if (bOpt.isEmpty()) {
            return "redirect:/batiment";
        }
        Batiment batiment = bOpt.get();

        // Récupérer les composantes => batiment.getComposantes()
        List<Composante> composantes = batiment.getComposantes();

        model.addAttribute("batiment", batiment);
        model.addAttribute("composantes", composantes);

        return "batiment/composantes";
    }

    @PostMapping("/create")
    public String createBatiment(@ModelAttribute Batiment batiment) {
        batimentService.saveBatiment(batiment);
        return "redirect:/batiment";
    }

    @GetMapping("/edit/{codeB}")
    public String showEditForm(@PathVariable String codeB, Model model) {
        Optional<Batiment> batimentOpt = batimentService.getBatimentById(codeB);
        if (batimentOpt.isPresent()) {
            Batiment batiment = batimentOpt.get();
            model.addAttribute("batiment", batiment);
            model.addAttribute("campuses", campusRepository.findAll());  // Ajout des campuses pour sélectionner
            model.addAttribute("composantes", composanteService.getAllComposantes()); // Ajout des composantes
            return "batiment/form";
        } else {
            return "redirect:/batiment";
        }
    }

    @PostMapping("/edit/{codeB}")
    public String updateBatiment(@PathVariable String codeB, @ModelAttribute Batiment batiment) {
        batimentService.saveBatiment(batiment);
        return "redirect:/batiment";
    }

    @GetMapping("/delete/{codeB}")
    public String deleteBatiment(@PathVariable String codeB) {
        batimentService.deleteBatiment(codeB);
        return "redirect:/batiment";
    }
}
