package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

/**
 * @author Marek Petrovič
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class BeanMappingServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    BeanMappingService beanMappingService;

    private Image image;
    private Image imageTitle;
    private UserRating userRating;
    private Movie movie;
    private List<Movie> movies;
    private Genre genre;
    private Person actor;
    private Person director;
    private User user;

    @BeforeMethod
    public void before(){
        this.imageTitle = new Image();
        imageTitle.setImage("obrazok".getBytes());
        imageTitle.setImageMimeType("jpg");

        this.image = new Image();
        image.setImage("momentka".getBytes());
        image.setImageMimeType("jpg");

        this.genre = new Genre();
        genre.setName("veľmi akčný");

        this.actor = new Person();
        actor.setName("Johny Cash");

        this.director = new Person();
        director.setName("Tommy Cash");

        this.user = new User();
        user.setEmail("janko@azet.sk");
        user.setNickName("janko");
        user.setPasswordHash("j4nk0");
        user.setName("Janko Rúžička");
        user.setDateOfBirth(LocalDate.of(2000, Month.APRIL, 1));
        user.setAdministrator(false);

        this.userRating = new UserRating();
        userRating.setUser(user);
        userRating.setMovie(movie);
        userRating.setActorScore(5);
        userRating.setStoryScore(5);
        userRating.setVisualScore(5);
        userRating.setOverallScore(5);
        userRating.setUser(user);

        this.movie = new Movie();
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
        userRating.setMovie(movie);
        user.getRatings().add(userRating);

        this.movies = new ArrayList<>();
        movies.add(movie);
    }

    /**
     * Tests if Mapper maps also internal categories of object
     */
    @Test
    public void movieToMovieDetailMappingTest(){
        List<MovieDetailDTO> mDTOs = beanMappingService.mapTo(movies,MovieDetailDTO.class);
        Assert.assertNotNull(mDTOs);
        Assert.assertEquals(mDTOs.size(),1);
        MovieDetailDTO movieDetailDTO = mDTOs.get(0);

        Assert.assertNotNull(movieDetailDTO);
        Assert.assertEquals(movieDetailDTO.getName(), movie.getName());
        Assert.assertEquals(movieDetailDTO.getDescription(), movie.getDescription());

        ImageDetailDTO imageDTOtitle = movieDetailDTO.getImageTitle();
        Assert.assertNotNull(imageDTOtitle);
        Assert.assertEquals(imageDTOtitle.getImage(), imageTitle.getImage());
        Assert.assertEquals(imageDTOtitle.getImageMimeType(), imageTitle.getImageMimeType());

        Set<ImageDetailDTO> imageDTOgallery = movieDetailDTO.getGallery();
        Assert.assertNotNull(imageDTOgallery);
        Assert.assertEquals(imageDTOgallery.size(),1);
        Iterator<ImageDetailDTO> iterator = imageDTOgallery.iterator();
        Assert.assertTrue(iterator.hasNext());
        ImageDetailDTO imageDTO = iterator.next();
        Assert.assertNotNull(imageDTO);
        Assert.assertEquals(imageDTO.getImage(),image.getImage());
        Assert.assertEquals(imageDTO.getImageMimeType(),image.getImageMimeType());

        Assert.assertEquals(movieDetailDTO.getYearMade(),movie.getYearMade());
        Assert.assertEquals(movieDetailDTO.getCountryCode(),movie.getCountryCode());
        Assert.assertEquals(movieDetailDTO.getLengthMin(),movie.getLengthMin());

        Set<GenreDTO> genreDTOS = movieDetailDTO.getGenres();
        Assert.assertNotNull(genreDTOS);
        Assert.assertEquals(genreDTOS.size(),1);
        Iterator<GenreDTO> iterator1 = genreDTOS.iterator();
        Assert.assertTrue(iterator1.hasNext());
        GenreDTO genreDTO = iterator1.next();
        Assert.assertNotNull(genreDTO);
        Assert.assertEquals(genreDTO.getName(),genre.getName());

        Set<PersonDTO> personDTOS = movieDetailDTO.getActors();
        Assert.assertNotNull(personDTOS);
        Assert.assertEquals(personDTOS.size(),1);
        Iterator<PersonDTO> iterator2 = personDTOS.iterator();
        Assert.assertTrue(iterator2.hasNext());
        PersonDTO actorDTO = iterator2.next();
        Assert.assertNotNull(actorDTO);
        Assert.assertEquals(actorDTO.getName(),actor.getName());

        PersonDTO directorDTO = movieDetailDTO.getDirector();
        Assert.assertNotNull(directorDTO);
        Assert.assertEquals(directorDTO.getName(),director.getName());

//        movieDetailDTO.setRatingAgg(beanMappingService.mapTo(userRating,UserRatingDTO.class));
//
//        UserRatingDTO userRatingDTO = movieDetailDTO.getRatingAgg();
//        Assert.assertNotNull(userRatingDTO);
//        Assert.assertEquals(userRatingDTO.getActorScore(), userRating.getActorScore());
//        Assert.assertEquals(userRatingDTO.getOverallScore(), userRating.getOverallScore());
//        Assert.assertEquals(userRatingDTO.getVisualScore(), userRating.getVisualScore());
//        Assert.assertEquals(userRatingDTO.getStoryScore(), userRating.getStoryScore());
    }

    @Test
    public void mapToUser() {
        UserDTO userDTO = beanMappingService.mapTo(user, UserDTO.class);
        assertEqualsUserDTOtoEntity(userDTO, user);
    }

    @Test
    public void mapToUserRating() {
        UserRatingDTO userRatingDTO = beanMappingService.mapTo(userRating, UserRatingDTO.class);
        assertEqualsUserRatingDTOtoEntity(userRatingDTO, userRating);
    }

    private void assertEqualsUserDTOtoEntity(UserDTO userDTO, User user) {
        Assert.assertEquals(userDTO.getId(), user.getId());
        Assert.assertEquals(userDTO.getNickName(), user.getNickName());
        Assert.assertEquals(userDTO.getEmail(), user.getEmail());
        Assert.assertEquals(userDTO.getName(), user.getName());
        Assert.assertEquals(userDTO.getPassword(), user.getPasswordHash());
        Assert.assertEquals(userDTO.getDateOfBirth(), user.getDateOfBirth());
        Assert.assertEquals(userDTO.isAdministrator(), user.isAdministrator());
    }

    private void assertEqualsUserRatingDTOtoEntity(UserRatingDTO userRatingDTO, UserRating userRating) {
        Assert.assertEquals(userRatingDTO.getId(), userRating.getId());
        Assert.assertEquals(userRatingDTO.getUserId(), userRating.getUser().getId());
        Assert.assertEquals(userRatingDTO.getMovieId(), userRating.getMovie().getId());
        Assert.assertEquals(userRatingDTO.getStoryScore(), userRating.getStoryScore());
        Assert.assertEquals(userRatingDTO.getActorScore(), userRating.getActorScore());
        Assert.assertEquals(userRatingDTO.getVisualScore(), userRating.getVisualScore());
        Assert.assertEquals(userRatingDTO.getOverallScore(), userRating.getOverallScore());
    }

    @Test(expectedExceptions = MappingException.class)
    public void nullMapping(){
        MovieDetailDTO movieDetailDTO = beanMappingService.mapTo((MovieDetailDTO)null,MovieDetailDTO.class);
    }

    @Test
    public void internalNullMapping(){
        UserDTO userDTO = new UserDTO();
        userDTO.setNickName(null);
        userDTO.setName(null);
        userDTO.setId(null);
        userDTO.setEmail(null);

        User user = beanMappingService.mapTo(userDTO,User.class);
    }
}
