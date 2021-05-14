package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.facade.MovieFacade;
import cz.muni.fi.pa165.jpql.GenreAndRating;
import cz.muni.fi.pa165.jpql.MovieAndRating;
import cz.muni.fi.pa165.service.*;
import cz.muni.fi.pa165.service.utils.Validator;


import org.hibernate.ObjectNotFoundException;
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
@Service
@Transactional
public class MovieFacadeImpl implements MovieFacade {

    private final MovieService movieService;

    private final ImageService imageService;

    private final PersonService personService;

    private final UserRatingService userRatingService;

    private final BeanMappingService beanMappingService;

    @Autowired
    public MovieFacadeImpl(MovieService movieService, ImageService imageService, PersonService personService, UserRatingService userRatingService, BeanMappingService beanMappingService) {
        this.movieService = movieService;
        this.imageService = imageService;
        this.personService = personService;
        this.userRatingService = userRatingService;
        this.beanMappingService = beanMappingService;
    }

    @Override
    public MovieDetailDTO findMovieById(Long movieId) {
        Validator.validate(this.getClass(),movieId,new Object() {}.getClass().getEnclosingMethod().getName()+
                " one of parameters was null");
//        Movie movie =  movieService.findById(movieId);
        MovieAndRating movieAndRating = movieService.findByIdWithRating(movieId);


        if (movieAndRating != null){
            MovieDetailDTO movieDetailDTO = beanMappingService.mapTo(movieAndRating.getMovie(),MovieDetailDTO.class);
            movieDetailDTO.setRatingAgg(movieAndRating.getOverallScore());
            return movieDetailDTO;
        }
        return null;
//        return (movie == null) ? null :
//                beanMappingService.mapTo(movie, MovieDetailDTO.class);
    }

    @Override
    public List<MovieListDTO> findMovieByParameters(ParametersDTO parametersDTO) {
        Validator.validate(this.getClass(),parametersDTO,new Object() {}.getClass().getEnclosingMethod().getName()+
                " one of parameters was null");
        List<Genre> genres = null;
        if (parametersDTO.getGenreDTOList() != null){
            genres = beanMappingService.mapTo(parametersDTO.getGenreDTOList(),Genre.class);
        }

        List<Person> personList = null;
        if (parametersDTO.getPersonDTOList() != null){
            personList = beanMappingService.mapTo(parametersDTO.getPersonDTOList(),Person.class);
        }

        String name = parametersDTO.getMovieName();
        Integer year = parametersDTO.getYearMade();

        LocalDate yearMade = null;
        if (year != null){
            yearMade = LocalDate.of(year,1,1);
        }
        String countryCode = parametersDTO.getCountryCode();

        List<MovieAndRating> movies = movieService.findByParameters(genres,personList,name,yearMade,countryCode);

        List<MovieListDTO> movieListDTOS = new ArrayList<>();
        for (MovieAndRating movieAndRating: movies) {
            MovieListDTO movieListDTO = beanMappingService.mapTo(movieAndRating.getMovie(),MovieListDTO.class);
            movieListDTO.setOverallScoreAgg(movieAndRating.getOverallScore().floatValue());
            movieListDTOS.add(movieListDTO);
        }

        return movieListDTOS;
    }


    @Override
    public List<MovieListDTO> getRecommendedMovies(UserDTO userDTO) {
        Validator.validate(this.getClass(),userDTO,new Object() {}.getClass().getEnclosingMethod().getName()+
                " one of parameters was null");
        User user = beanMappingService.mapTo(userDTO,User.class);

        List<GenreAndRating> genreAndRatings = userRatingService.findAggregateByGenreForUser(user);

        Collections.sort(genreAndRatings);
        genreAndRatings = genreAndRatings.stream().limit(5).collect(Collectors.toList());

        List<Genre> genres = new ArrayList<>();
        for (GenreAndRating genreAndRating: genreAndRatings) {
            genres.add(genreAndRating.getGenre());
        }

        List<MovieAndRating> movies = movieService.getRecommendedMovies(genres, user);

        List<MovieListDTO> movieListDTOS = new ArrayList<>();
        for (MovieAndRating movieAndRating: movies) {
            MovieListDTO movieListDTO = beanMappingService.mapTo(movieAndRating.getMovie(),MovieListDTO.class);
            movieListDTO.setOverallScoreAgg(movieAndRating.getOverallScore().floatValue());
            movieListDTOS.add(movieListDTO);
        }

        return movieListDTOS;
    }

