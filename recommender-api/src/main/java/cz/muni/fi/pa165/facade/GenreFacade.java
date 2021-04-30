package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.GenreDTO;
import java.util.List;

/**
 * @author Peter Mravec
 */
public interface GenreFacade {

    /**
     * Creates Genre by GenreDTO
     *
     * @param genreDTO of GenreDTO
     */
    void createGenre(GenreDTO genreDTO);

    /**
     * Finds Genre by id
     *
     * @param id of Genre
     * @return found GenreDTO
     */
    GenreDTO findGenreById(Long id);

    /**
     * Finds all Genres
     *
     * @return list of all GenreDTO
     */
    List<GenreDTO> findAllGenres();

    /**
     * Updates existing Genre
     *
     * @param genreDTO Genre object to update
     * @return updated GenreDTO object
     */
    GenreDTO updateGenre(GenreDTO genreDTO);

    /**
     * Deletes existing Genre
     *
     * @param id Id of the Genre object that should be deleted
     */
    void deleteGenre(Long id);
}
