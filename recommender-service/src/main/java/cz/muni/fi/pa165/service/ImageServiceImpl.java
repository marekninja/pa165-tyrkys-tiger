package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.ImageDao;
import cz.muni.fi.pa165.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link ImageService}
 *
 * @author Marek Petrovič
 */
@Service
public class ImageServiceImpl implements ImageService {

    final ImageDao imageDao;

    @Autowired
    public ImageServiceImpl(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    @Override
    public Image create(Image image) {
        imageDao.create(image);
        return image;
    }

    @Override
    public Image getById(Long id) {
        return imageDao.findById(id);
    }

    @Override
    public void update(Image image) {
        imageDao.update(image);
    }

    @Override
    public void delete(Image image) {
        imageDao.remove(image);
    }
}
