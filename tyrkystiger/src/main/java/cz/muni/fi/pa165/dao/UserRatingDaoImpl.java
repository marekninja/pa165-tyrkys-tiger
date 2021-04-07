package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Movie;
import cz.muni.fi.pa165.entity.User;
import cz.muni.fi.pa165.entity.UserRating;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * This is a base implementation of {@link UserRatingDao}
 *
 * @author Peter Mravec
 */
@Repository
public class UserRatingDaoImpl implements UserRatingDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public UserRating findById(Long id) {
        return em.find(UserRating.class, id);
    }

    @Override
    public List<UserRating> findAll() {
        return em.createQuery("select u from UserRating u", UserRating.class).getResultList();
    }

    @Override
    public Long createUserRating(UserRating userRating) {
        em.persist(userRating);
        return userRating.getId();
    }

    @Override
    public List<UserRating> findByUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("user was null.");
        }
        try {
            return em.createQuery("select u from UserRating u where user = :user", UserRating.class)
                    .setParameter("user", user)
                    .getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<UserRating> findByMovie(Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("movie was null.");
        }
        try {
            return em.createQuery("select u from UserRating u where movie = :movie", UserRating.class)
                    .setParameter("movie", movie)
                    .getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public void updateUserRating(UserRating userRating) {
        em.merge(userRating);
    }

    @Override
    public void deleteUserRating(UserRating userRating) {
        em.remove(userRating);
    }
}
