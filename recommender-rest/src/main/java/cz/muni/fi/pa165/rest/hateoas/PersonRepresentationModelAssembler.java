package cz.muni.fi.pa165.rest.hateoas;

import cz.muni.fi.pa165.dto.PersonDTO;
import cz.muni.fi.pa165.rest.Uris;
import cz.muni.fi.pa165.rest.controllers.PersonController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Peter Mravec
 */
@Component
public class PersonRepresentationModelAssembler implements RepresentationModelAssembler<PersonDTO, EntityModel<PersonDTO>> {

    private final static Logger log = LoggerFactory.getLogger(PersonRepresentationModelAssembler.class);

    @Override
    public EntityModel<PersonDTO> toModel(PersonDTO entity) {
        log.debug("toModel of Person: {}", entity);

        EntityModel<PersonDTO> entityModel = EntityModel.of(entity);

        Link link = linkTo(methodOn(PersonController.class).findPersonById(entity.getId())).withSelfRel();

        // self link using method
        entityModel.add(link);

        // collection link
        entityModel.add(linkTo(PersonController.class).withRel(Uris.ROOT_URI_PERSONS));

        return entityModel;
    }
}
