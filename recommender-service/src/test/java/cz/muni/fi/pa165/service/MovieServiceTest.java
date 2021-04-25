package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.MovieDao;
import cz.muni.fi.pa165.dao.UserDao;
import cz.muni.fi.pa165.dao.UserRatingDao;
import cz.muni.fi.pa165.dao.UserRatingDaoImpl;
import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author Marek Petrovič
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)  // Calls RollBack after each test
@Transactional
public class MovieServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    MovieDao movieDao;
//

    @Mock
    UserRatingService userRatingService;

    @Autowired
    @InjectMocks
    MovieService movieService;


    private Image image;
    private Image imageTitle;
    private UserRating userRating;
    private Movie movie;
    private List<Movie> movies;
    private Genre genre;
    private Person actor;
    private Person director;
    private User user;

    @BeforeClass
    public void setMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void before(){
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

        this.actor = new Person();
        actor.setId(1L);
        actor.setName("Johny Cash");

        this.director = new Person();
        director.setId(2L);
        director.setName("Tommy Cash");

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
        userRating.setMovie(movie);
        userRating.setUser(user);



        this.movie = new Movie();
        movie.setId(1L);
        movie.setName("5 proti a tak dalej");
        movie.setDescription("proti sebe navzajom a proti 100-slovakom");
        movie.setYearMade(LocalDate.of(2020,1,1));
        movie.setCountryCode("USA");
        movie.setLengthMin(200);
        movie.addActor(actor);
        movie.setDirector(director);
        movie.addToGallery(image);
        movie.setImageTitle(imageTitle);
        movie.addGenre(genre);
        movie.addUserRating(userRating);


        //TODO user should have addRelation/removeRelation methods
        user.addRating(userRating);



        this.movies = new ArrayList<>();
        movies.add(movie);
    }

    @Test
    public void findByIdTest(){
        when(movieDao.findById(movie.getId())).thenReturn(movie);
        Movie found = movieService.findById(movie.getId());
        Assert.assertNotNull(found);
        Assert.assertEquals(found,movie);
    }

    //TODO find by parameters test
    @Test
    public void findByParameters(){}

    //TODO getRecommendedTest

    @Test
    public void update(){
        when(movieDao.update(movie)).thenReturn(movie);
        Movie updated = movieService.update(movie);
        Assert.assertNotNull(updated);
        Assert.assertEquals(updated,movie);
    }

    //FIXME toto je velmi divne, ze mi nejde zmazat UserRating z Movie
    @Test
    public void deleteUserRating(){
//        Mockito.
//        when(userRatingDao.deleteUserRating(userRating)).;

        doAnswer((i) -> {
            Assert.assertEquals(i.getArguments()[0], userRating);

            Movie relatedMovie = userRating.getMovie();
            Integer firstSize = relatedMovie.getRatings().size();
            relatedMovie.removeUserRating(userRating);

            Assert.assertEquals(relatedMovie.getRatings().size(), firstSize - 1);

            User relatedUser = userRating.getUser();
            Integer userSize = relatedUser.getRatings().size();
            relatedUser.getRatings().remove(userRating);
            Assert.assertEquals(relatedUser.getRatings().size(),userSize-1);

            return null;
                }).when(userRatingService).deleteUserRating(userRating);

        Assert.assertEquals(movie.getRatings().size(),1);
        Assert.assertEquals(user.getRatings().size(),1);

        Iterator<UserRating> iterator = movie.getRatings().iterator();
        UserRating userRatingTest = iterator.next();

        Assert.assertEquals(userRatingTest.hashCode(),userRating.hashCode());

        Assert.assertEquals(userRatingTest,userRating);

        System.err.println("delete test " + movie.getRatings().contains(userRatingTest));
        System.err.println("delete original " + movie.getRatings().contains(userRating));


//        movie.removeUserRating(userRating);

        movieService.deleteUserRating(userRating);
        Assert.assertEquals(movie.getRatings().size(),0);
        Assert.assertEquals(user.getRatings().size(),0);
    }

}
