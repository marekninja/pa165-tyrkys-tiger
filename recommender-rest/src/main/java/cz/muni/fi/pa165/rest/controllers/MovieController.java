package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.MovieDetailDTO;
import cz.muni.fi.pa165.dto.MovieListDTO;
import cz.muni.fi.pa165.dto.ParametersDTO;
import cz.muni.fi.pa165.facade.MovieFacade;
import cz.muni.fi.pa165.rest.Uris;
import cz.muni.fi.pa165.rest.hateoas.MovieDetailRepresentationModelAssembler;
import cz.muni.fi.pa165.rest.hateoas.MovieListRepresentationModelAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


/**
 * SpringMVC controller to handle all concerning Movie
 *
 * @author Marek Petrovič
 */
@RestController
//@CrossOrigin(origins = "http://localhost:8080")
@ExposesResourceFor(MovieDetailDTO.class)
@RequestMapping(Uris.ROOT_URI_MOVIES)
public class MovieController {

    final static Logger log = LoggerFactory.getLogger(MovieController.class);

    private MovieFacade movieFacade;

    private MovieDetailRepresentationModelAssembler movieDetailRepresentationModelAssembler;

    private MovieListRepresentationModelAssembler movieListRepresentationModelAssembler;

    private EntityLinks entityLinks;


    @Autowired
    public MovieController(MovieFacade movieFacade,
                           MovieDetailRepresentationModelAssembler movieDetailRepresentationModelAssembler,
                           MovieListRepresentationModelAssembler movieListRepresentationModelAssembler,
                           @SuppressWarnings("SpringJavaAutowiringInspection") EntityLinks entityLinks) {

        this.movieFacade = movieFacade;
        this.movieListRepresentationModelAssembler = movieListRepresentationModelAssembler;
        this.movieDetailRepresentationModelAssembler = movieDetailRepresentationModelAssembler;
        this.entityLinks = entityLinks;
    }


    /**
     * Handles GET request to browse Movies
     *
     * @return list all of Movies (MovieListDTO)
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<CollectionModel<EntityModel<MovieListDTO>>> getAll(){
        log.debug("rest browse() - get all movies");
        ParametersDTO parametersDTO = new ParametersDTO(null,null,null,null, null);
        List<MovieListDTO> allMovies = movieFacade.findMovieByParameters(parametersDTO);
//        CollectionModel<EntityModel<MovieListDTO>> modelCollectionModel = movieListRepresentationModelAssembler.toCollectionModel(allMovies);
        CollectionModel<EntityModel<MovieListDTO>> modelCollectionModel = movieListRepresentationModelAssembler.toCollectionModel(allMovies);
        modelCollectionModel.add(linkTo(MovieController.class).withSelfRel());
        return new ResponseEntity<>(modelCollectionModel, HttpStatus.OK);
    }

    /**
     * Handles Movie browsing/filtering.
     * Can be also used for listing all Movies if all parameters are null.
     *
     * @param parametersDTO ParametersDTO
     * @return list of filtered Movies (MovieListDTO)
     */
//    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(value = "/browse",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<CollectionModel<EntityModel<MovieListDTO>>> browseFilter(@RequestBody ParametersDTO parametersDTO){
        log.debug("browse(parametersDTO={})", parametersDTO);

        List<MovieListDTO> movieListDTOList = movieFacade.findMovieByParameters(parametersDTO);
        CollectionModel<EntityModel<MovieListDTO>> modelCollectionModel = movieListRepresentationModelAssembler.toCollectionModel(movieListDTOList);
        return new ResponseEntity<>(modelCollectionModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/titleImage",method = RequestMethod.GET)
    public void movieTitleImage(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) throws IOException{
        MovieDetailDTO movieDetailDTO = movieFacade.findMovieById(id);
        byte[] image = movieDetailDTO.getImageTitle().getImage();
        if (image == null) {
            response.sendRedirect(request.getContextPath() + "/no-image.png");
        } else {
            response.setContentType(movieDetailDTO.getImageTitle().getImageMimeType());
            ServletOutputStream out = response.getOutputStream();
            out.write(image);
            out.flush();
        }
    }
}
