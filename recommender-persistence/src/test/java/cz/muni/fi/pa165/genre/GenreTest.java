package cz.muni.fi.pa165.genre;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.entity.Genre;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.Month;


/**
 * Test for {@link Genre}
 *
 * @author Marek Petrovič
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GenreTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    private Genre GenreFull;
    private Genre GenreNull;


    /**
     * Sets special cases of Genre to be tested
     */
    @BeforeMethod
    public void before(){
        this.GenreFull = new Genre();
        GenreFull.setName("Komedia pre celu rodinu");
        this.GenreNull = new Genre();
        GenreNull.setName(null);
    }

    /**
     * Test if saves Genre with only needed atributes
     */
    @Test
    public void testSavesFull(){
        EntityManager entityManager = null;
        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            entityManager.persist(GenreFull);

            entityManager.getTransaction().commit();
        }finally {
            if (entityManager != null){
                entityManager.close();
            }
        }

        EntityManager entityManager1 = null;
        try{
            entityManager1 = entityManagerFactory.createEntityManager();
            entityManager1.getTransaction().begin();

            Genre found = entityManager1.find(Genre.class,GenreFull.getId());

            Assert.assertNotNull(found);
            Assert.assertEquals(found.getName(),"Komedia pre celu rodinu");

            entityManager1.getTransaction().commit();
        }finally {
            if (entityManager1 != null){
                entityManager1.close();
            }
        }
    }

    /**
     * Test if saves null Genre
     */
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testDoesntSaveNull(){
        EntityManager entityManager = null;
        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            entityManager.persist(GenreNull);

            entityManager.getTransaction().commit();
        }finally {
            if (entityManager != null){
                entityManager.close();
            }
        }
    }

    //TODO check why doesn't work
//    /**
//     * Test if saves Genre with same nickname as already saved, it should not
//     */
//    @Test(expectedExceptions = ConstraintViolationException.class)
//    public void testDoesntSaveNotUnique(){
//
//        Genre genre = new Genre();
//        genre.setName("srandy");
//
//        EntityManager entityManager = null;
//        try{
//            entityManager = entityManagerFactory.createEntityManager();
//            entityManager.getTransaction().begin();
//
//            entityManager.persist(genre);
//
//            entityManager.getTransaction().commit();
//        }finally {
//            if (entityManager != null){
//                entityManager.close();
//            }
//        }
//
//        Genre duplicite = new Genre();
//        duplicite.setName("srandy");
//        Assert.assertEquals(duplicite.getName(), genre.getName());
//
//        EntityManager entityManager1 = null;
//        try{
//            entityManager1 = entityManagerFactory.createEntityManager();
//            entityManager1.getTransaction().begin();
//
//            entityManager1.persist(duplicite);
//
//            entityManager1.getTransaction().commit();
//        }finally {
//            if (entityManager1 != null){
//                entityManager1.close();
//            }
//        }
//    }
}
