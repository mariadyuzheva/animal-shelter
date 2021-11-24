package ru.matmex.animalshelter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import ru.matmex.animalshelter.model.Animal;
import ru.matmex.animalshelter.model.AnimalType;
import ru.matmex.animalshelter.repository.AnimalRepository;
import ru.matmex.animalshelter.service.UserService;
import ru.matmex.animalshelter.service.csv.AddressCSVService;
import ru.matmex.animalshelter.service.csv.AnimalCSVService;
import ru.matmex.animalshelter.service.csv.ClinicCSVService;
import ru.matmex.animalshelter.service.csv.CuratorCSVService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import({AddressCSVService.class,
        CuratorCSVService.class,
        ClinicCSVService.class,
        AnimalCSVService.class,
        UserService.class,
        BCryptPasswordEncoder.class})
public class AnimalRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private AnimalRepository animalRepository;

    @Test
    public void whenFindByType_thenListShouldContain() {
        Animal alex = new Animal(
                "Алекс", AnimalType.DOG, "дог", 3, 6, null, null, null);
        Animal sandy = new Animal(
                "Сэнди", AnimalType.CAT, "персидская", 2, 8, null, null, null);
        entityManager.persist(alex);
        entityManager.persist(sandy);

        List<Animal> found = animalRepository.findByType(AnimalType.DOG);

        assertThat(found).contains(alex);
        assertThat(found).doesNotContain(sandy);
    }

    @Test
    public void whenFindByAgeYears_thenListShouldContain() {
        Animal alex = new Animal(
                "Алекс", AnimalType.DOG, "дог", 3, 6, null, null, null);
        Animal sandy = new Animal(
                "Сэнди", AnimalType.CAT, "персидская", 5, 0, null, null, null);
        Animal kitty = new Animal(
                "Китти", AnimalType.CAT, "беспородная", 1, 3, null, null, null);
        entityManager.persist(alex);
        entityManager.persist(sandy);
        entityManager.persist(kitty);

        List<Animal> found = animalRepository
                .findByAgeYearsGreaterThanEqualAndAgeYearsLessThanEqual(2, 4);

        assertThat(found).contains(alex);
        assertThat(found).doesNotContain(sandy);
        assertThat(found).doesNotContain(kitty);
    }
}