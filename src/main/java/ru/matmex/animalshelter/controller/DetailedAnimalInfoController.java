package ru.matmex.animalshelter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.matmex.animalshelter.repository.AnimalRepository;

@Controller
public class DetailedAnimalInfoController {
    @Autowired
    private AnimalRepository animalRepository;

    @GetMapping("/detailedAnimalInfo/{id}")
    public String animals(Model model, @PathVariable Long id) {
        model.addAttribute("animal", animalRepository.getOne(id));

        return "detailedAnimalInfo";
    }
}
