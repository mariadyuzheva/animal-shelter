package ru.matmex.animalshelter.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import ru.matmex.animalshelter.model.Animal;

public interface AnimalRepository extends CrudRepository<Animal, Long> {

    List<Animal> findByName(String name);

    Animal findById(long id);
}
