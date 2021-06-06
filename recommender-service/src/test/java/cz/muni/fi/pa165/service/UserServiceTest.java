package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.UserDao;
import cz.muni.fi.pa165.entity.User;
import cz.muni.fi.pa165.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.service.exceptions.NullArgumentException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;


/**
 * @author Matej Turek
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@ExtendWith(MockitoExtension.class)
public class UserServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private UserDao userDaoMock;

    @Mock
    private PasswordEncoder encoderMock;

    @Inject
    @InjectMocks
    private UserServiceImpl service;

    private User woman;

    @BeforeEach
    public void beforeMethod() {
        woman = User.builder()
                .id(1L)
                .nickName("broskve")
                .name("Vysoká Štíhla")
                .email("vysoka.stihla@modeling.com")
                .dateOfBirth(LocalDate.of(2000, Month.JANUARY, 1))
                .isAdministrator(false)
                .build();
    }

    @Test
    public void findUserByIdTest() {
        User fromUserDaoMock = service.findUserById(1L);

        Mockito.verify(userDaoMock, Mockito.times(1)).findById(1L);
    }

    @Test
    public void findUserByIdNullInputTest() {
        Assertions.assertThatThrownBy(() -> service.findUserById(null)).isInstanceOf(NullArgumentException.class);
        Mockito.verify(userDaoMock, Mockito.times(0)).findById(null);
    }

    @Test
    public void findUserByIdNotStoredTest() {
        service.findUserById(2L);

        Mockito.verify(userDaoMock, Mockito.times(1)).findById(2L);
    }

    @Test
    public void findUserByEmailTest() {
        Mockito.when(userDaoMock.findByEmail("vysoka.stihla@modeling.com")).thenReturn(woman);

        User fromUserDaoMock = service.findUserByEmail("vysoka.stihla@modeling.com");

        Mockito.verify(userDaoMock, Mockito.times(1)).findByEmail("vysoka.stihla@modeling.com");

        Assertions.assertThat(fromUserDaoMock).isNotNull();
        Assertions.assertThat(fromUserDaoMock).usingRecursiveComparison().isEqualTo(woman);
    }

    @Test
    public void findUserByEmailNullInputTest() {
        Assertions.assertThatThrownBy(() -> service.findUserByEmail(null)).isInstanceOf(NullArgumentException.class);
        Mockito.verify(userDaoMock, Mockito.times(0)).findByEmail(null);
    }

    @Test
    public void findUserByEmailNotStoredTest() {
        Mockito.when(userDaoMock.findByEmail(not(eq("vysoka.stihla@modeling.com")))).thenReturn(null);

        Assertions.assertThat(service.findUserByEmail("something@mail.com")).isNull();
        Mockito.verify(userDaoMock, Mockito.times(1)).findByEmail("something@mail.com");
    }

    @Test
    public void findUserByNickNameTest() {
        Mockito.when(userDaoMock.findByNickName("broskve")).thenReturn(woman);

        User fromUserDaoMock = service.findUserByNickName("broskve");

        Mockito.verify(userDaoMock, Mockito.times(1)).findByNickName("broskve");

        Assertions.assertThat(fromUserDaoMock).isNotNull();
        Assertions.assertThat(fromUserDaoMock).usingRecursiveComparison().isEqualTo(woman);
    }

    @Test
    public void findUserByNickNameNullInputTest() {
        Assertions.assertThatThrownBy(() -> service.findUserByNickName(null)).isInstanceOf(NullArgumentException.class);
        Mockito.verify(userDaoMock, Mockito.times(0)).findByNickName(null);
    }

    @Test
    public void findUserByNickNameNotStoredTest() {
        Mockito.when(userDaoMock.findByNickName(not(eq("broskve")))).thenReturn(null);

        Assertions.assertThat(service.findUserByNickName("nickname")).isNull();
        Mockito.verify(userDaoMock, Mockito.times(1)).findByNickName("nickname");
    }

    @Test
    public void findAllUsersTest() {
        Mockito.when(userDaoMock.findAll()).thenReturn(List.of(woman));

        List<User> fromUserDaoMock = service.findAllUsers();

        Mockito.verify(userDaoMock, Mockito.times(1)).findAll();

        Assertions.assertThat(fromUserDaoMock).isNotNull();
        Assertions.assertThat(fromUserDaoMock).isNotEmpty();
        Assertions.assertThat(fromUserDaoMock).containsExactlyInAnyOrder(woman);
    }

    @Test
    public void updateUserTest() {
        woman.setName("Nízka Široká");
        woman.setEmail("nizka.siroka@modeling.com");
        service.updateUser(woman);

        Mockito.verify(userDaoMock, Mockito.times(1)).updateUser(woman);
    }

    @Test
    public void updateNullUserTest() {
        Assertions.assertThatThrownBy(() -> service.updateUser(null)).isInstanceOf(NullArgumentException.class);
        Mockito.verify(userDaoMock, Mockito.times(0)).updateUser(null);
    }

    @Test
    public void deleteUserTest() {
        service.deleteUser(woman);
        Mockito.verify(userDaoMock, Mockito.times(1)).deleteUser(woman);
    }

    @Test
    public void deleteNullUserTest() {
        Assertions.assertThatThrownBy(() -> service.deleteUser(null)).isInstanceOf(NullArgumentException.class);
        Mockito.verify(userDaoMock, Mockito.times(0)).deleteUser(null);
    }

    @Test
    public void authenticateNullUserUserTest() {
        Assertions.assertThatThrownBy(() -> service.authenticate(null, "password"))
                .isInstanceOf(NullArgumentException.class);
    }

    @Test
    public void authenticateNullPasswordUserTest() {
        Assertions.assertThatThrownBy(() -> service.authenticate(woman, null))
                .isInstanceOf(NullArgumentException.class);
        Mockito.verify(userDaoMock, Mockito.times(0)).findById(1L);
    }

    @Test
    public void registerNullUserUserTest() {
        Assertions.assertThatThrownBy(() -> service.registerUser(null, "password1234"))
                .isInstanceOf(NullArgumentException.class);
    }

    @Test
    public void registerNullPasswordUserTest() {
        Assertions.assertThatThrownBy(() -> service.registerUser(woman, null))
                .isInstanceOf(NullArgumentException.class);
        Mockito.verify(userDaoMock, Mockito.times(0)).createUser(woman);
    }

    @Test
    public void registerUserTest() {
        service.registerUser(woman, "password1234");
        Mockito.verify(userDaoMock, Mockito.times(1)).createUser(woman);
        Mockito.verify(encoderMock, Mockito.times(1)).encode("password1234");
    }

    @Test
    public void authenticateValidUserTest() {
        Mockito.when(encoderMock.encode("password1234")).thenReturn("h4$hov4n€H€$Lo$O$olov");
        Mockito.when(encoderMock.matches("password1234", "h4$hov4n€H€$Lo$O$olov")).thenReturn(true);

        service.registerUser(woman, "password1234");
        Assertions.assertThat(woman.getPasswordHash()).isEqualTo("h4$hov4n€H€$Lo$O$olov");
        Assertions.assertThat(service.authenticate(woman, "password1234")).isTrue();

        Mockito.verify(encoderMock, Mockito.times(1)).matches("password1234", woman.getPasswordHash());
    }

    @Test
    public void authenticateInvalidUserTest() {
        Mockito.when(encoderMock.matches(any(), any())).thenReturn(false);

        Assertions.assertThat(woman.getPasswordHash()).isNotEqualTo("h4$hov4n€H€$Lo$O$olov");
        Assertions.assertThat(service.authenticate(woman, "password1234")).isFalse();

        Mockito.verify(encoderMock, Mockito.times(1)).matches("password1234", woman.getPasswordHash());
    }
}
