package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.PersonDTO;
import java.util.List;

/**
 * @author Peter Mravec
 */
public interface PersonFacade {

    /**
     * Creates Person by PersonDTO
     *
     * @param personDTO of PersonDTO
     */
    void create(PersonDTO personDTO);

    /**
     * Finds Person by id
     *
     * @param id of Person
     * @return found PersonDTO
     */
    PersonDTO findById(Long id);

    /**
     * Finds Person by name
     *
     * @param name of Person
     * @return found PersonDTO
     */
    PersonDTO findByName(String name);

    /**
     * Finds all Persons by PersonDTO
     *
     * @return list of all PersonDTO
     */
    List<PersonDTO> findAll();

    /**
     * Updates existing Person by PersonDTO
     *
     * @param personDTO Person object to update
     * @return updated PersonDTO object
     */
    PersonDTO update(PersonDTO personDTO);

    /**
     * Deletes existing Person
     *
     * @param id Id of the PersonDTO object that should be deleted
     */
    void delete(Long id);
}

