package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.ImageDetailDTO;
import cz.muni.fi.pa165.dto.MovieDetailDTO;
import cz.muni.fi.pa165.entity.Image;
import cz.muni.fi.pa165.facade.ImageFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.ImageService;
import cz.muni.fi.pa165.service.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marek Petrovič
 */
@Service
@Transactional
public class ImageFacadeImpl implements ImageFacade {

    private final ImageService imageService;

    private final BeanMappingService beanMappingService;

    @Autowired
    public ImageFacadeImpl(ImageService imageService, BeanMappingService beanMappingService) {
        this.imageService = imageService;
        this.beanMappingService = beanMappingService;
    }

    @Override
    public ImageDetailDTO findById(Long id) {
        Validator.validate(this.getClass(),id,"Image ID can not be NULL");
        Image image = imageService.getById(id);
        if (image == null){
            return null;
        }
        return beanMappingService.mapTo(image, ImageDetailDTO.class);
    }
}
