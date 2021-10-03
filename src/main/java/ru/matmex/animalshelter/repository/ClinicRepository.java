package ru.matmex.animalshelter.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import ru.matmex.animalshelter.model.Clinic;

public interface ClinicRepository extends CrudRepository<Clinic, Long> {

    List<Clinic> findByName(String name);

    Clinic findById(long id);
}
