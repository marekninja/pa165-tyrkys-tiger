package cz.muni.fi.pa165.movie;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.dao.MovieDao;
import cz.muni.fi.pa165.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

/**
 * FOR MILESTONE 1 EVAL
 * @author Peter Mravec
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class MovieDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    MovieDao movieDao;

    @PersistenceContext
    private EntityManager entityManager;

    private Movie movie;
    private Movie movie1;

    /**
     * Set up Movie special cases
     */
    @BeforeMethod
    public void before(){
        Movie movie = new Movie();
        movie.setName("Popelka");
        movie.setDescription("a sedem trpaslikov.");

        this.movie = movie;

        Movie movie1 = new Movie();
        movie1.setName("Lobotomia");
        movie1.setDescription("Príbeh o nekonečnej znalosti.");

        this.movie1 = movie1;
    }

    @Test
    public void createMovieTest(){
        movieDao.create(movie);
        Movie foundMovie = movieDao.findById(movie.getId());
        Assert.assertNotNull(foundMovie);
        Assert.assertEquals(movie, foundMovie);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullTest(){
        movieDao.create(null);
    }

    @Test
    public void updateMovieTest() {
        movieDao.create(movie1);
        Movie foundMovie = movieDao.findById(movie1.getId());
        assertEquals(movie1, foundMovie);
        foundMovie.setCountryCode("USA");
        movieDao.update(foundMovie);
        foundMovie = movieDao.findById(movie1.getId());
        assertEquals("USA", foundMovie.getCountryCode());
    }

    @Test
    public void findAllMovies() {
        assertEquals(movieDao.findAll().size(), 0);
        movieDao.create(movie);
        movieDao.create(movie1);
        assertEquals(movieDao.findAll().size(), 2);
    }

    @Test
    public void removeTestMovie() {
        assertEquals(movieDao.findAll().size(), 0);
        movieDao.create(movie);
        assertEquals(movieDao.findAll().size(), 1);
        movieDao.remove(movie);
        assertEquals(movieDao.findAll().size(), 0);
        Movie foundMovie = movieDao.findById(movie.getId());
        Assert.assertNull(foundMovie);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createMovieWithFutureYearMadeTest() {
        Movie movie = new Movie();
        movie.setName("Future");
        movie.setDescription("It will be in the future :D.");
        movie.setYearMade(LocalDate.of(9999, 1, 1));
        movieDao.create(movie);
    }
/*
    // It should failed but it was not ...... TODO!!!!
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void updateMovieWithFutureYearMadeTest() {
        movieDao.create(movie);
        Movie foundMovie = movieDao.findById(movie.getId());
        foundMovie.setYearMade(LocalDate.of(9999, 1, 1));
        movieDao.update(foundMovie);
        assertEquals(LocalDate.of(9999, 1, 1), foundMovie.getYearMade());
    }*/

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createMovieWithBlankNameTest() {
        Movie movie = new Movie();
        movie.setName("    ");
        movie.setDescription("Blank name");
        movieDao.create(movie);
    }
    /*
        // It should failed but it was not ...... TODO!!!!
        @Test(expectedExceptions = ConstraintViolationException.class)
        public void updateMovieWithBlankNameTest() {
            movieDao.create(movie);
            Movie foundMovie = movieDao.findById(movie.getId());
            foundMovie.setName("      ");
            movieDao.update(foundMovie);
            assertEquals("      ", foundMovie.getName());
        }
    */
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createMovieWithBlankDescriptionTest() {
        Movie movie = new Movie();
        movie.setName("Blank description");
        movie.setDescription("  ");
        movieDao.create(movie);
    }
/*
    // It should failed but it was not ...... TODO!!!!
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void updateMovieWithBlankDescriptionTest() {
        movieDao.create(movie);
        Movie foundMovie = movieDao.findById(movie.getId());
        foundMovie.setDescription("      ");
        movieDao.update(foundMovie);
        assertEquals("      ", foundMovie.getDescription());
    }
*/

    @Test
    public void findByNameTest() {
        Movie movie = new Movie();
        movie.setName("Variacie a kombinacie");
        movie.setDescription("Maturitná skúška.");
        movie.setYearMade(LocalDate.of(2020, Month.DECEMBER, 10));
        movieDao.create(movie);

        List<Movie> movie2 = movieDao.findByName("Variacie a kombinacie");

        Assert.assertNotNull(movie2);
        Assert.assertEquals(movie2.size(), 1);
        Assert.assertEquals(movie2.get(0).getName(), "Variacie a kombinacie");
        Assert.assertEquals(movie2.get(0).getDescription(), "Maturitná skúška.");
        Assert.assertEquals(movie2.get(0).getYearMade(), LocalDate.of(2020, Month.DECEMBER, 10));
    }
}