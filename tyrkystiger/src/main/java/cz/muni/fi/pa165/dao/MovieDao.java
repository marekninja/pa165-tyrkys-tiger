package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Movie;

import java.util.List;

/**
 * DAO interface of {@link Movie}
 * supports CRUD over Movie:
 *  create, read (one, all, by name, by genre, by actor, by director), update(one), delete
 *
 * @author Marek Petrovič
 */
public interface MovieDao {

    /**
     * Creates movie in DB, from Movie instance provided
     * @param movie instance of Movie
     */
    void create(Movie movie);

    /**
     * Returns all persisted Movies
     * @return list of Movie
     */
    List<Movie> findAll();

    /**
     * Returns one Movie, found by id
     * @param Id Long unique identifier in DB
     * @return Movie instance
     */
    Movie findById(Long Id);

    /**
     * Returns all persisted Movies with name (could be more with same name)
     * @param name String name of Movie,
     * @return list of Movies
     */
    List<Movie> findByName(String name);

    /**
     * Returns all persisted Movies with Person
     * @param person Person, either actor or director
     * @return list of Movies
     */
    List<Movie> findByPerson(Person person);

    /**
     * Returns all persisted Movies with Person as actor
     * @param person Actor
     * @return list of Movies
     */
    List<Movie> findByActor(Person person);

    /**
     * Returns all persisted Movies that have Person as director
     * @param person Director
     * @return list of Movies
     */
    List<Movie> findByDirector(Person person);

    /**
     * Compares stored Movie, with Movie provided and updates fields in stored Movie
     * @param movie modified Movie instance
     */
    Movie update(Movie movie);

    /**
     * Removes Movie from DB
     * @param movie Movie instance to be removed
     */
    void remove(Movie movie);
}
