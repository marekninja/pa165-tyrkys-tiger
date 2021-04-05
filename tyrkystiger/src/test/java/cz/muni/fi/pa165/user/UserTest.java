package cz.muni.fi.pa165.user;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.entity.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.Month;

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class UserTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    private User userFull;
    private User userMinimal;
    private User userNoNick;
    private User userNoPass;
    private User userEmpty;
    private User userInvalidMail;
    private User userFromFuture;




    @BeforeClass
    public void before(){
        this.userFull = new User();
        userFull.setName("Milanko");
        userFull.setNickName("MilankoBOSS");
        userFull.setPasswordHash("totoj3h4$h");
        userFull.setEmail("milan.boss@pokec.sk");
        userFull.setDateOfBirth(LocalDate.of(1990, Month.JANUARY,1));
        userFull.setAdministrator(false);

        this.userMinimal = new User();
        userMinimal.setPasswordHash("s1ln1h4$h");
        userMinimal.setNickName("Borec");

        this.userNoPass = new User();
        userNoPass.setNickName("Elenka");

        this.userNoNick = new User();
        userNoNick.setPasswordHash("siln1h35");

        this.userEmpty = new User();

        this.userInvalidMail = new User();
        userInvalidMail.setNickName("Dalsi");
        userInvalidMail.setPasswordHash("nejakyhash");
        userInvalidMail.setEmail("@pokec.sk");

        this.userFromFuture = new User();
        userFromFuture.setNickName("zase");
        userFromFuture.setPasswordHash("niecoine");
        userFromFuture.setDateOfBirth(LocalDate.of(2100,Month.JANUARY,1));
    }

    @Test
    public void testSettersGetters(){
        User user = new User();
        user.setNickName("Borec");
        Assert.assertEquals(user.getNickName(), "Borec");

        user.setPasswordHash("volaco");
        Assert.assertEquals(user.getPasswordHash(), "volaco");

        user.setName("Jozko Mrkvicka");
        Assert.assertEquals(user.getName(),"Jozko Mrkvicka");

        user.setAdministrator(false);
        Assert.assertEquals(user.isAdministrator(),false);

        user.setEmail("frajer@pokec.sk");
        Assert.assertEquals(user.getEmail(),"frajer@pokec.sk");

        user.setDateOfBirth(LocalDate.of(1990,Month.JANUARY,1));
        Assert.assertEquals(user.getDateOfBirth(),LocalDate.of(1990,Month.JANUARY,1));
    }

    @Test
    public void testSavesMinimal(){
        EntityManager entityManager = null;
        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            entityManager.persist(userMinimal);

            entityManager.getTransaction().commit();
        }finally {
            if (entityManager != null){
                entityManager.close();
            }
        }

        EntityManager entityManager1 = null;
        try{
            entityManager1 = entityManagerFactory.createEntityManager();
            entityManager1.getTransaction().begin();

            User user = entityManager1.find(User.class,userMinimal.getId());

            Assert.assertNotNull(user);
            Assert.assertEquals(user.getNickName(),"Borec");
            Assert.assertEquals(user.getPasswordHash(),"s1ln1h4$h");

            entityManager1.getTransaction().commit();
        }finally {
            if (entityManager1 != null){
                entityManager1.close();
            }
        }
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testDoesntSaveEmpty(){
        EntityManager entityManager = null;
        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            entityManager.persist(userEmpty);

            entityManager.getTransaction().commit();
        }finally {
            if (entityManager != null){
                entityManager.close();
            }
        }
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testDoesntSaveNullNick(){
        EntityManager entityManager = null;
        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            entityManager.persist(userNoNick);

            entityManager.getTransaction().commit();
        }finally {
            if (entityManager != null){
                entityManager.close();
            }
        }
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testDoesntSaveNullPass(){
        EntityManager entityManager = null;
        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            entityManager.persist(userNoPass);

            entityManager.getTransaction().commit();
        }finally {
            if (entityManager != null){
                entityManager.close();
            }
        }
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testDoesntSaveNotUnique(){
        EntityManager entityManager = null;
        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            entityManager.persist(userFull);

            entityManager.getTransaction().commit();
        }finally {
            if (entityManager != null){
                entityManager.close();
            }
        }

        EntityManager entityManager1 = null;
        try{
            entityManager1 = entityManagerFactory.createEntityManager();
            entityManager1.getTransaction().begin();

            User user = new User();
            user.setName("MilankoBOSS");
            user.setName("totoj3inyh4$h");

            entityManager1.persist(user);

            entityManager1.getTransaction().commit();
        }finally {
            if (entityManager1 != null){
                entityManager1.close();
            }
        }
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testInvalidMail(){
        EntityManager entityManager = null;
        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            entityManager.persist(userInvalidMail);

            entityManager.getTransaction().commit();
        }finally {
            if (entityManager != null){
                entityManager.close();
            }
        }
    }

    @Test
    public void testFutureBirthdate(){
        EntityManager entityManager = null;
        try{
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            entityManager.persist(userFromFuture);

            entityManager.getTransaction().commit();
        }finally {
            if (entityManager != null){
                entityManager.close();
            }
        }
    }
    
}
