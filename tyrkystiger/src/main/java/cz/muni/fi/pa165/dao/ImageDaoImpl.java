package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Image;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ImageDaoImpl implements ImageDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void create(Image image) {
        entityManager.persist(image);
    }

    @Override
    public List<Image> findAll() {

        return entityManager.createQuery("select i from Image i", Image.class).getResultList();
    }

    @Override
    public Image findById(Long Id) {

        return entityManager.find(Image.class,Id);
    }

    @Override
    public Image update(Image image) {

        return entityManager.merge(image);
    }

    @Override
    public void remove(Image image) {
        entityManager.remove(image);
    }
}
