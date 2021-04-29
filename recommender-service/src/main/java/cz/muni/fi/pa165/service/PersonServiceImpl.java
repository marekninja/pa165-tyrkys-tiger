package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.PersonDao;
import cz.muni.fi.pa165.entity.Person;
import cz.muni.fi.pa165.service.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Peter Mravec
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonDao personDao;

    @Override
    public Person update(Person person) {
        Validator.validate(this.getClass(), person, "Person cannot be null.");
        return personDao.updatePerson(person);
    }

    @Override
    public void create(Person person) {
        Validator.validate(this.getClass(), person, "Person cannot be null.");
        personDao.createPerson(person);
    }

    @Override
    public Person findById(Long id) {
        Validator.validate(this.getClass(), id, "Person id cannot be null.");
        return personDao.findById(id);
    }

    @Override
    public Person findByName(String name) {
        Validator.validate(this.getClass(), name, "Person name cannot be null.");
        return personDao.findByName(name);
    }

    @Override
    public List<Person> findAll() {
        return personDao.findAll();
    }

    @Override
    public void delete(Person person) {
        Validator.validate(this.getClass(), person, "Person cannot be null.");
        personDao.deletePerson(person);
    }
}
