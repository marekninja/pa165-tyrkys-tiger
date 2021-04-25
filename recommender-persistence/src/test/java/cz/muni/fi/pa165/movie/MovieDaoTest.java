//package cz.muni.fi.pa165.movie;
//
//import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
//import cz.muni.fi.pa165.dao.MovieDao;
//import cz.muni.fi.pa165.entity.Movie;
//import cz.muni.fi.pa165.entity.Person;
//import cz.muni.fi.pa165.entity.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestExecutionListeners;
//import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
//import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
//import org.springframework.transaction.annotation.Transactional;
//import org.testng.Assert;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.PersistenceUnit;
//import javax.validation.ConstraintViolationException;
//import java.time.LocalDate;
//import java.time.Month;
//import java.util.List;
//
//import static org.testng.AssertJUnit.assertEquals;
//import static org.testng.AssertJUnit.assertFalse;
//
///**
// * FOR MILESTONE 1 EVAL
// * @author Peter Mravec
// */
//@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//@TestExecutionListeners(TransactionalTestExecutionListener.class)
//@Transactional
//public class MovieDaoTest extends AbstractTestNGSpringContextTests {
//
//    @Autowired
//    MovieDao movieDao;
//
//    @PersistenceUnit
//    private EntityManagerFactory entityManagerFactory;
//
//    private Movie movie;
//    private Movie movie1;
//
//    /**
//     * Set up Movie special cases
//     */
//    @BeforeClass
//    public void before(){
//        Movie movie = new Movie();
//        movie.setName("Popelka");
//        movie.setDescription("a sedem trpaslikov.");
//
//        this.movie = movie;
//
//        Movie movie1 = new Movie();
//        movie1.setName("Lobotomia");
//        movie1.setDescription("Príbeh o nekonečnej znalosti.");
//
//        this.movie1 = movie1;
//    }
//
//    @Test
//    public void createMovieTest(){
//        movieDao.create(movie);
//        Movie foundMovie = movieDao.findById(movie.getId());
//        Assert.assertNotNull(foundMovie);
//        Assert.assertEquals(movie, foundMovie);
//    }
//
//    @Test
//    public void updateMovieTest() {
//        movieDao.create(movie1);
//        Movie foundMovie = movieDao.findById(movie1.getId());
//        assertEquals(movie1, foundMovie);
//        foundMovie.setCountryCode("USA");
//        movieDao.update(foundMovie);
//        foundMovie = movieDao.findById(movie1.getId());
//        assertEquals("USA", foundMovie.getCountryCode());
//    }
//
//    @Test
//    public void findAllMovies() {
//        assertEquals(movieDao.findAll().size(), 0);
//        movieDao.create(movie);
//        movieDao.create(movie1);
//        assertEquals(movieDao.findAll().size(), 2);
//    }
//
//    @Test
//    public void removeTestMovie() {
//        assertEquals(movieDao.findAll().size(), 0);
//        movieDao.create(movie);
//        movieDao.remove(movie);
//        assertEquals(movieDao.findAll().size(), 0);
//        Movie foundMovie = movieDao.findById(movie.getId());
//        Assert.assertNull(foundMovie);
//    }
//
//    @Test
//    public void add() {
//        assertEquals(movieDao.findAll().size(), 0);
//        movieDao.create(movie);
//        movieDao.remove(movie);
//        assertEquals(movieDao.findAll().size(), 0);
//        Movie foundMovie = movieDao.findById(movie.getId());
//        Assert.assertNull(foundMovie);
//    }
//
//    @Test(expectedExceptions = ConstraintViolationException.class)
//    public void createMovieWithFutureYearMadeTest() {
//        Movie movie = new Movie();
//        movie.setName("Future");
//        movie.setDescription("It will be in the future :D.");
//        movie.setYearMade(LocalDate.of(9999, 1, 1));
//        movieDao.create(movie);
//    }
//
//    // It should failed but it was not ...... TODO!!!!
//    @Test(expectedExceptions = ConstraintViolationException.class)
//    public void updateMovieWithFutureYearMadeTest() {
//        movieDao.create(movie);
//        Movie foundMovie = movieDao.findById(movie.getId());
//        foundMovie.setYearMade(LocalDate.of(9999, 1, 1));
//        movieDao.update(foundMovie);
//        assertEquals(LocalDate.of(9999, 1, 1), foundMovie.getYearMade());
//    }
//
//    @Test(expectedExceptions = ConstraintViolationException.class)
//    public void createMovieWithBlankNameTest() {
//        Movie movie = new Movie();
//        movie.setName("    ");
//        movie.setDescription("Blank name");
//        movieDao.create(movie);
//    }
//
//    // It should failed but it was not ...... TODO!!!!
//    @Test(expectedExceptions = ConstraintViolationException.class)
//    public void updateMovieWithBlankNameTest() {
//        movieDao.create(movie);
//        Movie foundMovie = movieDao.findById(movie.getId());
//        foundMovie.setName("      ");
//        movieDao.update(foundMovie);
//        assertEquals("      ", foundMovie.getName());
//    }
//
//    @Test(expectedExceptions = ConstraintViolationException.class)
//    public void createMovieWithBlankDescriptionTest() {
//        Movie movie = new Movie();
//        movie.setName("Blank description");
//        movie.setDescription("  ");
//        movieDao.create(movie);
//    }
//
//    // It should failed but it was not ...... TODO!!!!
//    @Test(expectedExceptions = ConstraintViolationException.class)
//    public void updateMovieWithBlankDescriptionTest() {
//        movieDao.create(movie);
//        Movie foundMovie = movieDao.findById(movie.getId());
//        foundMovie.setDescription("      ");
//        movieDao.update(foundMovie);
//        assertEquals("      ", foundMovie.getDescription());
//    }
//
//    /**
//     * Test if saves null, it should not
//     */
//    @Test(expectedExceptions = IllegalArgumentException.class)
//    public void createNullTest(){
//        Movie movie = null;
//        EntityManager entityManager = null;
//        try{
//            entityManager = entityManagerFactory.createEntityManager();
//            entityManager.getTransaction().begin();
//
//            movieDao.create(movie);
//
//            entityManager.getTransaction().commit();
//        }finally {
//            if (entityManager != null){
//                entityManager.close();
//            }
//        }
//    }
//
//    @Test
//    public void removeMovieTest(){
//        Movie movie = new Movie();
//        movie.setName("Pokoj v duši");
//        movie.setDescription("Vec, ktorú nikdy nedosiahnem.");
//        movie.setYearMade(LocalDate.of(2020, Month.DECEMBER, 10));
//
//        EntityManager entityManager = null;
//        try{
//            entityManager = entityManagerFactory.createEntityManager();
//            entityManager.getTransaction().begin();
//
//            movieDao.create(movie);
//
//            entityManager.getTransaction().commit();
//        } finally {
//            if (entityManager != null){
//                entityManager.close();
//            }
//        }
//
//
//
//        EntityManager entityManager1 = null;
//        try{
//            entityManager1 = entityManagerFactory.createEntityManager();
//            entityManager1.getTransaction().begin();
//
//            List<Movie> movies = movieDao.findAll();
//            Assert.assertNotNull(movies);
//            Assert.assertEquals(movies.size(), 1);
//
//            entityManager1.getTransaction().commit();
//        } finally {
//            if (entityManager1 != null){
//                entityManager1.close();
//            }
//        }
//
//        EntityManager entityManager2 = null;
//        try{
//            entityManager2 = entityManagerFactory.createEntityManager();
//            entityManager2.getTransaction().begin();
//
//            movieDao.remove(movie);
//
//            entityManager2.getTransaction().commit();
//        } finally {
//            if (entityManager2 != null){
//                entityManager2.close();
//            }
//        }
//
//        EntityManager entityManager3 = null;
//        try{
//            entityManager3 = entityManagerFactory.createEntityManager();
//            entityManager3.getTransaction().begin();
//
//            List<Movie> movies = movieDao.findAll();
//            Assert.assertNotNull(movies);
//            Assert.assertEquals(movies.size(), 0);
//
//            entityManager3.getTransaction().commit();
//        } finally {
//            if (entityManager3 != null){
//                entityManager3.close();
//            }
//        }
//    }
//
//    @Test
//    public void findByNameTest() {
//        EntityManager entityManager = null;
//        Movie movie = new Movie();
//        movie.setName("Variacie a kombinacie");
//        movie.setDescription("Maturitná skúška.");
//        movie.setYearMade(LocalDate.of(2020, Month.DECEMBER, 10));
//
//        try {
//            entityManager = entityManagerFactory.createEntityManager();
//            entityManager.getTransaction().begin();
//
//            movieDao.create(movie);
//
//            entityManager.getTransaction().commit();
//        } finally {
//            if (entityManager != null){
//                entityManager.close();
//            }
//        }
//
//        EntityManager entityManager1 = null;
//        try {
//            entityManager1 = entityManagerFactory.createEntityManager();
//            entityManager1.getTransaction().begin();
//            List<Movie> movie2 = movieDao.findByName("Variacie a kombinacie");
//
//            Assert.assertNotNull(movie2);
//            Assert.assertEquals(movie2.size(), 1);
//            Assert.assertEquals(movie2.get(0).getName(), "Variacie a kombinacie");
//            Assert.assertEquals(movie2.get(0).getDescription(), "Maturitná skúška.");
//            Assert.assertEquals(movie2.get(0).getYearMade(), LocalDate.of(2020, Month.DECEMBER, 10));
//
//            entityManager1.getTransaction().commit();
//        } finally {
//            if (entityManager1 != null){
//                entityManager1.close();
//            }
//        }
//    }
//}
