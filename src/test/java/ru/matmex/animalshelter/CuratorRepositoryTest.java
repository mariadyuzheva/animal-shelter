package ru.matmex.animalshelter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import ru.matmex.animalshelter.model.Curator;
import ru.matmex.animalshelter.repository.CuratorRepository;
import ru.matmex.animalshelter.service.UserService;
import ru.matmex.animalshelter.service.csv.AddressCSVService;
import ru.matmex.animalshelter.service.csv.AnimalCSVService;
import ru.matmex.animalshelter.service.csv.ClinicCSVService;
import ru.matmex.animalshelter.service.csv.CuratorCSVService;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import({AddressCSVService.class,
        CuratorCSVService.class,
        ClinicCSVService.class,
        AnimalCSVService.class,
        UserService.class,
        BCryptPasswordEncoder.class})
public class CuratorRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CuratorRepository curatorRepository;

    @Test
    public void whenFindByNameAndPhone_thenReturnCurator() {
        Curator alex = new Curator("Alex", "+79123334455");
        entityManager.persist(alex);

        Curator found = curatorRepository.getByCuratorNameAndCuratorPhone(
                alex.getCuratorName(), alex.getCuratorPhone());

        assertThat(found.getCuratorName())
                .isEqualTo(alex.getCuratorName());
        assertThat(found.getCuratorPhone())
                .isEqualTo(alex.getCuratorPhone());
    }

    @Test
    public void whenFindByInvalidNameAndPhone_thenReturnNull() {
        Curator alex = new Curator("Alex", "+79123334455");
        entityManager.persist(alex);

        Curator found = curatorRepository.getByCuratorNameAndCuratorPhone(
                "wrongName", alex.getCuratorPhone());

        assertThat(found).isNull();
    }
}
