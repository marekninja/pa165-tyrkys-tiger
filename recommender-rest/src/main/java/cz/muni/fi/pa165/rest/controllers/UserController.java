package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.dto.UserPasswordlessDTO;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.rest.Uris;
import cz.muni.fi.pa165.rest.exceptions.AuthenticationException;
import cz.muni.fi.pa165.rest.exceptions.BindingException;
import cz.muni.fi.pa165.rest.exceptions.CouldNotUpdateException;
import cz.muni.fi.pa165.rest.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.rest.hateoas.UserRepresentationModelAssembler;
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
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<EntityModel<UserPasswordlessDTO>> findUserById(@PathVariable Long id) {
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
    public ResponseEntity<EntityModel<UserPasswordlessDTO>> findUserByEmail(@PathVariable String email) {
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
    public ResponseEntity<EntityModel<UserPasswordlessDTO>> findUserByNickName(@PathVariable String nickName) {
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
    public final ResponseEntity<CollectionModel<EntityModel<UserPasswordlessDTO>>> getAllUsers() {
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
    @PutMapping(value = "/update")
    public final ResponseEntity<EntityModel<UserPasswordlessDTO>> updateUser(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {
        logger.debug("rest updateUser() - update the user");

        if (bindingResult.hasErrors()) {
            logger.error("Error has occurred during binding.\nReason: {}", bindingResult);
            throw new BindingException("Error occurred during binding.");
        }

        try {
            UserPasswordlessDTO updatedUser = userFacade.updateUser(userDTO);
            EntityModel<UserPasswordlessDTO> entityModel = userRepresentationModelAssembler.toModel(updatedUser);

            return new ResponseEntity<>(entityModel, HttpStatus.OK);
        }
        catch (Exception ex) {
            logger.error("Error has occurred during update.");
            throw new CouldNotUpdateException("Error occurred during update.", ex);
        }
    }

    /**
     * Handles DELETE request to delete the user.
     *
     * @return ResponseEntity with UserPasswordless object in json representation and status report
     */
    @ApiOperation(value = "Delete user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        logger.debug("rest deleteUser() - delete the user with id = {}", id);

        userFacade.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Handles DELETE request to delete the user.
     *
     * @return HttpEntity with UserPasswordless object in json representation and status report
     */
    /*@ApiOperation(value = "Delete user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
    })
    @DeleteMapping(value = "/{id}")
    public boolean isAdministrator(UserDTO userDTO) {

    }*/


    /**
     * Handles authentication request for the user.
     *
     * @return ResponseEntity with UserPasswordless object in json representation and status report
     */
    /*@ApiOperation(value = "Delete user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
            @ApiResponse(code = 401, message = "Invalid credentials!"),
            @ApiResponse(code = 500, message = "Error occurred during binding")
    })
    @GetMapping(value = "/authentication")
    public ResponseEntity<> authenticate(@RequestBody @Valid UserAuthenticateDTO userDTO, BindingResult bindingResult) {
        logger.debug("rest authenticate() - authenticate the user");

        if (bindingResult.hasErrors()) {
            logger.error("Error has occurred during binding.\nReason: {}", bindingResult);
            throw new BindingException("Error occurred during binding");
        }

        boolean result = userFacade.authenticate(userDTO);

        if (!result) {
            throw new AuthenticationException("Invalid credentials!");
        }

        // GENERATE TOKEN
        // STORE TO SESSION + CONNECT WITH SESSION

        return new ResponseEntity<>(, HttpStatus.OK);
    }*/

    /**
     * Handles registration request for the user.
     *
     * @return ResponseEntity with UserPasswordless object in json representation and status report
     */
    @ApiOperation(value = "User registration")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class)
    })
    @PostMapping(value = "/registration")
    public ResponseEntity<EntityModel<UserPasswordlessDTO>> registerUser(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {
        logger.debug("rest authenticate() - authenticate the user");

        if (bindingResult.hasErrors()) {
            logger.error("Error has occurred during binding.\nReason: {}", bindingResult);
            throw new BindingException("Error occurred during binding");
        }

        UserPasswordlessDTO registeredUser = userFacade.registerUser(userDTO, userDTO.getPassword());
        EntityModel<UserPasswordlessDTO> entityModel = userRepresentationModelAssembler.toModel(registeredUser);

        return new ResponseEntity<>(entityModel, HttpStatus.CREATED);
    }
}
