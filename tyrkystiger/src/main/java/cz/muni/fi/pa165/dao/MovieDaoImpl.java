package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Movie;
import cz.muni.fi.pa165.entity.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class MovieDaoImpl implements MovieDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Movie movie) {
        if (movie == null){
            throw new IllegalArgumentException("Movie was null");
        }

        entityManager.persist(movie);
    }

    @Override
    public List<Movie> findAll() {

        return entityManager.createQuery("select m from Movie m", Movie.class).getResultList();
    }

    @Override
    public Movie findById(Long Id) {
        if (Id == null){
            throw new IllegalArgumentException("Id was null");
        }

        return entityManager.find(Movie.class, Id);
    }

    @Override
    public List<Movie> findByName(String name) {
        if (name == null){
            throw new IllegalArgumentException("Name was null");
        }

        return entityManager.createQuery("select m from Movie m where m.name = :name", Movie.class)
                .setParameter("name",name)
                .getResultList();
    }

    @Override
    public List<Movie> findByPerson(Person person) {
        if (person == null){
            throw new IllegalArgumentException("Person was null");
        }

        return entityManager.createQuery("select m from Movie m where m.actors = :actor or m.director = :director",Movie.class)
                .setParameter("actor",person)
                .setParameter("director",person).getResultList();
    }

    @Override
    public List<Movie> findByActor(Person person) {
        if (person == null){
            throw new IllegalArgumentException("Person was null");
        }
        return entityManager.createQuery("select m from Movie m where m.actors = :actor",Movie.class)
                .setParameter("actor",person)
                .getResultList();
    }

    @Override
    public List<Movie> findByDirector(Person person) {
        if (person == null){
            throw new IllegalArgumentException("Person was null");
        }
        return entityManager.createQuery("select m from Movie m where m.director = :director",Movie.class)
                .setParameter("director",person)
                .getResultList();
    }

    @Override
    public Movie update(Movie movie) {

        return entityManager.merge(movie);
    }

    @Override
    public void remove(Movie movie) {
        entityManager.remove(movie);
    }
}
