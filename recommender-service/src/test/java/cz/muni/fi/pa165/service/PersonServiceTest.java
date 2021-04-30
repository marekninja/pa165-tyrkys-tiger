package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.PersonDao;
import cz.muni.fi.pa165.entity.Person;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.service.exceptions.NullArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Peter Mravec
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private PersonDao personDaoMock;

    @Autowired
    @InjectMocks
    private PersonServiceImpl personService;

    private Person person_1;
    private Person person_2;

    @BeforeEach
    public void before(){
        this.person_1 = new Person();
        person_1.setId(1L);
        person_1.setName("Bolek Polívka");

        this.person_2 = new Person();
        person_2.setId(2L);
        person_2.setName("Jozef Kroner");
    }

    @Test
    public void findPersonByIdTest(){
        when(personDaoMock.findById(1L)).thenReturn(person_1);

        Person found = personService.findById(1L);

        verify(personDaoMock, Mockito.times(1)).findById(1L);

        Assertions.assertThat(found).isNotNull();
        Assertions.assertThat(found).usingRecursiveComparison().isEqualTo(person_1);
    }

    @Test
    public void findPersonByIdNullInputTest() {
        Assertions.assertThatThrownBy(() -> personService.findById(null)).isInstanceOf(NullArgumentException.class);
        Mockito.verify(personDaoMock, Mockito.times(0)).findById(null);
    }

    @Test
    public void findPersonByNameTest(){
        when(personDaoMock.findByName("Jozef Kroner")).thenReturn(person_2);

        Person found = personService.findByName("Jozef Kroner");

        verify(personDaoMock, Mockito.times(1)).findByName("Jozef Kroner");

        Assertions.assertThat(found).isNotNull();
        Assertions.assertThat(found).usingRecursiveComparison().isEqualTo(person_2);
    }

    @Test
    public void findPersonByNameNullInputTest() {
        Assertions.assertThatThrownBy(() -> personService.findByName(null)).isInstanceOf(NullArgumentException.class);
        Mockito.verify(personDaoMock, Mockito.times(0)).findByName(null);
    }

    @Test
    public void findPersonByIdNotStoredInDBTest() {
        when(personDaoMock.findById(6L)).thenReturn(null);

        Person found = personService.findById(6L);

        verify(personDaoMock, Mockito.times(1)).findById(6L);
        Assertions.assertThat(found).isNull();
    }

    @Test
    public void findPersonByNameNotStoredInDBTest() {
        when(personDaoMock.findByName("Kráľ Jelimán")).thenReturn(null);

        Person found = personService.findByName("Kráľ Jelimán");

        verify(personDaoMock, Mockito.times(1)).findByName("Kráľ Jelimán");
        Assertions.assertThat(found).isNull();
    }

    @Test
    public void createPersonTest(){
        personService.create(person_1);
        verify(personDaoMock, Mockito.times(1)).createPerson(person_1);
    }

    @Test
    public void createPersonByNullInputTest(){
        Assertions.assertThatThrownBy(() -> personService.create(null)).isInstanceOf(NullArgumentException.class);
        Mockito.verify(personDaoMock, Mockito.times(0)).createPerson(null);
    }

    @Test
    public void findAllPersonsTest(){
        when(personDaoMock.findAll()).thenReturn(Arrays.asList(person_1, person_2));

        List<Person> foundPersons = personService.findAll();

        verify(personDaoMock, Mockito.times(1)).findAll();

        Assertions.assertThat(foundPersons).isNotNull();
        Assertions.assertThat(foundPersons.size()).isEqualTo(2);
        Assertions.assertThat(foundPersons).contains(person_1);
        Assertions.assertThat(foundPersons).contains(person_2);
    }

    @Test
    public void updatePersonTest(){
        person_1.setName("Anne Hathaway");
        personService.update(person_1);

        Mockito.verify(personDaoMock, Mockito.times(1)).updatePerson(person_1);
    }

    @Test
    public void updatePersonByNullInputTest(){
        Assertions.assertThatThrownBy(() -> personService.update(null)).isInstanceOf(NullArgumentException.class);
        Mockito.verify(personDaoMock, Mockito.times(0)).updatePerson(null);
    }

    @Test
    public void deletePersonValidTest(){
        personService.delete(person_1);
        verify(personDaoMock, Mockito.times(1)).deletePerson(person_1);
    }

    @Test
    public void deletePersonByNullInputTest(){
        Assertions.assertThatThrownBy(() -> personService.delete(null)).isInstanceOf(NullArgumentException.class);
        Mockito.verify(personDaoMock, Mockito.times(0)).deletePerson(null);
    }
}
