package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.entity.Movie;
import cz.muni.fi.pa165.entity.Person;
import cz.muni.fi.pa165.entity.UserRating;

import java.time.LocalDate;
import java.util.List;

/**
 * Service for Movie
 * @author Marek Petrovič
 */
public interface MovieService {
    public Movie findById(Long id);
    public List<Movie> findByParameters(List<Genre> genreList, List<Person> personList, String movieName, LocalDate yearMade, String countryCode);
    public Movie create(Movie movie);
    public Movie update(Movie movie);
    public void delete(Movie movie);
    public void deleteUserRating(UserRating userRating);
}
