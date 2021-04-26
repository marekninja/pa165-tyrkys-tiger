package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.jpql.GenreAndRating;

import java.time.LocalDate;
import java.util.List;

/**
 * Service for Movie
 * @author Marek Petrovič
 */
public interface MovieService {
    Movie findById(Long id);
    List<Movie> findByParameters(List<Genre> genreList, List<Person> personList, String movieName, LocalDate yearMade, String countryCode);

    //todo test
    List<Movie> getRecommendedMovies(List<Genre> genres, User user);

    Movie create(Movie movie);
    Movie update(Movie movie);
    void delete(Movie movie);
    void deleteUserRating(UserRating userRating);
}
