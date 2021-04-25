package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.entity.Image;
import cz.muni.fi.pa165.entity.Movie;
import cz.muni.fi.pa165.entity.Person;
import cz.muni.fi.pa165.entity.UserRating;
import cz.muni.fi.pa165.facade.MovieFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.ImageService;
import cz.muni.fi.pa165.service.MovieService;
import cz.muni.fi.pa165.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Marek Petrovič
 */
@Service
public class MovieFacadeImpl implements MovieFacade {

    @Autowired
    MovieService movieService;

    @Autowired
    ImageService imageService;

    @Autowired
    PersonService personService;

    @Autowired
    BeanMappingService beanMappingService;

    @Override
    public MovieDetailDTO findMovieById(Long movieId) {
        Movie movie =  movieService.findById(movieId);
        return (movie == null) ? null :
                beanMappingService.mapTo(movie, MovieDetailDTO.class);
    }

    @Override
    public List<MovieListDTO> findMovieByParameters(List<Long> genreIds, List<Long> personIds, String movieName, Integer yearMade, String countryCode) {
        return null;
    }

    @Override
    public Long createMovie(MovieCreateDTO movieCreateDTO) {
        Movie movie = beanMappingService.mapTo(movieCreateDTO, Movie.class);
        Movie savedMovie = movieService.create(movie);
        return savedMovie.getId();
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
        savedImage.setMovieTitle(movie);

        imageService.update(savedImage);
        movieService.update(movie);
    }

    @Override
    public void addImage(ImageCreateDTO imageCreateDTO) {
        Movie movie = movieService.findById(imageCreateDTO.getMovieId());

        Image image = beanMappingService.mapTo(imageCreateDTO, Image.class);
        Image savedImage = imageService.create(image);

        movie.addToGallery(savedImage);
        savedImage.setMovieGallery(movie);

        movieService.update(movie);
        imageService.update(savedImage);
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

    //TODO co keby bolo jednostranny vztah Movie -> Person,
    // teda by Person nemusel drzat info o Movie
    @Override
    public void addActor(Long movieId, PersonDTO personDTO) {
        Movie movie = movieService.findById(movieId);
        Person person = beanMappingService.mapTo(personDTO,Person.class);

        movie.addActor(person);
        person.addActorsMovies(movie);
        movieService.update(movie);
        personService.update(person);

    }

    //TODO tiez keby mozeme urobit jednostranny vztah, by bolo toto jednoduchsie
    @Override
    public void deleteActor(Long movieId, PersonDTO personDTO) {
        Movie movie = movieService.findById(movieId);
        Person person = beanMappingService.mapTo(personDTO,Person.class);

        movie.removeActor(person);
        person.getActorsMovies().remove(movie);

        movieService.update(movie);
        personService.update(person);
    }

    @Override
    public void changeDirector(Long movieId, PersonDTO personDTO) {
        Movie movie = movieService.findById(movieId);
        Person person = beanMappingService.mapTo(personDTO,Person.class);

        Person oldDirector = movie.getDirector();
        movie.setDirector(person);
        person.addDirectedMovies(movie);

        oldDirector.getDirectedMovies().remove(movie);

        movieService.update(movie);
        personService.update(person);
        personService.update(oldDirector);
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
