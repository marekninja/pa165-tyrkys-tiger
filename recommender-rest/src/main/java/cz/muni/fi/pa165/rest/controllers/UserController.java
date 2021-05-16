package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.UserPasswordlessDTO;
import cz.muni.fi.pa165.facade.UserFacade;
import cz.muni.fi.pa165.rest.Uris;
import cz.muni.fi.pa165.rest.hateoas.UserRepresentationModelAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * @author Matej Turek
 */
@RestController
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
     * Handles GET request to browse all Users
     *
     * @return list of UserPasswordlessDTOs
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<UserPasswordlessDTO> getAllUsers() {
        logger.debug("rest browse() - get all users");
        List<UserPasswordlessDTO> allUser = userFacade.findAllUsers();
        CollectionModel<EntityModel<UserPasswordlessDTO>> entityModels = userRepresentationModelAssembler.toCollectionModel(allUser);
        entityModels.add(linkTo(UserController.class).withSelfRel());
        return new ResponseEntity<>(entityModels, HttpStatus.OK);
    }


}
