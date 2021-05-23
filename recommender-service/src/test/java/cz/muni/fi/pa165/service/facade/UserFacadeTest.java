package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.UserAuthenticationDTO;
import cz.muni.fi.pa165.dto.UserCreateDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.dto.UserPasswordlessDTO;
import cz.muni.fi.pa165.entity.User;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.UserService;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.eq;

/**
 * @author Matej Turek
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@ExtendWith(MockitoExtension.class)
public class UserFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private UserService userService;

    @Mock
    private BeanMappingService beanMappingService;

    @Inject
    @InjectMocks
    private UserFacadeImpl userFacade;

    private User user;

    private UserDTO userDTO;

    private UserCreateDTO userCreateDTO;

    private UserAuthenticationDTO userAuthenticationDTO;

    private UserPasswordlessDTO userPasswordlessDTO;

    @BeforeEach
    public void init() {
        user = User.builder()
                .id(1L)
                .nickName("broskve")
                //.passwordHash("h4$hov4n€H€$Lo$O$olov")
                .name("Vysoká Štíhla")
                .email("vysoka.stihla@modeling.com")
                .isAdministrator(false)
                .dateOfBirth(LocalDate.of(2000, Month.JANUARY, 1))
                .build();

        userDTO = UserDTO.builder()
                .id(1L)
                .nickName("broskve")
                .password("OcelovaVeverkaNeskace9912")
                .name("Vysoká Štíhla")
                .email("vysoka.stihla@modeling.com")
                .isAdministrator(false)
                .dateOfBirth(LocalDate.of(2000, Month.JANUARY, 1))
                .build();

        userCreateDTO = UserCreateDTO.builder()
                .nickName("broskve")
                .password("OcelovaVeverkaNeskace9912")
                .name("Vysoká Štíhla")
                .email("vysoka.stihla@modeling.com")
                .isAdministrator(false)
                .dateOfBirth(LocalDate.of(2000, Month.JANUARY, 1))
                .build();

        userAuthenticationDTO = UserAuthenticationDTO.builder()
                .nickName("broskve")
                .password("OcelovaVeverkaNeskace9912")
                .build();

        userPasswordlessDTO = UserPasswordlessDTO.builder()
                .id(1L)
                .nickName("broskve")
                .name("Vysoká Štíhla")
                .email("vysoka.stihla@modeling.com")
                .isAdministrator(false)
                .dateOfBirth(LocalDate.of(2000, Month.JANUARY, 1))
                .build();
    }

    @Test
    public void findUserByIdTest() {
        Mockito.when(userService.findUserById(1L)).thenReturn(user);
        Mockito.when(beanMappingService.mapTo(user, UserPasswordlessDTO.class)).thenReturn(userPasswordlessDTO);

        UserPasswordlessDTO returned = userFacade.findUserById(1L);
        Mockito.verify(userService, Mockito.times(1)).findUserById(1L);
        Mockito.verify(beanMappingService, Mockito.times(1)).mapTo(user, UserPasswordlessDTO.class);

        Assertions.assertThat(returned).isEqualTo(userPasswordlessDTO);
    }

    @Test
    public void findUserByNullIdTest() {
        Mockito.when(userService.findUserById(null)).thenThrow(NullArgumentException.class);

        Assertions.assertThatThrownBy(() -> userFacade.findUserById(null)).isInstanceOf(NullArgumentException.class);

        Mockito.verify(userService, Mockito.times(1)).findUserById(null);
        Mockito.verify(beanMappingService, Mockito.times(0)).mapTo(user, UserPasswordlessDTO.class);

    }

    @Test
    public void findUserByEmailTest() {
        Mockito.when(userService.findUserByEmail("vysoka.stihla@modeling.com")).thenReturn(user);
        Mockito.when(beanMappingService.mapTo(user, UserPasswordlessDTO.class)).thenReturn(userPasswordlessDTO);

        UserPasswordlessDTO returned = userFacade.findUserByEmail("vysoka.stihla@modeling.com");
        Mockito.verify(userService, Mockito.times(1)).findUserByEmail("vysoka.stihla@modeling.com");
        Mockito.verify(beanMappingService, Mockito.times(1)).mapTo(user, UserPasswordlessDTO.class);

        Assertions.assertThat(returned).isEqualTo(userPasswordlessDTO);
    }

    @Test
    public void findUserByNullEmailTest() {
        Mockito.when(userService.findUserByEmail(null)).thenThrow(NullArgumentException.class);

        Assertions.assertThatThrownBy(() -> userFacade.findUserByEmail(null)).isInstanceOf(NullArgumentException.class);

        Mockito.verify(userService, Mockito.times(1)).findUserByEmail(null);
        Mockito.verify(beanMappingService, Mockito.times(0)).mapTo(user, UserPasswordlessDTO.class);
    }

    @Test
    public void findUserByNickNameTest() {
        Mockito.when(userService.findUserByNickName("broskve")).thenReturn(user);
        Mockito.when(beanMappingService.mapTo(user, UserPasswordlessDTO.class)).thenReturn(userPasswordlessDTO);

        UserPasswordlessDTO returned = userFacade.findUserByNickName("broskve");
        Mockito.verify(userService, Mockito.times(1)).findUserByNickName("broskve");
        Mockito.verify(beanMappingService, Mockito.times(1)).mapTo(user, UserPasswordlessDTO.class);

        Assertions.assertThat(returned).isEqualTo(userPasswordlessDTO);
    }

    @Test
    public void findUserByNullNickNameTest() {
        Mockito.when(userService.findUserByNickName(null)).thenThrow(NullArgumentException.class);

        Assertions.assertThatThrownBy(() -> userFacade.findUserByNickName(null)).isInstanceOf(NullArgumentException.class);

        Mockito.verify(userService, Mockito.times(1)).findUserByNickName(null);
        Mockito.verify(beanMappingService, Mockito.times(0)).mapTo(user, UserPasswordlessDTO.class);
    }

    @Test
    public void findAllUsersTest() {
        Mockito.when(userService.findAllUsers()).thenReturn(List.of(user));
        Mockito.when(beanMappingService.mapTo(List.of(user), UserPasswordlessDTO.class)).thenReturn(List.of(userPasswordlessDTO));

        List<UserPasswordlessDTO> returned = userFacade.findAllUsers();
        Mockito.verify(userService, Mockito.times(1)).findAllUsers();
        Mockito.verify(beanMappingService, Mockito.times(1)).mapTo(List.of(user), UserPasswordlessDTO.class);

        Assertions.assertThat(returned).containsExactlyInAnyOrder(userPasswordlessDTO);
    }

    @Test
    public void updateUserTest() {
        user.setAdministrator(true);
        Mockito.when(userService.updateUser(user)).thenReturn(user);
        Mockito.when(beanMappingService.mapTo(userDTO, User.class)).thenReturn(user);
        Mockito.when(beanMappingService.mapTo(user, UserPasswordlessDTO.class)).thenReturn(userPasswordlessDTO);

        userDTO.setAdministrator(true);
        UserPasswordlessDTO returned = userFacade.updateUser(userDTO);

        Mockito.verify(beanMappingService, Mockito.times(1)).mapTo(userDTO, User.class);
        Mockito.verify(beanMappingService, Mockito.times(1)).mapTo(user, UserPasswordlessDTO.class);
        Mockito.verify(userService, Mockito.times(1)).updateUser(user);

        userPasswordlessDTO.setAdministrator(true);
        Assertions.assertThat(returned).isEqualTo(userPasswordlessDTO);
    }

    @Test
    public void updateNullUserTest() {
        Assertions.assertThatThrownBy(() -> userFacade.updateUser(null))
                .isInstanceOf(NullArgumentException.class);

        Mockito.verify(beanMappingService, Mockito.times(0)).mapTo(null, User.class);
        Mockito.verify(beanMappingService, Mockito.times(0)).mapTo(user, UserPasswordlessDTO.class);
        Mockito.verify(userService, Mockito.times(0)).updateUser(user);
    }

    @Test
    public void deleteUserTest() {
        Mockito.when(userService.findUserById(userDTO.getId())).thenReturn(user);

        userFacade.deleteUser(userDTO.getId());

        Mockito.verify(userService, Mockito.times(1)).deleteUser(user);
        Mockito.verify(userService, Mockito.times(1)).findUserById(userDTO.getId());
    }

    @Test
    public void deleteNullUserTest() {
        Mockito.when(userService.findUserById(null)).thenThrow(NullArgumentException.class);

        Assertions.assertThatThrownBy(() -> userFacade.deleteUser(null))
                .isInstanceOf(NullArgumentException.class);

        Mockito.verify(userService, Mockito.times(0)).deleteUser(null);
        Mockito.verify(userService, Mockito.times(1)).findUserById(null);
    }

    @Test
    public void isAdministratorTest() {
        Mockito.when(userService.isAdministrator(user)).thenReturn(false);
        Mockito.when(beanMappingService.mapTo(userDTO, User.class)).thenReturn(user);

        Assertions.assertThat(userFacade.isAdministrator(userDTO)).isFalse();

        Mockito.verify(userService, Mockito.times(1)).isAdministrator(user);
        Mockito.verify(beanMappingService, Mockito.times(1)).mapTo(userDTO, User.class);
    }

    @Test
    public void isNullAdministratorTest() {
        Assertions.assertThatThrownBy(() -> userFacade.isAdministrator(null))
                .isInstanceOf(NullArgumentException.class);

        Mockito.verify(userService, Mockito.times(0)).isAdministrator(user);
        Mockito.verify(beanMappingService, Mockito.times(0)).mapTo(null, User.class);
    }

    @Test
    public void authenticateTest() {
        Mockito.when(userService.authenticate(user, "OcelovaVeverkaNeskace9912")).thenReturn(true);
        Mockito.when(userService.authenticate(eq(user), not(eq("OcelovaVeverkaNeskace9912")))).thenReturn(false);
        Mockito.when(userService.findUserByNickName(userAuthenticationDTO.getNickName())).thenReturn(user);

        Assertions.assertThat(userFacade.authenticate(userAuthenticationDTO).isSuccess()).isTrue();
        userAuthenticationDTO.setPassword("nieco");
        Assertions.assertThat(userFacade.authenticate(userAuthenticationDTO).isSuccess()).isFalse();

        Mockito.verify(userService, Mockito.times(2)).findUserByNickName(userAuthenticationDTO.getNickName());
        Mockito.verify(userService, Mockito.times(1)).authenticate(user, "OcelovaVeverkaNeskace9912");
        Mockito.verify(userService, Mockito.times(1)).authenticate(user, "nieco");
    }

    @Test
    public void authenticateNullTest() {
        Assertions.assertThatThrownBy(() -> userFacade.authenticate(null))
                .isInstanceOf(NullArgumentException.class);

        Mockito.verify(userService, Mockito.times(0)).authenticate(null, null);
        Mockito.verify(userService, Mockito.times(0)).findUserById(null);
    }

    @Test
    public void registerUserTest() {
        Mockito.when(beanMappingService.mapTo(userCreateDTO, User.class)).thenReturn(user);

        userFacade.registerUser(userCreateDTO, userDTO.getPassword());

        Mockito.verify(beanMappingService, Mockito.times(1)).mapTo(userDTO, User.class);
        Mockito.verify(userService, Mockito.times(1)).registerUser(user, "OcelovaVeverkaNeskace9912");
    }
}
