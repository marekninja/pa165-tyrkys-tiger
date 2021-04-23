package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * FOR MILESTONE 1 EVALUATION, Transactional for tests sake
 * This is a base implementation of {@link UserDao}
 *
 * @author Matej Turek
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User findByEmail(String email) {
        try {
            return em.createQuery("select u from User u where email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public User findByNickName(String nickName) {
        try {
            return em.createQuery("select u from User u where nickName = :nickname", User.class)
                    .setParameter("nickname", nickName)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void createUser(User user) {
        em.persist(user);
    }

    @Override
    public User updateUser(User user) {
        return em.merge(user);
    }

    @Override
    public void deleteUser(User user) {
        if (user == null){
            throw new IllegalArgumentException("User was null!");
        }
        em.remove(this.findById(user.getId()));
    }
}
