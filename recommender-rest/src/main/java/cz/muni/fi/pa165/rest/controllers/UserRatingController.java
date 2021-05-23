package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.facade.UserRatingFacade;
import cz.muni.fi.pa165.rest.Uris;
import cz.muni.fi.pa165.rest.exceptions.*;
import cz.muni.fi.pa165.rest.hateoas.UserRatingRepresentationalModelAssembler;
import cz.muni.fi.pa165.service.exceptions.NullArgumentException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.dozer.MappingException;
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

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * @author Matej Turek
 */
@RestController
@ExposesResourceFor(UserRatingDTO.class)
@RequestMapping(Uris.ROOT_URI_RATINGS)
public class UserRatingController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserRatingFacade userRatingFacade;
    private final UserRatingRepresentationalModelAssembler userRatingRepresentationalModelAssembler;


    @Autowired
    public UserRatingController(UserRatingFacade userRatingFacade, UserRatingRepresentationalModelAssembler userRatingRepresentationalModelAssembler) {
        this.userRatingFacade = userRatingFacade;
        this.userRatingRepresentationalModelAssembler = userRatingRepresentationalModelAssembler;
    }

    /**
     * Handles GET request to find user rating by specific id.
     *
     * @return ResponseEntity with UserRatingDTO object in json representation and status report
     */
    @ApiOperation(value = "Find user rating with given id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @GetMapping(value = "/{id}",
            produces = "application/hal+json")
    public ResponseEntity<EntityModel<UserRatingDTO>> findUserRatingById(@PathVariable Long id) throws ResourceNotFoundException {
        logger.debug("rest findUserRatingById() - get user rating by id.");

        try {
            UserRatingDTO userRating = userRatingFacade.findUserRatingById(id);

            if (userRating == null) {
                logger.error("User rating with id = {} not found.", id);
                throw new ResourceNotFoundException("User rating with id = {" + id + "} not found.");
            }

            EntityModel<UserRatingDTO> entityModel = userRatingRepresentationalModelAssembler.toModel(userRating);

            return new ResponseEntity<>(entityModel, HttpStatus.OK);
        } catch (MappingException ex) {
            logger.error("User rating with id = {} not found.", id);
            throw new ResourceNotFoundException("User rating with id = {" + id + "} not found.", ex);
        }
    }

    /**
     * Handles POST request to create new genre
     *
     * @return ResponseEntity with UserRatingDTO object in json representation and status report
     */
    @ApiOperation(value = "User registration")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = ResponseEntity.class),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping(value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = "application/hal+json")
    public ResponseEntity<EntityModel<UserRatingDTO>> createUserRating(@RequestBody @Valid UserRatingCreateDTO userRatingCreateDTO, BindingResult bindingResult) throws CouldNotCreateException {
        logger.debug("rest createUserRating() - authenticate the user");

        if (bindingResult.hasErrors()) {
            logger.error("Error has occurred during binding.\nReason: {}", bindingResult);
            throw new BindingException("Error occurred during binding");
        }

        try {
            UserRatingDTO newUserRating = userRatingFacade.createUserRating(userRatingCreateDTO);
            EntityModel<UserRatingDTO> entityModel = userRatingRepresentationalModelAssembler.toModel(newUserRating);

            return new ResponseEntity<>(entityModel, HttpStatus.CREATED);
        } catch (NullArgumentException ex) {
            logger.error("Error has occurred during creating.\nReason: {}", ex.getMessage());
            throw new CouldNotCreateException("Error has occurred during creating.", ex);
        }
    }

    /**
     * Handles PUT request to update the user rating.
     *
     * @return ResponseEntity with UserRatingDTO object in json representation and status report
     */
    @ApiOperation(value = "Update user rating")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PutMapping(value = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = "application/hal+json")
    public final ResponseEntity<EntityModel<UserRatingDTO>> updateUserRating(@RequestBody @Valid UserRatingDTO userRatingDTO, BindingResult bindingResult) {
        logger.debug("rest updateUserRating() - update the user rating");

        if (bindingResult.hasErrors()) {
            logger.error("Error has occurred during binding.\nReason: {}", bindingResult);
            throw new BindingException("Error occurred during binding.");
        }

        try {
            UserRatingDTO updatedUserRating = userRatingFacade.updateUserRating(userRatingDTO);
            EntityModel<UserRatingDTO> entityModel = userRatingRepresentationalModelAssembler.toModel(updatedUserRating);

            return new ResponseEntity<>(entityModel, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Error has occurred during update.\nReason: {}", ex.getMessage());
            throw new CouldNotUpdateException("Error occurred during update.", ex);
        }
    }

    /**
     * Handles DELETE request to delete user rating specified with id.
     */
    @ApiOperation(value = "Delete user rating")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteUserRating(@PathVariable Long id) {
        logger.debug("rest deleteUserRating() - delete the user rating with id = {}", id);

        try {
            userRatingFacade.deleteUserRating(id);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NullArgumentException ex) {
            logger.error("User rating with id: {} is not in db.\nReason: {}", id, ex.getMessage());
            throw new ResourceNotFoundException(String.format("User rating with id: {%d} is not in db.", id), ex);
        } catch (IllegalArgumentException ex) {
            logger.error("Error has occurred during deleting user rating with id: {}.\nReason: {}", id, ex.getMessage());
            throw new IllegalArgumentException(String.format("Error has occurred during deleting user rating with id: {%d}", id), ex);
        }
    }
}
