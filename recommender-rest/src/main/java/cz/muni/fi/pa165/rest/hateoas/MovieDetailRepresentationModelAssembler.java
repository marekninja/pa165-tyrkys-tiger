package cz.muni.fi.pa165.rest.hateoas;

import cz.muni.fi.pa165.dto.*;
import lombok.Getter;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.hateoas.mediatype.hal.HalModelBuilder;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * Assembles HATEOS compliant representation of movie to be listed from MovieListDTO
 *
 * @author Marek Petrovič
 */
//TODO neeed test on all routes - some have been test (is mentioned)
// needs fillup with user rating
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


    //TODO user rating fill-up when logged in
    @SneakyThrows
    @Override
    public EntityModel<RepresentationModel<EntityModel<MovieDetailDTO>>> toModel(MovieDetailDTO entity) {
        log.debug("toModel of MovieDetail: {}",entity);
        long id = entity.getId();

        EntityModel<MovieDetailDTO> entityModel = EntityModel.of(entity);
        log.debug("toModel of MovieDetail: enityModel of movieDetailDummy={}",entityModel);

        List<ImageDetailDTO> imageDetailDTOS = new ArrayList<>(entity.getGallery());
        log.debug("toModel of MovieDetail: imageDetailDTOS= {}",imageDetailDTOS);

        ImageDetailDTO imageDetailDTOTitle = entity.getImageTitle();
        log.debug("toModel of MovieDetail: imageDetailDTOTitle= {}",imageDetailDTOTitle);



        HalModelBuilder halModelBuilder = HalModelBuilder.halModelOf(entityModel);


        EntityModel<ImageDetailDTO> titleImageEntity = imageDetailRepresentationModelAssembler.toModel(imageDetailDTOTitle);
        halModelBuilder.embed(titleImageEntity, LinkRelation.of("titleImage"));

        CollectionModel<EntityModel<ImageDetailDTO>> galleryImageColletion = imageDetailRepresentationModelAssembler.toCollectionModel(imageDetailDTOS);
//        if (!imageDetailDTOS.isEmpty()){
//             galleryImageColletion =

//        } else {
//            ImageDetailDTO noImage = imageDetailRepresentationModelAssembler.getEmpty().getContent();
//            List<ImageDetailDTO> imageDetailDTOS1 = new ArrayList<>(Collections.singletonList(noImage));
//            galleryImageColletion =  imageDetailRepresentationModelAssembler.toCollectionModel(imageDetailDTOS1);
//        }
        halModelBuilder.embed(galleryImageColletion, LinkRelation.of("imageGallery"));


        try {
            Link movieDetailLink = entityLinks.linkForItemResource(MovieDetailDTO.class, id).withSelfRel();
//            entityModel.add(movieDetailLink);
            halModelBuilder.link(movieDetailLink);

            Link browseLink = entityLinks.linkFor(MovieDetailDTO.class).withRel("browse");
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
