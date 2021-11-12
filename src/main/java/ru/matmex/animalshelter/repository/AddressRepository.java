package ru.matmex.animalshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.matmex.animalshelter.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Address getOne(long id);
    Address getByCityAndStreetAndHouse(String city, String street, String house);
}
