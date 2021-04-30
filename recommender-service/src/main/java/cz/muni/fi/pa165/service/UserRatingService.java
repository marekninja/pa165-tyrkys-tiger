package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Movie;
import cz.muni.fi.pa165.entity.User;
import cz.muni.fi.pa165.entity.UserRating;
import cz.muni.fi.pa165.jpql.GenreAndRating;
import cz.muni.fi.pa165.jpql.RatingDummy;

import java.util.List;

/**
 * An interface that defines a service access to the {@link UserRating} entity.
 * @author Matej Turek
 */
public interface UserRatingService {

    /**
     * Creates new UserRating
     *
     * @param userRating UserRating object to create
     * @param user Owner of the userRating
     * @param movie Movie that is evaluated
     */
    void createUserRating(UserRating userRating, User user, Movie movie);

    /**
     * Finds UserRating by its id
     *
     * @param id of UserRating
     * @return found UserRating
     */
    UserRating findUserRatingById(Long id);

    /**
     * Finds all userRatings for given user
     *
     * @param user - user for which the userRatings are being found
     * @return List of userRatings associated with the given user
     */
    List<UserRating> findUserRatingsByUser(User user);

    /**
     * Finds average rating of User by Genre
     * Returns dummy objects
     *
     * @param user User for which the aggregate will be made
     * @return List of pairs of Genre and UserRating
     * @author Marek Petrovič
     */
    List<GenreAndRating> findAggregateByGenreForUser(User user);
    

    /**
     * Finds all userRatings for given movie
     *
     * @param movie - movie for which the userRatings are being found
     * @return List of userRatings associated with the given movie
     */
    List<UserRating> findUserRatingsByMovie(Movie movie);

    /**
     * Finds all UserRatings
     *
     * @return list of all UserRatings
     */
    List<UserRating> findAllUserRatings();

    /**
     * Deletes existing UserRating
     *
     * @param userRating UserRating object to delete
     */
    void deleteUserRating(UserRating userRating);
}
