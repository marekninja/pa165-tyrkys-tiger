package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.MovieDao;
import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.jpql.MovieAndRating;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.service.exceptions.NullArgumentException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

/**
 * @author Marek Petrovič
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class MovieServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private MovieDao movieDao;

    private MovieService movieService;


    private Image image;
    private Image imageTitle;
    private UserRating userRating;
    private Movie movie;
    private List<Movie> movies;
    private Genre genre;
    private List<Genre> genres = new ArrayList<>();
    private Person actor;
    private Person director;
    private List<Person> personList = new ArrayList<>();
    private User user;

    @BeforeClass
    public void setMocks(){
        MockitoAnnotations.openMocks(this);
        movieService = new MovieServiceImpl(movieDao);
    }

    @BeforeMethod
    public void setUp() {
        this.imageTitle = new Image();
        imageTitle.setId(1L);
        imageTitle.setImage("obrazok".getBytes());
        imageTitle.setImageMimeType("jpg");

        this.image = new Image();
        image.setId(1L);
        image.setImage("momentka".getBytes());
        image.setImageMimeType("jpg");

        this.genre = new Genre();
        genre.setId(1L);
        genre.setName("veľmi akčný");
        this.genres.add(genre);

        this.actor = new Person();
        actor.setId(1L);
        actor.setName("Johny Cash");
        this.personList.add(actor);

        this.director = new Person();
        director.setId(2L);
        director.setName("Tommy Cash");
        this.personList.add(director);

        this.user = new User();
        user.setId(1L);
        user.setEmail("janko@azet.sk");
        user.setNickName("janko");
        user.setPasswordHash("j4nk0");

        this.userRating = new UserRating();
        userRating.setId(1L);
        userRating.setActorScore(5);
        userRating.setStoryScore(5);
        userRating.setVisualScore(5);
        userRating.setOverallScore(5);
//        userRating.setMovie(movie);
//        userRating.setUser(user);



        this.movie = new Movie();
        movie.setId(1L);
        movie.setName("5 proti a tak dalej");
        movie.setDescription("proti sebe navzajom a proti 100-slovakom");
        movie.setYearMade(LocalDate.of(2020,1,1));
        movie.setCountryCode("USA");
        movie.setLengthMin(200);
//        movie.addActor(actor);
//        movie.setDirector(director);
//        movie.addToGallery(image);
//        movie.setImageTitle(imageTitle);
//        movie.addGenre(genre);
//        movie.addUserRating(userRating);

//        user.addRating(userRating);



        this.movies = new ArrayList<>();
        movies.add(movie);
    }

    @Test
    public void testFindById() {
        when(movieDao.findById(movie.getId())).thenReturn(movie);
        Movie found = movieService.findById(movie.getId());
        Assert.assertNotNull(found);
        Assert.assertEquals(found,movie);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByIdNull() {
        when(movieDao.findById(null)).thenThrow(IllegalArgumentException.class);
        Movie found = movieService.findById(null);
        Assert.assertNotNull(found);
        Assert.assertEquals(found,movie);
    }

    @Test
    public void testFindByParametersAllNull() {
        MovieAndRating movieAndRating = new MovieAndRating(movie, 5.0);
        List<MovieAndRating> movieAndRatings = new ArrayList<>();
        movieAndRatings.add(movieAndRating);
        when(movieDao.findByParameters(null,null,null,null,null))
                .thenReturn(movieAndRatings);

        List<MovieAndRating> found = movieService.findByParameters(null,null,null,null,null);
        Assert.assertNotNull(found);
        Assert.assertEquals(found.size(),1);
        Assert.assertEquals(found.get(0),movieAndRatings.get(0));
    }

    @Test
    public void testFindByParametersCorrect() {
        MovieAndRating movieAndRating = new MovieAndRating(movie, (double)userRating.getOverallScore());
        List<MovieAndRating> movieAndRatings = new ArrayList<>();
        movieAndRatings.add(movieAndRating);

        when(movieDao.findByParameters(genres,personList,movie.getName(),movie.getYearMade(),movie.getCountryCode()))
                .thenReturn(movieAndRatings);

        List<MovieAndRating> found = movieService.findByParameters(genres,personList,movie.getName(),movie.getYearMade(),movie.getCountryCode());
        Assert.assertNotNull(found);
        Assert.assertEquals(found.size(),1);
        Assert.assertEquals(found.get(0),movieAndRatings.get(0));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByParametersFuture() {
        LocalDate yearMade = LocalDate.of(2050,1,1);
        List<MovieAndRating> found = movieService.findByParameters(null,null,null,yearMade,null);
    }

    @Test(expectedExceptions = NullArgumentException.class)
    public void testGetRecommendedMoviesAllNull() {
        movieService.getRecommendedMovies(null,null);
    }

    @Test
    public void testCreate() {
        doNothing().when(movieDao).create(movie);
        movieService.create(movie);
    }

    @Test(expectedExceptions = PersistenceException.class)
    public void testCreateNull() {
        doThrow(PersistenceException.class).when(movieDao).create(null);
        movieService.create(null);
    }

    @Test
    public void testDelete() {
        doNothing().when(movieDao).remove(movie);
        movieService.delete(movie);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteNull(){
        doThrow(IllegalArgumentException.class).when(movieDao).remove(null);
        movieService.delete(null);
    }

    @Test
    public void testSetImageTitle() {
        when(movieDao.update(movie)).thenReturn(movie);
        movieService.setImageTitle(movie,imageTitle);
        Assert.assertEquals(movie.getImageTitle(),imageTitle);
    }

    @Test(expectedExceptions = NullArgumentException.class)
    public void testSetImageTitleNull() {
        when(movieDao.update(movie)).thenReturn(movie);
        movieService.setImageTitle(movie,null);
    }

    @Test
    public void testAddToGallery() {
        when(movieDao.update(movie)).thenReturn(movie);
        movieService.addToGallery(movie,image);
        Assert.assertNotNull(movie.getGallery());
        Assert.assertEquals(movie.getGallery().size(),1);
        Assert.assertTrue(movie.getGallery().contains(image));
    }

    @Test(expectedExceptions = NullArgumentException.class)
    public void testAddToGalleryNull() {
        when(movieDao.update(movie)).thenReturn(movie);
        movieService.addToGallery(movie,null);
    }

    @Test
    public void testRemoveFromGallery() {
        when(movieDao.update(movie)).thenReturn(movie);
        movieService.addToGallery(movie,image);
        movieService.removeFromGallery(movie,image);
        Assert.assertNotNull(movie.getGallery());
        Assert.assertEquals(movie.getGallery().size(),0);
        Assert.assertFalse(movie.getGallery().contains(image));
    }

    @Test
    public void testRemoveFromGalleryNull() {
        when(movieDao.update(movie)).thenReturn(movie);
        movieService.removeFromGallery(movie,null);
    }

    @Test
    public void testAddActor() {
        when(movieDao.update(movie)).thenReturn(movie);
        movieService.addActor(movie,actor);
        Assert.assertNotNull(movie.getActors());
        Assert.assertEquals(movie.getActors().size(),1);
        Assert.assertTrue(movie.getActors().contains(actor));
    }

    @Test(expectedExceptions = NullArgumentException.class)
    public void testAddActorNull() {
        when(movieDao.update(movie)).thenReturn(movie);
        movieService.addActor(movie,null);

    }

    @Test
    public void testRemoveActor() {
        when(movieDao.update(movie)).thenReturn(movie);
        movieService.addActor(movie,actor);
        movieService.removeActor(movie,actor);
        Assert.assertNotNull(movie.getActors());
        Assert.assertEquals(movie.getActors().size(),0);
    }

    @Test
    public void testRemoveActorNull() {
        when(movieDao.update(movie)).thenReturn(movie);
        movieService.removeActor(movie,null);
        Assert.assertEquals(movie.getActors().size(),0);
    }

    @Test
    public void testChangeDirector() {
        when(movieDao.update(movie)).thenReturn(movie);
        Assert.assertNull(movie.getDirector());
        movieService.changeDirector(movie,director);
        Assert.assertNotNull(movie.getDirector());
        Assert.assertEquals(movie.getDirector(),director);

        Person person = new Person();
        person.setId(2000L);
        person.setName("director2");
        movieService.changeDirector(movie,person);
        Assert.assertNotNull(movie.getDirector());
        Assert.assertEquals(movie.getDirector(),person);
    }

    @Test(expectedExceptions = NullArgumentException.class)
    public void testChangeDirectorNull() {
        when(movieDao.update(movie)).thenReturn(movie);

        movieService.changeDirector(movie,null);
    }

    @Test
    public void testAddGenre() {
        when(movieDao.update(movie)).thenReturn(movie);

        Assert.assertEquals(movie.getGenres().size(),0);
        movieService.addGenre(movie,genre);
        Assert.assertNotNull(movie.getGenres());
        Assert.assertEquals(movie.getGenres().size(),1);
        Assert.assertTrue(movie.getGenres().contains(genre));
    }

    @Test(expectedExceptions = NullArgumentException.class)
    public void testAddGenreNull() {
        when(movieDao.update(movie)).thenReturn(movie);
        movieService.addGenre(movie,null);
    }

    @Test
    public void testRemoveGenre() {
        doNothing().when(movieDao).remove(movie);
        movieService.addGenre(movie,genre);
        Assert.assertEquals(movie.getGenres().size(),1);
        movieService.removeGenre(movie,genre);
        Assert.assertEquals(movie.getGenres().size(),0);
    }

    @Test(expectedExceptions = NullArgumentException.class)
    public void testRemoveGenreNull() {
        doNothing().when(movieDao).remove(movie);
        movieService.removeGenre(movie,null);
    }

    @Test
    public void testUpdateMovieAttrs() {
        when(movieDao.update(movie)).thenReturn(movie);
        when(movieDao.findById(movie.getId())).thenReturn(movie);

        Movie changes = new Movie();
        changes.setId(movie.getId());
        changes.setName("po schodoch a tak ďalej");
        changes.setDescription("veľmi ďaleko za horami a za dolami ešte");
        changes.setYearMade(LocalDate.of(2015,1,1));
        changes.setCountryCode("HUN");
        changes.setLengthMin(205);
        changes.addGenre(genre);
        changes.addActor(actor);
        changes.setDirector(director);

        movieService.updateMovieAttrs(changes);

        Assert.assertNotEquals(movie.getGenres(),changes.getGenres());
        Assert.assertEquals(movie.getName(),"po schodoch a tak ďalej");
        Assert.assertEquals(movie.getDescription(),"veľmi ďaleko za horami a za dolami ešte");
        Assert.assertEquals(movie.getYearMade(),LocalDate.of(2015,1,1));
        Assert.assertEquals(movie.getCountryCode(),"HUN");
        Assert.assertEquals(movie.getLengthMin(),(Integer) 205);
        Assert.assertNotEquals(movie.getGenres(),changes.getGenres());
        Assert.assertNotEquals(movie.getActors(),changes.getActors());
        Assert.assertNotEquals(movie.getDirector(),changes.getDirector());
    }

    @Test(expectedExceptions = NullArgumentException.class)
    public void testUpdateMovieAttrsNull() {
        when(movieDao.update(movie)).thenReturn(movie);
        when(movieDao.findById(movie.getId())).thenReturn(movie);

        movieService.updateMovieAttrs(null);
    }
}