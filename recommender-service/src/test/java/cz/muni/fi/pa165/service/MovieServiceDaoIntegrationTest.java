package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.MovieDao;
import cz.muni.fi.pa165.entity.Image;
import cz.muni.fi.pa165.entity.Movie;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.parser.Entity;
import java.time.LocalDate;

/**
 * Just to check some use-cases
 * @author Marek Petrovič
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class MovieServiceDaoIntegrationTest extends AbstractTestNGSpringContextTests {
    @Autowired
    MovieService movieService;

    @Autowired
    ImageService imageService;

    @PersistenceContext
    EntityManager entityManager;

    private Movie movie;
    private Image imageTitle;

    private Image image2;

    @BeforeMethod
    public void before(){
        this.imageTitle = new Image();
        imageTitle.setImage("obrazok".getBytes());
        imageTitle.setImageMimeType("jpg");

        this.image2 = new Image();
        image2.setImage("iny obrazok".getBytes());
        image2.setImageMimeType("png");

        this.movie = new Movie();
        movie.setName("5 proti a tak dalej");
        movie.setDescription("proti sebe navzajom a proti 100-slovakom");
        movie.setYearMade(LocalDate.of(2020,1,1));
        movie.setCountryCode("USA");
        movie.setLengthMin(200);
    }

    /**
     * test if updates both ends of relationship
     */
    @Test
    public void addToGalleryTest(){
        movieService.create(movie);
        imageService.create(imageTitle);

        movieService.addToGallery(movie,imageTitle);
        Assert.assertNotNull(movie.getGallery());
        Assert.assertNotNull(imageTitle.getMovieGallery());
        Assert.assertEquals(movie.getGallery().size(),1);
        Assert.assertEquals(imageTitle.getMovieGallery(),movie);
        Assert.assertTrue(movie.getGallery().contains(imageTitle));
    }


    /**
     * test if  removes image relationship with dao
     */
    @Test
    public void removeFromGalleryTest() {
        movieService.create(movie);
        imageService.create(imageTitle);

        movieService.addToGallery(movie,imageTitle);
        Assert.assertEquals(movie.getGallery().size(),1);

        movieService.removeFromGallery(movie,imageTitle);
        entityManager.flush();

        Assert.assertEquals(movie.getGallery().size(),0);
        Assert.assertNull(imageTitle.getMovieGallery());

        Image found = imageService.getById(imageTitle.getId());
        Assert.assertNull(found);
    }

    @Test
    public void setImageTitleTest() {
        movieService.create(movie);
        imageService.create(imageTitle);
        imageService.create(image2);

        movieService.setImageTitle(movie,imageTitle);
        Assert.assertEquals(movie.getImageTitle(),imageTitle);


        movieService.setImageTitle(movie,image2);
        Assert.assertEquals(movie.getImageTitle(),image2);

        Image found = imageService.getById(imageTitle.getId());
        Assert.assertNull(found);
    }
}


