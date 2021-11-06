package ru.matmex.animalshelter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.matmex.animalshelter.model.*;
import ru.matmex.animalshelter.repository.*;
import ru.matmex.animalshelter.service.CSV.AddressCSVService;
import ru.matmex.animalshelter.service.CSV.AnimalCSVService;
import ru.matmex.animalshelter.service.CSV.ClinicCSVService;
import ru.matmex.animalshelter.service.CSV.CuratorCSVService;


@SpringBootApplication
public class AnimalShelterApplication {

	@Autowired
	AddressCSVService addressCSVService;
	@Autowired
	CuratorCSVService curatorCSVService;
	@Autowired
	ClinicCSVService clinicCSVService;
	@Autowired
	AnimalCSVService animalCSVService;

	private static final Logger log = LoggerFactory.getLogger(AnimalShelterApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AnimalShelterApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(RoleRepository roleRepository) {

		return (args) -> {
			try {
				addressCSVService.save("src/main/resources/static/csv/addresses.csv");
				clinicCSVService.save("src/main/resources/static/csv/clinics.csv");
				curatorCSVService.save("src/main/resources/static/csv/curators.csv");
				animalCSVService.save("src/main/resources/static/csv/animals.csv");
			}
			catch (Exception e) {
				log.error(e.getMessage());
			}

			roleRepository.save(new Role("ROLE_ADMIN"));
			roleRepository.save(new Role("ROLE_USER"));
		};
	}

}
