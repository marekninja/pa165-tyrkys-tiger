package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.*;
import cz.muni.fi.pa165.jpql.MovieAndRating;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface that defines service access to {@link Movie}
 * @author Marek Petrovič
 */
public interface MovieService {

    /**
     * Returns User from DB by his ID
     * @param id ID in DB
     * @return Movie
     */
    Movie findById(Long id);


    /**
     * Returns one Movie, found by Id,
     * with aggregated UserRating
     * @param id ID of Movie
     * @return MovieAndRating
     */
    MovieAndRating findByIdWithRating(Long id);


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
     * Number of max movies per Genre is not specified.
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

    /**
     * Sets new titleImage of Movie
     * Image should be already created in DB
     * Previous titleImage should be deleted,
     * orphanRemoval=True shoul be set in Movie on titleImage attr
     *
     * @param movie Movie to assign title image to
     * @param imageTitle Image
     */
    void setImageTitle(Movie movie, Image imageTitle);

    /**
     * Adds Image to Movie gallery
     * @param movie Movie to assign Image into gallery
     * @param image Image
     */
    void addToGallery(Movie movie, Image image);

    /**
     * Removes Image from Movie gallery.
     * Movie should have set orphanRemoval=True,
     *  so that Image is deleted afterwards
     * @param movie Movie
     * @param image Image
     */
    void removeFromGallery(Movie movie, Image image);

    /**
     * Adds Actor to movie
     * @param movie Movie to add actor to
     * @param person Person actor
     */
    void addActor(Movie movie, Person person);

    /**
     * Remove Actor relation with Movie
     * Should not delete Actor from DB, only from Movie
     *
     * @param movie Movie to delete Actor from
     * @param person Person actor
     */
    void removeActor(Movie movie, Person person);

    /**
     * Change director of Movie
     * Director should note be deleted
     *
     * @param movie Movie to change director
     * @param person Person director
     */
    void changeDirector(Movie movie, Person person);

    /**
     * Adds genre to Movie
     * @param movie Movie to add Genre to
     * @param genre Genre
     */
    void addGenre(Movie movie, Genre genre);

    /**
     * Remove Genre from Movie
     * Should not delete Genre
     * @param movie Movie to delete Genre from
     * @param genre Genre
     */
    void removeGenre(Movie movie, Genre genre);

    /**
     * Updates Movie non-relational attributes
     * (String-s, Date-s, ...)
     * Only these atributes will be updated,
     *  even if Movie provided has other changes
     *
     * @param movie Movie with updated values
     */
    void updateMovieAttrs(Movie movie);
}
