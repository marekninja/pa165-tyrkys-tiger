package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Movie;
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
        entityManager.persist(movie);
    }

    @Override
    public List<Movie> findAll() {

        return entityManager.createQuery("select m from Movie m", Movie.class).getResultList();
    }

    @Override
    public Movie findById(Long Id) {

        return entityManager.find(Movie.class, Id);
    }

    @Override
    public List<Movie> findByName(String name) {

        return entityManager.createQuery("select m from Movie m where m.name = :name", Movie.class)
                .setParameter("name",name)
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
