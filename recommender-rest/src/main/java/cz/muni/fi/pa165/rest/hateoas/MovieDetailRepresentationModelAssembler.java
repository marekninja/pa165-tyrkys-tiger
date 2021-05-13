package cz.muni.fi.pa165.rest.hateoas;

import cz.muni.fi.pa165.dto.MovieDetailDTO;
import cz.muni.fi.pa165.dto.MovieListDTO;
import cz.muni.fi.pa165.rest.controllers.MovieController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

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


    //TODO
    @Override
    public EntityModel<MovieDetailDTO> toModel(MovieDetailDTO entity) {
        log.debug("toModel of MovieDetail: {}",entity);
        long id = entity.getId();


        EntityModel<MovieDetailDTO> entityModel = EntityModel.of(entity);
        try {
            Link movieDetailLink = entityLinks.linkForItemResource(MovieDetailDTO.class, id).withSelfRel();
            entityModel.add(movieDetailLink);

            Link browseLink = entityLinks.linkFor(MovieDetailDTO.class).slash("/browse").withRel("browse");
            entityModel.add(browseLink);

//            Method movieGalleryImages = MovieController.class.getMethod("movieGalleryImages",long.class, HttpServletRequest.class, HttpServletResponse.class);
//            Link imageLink = linkTo(movieGalleryImages.getDeclaringClass(),movieGalleryImages,id).withRel("gallery");
//            entityModel.add(imageLink);
        } catch (Exception ex) {
            log.error("cannot link HATEOAS", ex);
        }

        return  entityModel;
    }

    @Override
    public CollectionModel<EntityModel<MovieDetailDTO>> toCollectionModel(Iterable<? extends MovieDetailDTO> entities) {

        return null;
    }
}
