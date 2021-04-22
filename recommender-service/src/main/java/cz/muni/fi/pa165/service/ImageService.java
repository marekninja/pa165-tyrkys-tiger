package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Image;

/**
 * Image service interface
 *
 * @author Marek Petrovič
 */
public interface ImageService {
    public Image create(Image image);
    public Image getById(Long id);
    public void update(Image image);
    public void delete(Image image);
}
