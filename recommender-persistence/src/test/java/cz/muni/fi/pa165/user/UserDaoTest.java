package cz.muni.fi.pa165.user;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.dao.UserDao;
import cz.muni.fi.pa165.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.PersistenceUnit;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * FOR MILESTONE 1 EVALUATION
 * Test for {@link UserDao}
 *
 * @author Marek Petrovič
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    UserDao userDao;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    private User user;
    private User user1;

    /**
     * Set up User special cases
     */
    @BeforeMethod
    public void before(){
        User user = new User();
        user.setNickName("MilankoBOSS");
        user.setPasswordHash("totoj3h4$h");
        user.setEmail("borec1@pokec.sk");

        this.user = user;

        User user1 = new User();
        user1.setNickName("MilankoBOSS2");
        user1.setPasswordHash("totoj3h5$h");
        user1.setEmail("borec@pokec.sk");

        this.user1 = user1;
    }

    /**
     * Simple test if create and find work
     */
    @Test
    public void createFindTest() {
        Assert.assertNull(user.getId());
        userDao.createUser(user);
        Assert.assertNotNull(user.getId());
        User found = userDao.findById(user.getId());
        Assert.assertNotNull(found);
        Assert.assertEquals(user, found);
    }

    @Test(expectedExceptions = PersistenceException.class)
    public void createDuplicateTest() {
        userDao.createUser(user);
        User duplicate = new User();
        duplicate.setName(user.getName());
        duplicate.setEmail(user.getEmail());
        duplicate.setNickName(user.getNickName());
        duplicate.setPasswordHash(user.getPasswordHash());
        userDao.createUser(duplicate);
    }

    @Test
    public void updateTest() {
        userDao.createUser(user);
        user.setName("Milanko Hacik");
        userDao.updateUser(user);
        User found = userDao.findById(user.getId());
        Assert.assertNotNull(found);
        Assert.assertEquals(user, found);
        Assert.assertEquals(user.getName(),found.getName());
        Assert.assertEquals(found.getName(),"Milanko Hacik");
    }

    @Test
    public void removeTest() {
        userDao.createUser(user);
        userDao.deleteUser(user);
        User found = userDao.findById(user.getId());
        Assert.assertNull(found);
    }

    @Test
    public void findAllTest() {
        userDao.createUser(user);
        userDao.createUser(user1);

        List<User> users = userDao.findAll();

        Assert.assertEquals(users.size(),2);

        User user2 = new User();
        user2.setNickName(user.getNickName());
        user2.setEmail(user.getEmail());

        User user3 = new User();
        user3.setNickName(user1.getNickName());
        user3.setEmail(user1.getEmail());

        Assert.assertTrue(users.contains(user2));
        Assert.assertTrue(users.contains(user3));
    }

    @Test
    public void findByNicknameTest(){
        userDao.createUser(user);

        User found = userDao.findByNickName("MilankoBOSS");
        Assert.assertNotNull(found);
        Assert.assertEquals(user,found);
        Assert.assertEquals(found.getNickName(),"MilankoBOSS");
    }

    /**
     * Test if saves null, it should not
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullTest(){
        User user = null;
        userDao.createUser(user);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateNullTest(){
        User user = null;
        userDao.updateUser(user);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNullTest(){
        User user = null;
        userDao.deleteUser(user);
    }


}
