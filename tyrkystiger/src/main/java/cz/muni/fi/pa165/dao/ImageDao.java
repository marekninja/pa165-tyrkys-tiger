package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Image;

import java.util.List;

/**
 * DAO interface of {@link Image} entity
 * Supports Crud:
 *  create, read, update, remove
 *
 * @author Marek Petrovič
 */
public interface ImageDao {
    
    /**
     * Creates image in DB, from Image instance provided
     * @param image instance of Image
     */
    void create(Image image);

    /**
     * Returns all persisted Images
     * @return list of Image
     */
    List<Image> findAll();

    /**
     * Returns one Image, found by id
     * @param Id Long unique identifier in DB
     * @return Image instance
     */
    Image findById(Long Id);

    /**
     * Compares stored Image, with Image provided and updates fields in stored Image
     * @param image modified Image instance
     */
    Image update(Image image);

    /**
     * Removes Image from DB
     * @param image Image instance to be removed
     */
    void remove(Image image);

}
