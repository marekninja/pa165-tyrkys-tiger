package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.User;

import java.util.List;

/**
 * FOR MILESTONE 1 EVALUATION
 * Dao interface for entity User
 * Supports basic CRUD operations.
 *
 * @author Matej Turek
 */
public interface UserDao {

    /**
     * Finds User by id
     *
     * @param id of User
     * @return found User
     */
    User findById(Long id);

    /**
     * Finds User by his nickname
     *
     * @param nickName of User
     * @return found User
     */
    User findByNickName(String nickName);

    /**
     * Finds all Users
     *
     * @return list of all Users
     */
    List<User> findAll();

    /**
     * Inserts User into DB
     *
     * @param user User object to create
     */
    void createUser(User user);

    /**
     * Updates User in DB
     *
     * @param user User object to update
     * @return updated User object
     */
    User updateUser(User user);

    /**
     * Deletes User from DB
     *
     * @param user User object to delete
     */
    void deleteUser(User user);
}
