package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.PersonCreateDTO;
import cz.muni.fi.pa165.dto.PersonDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.entity.Person;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.PersonService;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.service.exceptions.NullArgumentException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import javax.inject.Inject;

import java.util.List;

import static org.mockito.Mockito.doThrow;

/**
 * @author Peter Mravec
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@ExtendWith(MockitoExtension.class)
public class PersonFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private PersonService personServiceMock;

    @Mock
    private BeanMappingService beanMappingService;

    @Inject
    @InjectMocks
    private PersonFacadeImpl personFacade;

    private Person person_1;
    private Person person_2;

    private PersonDTO person_1_DTO;
    private PersonDTO person_2_DTO;

    private PersonCreateDTO person_1_CreateDTO;
    private PersonCreateDTO person_2_CreateDTO;


    @BeforeEach
    public void before(){
        this.person_1 = new Person();
        person_1.setId(1L);
        person_1.setName("Jan Kroner");

        this.person_2 = new Person();
        person_2.setId(2L);
        person_2.setName("Bolek Polívka");

        this.person_1_DTO = new PersonDTO();
        person_1_DTO.setId(1L);
        person_1_DTO.setName("Jan Kroner");

        this.person_2_DTO = new PersonDTO();
        person_2_DTO.setId(2L);
        person_2_DTO.setName("Bolek Polívka");

        this.person_1_CreateDTO = new PersonCreateDTO();
        person_1_CreateDTO.setName("Jan Kroner");

        this.person_2_CreateDTO = new PersonCreateDTO();
        person_2_CreateDTO.setName("Bolek Polívka");
    }

    @Test
    public void createPersonValidTest() {
        personFacade.create(person_1_CreateDTO);
        Mockito.verify(personServiceMock, Mockito.times(1)).create(person_1);
    }

    @Test
    public void createPersonNullTest() {
        Assertions.assertThatThrownBy(() -> personFacade.create(null)).isInstanceOf(NullArgumentException.class);
        Mockito.verify(personServiceMock, Mockito.times(0)).create(null);
    }

    @Test
    public void findPersonByIdValidTest() {
        Mockito.when(personServiceMock.findById(1L)).thenReturn(person_1);
        Mockito.when(beanMappingService.mapTo(person_1, PersonDTO.class)).thenReturn(person_1_DTO);

        PersonDTO returned = personFacade.findById(1L);
        Mockito.verify(personServiceMock, Mockito.times(1)).findById(1L);
        Mockito.verify(beanMappingService, Mockito.times(1)).mapTo(person_1, PersonDTO.class);

        Assertions.assertThat(returned).isEqualTo(person_1_DTO);
    }

    @Test
    public void findPersonByIDNullIdTest() {
        Assertions.assertThatThrownBy(() -> personFacade.findById(null)).isInstanceOf(NullArgumentException.class);
        Mockito.verify(personServiceMock, Mockito.times(0)).findById(null);
    }

    @Test
    public void findPersonByNameValidTest() {
        Mockito.when(personServiceMock.findByName("Jan Kroner")).thenReturn(person_1);
        Mockito.when(beanMappingService.mapTo(person_1, PersonDTO.class)).thenReturn(person_1_DTO);

        PersonDTO returned = personFacade.findByName("Jan Kroner");
        Mockito.verify(personServiceMock, Mockito.times(1)).findByName("Jan Kroner");
        Mockito.verify(beanMappingService, Mockito.times(1)).mapTo(person_1, PersonDTO.class);

        Assertions.assertThat(returned).isEqualTo(person_1_DTO);
    }

    @Test
    public void findPersonByNameNullTest() {
        Assertions.assertThatThrownBy(() -> personFacade.findByName(null)).isInstanceOf(NullArgumentException.class);
        Mockito.verify(personServiceMock, Mockito.times(0)).findByName(null);
    }

    @Test
    public void findAllPersonsTest() {
        Mockito.when(personServiceMock.findAll()).thenReturn(List.of(person_1, person_2));
        Mockito.when(beanMappingService.mapTo(List.of(person_1, person_2), PersonDTO.class)).thenReturn(List.of(person_1_DTO, person_2_DTO));

        List<PersonDTO> returned = personFacade.findAll();
        Mockito.verify(personServiceMock, Mockito.times(1)).findAll();
        Mockito.verify(beanMappingService, Mockito.times(1)).mapTo(List.of(person_1, person_2), PersonDTO.class);

        Assertions.assertThat(returned).containsExactlyInAnyOrder(person_1_DTO, person_2_DTO);
    }

    @Test
    public void updatePersonValidTest() {
        person_1.setName("Kráľ Jelimán");
        Mockito.when(personServiceMock.update(person_1)).thenReturn(person_1);
        Mockito.when(beanMappingService.mapTo(person_1_DTO, Person.class)).thenReturn(person_1);
        Mockito.when(beanMappingService.mapTo(person_1, PersonDTO.class)).thenReturn(person_1_DTO);

        person_1_DTO.setName("Kráľ Jelimán");
        PersonDTO returned = personFacade.update(person_1_DTO);

        Mockito.verify(beanMappingService, Mockito.times(1)).mapTo(person_1_DTO, Person.class);
        Mockito.verify(beanMappingService, Mockito.times(1)).mapTo(person_1, PersonDTO.class);
        Mockito.verify(personServiceMock, Mockito.times(1)).update(person_1);

        Assertions.assertThat(returned).isEqualTo(person_1_DTO);
    }

    @Test
    public void updatePersonForNullTest() {
        Assertions.assertThatThrownBy(() -> personFacade.update(null)).isInstanceOf(NullArgumentException.class);
        Mockito.verify(personServiceMock, Mockito.times(0)).update(null);
    }

    @Test
    public void deletePersonValidTest() {
        Mockito.when(personServiceMock.findById(person_1_DTO.getId())).thenReturn(person_1);

        personFacade.delete(person_1_DTO.getId());

        Mockito.verify(personServiceMock, Mockito.times(1)).delete(person_1);
        Mockito.verify(personServiceMock, Mockito.times(1)).findById(person_1_DTO.getId());
    }

    @Test
    public void deletePersonForNullTest() {
        Assertions.assertThatThrownBy(() -> personFacade.delete(null)).isInstanceOf(NullArgumentException.class);
        Mockito.verify(personServiceMock, Mockito.times(0)).delete(null);
    }
}
