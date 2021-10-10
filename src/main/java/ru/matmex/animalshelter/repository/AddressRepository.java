package ru.matmex.animalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.matmex.animalshelter.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findById(long id);

    Address getOne(long id);
}
