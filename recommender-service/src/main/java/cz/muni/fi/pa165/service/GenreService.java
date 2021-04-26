package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Genre;
import java.util.List;

/**
 * An interface that defines a service access to the {@link Genre} entity.
 * @author Peter Mravec
 */
public interface GenreService {

    /**
     * Finds Genre by id
     *
     * @param id of Genre
     * @return found Genre
     */
    Genre findGenreById(Long id);

    /**
     * Finds all Genres
     *
     * @return list of all Genres
     */
    List<Genre> findAllGenres();

    /**
     * Updates existing Genre
     *
     * @param genre Genre object to update
     * @return updated Genre object
     */
    Genre updateGenre(Genre genre);

    /**
     * Deletes existing Genre
     *
     * @param genre Genre object to delete
     */
    void deleteGenre(Genre genre);


}
