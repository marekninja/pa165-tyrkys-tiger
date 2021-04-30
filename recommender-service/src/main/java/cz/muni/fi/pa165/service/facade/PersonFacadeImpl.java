package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.PersonRealDTO;
import cz.muni.fi.pa165.entity.Person;
import cz.muni.fi.pa165.facade.PersonFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.PersonService;
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
    public void create(PersonRealDTO personRealDTO) {
        Person person = new Person();
        person.setName(person.getName());
        personService.create(person);
    }

    @Override
    public PersonRealDTO findById(Long id) {
        Person storedPerson = personService.findById(id);
        return (storedPerson == null) ? null :
                beanMappingService.mapTo(storedPerson, PersonRealDTO.class);
    }

    @Override
    public PersonRealDTO findByName(String name) {
        Person storedPerson = personService.findByName(name);
        return (storedPerson == null) ? null :
                beanMappingService.mapTo(storedPerson, PersonRealDTO.class);
    }

    @Override
    public List<PersonRealDTO> findAll() {
        return beanMappingService.mapTo(personService.findAll(), PersonRealDTO.class);
    }

    @Override
    public PersonRealDTO update(PersonRealDTO personRealDTO) {
        Person updated = personService.update(beanMappingService.mapTo(personRealDTO, Person.class));
        return beanMappingService.mapTo(updated, PersonRealDTO.class);
    }

    @Override
    public void delete(Long id) {
        Person person = new Person();
        person.setId(id);
        personService.delete(person);
    }
}
