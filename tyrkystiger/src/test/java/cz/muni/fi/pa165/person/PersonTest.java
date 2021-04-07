package cz.muni.fi.pa165.person;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.entity.Movie;
import cz.muni.fi.pa165.entity.Person;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.validation.ConstraintViolationException;

/**
 * FOR MILESTONE 1 EVALUATION
 * Unit tests for entity Person.
 *
 * @author Matej Turek
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class PersonTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    private Person simplePerson;
    private Person complexPerson;
    private Person emptyPerson;
    private Person namelessPerson;

    @BeforeMethod
    public void before() {
        simplePerson = new Person();
        simplePerson.setName("Simple person");

        Movie titanic = new Movie();
        titanic.setName("Titanic");
        titanic.setDescription("The ship sunk, DiCaprio died, very very sad story.");

        Movie shrek = new Movie();
        shrek.setName("Shrek");
        shrek.setDescription("Animated story about a green monster with a good heart.");

        complexPerson = new Person();
        complexPerson.setName("Complex person");
        complexPerson.setActor(true);
        complexPerson.setDirector(true);
        complexPerson.getDirectedMovies().add(titanic);
        complexPerson.getActorsMovies().add(shrek);

        emptyPerson = new Person();

        namelessPerson = new Person();
        namelessPerson.setActor(true);
        namelessPerson.setDirector(true);
        namelessPerson.getDirectedMovies().add(titanic);
        namelessPerson.getActorsMovies().add(shrek);
    }

    @Test
    public void simplePersonSaveTest() {
        EntityManager em = null;

        try {
            em = emf.createEntityManager();

            em.getTransaction().begin();

            em.persist(this.simplePerson);

            em.getTransaction().commit();

        } finally {
            if (em != null) em.close();
        }
    }

    @Test
    public void complexPersonSaveTest() {
        EntityManager em = null;

        try {
            em = emf.createEntityManager();

            em.getTransaction().begin();

            em.persist(complexPerson);

            em.getTransaction().commit();

        } finally {
            if (em != null) em.close();
        }
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void emptyPersonSaveTest() {
        EntityManager em = null;

        try {
            em = emf.createEntityManager();

            em.getTransaction().begin();

            em.persist(emptyPerson);

            em.getTransaction().commit();

        } finally {
            if (em != null) em.close();
        }
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void emptyNamePersonSaveTest() {
        EntityManager em = null;

        try {
            em = emf.createEntityManager();

            em.getTransaction().begin();

            em.persist(namelessPerson);

            em.getTransaction().commit();

        } finally {
            if (em != null) em.close();
        }
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void duplicitousNamePersonSaveTest() {
        EntityManager em = null;

        try {
            em = emf.createEntityManager();

            em.getTransaction().begin();

            em.persist(simplePerson);
            em.persist(simplePerson);

            em.getTransaction().commit();

        } finally {
            if (em != null) em.close();
        }
    }
}
