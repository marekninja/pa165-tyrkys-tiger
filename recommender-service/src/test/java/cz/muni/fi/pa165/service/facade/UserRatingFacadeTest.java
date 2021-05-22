package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.MovieDetailDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.dto.UserRatingDTO;
import cz.muni.fi.pa165.dto.UserRatingViewDTO;
import cz.muni.fi.pa165.entity.Movie;
import cz.muni.fi.pa165.entity.User;
import cz.muni.fi.pa165.entity.UserRating;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.MovieService;
import cz.muni.fi.pa165.service.UserRatingService;
import cz.muni.fi.pa165.service.UserService;
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
import java.util.List;

/**
 * @author Matej Turek
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@ExtendWith(MockitoExtension.class)
public class UserRatingFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private UserRatingService userRatingService;

    @Mock
    private UserService userService;

    @Mock
    private MovieService movieService;

    @Mock
    private BeanMappingService beanMappingService;

    @Inject
    @InjectMocks
    private UserRatingFacadeImpl userRatingFacade;

    private User user;

    private UserDTO userDTO;

    private Movie movie;

    private MovieDetailDTO movieDetailDTO;

    private UserRating rating;

    private UserRatingDTO userRatingDTO;

    private UserRatingViewDTO userRatingViewDTO;

    @BeforeEach
    public void init() {
        user = User.builder()
                .id(1L)
                .nickName("broskve")
                //.passwordHash("h4$hov4n€H€$Lo$O$olov")
                .name("Vysoká Štíhla")
                .email("vysoka.stihla@modeling.com")
                .dateOfBirth(LocalDate.of(2000, Month.JANUARY, 1))
                .isAdministrator(false)
                .build();

        userDTO = UserDTO.builder()
                .id(1L)
                .nickName("broskve")
                .password("OcelovaVeverkaNeskace9912")
                .name("Vysoká Štíhla")
                .email("vysoka.stihla@modeling.com")
                .isAdministrator(false)
                .dateOfBirth(LocalDate.of(2000, Month.JANUARY, 1))
                .build();

        movie = new Movie();
        movie.setId(1L);
        movie.setName("Kakophonia");
        movie.setDescription("Dalo by sa bolo bývalo dať povedať, že nie práve ľubozvučnosť.");
        movie.setYearMade(LocalDate.of(2020, Month.APRIL,1));
        movie.setCountryCode("CZK");
        movie.setLengthMin(98);

        movieDetailDTO = new MovieDetailDTO();

        rating = new UserRating();
        rating.setId(1L);
        rating.setStoryScore(7);
        rating.setVisualScore(9);
        rating.setActorScore(8);
        rating.setOverallScore(null);

        userRatingDTO = new UserRatingDTO();
        userRatingDTO.setId(1L);
        userRatingDTO.setUserId(1L);
        userRatingDTO.setMovieId(1L);
        userRatingDTO.setStoryScore(7);
        userRatingDTO.setVisualScore(9);
        userRatingDTO.setActorScore(8);
        userRatingDTO.setOverallScore(null);

        userRatingViewDTO = new UserRatingViewDTO();
        userRatingViewDTO.setId(1L);
        userRatingViewDTO.setStoryScore(7);
        userRatingViewDTO.setVisualScore(9);
        userRatingViewDTO.setActorScore(8);
        userRatingViewDTO.setOverallScore(8);
    }

    @Test
    public void createUserRatingTest() {
        Mockito.when(beanMappingService.mapTo(userRatingDTO, UserRating.class))
                .thenReturn(rating);
        Mockito.when(userService.findUserById(userRatingDTO.getUserId()))
                .thenReturn(user);
        Mockito.when(movieService.findById(userRatingDTO.getMovieId()))
                .thenReturn(movie);

        userRatingFacade.createUserRating(userRatingDTO);

        Mockito.verify(beanMappingService, Mockito.times(1))
                .mapTo(userRatingDTO, UserRating.class);
        Mockito.verify(userService, Mockito.times(1))
                .findUserById(userRatingDTO.getUserId());
        Mockito.verify(movieService, Mockito.times(1))
                .findById(userRatingDTO.getMovieId());
        Mockito.verify(userRatingService, Mockito.times(1))
                .createUserRating(rating, user, movie);
    }

    @Test
    public void createNullUserRatingTest() {
        Assertions.assertThatThrownBy(() -> userRatingFacade.createUserRating(null))
                .isInstanceOf(NullArgumentException.class);

        Mockito.verify(beanMappingService, Mockito.times(0))
                .mapTo(userRatingDTO, UserRating.class);
        Mockito.verify(userService, Mockito.times(0))
                .findUserById(userRatingDTO.getUserId());
        Mockito.verify(movieService, Mockito.times(0))
                .findById(userRatingDTO.getMovieId());
        Mockito.verify(userRatingService, Mockito.times(0))
                .createUserRating(rating, user, movie);
    }

    @Test
    public void findUserRatingByIdTest() {
        Mockito.when(userRatingService.findUserRatingById(1L))
                .thenReturn(rating);
        Mockito.when(beanMappingService.mapTo(rating, UserRatingViewDTO.class))
                .thenReturn(userRatingViewDTO);

        userRatingFacade.findUserRatingById(1L);

        Mockito.verify(beanMappingService, Mockito.times(1))
                .mapTo(rating, UserRatingViewDTO.class);
        Mockito.verify(userRatingService, Mockito.times(1))
                .findUserRatingById(1L);
    }

    @Test
    public void findUserRatingByNullIdTest() {
        Assertions.assertThatThrownBy(() -> userRatingFacade.findUserRatingById(null))
                .isInstanceOf(NullArgumentException.class);

        Mockito.verify(beanMappingService, Mockito.times(0))
                .mapTo(rating, UserRatingViewDTO.class);
        Mockito.verify(userRatingService, Mockito.times(0))
                .findUserRatingById(1L);
    }

    @Test
    public void findUserRatingsByUserTest() {
        Mockito.when(userRatingService.findUserRatingsByUser(user))
                .thenReturn(List.of(rating));
        Mockito.when(beanMappingService.mapTo(userDTO, User.class))
                .thenReturn(user);
        Mockito.when(beanMappingService.mapTo(List.of(rating), UserRatingViewDTO.class))
                .thenReturn(List.of(userRatingViewDTO));

        userRatingFacade.findUserRatingsByUser(userDTO);

        Mockito.verify(beanMappingService, Mockito.times(1))
                .mapTo(userDTO, User.class);
        Mockito.verify(beanMappingService, Mockito.times(1))
                .mapTo(List.of(rating), UserRatingViewDTO.class);
        Mockito.verify(userRatingService, Mockito.times(1))
                .findUserRatingsByUser(user);
    }

    @Test
    public void findUserRatingsByNullUserTest() {
        Assertions.assertThatThrownBy(() -> userRatingFacade.findUserRatingsByUser(null))
                .isInstanceOf(NullArgumentException.class);

        Mockito.verify(beanMappingService, Mockito.times(0))
                .mapTo(userDTO, User.class);
        Mockito.verify(beanMappingService, Mockito.times(0))
                .mapTo(List.of(rating), UserRatingViewDTO.class);
        Mockito.verify(userRatingService, Mockito.times(0))
                .findUserRatingsByUser(user);
    }

    @Test
    public void findUserRatingsByMovieTest() {
        Mockito.when(userRatingService.findUserRatingsByMovie(movie))
                .thenReturn(List.of(rating));
        Mockito.when(beanMappingService.mapTo(movieDetailDTO, Movie.class))
                .thenReturn(movie);
        Mockito.when(beanMappingService.mapTo(List.of(rating), UserRatingViewDTO.class))
                .thenReturn(List.of(userRatingViewDTO));

        userRatingFacade.findUserRatingsByMovie(movieDetailDTO);

        Mockito.verify(beanMappingService, Mockito.times(1))
                .mapTo(movieDetailDTO, Movie.class);
        Mockito.verify(beanMappingService, Mockito.times(1))
                .mapTo(List.of(rating), UserRatingViewDTO.class);
        Mockito.verify(userRatingService, Mockito.times(1))
                .findUserRatingsByMovie(movie);
    }

    @Test
    public void findUserRatingsByNullMovieTest() {
        Assertions.assertThatThrownBy(() -> userRatingFacade.findUserRatingsByMovie(null))
                .isInstanceOf(NullArgumentException.class);

        Mockito.verify(beanMappingService, Mockito.times(0))
                .mapTo(movieDetailDTO, Movie.class);
        Mockito.verify(beanMappingService, Mockito.times(0))
                .mapTo(List.of(rating), UserRatingViewDTO.class);
        Mockito.verify(userRatingService, Mockito.times(0))
                .findUserRatingsByMovie(movie);
    }

    @Test
    public void updateUserRatingTest() {
        Mockito.when(beanMappingService.mapTo(userRatingDTO, UserRating.class))
                .thenReturn(rating);
        Mockito.when(userService.findUserById(1L))
                .thenReturn(user);
        Mockito.when(movieService.findById(1L))
                .thenReturn(movie);
        Mockito.when(beanMappingService.mapTo(rating, UserRatingViewDTO.class))
                .thenReturn(userRatingViewDTO);

        userRatingFacade.updateUserRating(userRatingDTO);

        Mockito.verify(beanMappingService, Mockito.times(1))
                .mapTo(userRatingDTO, UserRating.class);
        Mockito.verify(userRatingService, Mockito.times(1))
                .deleteUserRating(rating);
        Mockito.verify(userService, Mockito.times(1))
                .findUserById(1L);
        Mockito.verify(movieService, Mockito.times(1))
                .findById(1L);
        Mockito.verify(userRatingService, Mockito.times(1))
                .createUserRating(rating, user, movie);
        Mockito.verify(beanMappingService, Mockito.times(1))
                .mapTo(rating, UserRatingViewDTO.class);
    }

    @Test
    public void updateNullUserRatingTest() {
        Assertions.assertThatThrownBy(() -> userRatingFacade.updateUserRating(null))
                .isInstanceOf(NullArgumentException.class);

        Mockito.verify(beanMappingService, Mockito.times(0))
                .mapTo(userRatingDTO, UserRating.class);
        Mockito.verify(userRatingService, Mockito.times(0))
                .deleteUserRating(rating);
        Mockito.verify(userService, Mockito.times(0))
                .findUserById(1L);
        Mockito.verify(movieService, Mockito.times(0))
                .findById(1L);
        Mockito.verify(userRatingService, Mockito.times(0))
                .createUserRating(rating, user, movie);
        Mockito.verify(beanMappingService, Mockito.times(0))
                .mapTo(rating, UserRatingViewDTO.class);
    }

    @Test
    public void deleteUserRatingTest() {
        Mockito.when(beanMappingService.mapTo(userRatingDTO, UserRating.class))
                .thenReturn(rating);

        userRatingFacade.deleteUserRating(userRatingDTO.getId());

        Mockito.verify(beanMappingService, Mockito.times(1))
                .mapTo(userRatingDTO, UserRating.class);
        Mockito.verify(userRatingService, Mockito.times(1))
                .deleteUserRating(rating);
    }

    @Test
    public void deleteNullUserRatingTest() {
        Assertions.assertThatThrownBy(() -> userRatingFacade.deleteUserRating(null))
                .isInstanceOf(NullArgumentException.class);

        Mockito.verify(beanMappingService, Mockito.times(0))
                .mapTo(userRatingDTO, UserRating.class);
        Mockito.verify(userRatingService, Mockito.times(0))
                .deleteUserRating(rating);
    }
}
