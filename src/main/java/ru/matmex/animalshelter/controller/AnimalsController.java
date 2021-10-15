package ru.matmex.animalshelter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.matmex.animalshelter.repository.AnimalRepository;

@Controller
public class AnimalsController {
    @Autowired
    private AnimalRepository animalRepository;

    @GetMapping("/animals")
    public String animals(Model model) {
        model.addAttribute("animals", animalRepository.findAll());

        return "animals";
    }
}
