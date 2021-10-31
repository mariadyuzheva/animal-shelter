package ru.matmex.animalshelter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.matmex.animalshelter.model.Address;
import ru.matmex.animalshelter.model.Animal;
import ru.matmex.animalshelter.model.Clinic;
import ru.matmex.animalshelter.model.Curator;
import ru.matmex.animalshelter.service.AnimalService;

import java.io.IOException;


@Controller
public class AdminController {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AnimalService animalService;

    @GetMapping("/admin/save")
    public String addAnimal(Model model) {
        model.addAttribute("animalForm", new Animal());
        return "saveAnimal";
    }

    @PostMapping("/admin/save")
    public String addAnimal(@ModelAttribute("animalForm") Animal animalForm,
                            @RequestParam("file") MultipartFile file,
                            @ModelAttribute("curator") Curator curator,
                            @ModelAttribute("clinic") Clinic clinic,
                            @ModelAttribute("address") Address address,
                            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "/admin/save";
        }
        if (file != null) {
            byte[] filecontent = null;
            try {
                filecontent = file.getBytes();
            } catch (IOException ex) {
                log.error("Error saving uploaded file");
            }
            animalForm.setImage(filecontent);
        }
        animalForm.setCurator(curator);
        clinic.setClinicAddress(address);
        animalForm.setClinic(clinic);
        animalService.saveAnimal(animalForm, curator, clinic, address);
        return "redirect:/animals";
    }
}
