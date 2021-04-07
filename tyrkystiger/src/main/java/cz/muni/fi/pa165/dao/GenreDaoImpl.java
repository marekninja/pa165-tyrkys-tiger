package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * This is a base implementation of {@link GenreDao}
 *
 * @author Matej Turek
 */
public class GenreDaoImpl implements GenreDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Genre findById(Long id) {
        return em.find(Genre.class, id);
    }

    @Override
    public List<Genre> findAll() {
        return em.createQuery("select g from Genre g", Genre.class).getResultList();
    }

    @Override
    public void createGenre(Genre genre) {
        em.persist(genre);
    }

    @Override
    public Genre updateGenre(Genre genre) {
        return em.merge(genre);
    }

    @Override
    public void deleteUser(Genre genre) {
        em.remove(genre);
    }
}
