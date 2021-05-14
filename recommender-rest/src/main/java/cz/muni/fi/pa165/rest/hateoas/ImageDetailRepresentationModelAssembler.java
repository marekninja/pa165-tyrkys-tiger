package cz.muni.fi.pa165.rest.hateoas;

import cz.muni.fi.pa165.dto.ImageDetailDTO;
import cz.muni.fi.pa165.dto.MovieDetailDTO;
import cz.muni.fi.pa165.rest.controllers.ImageController;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * @author Marek Petrovič
 */
@Component
public class ImageDetailRepresentationModelAssembler implements RepresentationModelAssembler<ImageDetailDTO, EntityModel<ImageDetailDTO>> {

    private EntityLinks entityLinks;

    private final static Logger log = LoggerFactory.getLogger(MovieDetailRepresentationModelAssembler.class);


    public ImageDetailRepresentationModelAssembler(@SuppressWarnings("SpringJavaAutowiringInspection")
                                                   @Autowired EntityLinks entityLinks) {
        this.entityLinks = entityLinks;
    }

    @Override
    public EntityModel<ImageDetailDTO> toModel(ImageDetailDTO entity) {
        log.debug("toModel of ImageDetail: {}",entity);
        long id = entity.getId();

        EntityModel<ImageDetailDTO> entityModel = EntityModel.of(entity);
        try {

            Method getImage = ImageController.class.getMethod("getImage", long.class, HttpServletRequest.class, HttpServletResponse.class);
            Link imageLink = linkTo(getImage.getDeclaringClass(),getImage,id).withSelfRel();
            entityModel.add(imageLink);

        } catch (Exception ex) {
            log.error("cannot link HATEOAS", ex);
        }
        return  entityModel;
    }
}
