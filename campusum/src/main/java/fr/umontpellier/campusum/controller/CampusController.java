package fr.umontpellier.campusum.controller;


import fr.umontpellier.campusum.entity.Batiment;
import fr.umontpellier.campusum.entity.Campus;
import fr.umontpellier.campusum.repository.CampusRepository;
import fr.umontpellier.campusum.service.BatimentService;
import fr.umontpellier.campusum.service.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@Controller
@RequestMapping("/campus")
public class CampusController {

    @Autowired
    private CampusService campusService;

    @Autowired
    private BatimentService batimentService;

    @GetMapping
    public String listCampuses(Model model) {
        model.addAttribute("campuses", campusService.getAllCampus());
        return "campus/campus";
    }

    @GetMapping("/{nomC}/batiments")
    public String listBatimentsDuCampus(@PathVariable String nomC, Model model) {
        // 1. Vérifier que le campus existe
        Optional<Campus> campusOpt = campusService.getCampusByName(nomC);
        if (campusOpt.isEmpty()) {
            return "redirect:/campus";
        }
        Campus campus = campusOpt.get();

        // 2. Récupérer la liste des batiments (ex: batimentService.findByCampusNomC(nomC))
        List<Batiment> batiments = batimentService.getBatimentsByCampus(nomC);

        // 3. Ajouter au modèle
        model.addAttribute("campus", campus);
        model.addAttribute("batiments", batiments);

        // 4. Retourner la vue "batiment/list" (par ex.)
        return "batiment/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("campus", new Campus());
        return "campus/createCampus";
    }

    @PostMapping("/create")
    public String createCampus(@ModelAttribute Campus campus) {
        campusService.saveCampus(campus);
        return "redirect:/campus";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Optional<Campus> campus = campusService.getCampusByName(id);
        if (campus.isPresent()) {
            model.addAttribute("campus", campus.get());
            return "campus/editCampus";
        } else {
            return "redirect:/campus";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateCampus(@PathVariable String id, @ModelAttribute Campus campus) {
        campusService.saveCampus(campus);
        return "redirect:/campus";
    }

    @GetMapping("/delete/{id}")
    public String deleteCampus(@PathVariable String id) {
        campusService.deleteCampus(id);
        return "redirect:/campus";
    }
}