package cz.muni.fi.pa165.rest.hateoas;

import cz.muni.fi.pa165.dto.UserRatingViewDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

/**
 * @author Matej Turek
 */
@Component
public class UserRatingRepresentationalModelAssembler implements RepresentationModelAssembler<UserRatingViewDTO, EntityModel<UserRatingViewDTO>> {

    private final static Logger log = LoggerFactory.getLogger(UserRatingRepresentationalModelAssembler.class);

    @Override
    public EntityModel<UserRatingViewDTO> toModel(UserRatingViewDTO entity) {
        log.debug("toModel of UserRating: {}", entity);

        EntityModel<UserRatingViewDTO> entityModel = EntityModel.of(entity);

        // Maybe add links to user and movie -> requires new DTO representation

        return entityModel;
    }
}
