package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.facade.UserRatingFacade;
import cz.muni.fi.pa165.rest.Uris;
import cz.muni.fi.pa165.rest.hateoas.UserRatingRepresentationalModelAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Matej Turek
 */
@RestController
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
}
