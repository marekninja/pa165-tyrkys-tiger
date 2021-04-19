package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.entity.Movie;
import cz.muni.fi.pa165.entity.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

/**
 * FOR MILESTONE 1 EVALUATION, Transactional for tests sake
 * Implementation of {@link MovieDao}
 *
 * @author Marek Petrovič
 */
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

//    @Override
//    public List<Movie> findByParameters(List<Person> personList, List<Genre> genres, String name, LocalDate yearMade) {
//
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Movie> query = cb.createQuery(Movie.class);
//
//        Root<Movie> movieRoot = query.from(Movie.class);
//
//        query.select(movieRoot).where(cb.like(movieRoot.get("name"),name))
//                .where(cb.);
//
//
//        //TODO under this line create a Root<Product> instance and then use .select() method on this instance and .where on this instance
//        //content of where should use CriteriaBuilder.isNotEmpty method
//        query.select(productRoot).where(cb.isNotEmpty(productRoot.get("categories")));
//
//
//        List<Product> found = em.createQuery(query).getResultList();
//        Assert.assertEquals(found.size(), 3);
//
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaBuilder<Movie> movieCriteriaBuilder = criteriaBuilder.createQuery(Movie.class)
//        entityManager.createQuery("SELECT m FROM Movie m where m.actors = ")
//
//
//        return null;
//    }

    //    @Override
//    public List<Movie> findByPerson(Person person) {
//        if (person == null){
//            throw new IllegalArgumentException("Person was null");
//        }
//
//        return entityManager.createQuery("select m from Movie m where m.actors = :actor or m.director = :director",Movie.class)
//                .setParameter("actor",person.getId())
//                .setParameter("director",person.getId()).getResultList();
//    }
//
//    @Override
//    public List<Movie> findByActor(Person person) {
//        if (person == null){
//            throw new IllegalArgumentException("Person was null");
//        }
//        return entityManager.createQuery("select m from Movie m where m.actors = :actor",Movie.class)
//                .setParameter("actor",person.getId())
//                .getResultList();
//    }
//
//    @Override
//    public List<Movie> findByDirector(Person person) {
//        if (person == null){
//            throw new IllegalArgumentException("Person was null");
//        }
//        return entityManager.createQuery("select m from Movie m where m.director = :director",Movie.class)
//                .setParameter("director",person.getId())
//                .getResultList();
//    }

    @Override
    public Movie update(Movie movie) {

        return entityManager.merge(movie);
    }

    @Override
    public void remove(Movie movie) {
        if (movie == null){
            throw new IllegalArgumentException("Image was null");
        }
        entityManager.remove(this.findById(movie.getId()));
//        entityManager.remove(movie);
    }
}
