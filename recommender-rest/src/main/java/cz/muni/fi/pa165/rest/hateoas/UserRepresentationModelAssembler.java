package cz.muni.fi.pa165.rest.hateoas;

import cz.muni.fi.pa165.dto.UserPasswordlessDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

/**
 * @author Matej Turek
 */
@Component
public class UserRepresentationModelAssembler implements RepresentationModelAssembler<UserPasswordlessDTO, EntityModel<UserPasswordlessDTO>> {

    private final static Logger log = LoggerFactory.getLogger(UserRepresentationModelAssembler.class);

    @Override
    public EntityModel<UserPasswordlessDTO> toModel(UserPasswordlessDTO entity) {
        return null;
    }

    @Override
    public CollectionModel<EntityModel<UserPasswordlessDTO>> toCollectionModel(Iterable<? extends UserPasswordlessDTO> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
