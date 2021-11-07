package ru.matmex.animalshelter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.matmex.animalshelter.model.Animal;
import ru.matmex.animalshelter.model.AnimalType;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Animal getOne(long id);
    List<Animal> findByAgeGreaterThanEqualAndAgeLessThanEqual(Integer ageFrom, Integer ageTo);
    List<Animal> findByType(AnimalType type);
    Animal findFirstByOrderByAgeDesc();
}
