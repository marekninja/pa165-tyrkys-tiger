package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Movie;
import cz.muni.fi.pa165.entity.User;
import cz.muni.fi.pa165.entity.UserRating;

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
     */
    void createUserRating(UserRating userRating);

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
     * Updates existing UserRating
     *
     * @param userRating UserRating object to update
     * @return userRating updated
     */
    UserRating updateUserRating(UserRating userRating);

    /**
     * Deletes existing UserRating
     *
     * @param userRating UserRating object to delete
     */
    void deleteUserRating(UserRating userRating);
}
