package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.GenreDTO;
import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.facade.GenreFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.GenreService;
import cz.muni.fi.pa165.service.utils.Validator;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Peter Mravec
 */
@Service
@Transactional
public class GenreFacadeImpl implements GenreFacade {

    private final GenreService genreService;

    private final BeanMappingService beanMappingService;

    @Inject
    public GenreFacadeImpl(GenreService genreService, BeanMappingService mapping) {
        this.genreService = genreService;
        this.beanMappingService = mapping;
    }

    @Override
    public void createGenre(GenreDTO genreDTO) {
        Validator.validate(this.getClass(), genreDTO, "GenreDTO cannot be null.");
        Genre genre = new Genre();
        genre.setName(genreDTO.getName());
        genreService.createGenre(genre);
    }

    @Override
    public GenreDTO findGenreById(Long id) {
        Validator.validate(this.getClass(), id, "ID cannot be null.");
        Genre storedGenre = genreService.findGenreById(id);
        return (storedGenre == null) ? null :
                beanMappingService.mapTo(storedGenre, GenreDTO.class);
    }

    @Override
    public List<GenreDTO> findAllGenres() {
        return beanMappingService.mapTo(genreService.findAllGenres(), GenreDTO.class);
    }

    @Override
    public GenreDTO updateGenre(GenreDTO genreDTO) {
        Validator.validate(this.getClass(), genreDTO, "GenreDTO cannot be null.");
        Genre updated = genreService.updateGenre(beanMappingService.mapTo(genreDTO, Genre.class));
        return beanMappingService.mapTo(updated, GenreDTO.class);
    }

    @Override
    public void deleteGenre(Long id) {
        Validator.validate(this.getClass(), id, "ID cannot be null.");
        Genre foundToBeDeleted = genreService.findGenreById(id);
        genreService.deleteGenre(foundToBeDeleted);
    }
}
