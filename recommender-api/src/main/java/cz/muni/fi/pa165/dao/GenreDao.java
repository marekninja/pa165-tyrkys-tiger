package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.entity.User;

import java.util.List;

/**
 * Dao interface for entity Genre
 * Supports basic CRUD operations.
 *
 * @author Matej Turek
 */
public interface GenreDao {

    /**
     * Finds Genre by id
     *
     * @param id of Genre
     * @return found Genre
     */
    Genre findById(Long id);

    /**
     * Finds all Genres
     *
     * @return list of all Genres
     */
    List<Genre> findAll();

    /**
     * Inserts Genre into DB
     *
     * @param genre Genre object to create
     */
    void createGenre(Genre genre);

    /**
     * Updates Genre in DB
     *
     * @param genre Genre object to update
     * @return updated Genre object
     */
    Genre updateGenre(Genre genre);

    /**
     * Deletes User from DB
     *
     * @param genre Genre object to delete
     */
    void deleteUser(Genre genre);
}
