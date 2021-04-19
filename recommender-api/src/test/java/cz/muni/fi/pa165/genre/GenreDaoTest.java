//package cz.muni.fi.pa165.genre;
//
//import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
//import cz.muni.fi.pa165.dao.UserDao;
//import cz.muni.fi.pa165.entity.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.testng.Assert;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.PersistenceUnit;
//
///**
// * Test for {@link GenreDao}
// *
// * @author Marek Petrovič
// */
//@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
//public class GenreDaoTest {
//
//    @Autowired
//    UserDao userDao;
//
//    @PersistenceUnit
//    private EntityManagerFactory entityManagerFactory;
//
//    private User user;
//    private User user1;
//
//    /**
//     * Set up User special cases
//     */
//    @BeforeClass
//    public void before(){
//        User user = new User();
//        user.setNickName("MilankoBOSS");
//        user.setPasswordHash("totoj3h4$h");
//
//        this.user = user;
//
//        User user1 = new User();
//        user1.setNickName("MilankoBOSS2");
//        user1.setPasswordHash("totoj3h5$h");
//
//        this.user1 = user1;
//    }
//
//    /**
//     * Simple test if create and find work
//     */
//    @Test
//    public void createFindUpdateTest(){
//
//        EntityManager entityManager = null;
//
//        // Simple user, should work fine
//        try{
//            entityManager = entityManagerFactory.createEntityManager();
//            entityManager.getTransaction().begin();
//
//            userDao.createUser(user);
//            userDao.createUser(user1);
//
//            entityManager.getTransaction().commit();
//        }finally {
//            if (entityManager != null){
//                entityManager.close();
//            }
//        }
//
//        EntityManager entityManager1 = null;
//        try{
//            entityManager1 = entityManagerFactory.createEntityManager();
//            entityManager1.getTransaction().begin();
//            User user2 = userDao.findById(user.getId());
//            User user3 = userDao.findById(user1.getId());
//
//            Assert.assertNotNull(user2);
//            Assert.assertEquals(user2.getNickName(), "MilankoBOSS");
//            Assert.assertEquals(user2.getPasswordHash(), "totoj3h4$h");
//
//            Assert.assertNotNull(user3);
//            Assert.assertEquals(user3.getNickName(), "MilankoBOSS2");
//            Assert.assertEquals(user3.getPasswordHash(), "totoj3h5$h");
//
//            entityManager1.getTransaction().commit();
//        }finally {
//            if (entityManager1 != null){
//                entityManager1.close();
//            }
//        }
//
//        this.user.setName("Milanko Hacik");
//
//        EntityManager entityManager2 = null;
//        try{
//            entityManager2 = entityManagerFactory.createEntityManager();
//            entityManager2.getTransaction().begin();
//            userDao.updateUser(this.user);
//
//            entityManager2.getTransaction().commit();
//        }finally {
//            if (entityManager2 != null){
//                entityManager2.close();
//            }
//        }
//
//        EntityManager entityManager3 = null;
//        try{
//            entityManager3 = entityManagerFactory.createEntityManager();
//            entityManager3.getTransaction().begin();
//            User user = userDao.findById(this.user.getId());
//
//            Assert.assertNotNull(user);
//            Assert.assertEquals(user.getName(),"Milanko Hacik");
//
//            entityManager3.getTransaction().commit();
//        }finally {
//            if (entityManager3 != null){
//                entityManager3.close();
//            }
//        }
//
//    }
//
//    /**
//     * Test if saves null, it should not
//     */
//    @Test(expectedExceptions = IllegalArgumentException.class)
//    public void createNullTest(){
//        User user = null;
//        EntityManager entityManager = null;
//        try{
//            entityManager = entityManagerFactory.createEntityManager();
//            entityManager.getTransaction().begin();
//
//            userDao.createUser(user);
//
//            entityManager.getTransaction().commit();
//        }finally {
//            if (entityManager != null){
//                entityManager.close();
//            }
//        }
//    }
//
////    /**
////     * Test if update works
////     */
////    @Test
////    public void updateTest(){
////        EntityManager entityManager = null;
////
////        this.user.setName("Milanko Hacik");
////
////        try{
////            entityManager = entityManagerFactory.createEntityManager();
////            entityManager.getTransaction().begin();
////
////            User userFound = userDao.findById(this.user.getId());
////            Assert.assertNotNull(userFound);
////            Assert.assertNotEquals(userFound.getName(),"Milanko Hacik");
////
////            userDao.updateUser(user);
////
////            User userUpdated = userDao.findById(this.user.getId());
////            Assert.assertNotNull(userUpdated);
////            Assert.assertNotEquals(userUpdated.getName(),"Milanko Hacik");
////
////            entityManager.getTransaction().commit();
////        }finally {
////            if (entityManager != null){
////                entityManager.close();
////            }
////        }
////    }
//
//
//}
