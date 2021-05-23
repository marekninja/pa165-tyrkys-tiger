package cz.muni.fi.pa165.movie;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.dao.*;
import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.jpql.MovieAndRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
    UserRatingDao userRatingDao;

    @Autowired
    GenreDao genreDao;

    @Autowired
    ImageDao imageDao;

    @PersistenceContext
    private EntityManager entityManager;

    private Movie movie;
    private Movie movie1;
    private Genre genre;
    private Person actorJohny;
    private Person actorEva;
    private Person directorTommy;
    private Person directorJana;
    private Movie movieEvaTommy;
    private Movie movieJohnyJana;
    private ArrayList<Movie> movies;
    private ArrayList<Genre> genres;
    private ArrayList<Person> personList;


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

        this.genre = new Genre();
        genre.setName("veľmi akčný");
        genreDao.createGenre(genre);

        this.actorJohny = new Person();
        actorJohny.setName("Johny Cash");
        personDao.createPerson(actorJohny);

        this.actorEva = new Person();
        actorEva.setName("Eva");
        personDao.createPerson(actorEva);

        this.directorTommy = new Person();
        directorTommy.setName("Tommy Cash");
        personDao.createPerson(directorTommy);

        this.directorJana = new Person();
        directorJana.setName("Jana Cash");
        personDao.createPerson(directorJana);

        this.movieEvaTommy = new Movie();
        movieEvaTommy.setName("5 proti a tak dalej");
        movieEvaTommy.setDescription("proti sebe navzajom a proti 100-slovakom");
        movieEvaTommy.setYearMade(LocalDate.of(2020,1,1));
        movieEvaTommy.setCountryCode("USA");
        movieEvaTommy.setLengthMin(200);
        movieEvaTommy.addActor(actorEva);
        movieEvaTommy.setDirector(directorTommy);
        movieEvaTommy.addGenre(genre);


        this.movieJohnyJana = new Movie();
        movieJohnyJana.setName("slovensko proti madarsku");
        movieJohnyJana.setDescription("100 slovaci sa nezapojili");
        movieJohnyJana.setYearMade(LocalDate.of(2020,1,1));
        movieJohnyJana.setCountryCode("USA");
        movieJohnyJana.setLengthMin(200);
        movieJohnyJana.addActor(actorJohny);
        movieJohnyJana.setDirector(directorJana);
        movieJohnyJana.addGenre(genre);


        this.movies = new ArrayList<>();
        movies.add(movieEvaTommy);
        movies.add(movieJohnyJana);

        this.genres = new ArrayList<>();
        genres.add(genre);

        this.personList = new ArrayList<>();
        personList.add(actorEva);
        personList.add(directorTommy);
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

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void updateMovieWithFutureYearMadeTest() {
        movieDao.create(movie);
        Movie foundMovie = movieDao.findById(movie.getId());
        foundMovie.setYearMade(LocalDate.of(9999, 1, 1));
        movieDao.update(foundMovie);
        entityManager.flush();
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createMovieWithBlankNameTest() {
        Movie movie = new Movie();
        movie.setName(" ");
        movie.setDescription("Blank name");
        movieDao.create(movie);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void updateMovieWithBlankNameTest() {
        movieDao.create(movie);
        Movie foundMovie = movieDao.findById(movie.getId());
        foundMovie.setName("      ");
        movieDao.update(foundMovie);
        entityManager.flush();
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createMovieWithBlankDescriptionTest() {
        Movie movie = new Movie();
        movie.setName("Blank description");
        movie.setDescription("  ");
        movieDao.create(movie);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void updateMovieWithBlankDescriptionTest() {
        movieDao.create(movie);
        Movie foundMovie = movieDao.findById(movie.getId());
        foundMovie.setDescription("      ");
        movieDao.update(foundMovie);
        entityManager.flush();
    }

    /**
     * Test of findByParameters method of MovieDao
     * Mega test, uses many other entities and DAOs...
     *  Not ideal, but had to be tested.
     *
     * @author Marek Petrovič
     */
    @Test
    public void findByParametersTest(){
        movieDao.create(movieEvaTommy);
        movieDao.create(movieJohnyJana);

        List<MovieAndRating> foundAll = movieDao.findByParameters(genres,null,null,null,null);
        Assert.assertNotNull(foundAll);
        Assert.assertEquals(foundAll.size(),2);
        List<Movie> movieList = new ArrayList<>();
        for (MovieAndRating found:foundAll) {
            movieList.add(found.getMovie());
        }
        Assert.assertEquals(movies,movieList);

        List<MovieAndRating> foundEvaTommy = movieDao.findByParameters(null,personList,null,null,null);
        Assert.assertNotNull(foundEvaTommy);
        Assert.assertEquals(foundEvaTommy.size(),1);
        Assert.assertEquals(foundEvaTommy.get(0).getMovie(),movieEvaTommy);

        List<MovieAndRating> foundByName = movieDao.findByParameters(null,null,"proti",null,null);
        Assert.assertNotNull(foundByName);
        Assert.assertEquals(foundByName.size(),2);

        List<MovieAndRating> all = movieDao.findByParameters(null,null,null,null,null);
        Assert.assertNotNull(all);
        Assert.assertEquals(all.size(),2);

        List<MovieAndRating> megaFilter = movieDao.findByParameters(genres,personList,"a tak",LocalDate.of(2020,1,1),"USA");
        Assert.assertNotNull(megaFilter);
        Assert.assertEquals(megaFilter.size(),1);
        Assert.assertEquals(megaFilter.get(0).getMovie(),movieEvaTommy);
    }

    /**
     * @author Marek Petrovič
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getMoviesOfGenresTestAllNull(){
        movieDao.create(movieEvaTommy);
        movieDao.create(movieJohnyJana);
        movieDao.getMoviesOfGenres(null,0,null);
    }

    /**
     * @author Marek Petrovič
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getMoviesOfGenresTestUserNull(){
        movieDao.create(movieEvaTommy);
        movieDao.create(movieJohnyJana);
        List<MovieAndRating> movieAndRatings = movieDao.getMoviesOfGenres(genres,0,null);
    }

    /**
     * Mega test of functionality
     *   tests if returns movies without rating
     *   tests if doesnt return already seen movies
     *   tests if calculates aggregates
     *
     * @author Marek Petrovič
     */
    @Test
    public void getMoviesOfGenresTest(){
        User user = new User();
        user.setEmail("m@p.com");
        user.setPasswordHash("gdfsfrgs");
        user.setNickName("asdfg");
        userDao.createUser(user);

        movieDao.create(movieEvaTommy);
        movieDao.create(movieJohnyJana);

        //user did not rate any - return all
        List<MovieAndRating> movieAndRatings = movieDao.getMoviesOfGenres(genres,2,user);
        Assert.assertNotNull(movieAndRatings);
        Assert.assertEquals(movieAndRatings.size(),2);
        Assert.assertEquals(movieAndRatings.get(0).getOverallScore(), Double.valueOf(0));

        //user rated one - return else
        UserRating userRating = new UserRating();
        userRating.setUser(user);
        userRating.setMovie(movieEvaTommy);
        userRating.setVisualScore(5);
        userRating.setStoryScore(5);
        userRating.setActorScore(5);
        userRating.setOverallScore(5);
        userRatingDao.createUserRating(userRating);

        List<MovieAndRating> movieAndRatings2 = movieDao.getMoviesOfGenres(genres,2,user);
        Assert.assertNotNull(movieAndRatings2);
        Assert.assertEquals(movieAndRatings2.size(),1);
        Assert.assertEquals(movieAndRatings2.get(0).getOverallScore(), Double.valueOf(0));

        User user1 = new User();
        user1.setNickName("janka");
        user1.setPasswordHash("dsagfdfs");
        user1.setEmail("j@m.com");
        userDao.createUser(user1);

        UserRating userRating1 = new UserRating();
        userRating1.setUser(user1);
        userRating1.setMovie(movieJohnyJana);
        userRating1.setVisualScore(8);
        userRating1.setStoryScore(8);
        userRating1.setActorScore(8);
        userRating1.setOverallScore(8);
        userRatingDao.createUserRating(userRating1);

        List<MovieAndRating> movieAndRatings3 = movieDao.getMoviesOfGenres(genres,2,user);
        Assert.assertNotNull(movieAndRatings3);
        Assert.assertEquals(movieAndRatings3.size(),1);
        Assert.assertEquals(movieAndRatings3.get(0).getOverallScore(),(Double) 8.0);

    }

    @Test
    public void findByIdWithRatingTest(){
        movieDao.create(movieEvaTommy);
        User user1 = new User();
        user1.setNickName("janka");
        user1.setPasswordHash("dsagfdfs");
        user1.setEmail("j@m.com");
        userDao.createUser(user1);

        UserRating userRating1 = new UserRating();
        userRating1.setUser(user1);
        userRating1.setMovie(movieEvaTommy);
        userRating1.setVisualScore(8);
        userRating1.setStoryScore(8);
        userRating1.setActorScore(8);
        userRating1.setOverallScore(8);
        userRatingDao.createUserRating(userRating1);

        MovieAndRating movieAndRating = movieDao.findByIdWithRating(movieEvaTommy.getId());
        Assert.assertNotNull(movieAndRating);
        Assert.assertEquals(movieAndRating.getMovie(),movieEvaTommy);
        Assert.assertEquals(movieAndRating.getOverallScore().doubleValue(),userRating1.getOverallScore().doubleValue());
    }

    @Test
    public void findByIdWithRatingNoRatingTest(){
        movieDao.create(movieEvaTommy);
        User user1 = new User();
        user1.setNickName("janka");
        user1.setPasswordHash("dsagfdfs");
        user1.setEmail("j@m.com");
        userDao.createUser(user1);

        MovieAndRating movieAndRating = movieDao.findByIdWithRating(movieEvaTommy.getId());
        Assert.assertNotNull(movieAndRating);
        Assert.assertEquals(movieAndRating.getMovie(),movieEvaTommy);
        Assert.assertEquals(movieAndRating.getOverallScore(), Double.valueOf(0));
    }

    @Test
    public void findByIdWithRatingNonExistTest(){
        MovieAndRating movieAndRating = movieDao.findByIdWithRating(45646541L);
        Assert.assertNull(movieAndRating);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByIdWithRatingNullTest() {
        MovieAndRating movieAndRating = movieDao.findByIdWithRating(null);
    }

}