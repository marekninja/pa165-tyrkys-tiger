package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Thi is a base implementation of {@link UserDao}
 *
 * @author Matej Turek
 */
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public User findByNickName(String nickName) {
        if (nickName == null) {
            throw new IllegalArgumentException("NickName was null.");
        }

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
    public Long createUser(User user) {
        em.persist(user);
        return user.getId();
    }

    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    @Override
    public void deleteUser(User user) {
        em.remove(user);
    }
}
