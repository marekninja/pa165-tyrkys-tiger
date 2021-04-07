package cz.muni.fi.pa165.image;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.entity.Image;
import cz.muni.fi.pa165.entity.Movie;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.validation.ConstraintViolationException;

/**
 * Test for Image entity
 *
 * @author Peter Mravec
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class ImageTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    private Image imageFull;
    private Image imageMinimal;
    private Image imageNoImageMimeType;
    private Image imageNoImageAttribute;
    private Image imageEmpty;
    private Movie movie1;
    private Movie movie2;

    @BeforeClass
    public void before(){
        this.movie1 = new Movie();
        movie1.setName("Terminator");
        movie1.setDescription("There is lots of pain");

        this.movie2 = new Movie();
        movie2.setName("Terminator 2");
        movie2.setDescription("Terminator continues :D");


        this.imageFull = new Image();
        imageFull.setImage(new byte[]{69, 121, 101, 45, 62, 118, 101, 114, (byte) 196, (byte) 195, 61, 101, 98});
        imageFull.setImageMimeType("jpg");
        imageFull.setMovieTitle(movie1);
        imageFull.setMovieGallery(movie2);

        this.imageMinimal = new Image();
        imageMinimal.setImage(new byte[]{69, 121, 101, 45, 62, 118});
        imageEmpty.setImageMimeType("png");

        this.imageNoImageAttribute = new Image();
        imageNoImageAttribute.setImageMimeType("png");

        this.imageNoImageMimeType = new Image();
        imageNoImageMimeType.setImage(new byte[]{69, 121, 101, 45, 62});

        this.imageEmpty = new Image();

    }

    @Test
    public void testSettersGetters(){
        Image image = new Image();
        image.setImage(new byte[]{69, 121, 101, 45, 62, 118, 101, 61, 101, 98});

        Assert.assertNotNull(image);
        Assert.assertEquals(image.getImage(), new byte[]{69, 121, 101, 45, 62, 118, 101, 61, 101, 98});

        image.setImageMimeType("png");
        Assert.assertEquals(image.getImageMimeType(), "png");

        image.setMovieTitle(this.movie1);
        Assert.assertEquals(image.getMovieTitle(),this.movie1);

        image.setMovieGallery(this.movie2);
        Assert.assertEquals(image.getMovieGallery(),this.movie2);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testDoesntSaveEmpty(){
        EntityManager entityManager = null;
        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            entityManager.persist(imageEmpty);

            entityManager.getTransaction().commit();
        }finally {
            if (entityManager != null){
                entityManager.close();
            }
        }
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testDoesntSaveNullImageMimeType(){
        EntityManager entityManager = null;
        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            entityManager.persist(imageNoImageMimeType);

            entityManager.getTransaction().commit();
        }finally {
            if (entityManager != null){
                entityManager.close();
            }
        }
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testDoesntSaveNullImageAttribute(){
        EntityManager entityManager = null;
        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            entityManager.persist(imageNoImageAttribute);

            entityManager.getTransaction().commit();
        }finally {
            if (entityManager != null){
                entityManager.close();
            }
        }
    }

    /**
     * Test if saves Image with only needed attributes
     */
    @Test
    public void testSavesMinimal(){
        EntityManager entityManager = null;
        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            entityManager.persist(imageMinimal);

            entityManager.getTransaction().commit();
        }finally {
            if (entityManager != null){
                entityManager.close();
            }
        }

        EntityManager entityManager1 = null;
        try{
            entityManager1 = entityManagerFactory.createEntityManager();
            entityManager1.getTransaction().begin();

            Image image = entityManager1.find(Image.class,imageMinimal.getId());

            Assert.assertNotNull(image);
            Assert.assertEquals(image.getImageMimeType(),"png");
            Assert.assertEquals(image.getImage(),new byte[]{69, 121, 101, 45, 62, 118});

            entityManager1.getTransaction().commit();
        }finally {
            if (entityManager1 != null){
                entityManager1.close();
            }
        }
    }


    /**
     * Test if saves Image with same image + imageMimeType as already saved, it should not
     */
    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testDoesntSaveNotUnique(){
        Image image = new Image();
        image.setImageMimeType("jpg");
        image.setImage(new byte[]{69, 121, 101, 45, 62, 118, 101, 61, 101, 98});
        EntityManager entityManager = null;
        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            entityManager.persist(image);

            entityManager.getTransaction().commit();
        }finally {
            if (entityManager != null){
                entityManager.close();
            }
        }

        EntityManager entityManager1 = null;
        try{
            entityManager1 = entityManagerFactory.createEntityManager();
            entityManager1.getTransaction().begin();

            Image imageNotUnique = new Image();
            imageNotUnique.setImageMimeType("jpg");
            imageNotUnique.setImage(new byte[]{69, 121, 101, 45, 62, 118, 101, 61, 101, 98});

            entityManager1.persist(imageNotUnique);

            entityManager1.getTransaction().commit();
        }finally {
            if (entityManager1 != null){
                entityManager1.close();
            }
        }
    }
}
