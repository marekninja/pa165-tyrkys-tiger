package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.dto.UserPasswordlessDTO;

import java.util.List;

/**
 * @author Matej Turek
 */
public interface UserFacade {

    /**
     * Finds User by id
     *
     * @param id of User
     * @return found User
     */
    UserPasswordlessDTO findUserById(Long id);

    /**
     * Finds User by his email
     *
     * @param email of User
     * @return found User
     */
    UserPasswordlessDTO findUserByEmail(String email);

    /**
     * Finds User by his nickname
     *
     * @param nickName of User
     * @return found User
     */
    UserPasswordlessDTO findUserByNickName(String nickName);

    /**
     * Finds all Users
     *
     * @return list of all Users
     */
    List<UserPasswordlessDTO> findAllUsers();

    /**
     * Updates existing User
     *
     * @param userDTO User object to update
     * @return updated UserDTO object
     */
    UserPasswordlessDTO updateUser(UserDTO userDTO);

    /**
     * Deletes existing User
     *
     * @param userId Id of the User object that should be deleted
     */
    void deleteUser(Long userId);

    /**
     * Check if the given user is admin.
     *
     * @param userDTO User object to test
     * @return true, only if the user is administrator.
     */
    boolean isAdministrator(UserDTO userDTO);

    /**
     * Try to authenticate a user. Return true only if the hashed password matches the records.
     *
     * @param userDTO User object to authenticate
     */
    boolean authenticate(UserAuthenticateDTO userDTO);

    /**
     * Register the given user with the given unencrypted password.
     *
     * @param userDTO User object to create
     * @param unencryptedPassword password in open form
     * @return UserPasswordlessDto representation of user
     */
    UserPasswordlessDTO registerUser(UserDTO userDTO, String unencryptedPassword);
}