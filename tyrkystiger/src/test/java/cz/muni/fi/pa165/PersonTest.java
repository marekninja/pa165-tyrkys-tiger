package cz.muni.fi.pa165;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * Unit tests for class Person
 *
 * @author Matej Turek
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class PersonTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    EntityManagerFactory emf;

    Person person;

    @BeforeClass
    public void init() {
        person = new Person();


    }
}
