package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * This is a base implementation of {@link PersonDao}
 *
 * @author Peter Mravec
 */
@Repository
public class PersonDaoImpl implements PersonDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public Person findById(Long id) {
        return em.find(Person.class, id);
    }

    @Override
    public Person findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name was null.");
        }

        try {
            return em.createQuery("select p from Person p where name = :name", Person.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<Person> findAll() {
        return em.createQuery("select p from Person p", Person.class).getResultList();
    }

    @Override
    public Long createPerson(Person person) {
        em.persist(person);
        return person.getId();
    }

    @Override
    public void updatePerson(Person person) {
        em.merge(person);
    }

    @Override
    public void deletePerson(Person person) {
        em.remove(person);
    }
}
