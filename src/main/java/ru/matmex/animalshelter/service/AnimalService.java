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
        saveCurator(curator, animal);
        saveAddress(address, clinic);
        saveClinic(clinic, animal);
        animalRepository.save(animal);
    }

    private void saveCurator(Curator curator, Animal animal) {
        Curator savedCurator = curatorRepository.getByCuratorNameAndCuratorPhone(
                curator.getCuratorName(), curator.getCuratorPhone());
        if (savedCurator == null) {
            curatorRepository.save(curator);
        }
        else {
            animal.setCurator(savedCurator);
        }
    }

    private void saveAddress(Address address, Clinic clinic) {
        Address savedAddress = addressRepository.getByCityAndStreetAndHouse(
                address.getCity(), address.getStreet(), address.getHouse());
        if (savedAddress == null) {
            addressRepository.save(address);
        }
        else {
            clinic.setClinicAddress(savedAddress);
        }
    }

    private void saveClinic(Clinic clinic, Animal animal) {
        Clinic savedClinic = clinicRepository.getByClinicNameAndClinicAddressAndClinicPhone(
                clinic.getClinicName(), clinic.getClinicAddress(), clinic.getClinicPhone());
        if (savedClinic == null) {
            clinicRepository.save(clinic);
        }
        else {
            animal.setClinic(savedClinic);
        }
    }
}
