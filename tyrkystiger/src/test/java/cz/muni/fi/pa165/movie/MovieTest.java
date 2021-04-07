package cz.muni.fi.pa165.movie;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.entity.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Locale;

/**
 * Test for {@link Movie}
 *
 * @author Peter Mravec
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class MovieTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    private Movie movieFull;
    private Movie movieMinimal;
    private Movie movieNoName;
    private Movie movieNoDescription;
    private Movie movieEmpty;
    private Movie movieInFuture;

    private Image image1;
    private Person person1;
    private User user;


    /**
     * Sets special cases of Movie to be tested
     */
    @BeforeClass
    public void before(){
        this.user = new User();
        user.setNickName("user");
        user.setPasswordHash("dasd448984ve84");
        user.setEmail("user@mail.com");

        this.image1 = new Image();
        image1.setImage(new byte[]{1, 2, 3, 4, 5});
        image1.setImageMimeType("jpg");

        this.person1 = new Person();
        person1.setName("Leonardo Di Capriooo");

        this.movieFull = new Movie();
        movieFull.setName("Terminator");
        movieFull.setDescription("Movie full of action");
        movieFull.setCountryCode(Locale.IsoCountryCode.valueOf("USA"));
        movieFull.setLengthMin(144);
        movieFull.setYearMade(LocalDate.of(2002, Month.JANUARY,1));
        movieFull.setImageTitle(image1);
        movieFull.setDirector(person1);
        movieFull.addActor(person1);
        movieFull.addToGallery(image1);

        this.movieMinimal = new Movie();
        movieMinimal.setName("Shrek");
        movieMinimal.setDescription("Huge big green creature");

        this.movieNoName = new Movie();
        movieNoName.setDescription("Amaying movie");

        this.movieNoDescription = new Movie();
        movieNoDescription.setName("Good movie");

        this.movieEmpty = new Movie();

        this.movieInFuture = new Movie();
        movieInFuture.setName("Shrek 2");
        movieInFuture.setDescription("Huge big green creature continues");
        movieInFuture.setYearMade(LocalDate.of(9999, Month.JANUARY,1));

    }

    /**
     * Test simple setter and getter
     */
    @Test
    public void testSettersGetters(){
        Movie movie = new Movie();
        movie.setName("Fast and furious");
        Assert.assertEquals(movie.getName(), "Fast and furious");

        movie.setDescription("Something about love.");
        Assert.assertEquals(movie.getDescription(), "Something about love.");

        movie.setDirector(person1);
        Assert.assertEquals(movie.getDirector().getName(),person1.getName());

        movie.addActor(person1);
        Assert.assertEquals(movie.getActors().toArray()[0],person1.getName());

        movie.setYearMade(LocalDate.of(2002, Month.JANUARY,1));
        Assert.assertEquals(movie.getYearMade(),LocalDate.of(2002, Month.JANUARY,1));

        movie.setLengthMin(Integer.valueOf(150));
        Assert.assertEquals(movie.getLengthMin(),Integer.valueOf(150));

        movie.setImageTitle(image1);
        Assert.assertEquals(movie.getImageTitle(),image1);

        movie.addToGallery(image1);
        Assert.assertEquals(movie.getGallery().toArray()[0],image1);

        movie.setCountryCode("SVK");
        Assert.assertEquals(movie.getCountryCode(),"SVK");

        UserRating userRating = new UserRating();
        userRating.setUser(user);
        userRating.setMovie(movie);
        userRating.setActorScore(2);
        userRating.setOverallScore(5);
        userRating.setStoryScore(7);
        userRating.setVisualScore(6);

        movie.addUserRating(userRating);
        Assert.assertEquals(movie.getRatings(), userRating);

    }

    /**
     * Test if saves Movie with only needed attributes
     */
    @Test
    public void testSavesMinimal(){
        EntityManager entityManager = null;
        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            entityManager.persist(movieMinimal);

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

            Movie movieMiniFound = entityManager1.find(Movie.class,movieMinimal.getId());

            Assert.assertNotNull(movieMiniFound);
            Assert.assertEquals(movieMiniFound.getName(),"Shrek");
            Assert.assertEquals(movieMiniFound.getDescription(),"Huge big green creature");

            entityManager1.getTransaction().commit();
        }finally {
            if (entityManager1 != null){
                entityManager1.close();
            }
        }
    }

    /**
     * Test if saves empty Movie, it should not because of ContstraintViolation
     */
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testDoesntSaveEmpty(){
        EntityManager entityManager = null;
        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            entityManager.persist(movieEmpty);

            entityManager.getTransaction().commit();
        }finally {
            if (entityManager != null){
                entityManager.close();
            }
        }
    }

    /**
     * Test if saves Movie without name, it should not
     */
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testDoesntSaveNullName(){
        EntityManager entityManager = null;
        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            entityManager.persist(movieNoName);

            entityManager.getTransaction().commit();
        }finally {
            if (entityManager != null){
                entityManager.close();
            }
        }
    }

    /**
     * Test if saves Movie without description, it should not
     */
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testDoesntSaveNullDescription(){
        EntityManager entityManager = null;
        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            entityManager.persist(movieNoDescription);

            entityManager.getTransaction().commit();
        }finally {
            if (entityManager != null){
                entityManager.close();
            }
        }
    }

    /**
     * Test if saves Movie with year from future, it should not
     */
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testInvalidYear(){
        EntityManager entityManager = null;
        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            entityManager.persist(movieInFuture);

            entityManager.getTransaction().commit();
        }finally {
            if (entityManager != null){
                entityManager.close();
            }
        }
    }

}
