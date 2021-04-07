package cz.muni.fi.pa165.userrating;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;

/**
 * Unit tests for UserRating.
 *
 * @author Matej Turek
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@Transactional
public class UserRatingDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    EntityManager emf;

    @Inject
    UserRatingDao userRatingDao;

    @BeforeClass
    public void beforeClass() {
        userRatingDao = new UserRatingDao();


    }

    @BeforeMethod
    public void beforeMethod() {

    }

    @Test
    public void findByIdTest() {

    }

    @Test
    public void findByUserTest() {

    }

    @Test
    public void findByMovieTest() {

    }

    @Test
    public void findAllTest() {

    }

    @Test
    public void createUserRatingTest() {

    }

    @Test
    public void updateUserRatingTest() {

    }

    @Test
    public void deleteUserRatingTest() {

    }
}
