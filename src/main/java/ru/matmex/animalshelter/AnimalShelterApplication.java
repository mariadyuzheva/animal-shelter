package ru.matmex.animalshelter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.matmex.animalshelter.model.Animal;
import ru.matmex.animalshelter.repository.AnimalRepository;

@SpringBootApplication
public class AnimalShelterApplication {

	private static final Logger log = LoggerFactory.getLogger(AnimalShelterApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AnimalShelterApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(AnimalRepository repository) {
		return (args) -> {
			// save a few animals
			repository.save(new Animal("Jack", "dog", 2));
			repository.save(new Animal("Chloe", "cat", 5));
			repository.save(new Animal("Kim", "dog", 3));
			repository.save(new Animal("David", "dog", 1));
			repository.save(new Animal("Michelle", "cat", 6));

			// fetch all animals
			log.info("Animals found with findAll():");
			log.info("-------------------------------");
			for (Animal animal : repository.findAll()) {
				log.info(animal.toString());
			}
			log.info("");

			// fetch an individual animal by ID
			Animal animal = repository.findById(1L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(animal.toString());
			log.info("");

			// fetch animals by name
			log.info("Animal found with findByName('Kim'):");
			log.info("--------------------------------------------");
			repository.findByName("Kim").forEach(bauer -> {
				log.info(bauer.toString());
			});
			// for (Animal bauer : repository.findByLastName("Bauer")) {
			//  log.info(bauer.toString());
			// }
			log.info("");
		};
	}

}
