package ru.matmex.animalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.matmex.animalshelter.model.Curator;

public interface CuratorRepository extends JpaRepository<Curator, Long> {

    Curator getOne(long id);
    Curator getByCuratorNameAndCuratorPhone(String name, String phone);
}
