package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.ImageDetailDTO;
import cz.muni.fi.pa165.dto.MovieDetailDTO;
import cz.muni.fi.pa165.facade.ImageFacade;
import cz.muni.fi.pa165.facade.MovieFacade;
import cz.muni.fi.pa165.rest.Uris;
import cz.muni.fi.pa165.rest.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.rest.hateoas.ImageDetailRepresentationModelAssembler;
import cz.muni.fi.pa165.rest.hateoas.MovieDetailRepresentationModelAssembler;
import cz.muni.fi.pa165.rest.hateoas.MovieListRepresentationModelAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Marek Petrovič
 */
@RestController
@ExposesResourceFor(ImageDetailDTO.class)
@RequestMapping(Uris.ROOT_URI_IMAGES)
public class ImageController {
    final static Logger log = LoggerFactory.getLogger(MovieController.class);

    private ImageFacade imageFacade;

    private ImageDetailRepresentationModelAssembler imageDetailRepresentationModelAssembler;

    private EntityLinks entityLinks;

    @Autowired
    public ImageController(ImageFacade imageFacade, ImageDetailRepresentationModelAssembler imageDetailRepresentationModelAssembler,@SuppressWarnings("SpringJavaAutowiringInspection")  EntityLinks entityLinks) {
        this.imageFacade = imageFacade;
        this.imageDetailRepresentationModelAssembler = imageDetailRepresentationModelAssembler;
        this.entityLinks = entityLinks;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<EntityModel<ImageDetailDTO>> getWrappedImage(@PathVariable long id){
        log.debug("getWrappedImage(id={})",id);

        ImageDetailDTO imageDetailDTO = imageFacade.findById(id);
        if (imageDetailDTO == null) {
            throw new ResourceNotFoundException("Image with id="+id+" not found");
        }
        EntityModel<ImageDetailDTO> imageDetailDTOEntityModel = imageDetailRepresentationModelAssembler.toModel(imageDetailDTO);
        return new ResponseEntity<>(imageDetailDTOEntityModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/url/{id}",method = RequestMethod.GET)
    public void getImage(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.debug("getImage(id={})",id);
        ImageDetailDTO imageDetailDTO = imageFacade.findById(id);
        if (imageDetailDTO == null) {
            response.sendRedirect(request.getContextPath() + "/no-image.png");
        }
        assert imageDetailDTO != null;
        byte[] image = imageDetailDTO.getImage();
        if (image == null) {
            response.sendRedirect(request.getContextPath() + "/no-image.png");
        } else {
            response.setContentType(imageDetailDTO.getImageMimeType());
            ServletOutputStream out = response.getOutputStream();
            out.write(image);
            out.flush();
        }
    }
}
