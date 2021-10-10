package ru.matmex.animalshelter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.matmex.animalshelter.model.*;
import ru.matmex.animalshelter.repository.AddressRepository;
import ru.matmex.animalshelter.repository.AnimalRepository;
import ru.matmex.animalshelter.repository.ClinicRepository;
import ru.matmex.animalshelter.repository.CuratorRepository;

import java.nio.file.Files;
import java.nio.file.Paths;


@SpringBootApplication
public class AnimalShelterApplication {

	private static final Logger log = LoggerFactory.getLogger(AnimalShelterApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AnimalShelterApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(AnimalRepository animalRepository,
								  ClinicRepository clinicRepository,
								  CuratorRepository curatorRepository,
								  AddressRepository addressRepository) {
		return (args) -> {
			addressRepository.save(new Address("Екатеринбург", "Белинского", "120"));
			addressRepository.save(new Address("Екатеринбург", "Амундсена", "56"));

			clinicRepository.save(new Clinic("Зоодоктор", addressRepository.getOne(1L), "83432143716"));
			clinicRepository.save(new Clinic("Айболит", addressRepository.getOne(2L), "83433416529"));

			curatorRepository.save(new Curator("Екатерина", "89122548132"));
			curatorRepository.save(new Curator("Андрей", "89125932144"));

			byte[] image1 = Files.readAllBytes(Paths.get("src/main/resources/static/dog.jpg"));

			animalRepository.save(new Animal("Джек", AnimalType.DOG, "беспородный", 2,
					clinicRepository.getOne(3L), curatorRepository.getOne(5L), image1));
			animalRepository.save(new Animal("Хлоя", AnimalType.CAT, "сиамская", 5,
					clinicRepository.getOne(3L), curatorRepository.getOne(6L), image1));
			animalRepository.save(new Animal("Ким", AnimalType.DOG, "немецкая овчарка", 3,
					clinicRepository.getOne(4L), curatorRepository.getOne(5L), image1));
			animalRepository.save(new Animal("Джерри", AnimalType.DOG, "дог", 1,
					clinicRepository.getOne(4L), curatorRepository.getOne(5L), image1));
			animalRepository.save(new Animal("Мишель", AnimalType.CAT, "беспородная", 6,
					clinicRepository.getOne(3L), curatorRepository.getOne(6L), image1));

			log.info("Animals found with findAll():");
			log.info("-------------------------------");
			for (Animal animal : animalRepository.findAll()) {
				log.info(animal.toString());
			}
			log.info("");

			Animal animal = animalRepository.findById(7L);
			log.info("Animal found with findById(7L):");
			log.info("--------------------------------");
			log.info(animal.toString());
			log.info("");

			log.info("Animal found with findByName('Ким'):");
			log.info("--------------------------------------------");
			animalRepository.findByName("Ким").forEach(x -> {
				log.info(x.toString());
			});
			log.info("");
		};
	}

}
