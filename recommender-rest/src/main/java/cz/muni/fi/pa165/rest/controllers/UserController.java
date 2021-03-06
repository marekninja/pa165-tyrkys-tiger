package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.rest.Uris;
import cz.muni.fi.pa165.rest.exceptions.*;
import cz.muni.fi.pa165.rest.hateoas.UserRepresentationModelAssembler;
import cz.muni.fi.pa165.service.exceptions.AuthenticationException;
import cz.muni.fi.pa165.service.exceptions.NullArgumentException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

// SWAGGER - info
// https://docs.swagger.io/swagger-core/v1.5.0/apidocs/io/swagger/annotations/ApiOperation.html
// https://docs.swagger.io/swagger-core/v1.5.0/apidocs/io/swagger/annotations/ApiResponse.html

/**
 * @author Matej Turek
 */
@RestController
@ExposesResourceFor(UserPasswordlessDTO.class)
@RequestMapping(Uris.ROOT_URI_USERS)
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserFacade userFacade;
    private final UserRepresentationModelAssembler userRepresentationModelAssembler;

    @Autowired
    public UserController(UserFacade userFacade, UserRepresentationModelAssembler userRepresentationModelAssembler) {
        this.userFacade = userFacade;
        this.userRepresentationModelAssembler = userRepresentationModelAssembler;
    }

    /**
     * Handles GET request to find user by specific id.
     *
     * @return ResponseEntity with UserPasswordless object in json representation and status report
     */
    @ApiOperation(value = "Find user with given id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @GetMapping(value = "/{id}",
            produces = "application/hal+json")
    public ResponseEntity<?> findUserById(@PathVariable Long id) throws ResourceNotFoundException {
        logger.debug("rest findUserById() - get user by id.");

        UserPasswordlessDTO user = userFacade.findUserById(id);

        if (user == null) {
            throw new ResourceNotFoundException("User with id = {" + id + "} not found.");
        }

        EntityModel<UserPasswordlessDTO> entityModel = userRepresentationModelAssembler.toModel(user);

        return new ResponseEntity<>(entityModel, HttpStatus.OK);
    }

    /**
     * Handles GET request to find user by email.
     *
     * @return ResponseEntity with UserPasswordless object in json representation and status report
     */
    @ApiOperation(value = "Find user with given email")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @GetMapping(value = "/email/{email}",
            produces = "application/hal+json")
    public ResponseEntity<?> findUserByEmail(@PathVariable String email) throws ResourceNotFoundException {
        logger.debug("rest findUserByEmail() - get user by email.");

        UserPasswordlessDTO user = userFacade.findUserByEmail(email);

        if (user == null) {
            throw new ResourceNotFoundException("User with email = {" + email + "} not found.");
        }

        EntityModel<UserPasswordlessDTO> entityModel = userRepresentationModelAssembler.toModel(user);

        return new ResponseEntity<>(entityModel, HttpStatus.OK);
    }

    /**
     * Handles GET request to find user by nickName.
     *
     * @return ResponseEntity with UserPasswordless object in json representation and status report
     */
    @ApiOperation(value = "Find user with given nickName")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @GetMapping(value = "/nickname/{nickName}",
            produces = "application/hal+json")
    public ResponseEntity<?> findUserByNickName(@PathVariable String nickName) throws ResourceNotFoundException {
        logger.debug("rest findUserByNickName() - get user by email.");

        UserPasswordlessDTO user = userFacade.findUserByNickName(nickName);

        if (user == null) {
            throw new ResourceNotFoundException("User with nickName = {" + nickName + "} not found.");
        }

        EntityModel<UserPasswordlessDTO> entityModel = userRepresentationModelAssembler.toModel(user);

        return new ResponseEntity<>(entityModel, HttpStatus.OK);
    }

    /**
     * Handles GET request to browse all Users.
     *
     * @return ResponseEntity with collection of UserPasswordless objects in json representation and status report
     */
    @ApiOperation(value = "Find all user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class)
    })
    @GetMapping(produces = "application/hal+json")
    public final ResponseEntity<?> getAllUsers() {
        logger.debug("rest getAllUsers() - get all users");
        List<UserPasswordlessDTO> allUser = userFacade.findAllUsers();
        CollectionModel<EntityModel<UserPasswordlessDTO>> entityModels = userRepresentationModelAssembler.toCollectionModel(allUser);

        // self link to collection
        entityModels.add(linkTo(UserController.class).withSelfRel());

        return new ResponseEntity<>(entityModels, HttpStatus.OK);
    }

    /**
     * Handles PUT request to update the user.
     *
     * @return ResponseEntity with UserPasswordless object in json representation and status report
     */
    @ApiOperation(value = "Update user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PutMapping(value = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = "application/hal+json")
    public final ResponseEntity<?> updateUser(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) throws BindingException, CouldNotUpdateException {
        logger.debug("rest updateUser() - update the user");

        if (bindingResult.hasErrors()) {
            logger.error("Error has occurred during binding.\nReason: {}", bindingResult);
            throw new BindingException("Error has occurred during binding.");
        }

        try {
            UserPasswordlessDTO updatedUser = userFacade.updateUser(userDTO);
            EntityModel<UserPasswordlessDTO> entityModel = userRepresentationModelAssembler.toModel(updatedUser);

            return new ResponseEntity<>(entityModel, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Error has occurred during update.\nReason: {}", ex.getMessage());
            throw new CouldNotUpdateException("Error occurred during update.", ex);
        }
    }

    /**
     * Handles DELETE request to delete the user. This request will also delete all ratings of the user.
     *
     */
    @ApiOperation(value = "Delete user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) throws IllegalArgumentException {
        logger.debug("rest deleteUser() - delete the user with id = {}", id);

        try {
            userFacade.deleteUser(id);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            logger.error("Error has occurred during deleting user with id: {}.\n Reason: {}", id, ex.getMessage());
            throw new IllegalArgumentException(ex);
        }
    }

    /**
     * Handles authentication request for the user.
     *
     * @return ResponseEntity with UserPasswordless object in json representation and status report
     */
    @ApiOperation(value = "Delete user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
            @ApiResponse(code = 401, message = "Invalid credentials!"),
            @ApiResponse(code = 500, message = "Error occurred during binding")
    })
    @PostMapping(value = "/authentication",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = "application/hal+json")
    public ResponseEntity<?> authenticate(@RequestBody @Valid UserAuthenticationDTO userDTO, BindingResult bindingResult) throws AuthenticationException, BindingException, ResourceNotFoundException {
        logger.debug("rest authenticate() - authenticate the user");

        if (bindingResult.hasErrors()) {
            logger.error("Error has occurred during binding.\nReason: {}", bindingResult);
            throw new BindingException("Error has occurred during binding");
        }

        try {
            UserAuthenticationResponseDTO authenticateDTO = userFacade.authenticate(userDTO);
            logger.debug("rest authenticate() - authentication success");
            return new ResponseEntity<>(EntityModel.of(authenticateDTO), HttpStatus.OK);
        } catch (NullArgumentException ex) {
            logger.error("User with nickname: {} was not found.\nReason: {}", userDTO.getNickName(), ex.getMessage());
            throw new ResourceNotFoundException("Invalid credentials!", ex);
        }
    }

    /**
     * Handles POST request for registration of the user.
     *
     * @return ResponseEntity with UserPasswordless object in json representation and status report
     */
    @ApiOperation(value = "User registration")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = ResponseEntity.class),
            @ApiResponse(code = 422, message = "Unprocessable Entity")
    })
    @PostMapping(value = "/registration",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = "application/hal+json")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserCreateDTO userCreateDTO, BindingResult bindingResult) throws ResourceAlreadyExistsException, BindingException {
        logger.debug("rest registerUser() - register the user");

        if (bindingResult.hasErrors()) {
            logger.error("Error has occurred during binding.\nReason: {}", bindingResult);
            throw new BindingException("Error occurred during binding");
        }

        try {
            UserPasswordlessDTO registeredUser = userFacade.registerUser(userCreateDTO, userCreateDTO.getPassword());
            EntityModel<UserPasswordlessDTO> entityModel = userRepresentationModelAssembler.toModel(registeredUser);

            return new ResponseEntity<>(entityModel, HttpStatus.CREATED);
        } catch (PersistenceException ex) {
            logger.error("User with nickname: {} or email: {} already exists!\nReason: {}", userCreateDTO.getNickName(), userCreateDTO.getEmail(), ex.getMessage());
            throw new ResourceAlreadyExistsException(String.format("User with nickname: %s or email: %s already exists!", userCreateDTO.getNickName(), userCreateDTO.getEmail()), ex);
        }
    }
}
