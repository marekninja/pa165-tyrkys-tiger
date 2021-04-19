package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Person;

import java.util.List;

/**
 * FOR MILESTONE 1 EVALUATION
 * Dao interface for entity Person
 * Supports basic CRUD operations.
 *
 * @author Peter Mravec
 */
public interface PersonDao {

    /**
     * Finds Person by id
     *
     * @param id of Person
     * @return found Person
     */
    Person findById(Long id);

    /**
     * Finds Person by his name
     *
     * @param name of Person
     * @return found Person
     */
    Person findByName(String name);

    /**
     * Finds all Persons
     *
     * @return list of all Persons
     */
    List<Person> findAll();

    /**
     * Inserts Peron into DB
     *
     * @param person Person object to create
     */
    void createPerson(Person person);

    /**
     * Updates Person in DB
     *
     * @param person Person object to update
     * @return person updated
     */
    Person updatePerson(Person person);

    /**
     * Deletes Person from DB
     *
     * @param person Person object to delete
     */
    void deletePerson(Person person);
}
