package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.UserDao;
import cz.muni.fi.pa165.entity.User;
import cz.muni.fi.pa165.service.utils.Validator;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of the {@link UserService}.
 *
 * @author Matej Turek
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    // Argon2 is the best Key Derivation Function since 2015
    private final PasswordEncoder encoder = new Argon2PasswordEncoder();

    @Inject
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findUserById(Long id) {
        Validator.validate(this.getClass(), id, "User id cannot be null.");
        return userDao.findById(id);
    }

    @Override
    public User findUserByEmail(String email) {
        Validator.validate(this.getClass(), email, "User email cannot be null.");
        return userDao.findByEmail(email);
    }

    @Override
    public User findUserByNickName(String nickName) {
        Validator.validate(this.getClass(), nickName, "User nickname cannot be null.");
        return userDao.findByNickName(nickName);
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User updateUser(User user) {
        Validator.validate(this.getClass(), user, "updateUser - User cannot be null.");
        user.setPasswordHash(encoder.encode(user.getPasswordHash()));
        return userDao.updateUser(user);
    }

    @Override
    public void deleteUser(User user) {
        Validator.validate(this.getClass(), user, "deleteUser - User cannot be null.");
        userDao.deleteUser(user);
    }

    @Override
    public boolean isAdministrator(User user) {
        Validator.validate(this.getClass(), user, "isAdministrator - User cannot be null.");
        // condition is checked against user that is returned from DB
        return userDao.findById(user.getId()).isAdministrator();
    }

    @Override
    public boolean authenticate(User user, String unencryptedPassword) {
        Validator.validate(this.getClass(), user, "authenticate - User cannot be null.");
        Validator.validate(this.getClass(), unencryptedPassword, "Password cannot be null.");
        //User stored = userDao.findById(user.getId());

        Validator.validate(this.getClass(), user, String.format("User with id %d not in db", user.getId()));
        return encoder.matches(unencryptedPassword, user.getPasswordHash());
    }

    @Override
    public void registerUser(User user, String unencryptedPassword) {
        Validator.validate(this.getClass(), user, "registerUser - User cannot be null.");
        Validator.validate(this.getClass(), unencryptedPassword, "Password cannot be null.");
        user.setPasswordHash(encoder.encode(unencryptedPassword));
        userDao.createUser(user);
    }
}
