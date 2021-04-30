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
    private ImageDao imageDao;

    private ImageService imageService;

    private Image image;

    @BeforeClass
    public void init(){

        MockitoAnnotations.openMocks(this);
        this.imageService = new ImageServiceImpl(imageDao);
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

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getByIdTestNull(){
        doThrow(IllegalArgumentException.class).when(imageDao).findById(null);
        Image found = imageService.getById(null);
    }

    @Test
    public void createTest(){
        doNothing().when(imageDao).create(image);
        Image image1 = imageService.create(image);
        Assert.assertNotNull(image1);
        Assert.assertEquals(image1,image);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createTestNull(){
        doThrow(IllegalArgumentException.class).when(imageDao).create(null);
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

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateTestNull(){
        doThrow(IllegalArgumentException.class).when(imageDao).update(null);
        imageService.update(null);
    }

    @Test
    public void deleteTest(){
        doNothing().when(imageDao).remove(image);
        imageService.delete(image);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteTestNull(){
        doThrow(IllegalArgumentException.class).when(imageDao).remove(null);
        imageService.delete(null);
    }

}
