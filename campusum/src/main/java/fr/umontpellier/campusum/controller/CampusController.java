package fr.umontpellier.campusum.controller;


import fr.umontpellier.campusum.entity.Campus;
import fr.umontpellier.campusum.repository.CampusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;



@Controller
@RequestMapping("/campus")
public class CampusController {

    @Autowired
    private CampusRepository campusRepository;

    @GetMapping
    public String listCampuses(Model model) {
        model.addAttribute("campuses", campusRepository.findAll());
        return "campus";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("campus", new Campus());
        return "createCampus";
    }

    @PostMapping("/create")
    public String createCampus(@ModelAttribute Campus campus) {
        campusRepository.save(campus);
        return "redirect:/campus";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Optional<Campus> campus = campusRepository.findById(id);
        if (campus.isPresent()) {
            model.addAttribute("campus", campus.get());
            return "editCampus";
        } else {
            return "redirect:/campus";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateCampus(@PathVariable String id, @ModelAttribute Campus campus) {
        campusRepository.save(campus);
        return "redirect:/campus";
    }

    @GetMapping("/delete/{id}")
    public String deleteCampus(@PathVariable String id) {
        campusRepository.deleteById(id);
        return "redirect:/campus";
    }
}