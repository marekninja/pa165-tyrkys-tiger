package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.GenreCreateDTO;
import cz.muni.fi.pa165.dto.GenreDTO;
import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.GenreService;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.service.exceptions.NullArgumentException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.List;

/**
 * @author Peter Mravec
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@ExtendWith(MockitoExtension.class)
public class GenreFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private GenreService genreServiceMock;

    @Mock
    private BeanMappingService beanMappingService;

    @Inject
    @InjectMocks
    private GenreFacadeImpl genreFacade;

    private Genre genre_1;
    private Genre genre_2;

    private GenreDTO genre_1_DTO;
    private GenreDTO genre_2_DTO;

    private GenreCreateDTO genre_1_CreateDTO;
    private GenreCreateDTO genre_2_CreateDTO;


    @BeforeEach
    public void before(){
        this.genre_1 = new Genre();
        genre_1.setId(1L);
        genre_1.setName("COMEDY");

        this.genre_2 = new Genre();
        genre_2.setId(2L);
        genre_2.setName("THRILLER");

        this.genre_1_DTO = new GenreDTO();
        genre_1_DTO.setId(1L);
        genre_1_DTO.setName("COMEDY");

        this.genre_2_DTO = new GenreDTO();
        genre_2_DTO.setId(2L);
        genre_2_DTO.setName("THRILLER");

        this.genre_1_CreateDTO = new GenreCreateDTO();
        genre_1_CreateDTO.setName("COMEDY");

        this.genre_2_CreateDTO = new GenreCreateDTO();
        genre_2_CreateDTO.setName("THRILLER");
    }

    @Test
    public void createGenreValidTest() {
        genreFacade.createGenre(genre_1_CreateDTO);
        Mockito.verify(genreServiceMock, Mockito.times(1)).createGenre(genre_1);
    }

    @Test
    public void createPersonNullTest() {
        Assertions.assertThatThrownBy(() -> genreFacade.createGenre(null)).isInstanceOf(NullArgumentException.class);
        Mockito.verify(genreServiceMock, Mockito.times(0)).createGenre(null);
    }

    @Test
    public void findGenreByIdValidTest() {
        Mockito.when(genreServiceMock.findGenreById(1L)).thenReturn(genre_1);
        Mockito.when(beanMappingService.mapTo(genre_1, GenreDTO.class)).thenReturn(genre_1_DTO);

        GenreDTO returned = genreFacade.findGenreById(1L);
        Mockito.verify(genreServiceMock, Mockito.times(1)).findGenreById(1L);
        Mockito.verify(beanMappingService, Mockito.times(1)).mapTo(genre_1, GenreDTO.class);

        Assertions.assertThat(returned).isEqualTo(genre_1_DTO);
    }

    @Test
    public void findGenreByNullIdTest() {
        Assertions.assertThatThrownBy(() -> genreFacade.findGenreById(null)).isInstanceOf(NullArgumentException.class);
        Mockito.verify(genreServiceMock, Mockito.times(0)).findGenreById(null);
    }

    @Test
    public void findAllGenresTest() {
        Mockito.when(genreServiceMock.findAllGenres()).thenReturn(List.of(genre_1, genre_2));
        Mockito.when(beanMappingService.mapTo(List.of(genre_1, genre_2), GenreDTO.class)).thenReturn(List.of(genre_1_DTO, genre_2_DTO));

        List<GenreDTO> returned = genreFacade.findAllGenres();
        Mockito.verify(genreServiceMock, Mockito.times(1)).findAllGenres();
        Mockito.verify(beanMappingService, Mockito.times(1)).mapTo(List.of(genre_1, genre_2), GenreDTO.class);

        Assertions.assertThat(returned).containsExactlyInAnyOrder(genre_1_DTO, genre_2_DTO);
    }

    @Test
    public void updateGenreValidTest() {
        genre_1.setName("Horror");
        Mockito.when(genreServiceMock.updateGenre(genre_1)).thenReturn(genre_1);
        Mockito.when(beanMappingService.mapTo(genre_1_DTO, Genre.class)).thenReturn(genre_1);
        Mockito.when(beanMappingService.mapTo(genre_1, GenreDTO.class)).thenReturn(genre_1_DTO);

        genre_1_DTO.setName("Horror");
        GenreDTO returned = genreFacade.updateGenre(genre_1_DTO);

        Mockito.verify(beanMappingService, Mockito.times(1)).mapTo(genre_1_DTO, Genre.class);
        Mockito.verify(beanMappingService, Mockito.times(1)).mapTo(genre_1, GenreDTO.class);
        Mockito.verify(genreServiceMock, Mockito.times(1)).updateGenre(genre_1);

        Assertions.assertThat(returned).isEqualTo(genre_1_DTO);
    }

    @Test
    public void updateGenreForNullTest() {
        Assertions.assertThatThrownBy(() -> genreFacade.updateGenre(null)).isInstanceOf(NullArgumentException.class);
        Mockito.verify(genreServiceMock, Mockito.times(0)).updateGenre(null);
    }

    @Test
    public void deleteGenreValidTest() {
        Mockito.when(genreServiceMock.findGenreById(genre_1_DTO.getId())).thenReturn(genre_1);

        genreFacade.deleteGenre(genre_1_DTO.getId());

        Mockito.verify(genreServiceMock, Mockito.times(1)).deleteGenre(genre_1);
        Mockito.verify(genreServiceMock, Mockito.times(1)).findGenreById(genre_1_DTO.getId());
    }

    @Test
    public void deleteGenreForNullTest() {
        Assertions.assertThatThrownBy(() -> genreFacade.deleteGenre(null)).isInstanceOf(NullArgumentException.class);
        Mockito.verify(genreServiceMock, Mockito.times(0)).deleteGenre(null);
    }
}
