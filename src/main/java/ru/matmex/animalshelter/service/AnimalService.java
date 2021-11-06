package ru.matmex.animalshelter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.matmex.animalshelter.model.Address;
import ru.matmex.animalshelter.model.Animal;
import ru.matmex.animalshelter.model.Clinic;
import ru.matmex.animalshelter.model.Curator;
import ru.matmex.animalshelter.repository.AddressRepository;
import ru.matmex.animalshelter.repository.AnimalRepository;
import ru.matmex.animalshelter.repository.ClinicRepository;
import ru.matmex.animalshelter.repository.CuratorRepository;


@Service
public class AnimalService {
    @Autowired
    AnimalRepository animalRepository;
    @Autowired
    CuratorRepository curatorRepository;
    @Autowired
    ClinicRepository clinicRepository;
    @Autowired
    AddressRepository addressRepository;

    public void saveAnimal(Animal animal, Curator curator, Clinic clinic, Address address) {
        curatorRepository.save(curator);
        addressRepository.save(address);
        clinicRepository.save(clinic);
        animalRepository.save(animal);
    }

    public boolean deleteAnimal(Long animalId) {
        if (animalRepository.findById(animalId).isPresent()) {
            animalRepository.deleteById(animalId);
            return true;
        }
        return false;
    }
}
