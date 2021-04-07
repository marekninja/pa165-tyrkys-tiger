package cz.muni.fi.pa165.person;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.entity.Person;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
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

    @Test
    public void simplePersonSaveTest() {
        EntityManager em = null;
        Person simplePerson = new Person();

        try {
            em = emf.createEntityManager();

            em.getTransaction().begin();

            simplePerson.setName("Simple person");
            em.persist(simplePerson);

            em.getTransaction().commit();

        } finally {
            if (em != null) em.close();
        }

        EntityManager entityManager = null;
        try{
            entityManager = emf.createEntityManager();
            entityManager.getTransaction().begin();

            Person person = entityManager.find(Person.class, simplePerson.getId());

            Assert.assertNotNull(person);
            Assert.assertEquals(person.getName(),"Simple person");

            entityManager.getTransaction().commit();
        } finally {
            if (entityManager != null){
                entityManager.close();
            }
        }
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void emptyPersonSaveTest() {
        EntityManager em = null;

        try {
            em = emf.createEntityManager();

            em.getTransaction().begin();

            Person emptyPerson = new Person();
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

            Person namelessPerson = new Person();
            namelessPerson.setActor(true);
            namelessPerson.setDirector(true);
            em.persist(namelessPerson);

            em.getTransaction().commit();

        } finally {
            if (em != null) em.close();
        }
    }
}
