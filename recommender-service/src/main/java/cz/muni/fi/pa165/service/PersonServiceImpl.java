package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.PersonDao;
import cz.muni.fi.pa165.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Marek Petrovič
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonDao personDao;

    @Override
    public Person update(Person person) {
        return personDao.updatePerson(person);
    }
}
