package ru.matmex.animalshelter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.matmex.animalshelter.model.Animal;
import ru.matmex.animalshelter.service.AnimalService;


@Controller
public class AdminController {
    @Autowired
    private AnimalService animalService;

    @GetMapping("/admin/add")
    public String addAnimal(Model model) {
        model.addAttribute("animalForm", new Animal());
        return "addAnimal";
    }

    @PostMapping("/admin/add")
    public String addAnimal(@ModelAttribute("animalForm") Animal animalForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "/admin/add";
        }
        animalService.saveAnimal(animalForm);
        return "redirect:/animals";
    }
}
