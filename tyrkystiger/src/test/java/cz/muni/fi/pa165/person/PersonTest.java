package cz.muni.fi.pa165.person;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.entity.Movie;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.validation.ConstraintViolationException;

/**
 * Unit tests for entity Person.
 *
 * @author Matej Turek
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class PersonTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    private Movie titanic;
    private Movie shrek;

    private Person simplePerson;
    private Person complexPerson;
    private Person emptyPerson;
    private Person namelessPerson;

    @BeforeClass
    public void before() {
        simplePerson = new Person();
        simplePerson.setName("Simple person");

        titanic = new Movie();
        titanic.setName("Titanic");
        titanic.setDescription("The ship sunk, DiCaprio died, very very sad story.");

        shrek = new Movie();
        shrek.setName("Shrek");
        shrek.setDescription("Animated story about a green monster with a good heart.");

        complexPerson = new Person();
        complexPerson.setName("Complex person");
        complexPerson.setActor(true);
        complexPerson.SetDirector(true);
        complexPerson.getDirectedMovies().add(titanic);
        complexPerson.getActedMovies().add(shrek);

        emptyPerson = new Person();

        namelessPerson = new Person();
        namelessPerson.setActor(true);
        namelessPerson.SetDirector(true);
        namelessPerson.getDirectedMovies().add(titanic);
        namelessPerson.getActedMovies().add(shrek);
    }

    @Test
    public void SimplePersonSaveTest() {
        EntityManager em = null;

        try {
            em = emf.createEntityManager();

            em.getTransaction().begin();

            em.persist(simplePerson);

            em.getTransaction().commit();

        } finally {
            if (em != null) em.close();
        }
    }

    @Test
    public void ComplexPersonSaveTest() {
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
    public void EmptyPersonSaveTest() {
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
    public void EmptyPersonSaveTest() {
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
}
