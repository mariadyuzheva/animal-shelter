package ru.matmex.animalshelter.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import ru.matmex.animalshelter.model.Curator;

public interface CuratorRepository extends CrudRepository<Curator, Long> {

    List<Curator> findByName(String name);

    Curator findById(long id);
}
