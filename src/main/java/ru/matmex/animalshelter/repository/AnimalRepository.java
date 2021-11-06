package ru.matmex.animalshelter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.matmex.animalshelter.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Animal getOne(long id);
}
