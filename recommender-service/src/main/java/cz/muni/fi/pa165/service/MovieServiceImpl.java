package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.MovieDao;
import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.jpql.MovieAndRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Marek Petrovič
 */
//TODO HANDLE EXCEPTIONS
// not much exception handling in example project...
//todo check if all methods are tested
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieDao movieDao;

    private final UserRatingService userRatingService;

    @Autowired
    public MovieServiceImpl(MovieDao movieDao, UserRatingService userRatingService) {
        this.movieDao = movieDao;
        this.userRatingService = userRatingService;
    }

    @Override
    public Movie findById(Long id) {
        return movieDao.findById(id);
    }

    //TODO test
    @Override
    public List<MovieAndRating> findByParameters(List<Genre> genreList, List<Person> personList, String movieName, LocalDate yearMade, String countryCode) {
        if (yearMade != null && yearMade.getYear() > LocalDate.now().getYear()){
            throw new IllegalArgumentException("It is not yet possible to search movies from future! " +
                    "yearMade was "+yearMade.getYear());
        }
        return movieDao.findByParameters(genreList,personList,movieName,yearMade,countryCode);
    }


    //TODO test
    @Override
    public List<MovieAndRating> getRecommendedMovies(List<Genre> genres, User user) {
        int maxOfGenre = 2;
        return movieDao.getMoviesOfGenres(genres,maxOfGenre,user);
    }

    @Override
    public Movie create(Movie movie) {
        movieDao.create(movie);
        return movie;
    }

    @Override
    public void delete(Movie movie) {
        movieDao.remove(movie);
    }


    @Override
    public void setImageTitle(Movie movie, Image imageTitle) {
        movie.setImageTitle(imageTitle);
        movieDao.update(movie);
    }

    @Override
    public void addToGallery(Movie movie, Image image) {
        movie.addToGallery(image);
        movieDao.update(movie);
    }

    //todo test if removes image relationship with dao
    @Override
    public void removeFromGallery(Movie movie, Image image) {
        movie.removeFromGallery(image);
        movieDao.update(movie);
    }

    @Override
    public void addActor(Movie movie, Person person) {
        movie.addActor(person);
        movieDao.update(movie);
    }

    @Override
    public void removeActor(Movie movie, Person person) {
        movie.removeActor(person);
        movieDao.update(movie);
    }

    @Override
    public void changeDirector(Movie movie, Person person) {
        movie.setDirector(person);
        movieDao.update(movie);
    }

    @Override
    public void addGenre(Movie movie, Genre genre) {
        movie.addGenre(genre);
        movieDao.update(movie);
    }

    @Override
    public void removeGenre(Movie movie, Genre genre) {
        movie.removeGenre(genre);
        movieDao.update(movie);
    }

    @Override
    public void updateMovieAttrs(Movie movie) {
        Movie original = this.findById(movie.getId());
        original.setName(movie.getName());
        original.setDescription(movie.getDescription());
        original.setYearMade(movie.getYearMade());
        original.setCountryCode(movie.getCountryCode());
        original.setLengthMin(movie.getLengthMin());
        movieDao.update(original);
    }
}
