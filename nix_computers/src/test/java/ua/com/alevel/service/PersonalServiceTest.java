package ua.com.alevel.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.alevel.persistence.entity.user.Personal;
import ua.com.alevel.persistence.repository.user.PersonalRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonalServiceTest {

    @Autowired
    private PersonalService personalService;

    @Before
    public void setUp() {
        Personal testPersonal = new Personal();
        testPersonal.setEmail("test@mail");

        Mockito.when(personalService.findByName(testPersonal.getEmail()))
                .thenReturn(testPersonal.getId());
    }

    @Test
    public void whenValidName_thenPersonalShouldBeFound() {
        String email = "test@mail";
        Long personalId = personalService.findByName(email);
        Personal testPersonal = personalService.findById(personalId).get();

        assertThat(testPersonal.getEmail())
                .isEqualTo(email);
    }
}
