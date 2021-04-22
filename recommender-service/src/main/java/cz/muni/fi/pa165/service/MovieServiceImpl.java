package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.MovieDao;
import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.entity.Movie;
import cz.muni.fi.pa165.entity.Person;
import cz.muni.fi.pa165.entity.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Marek Petrovič
 */
@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieDao movieDao;

    @Autowired
    UserRatingService userRatingService;

    @Override
    public Movie findById(Long id) {
        return movieDao.findById(id);
    }

    //TODO findByParameters
    @Override
    public List<Movie> findByParameters(List<Genre> genreList, List<Person> personList, String movieName, LocalDate yearMade, String countryCode) {
        return null;
    }

    @Override
    public Movie create(Movie movie) {
        movieDao.create(movie);
        return movie;
    }

    @Override
    public Movie update(Movie movie) {
        movieDao.update(movie);
        return movie;
    }

    @Override
    public void delete(Movie movie) {
        movieDao.remove(movie);

    }

    @Override
    public void deleteUserRating(UserRating userRating) {
        userRatingService.delete(userRating);
    }
}