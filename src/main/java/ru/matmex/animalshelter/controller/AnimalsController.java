package ru.matmex.animalshelter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.matmex.animalshelter.model.*;
import ru.matmex.animalshelter.repository.AnimalRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class AnimalsController {
    @Autowired
    private AnimalRepository animalRepository;

    @GetMapping("/animals")
    public String animals(Model model) {
        var animals = animalRepository.findAll();
        model.addAttribute("animals", animals);
        model.addAttribute("ageFrom", 1);
        model.addAttribute("ageTo", animalRepository.findFirstByOrderByAgeDesc().getAge());
        model.addAttribute("ageMax", animalRepository.findFirstByOrderByAgeDesc().getAge());

        return "animals";
    }


    @GetMapping("/filter")
    public String animalsFilter(Model model, @ModelAttribute("ageFrom") Integer ageFrom, @ModelAttribute("ageTo") Integer ageTo, @ModelAttribute("type") String type) {
        var animalsFilteredAge = animalRepository.findByAgeGreaterThanEqualAndAgeLessThanEqual(ageFrom, ageTo);
        List<Animal> animals = new ArrayList<Animal>();

        if (type.equals("ALL"))
            animals = animalsFilteredAge;
        else {
            var animalsFilteredType = animalRepository.findByType(AnimalType.valueOf(type));
            for(Animal animal: animalsFilteredType){
                if (animalsFilteredAge.contains(animal)){
                    animals.add(animal);
                }
            }
        }
        model.addAttribute("animals", animals);
        model.addAttribute("ageFrom", ageFrom);
        model.addAttribute("ageTo", ageTo);
        model.addAttribute("ageMax", animalRepository.findFirstByOrderByAgeDesc().getAge());

        return "animals";
    }
}
