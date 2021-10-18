package ru.matmex.animalshelter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.matmex.animalshelter.model.Clinic;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {

    Clinic getOne(long id);
}
