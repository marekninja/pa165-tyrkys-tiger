//package cz.muni.fi.pa165.userrating;
//
//import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
//import cz.muni.fi.pa165.entity.Movie;
//import cz.muni.fi.pa165.entity.User;
//import cz.muni.fi.pa165.entity.UserRating;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.PersistenceUnit;
//import javax.validation.ConstraintViolationException;
//
///**
// * Unit tests for entity UserRating.
// *
// * @author Matej Turek
// */
//@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
//public class UserRatingTest extends AbstractTestNGSpringContextTests {
//
//    @PersistenceUnit
//    private EntityManagerFactory emf;
//
//    private UserRating rating;
//    private UserRating emptyRating;
//
//
//    @BeforeClass
//    public void before() {
//        Movie movie = new Movie();
//        movie.setName("Why");
//        movie.setDescription("Just why?");
//
//        User user = new User();
//        user.setNickName("JožiVaja");
//        user.setPasswordHash("H4$hov4ni3");
//        user.setName("Jožko Vajda");
//        user.setEmail("123@mail.muni.cz");
//
//        rating = new UserRating();
//        rating.setMovie(movie);
//        rating.setUser(user);
//        rating.setStoryScore(9);
//        rating.setVisualScore(9);
//        rating.setActorScore(9);
//        rating.setOverallScore(9);
//
//        emptyRating = new UserRating();
//    }
//
//    @Test
//    public void fullRatingTest() {
//        EntityManager em = null;
//
//        try {
//            em = emf.createEntityManager();
//
//            em.getTransaction().begin();
//
//            em.persist(rating);
//
//            em.getTransaction().commit();
//
//        } finally {
//            if (em != null) em.close();
//        }
//    }
//
//    @Test(expectedExceptions = ConstraintViolationException.class)
//    public void emptyRatingTest() {
//        EntityManager em = null;
//
//        try {
//            em = emf.createEntityManager();
//
//            em.getTransaction().begin();
//
//            em.persist(emptyRating);
//
//            em.getTransaction().commit();
//
//        } finally {
//            if (em != null) em.close();
//        }
//    }
//
//    @Test
//    public void Test() {
//        EntityManager em = null;
//
//        try {
//            em = emf.createEntityManager();
//
//            em.getTransaction().begin();
//
//
//            em.getTransaction().commit();
//
//        } finally {
//            if (em != null) em.close();
//        }
//    }
//
///*    @Test
//    public void Test() {
//        EntityManager em = null;
//
//        try {
//            em = emf.createEntityManager();
//
//            em.getTransaction().begin();
//
//
//            em.getTransaction().commit();
//
//        } finally {
//            if (em != null) em.close();
//        }
//    }
//
//    @Test
//    public void Test() {
//        EntityManager em = null;
//
//        try {
//            em = emf.createEntityManager();
//
//            em.getTransaction().begin();
//
//
//            em.getTransaction().commit();
//
//        } finally {
//            if (em != null) em.close();
//        }
//    }*/
//}
