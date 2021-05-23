package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.entity.User;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.UserService;
import cz.muni.fi.pa165.service.exceptions.AuthenticationException;
import cz.muni.fi.pa165.service.utils.Validator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Matej Turek
 */
@Service
@Transactional(readOnly = true)
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;

    private final BeanMappingService beanMappingService;

    @Inject
    public UserFacadeImpl(UserService userService, BeanMappingService mapping) {
        this.userService = userService;
        this.beanMappingService = mapping;
    }

    @Override
    public UserPasswordlessDTO findUserById(Long id) {
        User storedUser = userService.findUserById(id);
        return (storedUser == null) ? null :
                beanMappingService.mapTo(storedUser, UserPasswordlessDTO.class);
    }

    @Override
    public UserPasswordlessDTO findUserByEmail(String email) {
        User storedUser = userService.findUserByEmail(email);
        return (storedUser == null) ? null :
                beanMappingService.mapTo(storedUser, UserPasswordlessDTO.class);
    }

    @Override
    public UserPasswordlessDTO findUserByNickName(String nickName) {
        User storedUser = userService.findUserByNickName(nickName);
        return (storedUser == null) ? null :
                beanMappingService.mapTo(storedUser, UserPasswordlessDTO.class);
    }

    @Override
    public List<UserPasswordlessDTO> findAllUsers() {
        return beanMappingService.mapTo(userService.findAllUsers(), UserPasswordlessDTO.class);
    }

    @Override
    @Transactional
    public UserPasswordlessDTO updateUser(UserDTO userDTO) {
        Validator.validate(this.getClass(), userDTO, "Input UserDTO cannot be null!");
        User updated = userService.updateUser(beanMappingService.mapTo(userDTO, User.class));
        return beanMappingService.mapTo(updated, UserPasswordlessDTO.class);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        User user = userService.findUserById(userId);
        userService.deleteUser(user);
    }

    @Override
    public boolean isAdministrator(UserDTO userDTO) {
        Validator.validate(this.getClass(), userDTO, "Input UserDTO cannot be null!");

        return userService.isAdministrator(beanMappingService.mapTo(userDTO, User.class));
    }

    @Override
    public UserAuthenticationResponseDTO authenticate(UserAuthenticationDTO userDTO) throws AuthenticationException {
        /* May throw NPE -> therefore check */
        Validator.validate(this.getClass(), userDTO, "Input UserDTO cannot be null!");

        User user = userService.findUserByEmail(userDTO.getNickName());

        if (userService.authenticate(user, userDTO.getPassword())) {
            throw new AuthenticationException("Invalid credentials!");
        }

        return UserAuthenticationResponseDTO.builder()
                .user(beanMappingService.mapTo(user, UserPasswordlessDTO.class))
                .success(true)
                .build();
    }

    @Override
    @Transactional
    public UserPasswordlessDTO registerUser(UserCreateDTO userCreateDTO, String unencryptedPassword) {
        User userEntity = beanMappingService.mapTo(userCreateDTO, User.class);
        userService.registerUser(userEntity, unencryptedPassword);

        return beanMappingService.mapTo(userEntity, UserPasswordlessDTO.class);
    }
}
