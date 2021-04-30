package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Image;
import cz.muni.fi.pa165.entity.Movie;

/**
 * Interfaces that defines service access to {@link Image}
 *
 * @author Marek Petrovič
 */
public interface ImageService {

    /**
     * Creates image
     * @param image Image
     * @return Image created
     */
    Image create(Image image);

    /**
     * Gets Image by ID from DB
     * @param id Long Id in DB
     * @return Image
     */
    Image getById(Long id);

    /**
     * Updates Image in DB
     * @param image Image
     */
    void update(Image image);

    /**
     * Deletes Image from DB
     * @param image Image
     */
    void delete(Image image);
}
