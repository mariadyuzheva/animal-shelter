package ru.matmex.animalshelter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.matmex.animalshelter.model.Curator;

public interface CuratorRepository extends JpaRepository<Curator, Long> {

    List<Curator> findByName(String name);

    Curator findById(long id);

    Curator getOne(long id);
}
