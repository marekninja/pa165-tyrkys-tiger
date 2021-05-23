package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.ImageDetailDTO;
import cz.muni.fi.pa165.facade.ImageFacade;
import cz.muni.fi.pa165.rest.Uris;
import cz.muni.fi.pa165.rest.exceptions.CouldNotCreateException;
import cz.muni.fi.pa165.rest.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.rest.hateoas.ImageDetailRepresentationModelAssembler;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    /**
     * Used for creating ImageDetail Hateoas compliant entity
     * @param id id of image
     * @return
     */
    @ApiOperation(value = "Get image entity")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "Resource not found"),
    })
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

    /**
     * Url to serve images
     * @param id of image
     * @param request
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "Get image data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "Resource not found"),
            @ApiResponse(code = 500, message = "Unspecified internal error"),
    })
    @RequestMapping(value = "/url/{id}",method = RequestMethod.GET)
    public void getImage(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) {
        log.debug("getImage(id={})",id);
        ImageDetailDTO imageDetailDTO = imageFacade.findById(id);
        if (imageDetailDTO == null) {
            throw new ResourceNotFoundException("Image does not exist");
        }
        byte[] image = imageDetailDTO.getImage();
        try {
            if (image == null) {
                response.sendRedirect(request.getContextPath() + "/no-image.png");
            } else {
                response.setContentType(imageDetailDTO.getImageMimeType());
                ServletOutputStream out = response.getOutputStream();
                out.write(image);
                out.flush();
            }
        } catch (IOException e){
            throw new CouldNotCreateException("Error during image loading.");
        }

    }

    // not used
    @RequestMapping(value = "/url/na",method = RequestMethod.GET)
    public void getNoImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath() + "/no-image.png");
    }
}
