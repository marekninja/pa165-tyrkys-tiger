package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.GenreDao;
import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.entity.Image;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;


/**
 * @author Peter Mravec
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class GenreServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    GenreDao genreDao;

    @Autowired
    @InjectMocks
    GenreService genreService;

    private Genre genre_1;
    //private Genre genre_2;

    @BeforeClass
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void before(){
        this.genre_1 = new Genre();
        genre_1.setId(1L);
        genre_1.setName("COMEDY");

/*        this.genre_2 = new Genre();
        genre_2.setId(2L);
        genre_2.setName("SCI-FI");*/
    }

    @Test
    public void getByIdTest(){
        when(genreDao.findById(genre_1.getId())).thenReturn(genre_1);
        Genre found = genreService.findGenreById(genre_1.getId());
        Assert.assertNotNull(found);
        Assert.assertEquals(found,genre_1);
    }

/*    @Test
    public void createTest(){
        doAnswer((i) -> {
            Assert.assertEquals(i.getArguments()[0],image);
            return null;
        }).when(imageDao).create(image);

        Image image1 = imageService.create(image);
        Assert.assertNotNull(image1);
        Assert.assertEquals(image1,image);
    }*/

}
