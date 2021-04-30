package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.facade.MovieFacade;
import cz.muni.fi.pa165.jpql.GenreAndRating;
import cz.muni.fi.pa165.jpql.MovieAndRating;
import cz.muni.fi.pa165.service.*;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.service.exceptions.NullArgumentException;
import org.dozer.MappingException;
import org.hibernate.ObjectNotFoundException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

/**
 * @author Marek Petrovič
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class MovieFacadeTest {

    @Mock
    MovieService movieService;

    @Mock
    ImageService imageService;

    @Mock
    PersonService personService;

    @Mock
    UserRatingService userRatingService;

    @Mock
    BeanMappingService beanMappingService;

    private MovieFacade movieFacade;

    private Image image;
    private Image imageTitle;
    private UserRating userRating;
    private Movie movie;
    private List<Movie> movies;
    private Genre genre;
    private List<Genre> genres;
    private Person actor;
    private Person director;
    private List<Person> personList;
    private User user;
    private MovieDetailDTO movieDetailDTOnoRatings;
    private ParametersDTO parametersDTO;
    private MovieAndRating movieAndRating;
    private List<MovieAndRating> movieAndRatingList;
    private MovieListDTO movieListDTO;
    private ArrayList<MovieListDTO> movieListDTOList;
    private UserDTO userDTO;
    private GenreAndRating genreAndRating;
    private ArrayList<GenreAndRating> genreAndRatingList;
    private ImageCreateDTO imageCreateDTO;
    private PersonToMovieDTO personToMovieDTO;
    private GenreToMovieDTO genreToMovie;

    @BeforeMethod
    public void before(){

        MockitoAnnotations.openMocks(this);
        this.movieFacade = new MovieFacadeImpl(movieService,imageService,personService,userRatingService,beanMappingService);

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
        this.genres = new ArrayList<>();
        this.genres.add(genre);

        this.actor = new Person();
        actor.setId(1L);
        actor.setName("Johny Cash");

        this.personList = new ArrayList<>();
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

        this.userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setNickName(user.getNickName());

        this.userRating = new UserRating();
        userRating.setId(1L);
        userRating.setActorScore(5);
        userRating.setStoryScore(5);
        userRating.setVisualScore(5);
        userRating.setOverallScore(5);

        this.movie = new Movie();
        movie.setId(1L);
        movie.setName("5 proti a tak dalej");
        movie.setDescription("proti sebe navzajom a proti 100-slovakom");
        movie.setYearMade(LocalDate.of(2020,1,1));
        movie.setCountryCode("USA");
        movie.setLengthMin(200);

        this.movies = new ArrayList<>();
        movies.add(movie);

        this.movieDetailDTOnoRatings = new MovieDetailDTO();
        movieDetailDTOnoRatings.setRatingAgg(null);
        movieDetailDTOnoRatings.setId(movie.getId());
        movieDetailDTOnoRatings.setName(movie.getName());
        movieDetailDTOnoRatings.setDescription(movie.getDescription());
        movieDetailDTOnoRatings.setYearMade(movie.getYearMade());
        movieDetailDTOnoRatings.setCountryCode(movie.getCountryCode());
        movieDetailDTOnoRatings.setLengthMin(movie.getLengthMin());

        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(genre.getId());
        genreDTO.setName(genre.getName());

        List<GenreDTO> genreDTOS = new ArrayList<>();
        genreDTOS.add(genreDTO);

        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(actor.getId());
        personDTO.setName(actor.getName());

        PersonDTO personDTO1 = new PersonDTO();
        personDTO1.setId(director.getId());
        personDTO1.setName(director.getName());

        List<PersonDTO> personDTOS = new ArrayList<>();
        personDTOS.add(personDTO);
        personDTOS.add(personDTO1);

        this.parametersDTO = new ParametersDTO();
        parametersDTO.setGenreDTOList(genreDTOS);
        parametersDTO.setCountryCode(movie.getCountryCode());
        parametersDTO.setMovieName(movie.getName());
        parametersDTO.setYearMade(movie.getYearMade().getYear());
        parametersDTO.setPersonDTOList(personDTOS);

        this.movieAndRating = new MovieAndRating(movie,5.0);
        this.movieAndRatingList = new ArrayList<>();
        movieAndRatingList.add(movieAndRating);

        this.movieListDTO = new MovieListDTO();
        movieListDTO.setOverallScoreAgg(movieAndRating.getOverallScore().floatValue());
        movieListDTO.setName(movie.getName());
        movieListDTO.setDescription(movie.getDescription());
        movieListDTO.setGenres(new HashSet<>(genreDTOS));
        movieListDTO.setTitleImage(null);

        this.movieListDTOList = new ArrayList<>();
        movieListDTOList.add(movieListDTO);

        this.genreAndRating = new GenreAndRating(genre,5f);
        this.genreAndRatingList = new ArrayList<>();
        genreAndRatingList.add(genreAndRating);

        this.imageCreateDTO = new ImageCreateDTO();
        imageCreateDTO.setImage(image.getImage());
        imageCreateDTO.setImageMimeType(image.getImageMimeType());
        imageCreateDTO.setMovieId(movie.getId());

        this.personToMovieDTO = new PersonToMovieDTO();
        personToMovieDTO.setMovieId(movie.getId());
        personToMovieDTO.setName(actor.getName());
        personToMovieDTO.setId(actor.getId());

        this.genreToMovie = new GenreToMovieDTO();
        genreToMovie.setMovieId(movie.getId());
        genreToMovie.setId(genre.getId());
        genreToMovie.setName(genre.getName());
    }

    @Test
    public void testFindMovieById() {
        when(movieService.findById(1L)).thenReturn(movie);
        when(beanMappingService.mapTo(movie, MovieDetailDTO.class))
                .thenReturn(movieDetailDTOnoRatings);
        MovieDetailDTO movieDetailDTO = movieFacade.findMovieById(movie.getId());
        Assert.assertEquals(movieDetailDTO,movieDetailDTOnoRatings);

        Mockito.verify(movieService,Mockito.times(1)).findById(1L);
        Mockito.verify(beanMappingService,Mockito.times(1)).mapTo(movie,MovieDetailDTO.class);
    }

    @Test(expectedExceptions = NullArgumentException.class)
    public void testFindMovieByIdNull() {
        MovieDetailDTO movieDetailDTO = movieFacade.findMovieById(null);
    }

    @Test
    public void testFindMovieByIdNonExist() {
        when(movieService.findById(2L)).thenReturn(null);
        doThrow(MappingException.class).when(beanMappingService).mapTo(null, MovieDetailDTO.class);

        MovieDetailDTO movieDetailDTO = movieFacade.findMovieById(2L);
        Assert.assertNull(movieDetailDTO);

        Mockito.verify(movieService,Mockito.times(1)).findById(2L);
        Mockito.verify(beanMappingService,Mockito.times(0)).mapTo(null,MovieDetailDTO.class);
    }

    @Test
    public void testFindMovieByParameters() {
        when(movieService.findByParameters(genres,personList,movie.getName(),movie.getYearMade(),movie.getCountryCode()))
                .thenReturn(movieAndRatingList);
        when(beanMappingService.mapTo(movie, MovieListDTO.class)).thenReturn(movieListDTO);
        when(beanMappingService.mapTo(parametersDTO.getGenreDTOList(), Genre.class)).thenReturn(genres);
        when(beanMappingService.mapTo(parametersDTO.getPersonDTOList(), Person.class)).thenReturn(personList);
        List<MovieListDTO> foundMovieListDTOs = movieFacade.findMovieByParameters(parametersDTO);
        Assert.assertNotNull(foundMovieListDTOs);
        Assert.assertEquals(foundMovieListDTOs.size(),1);
        Assert.assertEquals(foundMovieListDTOs.get(0),movieListDTO);

        Mockito.verify(movieService,Mockito.times(1))
                .findByParameters(genres,personList,movie.getName(),movie.getYearMade(),movie.getCountryCode());
        Mockito.verify(beanMappingService,Mockito.times(1)).mapTo(movie,MovieListDTO.class);
        Mockito.verify(beanMappingService,Mockito.times(1)).mapTo(parametersDTO.getGenreDTOList(),Genre.class);
        Mockito.verify(beanMappingService,Mockito.times(1)).mapTo(parametersDTO.getPersonDTOList(),Person.class);
    }

    @Test(expectedExceptions = NullArgumentException.class)
    public void testFindMovieByParametersNull() {
        List<MovieListDTO> foundMovieListDTOs = movieFacade.findMovieByParameters(null);
    }

    @Test
    public void testFindMovieByParametersNullInternal() {

        ParametersDTO parametersDTOnull = new ParametersDTO();
        parametersDTOnull.setPersonDTOList(null);
        parametersDTOnull.setMovieName(null);
        parametersDTOnull.setGenreDTOList(null);
        parametersDTOnull.setCountryCode(null);
        parametersDTOnull.setYearMade(null);

        when(movieService.findByParameters(null,null,null,null,null))
                .thenReturn(movieAndRatingList);
        when(beanMappingService.mapTo(movie, MovieListDTO.class)).thenReturn(movieListDTO);
        doThrow(MappingException.class).when(beanMappingService).mapTo(null, Genre.class);
        doThrow(MappingException.class).when(beanMappingService).mapTo(null, Person.class);

        List<MovieListDTO> foundMovieListDTOs = movieFacade.findMovieByParameters(parametersDTOnull);
        Assert.assertNotNull(foundMovieListDTOs);
        Assert.assertEquals(foundMovieListDTOs.size(),1);
        Assert.assertEquals(foundMovieListDTOs.get(0),movieListDTO);

        Mockito.verify(movieService,Mockito.times(1))
                .findByParameters(null,null,null,null,null);
        Mockito.verify(beanMappingService,Mockito.times(1)).mapTo(movie,MovieListDTO.class);
        Mockito.verify(beanMappingService,Mockito.times(0)).mapTo(parametersDTOnull.getGenreDTOList(),Genre.class);
        Mockito.verify(beanMappingService,Mockito.times(0)).mapTo(parametersDTOnull.getPersonDTOList(),Person.class);
    }

    @Test
    public void testGetRecommendedMovies() {
        when(beanMappingService.mapTo(userDTO,User.class)).thenReturn(user);
        when(userRatingService.findAggregateByGenreForUser(user)).thenReturn(genreAndRatingList);
        when(movieService.getRecommendedMovies(genres,user)).thenReturn(movieAndRatingList);
        when(beanMappingService.mapTo(movieAndRating.getMovie(),MovieListDTO.class)).thenReturn(movieListDTO);

        List<MovieListDTO> movieListDTOS = movieFacade.getRecommendedMovies(userDTO);
        Assert.assertNotNull(movieListDTOS);
        Assert.assertEquals(movieListDTOS.size(),1);
        Assert.assertEquals(movieListDTOS.get(0),movieListDTO);

        Mockito.verify(beanMappingService,Mockito.times(1)).mapTo(userDTO,User.class);
        Mockito.verify(userRatingService,Mockito.times(1)).findAggregateByGenreForUser(user);
        Mockito.verify(movieService,Mockito.times(1)).getRecommendedMovies(genres,user);
        Mockito.verify(beanMappingService,Mockito.times(1)).mapTo(movieAndRating.getMovie(),MovieListDTO.class);
    }

    @Test(expectedExceptions = NullArgumentException.class)
    public void testGetRecommendedMoviesNull() {
        List<MovieListDTO> movieListDTOS = movieFacade.getRecommendedMovies(null);
    }

    @Test(expectedExceptions = PersistenceException.class)
    public void testGetRecommendedMoviesNullInternal() {
        UserDTO userDTO = new UserDTO();
        userDTO.setNickName(null);
        userDTO.setName(null);
        userDTO.setId(null);
        userDTO.setEmail(null);

        User user = new User();
        user.setEmail(null);
        user.setNickName(null);
        user.setPasswordHash(null);
        user.setId(null);

        when(beanMappingService.mapTo(userDTO,User.class)).thenReturn(user);

        doThrow(PersistenceException.class).when(userRatingService).findAggregateByGenreForUser(user);

        List<MovieListDTO> movieListDTOS = movieFacade.getRecommendedMovies(userDTO);

    }

    @Test
    public void testCreateMovie() {
        MovieCreateDTO movieCreateDTO = new MovieCreateDTO();
        when(beanMappingService.mapTo(movieCreateDTO,Movie.class)).thenReturn(movie);
        when(movieService.create(movie)).thenReturn(movie);
        when(beanMappingService.mapTo(movieCreateDTO.getImageTitle(),Image.class)).thenReturn(imageTitle);
        List<Image> images = new ArrayList<>();
        images.add(image);
        when(beanMappingService.mapTo(movieCreateDTO.getGallery(),Image.class)).thenReturn(images);
        when(imageService.create(image)).thenReturn(image);
        doNothing().when(movieService).addToGallery(movie,image);

        Long id = movieFacade.createMovie(movieCreateDTO);
        Assert.assertNotNull(id);
        Assert.assertEquals(id,movie.getId());

        Mockito.verify(beanMappingService,Mockito.times(1)).mapTo(movieCreateDTO,Movie.class);
        Mockito.verify(movieService,Mockito.times(1)).create(movie);
        Mockito.verify(beanMappingService,Mockito.times(1)).mapTo(movieCreateDTO.getImageTitle(),Image.class);
        Mockito.verify(beanMappingService,Mockito.times(1)).mapTo(movieCreateDTO.getGallery(),Image.class);
        Mockito.verify(imageService,Mockito.times(1)).create(image);
        Mockito.verify(movieService,Mockito.times(1)).addToGallery(movie,image);
    }

    @Test(expectedExceptions = NullArgumentException.class)
    public void testCreateMovieNull() {
        Long id = movieFacade.createMovie(null);
    }

    @Test(expectedExceptions = NullArgumentException.class)
    public void testCreateMovieNullIntern() {
        MovieCreateDTO movieCreateDTO = new MovieCreateDTO();
        movieCreateDTO.setDescription(null);
        movieCreateDTO.setCountryCode(null);
        movieCreateDTO.setName(null);
        movieCreateDTO.setGenres(null);
        movieCreateDTO.setYearMade(null);
        movieCreateDTO.setActors(null);
        movieCreateDTO.setDirector(null);
        movieCreateDTO.setGallery(null);
        movieCreateDTO.setImageTitle(null);

        Movie movie = new Movie();
        movie.setDescription(null);
        movie.setCountryCode(null);
        movie.setName(null);
        movie.setGenres(null);
        movie.setYearMade(null);
        movie.setActors(null);
        movie.setDirector(null);
        movie.setGallery(null);
        movie.setImageTitle(null);

        when(beanMappingService.mapTo(movieCreateDTO,Movie.class)).thenReturn(movie);
        when(movieService.create(movie)).thenReturn(movie);
        when(beanMappingService.mapTo(movieCreateDTO.getImageTitle(),Image.class)).thenReturn(null);
        List<Image> images = new ArrayList<>();
        images.add(image);
        when(beanMappingService.mapTo(movieCreateDTO.getGallery(),Image.class)).thenReturn(null);
        doThrow(NullArgumentException.class).when(imageService).create(null);
        doThrow(NullArgumentException.class).when(movieService).addToGallery(movie,null);

        Long id = movieFacade.createMovie(movieCreateDTO);
        Assert.assertNotNull(id);
        Assert.assertEquals(id,movie.getId());
    }

    @Test
    public void testUpdateMovieAttrs() {
        Movie movie1 = new Movie();
        movie1.setId(movie.getId());
        movie1.setDescription("dsfasfsdagdfgkjhinenighnerjsauwsfil");
        movieDetailDTOnoRatings.setDescription(movie1.getDescription());

        when(beanMappingService.mapTo(movieDetailDTOnoRatings,Movie.class)).thenReturn(movie1);
        doNothing().when(movieService).updateMovieAttrs(movie1);
        Long id = movieFacade.updateMovieAttrs(movieDetailDTOnoRatings);
        Assert.assertNotNull(id);
        Assert.assertEquals(movie1.getId(),movie.getId());

        Mockito.verify(beanMappingService,Mockito.times(1)).mapTo(movieDetailDTOnoRatings,Movie.class);
        Mockito.verify(movieService,Mockito.times(1)).updateMovieAttrs(movie1);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testUpdateMovieAttrsNullInternal() {
        MovieDetailDTO movieDetailDTOnull = new MovieDetailDTO();
        movieDetailDTOnull.setDescription(null);
        movieDetailDTOnull.setCountryCode(null);
        movieDetailDTOnull.setName(null);
        movieDetailDTOnull.setGenres(null);
        movieDetailDTOnull.setYearMade(null);
        movieDetailDTOnull.setActors(null);
        movieDetailDTOnull.setDirector(null);
        movieDetailDTOnull.setGallery(null);
        movieDetailDTOnull.setImageTitle(null);

        Movie movie = new Movie();
        movie.setDescription(null);
        movie.setCountryCode(null);
        movie.setName(null);
        movie.setGenres(null);
        movie.setYearMade(null);
        movie.setActors(null);
        movie.setDirector(null);
        movie.setGallery(null);
        movie.setImageTitle(null);


        when(beanMappingService.mapTo(movieDetailDTOnull,Movie.class)).thenReturn(movie);
        doThrow(ConstraintViolationException.class).when(movieService).updateMovieAttrs(movie);
        Long id = movieFacade.updateMovieAttrs(movieDetailDTOnull);
    }

    @Test(expectedExceptions = NullArgumentException.class)
    public void testUpdateMovieAttrsNull() {
        Long id = movieFacade.updateMovieAttrs(null);
    }

    @Test
    public void testDeleteMovie() {
        when(movieService.findById(movie.getId())).thenReturn(movie);
        doNothing().when(movieService).delete(movie);
        movieFacade.deleteMovie(movie.getId());

        Mockito.verify(movieService,Mockito.times(1)).findById(movie.getId());
        Mockito.verify(movieService,Mockito.times(1)).delete(movie);
    }

    @Test(expectedExceptions = NullArgumentException.class)
    public void testDeleteMovieNull() {
        movieFacade.deleteMovie(null);
    }

    @Test(expectedExceptions = NullArgumentException.class)
    public void testDeleteMovieNullInternal() {
        Movie movieNull = new Movie();
        movieNull.setDescription(null);
        movieNull.setCountryCode(null);
        movieNull.setName(null);
        movieNull.setGenres(null);
        movieNull.setYearMade(null);
        movieNull.setActors(null);
        movieNull.setDirector(null);
        movieNull.setGallery(null);
        movieNull.setImageTitle(null);

        doThrow(IllegalArgumentException.class).when(movieService).findById(movieNull.getId());
        doThrow(IllegalArgumentException.class).when(movieService).delete(movieNull);
        movieFacade.deleteMovie(movieNull.getId());
    }

    @Test
    public void testChangeTitleImage() {
        when(movieService.findById(imageCreateDTO.getMovieId())).thenReturn(movie);
        when(beanMappingService.mapTo(imageCreateDTO,Image.class)).thenReturn(image);
        when(imageService.create(image)).thenReturn(image);
        doNothing().when(movieService).setImageTitle(movie,image);
        movieFacade.changeTitleImage(imageCreateDTO);

        Mockito.verify(movieService,Mockito.times(1)).findById(imageCreateDTO.getMovieId());
        Mockito.verify(beanMappingService,Mockito.times(1)).mapTo(imageCreateDTO,Image.class);
        Mockito.verify(imageService,Mockito.times(1)).create(image);
        Mockito.verify(movieService,Mockito.times(1)).setImageTitle(movie,image);
    }

    @Test(expectedExceptions = NullArgumentException.class)
    public void testChangeTitleImageNull() {
        movieFacade.changeTitleImage(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testChangeTitleImageNullIntern() {
        ImageCreateDTO imageCreateDTOnull= new ImageCreateDTO();
        imageCreateDTOnull.setImage(null);
        imageCreateDTOnull.setImageMimeType(null);
        imageCreateDTOnull.setMovieId(null);

        Image nullImage = new Image();
        nullImage.setImage(null);
        nullImage.setImageMimeType(null);

        doThrow(IllegalArgumentException.class).when(movieService).findById(imageCreateDTO.getMovieId());
        when(beanMappingService.mapTo(imageCreateDTOnull,Image.class)).thenReturn(nullImage);
        doThrow(ConstraintViolationException.class).when(imageService).create(nullImage);
        doThrow(IllegalArgumentException.class).when(movieService).setImageTitle(movie,image);
        movieFacade.changeTitleImage(imageCreateDTO);
    }

    @Test
    public void testAddImage() {
        when(movieService.findById(imageCreateDTO.getMovieId())).thenReturn(movie);
        when(beanMappingService.mapTo(imageCreateDTO,Image.class)).thenReturn(image);
        when(imageService.create(image)).thenReturn(image);
        doNothing().when(movieService).addToGallery(movie,image);
        movieFacade.addImage(imageCreateDTO);

        Mockito.verify(movieService,Mockito.times(1)).findById(imageCreateDTO.getMovieId());
        Mockito.verify(beanMappingService,Mockito.times(1)).mapTo(imageCreateDTO,Image.class);
        Mockito.verify(imageService,Mockito.times(1)).create(image);
        Mockito.verify(movieService,Mockito.times(1)).addToGallery(movie,image);
    }

    @Test(expectedExceptions = NullArgumentException.class)
    public void testAddImageNull() {
        movieFacade.addImage(null);
    }

    @Test
    public void testAddImageNullInternal() {
        ImageCreateDTO imageCreateDTOnull = new ImageCreateDTO();
        imageCreateDTOnull.setImage(null);
        imageCreateDTOnull.setImageMimeType(null);
        imageCreateDTOnull.setMovieId(null);

        Image nullImage = new Image();
        nullImage.setImage(null);
        nullImage.setImageMimeType(null);

        doThrow(IllegalArgumentException.class).when(movieService).findById(imageCreateDTOnull.getMovieId());
        when(beanMappingService.mapTo(imageCreateDTOnull,Image.class)).thenReturn(nullImage);
        doThrow(ConstraintViolationException.class).when(imageService).create(nullImage);
        doNothing().when(movieService).addToGallery(movie,nullImage);
        movieFacade.addImage(imageCreateDTO);
    }

    @Test
    public void testDeleteImage() {
        image.setMovieGallery(movie);
        when(imageService.getById(image.getId())).thenReturn(image);
        when(movieService.findById(image.getMovieGallery().getId())).thenReturn(movie);
        doNothing().when(movieService).removeFromGallery(movie,image);
        movieFacade.deleteImage(image.getId());

        Mockito.verify(imageService,Mockito.times(1)).getById(image.getId());
        Mockito.verify(movieService,Mockito.times(1)).findById(image.getMovieGallery().getId());
        Mockito.verify(movieService,Mockito.times(1)).removeFromGallery(movie,image);
    }

    @Test(expectedExceptions = NullArgumentException.class)
    public void testDeleteImageNull() {
        movieFacade.deleteImage(null);
    }

    @Test(expectedExceptions = ObjectNotFoundException.class)
    public void testDeleteImageNullInternal() {
        Image nullImage = new Image();
        nullImage.setImage(null);
        nullImage.setImageMimeType(null);
        nullImage.setMovieGallery(null);

        doThrow(NullArgumentException.class).when(imageService).getById(nullImage.getId());
        doThrow(NullArgumentException.class).when(movieService).findById(null);
        doNothing().when(movieService).removeFromGallery(movie,nullImage);
        movieFacade.deleteImage(image.getId());

    }

    @Test
    public void testAddActor() {
        when(movieService.findById(personToMovieDTO.getMovieId())).thenReturn(movie);
        when(beanMappingService.mapTo(personToMovieDTO,Person.class)).thenReturn(actor);
        doNothing().when(movieService).addActor(movie,actor);

        movieFacade.addActor(personToMovieDTO);

        Mockito.verify(movieService,Mockito.times(1)).findById(personToMovieDTO.getMovieId());
        Mockito.verify(beanMappingService,Mockito.times(1)).mapTo(personToMovieDTO,Person.class);
        Mockito.verify(movieService,Mockito.times(1)).addActor(movie,actor);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddActorNullInternal() {
        PersonToMovieDTO personToMovieDTOnull = new PersonToMovieDTO();
        personToMovieDTOnull.setId(null);
        personToMovieDTOnull.setName(null);
        personToMovieDTOnull.setMovieId(null);

        doThrow(IllegalArgumentException.class).when(movieService).findById(personToMovieDTOnull.getMovieId());

        movieFacade.addActor(personToMovieDTOnull);
    }

    @Test(expectedExceptions = NullArgumentException.class)
    public void testAddActorNull() {
        movieFacade.addActor(null);
    }

    @Test
    public void testDeleteActor() {
        when(movieService.findById(personToMovieDTO.getMovieId())).thenReturn(movie);
        when(beanMappingService.mapTo(personToMovieDTO,Person.class)).thenReturn(actor);
        doNothing().when(movieService).removeActor(movie,actor);

        movieFacade.deleteActor(personToMovieDTO);

        Mockito.verify(movieService,Mockito.times(1)).findById(personToMovieDTO.getMovieId());
        Mockito.verify(beanMappingService,Mockito.times(1)).mapTo(personToMovieDTO,Person.class);
        Mockito.verify(movieService,Mockito.times(1)).removeActor(movie,actor);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDeleteActorNullInternal() {
        PersonToMovieDTO personToMovieDTOnull = new PersonToMovieDTO();
        personToMovieDTOnull.setId(null);
        personToMovieDTOnull.setName(null);
        personToMovieDTOnull.setMovieId(null);

        doThrow(IllegalArgumentException.class).when(movieService).findById(personToMovieDTOnull.getMovieId());

        movieFacade.deleteActor(personToMovieDTOnull);
    }

    @Test(expectedExceptions = NullArgumentException.class)
    public void testDeleteActorNull() {
        movieFacade.deleteActor(null);
    }


    @Test
    public void testAddGenre() {
        when(movieService.findById(genreToMovie.getMovieId())).thenReturn(movie);
        when(beanMappingService.mapTo(genreToMovie,Genre.class)).thenReturn(genre);
        doNothing().when(movieService).addGenre(movie,genre);

        movieFacade.addGenre(genreToMovie);

        Mockito.verify(movieService,Mockito.times(1)).findById(genreToMovie.getMovieId());
        Mockito.verify(beanMappingService,Mockito.times(1)).mapTo(genreToMovie,Genre.class);
        Mockito.verify(movieService,Mockito.times(1)).addGenre(movie,genre);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddGenreNullInternal() {
        GenreToMovieDTO genreToMovieDTOnull = new GenreToMovieDTO();
        genreToMovieDTOnull.setMovieId(null);
        genreToMovieDTOnull.setId(null);
        genreToMovieDTOnull.setName(null);

        doThrow(IllegalArgumentException.class).when(movieService).findById(genreToMovieDTOnull.getMovieId());

        movieFacade.addGenre(genreToMovieDTOnull);
    }

    @Test(expectedExceptions = NullArgumentException.class)
    public void testAddGenreNull() {
        movieFacade.addGenre(null);
    }

    @Test
    public void testRemoveGenre() {
        when(movieService.findById(genreToMovie.getMovieId())).thenReturn(movie);
        when(beanMappingService.mapTo(genreToMovie,Genre.class)).thenReturn(genre);
        doNothing().when(movieService).removeGenre(movie,genre);

        movieFacade.removeGenre(genreToMovie);

        Mockito.verify(movieService,Mockito.times(1)).findById(genreToMovie.getMovieId());
        Mockito.verify(beanMappingService,Mockito.times(1)).mapTo(genreToMovie,Genre.class);
        Mockito.verify(movieService,Mockito.times(1)).removeGenre(movie,genre);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testRemoveGenreNullInternal() {
        GenreToMovieDTO genreToMovieDTOnull = new GenreToMovieDTO();
        genreToMovieDTOnull.setMovieId(null);
        genreToMovieDTOnull.setId(null);
        genreToMovieDTOnull.setName(null);

        doThrow(IllegalArgumentException.class).when(movieService).findById(genreToMovie.getMovieId());

        movieFacade.removeGenre(genreToMovie);
    }

    @Test(expectedExceptions = NullArgumentException.class)
    public void testRemoveGenreNull() {
        movieFacade.removeGenre(null);
    }

    @Test
    public void testChangeDirector() {
        when(movieService.findById(personToMovieDTO.getMovieId())).thenReturn(movie);
        when(beanMappingService.mapTo(personToMovieDTO,Person.class)).thenReturn(actor);
        doNothing().when(movieService).changeDirector(movie,actor);

        movieFacade.changeDirector(personToMovieDTO);

        Mockito.verify(movieService,Mockito.times(1)).findById(personToMovieDTO.getMovieId());
        Mockito.verify(beanMappingService,Mockito.times(1)).mapTo(personToMovieDTO,Person.class);
        Mockito.verify(movieService,Mockito.times(1)).changeDirector(movie,actor);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testChangeDirectorNullInternal() {
        PersonToMovieDTO personToMovieDTOnull = new PersonToMovieDTO();
        personToMovieDTOnull.setId(null);
        personToMovieDTOnull.setName(null);
        personToMovieDTOnull.setMovieId(null);

        doThrow(IllegalArgumentException.class).when(movieService).findById(personToMovieDTOnull.getMovieId());

        movieFacade.changeDirector(personToMovieDTOnull);
    }

    @Test(expectedExceptions = NullArgumentException.class)
    public void testChangeDirectorNull(){
        movieFacade.changeDirector(null);
    }

}