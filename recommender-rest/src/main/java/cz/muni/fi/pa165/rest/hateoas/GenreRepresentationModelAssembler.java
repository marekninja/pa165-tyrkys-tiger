package cz.muni.fi.pa165.rest.hateoas;

import cz.muni.fi.pa165.dto.GenreDTO;
import cz.muni.fi.pa165.rest.Uris;
import cz.muni.fi.pa165.rest.controllers.GenreController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Peter Mravec
 */
@Component
public class GenreRepresentationModelAssembler implements RepresentationModelAssembler<GenreDTO, EntityModel<GenreDTO>> {

    private final static Logger log = LoggerFactory.getLogger(GenreRepresentationModelAssembler.class);

    @Override
    public EntityModel<GenreDTO> toModel(GenreDTO entity) {
        log.debug("toModel of Genre: {}", entity);

        EntityModel<GenreDTO> entityModel = EntityModel.of(entity);

        Link link = linkTo(methodOn(GenreController.class).findGenreById(entity.getId())).withSelfRel();

        // self link using method
        entityModel.add(link);

        // collection link
        entityModel.add(linkTo(GenreController.class).withRel(Uris.ROOT_URI_GENRES));

        return entityModel;
    }



}
