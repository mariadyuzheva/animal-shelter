package ru.matmex.animalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.matmex.animalshelter.model.Address;
import ru.matmex.animalshelter.model.Clinic;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {

    Clinic getOne(long id);
    Clinic getByClinicNameAndClinicAddressAndClinicPhone(String name, Address address, String phone);
}
