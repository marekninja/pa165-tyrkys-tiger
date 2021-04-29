package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.jpql.MovieAndRating;

import java.time.LocalDate;
import java.util.List;

/**
 * Service for Movie
 * @author Marek Petrovič
 */
//todo test all
public interface MovieService {

    /**
     * Returns User from DB by his ID
     * @param id ID in DB
     * @return Movie
     */
    Movie findById(Long id);

    /**
     * Return Movies from DB which satisfy search criteria
     * All one big AND condition, all can be null
     *
     * @param genreList List of genres
     * @param personList List of Person of Movie (actor/director)
     * @param movieName String some part of Movie name
     * @param yearMade LocalDate year of Movie production
     * @param countryCode String country code of movie origin
     * @return List of pairs of Movie and aggregated overall Score of Movie
     */
    List<MovieAndRating> findByParameters(List<Genre> genreList, List<Person> personList, String movieName, LocalDate yearMade, String countryCode);


    /**
     * Returns recommended movies by Genres for User
     * User is provided so it won't return already seen(rated by User) Movies
     *
     * @param genres List of genres
     * @param user User
     * @return List of pairs of Movie and aggregated overall Score of Movie
     */
    List<MovieAndRating> getRecommendedMovies(List<Genre> genres, User user);

    /**
     * Creates new Movie in DB
     * @param movie Movie
     * @return Movie created
     */
    Movie create(Movie movie);

    /**
     * Deletes Movie from DB
     * @param movie Movie to be deleted
     */
    void delete(Movie movie);

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
