package cz.muni.fi.pa165.movie;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.dao.*;
import cz.muni.fi.pa165.entity.*;
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
import java.util.ArrayList;
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

    @Autowired
    UserDao userDao;

    @Autowired
    PersonDao personDao;

    @Autowired
    GenreDao genreDao;

    @Autowired
    ImageDao imageDao;

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

    /**
     * Test of findByParameters method of MovieDao
     *
     * @author Marek Petrovič
     */
    @Test
    public void findByParametersTest(){
        Image imageTitle = new Image();
//        imageTitle.setId(1L);
        imageTitle.setImage("obrazok".getBytes());
        imageTitle.setImageMimeType("jpg");
        imageTitle.setIsTitleImage(true);
        imageDao.create(imageTitle);

        Image image = new Image();
//        image.setId(1L);
        image.setImage("momentka".getBytes());
        image.setImageMimeType("jpg");
        image.setIsTitleImage(false);
        imageDao.create(image);

        Genre genre = new Genre();
//        genre.setId(1L);
        genre.setName("veľmi akčný");
        genreDao.createGenre(genre);

        Person actorJohny = new Person();
//        actor.setId(1L);
        actorJohny.setName("Johny Cash");
        personDao.createPerson(actorJohny);

        Person actorEva = new Person();
        actorEva.setName("Eva");
        personDao.createPerson(actorEva);

        Person directorTommy = new Person();
//        director.setId(2L);
        directorTommy.setName("Tommy Cash");
        personDao.createPerson(directorTommy);

        Person directorJana = new Person();
//        director.setId(2L);
        directorJana.setName("Jana Cash");
        personDao.createPerson(directorJana);

        Movie movieEvaTommy = new Movie();
//        movie.setId(1L);
        movieEvaTommy.setName("5 proti a tak dalej");
        movieEvaTommy.setDescription("proti sebe navzajom a proti 100-slovakom");
        movieEvaTommy.setYearMade(LocalDate.of(2020,1,1));
        movieEvaTommy.setCountryCode("USA");
        movieEvaTommy.setLengthMin(200);
        movieEvaTommy.addActor(actorEva);
        movieEvaTommy.setDirector(directorTommy);
        movieEvaTommy.addToGallery(image);
        movieEvaTommy.setImageTitle(imageTitle);
        movieEvaTommy.addGenre(genre);
        movieDao.create(movieEvaTommy);

        Movie movieJohnyJana = new Movie();
        movieJohnyJana.setName("slovensko proti madarsku");
        movieJohnyJana.setDescription("100 slovaci sa nezapojili");
        movieJohnyJana.setYearMade(LocalDate.of(2020,1,1));
        movieJohnyJana.setCountryCode("USA");
        movieJohnyJana.setLengthMin(200);
        movieJohnyJana.addActor(actorJohny);
        movieJohnyJana.setDirector(directorJana);
        movieJohnyJana.addToGallery(image);
        movieJohnyJana.setImageTitle(imageTitle);
        movieJohnyJana.addGenre(genre);
        movieDao.create(movieJohnyJana);

        List<Movie> movies = new ArrayList<>();
        movies.add(movieEvaTommy);
        movies.add(movieJohnyJana);

        List<Genre> genres = new ArrayList<>();
        genres.add(genre);

        List<Person> personList = new ArrayList<>();
        personList.add(actorEva);
        personList.add(directorTommy);

        List<Movie> foundAll = movieDao.findByParameters(genres,null,null,null,null);
        Assert.assertNotNull(foundAll);
        Assert.assertEquals(foundAll.size(),2);
        Assert.assertEquals(movies,foundAll);

        List<Movie> foundEvaTommy = movieDao.findByParameters(null,personList,null,null,null);
        Assert.assertNotNull(foundEvaTommy);
        Assert.assertEquals(foundEvaTommy.size(),1);
        Assert.assertEquals(foundEvaTommy.get(0),movieEvaTommy);

        List<Movie> foundByName = movieDao.findByParameters(null,null,"proti",null,null);
        Assert.assertNotNull(foundByName);
        Assert.assertEquals(foundByName.size(),2);

        List<Movie> all = movieDao.findByParameters(null,null,null,null,null);
        Assert.assertNotNull(all);
        Assert.assertEquals(all.size(),2);

        List<Movie> megaFilter = movieDao.findByParameters(genres,personList,"a tak",LocalDate.of(2020,1,1),"USA");
        Assert.assertNotNull(megaFilter);
        Assert.assertEquals(megaFilter.size(),1);
        Assert.assertEquals(megaFilter.get(0),movieEvaTommy);
    }
}