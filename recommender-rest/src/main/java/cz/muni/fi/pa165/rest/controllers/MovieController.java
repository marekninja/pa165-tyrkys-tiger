package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.facade.MovieFacade;
import cz.muni.fi.pa165.rest.Uris;
import cz.muni.fi.pa165.rest.exceptions.CouldNotCreateException;
import cz.muni.fi.pa165.rest.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.rest.hateoas.MovieDetailRepresentationModelAssembler;
import cz.muni.fi.pa165.rest.hateoas.MovieListRepresentationModelAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


/**
 * SpringMVC controller to handle all concerning Movie
 * root: /movies
 * children:
 *  / - get all
 *  /browse - filter by posting JSON
 *  /{id} - get Movie Detail
 *
 *
 * @author Marek Petrovič
 */
@RestController
//@CrossOrigin(origins = "http://localhost:8080")
@ExposesResourceFor(MovieDetailDTO.class)
@RequestMapping(value = Uris.ROOT_URI_MOVIES)
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


    //    MovieDetailDTO findMovieById(Long movieId); OK - needs userRating fillup when logged in
    //    List<MovieListDTO> findMovieByParameters(ParametersDTO parametersDTO); - SEEMS OK

    /**
     * Handles GET request to browse Movies
     *
     * @return list all of Movies (MovieListDTO)
     */
    @RequestMapping(method = RequestMethod.GET,produces = "application/hal+json")
    public final HttpEntity<CollectionModel<EntityModel<RepresentationModel<EntityModel<MovieListDTO>>>>> getAll(){
        log.debug("rest browse() - get all movies");
        ParametersDTO parametersDTO = new ParametersDTO(null,null,null,null, null);
        List<MovieListDTO> allMovies = movieFacade.findMovieByParameters(parametersDTO);
        CollectionModel<EntityModel<RepresentationModel<EntityModel<MovieListDTO>>>> modelCollectionModel = movieListRepresentationModelAssembler.toCollectionModel(allMovies);
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
    @RequestMapping(value = "/browse",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = "application/hal+json")
    public final HttpEntity<CollectionModel<EntityModel<RepresentationModel<EntityModel<MovieListDTO>>>>> browseFilter(@RequestBody @Valid ParametersDTO parametersDTO, BindingResult bindingResult) throws Exception {
        log.debug("browse(parametersDTO={})", parametersDTO);
        if (bindingResult.hasErrors()){
            log.error("failed validation {}", bindingResult.toString());
            throw new Exception("Failed validation");
        }

        List<MovieListDTO> movieListDTOList = movieFacade.findMovieByParameters(parametersDTO);
        CollectionModel<EntityModel<RepresentationModel<EntityModel<MovieListDTO>>>> modelCollectionModel = movieListRepresentationModelAssembler.toCollectionModel(movieListDTOList);
        return new ResponseEntity<>(modelCollectionModel, HttpStatus.OK);
    }

    /**
     * Returns Detail of Movie
     * TODO needs fillup with userRating - of logged
     * @param id Movie ID
     * @return application/hal+json
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = "application/hal+json")
    public final HttpEntity<EntityModel<RepresentationModel<EntityModel<MovieDetailDTO>>>> getMovieDetail(@PathVariable long id){
        log.debug("getMovieDetail(id={})", id);

        MovieDetailDTO movieDetailDTO = movieFacade.findMovieById(id);
        if (movieDetailDTO == null) {
            throw new ResourceNotFoundException("Movie with id="+id+" not found");
        }
        EntityModel<RepresentationModel<EntityModel<MovieDetailDTO>>> movieDetailDTOEntityModel = movieDetailRepresentationModelAssembler.toModel(movieDetailDTO);
        return new ResponseEntity<>(movieDetailDTOEntityModel, HttpStatus.OK);
    }


    /**
     * Not used ?
     * @param id
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/{id}/titleImage",method = RequestMethod.GET)
    public void movieTitleImage(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) throws IOException{
        log.debug("movieTitleImage(id={})",id);
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

//    WHEN AUTHORIZED
//    List<MovieListDTO> getRecommendedMovies(UserDTO userDTO);
    @RequestMapping(value = "/recommended",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = "application/hal+json")
    public final HttpEntity<CollectionModel<EntityModel<RepresentationModel<EntityModel<MovieListDTO>>>>> getRecommendedMovies(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) throws Exception {
        log.debug("getRecommendedMovies(UserDTO={})", userDTO);
        if (bindingResult.hasErrors()){
            log.error("failed validation {}", bindingResult.toString());
            throw new Exception("Failed validation");
        }

        List<MovieListDTO> movieListDTOList = movieFacade.getRecommendedMovies(userDTO);
        CollectionModel<EntityModel<RepresentationModel<EntityModel<MovieListDTO>>>> modelCollectionModel = movieListRepresentationModelAssembler.toCollectionModel(movieListDTOList);
        return new ResponseEntity<>(modelCollectionModel, HttpStatus.OK);
    }



//    ONLY FOR ADMINS
//    Long createMovie(MovieCreateDTO movieCreateDTO);
    @RequestMapping(value = "/create",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = "application/hal+json")
    public final HttpEntity<EntityModel<RepresentationModel<EntityModel<MovieDetailDTO>>>> createMovie(@RequestBody @Valid MovieCreateDTO movieCreateDTO, BindingResult bindingResult) throws Exception {
        log.debug("createMovie(MovieCreateDTO={})", movieCreateDTO);
        if (bindingResult.hasErrors()){
            log.error("failed validation {}", bindingResult.toString());
            throw new Exception("Failed validation");
        }

        Long id = movieFacade.createMovie(movieCreateDTO);
        if (id == null){
            throw new CouldNotCreateException("Could not create movie 😢");
        }
        MovieDetailDTO movieDetailDTO = movieFacade.findMovieById(id);
        if (movieDetailDTO == null) {
            throw new ResourceNotFoundException("Movie with id="+id+" not found");
        }
        EntityModel<RepresentationModel<EntityModel<MovieDetailDTO>>> movieDetailDTOEntityModel = movieDetailRepresentationModelAssembler.toModel(movieDetailDTO);
        return new ResponseEntity<>(movieDetailDTOEntityModel, HttpStatus.OK);
    }

//    Long updateMovieAttrs(MovieDetailDTO movieDetailDTO);

    @RequestMapping(value = "/update",method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE,produces = "application/hal+json")
    public final HttpEntity<EntityModel<RepresentationModel<EntityModel<MovieDetailDTO>>>> updateMovie(@RequestBody @Valid MovieDetailDTO movieDetailDTO, BindingResult bindingResult) throws Exception {
        log.debug("updateMovie(MovieDetailDTO={})", movieDetailDTO);
        if (bindingResult.hasErrors()){
            log.error("failed validation {}", bindingResult.toString());
            throw new Exception("Failed validation");
        }

        Long id = movieFacade.updateMovieAttrs(movieDetailDTO);
        if (id == null){
            throw new CouldNotCreateException("Could not create movie 😢");
        }
        MovieDetailDTO movieById = movieFacade.findMovieById(id);
        if (movieById == null) {
            throw new ResourceNotFoundException("Movie with id="+id+" not found");
        }
        EntityModel<RepresentationModel<EntityModel<MovieDetailDTO>>> movieDetailDTOEntityModel = movieDetailRepresentationModelAssembler.toModel(movieById);
        return new ResponseEntity<>(movieDetailDTOEntityModel, HttpStatus.OK);
    }

//    void deleteMovie(Long movieId);
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public final HttpEntity<HttpStatus> deleteMovie(@PathVariable long id){
        log.debug("getMovieDetail(id={})", id);

        movieFacade.deleteMovie(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

//    void changeTitleImage(ImageCreateDTO imageCreateDTO);
    @RequestMapping(value = "/changetitle",method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<HttpStatus> changeTitleImage(@RequestBody @Valid ImageCreateDTO imageCreateDTO, BindingResult bindingResult) throws Exception {
        log.debug("changeTitleImage(ImageCreateDTO={})", imageCreateDTO);
        if (bindingResult.hasErrors()){
            log.error("failed validation {}", bindingResult.toString());
            throw new Exception("Failed validation");
        }

        movieFacade.changeTitleImage(imageCreateDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    void addImage(ImageCreateDTO imageCreateDTO);
    @RequestMapping(value = "/addimage",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<HttpStatus> addImage(@RequestBody @Valid ImageCreateDTO imageCreateDTO, BindingResult bindingResult) throws Exception {
        log.debug("addImage(ImageCreateDTO={})", imageCreateDTO);
        if (bindingResult.hasErrors()){
            log.error("failed validation {}", bindingResult.toString());
            throw new Exception("Failed validation");
        }

        movieFacade.addImage(imageCreateDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    void deleteImage(Long imageId);
    @RequestMapping(value = "/image/{id}",method = RequestMethod.DELETE)
    public final HttpEntity<HttpStatus> deleteImage(@PathVariable long id){
        log.debug("deleteImage(id={})", id);

        movieFacade.deleteImage(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    void addActor(PersonToMovieDTO personDTO);

    @RequestMapping(value = "/actor/add",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<HttpStatus> addActor(@RequestBody @Valid PersonToMovieDTO personToMovieDTO, BindingResult bindingResult) throws Exception {
        log.debug("addActor(PersonToMovieDTO={})", personToMovieDTO);
        if (bindingResult.hasErrors()){
            log.error("failed validation {}", bindingResult.toString());
            throw new Exception("Failed validation");
        }

        movieFacade.addActor(personToMovieDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    void deleteActor(PersonToMovieDTO personDTO);
    @RequestMapping(value = "/actor/delete",method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<HttpStatus> deleteActor(@RequestBody @Valid PersonToMovieDTO personToMovieDTO, BindingResult bindingResult) throws Exception {
        log.debug("deleteActor(PersonToMovieDTIO={})", personToMovieDTO);
        if (bindingResult.hasErrors()){
            log.error("failed validation {}", bindingResult.toString());
            throw new Exception("Failed validation");
        }

        movieFacade.deleteActor(personToMovieDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    void addGenre(GenreToMovieDTO genreToMovieDTO);
    @RequestMapping(value = "/genre/add",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<HttpStatus> addGenre(@RequestBody @Valid GenreToMovieDTO genreToMovieDTO, BindingResult bindingResult) throws Exception {
        log.debug("addGenre(GenreToMovie={})", genreToMovieDTO);
        if (bindingResult.hasErrors()){
            log.error("failed validation {}", bindingResult.toString());
            throw new Exception("Failed validation");
        }

        movieFacade.addGenre(genreToMovieDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    void removeGenre(GenreToMovieDTO genreToMovieDTO);
    @RequestMapping(value = "/genre/delete",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<HttpStatus> deleteGenre(@RequestBody @Valid GenreToMovieDTO genreToMovieDTO, BindingResult bindingResult) throws Exception {
        log.debug("addGenre(GenreToMovie={})", genreToMovieDTO);
        if (bindingResult.hasErrors()){
            log.error("failed validation {}", bindingResult.toString());
            throw new Exception("Failed validation");
        }

        movieFacade.removeGenre(genreToMovieDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    void changeDirector(PersonToMovieDTO personDTO);
    @RequestMapping(value = "/director",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<HttpStatus> changeDirector(@RequestBody @Valid PersonToMovieDTO personToMovieDTO, BindingResult bindingResult) throws Exception {
        log.debug("changeDIrector(PersonToMovie={})", personToMovieDTO);
        if (bindingResult.hasErrors()){
            log.error("failed validation {}", bindingResult.toString());
            throw new Exception("Failed validation");
        }

        movieFacade.changeDirector(personToMovieDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
