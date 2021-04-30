package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.UserRatingDao;
import cz.muni.fi.pa165.entity.Movie;
import cz.muni.fi.pa165.entity.User;
import cz.muni.fi.pa165.entity.UserRating;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.service.exceptions.NullArgumentException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.Month;

/**
 * @author Matej Turek
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@ExtendWith(MockitoExtension.class)
public class UserRatingServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private UserRatingDao userRatingDaoMock;

    @Inject
    @InjectMocks
    private UserRatingServiceImpl service;

    private Movie movie;

    private User user;

    private UserRating rating;

    @BeforeEach
    public void init() {
        movie = new Movie();
        movie.setId(1L);
        movie.setName("Kakophonia");
        movie.setDescription("Dalo by sa bolo bývalo dať povedať, že nie práve ľubozvučnosť.");
        movie.setYearMade(LocalDate.of(2020, Month.APRIL,1));
        movie.setCountryCode("CZK");
        movie.setLengthMin(98);

        user = User.builder()
                .id(1L)
                .nickName("broskve")
                .passwordHash("h4$hov4n€H€$Lo$O$olov")
                .name("Vysoká Štíhla")
                .email("vysoka.stihla@modeling.com")
                .dateOfBirth(LocalDate.of(2000, Month.JANUARY, 1))
                .isAdministrator(false)
                .build();

        rating = new UserRating();
        rating.setId(1L);
        rating.setActorScore(8);
        rating.setStoryScore(7);
        rating.setVisualScore(9);
        rating.setOverallScore(null);
    }

    @Test
    public void createUserRatingTest() {
        service.createUserRating(rating, user, movie);

        Mockito.verify(userRatingDaoMock, Mockito.times(1)).createUserRating(rating);
        Assertions.assertThat(rating.getOverallScore()).isEqualTo(8);
    }

    @Test
    public void createNullUserRatingTest() {
        Assertions.assertThatThrownBy(() -> service.createUserRating(null, user, movie))
                .isInstanceOf(NullArgumentException.class);
    }

    // StoryScore and VisualScore are the same
    @Test
    public void createUserRatingWithNullActorScoreTest() {
        rating.setActorScore(null);

        Assertions.assertThatThrownBy(() -> service.createUserRating(rating, user, movie))
                .isInstanceOf(NullArgumentException.class);
    }

    @Test
    public void findUserRatingByIdTest() {
        service.findUserRatingById(rating.getId());

        Mockito.verify(userRatingDaoMock, Mockito.times(1)).findById(rating.getId());
    }

    @Test
    public void findUserRatingByNullIdTest() {
        Assertions.assertThatThrownBy(() -> service.findUserRatingById(null))
                .isInstanceOf(NullArgumentException.class);

        Mockito.verify(userRatingDaoMock, Mockito.times(0)).findById(null);
    }

    @Test
    public void findUserRatingsByUserTest() {
        service.findUserRatingsByUser(user);

        Mockito.verify(userRatingDaoMock, Mockito.times(1)).findByUser(user);
    }

    @Test
    public void findUserRatingsByNullUserTest() {
        Assertions.assertThatThrownBy(() -> service.findUserRatingsByUser(null))
                .isInstanceOf(NullArgumentException.class);

        Mockito.verify(userRatingDaoMock, Mockito.times(0)).findByUser(null);
    }

    @Test
    public void findUserRatingsByMovieTest() {
        service.findUserRatingsByMovie(movie);

        Mockito.verify(userRatingDaoMock, Mockito.times(1)).findByMovie(movie);
    }

    @Test
    public void findUserRatingsByNullMovieTest() {
        Assertions.assertThatThrownBy(() -> service.findUserRatingsByMovie(null))
                .isInstanceOf(NullArgumentException.class);

        Mockito.verify(userRatingDaoMock, Mockito.times(0)).findByMovie(null);
    }

    @Test
    public void findAllUserRatingsTest() {
        service.findAllUserRatings();

        Mockito.verify(userRatingDaoMock, Mockito.times(1)).findAll();
    }

    @Test
    public void deleteUserRatingTest() {
        service.deleteUserRating(rating);

        Mockito.verify(userRatingDaoMock, Mockito.times(1)).deleteUserRating(rating);
    }

    @Test
    public void deleteNullUserRatingTest() {
        Assertions.assertThatThrownBy(() -> service.deleteUserRating(null))
                .isInstanceOf(NullArgumentException.class);

        Mockito.verify(userRatingDaoMock, Mockito.times(0)).deleteUserRating(null);
    }

    @Test
    public void findAggregateByGenreForUserTest() {
        service.findAggregateByGenreForUser(user);

        Mockito.verify(userRatingDaoMock, Mockito.times(1)).findAggregateByGenreForUser(user);
    }

    @Test
    public void findAggregateByGenreForNullUserTest() {
        Assertions.assertThatThrownBy(() -> service.findAggregateByGenreForUser(null))
                .isInstanceOf(NullArgumentException.class);

        Mockito.verify(userRatingDaoMock, Mockito.times(0)).findAggregateByGenreForUser(null);
    }
}
