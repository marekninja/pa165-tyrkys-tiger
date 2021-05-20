package cz.muni.fi.pa165.rest.hateoas;

import cz.muni.fi.pa165.dto.UserPasswordlessDTO;
import cz.muni.fi.pa165.rest.Uris;
import cz.muni.fi.pa165.rest.controllers.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * @author Matej Turek
 */
@Component
public class UserRepresentationModelAssembler implements RepresentationModelAssembler<UserPasswordlessDTO, EntityModel<UserPasswordlessDTO>> {

    private final static Logger log = LoggerFactory.getLogger(UserRepresentationModelAssembler.class);

    @Override
    public EntityModel<UserPasswordlessDTO> toModel(UserPasswordlessDTO entity) {
        log.debug("toModel of User: {}", entity);

        EntityModel<UserPasswordlessDTO> entityModel = EntityModel.of(entity);

        Link link = linkTo(methodOn(UserController.class).findUserById(entity.getId())).withSelfRel();

        // self link using method
        entityModel.add(link);

        // collection link
        entityModel.add(linkTo(UserController.class).withRel(Uris.ROOT_URI_USERS));

        // affordance link to update
        //entityModel.add(link.andAffordance(afford(methodOn(UserController.class).updateUser(entity.getId()))));

        return entityModel;
    }

}
