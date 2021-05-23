package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.ImageDetailDTO;

/**
 * @author Marek Petrovič
 */
public interface ImageFacade {

    /**
     * Finds image by ID, needed for controller to load image by Id and serve via URL
     * @param id Image ID in DB
     * @return ImageDetailDTO, null if not found
     */
    ImageDetailDTO findById(Long id);
}
