package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.ImageDetailDTO;
import cz.muni.fi.pa165.dto.MovieDetailDTO;
import cz.muni.fi.pa165.entity.Image;
import cz.muni.fi.pa165.facade.ImageFacade;
import cz.muni.fi.pa165.jpql.MovieAndRating;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.ImageService;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.service.exceptions.NullArgumentException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;

/**
 * @author Marek Petrovič
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class ImageFacadeTest {

    @Mock
    ImageService imageService;

    @Mock
    BeanMappingService beanMappingService;

    private ImageFacade imageFacade;

    private Image imageTitle;
    private ImageDetailDTO imageDetailDto;

    @BeforeMethod
    public void before() {

        MockitoAnnotations.openMocks(this);
        this.imageFacade = new ImageFacadeImpl(imageService, beanMappingService);

        this.imageTitle = new Image();
        imageTitle.setId(1L);
        imageTitle.setImage("obrazok".getBytes());
        imageTitle.setImageMimeType("jpg");

        this.imageDetailDto = new ImageDetailDTO();
        imageDetailDto.setImage(imageTitle.getImage());
        imageDetailDto.setImageMimeType(imageTitle.getImageMimeType());
        imageDetailDto.setId(imageTitle.getId());
    }



    @Test
    public void testFindById(){
        when(imageService.getById(1L)).thenReturn(imageTitle);
        when(beanMappingService.mapTo(imageTitle, ImageDetailDTO.class))
                .thenReturn(imageDetailDto);
        ImageDetailDTO imageDetailDTOfound = imageFacade.findById(imageTitle.getId());

        Assert.assertEquals(imageDetailDTOfound,imageDetailDto);
        Mockito.verify(imageService,Mockito.times(1)).getById(1L);
        Mockito.verify(beanMappingService,Mockito.times(1)).mapTo(imageTitle,ImageDetailDTO.class);
    }

    @Test(expectedExceptions = NullArgumentException.class)
    public void testFindByIdNull(){
        imageFacade.findById(null);
    }
}
