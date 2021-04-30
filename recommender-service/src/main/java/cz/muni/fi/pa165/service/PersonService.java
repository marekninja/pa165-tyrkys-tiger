package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Person;

import java.util.List;

/**
 * An interface that defines a service access to the {@link Person} entity.
 * @author Peter Mravec
 */
public interface PersonService {

    /**
     * create new Person
     *
     * @param person Person object to create
     */
    void create(Person person);

    /**
     * Finds Person by id
     *
     * @param id of Person
     * @return found Person
     */
    Person findById(Long id);

    /**
     * Finds Person by name
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
     * Updates existing Person
     *
     * @param person Person object to update
     * @return updated Person object
     */
    Person update(Person person);

    /**
     * Deletes existing Person
     *
     * @param person Person object to delete
     */
    void delete(Person person);

}
