package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * This is a base implementation of {@link GenreDao}
 *
 * @author Matej Turek
 */
@Repository
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
        if (genre == null){
            throw new IllegalArgumentException("Genre was null!");
        }
        em.remove(this.findById(genre.getId()));
    }
}
