package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.GenreDao;
import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.service.exceptions.NullArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * @author Peter Mravec
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@ExtendWith(MockitoExtension.class)
public class GenreServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private GenreDao genreDaoMock;

    @Autowired
    @InjectMocks
    private GenreServiceImpl genreService;

    private Genre genre_1;
    private Genre genre_2;

    @BeforeEach
    public void before(){
        this.genre_1 = new Genre();
        genre_1.setId(1L);
        genre_1.setName("COMEDY");

        this.genre_2 = new Genre();
        genre_2.setId(2L);
        genre_2.setName("THRILLER");
    }

    @Test
    public void findGenreByIdTest(){
        when(genreDaoMock.findById(1L)).thenReturn(genre_1);
        
        Genre found = genreService.findGenreById(1L);

        verify(genreDaoMock, Mockito.times(1)).findById(1L);

        Assertions.assertThat(found).isNotNull();
        Assertions.assertThat(found).usingRecursiveComparison().isEqualTo(genre_1);
    }

    @Test
    public void findGenreByIdNullInputTest() {
        Assertions.assertThatThrownBy(() -> genreService.findGenreById(null)).isInstanceOf(NullArgumentException.class);
        Mockito.verify(genreDaoMock, Mockito.times(0)).findById(null);
    }

    @Test
    public void findGenreByIdNotStoredInDBTest() {
        when(genreDaoMock.findById(8L)).thenReturn(null);

        Genre found = genreService.findGenreById(8L);

        verify(genreDaoMock, Mockito.times(1)).findById(8L);
        Assertions.assertThat(found).isNull();
    }

    @Test
    public void createGenreTest(){
        genreService.createGenre(genre_1);
        verify(genreDaoMock, Mockito.times(1)).createGenre(genre_1);
    }

    @Test
    public void createGenreByNullInputTest(){
        Assertions.assertThatThrownBy(() -> genreService.createGenre(null)).isInstanceOf(NullArgumentException.class);
        Mockito.verify(genreDaoMock, Mockito.times(0)).createGenre(null);
    }

    @Test
    public void findAllGenresTest(){
        when(genreDaoMock.findAll()).thenReturn(Arrays.asList(genre_1, genre_2));

        List<Genre> foundGenres = genreService.findAllGenres();

        verify(genreDaoMock, Mockito.times(1)).findAll();

        Assertions.assertThat(foundGenres).isNotNull();
        Assertions.assertThat(foundGenres.size()).isEqualTo(2);
        Assertions.assertThat(foundGenres).contains(genre_1);
        Assertions.assertThat(foundGenres).contains(genre_2);
    }

    @Test
    public void updateGenreTest(){
        Genre updatedGenre = new Genre(1L, "COMEDY-THRILLER");
        when(genreDaoMock.updateGenre(updatedGenre)).thenReturn(updatedGenre);

        Genre updateGenreReturn = genreService.updateGenre(updatedGenre);

        verify(genreDaoMock, Mockito.times(1)).updateGenre(updatedGenre);

        Assertions.assertThat(updateGenreReturn).isNotNull();
        Assertions.assertThat(updateGenreReturn).usingRecursiveComparison().isEqualTo(updatedGenre);
    }

    @Test
    public void updateGenreByNullInputTest(){
        Assertions.assertThatThrownBy(() -> genreService.updateGenre(null)).isInstanceOf(NullArgumentException.class);
        Mockito.verify(genreDaoMock, Mockito.times(0)).updateGenre(null);
    }

    @Test
    public void deleteGenreValidTest(){
        genreService.deleteGenre(genre_2);
        verify(genreDaoMock, Mockito.times(1)).deleteGenre(genre_2);
    }

    @Test
    public void deleteGenreByNullInputTest(){
        Assertions.assertThatThrownBy(() -> genreService.deleteGenre(null)).isInstanceOf(NullArgumentException.class);
        Mockito.verify(genreDaoMock, Mockito.times(0)).deleteGenre(null);
    }

}
