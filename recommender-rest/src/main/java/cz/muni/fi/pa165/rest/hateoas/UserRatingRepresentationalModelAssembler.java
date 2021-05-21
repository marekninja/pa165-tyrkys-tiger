package cz.muni.fi.pa165.rest.hateoas;

import cz.muni.fi.pa165.dto.UserRatingDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

/**
 * @author Matej Turek
 */
@Component
public class UserRatingRepresentationalModelAssembler implements RepresentationModelAssembler<UserRatingDTO, EntityModel<UserRatingDTO>> {

    private final static Logger log = LoggerFactory.getLogger(UserRatingRepresentationalModelAssembler.class);

    @Override
    public EntityModel<UserRatingDTO> toModel(UserRatingDTO entity) {
        log.debug("toModel of UserRating: {}", entity);

        return null;
    }
}
