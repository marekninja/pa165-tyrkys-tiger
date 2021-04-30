package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.dto.UserPasswordlessDTO;
import cz.muni.fi.pa165.entity.User;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.UserService;
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
    public boolean authenticate(UserAuthenticateDTO userDTO) {
        /* May throw NPE -> therefore check */
        Validator.validate(this.getClass(), userDTO, "Input UserDTO cannot be null!");
        return userService.authenticate(userService.findUserById(userDTO.getUserId()), userDTO.getPassword());
    }

    @Override
    @Transactional
    public void registerUser(UserDTO userDTO, String unencryptedPassword) {
        User userEntity = beanMappingService.mapTo(userDTO, User.class);
        userService.registerUser(userEntity, unencryptedPassword);
        userDTO.setId(userEntity.getId());
    }
}
