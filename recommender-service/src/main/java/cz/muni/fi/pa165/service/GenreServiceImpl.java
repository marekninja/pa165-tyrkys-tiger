package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.GenreDao;
import cz.muni.fi.pa165.entity.Genre;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of the {@link GenreService}.
 *
 * @author Peter Mravec
 */
@Service
public class GenreServiceImpl implements GenreService{

    private final GenreDao genreDao;

    @Inject
    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public Genre findGenreById(Long id) {
        return null;
    }

    @Override
    public List<Genre> findAllGenres() {
        return null;
    }

    @Override
    public Genre updateGenre(Genre genre) {
        return null;
    }

    @Override
    public void deleteGenre(Genre genre) {

    }
}
