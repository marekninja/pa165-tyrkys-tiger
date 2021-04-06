package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.UserRating;

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
    Long createUserRating(UserRating userRating);

    /**
     * Updates UserRating in DB
     *
     * @param userRating UserRating object to update
     */
    void updateUserRating(UserRating userRating);

    /**
     * Deletes UserRating from DB
     *
     * @param userRating UserRating object to delete
     */
    void deleteUserRating(UserRating userRating);
}
