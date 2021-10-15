package ru.matmex.animalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.matmex.animalshelter.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
