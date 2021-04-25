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
        if (id == null) {
            throw new IllegalArgumentException("id was null.");
        }
        return em.find(UserRating.class, id);
    }

    @Override
    public List<UserRating> findAll() {
        return em.createQuery("select u from UserRating u", UserRating.class).getResultList();
    }

    @Override
    public void createUserRating(UserRating userRating) {
        if (userRating == null) {
            throw new IllegalArgumentException("userRating was null.");
        }
        em.persist(userRating);
    }

    @Override
    public List<UserRating> findByUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("user was null.");
        }
        try {
            return em.createQuery("select u from UserRating u where u.user = :user", UserRating.class)
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
            return em.createQuery("select u from UserRating u where u.movie = :movie", UserRating.class)
                    .setParameter("movie", movie)
                    .getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }

    //TODO vyriešiť, či by sa nemalo mazať a vytvoriť (interne namiesto merge)
    @Override
    public UserRating updateUserRating(UserRating userRating) {
        if (userRating == null) {
            throw new IllegalArgumentException("userRating was null.");
        }
        return em.merge(userRating);
    }

    @Override
    public void deleteUserRating(UserRating userRating) {
        if (userRating == null) {
            throw new IllegalArgumentException("userRating was null.");
        }
        em.remove(userRating);
    }
}
