package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.UserDao;
import cz.muni.fi.pa165.entity.User;
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

    /*TODO maybe with Dep.Inj.?*/
    // Argon2 is the best Key Derivation Function since 2015
    private final PasswordEncoder encoder = new Argon2PasswordEncoder();

    @Inject
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findUserById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public User findUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User findByNickName(String nickName) {
        return userDao.findByNickName(nickName);
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    @Override
    public boolean isAdministrator(User user) {
        // condition is checked against user that is returned from DB
        return userDao.findById(user.getId()).isAdministrator();
    }

    /*TODO ask tutor if this is the correct way of checking password...
       pulling user from db rather than checking against the given one.*/
    @Override
    public boolean authenticate(User user, String unencryptedPassword) {
        User stored = userDao.findById(user.getId());
        return encoder.matches(unencryptedPassword, stored.getPasswordHash());
    }

    @Override
    public void registerUser(User user, String unencryptedPassword) {
        user.setPasswordHash(encoder.encode(unencryptedPassword));
        userDao.createUser(user);
    }
}
