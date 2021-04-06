package cz.muni.fi.pa165;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * Unit tests for entity UserRating
 *
 * @author Matej Turek
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class UserRatingTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    EntityManagerFactory emf;

    UserRating userRating;

    @BeforeClass
    public void init() {
        userRating = new UserRating();
    }

    @Test
    public void Test() {

    }
}
