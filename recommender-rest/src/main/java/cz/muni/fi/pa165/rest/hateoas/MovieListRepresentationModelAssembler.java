package cz.muni.fi.pa165.rest.hateoas;

import cz.muni.fi.pa165.dto.MovieDetailDTO;
import cz.muni.fi.pa165.dto.MovieListDTO;
import cz.muni.fi.pa165.rest.controllers.MovieController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * @author Marek Petrovič
 */
@Component
public class MovieListRepresentationModelAssembler implements RepresentationModelAssembler<MovieListDTO, EntityModel<MovieListDTO>> {

    private EntityLinks entityLinks;

    private final static Logger log = LoggerFactory.getLogger(MovieDetailRepresentationModelAssembler.class);


    public MovieListRepresentationModelAssembler(@SuppressWarnings("SpringJavaAutowiringInspection")
                                                 @Autowired EntityLinks entityLinks) {
        this.entityLinks = entityLinks;
    }



    @Override
    public EntityModel<MovieListDTO> toModel(MovieListDTO entity) {
        log.debug("toModel of MovieList: {}",entity);
        long id = entity.getId();


        EntityModel<MovieListDTO> entityModel = EntityModel.of(entity);
        try {
            Link movieDetailLink = entityLinks.linkForItemResource(MovieDetailDTO.class, id).withSelfRel();
            entityModel.add(movieDetailLink);

            Link browseLink = entityLinks.linkFor(MovieDetailDTO.class).slash("/browse").withRel("browse");
            entityModel.add(browseLink);

            Method movieTitleImage = MovieController.class.getMethod("movieTitleImage",long.class, HttpServletRequest.class, HttpServletResponse.class);
            Link imageLink = linkTo(movieTitleImage.getDeclaringClass(),movieTitleImage,id).withRel("titleImage");
            entityModel.add(imageLink);
        } catch (Exception ex) {
            log.error("cannot link HATEOAS", ex);
        }

        return entityModel;
    }

//    @Override
//    public CollectionModel<EntityModel<MovieListDTO>> toCollectionModel(Iterable<? extends MovieListDTO> entities) {
//
//
//        log.debug("toCollectionModel of Iterabe: {}",entities);
//        List<EntityModel<MovieListDTO>> entityModels = new ArrayList<>();
//        for (MovieListDTO movieListDTO: entities) {
//            entityModels.add(this.toModel(movieListDTO));
//        }
//
//        CollectionModel<EntityModel<MovieListDTO>> collectionModel = CollectionModel.of(entityModels);
//        collectionModel.add(entityLinks.linkFor(MovieDetailDTO.class).slash("browse").withSelfRel());
//        return collectionModel;
//    }
}
