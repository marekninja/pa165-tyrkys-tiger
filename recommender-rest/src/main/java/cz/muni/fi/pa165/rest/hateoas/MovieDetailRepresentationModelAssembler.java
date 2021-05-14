package cz.muni.fi.pa165.rest.hateoas;

import cz.muni.fi.pa165.dto.*;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.hateoas.mediatype.hal.HalModelBuilder;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * Assembles HATEOS compliant representation of movie to be listed from MovieListDTO
 *
 * @author Marek Petrovič
 */
@Component
public class MovieDetailRepresentationModelAssembler implements RepresentationModelAssembler<MovieDetailDTO, EntityModel<RepresentationModel<EntityModel<MovieDetailDTO>>>> {

    private EntityLinks entityLinks;
    private ImageDetailRepresentationModelAssembler imageDetailRepresentationModelAssembler;

    private final static Logger log = LoggerFactory.getLogger(MovieDetailRepresentationModelAssembler.class);



    @Autowired
    public MovieDetailRepresentationModelAssembler(@SuppressWarnings("SpringJavaAutowiringInspection")
                                                   EntityLinks entityLinks,
                                                   ImageDetailRepresentationModelAssembler imageDetailRepresentationModelAssembler) {
        this.entityLinks = entityLinks;
        this.imageDetailRepresentationModelAssembler = imageDetailRepresentationModelAssembler;
    }

    @Getter
    private class MovieDetailDummy{
        private Long id;
        private String name;
        private String description;
        private LocalDate yearMade;
        private String countryCode;
        private Integer lengthMin;
        private Set<GenreDTO> genres;
        private Set<PersonDTO> actors;
        private PersonDTO director;
        private UserRatingDTO ratingAgg;

        /**
         * Ultra hack to have images with links...
         *  Images should be added as embedded resources
         * @param movieDetailDTO MovieDetailDTO to make dummy from
         */
        public MovieDetailDummy(MovieDetailDTO movieDetailDTO) {
            this.id = movieDetailDTO.getId();
            this.name = movieDetailDTO.getName();
            this.description = movieDetailDTO.getDescription();
            this.yearMade = movieDetailDTO.getYearMade();
            this.countryCode = movieDetailDTO.getCountryCode();
            this.lengthMin = movieDetailDTO.getLengthMin();
            this.genres = movieDetailDTO.getGenres();
            this.actors = movieDetailDTO.getActors();
            this.director = movieDetailDTO.getDirector();
            this.ratingAgg = movieDetailDTO.getRatingAgg();
        }
    }


    //TODO
    @Override
    public EntityModel<RepresentationModel<EntityModel<MovieDetailDTO>>> toModel(MovieDetailDTO entity) {
        log.debug("toModel of MovieDetail: {}",entity);
        long id = entity.getId();


        MovieDetailDummy movieDetailDummy = new MovieDetailDummy(entity);

        EntityModel<MovieDetailDummy> entityModel = EntityModel.of(movieDetailDummy);
        log.debug("toModel of MovieDetail: enityModel of movieDetailDummy={}",entityModel);

        List<ImageDetailDTO> imageDetailDTOS = new ArrayList<>(entity.getGallery());
        log.debug("toModel of MovieDetail: imageDetailDTOS= {}",imageDetailDTOS);
        ImageDetailDTO imageDetailDTOTitle = entity.getImageTitle();
        log.debug("toModel of MovieDetail: imageDetailDTOTitle= {}",imageDetailDTOTitle);



        EntityModel<ImageDetailDTO> titleImageEntity = imageDetailRepresentationModelAssembler.toModel(imageDetailDTOTitle);


        CollectionModel<EntityModel<ImageDetailDTO>> galleryImageColletion =  imageDetailRepresentationModelAssembler.toCollectionModel(imageDetailDTOS);

        HalModelBuilder halModelBuilder = HalModelBuilder.halModelOf(entityModel);

        halModelBuilder.embed(titleImageEntity, LinkRelation.of("titleImage"));
        halModelBuilder.embed(galleryImageColletion, LinkRelation.of("imageGallery"));

        try {
            Link movieDetailLink = entityLinks.linkForItemResource(MovieDetailDTO.class, id).withSelfRel();
//            entityModel.add(movieDetailLink);
            halModelBuilder.link(movieDetailLink);

            Link browseLink = entityLinks.linkFor(MovieDetailDTO.class).slash("/browse").withRel("browse");
//            entityModel.add(browseLink);
            halModelBuilder.link(browseLink);

//            Method movieGalleryImages = MovieController.class.getMethod("movieGalleryImages",long.class, HttpServletRequest.class, HttpServletResponse.class);
//            Link imageLink = linkTo(movieGalleryImages.getDeclaringClass(),movieGalleryImages,id).withRel("gallery");
//            entityModel.add(imageLink);
        } catch (Exception ex) {
            log.error("cannot link HATEOAS", ex);
        }

//        return  entityModel;
        return EntityModel.of(halModelBuilder.build());
    }
//
//    @Override
//    public CollectionModel<EntityModel<MovieDetailDTO>> toCollectionModel(Iterable<? extends MovieDetailDTO> entities) {
//
//        return null;
//    }
}
