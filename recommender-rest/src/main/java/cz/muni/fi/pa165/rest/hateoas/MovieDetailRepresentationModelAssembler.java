package cz.muni.fi.pa165.rest.hateoas;

import cz.muni.fi.pa165.dto.MovieDetailDTO;
import cz.muni.fi.pa165.dto.MovieListDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

/**
 * Assembles HATEOS compliant representation of movie to be listed from MovieListDTO
 *
 * @author Marek Petrovič
 */
@Component
public class MovieDetailRepresentationModelAssembler implements RepresentationModelAssembler<MovieDetailDTO, EntityModel<MovieDetailDTO>> {

    private EntityLinks entityLinks;

    private final static Logger log = LoggerFactory.getLogger(MovieDetailRepresentationModelAssembler.class);


    public MovieDetailRepresentationModelAssembler(@SuppressWarnings("SpringJavaAutowiringInspection")
                                                   @Autowired EntityLinks entityLinks) {
        this.entityLinks = entityLinks;
    }


    @Override
    public EntityModel<MovieDetailDTO> toModel(MovieDetailDTO entity) {
        return null;
    }

    @Override
    public CollectionModel<EntityModel<MovieDetailDTO>> toCollectionModel(Iterable<? extends MovieDetailDTO> entities) {

        return null;
    }
}
