package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.User;

import java.util.List;

/**
 * An interface that defines a service access to the {@link User} entity.
 * @author Matej Turek
 */
public interface UserService {

    /**
     * Finds User by id
     *
     * @param id of User
     * @return found User
     */
    User findUserById(Long id);

    /**
     * Finds User by his email
     *
     * @param email of User
     * @return found User
     */
    User findUserByEmail(String email);

    /**
     * Finds User by his nickname
     *
     * @param nickName of User
     * @return found User
     */
    User findUserByNickName(String nickName);

    /**
     * Finds all Users
     *
     * @return list of all Users
     */
    List<User> findAllUsers();

    /**
     * Updates existing User
     *
     * @param user User object to update
     * @return updated User object
     */
    User updateUser(User user);

    /**
     * Deletes existing User
     *
     * @param user User object to delete
     */
    void deleteUser(User user);

    /**
     * Check if the given user is admin.
     *
     * @param user User object to test
     * @return true, only if the user is administrator.
     */
    boolean isAdministrator(User user);

    /**
     * Try to authenticate a user. Return true only if the hashed password matches the records.
     *
     * @param user User object to authenticate
     * @param unencryptedPassword password in open form
     */
    boolean authenticate(User user, String unencryptedPassword);

    /**
     * Register the given user with the given unencrypted password.
     *
     * @param user User object to create
     * @param unencryptedPassword password in open form
     */
    void registerUser(User user, String unencryptedPassword);
}
