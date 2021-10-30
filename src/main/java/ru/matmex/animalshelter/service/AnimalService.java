package ru.matmex.animalshelter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.matmex.animalshelter.model.Animal;
import ru.matmex.animalshelter.repository.AnimalRepository;


@Service
public class AnimalService {
    @Autowired
    AnimalRepository animalRepository;

    public boolean saveAnimal(Animal animal) {
        animalRepository.save(animal);
        return true;
    }

    public boolean deleteAnimal(Long animalId) {
        if (animalRepository.findById(animalId).isPresent()) {
            animalRepository.deleteById(animalId);
            return true;
        }
        return false;
    }
}
