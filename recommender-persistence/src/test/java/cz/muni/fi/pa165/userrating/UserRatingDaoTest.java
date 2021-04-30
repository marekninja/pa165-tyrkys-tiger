package cz.muni.fi.pa165.userrating;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.dao.GenreDao;
import cz.muni.fi.pa165.dao.MovieDao;
import cz.muni.fi.pa165.dao.UserDao;
import cz.muni.fi.pa165.dao.UserRatingDao;
import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.entity.Movie;
import cz.muni.fi.pa165.entity.User;
import cz.muni.fi.pa165.entity.UserRating;
import cz.muni.fi.pa165.jpql.GenreAndRating;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.testng.AssertJUnit.*;

/**
 * Unit tests for UserRating.
 *
 * @author Matej Turek
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)  // Calls RollBack after each test
@Transactional
public class UserRatingDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserRatingDao userRatingDao;

    @Inject
    private MovieDao movieDao;

    @Inject
    private UserDao userDao;

    @Inject
    private GenreDao genreDao;

    private Movie movie;
    private User jozko;
    private User jurko;
    private UserRating rating;
    private UserRating anotherRating;

    @BeforeMethod
    public void beforeMethod() {
        movie = new Movie();
        movie.setName("Ten thousand saints");
        movie.setDescription("Film about unexpected live situations.");

        jozko = User.builder()
                .nickName("JožiVaja")
                .passwordHash("H4$hov4ni3")
                .name("Jožko Vajda")
                .email("123@mail.muni.cz")
                .build();

        jurko = User.builder()
                .nickName("Jurko123")
                .passwordHash("H4$hov4ni3#ni3j3l4hk3")
                .name("Juraj Čiara")
                .email("juraj.ciara@mail.muni.cz")
                .isAdministrator(true)
                .dateOfBirth(LocalDate.of(1999, Month.APRIL, 28))
                .build();

        em.persist(movie);
        em.persist(jozko);
        em.persist(jurko);

        rating = new UserRating();
        rating.setMovie(movie);
        rating.setUser(jozko);
        rating.setStoryScore(9);
        rating.setVisualScore(9);
        rating.setActorScore(9);
        rating.setOverallScore(9);

        anotherRating = new UserRating();
        anotherRating.setMovie(movie);
        anotherRating.setUser(jurko);
        anotherRating.setStoryScore(8);
        anotherRating.setVisualScore(9);
        anotherRating.setActorScore(7);
        anotherRating.setOverallScore(8);
    }

    @Test
    public void createValid_UserRatingTest() {
        userRatingDao.createUserRating(rating);
        UserRating stored = userRatingDao.findById(rating.getId());
        assertNotNull(stored);
        assertEquals(rating, stored);
    }
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNull_UserRatingTest() {
        userRatingDao.createUserRating(null);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createEmpty_UserRatingTest() {
        UserRating emptyRating = new UserRating();
        userRatingDao.createUserRating(emptyRating);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createNullMovie_UserRatingTest() {
        rating.setMovie(null);
        userRatingDao.createUserRating(rating);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createNullUser_UserRatingTest() {
        rating.setUser(null);
        userRatingDao.createUserRating(rating);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createNullStoryScore_UserRatingTest() {
        rating.setStoryScore(null);
        userRatingDao.createUserRating(rating);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createNegativeValueStoryScore_UserRatingTest() {
        rating.setStoryScore(-1);
        userRatingDao.createUserRating(rating);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createOutOfRangeValueStoryScore_UserRatingTest() {
        rating.setStoryScore(11);
        userRatingDao.createUserRating(rating);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createNullVisualScore_UserRatingTest() {
        rating.setVisualScore(null);
        userRatingDao.createUserRating(rating);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createNegativeValueVisualScore_UserRatingTest() {
        rating.setVisualScore(11);
        userRatingDao.createUserRating(rating);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createOutOfRangeValueVisualScore_UserRatingTest() {
        rating.setVisualScore(-1);
        userRatingDao.createUserRating(rating);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createNullActorScore_UserRatingTest() {
        rating.setActorScore(null);
        userRatingDao.createUserRating(rating);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createNegativeValueActorScore_UserRatingTest() {
        rating.setActorScore(-1);
        userRatingDao.createUserRating(rating);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createOutOfRangeValueActorScore_UserRatingTest() {
        rating.setActorScore(11);
        userRatingDao.createUserRating(rating);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createNullOverallScore_UserRatingTest() {
        rating.setOverallScore(null);
        userRatingDao.createUserRating(rating);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createNegativeValueOverallScore_UserRatingTest() {
        rating.setOverallScore(-1);
        userRatingDao.createUserRating(rating);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createOutOfRangeValueOverallScore_UserRatingTest() {
        rating.setOverallScore(11);
        userRatingDao.createUserRating(rating);
    }

    @Test
    public void findByIdTest() {
        userRatingDao.createUserRating(rating);
        UserRating returned = userRatingDao.findById(rating.getId());

        assertNotNull(returned);
        assertEquals(rating, returned);
        assertEquals(movie, returned.getMovie());
        assertEquals(jozko, returned.getUser());
        assertEquals(rating.getStoryScore(), returned.getStoryScore());
        assertEquals(rating.getVisualScore(), returned.getStoryScore());
        assertEquals(rating.getActorScore(), returned.getActorScore());
        assertEquals(rating.getOverallScore(), returned.getOverallScore());
    }

    @Test
    public void findByUserTest() {
        userRatingDao.createUserRating(rating);
        List<UserRating> returnedRatings = userRatingDao.findByUser(jozko);

        assertNotNull(returnedRatings);
        assertFalse(returnedRatings.isEmpty());
        assertEquals(1, returnedRatings.size());

        UserRating returned = returnedRatings.get(0);
        assertNotNull(returned);
        assertEquals(rating, returned);
        assertEquals(movie, returned.getMovie());
        assertEquals(jozko, returned.getUser());
        assertEquals(rating.getStoryScore(), returned.getStoryScore());
        assertEquals(rating.getVisualScore(), returned.getStoryScore());
        assertEquals(rating.getActorScore(), returned.getActorScore());
        assertEquals(rating.getOverallScore(), returned.getOverallScore());
    }

    @Test
    public void findByMovieTest() {
        userRatingDao.createUserRating(rating);
        List<UserRating> returnedRatings = userRatingDao.findByMovie(movie);

        assertNotNull(returnedRatings);
        assertFalse(returnedRatings.isEmpty());
        assertEquals(1, returnedRatings.size());

        UserRating returned = returnedRatings.get(0);
        assertNotNull(returned);
        assertEquals(rating, returned);
        assertEquals(movie, returned.getMovie());
        assertEquals(jozko, returned.getUser());
        assertEquals(rating.getStoryScore(), returned.getStoryScore());
        assertEquals(rating.getVisualScore(), returned.getStoryScore());
        assertEquals(rating.getActorScore(), returned.getActorScore());
        assertEquals(rating.getOverallScore(), returned.getOverallScore());
    }

    @Test
    public void findAllTest() {
        userRatingDao.createUserRating(rating);
        userRatingDao.createUserRating(anotherRating);
        List<UserRating> returnedRatings = userRatingDao.findAll();

        assertNotNull(returnedRatings);
        assertFalse(returnedRatings.isEmpty());
        assertEquals(2, returnedRatings.size());
    }

    @Test
    public void updateUserRatingTest() {
        userRatingDao.createUserRating(rating);
        rating.setActorScore(5);
        userRatingDao.updateUserRating(rating);
        UserRating updatedRating = userRatingDao.findById(rating.getId());
        assertEquals(5, updatedRating.getActorScore().intValue());
    }

    @Test
    public void deleteUserRatingTest() {
        userRatingDao.createUserRating(rating);
        userRatingDao.createUserRating(anotherRating);
        assertEquals(2, userRatingDao.findAll().size());
        userRatingDao.deleteUserRating(rating);
        assertEquals(1, userRatingDao.findAll().size());
    }

    /**
     * usage of other DAOs is needed
     * @author Marek Petrovič
     */
    @Test
    public void findAggregateByGenreForUser(){
        Genre genre = new Genre();
        genre.setName("velmi akcny film");
        genreDao.createGenre(genre);

        Movie movie1 = new Movie();
        movie1.setName("Ten thousand saints");
        movie1.setDescription("Film about unexpected live situations.");
        movie1.addGenre(genre);
        movieDao.create(movie1);

        UserRating anotherRating1 = new UserRating();
        anotherRating1.setStoryScore(5);
        anotherRating1.setVisualScore(5);
        anotherRating1.setActorScore(5);
        anotherRating1.setOverallScore(5);
        anotherRating1.setUser(jurko);
        anotherRating1.setMovie(movie1);
        userRatingDao.createUserRating(anotherRating1);
        movie1.addUserRating(anotherRating1);
        movieDao.update(movie1);

        List<GenreAndRating> genreAndRatings = userRatingDao.findAggregateByGenreForUser(jurko);
        Assert.assertNotNull(genreAndRatings);
        Assert.assertEquals(genreAndRatings.size(),1);
        Assert.assertEquals(genreAndRatings.get(0).getGenre(),genre);
        Assert.assertEquals(genreAndRatings.get(0).getOverallScore().floatValue(),5f);
    }

    /**
     * usage of other DAOs is needed
     * @author Marek Petrovič
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findAggregateByGenreForUserNull() {
        List<GenreAndRating> genreAndRatings = userRatingDao.findAggregateByGenreForUser(null);
    }

    /**
     * usage of other DAOs is needed
     * @author Marek Petrovič
     */
    @Test
    public void findAggregateByGenreForUserNonExist() {
        User milanko = User.builder()
                .nickName("Srandista")
                .passwordHash("dsadfgsfdsaf")
                .name("Milanko Háčik")
                .email("dsafaasffdas@mail.muni.cz")
                .build();

        userDao.createUser(milanko);

        List<GenreAndRating> genreAndRatings = userRatingDao.findAggregateByGenreForUser(milanko);
        Assert.assertNotNull(genreAndRatings);
        Assert.assertEquals(genreAndRatings.size(),0);
    }

}
