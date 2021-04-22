package cz.muni.fi.pa165.genre;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.dao.GenreDao;
import cz.muni.fi.pa165.dao.UserDao;
import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.persistence.*;

/**
 * Test for {@link GenreDao}
 *
 * @author Marek Petrovič
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class GenreDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    GenreDao genreDao;

    private Genre genre;

    /**
     * Set up User special cases
     */
    @BeforeMethod
    public void before(){
        Genre genre = new Genre();
        genre.setName("Action");

        this.genre = genre;
    }

    /**
     * Simple test if create and find work
     */
    @Test
    public void createTest() {

        genreDao.createGenre(genre);
        Assert.assertNotNull(genre.getId());
        Genre found = genreDao.findById(genre.getId());
        Assert.assertNotNull(found);
        Assert.assertEquals(genre,found);
    }

    /**
     * Simple test if create and find work
     */
    @Test public void findTest(){
        genreDao.createGenre(genre);
        Assert.assertNotNull(genre.getId());
        Genre found = genreDao.findById(genre.getId());
        Assert.assertNotNull(found);
        Assert.assertEquals(genre,found);
        Assert.assertEquals("Action",found.getName());
    }

    /**
     * Simple test if create and find work
     */
    @Test
    public void updateTest(){
        genreDao.createGenre(genre);
        Assert.assertNotNull(genre.getId());
        Genre found = genreDao.findById(genre.getId());
        Assert.assertNotNull(found);
        Assert.assertEquals(genre,found);
        Assert.assertEquals("Action",found.getName());

        genre.setName("Romatická komédia pre celú rodinu");
        genreDao.updateGenre(genre);
        Assert.assertNotNull(found);
        Assert.assertEquals(genre,found);
        Assert.assertEquals("Romatická komédia pre celú rodinu",found.getName());
    }

    /**
     * Test if saves null, it should not
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullTest(){
        Genre genre = null;
        genreDao.createGenre(genre);
    }

    /**
     * Test if creates duplicate
     */
    @Test(expectedExceptions = PersistenceException.class)
    public void createDuplicateTest(){
        genreDao.createGenre(genre);
        Genre duplicate = new Genre();
        duplicate.setName(genre.getName());
        genreDao.createGenre(duplicate);

    }

}
