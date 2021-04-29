package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Movie;
import cz.muni.fi.pa165.entity.User;
import cz.muni.fi.pa165.entity.UserRating;
import cz.muni.fi.pa165.jpql.GenreAndRating;
import cz.muni.fi.pa165.jpql.RatingDummy;

import java.util.List;

/**
 * Dao interface for entity UserRating
 * Supports basic CRUD operations.
 *
 * @author Peter Mravec
 */
public interface UserRatingDao {

    /**
     * Finds UserRating by id
     *
     * @param id of UserRating
     * @return found UserRating
     */
    UserRating findById(Long id);

    /**
     * Finds all userRatings for given user
     *
     * @param user - user for which the userRatings are being found
     * @return List of userRatings associated with the given user
     */
    List<UserRating> findByUser(User user);

    /**
     * Finds all userRatings for given movie
     *
     * @param movie - movie for which the userRatings are being found
     * @return List of userRatings associated with the given movie
     */
    List<UserRating> findByMovie(Movie movie);

    /**
     * Finds all UserRatings
     *
     * @return list of all UserRatings
     */
    List<UserRating> findAll();

    /**
     * Inserts UserRating into DB
     *
     * @param userRating UserRating object to create
     */
    void createUserRating(UserRating userRating);

    /**
     * Find aggregates of ratings for User by Genre
     * @return List of pairs of UserRating aggregate and Genre
     *
     * @author Marek Petrovič
     */
    List<GenreAndRating> findAggregateByGenreForUser(User user);

    /**
     * Updates UserRating in DB
     *
     * @param userRating UserRating object to update
     * @return userRating updated
     */
    UserRating updateUserRating(UserRating userRating);

    /**
     * Deletes UserRating from DB
     *
     * @param userRating UserRating object to delete
     */
    void deleteUserRating(UserRating userRating);
}
