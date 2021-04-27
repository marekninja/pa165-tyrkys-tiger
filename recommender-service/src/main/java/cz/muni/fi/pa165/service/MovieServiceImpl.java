package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.MovieDao;
import cz.muni.fi.pa165.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Marek Petrovič
 */
//TODO HANDLE EXCEPTIONS
// not much exception handling in example project...
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

    //TODO test
    @Override
    public List<Movie> findByParameters(List<Genre> genreList, List<Person> personList, String movieName, LocalDate yearMade, String countryCode) {
        if (yearMade != null && yearMade.getYear() > LocalDate.now().getYear()){
            throw new IllegalArgumentException("It is not yet possible to search movies from future! " +
                    "yearMade was "+yearMade.getYear());
        }
        return movieDao.findByParameters(genreList,personList,movieName,yearMade,countryCode);
    }


    //TODO test
    @Override
    public List<Movie> getRecommendedMovies(List<Genre> genres, User user) {
        int maxOfGenre = 2;
        return movieDao.getMoviesOfGenres(genres,maxOfGenre,user);
    }

    @Override
    public Movie create(Movie movie) {
        movieDao.create(movie);
        return movie;
    }

    @Override
    public Movie update(Movie movie) {
        return movieDao.update(movie);
    }

    @Override
    public void delete(Movie movie) {
        movieDao.remove(movie);
    }

    @Override
    public void deleteUserRating(UserRating userRating) {

        userRatingService.deleteUserRating(userRating);
    }
}
