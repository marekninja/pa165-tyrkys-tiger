package cz.muni.fi.pa165.sampledata;

import cz.muni.fi.pa165.dto.ParametersDTO;
import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.jpql.MovieAndRating;
import cz.muni.fi.pa165.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Marek Petrovič
 */
@ContextConfiguration(classes = SampleDataConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class SampleDataLoadingFacadeImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ImageService imageService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private PersonService personService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRatingService userRatingService;

    @Autowired
    SampleDataLoadingFacade sampleDataLoadingFacade;

//    @Autowired
//    public SampleDataLoadingFacadeImplTest(ImageService imageService, MovieService movieService, GenreService genreService, PersonService personService, UserService userService, UserRatingService userRatingService) {
//        this.imageService = imageService;
//        this.movieService = movieService;
//        this.genreService = genreService;
//        this.personService = personService;
//        this.userService = userService;
//        this.userRatingService = userRatingService;
//    }

    @Test
    public void testLoadData() throws IOException {
        List<MovieAndRating> movieList = movieService.findByParameters(null,null,null,null,null);
        Assert.assertEquals(movieList.size(),4);

        Assert.assertNotNull(movieList.get(0).getMovie());
        Assert.assertNotNull(movieList.get(0).getOverallScore());

        Movie movie = movieList.get(0).getMovie();
        Assert.assertNotNull(movie.getImageTitle());
        Assert.assertNotNull(movie.getImageTitle().getId());
        Assert.assertNotNull(movie.getGallery());

        List<Genre> genres = genreService.findAllGenres();
        Assert.assertEquals(genres.size(),5);

        List<Person> personList = personService.findAll();
        Assert.assertEquals(personList.size(), 8);

        List<User> users = userService.findAllUsers();
        Assert.assertEquals(users.size(), 2);

        List<UserRating> userRatings = userRatingService.findAllUserRatings();
        Assert.assertEquals(userRatings.size(), 4);

    }
}