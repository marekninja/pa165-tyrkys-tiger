package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.UserRatingDao;
import cz.muni.fi.pa165.entity.Movie;
import cz.muni.fi.pa165.entity.User;
import cz.muni.fi.pa165.entity.UserRating;
import cz.muni.fi.pa165.service.utils.Validator;
import cz.muni.fi.pa165.jpql.GenreAndRating;


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
    public void createUserRating(UserRating userRating, User user, Movie movie) {
        Validator.validate(this.getClass(), userRating, "UserRating cannot be null.");
        Validator.validate(this.getClass(), user, "User cannot be null.");
        Validator.validate(this.getClass(), movie, "Movie cannot be null.");
        calculateOverallScore(userRating);
        userRating.setUser(user);
        userRating.setMovie(movie);

        userRatingDao.createUserRating(userRating);
    }

    private void calculateOverallScore(UserRating userRating) {
        Validator.validate(this.getClass(), userRating.getActorScore(), "Actor score cannot be null.");
        Validator.validate(this.getClass(), userRating.getStoryScore(), "Story score cannot be null.");
        Validator.validate(this.getClass(), userRating.getVisualScore(), "Visual score cannot be null.");

        Integer overallScore = (userRating.getVisualScore() + userRating.getStoryScore() + userRating.getActorScore()) / 3;
        userRating.setOverallScore(overallScore);
    }

    @Override
    public UserRating findUserRatingById(Long id) {
        Validator.validate(this.getClass(), id, "Id cannot be null.");
        return userRatingDao.findById(id);
    }

    @Override
    public List<UserRating> findUserRatingsByUser(User user) {
        Validator.validate(this.getClass(), user, "User cannot be null.");
        return userRatingDao.findByUser(user);
    }

    @Override
    public List<UserRating> findUserRatingsByMovie(Movie movie) {
        Validator.validate(this.getClass(), movie, "Movie cannot be null.");
        return userRatingDao.findByMovie(movie);
    }

    @Override
    public List<UserRating> findAllUserRatings() {
        return userRatingDao.findAll();
    }

    @Override
    public void deleteUserRating(UserRating userRating) {
        Validator.validate(this.getClass(), userRating, "UserRating cannot be null.");
        userRatingDao.deleteUserRating(userRating);
    }

    @Override
    public List<GenreAndRating> findAggregateByGenreForUser(User user) {
        Validator.validate(this.getClass(), user, "User cannot be null.");
        return userRatingDao.findAggregateByGenreForUser(user);
    }
}
