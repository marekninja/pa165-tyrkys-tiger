package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.PersonRealDTO;
import java.util.List;

/**
 * @author Peter Mravec
 */
public interface PersonFacade {

    /**
     * Creates Person by PersonRealDTO
     *
     * @param personRealDTO of PersonRealDTO
     */
    void create(PersonRealDTO personRealDTO);

    /**
     * Finds Person by id
     *
     * @param id of Person
     * @return found PersonRealDTO
     */
    PersonRealDTO findById(Long id);

    /**
     * Finds Person by name
     *
     * @param name of Person
     * @return found PersonRealDTO
     */
    PersonRealDTO findByName(String name);

    /**
     * Finds all Persons by PersonRealDTO
     *
     * @return list of all PersonRealDTO
     */
    List<PersonRealDTO> findAll();

    /**
     * Updates existing Person by PersonRealDTO
     *
     * @param personRealDTO Person object to update
     * @return updated PersonRealDTO object
     */
    PersonRealDTO update(PersonRealDTO personRealDTO);

    /**
     * Deletes existing Person
     *
     * @param id Id of the PersonRealDTO object that should be deleted
     */
    void delete(Long id);
}

