package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.facade.MovieFacade;
import cz.muni.fi.pa165.jpql.GenreAndRating;
import cz.muni.fi.pa165.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation of MovieFacade.
 * Contains most of the use-cases of Movie Recommender System.
 *
 * @author Marek Petrovič
 */
//TODO test all
@Service
@Transactional
public class MovieFacadeImpl implements MovieFacade {

    @Autowired
    MovieService movieService;

    @Autowired
    ImageService imageService;

    @Autowired
    PersonService personService;

    @Autowired
    UserRatingService userRatingService;

    @Autowired
    BeanMappingService beanMappingService;

    @Override
    public MovieDetailDTO findMovieById(Long movieId) {
        Movie movie =  movieService.findById(movieId);
        return (movie == null) ? null :
                beanMappingService.mapTo(movie, MovieDetailDTO.class);
    }

    @Override
    public List<MovieListDTO> findMovieByParameters(ParametersDTO parametersDTO) {
        List<Genre> genres = beanMappingService.mapTo(parametersDTO.getGenreDTOList(),Genre.class);
        List<Person> personList = beanMappingService.mapTo(parametersDTO.getPersonDTOList(),Person.class);
        String name = parametersDTO.getMovieName();
        Integer year = parametersDTO.getYearMade();
        LocalDate yearMade = LocalDate.of(year,1,1);
        String countryCode = parametersDTO.getCountryCode();

        List<Movie> movies = movieService.findByParameters(genres,personList,name,yearMade,countryCode);

        return beanMappingService.mapTo(movies,MovieListDTO.class);
    }


    @Override
    public List<MovieListDTO> getRecommendedMovies(UserDTO userDTO) {
        User user = beanMappingService.mapTo(userDTO,User.class);

        List<GenreAndRating> genreAndRatings = userRatingService.findAggregateByGenreForUser(user);

        Collections.sort(genreAndRatings);
        genreAndRatings = genreAndRatings.stream().limit(5).collect(Collectors.toList());

        List<Genre> genres = new ArrayList<>();
        for (GenreAndRating genreAndRating: genreAndRatings) {
            genres.add(genreAndRating.getGenre());
        }

        List<Movie> movies = movieService.getRecommendedMovies(genres, user);
        //TODO chyba agregovane skore, treba v UserRatingService metodu na agregovane skore jedneho Movie
        List<MovieListDTO> movieListDTOS= beanMappingService.mapTo(movies,MovieListDTO.class);

        return movieListDTOS;
    }

    @Override
    public Long createMovie(MovieCreateDTO movieCreateDTO) {
        Movie movie = beanMappingService.mapTo(movieCreateDTO, Movie.class);
        movie = movieService.create(movie);

        Image imageTitle = beanMappingService.mapTo(movieCreateDTO.getImageTitle(),Image.class);
        imageTitle = imageService.create(imageTitle);
        movie.setImageTitle(imageTitle);

        List<Image> imageGallery = beanMappingService.mapTo(movieCreateDTO.getGallery(),Image.class);
        for (Image image:imageGallery) {
            image.setMovieGallery(movie);
            movie.addToGallery(image);
            imageService.create(image);
        }

        return movie.getId();
    }

    @Override
    public Long updateMovie(MovieCreateDTO movieCreateDTO) {
        Movie movie = beanMappingService.mapTo(movieCreateDTO, Movie.class);
        Movie updatedMovie = movieService.update(movie);

        return updatedMovie.getId();
    }

    @Override
    public void deleteMovie(Long movieId) {
        movieService.delete(movieService.findById(movieId));
    }

    @Override
    public void changeTitleImage(ImageCreateDTO imageCreateDTO) {
        Movie movie = movieService.findById(imageCreateDTO.getMovieId());

        Image image = beanMappingService.mapTo(imageCreateDTO, Image.class);
        Image savedImage = imageService.create(image);

        movie.setImageTitle(savedImage);

        movieService.update(movie);
    }

    @Override
    public void addImage(ImageCreateDTO imageCreateDTO) {
        Movie movie = movieService.findById(imageCreateDTO.getMovieId());

        Image image = beanMappingService.mapTo(imageCreateDTO, Image.class);
        Image savedImage = imageService.create(image);

        savedImage.setMovieGallery(movie);
        movie.addToGallery(savedImage);

        imageService.update(savedImage);
        movieService.update(movie);

    }

    @Override
    public void deleteImage(Long imageId) {
        Image image = imageService.getById(imageId);

        Movie movie = movieService.findById(image.getMovieGallery().getId());
        if (movie != null){
            movie.removeFromGallery(image);
        }
        movieService.update(movie);
        imageService.delete(image);
    }

    @Override
    public void addActor(PersonDTO personDTO) {
        Movie movie = movieService.findById(personDTO.getMovieId());
        Person person = beanMappingService.mapTo(personDTO,Person.class);
        movie.addActor(person);
        movieService.update(movie);
    }

    @Override
    public void deleteActor(PersonDTO personDTO) {
        Movie movie = movieService.findById(personDTO.getMovieId());

        Person person = beanMappingService.mapTo(personDTO,Person.class);

        movie.removeActor(person);
        movieService.update(movie);
    }

    @Override
    public void changeDirector(Long movieId, PersonDTO personDTO) {
        Movie movie = movieService.findById(personDTO.getMovieId());
        Person person = beanMappingService.mapTo(personDTO,Person.class);
        movie.setDirector(person);
        movieService.update(movie);
    }

    //TODO UserRating mazanie
    @Override
    public void deleteUserRating(UserRatingDTO userRatingDTO) {
        Movie movie = movieService.findById(userRatingDTO.getMovieDetailDTO().getId());
        UserRating userRating = beanMappingService.mapTo(userRatingDTO, UserRating.class);
        movie.removeUserRating(userRating);
        movieService.update(movie);
        //TODO mazanie UserRatingService
//        userRatingService.delete(userRating);
    }
}
