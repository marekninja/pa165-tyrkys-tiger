package cz.muni.fi.pa165.rest.hateoas;

import cz.muni.fi.pa165.dto.UserRatingDTO;
import cz.muni.fi.pa165.dto.UserRatingViewDTO;
import cz.muni.fi.pa165.rest.controllers.MovieController;
import cz.muni.fi.pa165.rest.controllers.UserController;
import cz.muni.fi.pa165.rest.controllers.UserRatingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Matej Turek
 */
@Component
public class UserRatingRepresentationalModelAssembler implements RepresentationModelAssembler<UserRatingDTO, EntityModel<UserRatingDTO>> {

    private final static Logger log = LoggerFactory.getLogger(UserRatingRepresentationalModelAssembler.class);

    @Override
    public EntityModel<UserRatingDTO> toModel(UserRatingDTO entity) {
        log.debug("toModel of UserRating: {}", entity);

        EntityModel<UserRatingDTO> entityModel = EntityModel.of(entity);

        Link linkSelf = linkTo(methodOn(UserRatingController.class).findUserRatingById(entity.getId())).withSelfRel();
        Link linkUser = linkTo(methodOn(UserController.class).findUserById(entity.getUserId())).withRel("user");

        // this line throws NPE on:
        // [INFO] [talledLocalContainer] SEVERE: Servlet.service() for servlet [dispatcher] in context with path [/pa165] threw exception [Request processing failed; nested exception is java.lang.NullPointerException: Cannot invoke "cz.muni.fi.pa165.facade.MovieFacade.findMovieById(java.lang.Long)" because "this.movieFacade" is null] with root cause...
        // java.lang.NullPointerException: Cannot invoke "cz.muni.fi.pa165.facade.MovieFacade.findMovieById(java.lang.Long)" because "this.movieFacade" is null
        // [INFO] [talledLocalContainer]   at cz.muni.fi.pa165.rest.controllers.MovieController.getMovieDetail(MovieController.java:139)
        // Link linkMovie = linkTo(methodOn(MovieController.class).getMovieDetail(entity.getMovieId())).withRel("movie");

        Link linkMovie = linkTo(MovieController.class).slash(entity.getMovieId()).withRel("movie");


        entityModel.add(linkSelf);
        entityModel.add(linkUser);
        entityModel.add(linkMovie);

        return entityModel;
    }
}
