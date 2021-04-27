package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.GenreDao;
import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.service.utils.Validator;
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
    public Genre createGenre(Genre genre) {
        Validator.validate(this.getClass(), genre, "Genre cannot be null.");
        genreDao.createGenre(genre);
        return genre;
    }

    @Override
    public Genre findGenreById(Long id) {
        Validator.validate(this.getClass(), id, "Genre id cannot be null.");
        return genreDao.findById(id);
    }

    @Override
    public List<Genre> findAllGenres() {
        return genreDao.findAll();
    }

    @Override
    public Genre updateGenre(Genre genre) {
        Validator.validate(this.getClass(), genre, "Genre cannot be null.");
        return genreDao.updateGenre(genre);
    }

    @Override
    public void deleteGenre(Genre genre) {
        Validator.validate(this.getClass(), genre, "Genre cannot be null.");
        genreDao.deleteGenre(genre);
    }
}
