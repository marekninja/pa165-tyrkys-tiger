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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

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

    public EntityModel<ImageDetailDTO> getEmpty() throws IOException {
        log.debug("getEmpty");

        ImageDetailDTO imageDetailDTO = new ImageDetailDTO();
        imageDetailDTO.setImage("no-image".getBytes(StandardCharsets.UTF_8));
        imageDetailDTO.setImageMimeType("image/png");

        EntityModel<ImageDetailDTO> entityModel = EntityModel.of(imageDetailDTO);
        try {

            Method getImage = ImageController.class.getMethod("getNoImage", HttpServletRequest.class, HttpServletResponse.class);
            Link imageLink = linkTo(getImage.getDeclaringClass(),getImage).withSelfRel();
            entityModel.add(imageLink);

        } catch (Exception ex) {
            log.error("cannot link HATEOAS", ex);
        }
        return  entityModel;
    }

    private byte[] readImage(String filename) throws IOException {
        try (InputStream is = this.getClass().getResourceAsStream("/" + filename)) {
            int nRead;
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] data = new byte[1024];
            while ((nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            return buffer.toByteArray();
        }
    }
}
