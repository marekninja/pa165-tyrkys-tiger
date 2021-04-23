package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.UserRatingDao;
import cz.muni.fi.pa165.entity.Movie;
import cz.muni.fi.pa165.entity.User;
import cz.muni.fi.pa165.entity.UserRating;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of the {@link UserRatingService}.
 *
 * @author Matej Turek
 */
@Service
public class UserRatingServiceImpl implements UserRatingService {

    private final UserRatingDao userRatingDao;

    @Inject
    public UserRatingServiceImpl(UserRatingDao userRatingDao) {
        this.userRatingDao = userRatingDao;
    }

    @Override
    public void createUserRating(UserRating userRating) {
        userRatingDao.createUserRating(userRating);
    }

    @Override
    public UserRating findUserRatingById(Long id) {
        return userRatingDao.findById(id);
    }

    @Override
    public List<UserRating> findUserRatingsByUser(User user) {
        return userRatingDao.findByUser(user);
    }

    @Override
    public List<UserRating> findUserRatingsByMovie(Movie movie) {
        return userRatingDao.findByMovie(movie);
    }

    @Override
    public List<UserRating> findAllUserRatings() {
        return userRatingDao.findAll();
    }

    /*TODO if object doesnt exist merge insert new one into db... is this what we want?*/
    @Override
    public UserRating updateUserRating(UserRating userRating) {
        return userRatingDao.updateUserRating(userRating);
    }

    @Override
    public void deleteUserRating(UserRating userRating) {
        userRatingDao.deleteUserRating(userRating);
    }
}
