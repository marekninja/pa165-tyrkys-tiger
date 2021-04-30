package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.ImageDao;
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

import javax.persistence.PersistenceException;

import static org.mockito.Mockito.*;

/**
 * @author Marek Petrovič
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class ImageServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    ImageDao imageDao;

    @Autowired
    @InjectMocks
    ImageService imageService;

    private Image image;

    @BeforeClass
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @BeforeMethod
    public void before(){
        this.image = new Image();
        image.setId(1L);
        image.setImage("momentka".getBytes());
        image.setImageMimeType("jpg");
    }

    @Test
    public void getByIdTest(){
        when(imageDao.findById(image.getId())).thenReturn(image);
        Image found = imageService.getById(image.getId());
        Assert.assertNotNull(found);
        Assert.assertEquals(found,image);
    }

    @Test(expectedExceptions = PersistenceException.class)
    public void getByIdTestNull(){
        doThrow(PersistenceException.class).when(imageDao).findById(null);
        Image found = imageService.getById(null);
    }

    @Test
    public void createTest(){
        doNothing().when(imageDao).create(image);
        Image image1 = imageService.create(image);
        Assert.assertNotNull(image1);
        Assert.assertEquals(image1,image);
    }

    @Test(expectedExceptions = PersistenceException.class)
    public void createTestNull(){
        doThrow(PersistenceException.class).when(imageDao).create(null);
        Image image1 = imageService.create(null);
    }

    @Test
    public void updateTest(){
        when(imageDao.update(image)).thenReturn(image);
        image.setImageMimeType("png");
        image.setImage("novy obsah".getBytes());
        imageService.update(image);
        Assert.assertEquals(image.getImage(),"novy obsah".getBytes());
        Assert.assertEquals(image.getImageMimeType(),"png");
    }

    @Test(expectedExceptions = PersistenceException.class)
    public void updateTestNull(){
        doThrow(PersistenceException.class).when(imageDao).update(null);
        imageService.update(null);
    }

    @Test
    public void deleteTest(){
        doNothing().when(imageDao).remove(image);
        
    }

}
