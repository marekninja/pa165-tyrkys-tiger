package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.entity.User;
import cz.muni.fi.pa165.exceptions.DataAccessExceptionImpl;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.UserService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Matej Turek
 */
@Service
@Transactional
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;

    private final BeanMappingService beanMappingService;

    @Inject
    public UserFacadeImpl(UserService userService, BeanMappingService mapping) {
        this.userService = userService;
        this.beanMappingService = mapping;
    }

    @Override
    public UserDTO findUserById(Long id) {
        User storedUser = userService.findUserById(id);
        return (storedUser == null) ? null :
                beanMappingService.mapTo(storedUser, UserDTO.class);
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        User storedUser = userService.findUserByEmail(email);
        return (storedUser == null) ? null :
                beanMappingService.mapTo(storedUser, UserDTO.class);
    }

    @Override
    public UserDTO findUserByNickName(String nickName) {
        User storedUser = userService.findUserByNickName(nickName);
        return (storedUser == null) ? null :
                beanMappingService.mapTo(storedUser, UserDTO.class);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return beanMappingService.mapTo(userService.findAllUsers(), UserDTO.class);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        return null;
    }

    /*TODO consult what will happen with UserRatings after user is deleted!!!*/

    @Override
    public void deleteUser(Long userId) {
        try {
            userService.deleteUser(new User(userId));
        } catch (IllegalArgumentException ex) {
            throw new DataAccessExceptionImpl("Method tried to delete null.", ex);
        }
    }

    @Override
    public boolean isAdministrator(UserDTO userDTO) {
        return userService.isAdministrator(beanMappingService.mapTo(userDTO, User.class));
    }

    @Override
    public boolean authenticate(UserAuthenticateDTO userDTO) {
        return userService.authenticate(userService.findUserById(userDTO.getUserId()), userDTO.getPassword());
    }

    @Override
    public void registerUser(UserDTO userDTO, String unencryptedPassword) {
        User userEntity = beanMappingService.mapTo(userDTO, User.class);
        userService.registerUser(userEntity, unencryptedPassword);
        userDTO.setId(userEntity.getId());
    }
}
