package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.PersonCreateDTO;
import cz.muni.fi.pa165.dto.PersonDTO;
import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.entity.Person;
import cz.muni.fi.pa165.facade.PersonFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.PersonService;
import cz.muni.fi.pa165.service.utils.Validator;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Peter Mravec
 */
@Service
@Transactional
public class PersonFacadeImpl implements PersonFacade {

    private final PersonService personService;

    private final BeanMappingService beanMappingService;

    @Inject
    public PersonFacadeImpl(PersonService personService, BeanMappingService mapping) {
        this.personService = personService;
        this.beanMappingService = mapping;
    }

    @Override
    public void create(PersonCreateDTO personCreateDTO) {
        Validator.validate(this.getClass(), personCreateDTO, "PersonCreateDTO cannot be null.");
        Person person = new Person();
        person.setName(personCreateDTO.getName());
        personService.create(person);
    }

    @Override
    public PersonDTO findById(Long id) {
        Validator.validate(this.getClass(), id, "ID cannot be null.");
        Person storedPerson = personService.findById(id);
        return (storedPerson == null) ? null :
                beanMappingService.mapTo(storedPerson, PersonDTO.class);
    }

    @Override
    public PersonDTO findByName(String name) {
        Validator.validate(this.getClass(), name, "Name cannot be null.");
        Person storedPerson = personService.findByName(name);
        return (storedPerson == null) ? null :
                beanMappingService.mapTo(storedPerson, PersonDTO.class);
    }

    @Override
    public List<PersonDTO> findAll() {
        return beanMappingService.mapTo(personService.findAll(), PersonDTO.class);
    }

    @Override
    public PersonDTO update(PersonDTO personDTO) {
        Validator.validate(this.getClass(), personDTO, "PersonDTO cannot be null.");
        Person updated = personService.update(beanMappingService.mapTo(personDTO, Person.class));
        return beanMappingService.mapTo(updated, PersonDTO.class);
    }

    @Override
    public void delete(Long id) {
        Validator.validate(this.getClass(), id, "ID cannot be null.");
        Person foundToBeDeleted = personService.findById(id);
        personService.delete(foundToBeDeleted);
    }
}
