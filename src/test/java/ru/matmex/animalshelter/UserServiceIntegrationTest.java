package ru.matmex.animalshelter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import ru.matmex.animalshelter.model.User;
import ru.matmex.animalshelter.repository.RoleRepository;
import ru.matmex.animalshelter.repository.UserRepository;
import ru.matmex.animalshelter.service.UserService;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class UserServiceIntegrationTest {

    @TestConfiguration
    static class UserServiceTestContextConfiguration {

        @Bean
        public UserService userService() {
            return new UserService();
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private RoleRepository roleRepository;
    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Before
    public void setUp() {
        User sandy = new User();
        sandy.setUsername("Sandy");

        User alex = new User();
        alex.setUsername("Alex");

        Mockito.when(userRepository.findByUsername("Sandy"))
                .thenReturn(sandy);
        Mockito.when(userRepository.findAll())
                .thenReturn(Arrays.asList(sandy, alex));
    }

    @Test
    public void whenValidName_thenUserShouldBeFound() {
        String name = "Sandy";
        UserDetails found = userService.loadUserByUsername(name);

        assertThat(found.getUsername()).isEqualTo(name);
    }

    @Test
    public void whenFindAll_thenReturnAllUsers() {
        User sandy = new User();
        sandy.setUsername("Sandy");

        User alex = new User();
        alex.setUsername("Alex");

        List<User> found = userService.allUsers();

        assertThat(found)
                .hasSize(2)
                .extracting(User::getUsername)
                .contains(alex.getUsername(), sandy.getUsername());
    }
}
