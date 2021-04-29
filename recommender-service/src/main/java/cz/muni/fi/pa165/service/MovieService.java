package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.jpql.MovieAndRating;

import java.time.LocalDate;
import java.util.List;

/**
 * Service for Movie
 * @author Marek Petrovič
 */
public interface MovieService {
    Movie findById(Long id);
    List<MovieAndRating> findByParameters(List<Genre> genreList, List<Person> personList, String movieName, LocalDate yearMade, String countryCode);

    //todo test
    List<MovieAndRating> getRecommendedMovies(List<Genre> genres, User user);

    Movie create(Movie movie);
    void delete(Movie movie);
    void deleteUserRating(UserRating userRating);

    void setImageTitle(Movie movie, Image imageTitle);

    void addToGallery(Movie movie, Image image);

    void removeFromGallery(Movie movie, Image image);

    void addActor(Movie movie, Person person);

    void removeActor(Movie movie, Person person);

    void changeDirector(Movie movie, Person person);

    void addGenre(Movie movie, Genre genre);

    void removeGenre(Movie movie, Genre genre);

    void updateMovieAttrs(Movie movie);
}
