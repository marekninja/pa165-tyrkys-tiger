package cz.muni.fi.pa165.person;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.dao.PersonDao;
import cz.muni.fi.pa165.entity.Person;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.testng.AssertJUnit.*;

/**
 * Unit tests for PersonDao.
 *
 * @author Matej Turek
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PersonDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private PersonDao personDao;

    private Person actor, director;

    @BeforeMethod
    public void before() {
            actor = new Person();
            actor.setName("actor not director");
            actor.setActor(true);
            actor.setDirector(false);

            director = new Person();
            director.setName("director not actor");
            director.setActor(false);
            director.setDirector(true);
    }

    @Test
    public void createPersonTest() {
        Long personId = personDao.createPerson(actor);
        assertNotNull(personDao.findById(personId));
        assertEquals(actor, personDao.findById(personId));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullTest() {
        personDao.createPerson(null);
    }

    @Test
    public void findByIdTest() {
        Long personId = personDao.createPerson(actor);
        Person actorFound = personDao.findById(personId);
        assertNotNull(actorFound);
        assertEquals(actor, actorFound);
        assertEquals("actor not director", actorFound.getName());
        assertTrue(actorFound.isActor());
        assertFalse(actorFound.isDirector());
    }

    @Test
    public void findByNameTest() {
        personDao.createPerson(actor);
        Person actorFound = personDao.findByName(actor.getName());
        assertNotNull(actorFound);
        assertEquals(actor, actorFound);
        assertTrue(actorFound.isActor());
        assertFalse(actorFound.isDirector());
    }

    @Test
    public void findAllTest() {
        assertEquals(personDao.findAll().size(), 0);
        personDao.createPerson(actor);
        personDao.createPerson(director);
        assertEquals(personDao.findAll().size(), 2);
    }

    @Test
    public void updatePersonTest() {
        Long personId = personDao.createPerson(actor);
        Person actorFound = personDao.findById(personId);
        assertEquals(actor, actorFound);
        actorFound.setActor(false);
        personDao.updatePerson(actorFound);
        actorFound = personDao.findById(personId);
        assertFalse(actorFound.isActor());
    }

    @Test
    public void deletePersonTest() {
        assertEquals(personDao.findAll().size(), 0);
        Long directorId = personDao.createPerson(director);
        assertEquals(personDao.findAll().size(), 1);
        assertEquals(director, personDao.findById(directorId));
        personDao.deletePerson(director);
        assertNull(personDao.findById(directorId));
    }
}
