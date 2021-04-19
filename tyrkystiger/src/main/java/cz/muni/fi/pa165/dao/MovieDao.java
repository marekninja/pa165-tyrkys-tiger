package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.entity.Movie;
import cz.muni.fi.pa165.entity.Person;

import java.time.LocalDate;
import java.util.List;

/**
 * FOR MILESTONE 1 EVALUATION
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
     * Returns all persisted Movies with parameters specified.
     * Parameters should be of List<Person>, List<Genre>, String Name
     *
     * @param person Person, either actor or director
     * @return list of Movies
     */

    /**
     * Builds query and returns all the Movies which have specified parameters
     *
     * @param personList List of Person, can be Directors/Actors
     * @param genres List of Genre
     * @param name of Movie String, similarity
     * @param yearMade LocalDate, year of Movie published
     * @return List of Movies
     */
    List<Movie> findByParameters(List<Person> personList, List<Genre> genres, String name, LocalDate yearMade);

//    /**
//     * Returns all persisted Movies with Person as actor
//     * @param person Actor
//     * @return list of Movies
//     */
//    List<Movie> findByActor(Person person);
//
//    /**
//     * Returns all persisted Movies that have Person as director
//     * @param person Director
//     * @return list of Movies
//     */
//    List<Movie> findByDirector(Person person);

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
