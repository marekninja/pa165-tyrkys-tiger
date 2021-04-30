package cz.muni.fi.pa165.image;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.dao.ImageDao;
import cz.muni.fi.pa165.dao.MovieDao;
import cz.muni.fi.pa165.entity.Image;
import cz.muni.fi.pa165.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.*;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * Test for Image entity
 *
 * @author Peter Mravec started implementing - tests marked with author tag
 * @author Marek Petrovič reworked and finished all
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ImageDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    ImageDao imageDao;

    @Autowired
    MovieDao movieDao;

    private Image imageFull;
    private Image imageMinimal;
    private Image imageNoImageMimeType;
    private Image imageNoImageAttribute;
    private Image imageEmpty;

    /**
     * @author Peter Mravec
     */
    @BeforeMethod
    public void before(){

        this.imageFull = new Image();
        imageFull.setImage(new byte[]{69, 121, 101, 45, 62, 118, 101, 114, (byte) 196, (byte) 195, 61, 101, 98});
        imageFull.setImageMimeType("jpg");

        this.imageMinimal = new Image();
        imageMinimal.setImage(new byte[]{69, 121, 101, 45, 62, 118});
        imageMinimal.setImageMimeType("png");

        this.imageNoImageAttribute = new Image();
        imageNoImageAttribute.setImageMimeType("png");

        this.imageNoImageMimeType = new Image();
        imageNoImageMimeType.setImage(new byte[]{69, 121, 101, 45, 62});

        this.imageEmpty = new Image();

    }

    @Test
    public void testCreate() {
        Assert.assertNull(imageFull.getId());
        imageDao.create(imageFull);
        Assert.assertNotNull(imageFull.getId());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreateNull(){
        imageDao.create(null);
    }

    /**
     * @author Peter Mravec
     */
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testDoesntSaveEmpty(){
        imageDao.create(imageEmpty);
        entityManager.flush();
    }

    /**
     * @author Peter Mravec
     */
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testDoesntSaveNullImageMimeType(){
        imageDao.create(imageNoImageMimeType);
        entityManager.flush();
    }

    /**
     * @author Peter Mravec
     */
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testDoesntSaveNullImageAttribute(){
        imageDao.create(imageNoImageAttribute);
        entityManager.flush();
    }

    /**
     * Test if saves Image with only needed attributes
     * @author Peter Mravec
     */
    @Test
    public void testSavesMinimal(){
        Assert.assertNull(imageMinimal.getId());
        imageDao.create(imageMinimal);
        Assert.assertNotNull(imageMinimal.getId());
    }

    @Test
    public void testFindAll(){
        imageDao.create(imageFull);
        imageDao.create(imageMinimal);
        List<Image> images = imageDao.findAll();
        Assert.assertNotNull(images);
        Assert.assertEquals(images.size(),2);
    }

    @Test
    public void testFindAllEmpty(){
        List<Image> images = imageDao.findAll();
        Assert.assertNotNull(images);
        Assert.assertEquals(images.size(),0);
    }

    @Test
    public void testFindById(){
        imageDao.create(imageFull);
        Image found = imageDao.findById(imageFull.getId());
        Assert.assertNotNull(found);
        Assert.assertEquals(found,imageFull);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindByIdNull(){
        imageDao.findById(null);
    }

    @Test
    public void testFindByIdNonExist(){
        Image image = imageDao.findById(2000L);
        Assert.assertNull(image);
    }

    @Test
    public void testUpdate(){
        Movie movie = new Movie();
        movie.setName("asddfasfads");
        movie.setDescription("dfsfdadsfaadsfe");
        movieDao.create(movie);
        imageDao.create(imageFull);
        Assert.assertNull(imageFull.getMovieGallery());

        imageFull.setMovieGallery(movie);
        imageDao.update(imageFull);

        Assert.assertEquals(imageFull.getMovieGallery(),movie);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testUpdateNull(){
        imageDao.update(null);
    }

    @Test
    public void testRemove(){
        imageDao.create(imageFull);
        imageDao.remove(imageFull);
        Image found = imageDao.findById(imageFull.getId());
        Assert.assertNull(found);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testRemoveNull(){
        imageDao.remove(null);
    }

}
