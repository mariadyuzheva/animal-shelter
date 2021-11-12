package ru.matmex.animalshelter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.matmex.animalshelter.model.Animal;
import ru.matmex.animalshelter.model.AnimalType;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Animal getOne(long id);
    List<Animal> findByAgeYearsGreaterThanEqualAndAgeYearsLessThanEqual(Integer ageFrom, Integer ageTo);
    List<Animal> findByType(AnimalType type);
    Animal findFirstByOrderByAgeYearsDesc();
}
