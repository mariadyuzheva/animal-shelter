package ru.matmex.animalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.matmex.animalshelter.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
