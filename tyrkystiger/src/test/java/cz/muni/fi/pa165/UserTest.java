package cz.muni.fi.pa165;

import org.springframework.test.context.ContextConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class UserTest {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    
}