    @Override
    public Long createMovie(MovieCreateDTO movieCreateDTO) {
        Validator.validate(this.getClass(),movieCreateDTO,new Object() {}.getClass().getEnclosingMethod().getName()+
                " one of parameters was null");
        Movie movie = beanMappingService.mapTo(movieCreateDTO, Movie.class);
        movie = movieService.create(movie);

        Image imageTitle = beanMappingService.mapTo(movieCreateDTO.getImageTitle(),Image.class);
        imageTitle = imageService.create(imageTitle);
        movieService.setImageTitle(movie,imageTitle);

        List<Image> imageGallery = beanMappingService.mapTo(movieCreateDTO.getGallery(),Image.class);
        for (Image image:imageGallery) {
            imageService.create(image);
            movieService.addToGallery(movie,image);
        }

        return movie.getId();
    }

    @Override
    public Long updateMovieAttrs(MovieDetailDTO movieDetailDTO) {
        Validator.validate(this.getClass(),movieDetailDTO,new Object() {}.getClass().getEnclosingMethod().getName()+
                " one of parameters was null");
        Movie movie = beanMappingService.mapTo(movieDetailDTO,Movie.class);
        movieService.updateMovieAttrs(movie);
        return movie.getId();
    }

    @Override
    public void deleteMovie(Long movieId) {
        Validator.validate(this.getClass(),movieId,new Object() {}.getClass().getEnclosingMethod().getName()+
                " one of parameters was null");
        movieService.delete(movieService.findById(movieId));
    }

    @Override
    public void changeTitleImage(ImageCreateDTO imageCreateDTO) {
        Validator.validate(this.getClass(),imageCreateDTO,new Object() {}.getClass().getEnclosingMethod().getName()+
                " one of parameters was null");

        Movie movie = movieService.findById(imageCreateDTO.getMovieId());

        Image image = beanMappingService.mapTo(imageCreateDTO, Image.class);
        Image savedImage = imageService.create(image);
        movieService.setImageTitle(movie,savedImage);
    }

    @Override
    public void addImage(ImageCreateDTO imageCreateDTO) {
        Validator.validate(this.getClass(),imageCreateDTO,new Object() {}.getClass().getEnclosingMethod().getName()+
                " one of parameters was null");

        Movie movie = movieService.findById(imageCreateDTO.getMovieId());

        Image image = beanMappingService.mapTo(imageCreateDTO, Image.class);
        Image savedImage = imageService.create(image);

        movieService.addToGallery(movie,savedImage);
    }


    @Override
    public void deleteImage(Long imageId) {
        Validator.validate(this.getClass(),imageId,new Object() {}.getClass().getEnclosingMethod().getName()+
                " one of parameters was null");
        Image image = imageService.getById(imageId);
        if (image == null){
            throw new ObjectNotFoundException(imageId,"provided imageId does not exist");
        }
        Movie movie = movieService.findById(image.getMovieGallery().getId());
        if (movie != null){
            movieService.removeFromGallery(movie,image);
        }
    }

    @Override
    public void addActor(PersonToMovieDTO personDTO) {
        Validator.validate(this.getClass(),personDTO,new Object() {}.getClass().getEnclosingMethod().getName()+
                " one of parameters was null");

        Movie movie = movieService.findById(personDTO.getMovieId());
        Person person = beanMappingService.mapTo(personDTO,Person.class);
        movieService.addActor(movie,person);
    }

    @Override
    public void deleteActor(PersonToMovieDTO personDTO) {
        Validator.validate(this.getClass(),personDTO,new Object() {}.getClass().getEnclosingMethod().getName()+
                " one of parameters was null");

        Movie movie = movieService.findById(personDTO.getMovieId());
        Person person = beanMappingService.mapTo(personDTO,Person.class);
        movieService.removeActor(movie,person);
    }

    @Override
    public void addGenre(GenreToMovieDTO genreToMovieDTO) {
        Validator.validate(this.getClass(),genreToMovieDTO,new Object() {}.getClass().getEnclosingMethod().getName()+
                " one of parameters was null");

        Movie movie = movieService.findById(genreToMovieDTO.getMovieId());
        Genre genre = beanMappingService.mapTo(genreToMovieDTO,Genre.class);
        movieService.addGenre(movie,genre);
    }

    @Override
    public void removeGenre(GenreToMovieDTO genreToMovieDTO) {
        Validator.validate(this.getClass(),genreToMovieDTO,new Object() {}.getClass().getEnclosingMethod().getName()+
                " one of parameters was null");

        Movie movie = movieService.findById(genreToMovieDTO.getMovieId());
        Genre genre = beanMappingService.mapTo(genreToMovieDTO,Genre.class);
        movieService.removeGenre(movie,genre);
    }

    @Override
    public void changeDirector(PersonToMovieDTO personDTO) {
        Validator.validate(this.getClass(),personDTO,new Object() {}.getClass().getEnclosingMethod().getName()+
                " one of parameters was null");

        Movie movie = movieService.findById(personDTO.getMovieId());
        Person person = beanMappingService.mapTo(personDTO,Person.class);
        movieService.changeDirector(movie,person);
    }
}
