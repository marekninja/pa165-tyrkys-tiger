package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.rest.Uris;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * @author Matej Turek
 */
@RestController
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping(value = "/", produces = "application/hal+json")
    public ResponseEntity<EntityModel<Map<String, String>>> getResources() {

        logger.debug("rest getResources() - get available URIs.");

        Map<String,String> resourcesMap = new HashMap<>();

        resourcesMap.put("users_uri", Uris.ROOT_URI_USERS);
        resourcesMap.put("movies_uri", Uris.ROOT_URI_MOVIES);
        resourcesMap.put("ratings_uri", Uris.ROOT_URI_RATINGS);
        resourcesMap.put("persons_uri", Uris.ROOT_URI_PERSONS);
        resourcesMap.put("genres_uri", Uris.ROOT_URI_GENRES);
        resourcesMap.put("images_uri", Uris.ROOT_URI_IMAGES);

        EntityModel<Map<String, String>> entityModel = EntityModel.of(resourcesMap);

        entityModel.add(linkTo(MainController.class).withSelfRel());
        entityModel.add(linkTo(MainController.class).slash(Uris.ROOT_URI_USERS).withRel("users_uri"));
        entityModel.add(linkTo(MainController.class).slash(Uris.ROOT_URI_MOVIES).withRel("movies_uri"));
        entityModel.add(linkTo(MainController.class).slash(Uris.ROOT_URI_RATINGS).withRel("ratings_uri"));
        entityModel.add(linkTo(MainController.class).slash(Uris.ROOT_URI_PERSONS).withRel("persons_uri"));
        entityModel.add(linkTo(MainController.class).slash(Uris.ROOT_URI_GENRES).withRel("genres_uri"));
        entityModel.add(linkTo(MainController.class).slash(Uris.ROOT_URI_IMAGES).withRel("images_uri"));


        return new ResponseEntity<>(entityModel, HttpStatus.OK);
    }
}
