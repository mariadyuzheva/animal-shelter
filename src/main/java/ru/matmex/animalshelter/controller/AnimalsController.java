package ru.matmex.animalshelter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.matmex.animalshelter.model.*;
import ru.matmex.animalshelter.repository.AnimalRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AnimalsController {
    @Autowired
    private AnimalRepository animalRepository;

    @GetMapping("/")
    public String animals(Model model) {
        var animals = animalRepository.findAll();
        model.addAttribute("animals", animals);
        model.addAttribute("ageFrom", 0);
        model.addAttribute("ageTo", animalRepository.findFirstByOrderByAgeYearsDesc().getAgeYears());
        model.addAttribute("ageMax", animalRepository.findFirstByOrderByAgeYearsDesc().getAgeYears());
        model.addAttribute("isCheckedCat", true);
        model.addAttribute("isCheckedDog", true);
        model.addAttribute("isCheckedOther", true);

        return "animals";
    }


    @GetMapping("/filter")
    public String animalsFilter(Model model, @ModelAttribute("ageFrom") Integer ageFrom, @ModelAttribute("ageTo") Integer ageTo, @ModelAttribute("type") String type,
                                @ModelAttribute("cat") String cat, @ModelAttribute("dog") String dog, @ModelAttribute("other") String other) {
        var animalsFilteredAge = animalRepository.findByAgeYearsGreaterThanEqualAndAgeYearsLessThanEqual(ageFrom, ageTo);
        List<Animal> animals = new ArrayList<Animal>();

        var animalsFilteredType = new ArrayList<Animal>();
        if (cat.equals("on")) {
            animalsFilteredType.addAll( animalRepository.findByType(AnimalType.valueOf("CAT")));
        }
        if (dog.equals("on")) {
            animalsFilteredType.addAll( animalRepository.findByType(AnimalType.valueOf("DOG")));
        }
        if (other.equals("on")) {
            animalsFilteredType.addAll( animalRepository.findByType(AnimalType.valueOf("OTHER")));
        }

        for(Animal animal: animalsFilteredType){
            if (animalsFilteredAge.contains(animal)){
                animals.add(animal);
            }
        }

        model.addAttribute("animals", animals);
        model.addAttribute("ageFrom", ageFrom);
        model.addAttribute("ageTo", ageTo);
        model.addAttribute("ageMax", animalRepository.findFirstByOrderByAgeYearsDesc().getAgeYears());
        model.addAttribute("isCheckedCat", cat.equals("on"));
        model.addAttribute("isCheckedDog", dog.equals("on"));
        model.addAttribute("isCheckedOther", other.equals("on"));

        return "animals";
    }
}
